<?php

putenv('TZ=Asia/Bangkok');
require_once dirname(__DIR__, 2) . '/config/connect.php';

$userId = api_int($_GET, 'user_id');
$query = "SELECT * FROM datapersonal d
          LEFT JOIN `user` u ON u.user_id = d.user_id
          LEFT JOIN titlename t ON t.titelname_id = d.titlename_id
          LEFT JOIN sex s ON s.sex_id = d.sex_id
          LEFT JOIN statuslevel st ON st.stat_lev_id = d.stat_lev_id
          WHERE u.user_id = $userId";
$result = mysqli_query($conn, $query);

if ($result === false) {
    api_error('Query failed: ' . mysqli_error($conn), 500);
}

$rows = api_fetch_all($result, function (array $row): array {
    return [
        'descript_title' => $row['descript_title'],
        'name' => $row['name'],
        'lastname' => $row['lastname'],
        'descript_sex' => $row['descript_sex'],
        'descript_stat' => $row['descript_stat'],
        'weight' => $row['weight'],
        'height' => $row['height'],
        'birth' => date('d M Y', strtotime($row['birth'])),
        'address' => $row['address'],
        'picture' => asset_url($row['picture'] ?? ''),
    ];
});

api_success($rows);
