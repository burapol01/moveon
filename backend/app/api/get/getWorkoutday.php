<?php

putenv('TZ=Asia/Bangkok');
require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$query = "SELECT * FROM workoutday w
          LEFT JOIN categoryex c ON c.category_id = w.category_id
          LEFT JOIN `user` u ON u.user_id = w.user_id
          WHERE u.user_id = $userId";
$result = mysqli_query($conn, $query);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$rows = api_fetch_all($result, function (array $row): array {
    return [
        'user_username' => $row['user_username'],
        'name_del' => $row['name_del'],
        'datetime' => date('d M Y h:i:s A', strtotime($row['datetime'])),
        'picture' => asset_url($row['picture'] ?? ''),
    ];
});

api_success($rows);
