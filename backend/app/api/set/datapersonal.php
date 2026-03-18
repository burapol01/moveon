<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$name = api_optional($_GET, 'name');
$lastname = api_optional($_GET, 'lastname');

if ($name === '' || $lastname === '') {
    api_error('Missing datapersonal fields', 422);
}

$sql = "INSERT INTO datapersonal (name, lastname) VALUES ('$name', '$lastname')";

if ($conn->query($sql) === true) {
    api_success(null, 'Success');
}

api_error('Insert failed: ' . $conn->error, 500);
