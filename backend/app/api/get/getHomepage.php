<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$sql = "SELECT * FROM datanews ORDER BY RAND() LIMIT 6";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
