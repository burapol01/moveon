<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_POST, 'user_id');
$mode = api_int($_POST, 'mode');

if (!in_array($mode, [0, 1, 2], true)) {
    api_error('Invalid mode value', 422);
}

$createTable = "CREATE TABLE IF NOT EXISTS user_setting (
    user_id INT NOT NULL PRIMARY KEY,
    `mode` INT NOT NULL DEFAULT 0,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8";

if (!mysqli_query($conn, $createTable)) {
    api_error('Cannot prepare user_setting table: ' . mysqli_error($conn), 500);
}

$query = "INSERT INTO user_setting (user_id, `mode`)
          VALUES ($userId, $mode)
          ON DUPLICATE KEY UPDATE `mode` = VALUES(`mode`)";

if (!mysqli_query($conn, $query)) {
    api_error('Error! ' . mysqli_error($conn), 500);
}

api_success([
    'user_id' => $userId,
    'mode' => $mode,
]);