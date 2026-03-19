<?php

require_once dirname(__DIR__) . '/bootstrap.php';

$svname = getenv('DB_HOST');
$usname = getenv('DB_USER');
$pass   = getenv('DB_PASS');
$db     = getenv('DB_NAME');
$port   = getenv('DB_PORT') ?: 3306;

$conn = new mysqli($svname, $usname, $pass, $db, (int)$port);

if ($conn->connect_error) {
    api_error('Database connection failed: ' . $conn->connect_error, 500);
}

$conn->set_charset('utf8');
date_default_timezone_set('Asia/Bangkok');