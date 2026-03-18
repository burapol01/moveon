<?php
session_start();
require_once dirname(__DIR__, 2) . '/config/connect.php';

if (isset($_POST["login"])) {
    $login_input = trim($_POST['email']);
    $user_password = trim($_POST['user_password']);

    $stmt = $conn->prepare("SELECT * FROM user WHERE (email = ? OR user_username = ?) AND user_password = ? AND status_id = 1");
    $stmt->bind_param("sss", $login_input, $login_input, $user_password);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows > 0) {
        $rows = $result->fetch_assoc();
        $_SESSION["username"] = $rows['user_username'];
        $_SESSION["userid"] = $rows['user_id'];
        ?>
        <script>
            alert('Login successful');
            window.location.href = "<?= app_url('app/pages/news/News.php') ?>";
        </script>
        <?php
        exit;
    }

    ?>
    <script>
        alert('Invalid username or password');
        window.location.href = "index.php";
    </script>
    <?php
    exit;
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">
  <title>MOVE ON Login</title>
  <style>
    body {
      min-height: 100vh;
      margin: 0;
      font-family: "Lato", sans-serif;
      background:
        linear-gradient(135deg, rgba(10, 41, 64, 0.92), rgba(15, 118, 110, 0.82)),
        url('https://images.unsplash.com/photo-1534438327276-14e5300c3a48?ixlib=rb-1.2.1&auto=format&fit=crop&w=1400&q=80') center/cover no-repeat;
      color: #102a43;
      display: flex;
      align-items: center;
      justify-content: center;
      padding: 24px;
    }

    .login-shell {
      width: 100%;
      max-width: 1040px;
      display: grid;
      grid-template-columns: 1.1fr 0.9fr;
      border-radius: 32px;
      overflow: hidden;
      background: rgba(255,255,255,0.95);
      box-shadow: 0 28px 60px rgba(15, 23, 42, 0.24);
      backdrop-filter: blur(10px);
    }

    .login-brand {
      padding: 48px;
      background: linear-gradient(180deg, rgba(10, 41, 64, 0.96), rgba(18, 50, 75, 0.88));
      color: #fff;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      gap: 24px;
    }

    .login-brand__eyebrow {
      display: inline-flex;
      align-items: center;
      gap: 8px;
      width: fit-content;
      padding: 8px 14px;
      border-radius: 999px;
      background: rgba(255,255,255,0.1);
      font-size: 0.9rem;
      letter-spacing: 0.08em;
      text-transform: uppercase;
    }

    .login-brand h1 {
      font-size: clamp(2rem, 4vw, 3.5rem);
      margin: 0 0 14px;
      line-height: 1.05;
      letter-spacing: 0.04em;
    }

    .login-brand p {
      margin: 0;
      max-width: 420px;
      color: rgba(255,255,255,0.82);
      font-size: 1rem;
      line-height: 1.7;
    }

    .login-points {
      display: grid;
      gap: 14px;
      margin: 0;
      padding: 0;
      list-style: none;
    }

    .login-points li {
      display: flex;
      align-items: center;
      gap: 12px;
      color: rgba(255,255,255,0.88);
    }

    .login-points i {
      width: 38px;
      height: 38px;
      display: inline-flex;
      align-items: center;
      justify-content: center;
      border-radius: 12px;
      background: rgba(255,255,255,0.1);
    }

    .login-panel {
      padding: 48px 42px;
      display: flex;
      flex-direction: column;
      justify-content: center;
    }

    .login-panel h2 {
      margin: 0 0 10px;
      font-size: 2rem;
      font-weight: 700;
    }

    .login-panel p {
      margin: 0 0 28px;
      color: #5c6f82;
      line-height: 1.7;
    }

    .login-form .form-group {
      margin-bottom: 18px;
    }

    .login-form label {
      font-weight: 700;
      color: #102a43;
      margin-bottom: 8px;
    }

    .login-form .form-control {
      height: 52px;
      border-radius: 16px;
      border: 1px solid rgba(16, 42, 67, 0.12);
      background: #f8fbfc;
      padding: 0 16px;
    }

    .login-form .form-control:focus {
      border-color: rgba(15, 118, 110, 0.36);
      box-shadow: 0 0 0 0.2rem rgba(15, 118, 110, 0.12);
      background: #fff;
    }

    .login-form .btn {
      width: 100%;
      height: 54px;
      border: 0;
      border-radius: 999px;
      font-weight: 700;
      background: linear-gradient(135deg, #0f766e 0%, #159a8f 100%);
    }

    .login-form .btn:hover,
    .login-form .btn:focus {
      background: linear-gradient(135deg, #0b5e58 0%, #127e75 100%);
    }

    .login-note {
      margin-top: 18px;
      font-size: 0.92rem;
      color: #7b8794;
      text-align: center;
    }

    @media (max-width: 991.98px) {
      .login-shell {
        grid-template-columns: 1fr;
        max-width: 560px;
      }

      .login-brand,
      .login-panel {
        padding: 32px 24px;
      }
    }
  </style>
</head>
<body>
  <div class="login-shell">
    <section class="login-brand">
      <div>
        <span class="login-brand__eyebrow"><i class="fas fa-heartbeat"></i> MOVE ON</span>
        <h1>Health, Fitness, and Content Management</h1>
        <p>Manage wellness news, workout videos, exercise content, and healthy meal plans from one responsive dashboard.</p>
      </div>
      <ul class="login-points">
        <li><i class="fas fa-newspaper"></i> Publish and maintain health news content</li>
        <li><i class="fas fa-video"></i> Upload videos and supporting media files</li>
        <li><i class="fas fa-utensils"></i> Curate nutrition and meal planning data</li>
      </ul>
    </section>

    <section class="login-panel">
      <div>
        <h2>Sign In</h2>
        <p>Use your admin email or username and password to access the MOVE ON control panel.</p>
      </div>

      <form method="post" class="login-form">
        <div class="form-group">
          <label for="email">Email or Username</label>
          <input type="text" name="email" id="email" class="form-control" placeholder="Enter your email or username" required>
        </div>
        <div class="form-group">
          <label for="user_password">Password</label>
          <input type="password" name="user_password" id="user_password" class="form-control" placeholder="Enter your password" required>
        </div>
        <button type="submit" name="login" class="btn btn-primary">Sign In</button>
      </form>

      <div class="login-note">MOVE ON Admin Panel</div>
    </section>
  </div>

  <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" crossorigin="anonymous"></script>
</body>
</html>
