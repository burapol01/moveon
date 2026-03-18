<?php

require_once dirname(__DIR__) . '/bootstrap.php';

$svname = 'localhost';
$usname = 'root';
$pass = '';
$db = 'move_on';

$conn = new mysqli($svname, $usname, $pass, $db);

if ($conn->connect_error) {
    api_error('Database connection failed: ' . $conn->connect_error, 500);
}

$conn->set_charset('utf8');
date_default_timezone_set('Asia/Bangkok');
