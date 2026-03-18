<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$query = "SELECT Day FROM workoutday WHERE user_id = $userId ORDER BY datetime DESC";
$result = mysqli_query($conn, $query);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$rows = api_fetch_all($result, function (array $row): string {
    return (string) $row['Day'];
});

api_success($rows);