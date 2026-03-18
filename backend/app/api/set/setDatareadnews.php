<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$dataNewsId = api_int($_GET, 'datanews_id');
$sql = "INSERT INTO readdatanews (datanews_id,user_id) VALUES('$dataNewsId','$userId')";

if ($conn->query($sql) === true) {
    api_success(null, 'Tracking saved');
}

api_error('Insert failed: ' . $conn->error, 500);
