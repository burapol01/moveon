<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_POST, 'user_id');
$query = "DELETE FROM datapersonal WHERE user_id ='$userId'";

if (mysqli_query($conn, $query)) {
    api_success(null, 'Success');
}

api_error('Error! ' . mysqli_error($conn), 500);
