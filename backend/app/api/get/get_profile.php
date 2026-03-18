<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$sql = "SELECT picture FROM user WHERE user_id = '$userId' LIMIT 1";
$result = $conn->query($sql);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$profile = api_first($result, function (array $row): array {
    $row['picture'] = asset_url($row['picture'] ?? '');
    return $row;
});

if ($profile === null) {
    api_error('Profile not found', 404);
}

api_success($profile);
