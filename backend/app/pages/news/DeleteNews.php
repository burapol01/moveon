<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';

if (isset($_GET['datanews_id'])) {
    $datanews_id = (int) $_GET['datanews_id'];
    $conn->query("DELETE FROM datanews WHERE datanews_id = {$datanews_id}");
}

header('Location: News.php');
exit;

