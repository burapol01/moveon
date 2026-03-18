<?php

require_once dirname(__DIR__, 2) . '/config/connect.php';

$bmiResult = api_optional($_POST, 'bmiresult');
$descriptionBmi = api_optional($_POST, 'description_bmi');
$userId = api_int($_POST, 'user_id');

if ($bmiResult === '' || $descriptionBmi === '') {
    api_error('Missing BMI payload', 422);
}

$query = "INSERT INTO bmi (bmiresult,description_bmi,user_id)
          VALUES ('$bmiResult','$descriptionBmi','$userId')";

if (mysqli_query($conn, $query)) {
    api_success(null, 'Success');
}

api_error('Error! ' . mysqli_error($conn), 500);
