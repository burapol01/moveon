<?php 
require_once dirname(__DIR__, 2) . '/shared/head.php';
$userid = (int)($_SESSION["userid"] ?? 0);

$row = null;
if (isset($_GET['datanews_id'])) {
    $datanews_id = (int)$_GET['datanews_id'];
    $sql = "SELECT * FROM datanews WHERE datanews_id = $datanews_id";
    $query = $conn->query($sql);
    if ($query && $query->num_rows > 0) {
        $row = $query->fetch_assoc();
    }
}

if (!$row) {
    echo "ไม่พบข้อมูลข่าว";
    exit;
}
?>

<div class="py-5">
  <div class="py-5">
    <div class="container">
      <div class="form-group">
        <div class="col-sm-12 offset-md-3">

          <form name="setnews" method="post" enctype="multipart/form-data">

            <div class="form-group">
              <label for="topic_news" class="col-sm-3 control-label">หัวข้อข่าวสาร</label>
              <div class="col-sm-9">
                <input name="topic_news" type="text" id="topic_news" class="form-control" required="required" value="<?= $row['topic_news'] ?>">
              </div>
            </div>

            <div class="form-group">
              <label for="description_news" class="col-sm-3 control-label">รายละเอียดข่าว</label>
              <div class="col-sm-9">
                <input name="description_news" type="text" id="description_news" required="required" class="form-control" value="<?= $row['description_news'] ?>">
              </div>
            </div>

            <div class="form-group">
              <div class="container">
                <img src="<?= asset_url($row['picture_news']) ?>" width="400" height="200">
              </div>
            </div>

            <div class="form-group">
              <label for="fileToUpload" class="col-sm-3 control-label">เพิ่มรูปภาพ</label>
              <div class="col-sm-9">
                <input type="file" name="picnews" id="fileToUpload" class="form-control">
              </div>
            </div>

            <div class="form-group">
              <div class="container">
                <input type="submit" name="addnew" id="button" value="บันทึกข้อมูล" class="btn btn-dark">
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
  <a href="News.php">จัดการข้อมูลข่าวสาร</a>
  <a></a>
  <a></a>
  <a></a> 

  <span class="navbar__user">Staff: <?= app_current_username() ?></span>
</div>

<?php require_once dirname(__DIR__, 2) . '/shared/foot.php'; ?>

<?php 
if (isset($_POST['addnew'])) {
    $topic_news = trim($_POST['topic_news']);
    $description_news = trim($_POST['description_news']);
    $picture_news = '';

    $sql = "UPDATE datanews SET topic_news = '$topic_news', description_news = '$description_news'";

    if (!empty($_FILES["picnews"]["name"])) {
        $temp = explode(".", $_FILES["picnews"]["name"]);
        $newfilename = round(microtime(true)) . '.' . end($temp);
        move_uploaded_file($_FILES["picnews"]["tmp_name"], app_path("img/news/" . $newfilename));
        $picture_news = 'img/news/' . $newfilename;
        $sql .= ", picture_news = '$picture_news'";
    }

    $sql .= " WHERE datanews_id = $datanews_id";

    if ($conn->query($sql) === TRUE) {
        ?>
        <script>
          alert('แก้ไขสำเร็จ');
          window.location.href = "News.php";
        </script>
        <?php
        exit;
    } else {
        ?>
        <script>
          alert('แก้ไขไม่สำเร็จ โปรดลองอีกครั้ง');
          window.location.href = "EditNews.php?datanews_id=<?= $datanews_id ?>";
        </script>
        <?php
        exit;
    }
}
?>



