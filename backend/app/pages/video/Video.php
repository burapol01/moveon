<?php
require_once dirname(__DIR__, 2) . '/shared/head.php';
$userid = (int)($_SESSION["userid"] ?? 0);
?>
  
<div class="py-5">
  <div class="py-5">
    <div class="container">
      <div class="row">
        <div class="col-md-12 offset-md-1">
          
          <div class="mb-3 text-right">
            <a href="InsertVideo.php" class="btn btn-dark btn-xs"><b>+</b> เพิ่มข้อมูลไฟล์วิดีโอ</a>
          </div>

          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="thead-dark">
                <tr>
                  <th>ลำดับที่</th>
                  <th>วันที่เพิ่ม</th>
                  <th>หมวด</th>
                  <th>ไฟล์วิดีโอ</th>
                  <th width="50"></th>
                  <th width="50"></th>
                </tr>
              </thead>
              <tbody>
                <?php
                $sql = "SELECT * FROM `videofile` v
                        LEFT JOIN categoryex c ON c.category_id = v.category_id";
                $query = $conn->query($sql);
                $i = 1;

                if ($query && $query->num_rows > 0) {
                    while ($row = $query->fetch_assoc()) {
                        echo '<tr>
                                <td>'.$i++.'</td>
                                <td>'.$row["datetime_vd"].'</td>
                                <td>'.$row["name_del"].'</td>
                                <td>'.$row["video_file"].'</td>
                                <td><a href="EditVideo.php?video_id='.$row["video_id"].'" class="btn btn-primary btn-xs pull-right"><b>+</b>แก้ไข</a></td>
                                <td><a href="DeleteVideo.php?video_id='.$row["video_id"].'" class="btn btn-primary btn-xs pull-right">ลบ</a></td>
                              </tr>';
                    }
                } else {
                    echo '<tr><td colspan="6" class="text-center">ไม่พบข้อมูลไฟล์วิดีโอ</td></tr>';
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
  <a href="Video.php">จัดการข้อมูลไฟล์วิดีโอ</a>
  <a></a>
  <a></a>
  <a></a> 

  <span class="navbar__user">Staff: <?= app_current_username() ?></span>
</div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>


