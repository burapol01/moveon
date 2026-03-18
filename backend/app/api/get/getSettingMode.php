<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');

$createTable = "CREATE TABLE IF NOT EXISTS user_setting (
    user_id INT NOT NULL PRIMARY KEY,
    `mode` INT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8";

if (!mysqli_query($conn, $createTable)) {
    api_error('Cannot prepare user_setting table: ' . mysqli_error($conn), 500);
}

$query = "SELECT user_id, `mode` AS mode FROM user_setting WHERE user_id = $userId LIMIT 1";
$result = mysqli_query($conn, $query);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$row = mysqli_fetch_assoc($result);
if ($row === null) {
    api_success([
        'user_id' => $userId,
        'mode' => 0,
    ]);
}

api_success([
    'user_id' => (int) $row['user_id'],
    'mode' => (int) $row['mode'],
]);