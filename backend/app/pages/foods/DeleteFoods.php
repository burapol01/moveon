<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';

if (isset($_GET['datafood_id'])) {
    $datafood_id = (int) $_GET['datafood_id'];
    $conn->query("DELETE FROM datafoods WHERE datafood_id = {$datafood_id}");
}

header('Location: Foods.php');
exit;

