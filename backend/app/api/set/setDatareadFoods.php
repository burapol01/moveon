<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$dataFoodId = api_int($_GET, 'datafood_id');
$sql = "INSERT INTO readdatafoods (datafood_id,user_id) VALUES('$dataFoodId','$userId')";

if ($conn->query($sql) === true) {
    api_success(null, 'Tracking saved');
}

api_error('Insert failed: ' . $conn->error, 500);
