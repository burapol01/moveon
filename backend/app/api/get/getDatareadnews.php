<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$dataNewsId = api_int($_GET, 'datanews_id');
$sql = "SELECT * FROM datanews WHERE datanews_id = '$dataNewsId'";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
