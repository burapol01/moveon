<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$params = api_required($_GET, ['email', 'user_password']);
$email = mysqli_real_escape_string($conn, $params['email']);
$userPassword = mysqli_real_escape_string($conn, $params['user_password']);

$sql = "SELECT * FROM user WHERE email = '$email' AND user_password = '$userPassword' LIMIT 1";
$result = $conn->query($sql);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$user = api_first($result, function (array $row): array {
    if (isset($row['picture'])) {
        $row['picture'] = asset_url($row['picture']);
    }
    return $row;
});

if ($user === null) {
    api_error('Invalid email or password', 404);
}

api_success($user);
