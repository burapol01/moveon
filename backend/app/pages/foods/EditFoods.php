<?php 
require_once dirname(__DIR__, 2) . '/shared/head.php';
$userid = (int)($_SESSION["userid"] ?? 0);

$row = null;
if (isset($_GET['datafood_id'])) {
    $datafood_id = (int)$_GET['datafood_id'];
    $sql = "SELECT * FROM datafoods WHERE datafood_id = $datafood_id";
    $query = $conn->query($sql);

    if ($query && $query->num_rows > 0) {
        $row = $query->fetch_assoc();
    }
}

if (!$row) {
    echo "ไม่พบข้อมูลอาหาร";
    exit;
}
?>

<div class="py-5">
  <div class="py-5">
    <div class="container">
      <div class="form-group">
        <div class="col-sm-12 offset-md-3">

          <form name="setnews" method="post" enctype="multipart/form-data">

            <div class="tab-pane fade show active" id="tabone" role="tabpanel">

              <div class="form-group">
                <label class="control-label col-sm-3">หมวดอาหาร</label>
                <div class="col-sm-9">
                  <select name="categoryfood_id" id="categoryfood_id" class="form-control" required="required">
                    <option value="">โปรดเลือก</option>
                    <?php
                    $sql1 = "SELECT * FROM foodcategory";
                    $query1 = $conn->query($sql1);
                    while ($row1 = $query1->fetch_assoc()) {
                        if ($row['categoryfood_id'] == $row1['categoryfood_id']) {
                            echo '<option value="'.$row1['categoryfood_id'].'" selected>'.$row1['name_food'].'</option>';
                        } else {
                            echo '<option value="'.$row1['categoryfood_id'].'">'.$row1['name_food'].'</option>';
                        }
                    }
                    ?>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">ช่วงเวลารับประทานอาหาร</label>
                <div class="col-sm-9">
                  <select name="mealtime_id" id="mealtime_id" class="form-control" required="required">
                    <option value="">โปรดเลือก</option>
                    <?php
                    $sql1 = "SELECT * FROM mealtime";
                    $query1 = $conn->query($sql1);
                    while ($row1 = $query1->fetch_assoc()) {
                        if ($row['mealtime_id'] == $row1['mealtime_id']) {
                            echo '<option value="'.$row1['mealtime_id'].'" selected>'.$row1['mealtime'].'</option>';
                        } else {
                            echo '<option value="'.$row1['mealtime_id'].'">'.$row1['mealtime'].'</option>';
                        }
                    }
                    ?>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label for="toppic_fd" class="col-sm-3 control-label">หัวข้ออาหาร</label>
                <div class="col-sm-9">
                  <input name="toppic_fd" type="text" id="toppic_fd" class="form-control" required="required" value="<?= $row['toppic_fd'] ?>">
                </div>
              </div>

              <div class="form-group">
                <label for="description_fd" class="col-sm-3 control-label">รายละเอียดอาหารเพื่อสุขภาพ</label>
                <div class="col-sm-9">
                  <input name="description_fd" type="text" id="description_fd" required="required" class="form-control" value="<?= $row['description_fd'] ?>">
                </div>
              </div>

              <div class="form-group">
                <div class="container">
                  <img src="<?= asset_url($row['picture_fd']) ?>" width="400" height="300">
                </div> 
              </div>

              <div class="form-group">
                <label for="picfoods" class="col-sm-3 control-label">เพิ่มรูปภาพอาหาร</label>
                <div class="col-sm-9">
                  <input type="file" name="picfoods" id="picfoods" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <label class="control-label col-sm-3">หมวดเครื่องดื่ม</label>
                <div class="col-sm-9">
                  <select name="catdrinks_id" id="catdrinks_id" class="form-control" required="required">
                    <option value="">โปรดเลือก</option>
                    <?php
                    $sql1 = "SELECT * FROM dirnkcategory";
                    $query1 = $conn->query($sql1);
                    while ($row1 = $query1->fetch_assoc()) {
                        if ($row['catdrinks_id'] == $row1['catdrinks_id']) {
                            echo '<option value="'.$row1['catdrinks_id'].'" selected>'.$row1['name_drk'].'</option>';
                        } else {
                            echo '<option value="'.$row1['catdrinks_id'].'">'.$row1['name_drk'].'</option>';
                        }
                    }
                    ?>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label for="toppic_dk" class="col-sm-3 control-label">หัวข้อเครื่องดื่ม</label>
                <div class="col-sm-9">
                  <input name="toppic_dk" type="text" id="toppic_dk" class="form-control" required="required" value="<?= $row['toppic_dk'] ?>">
                </div>
              </div>

              <div class="form-group">
                <label for="description_dk" class="col-sm-3 control-label">รายละเอียดเครื่องดื่มเพื่อสุขภาพ</label>
                <div class="col-sm-9">
                  <input name="description_dk" type="text" id="description_dk" required="required" class="form-control" value="<?= $row['description_dk'] ?>">
                </div>
              </div>

              <div class="form-group">
                <div class="container">
                  <img src="<?= asset_url($row['picture_dk']) ?>" width="400" height="300">
                </div> 
              </div>

              <div class="form-group">
                <label for="picdirnk" class="col-sm-3 control-label">เพิ่มรูปภาพเครื่องดื่ม</label>
                <div class="col-sm-9">
                  <input type="file" name="picdirnk" id="picdirnk" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <div class="container">
                  <input type="submit" name="addfoods" id="button" value="บันทึกข้อมูล" class="btn btn-dark">
                </div>
              </div>

            </div>
          </form>

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

<?php 
if (isset($_POST['addfoods'])) {

    $categoryfood_id = trim($_POST['categoryfood_id']);
    $mealtime_id = trim($_POST['mealtime_id']);
    $toppic_fd = trim($_POST['toppic_fd']);
    $description_fd = trim($_POST['description_fd']);

    $catdrinks_id = trim($_POST['catdrinks_id']);
    $toppic_dk = trim($_POST['toppic_dk']);
    $description_dk = trim($_POST['description_dk']);

    $sql = "UPDATE datafoods SET 
                categoryfood_id = '$categoryfood_id',
                mealtime_id = '$mealtime_id',
                toppic_fd = '$toppic_fd',
                description_fd = '$description_fd',
                catdrinks_id = '$catdrinks_id',
                toppic_dk = '$toppic_dk',
                description_dk = '$description_dk'";

    if (!empty($_FILES["picfoods"]["name"])) {
        $temp = explode(".", $_FILES["picfoods"]["name"]);
        $newfilename = round(microtime(true)) . '.' . end($temp);
        move_uploaded_file($_FILES["picfoods"]["tmp_name"], app_path("img/foods/" . $newfilename));
        $picture_fd = 'img/foods/' . $newfilename;
        $sql .= ", picture_fd = '$picture_fd'";
    }

    if (!empty($_FILES["picdirnk"]["name"])) {
        $temp = explode(".", $_FILES["picdirnk"]["name"]);
        $newfilename2 = round(microtime(true)) . '.' . end($temp);
        move_uploaded_file($_FILES["picdirnk"]["tmp_name"], app_path("img/dirnk/" . $newfilename2));
        $picture_dk = 'img/dirnk/' . $newfilename2;
        $sql .= ", picture_dk = '$picture_dk'";
    }

    $sql .= " WHERE datafood_id = $datafood_id";
        
    if ($conn->query($sql) === TRUE) {
        ?>
        <script>
          alert('แก้ไขสำเร็จ');
          window.location.href = "Foods.php";
        </script>
        <?php
        exit;
    } else {
        ?>
        <script>
          alert('แก้ไขไม่สำเร็จ โปรดลองอีกครั้ง');
          window.location.href = "EditFoods.php?datafood_id=<?= $datafood_id ?>";
        </script>
        <?php
        exit;
    }
}
?>



