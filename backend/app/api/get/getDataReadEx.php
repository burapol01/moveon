<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$dataExId = api_int($_GET, 'dataex_id');
$sql = "SELECT * FROM dataexercises dx
        LEFT JOIN categoryex cx ON cx.category_id = dx.category_id
        WHERE dataex_id = '$dataExId'";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
