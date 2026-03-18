<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';

if (isset($_GET['video_id'])) {
    $video_id = (int) $_GET['video_id'];
    $conn->query("DELETE FROM videofile WHERE video_id = {$video_id}");
}

header('Location: Video.php');
exit;

