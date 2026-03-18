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
            <a href="InsertNews.php" class="btn btn-dark btn-xs"><b>+</b> เพิ่มข้อมูลข่าวสาร</a>
          </div>

          <div class="table-responsive">
            <table class="table table-bordered">
              <thead class="thead-dark">
                <tr>
                  <th>ลำดับที่</th>
                  <th>วันที่เพิ่ม</th>
                  <th>หัวข้อข่าวสาร</th>
                  <th width="50"></th>
                  <th width="50"></th>
                </tr>
              </thead>
              <tbody>
                <?php
                $sql = "SELECT * FROM `datanews`";
                $query = $conn->query($sql);
                $i = 1;

                if ($query && $query->num_rows > 0) {
                    while ($row = $query->fetch_assoc()) {
                        echo '<tr>
                                <td>'.$i++.'</td>
                                <td>'.$row["datetime_news"].'</td>
                                <td>'.$row["topic_news"].'</td>
                                <td><a href="EditNews.php?datanews_id='.$row["datanews_id"].'" class="btn btn-primary btn-xs pull-right"><b>+</b>แก้ไข</a></td>
                                <td><a href="DeleteNews.php?datanews_id='.$row["datanews_id"].'" class="btn btn-primary btn-xs pull-right">ลบ</a></td>
                              </tr>';
                    }
                } else {
                    echo '<tr><td colspan="5" class="text-center">ไม่พบข้อมูลข่าวสาร</td></tr>';
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
  <a href="News.php">จัดการข้อมูลข่าวสาร</a>
  <a></a>
  <a></a>
  <a></a>
 <span class="navbar__user">Staff: <?= app_current_username() ?></span>
</div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>


