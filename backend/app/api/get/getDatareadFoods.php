<?php
require_once dirname(__DIR__, 2) . '/config/connect.php';
$dataFoodId = api_int($_GET, 'datafood_id');
$sql = "SELECT * FROM datafoods df
        LEFT JOIN foodcategory ON df.categoryfood_id = foodcategory.categoryfood_id
        LEFT JOIN mealtime ON df.mealtime_id = mealtime.mealtime_id
        LEFT JOIN dirnkcategory ON df.catdrinks_id = dirnkcategory.catdrinks_id
        WHERE datafood_id = '$dataFoodId'";
$result = $conn->query($sql);
if ($result === false) {
    api_error('Query failed: ' . $conn->error, 500);
}
api_success(api_fetch_all($result));
