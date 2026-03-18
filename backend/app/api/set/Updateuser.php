<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_POST, 'user_id');
$email = api_optional($_POST, 'email');
$userUsername = api_optional($_POST, 'user_username');
$userPassword = api_optional($_POST, 'user_password');
$picture = api_optional($_POST, 'picture');

if ($email === '' || $userUsername === '' || $userPassword === '') {
    api_error('Missing required profile fields', 422);
}

$query = "UPDATE user SET
            email='$email',
            user_username='$userUsername',
            user_password='$userPassword'
          WHERE user_id ='$userId'";

if (!mysqli_query($conn, $query)) {
    api_error('Error! ' . mysqli_error($conn), 500);
}

if ($picture !== '') {
    $path = "/img/profile/$userId.jpeg";

    if (file_put_contents(app_path($path), base64_decode($picture)) === false) {
        api_error('Error! Unable to save picture file', 500);
    }

    $insertPicture = "UPDATE user SET picture='$path' WHERE user_id='$userId'";
    if (!mysqli_query($conn, $insertPicture)) {
        api_error('Error! ' . mysqli_error($conn), 500);
    }
}

api_success(null, 'Success');