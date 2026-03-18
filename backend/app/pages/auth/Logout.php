<!DOCTYPE html>
<?php
session_start();
ob_start();
error_reporting(~E_NOTICE);
require_once dirname(__DIR__, 2) . '/config/connect.php';
session_destroy();
header('Location: index.php');
exit();
?>

