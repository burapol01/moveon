<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$email = api_optional($_POST, 'email');
$userUsername = api_optional($_POST, 'user_username');
$userPassword = api_optional($_POST, 'user_password');
$picture = api_optional($_POST, 'picture');

if ($email === '' || $userUsername === '' || $userPassword === '') {
    api_error('Missing required registration fields', 422);
}

$query = "INSERT INTO user (email, user_username, user_password, status_id)
          VALUES ('$email', '$userUsername', '$userPassword', 2)";

if (!mysqli_query($conn, $query)) {
    api_error('Error! ' . mysqli_error($conn), 500);
}

$id = (int) mysqli_insert_id($conn);
$finalPath = '/pet_logo.png';

if ($picture !== '') {
    $path = "/img/profile/$id.jpeg";
    $finalPath = $path;

    if (file_put_contents(app_path($path), base64_decode($picture)) === false) {
        api_error('Error! Unable to save picture file', 500);
    }
}

$insertPicture = "UPDATE user SET picture='$finalPath' WHERE user_id='$id'";
if (!mysqli_query($conn, $insertPicture)) {
    api_error('Error! ' . mysqli_error($conn), 500);
}

api_success([
    'user_id' => $id,
    'picture' => asset_url($finalPath),
], 'Success');