<?php 
require_once dirname(__DIR__, 2) . '/shared/head.php';
$userid = (int)($_SESSION["userid"] ?? 0);

$row = null;
if (isset($_GET['dataex_id'])) {
    $dataex_id = (int)$_GET['dataex_id'];
    $sql = "SELECT * FROM dataexercises WHERE dataex_id = $dataex_id";
    $query = $conn->query($sql);

    if ($query && $query->num_rows > 0) {
        $row = $query->fetch_assoc();
    }
}

if (!$row) {
    echo "ไม่พบข้อมูลท่าออกกำลังกาย";
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
                <label class="control-label col-sm-3">หมวด</label>
                <div class="col-sm-9">
                  <select name="category_id" size="1" id="category_id" class="form-control" required="required">
                    <option value="">โปรดเลือก</option>
                    <?php
                    $sql1 = "SELECT * FROM categoryex";
                    $query1 = $conn->query($sql1);
                    while ($row1 = $query1->fetch_assoc()) {
                        if ($row['category_id'] == $row1['category_id']) {
                            echo '<option value="'.$row1['category_id'].'" selected>'.$row1['name_del'].'</option>';
                        } else {
                            echo '<option value="'.$row1['category_id'].'">'.$row1['name_del'].'</option>';
                        }
                    }
                    ?>
                  </select>
                </div>
              </div>

              <div class="form-group">
                <label for="name_ex" class="col-sm-3 control-label">ชื่อท่า</label>
                <div class="col-sm-9">
                  <input name="name_ex" type="text" id="name_ex" class="form-control" required="required" value="<?= $row['name_ex'] ?>">
                </div>
              </div>

              <div class="form-group">
                <label for="description_ex" class="col-sm-3 control-label">รายละเอียดท่าออกกำลังกาย</label>
                <div class="col-sm-9">
                  <input name="description_ex" type="text" id="description_ex" required="required" class="form-control" value="<?= $row['description_ex'] ?>">
                </div>
              </div>

              <div class="form-group">
                <div class="container">
                  <div class="col-sm-12 offset-md-3">
                    <img src="<?= asset_url($row['picture_ex']) ?>" width="160" height="300">
                  </div>
                </div> 
              </div>

              <div class="form-group">
                <label for="fileToUpload" class="col-sm-3 control-label">เพิ่มรูปภาพ</label>
                <div class="col-sm-9">
                  <input type="file" name="picexer" id="fileToUpload" class="form-control">
                </div>
              </div>

              <div class="form-group">
                <div class="container">
                  <input type="submit" name="addexer" id="button" value="บันทึกข้อมูล" class="btn btn-dark">
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
  <a href="Exercises.php">จัดการข้อมูลท่าออกกำลังกาย</a>
  <a></a>
  <a></a>
  <a></a> 

  <span class="navbar__user">Staff: <?= app_current_username() ?></span>
</div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>

<?php 
if (isset($_POST['addexer'])) {

    $category_id = trim($_POST['category_id']);
    $name_ex = trim($_POST['name_ex']);
    $description_ex = trim($_POST['description_ex']);

    $sql = "UPDATE dataexercises 
            SET category_id = '$category_id',
                name_ex = '$name_ex',
                description_ex = '$description_ex'";

    if (!empty($_FILES["picexer"]["name"])) {
        $temp = explode(".", $_FILES["picexer"]["name"]);
        $newfilename = round(microtime(true)) . '.' . end($temp);
        move_uploaded_file($_FILES["picexer"]["tmp_name"], app_path("img/exercises/" . $newfilename));
        $picture_ex = 'img/exercises/' . $newfilename;
        $sql .= ", picture_ex = '$picture_ex'";
    }

    $sql .= " WHERE dataex_id = $dataex_id";
        
    if ($conn->query($sql) === TRUE) {
        ?>
        <script>
          alert('แก้ไขสำเร็จ');
          window.location.href = "Exercises.php";
        </script>
        <?php
        exit;
    } else {
        ?>
        <script>
          alert('แก้ไขไม่สำเร็จ โปรดลองอีกครั้ง');
          window.location.href = "EditExer.php?dataex_id=<?= $dataex_id ?>";
        </script>
        <?php
        exit;
    }
}
?>



