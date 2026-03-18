<!DOCTYPE html>
<?php
ob_start();
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}
error_reporting(E_ALL & ~E_NOTICE);
require_once __DIR__ . '/../config/connect.php';

if (!isset($_SESSION["userid"]) || !isset($_SESSION["username"]) || $_SESSION["userid"] == '' || $_SESSION["username"] == '') {
    header("Location: " . app_url('app/pages/auth/index.php'));
    exit;
}
?>
<html>

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">

<style>
:root {
  --sidebar-width: 268px;
  --surface: #ffffff;
  --surface-soft: #f8fbfc;
  --ink: #102a43;
  --muted: #5c6f82;
  --line: rgba(16, 42, 67, 0.08);
  --nav: #0a2940;
  --accent: #0f766e;
  --accent-dark: #0b5e58;
  --accent-soft: rgba(15, 118, 110, 0.10);
  --danger-soft: rgba(185, 28, 28, 0.08);
  --shadow: 0 20px 45px rgba(15, 23, 42, 0.12);
  --shadow-soft: 0 14px 30px rgba(15, 23, 42, 0.07);
}

*,
*::before,
*::after {
  box-sizing: border-box;
}

body {
  margin: 0;
  min-height: 100vh;
  font-family: "Lato", sans-serif;
  color: var(--ink);
  background:
    radial-gradient(circle at top left, rgba(15, 118, 110, 0.12), transparent 28%),
    linear-gradient(180deg, #f8fafc 0%, #eef4f7 100%);
  padding-left: var(--sidebar-width);
}

.sidenav {
  position: fixed;
  top: 0;
  left: 0;
  z-index: 1040;
  width: var(--sidebar-width);
  height: 100vh;
  overflow-x: hidden;
  overflow-y: auto;
  padding-top: 22px;
  background: linear-gradient(180deg, #0a2940 0%, #12324b 100%);
  box-shadow: var(--shadow);
  transition: transform .25s ease;
}

.sidenav header {
  font-size: 22px;
  color: #fff;
  line-height: 70px;
  text-align: center;
  user-select: none;
  letter-spacing: 0.14em;
}

.sidenav ul {
  list-style: none;
  margin: 0;
  padding: 0;
}

.sidenav ul a {
  display: block;
  width: 100%;
  padding: 18px 22px;
  line-height: 1.4;
  font-size: 16px;
  color: rgba(255,255,255,0.92);
  text-decoration: none;
  border-bottom: 1px solid rgba(255,255,255,0.06);
  border-top: 1px solid rgba(255,255,255,0.04);
  transition: .25s ease;
}

.sidenav li:hover a,
.sidenav li:focus-within a {
  padding-left: 30px;
  background: rgba(255,255,255,0.08);
}

.sidenav ul a i {
  width: 24px;
  margin-right: 14px;
}

.mobile-topbar {
  display: none;
  align-items: center;
  gap: 12px;
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  z-index: 1050;
  height: 64px;
  padding: 0 16px;
  background: rgba(10, 41, 64, 0.96);
  color: #fff;
  box-shadow: 0 10px 30px rgba(15, 23, 42, 0.18);
}

.mobile-topbar__brand {
  font-size: 1rem;
  font-weight: 700;
  letter-spacing: 0.12em;
}

.app-nav-toggle {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 42px;
  height: 42px;
  border: 0;
  border-radius: 999px;
  background: transparent;
  color: #fff;
  font-size: 1.1rem;
}

.app-nav-toggle:focus {
  outline: 2px solid rgba(255,255,255,0.35);
  outline-offset: 2px;
}

.nav-backdrop {
  position: fixed;
  inset: 0;
  z-index: 1035;
  background: rgba(15, 23, 42, 0.45);
  opacity: 0;
  visibility: hidden;
  transition: opacity .2s ease;
}

.container {
  width: min(100%, 1240px);
}

.py-5 {
  padding-top: 2rem !important;
  padding-bottom: 2rem !important;
}

.offset-md-1,
.offset-md-3 {
  margin-left: 0;
}

.col-md-12.offset-md-1,
.col-sm-12.offset-md-3 {
  flex: 0 0 100%;
  max-width: 100%;
}

.col-sm-12.offset-md-3 {
  width: 100%;
  max-width: 860px;
  margin: 0 auto;
}

.col-md-12.offset-md-1,
.col-sm-12.offset-md-3,
.col-sm-12.offset-md-3 > form {
  position: relative;
}

.col-md-12.offset-md-1,
.col-sm-12.offset-md-3 > form {
  background: var(--surface);
  border: 1px solid var(--line);
  border-radius: 28px;
  box-shadow: var(--shadow-soft);
}

.col-md-12.offset-md-1 {
  padding: 24px;
}

.col-sm-12.offset-md-3 > form {
  padding: 28px;
}

.mb-3.text-right,
.row.mb-3 {
  margin-bottom: 1.25rem !important;
}

.row.mb-3 .col-md-12 {
  display: flex;
  flex-wrap: wrap;
  gap: 12px;
}

.btn {
  border-radius: 999px;
  font-weight: 700;
  padding: 0.72rem 1.15rem;
  border-width: 1px;
  box-shadow: none;
}

.btn-xs {
  font-size: 0.92rem;
}

.btn-primary,
.btn-dark {
  border-color: transparent;
}

.btn-primary {
  background: linear-gradient(135deg, var(--accent) 0%, #159a8f 100%);
}

.btn-primary:hover,
.btn-primary:focus {
  background: linear-gradient(135deg, var(--accent-dark) 0%, #127e75 100%);
}

.btn-outline-primary {
  border-color: rgba(15, 118, 110, 0.28);
  color: var(--accent);
  background: var(--accent-soft);
}

.btn-outline-primary:hover,
.btn-outline-primary:focus {
  background: rgba(15, 118, 110, 0.18);
  color: var(--accent-dark);
  border-color: rgba(15, 118, 110, 0.32);
}

.btn-dark {
  background: linear-gradient(135deg, #102a43 0%, #1f4564 100%);
}

.btn-dark:hover,
.btn-dark:focus {
  background: linear-gradient(135deg, #0c2236 0%, #173853 100%);
}

.table-responsive {
  overflow: auto;
  background: var(--surface);
  border: 1px solid var(--line);
  border-radius: 22px;
  box-shadow: inset 0 1px 0 rgba(255,255,255,0.4);
}

.table-responsive.is-enhanced {
  max-height: 540px;
  overflow: auto;
  scrollbar-width: thin;
}

.table-responsive.is-enhanced::-webkit-scrollbar {
  width: 10px;
  height: 10px;
}

.table-responsive.is-enhanced::-webkit-scrollbar-thumb {
  background: rgba(16, 42, 67, 0.18);
  border-radius: 999px;
}

.table-responsive.is-enhanced .table thead th {
  position: sticky;
  top: 0;
  z-index: 3;
}

.table {
  margin-bottom: 0;
}

.table-pagination {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 12px;
  flex-wrap: wrap;
  margin-top: 16px;
}

.table-pagination__meta {
  color: var(--muted);
  font-size: 0.92rem;
}

.table-pagination__pages {
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  gap: 8px;
}

.table-pagination__button {
  min-width: 40px;
  height: 40px;
  padding: 0 12px;
  border: 1px solid rgba(16, 42, 67, 0.1);
  border-radius: 999px;
  background: #fff;
  color: var(--ink);
  font-weight: 700;
  line-height: 1;
}

.table-pagination__button:hover,
.table-pagination__button:focus {
  border-color: rgba(15, 118, 110, 0.35);
  background: rgba(15, 118, 110, 0.08);
  color: var(--accent-dark);
}

.table-pagination__button.is-active {
  border-color: transparent;
  background: linear-gradient(135deg, var(--accent) 0%, #159a8f 100%);
  color: #fff;
}

.table-pagination__button[disabled] {
  opacity: 0.45;
  cursor: not-allowed;
}

.table thead.thead-dark th {
  background: #113450;
  border-color: #113450;
  color: #fff;
  font-weight: 700;
  letter-spacing: 0.01em;
}

.table th,
.table td {
  padding: 0.95rem 0.85rem;
  vertical-align: middle;
}

.table tbody tr:nth-child(even) {
  background: rgba(15, 23, 42, 0.015);
}

.table td .btn {
  padding: 0.55rem 0.95rem;
  font-size: 0.9rem;
}

.form-group {
  margin-bottom: 1.25rem;
}

.form-group label {
  display: block;
  font-weight: 700;
  margin-bottom: 0.55rem;
  color: var(--ink);
}

.form-group > .col-sm-3,
.form-group > .col-sm-9 {
  max-width: 100%;
  padding-left: 0;
  padding-right: 0;
}

.form-control,
.custom-select,
select.form-control {
  min-height: 48px;
  border-radius: 16px;
  border: 1px solid rgba(16, 42, 67, 0.12);
  background: var(--surface-soft);
  padding-left: 15px;
  padding-right: 15px;
}

.form-control:focus,
.custom-select:focus,
select.form-control:focus {
  border-color: rgba(15, 118, 110, 0.35);
  box-shadow: 0 0 0 0.22rem rgba(15, 118, 110, 0.12);
  background: #fff;
}

input[type="file"].form-control {
  height: auto;
  min-height: 52px;
  padding-top: 12px;
}

.form-group .container {
  padding-left: 0;
  padding-right: 0;
}

img,
video {
  display: block;
  max-width: 100%;
  height: auto;
  border-radius: 18px;
}

.embed-responsive {
  border-radius: 18px;
  overflow: hidden;
  box-shadow: var(--shadow-soft);
}

.navbar {
  display: flex;
  align-items: center;
  gap: 12px;
  flex-wrap: wrap;
  margin: 18px 24px 24px;
  padding: 16px 20px;
  background: rgba(255,255,255,0.92);
  border: 1px solid var(--line);
  border-radius: 22px;
  box-shadow: var(--shadow-soft);
  backdrop-filter: blur(10px);
}

.navbar a {
  display: inline-flex;
  align-items: center;
  padding: 0;
  color: var(--ink);
  text-decoration: none;
  font-size: 0.98rem;
}

.navbar a:hover {
  color: var(--accent);
}

.navbar a:empty {
  display: none;
}

.navbar a:last-child,
.navbar .navbar__user {
  margin-left: auto;
  color: var(--muted);
  font-weight: 700;
}

.empty-state {
  padding: 36px 24px;
  text-align: center;
  color: var(--muted);
}

@media (max-width: 991.98px) {
  body {
    padding-left: 0;
    padding-top: 80px;
  }

  body.nav-open {
    overflow: hidden;
  }

  .mobile-topbar {
    display: flex;
  }

  .sidenav {
    transform: translateX(-100%);
  }

  body.nav-open .sidenav {
    transform: translateX(0);
  }

  body.nav-open .nav-backdrop {
    opacity: 1;
    visibility: visible;
  }

  .col-md-12.offset-md-1 {
    padding: 18px;
    border-radius: 22px;
  }

  .col-sm-12.offset-md-3 > form {
    padding: 20px;
    border-radius: 22px;
  }

  .navbar {
    margin: 16px 16px 20px;
    padding: 14px 16px;
    border-radius: 18px;
  }

  .navbar a:last-child,
  .navbar .navbar__user {
    width: 100%;
    margin-left: 0;
  }

  .row.mb-3 .btn,
  .mb-3.text-right .btn {
    display: inline-flex;
    width: auto;
  }

  .mb-3.text-right {
    text-align: left !important;
  }

  .table {
    min-width: 640px;
  }

  .table th,
  .table td {
    white-space: nowrap;
    font-size: 0.92rem;
  }
}

@media (max-width: 575.98px) {
  .container {
    padding-left: 16px;
    padding-right: 16px;
  }

  .row.mb-3 .col-md-12,
  .mb-3.text-right {
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  .row.mb-3 .btn,
  .mb-3.text-right .btn,
  .table td .btn {
    width: 100%;
    justify-content: center;
  }

  .col-md-12.offset-md-1,
  .col-sm-12.offset-md-3 > form,
  .table-responsive,
  .navbar {
    border-radius: 18px;
  }
}
</style>
</head>

<body>
<div class="mobile-topbar">
  <button type="button" class="app-nav-toggle" aria-label="Toggle navigation">
    <i class="fas fa-bars"></i>
  </button>
  <span class="mobile-topbar__brand">MOVE ON</span>
</div>
<div class="nav-backdrop"></div>
<div class="sidenav">
  <ul>
    <header>MOVE ON</header>
    <li><a href="<?= app_url('app/pages/news/News.php') ?>"><i class="fas fa-newspaper" style="font-size:24px"></i> ข่าวสาร</a></li>
    <li><a href="<?= app_url('app/pages/video/Video.php') ?>"><i class="fas fa-video" style="font-size:24px"></i> วิดีโอ</a></li>
    <li><a href="<?= app_url('app/pages/exercises/Exercises.php') ?>"><i class="fas fa-dumbbell" style="font-size:24px;color:red"></i> ท่าออกกำลังกาย</a></li>
    <li><a href="<?= app_url('app/pages/foods/Foods.php') ?>"><i class="fas fa-utensils" style="font-size:24px"></i> อาหาร</a></li>
    <li><a href="<?= app_url('app/pages/auth/Logout.php') ?>"><i class="fas fa-sign-out-alt" style="font-size:24px"></i> ออกจากระบบ</a></li>
  </ul>
</div>

