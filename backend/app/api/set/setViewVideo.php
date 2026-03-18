<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$videoId = api_int($_GET, 'video_id');
$sql = "INSERT INTO viewdatavideo (video_id,user_id) VALUES('$videoId','$userId')";

if ($conn->query($sql) === true) {
    api_success(null, 'Tracking saved');
}

api_error('Insert failed: ' . $conn->error, 500);
