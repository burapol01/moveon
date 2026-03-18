<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$titlenameId = api_int($_POST, 'titlename_id');
$name = api_optional($_POST, 'name');
$lastname = api_optional($_POST, 'lastname');
$sexId = api_int($_POST, 'sex_id');
$statLevId = api_int($_POST, 'stat_lev_id');
$weight = api_optional($_POST, 'weight');
$height = api_optional($_POST, 'height');
$birth = api_optional($_POST, 'birth');
$address = api_optional($_POST, 'address');
$userId = api_int($_POST, 'user_id');

if ($name === '' || $lastname === '' || $weight === '' || $height === '' || $birth === '' || $address === '') {
    api_error('Missing datapersonal fields', 422);
}

$birthNewFormat = date('Y-m-d', strtotime($birth));
$query = "INSERT INTO datapersonal (
            titlename_id, name, lastname, sex_id, stat_lev_id, weight, height, birth, address, user_id
          ) VALUES (
            '$titlenameId', '$name', '$lastname', '$sexId', '$statLevId', '$weight', '$height', '$birthNewFormat', '$address', '$userId'
          )";

if (mysqli_query($conn, $query)) {
    api_success(null, 'Success');
}

api_error('Error! ' . mysqli_error($conn), 500);
