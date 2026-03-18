<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$day = api_optional($_GET, 'Day');
$categoryId = api_int($_GET, 'category_id');
$userId = api_int($_GET, 'user_id');

if ($day === '') {
    api_error('Missing parameter: Day', 422);
}

$query = "INSERT INTO workoutday (Day, category_id, user_id)
          VALUES ('$day', '$categoryId', '$userId')";

if (mysqli_query($conn, $query)) {
    api_success(null, 'Success');
}

api_error('Error! ' . mysqli_error($conn), 500);
