<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$sql = "SELECT DISTINCT (datanews_id) AS ID, SUM(readnews_stat) AS Views FROM readdatanews WHERE datanews_id = 2";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
