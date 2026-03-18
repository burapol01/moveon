<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$query = "SELECT * FROM user WHERE user_id = $userId";
$result = mysqli_query($conn, $query);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$rows = api_fetch_all($result, function (array $row): array {
    return [
        'user_username' => $row['user_username'],
        'email' => $row['email'],
        'picture' => asset_url($row['picture'] ?? ''),
    ];
});

api_success($rows);
