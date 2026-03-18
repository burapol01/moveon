<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$sql = "SELECT * FROM dataexercises d
        LEFT JOIN categoryex c ON c.category_id = d.category_id
        WHERE name_del LIKE 'Y%'";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
