<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$videoId = api_int($_GET, 'video_id');
$sql = "SELECT * FROM videofile vd
        LEFT JOIN categoryex ct ON ct.category_id = vd.category_id
        WHERE video_id = '$videoId'";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
