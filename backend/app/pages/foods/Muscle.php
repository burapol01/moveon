<?php
require_once dirname(__DIR__, 2) . '/shared/head.php';
$userid = (int)($_SESSION["userid"] ?? 0);
?>
  
<div class="py-5">
  <div class="py-5">
    <div class="container">
          <div class="row">
        <div class="col-md-12 offset-md-1">
          
          <div class="row mb-3">
            <div class="col-md-12">
              <a class="btn btn-outline-primary" href="Foods.php">ลดน้ำหนัก</a>
              <a class="btn btn-primary" href="Muscle.php">เพิ่มกล้ามเนื้อ</a>
            </div>
          </div>

          <div class="mb-3 text-right">
            <a href="InsertFoods.php" class="btn btn-dark btn-xs"><b>+</b> เพิ่มข้อมูลสำหรับอาหารเพื่อสุขภาพ</a>
              </div>

          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="thead-dark">
                 <tr>
            <th>ลำดับที่</th>
            <th>วันที่เพิ่ม</th>
            <th>หมวดอาหาร</th>
            <th>ช่วงเวลารับประทานอาหาร</th>
            <th>หัวข้ออาหาร</th>
            <th>หมวดเครื่องดื่ม</th>
            <th>หัวข้อเครื่องดื่ม</th>
            <th width="50"></th>
            <th width="50"></th>
           </tr>
              </thead>
              <tbody>
                  <?php
                      $sql = "SELECT * FROM `datafoods` df
                              LEFT JOIN foodcategory fc ON fc.categoryfood_id = df.categoryfood_id
                              LEFT JOIN dirnkcategory dk ON dk.catdrinks_id = df.catdrinks_id
                              LEFT JOIN mealtime m ON m.mealtime_id = df.mealtime_id
                              WHERE name_food LIKE '%เพิ่มกล้ามเนื้อ'";
                      $query = $conn->query($sql);
                      $i = 1;

                if ($query && $query->num_rows > 0) {
                    while ($row = $query->fetch_assoc()) {
                        echo '<tr>
                                <td>'.$i++.'</td>
                                <td>'.$row["datetime_fd"].'</td>
                                <td>'.$row["name_food"].'</td>
                                <td>'.$row["mealtime"].'</td>
                                <td>'.$row["toppic_fd"].'</td>
                                <td>'.$row["name_drk"].'</td>
                                <td>'.$row["toppic_dk"].'</td>
                                <td><a href="EditFoods.php?datafood_id='.$row["datafood_id"].'" class="btn btn-primary btn-xs pull-right"><b>+</b>แก้ไข</a></td>
                                <td><a href="DeleteFoods.php?datafood_id='.$row["datafood_id"].'" class="btn btn-primary btn-xs pull-right">ลบ</a></td>
                              </tr>';
                      }
                } else {
                    echo '<tr><td colspan="9" class="text-center">ไม่พบข้อมูลอาหารเพื่อสุขภาพ</td></tr>';
                }
                    ?>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

<div class="navbar">     
<a></a>
       <a></a>
      <a></a>
       <a></a>
      <a></a>
      <a></a>
  <a href="Foods.php">จัดการข้อมูลอาหารเพื่อสุขภาพ</a>
      <a></a>
    <a></a>
 <a></a> 

  <span class="navbar__user">Staff: <?= app_current_username() ?></span>
  </div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>


