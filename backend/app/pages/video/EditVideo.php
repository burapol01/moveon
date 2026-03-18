<?php 
require_once dirname(__DIR__, 2) . '/shared/head.php';
$userid = (int)($_SESSION["userid"] ?? 0);

$row = null;
if (isset($_GET['video_id'])) {
    $video_id = (int)$_GET['video_id'];
    $sql = "SELECT * FROM videofile WHERE video_id = $video_id";
    $query = $conn->query($sql);
    if ($query && $query->num_rows > 0) {
        $row = $query->fetch_assoc();
    }
}

if (!$row) {
    echo "ไม่พบข้อมูลวิดีโอ";
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
                <label for="name_vd" class="col-sm-3 control-label">ชื่อไฟล์วิดีโอ</label>
                <div class="col-sm-9">
                  <input name="name_vd" type="text" id="name_vd" class="form-control" required="required" value="<?= $row['name_vd'] ?>">
                </div>
              </div>

              <div class="form-group">
                <label for="descriptions_vd" class="col-sm-3 control-label">รายละเอียดวิดีโอ</label>
                <div class="col-sm-9">
                  <input name="descriptions_vd" type="text" id="descriptions_vd" required="required" class="form-control" value="<?= $row['descriptions_vd'] ?>">
                </div>
              </div>

              <div class="form-group">
                <div class="container">
                  <img src="<?= asset_url($row['picture_video']) ?>" width="400" height="300">
                </div> 
              </div>  

              <div class="form-group">
                <label for="picture_vdo" class="col-sm-3 control-label">เพิ่มรูปภาพ</label>
                <div class="col-sm-9">
                  <input type="file" name="picture_vdo" id="picture_vdo" class="form-control">
                </div>
              </div>   

              <div class="form-group">
                <div class="container">
                  <div class="col-sm-8">
                    <div class="embed-responsive embed-responsive-16by9">
                      <video src="<?= asset_url($row['video_file']) ?>" class="embed-responsive-item" controls="controls">
                        Your browser does not support HTML5 video.
                      </video>
                    </div>
                  </div>
                </div> 
              </div>     

              <div class="form-group">
                <label for="picexer" class="col-sm-3 control-label">เพิ่มไฟล์วิดีโอ</label>
                <div class="col-sm-9">
                  <input type="file" name="picexer" id="picexer" class="form-control">
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
  <a href="Video.php">จัดการข้อมูลไฟล์วิดีโอ</a>
  <a></a>
  <a></a>
  <a></a> 

  <span class="navbar__user">Staff: <?= app_current_username() ?></span>
</div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>

<?php 
if (isset($_POST['addexer'])) {

    $category_id = trim($_POST['category_id']);
    $name_vd = trim($_POST['name_vd']);
    $descriptions_vd = trim($_POST['descriptions_vd']);

    $sql = "UPDATE videofile 
            SET category_id = '$category_id',
                name_vd = '$name_vd',
                descriptions_vd = '$descriptions_vd'";

    if (!empty($_FILES["picexer"]["name"])) {
        $temp = explode(".", $_FILES["picexer"]["name"]);
        $newfilename = round(microtime(true)) . '.' . end($temp);
        move_uploaded_file($_FILES["picexer"]["tmp_name"], app_path("img/filevideo/" . $newfilename));
        $video_file = 'img/filevideo/' . $newfilename;
        $sql .= ", video_file = '$video_file'";
    }

    if (!empty($_FILES["picture_vdo"]["name"])) {
        $temp = explode(".", $_FILES["picture_vdo"]["name"]);
        $newfilename2 = round(microtime(true)) . '.' . end($temp);
        move_uploaded_file($_FILES["picture_vdo"]["tmp_name"], app_path("img/filevideo/" . $newfilename2));
        $picture_video = 'img/filevideo/' . $newfilename2;
        $sql .= ", picture_video = '$picture_video'";
    }

    $sql .= " WHERE video_id = $video_id";
        
    if ($conn->query($sql) === TRUE) {
        ?>
        <script>
          alert('แก้ไขสำเร็จ');
          window.location.href = "Video.php";
        </script>
        <?php
        exit;
    } else {
        ?>
        <script>
          alert('แก้ไขไม่สำเร็จ โปรดลองอีกครั้ง');
          window.location.href = "EditVideo.php?video_id=<?= $video_id ?>";
        </script>
        <?php
        exit;
    }
}
?>



