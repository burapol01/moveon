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
              <a class="btn btn-primary" href="Exercises.php">T25</a>
              <a class="btn btn-outline-primary" href="Yoga.php">YOGA</a>
            </div>
          </div>
          
          <div class="mb-3 text-right">
            <a href="InsertExer.php" class="btn btn-dark btn-xs"><b>+</b> เพิ่มข้อมูลท่าออกกำลังกาย</a>
          </div>

          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="thead-dark">
                <tr>
                  <th>ลำดับที่</th>
                  <th>วันที่เพิ่ม</th>
                  <th>หมวด</th>
                  <th>ชื่อท่า</th>
                  <th>ไฟล์ภาพ</th>
                  <th width="50"></th>
                  <th width="50"></th>
                </tr>
              </thead>
              <tbody>
                <?php
                $sql = "SELECT * FROM `dataexercises` d
                        LEFT JOIN categoryex c ON c.category_id = d.category_id
                        WHERE name_del LIKE 'T%'";
                $query = $conn->query($sql);
                $i = 1;

                if ($query && $query->num_rows > 0) {
                    while ($row = $query->fetch_assoc()) {
                        echo '<tr>
                                <td>'.$i++.'</td>
                                <td>'.$row["datetime_ex"].'</td>
                                <td>'.$row["name_del"].'</td>
                                <td>'.$row["name_ex"].'</td>
                                <td><img src="'.asset_url($row["picture_ex"]).'" width="80"></td>
                                <td><a href="EditExer.php?dataex_id='.$row["dataex_id"].'" class="btn btn-primary btn-xs pull-right"><b>+</b>แก้ไข</a></td>
                                <td><a href="DeleteExer.php?dataex_id='.$row["dataex_id"].'" class="btn btn-primary btn-xs pull-right">ลบ</a></td>
                              </tr>';
                    }
                } else {
                    echo '<tr><td colspan="7" class="text-center">ไม่พบข้อมูลท่าออกกำลังกาย</td></tr>';
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
  <a href="Exercises.php">จัดการข้อมูลท่าออกกำลังกาย</a>
  <a></a>
  <a></a>
  <a></a> 

  <span class="navbar__user">Staff: <?= app_current_username() ?></span>
</div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>



