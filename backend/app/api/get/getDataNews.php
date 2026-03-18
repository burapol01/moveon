<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$sql = "SELECT * FROM datanews ORDER BY datetime_news DESC";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
