<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$dataExId = api_int($_GET, 'dataex_id');
$sql = "INSERT INTO readdataex (dataex_id,user_id) VALUES('$dataExId','$userId')";

if ($conn->query($sql) === true) {
    api_success(null, 'Tracking saved');
}

api_error('Insert failed: ' . $conn->error, 500);
