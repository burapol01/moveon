<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';

if (isset($_GET['dataex_id'])) {
    $dataex_id = (int) $_GET['dataex_id'];
    $conn->query("DELETE FROM dataexercises WHERE dataex_id = {$dataex_id}");
}

header('Location: Exercises.php');
exit;

