<?php

require_once dirname(__DIR__) . '/bootstrap.php';

function load_env_file(string $path): void
{
    if (!is_readable($path)) {
        return;
    }

    $lines = file($path, FILE_IGNORE_NEW_LINES | FILE_SKIP_EMPTY_LINES);
    if ($lines === false) {
        return;
    }

    foreach ($lines as $line) {
        $line = trim($line);
        if ($line === '' || strpos($line, '#') === 0) {
            continue;
        }

        $separator = strpos($line, '=');
        if ($separator === false) {
            continue;
        }

        $key = trim(substr($line, 0, $separator));
        $value = trim(substr($line, $separator + 1));

        if ($key === '') {
            continue;
        }

        if (
            (substr($value, 0, 1) === '"' && substr($value, -1) === '"') ||
            (substr($value, 0, 1) === "'" && substr($value, -1) === "'")
        ) {
            $value = substr($value, 1, -1);
        }

        if (getenv($key) !== false) {
            continue;
        }

        putenv($key . '=' . $value);
        $_ENV[$key] = $value;
        $_SERVER[$key] = $value;
    }
}

function env_value(string $key, ?string $default = null): ?string
{
    $value = getenv($key);
    if ($value !== false && $value !== '') {
        return $value;
    }

    if (isset($_ENV[$key]) && $_ENV[$key] !== '') {
        return (string) $_ENV[$key];
    }

    if (isset($_SERVER[$key]) && $_SERVER[$key] !== '') {
        return (string) $_SERVER[$key];
    }

    return $default;
}

$projectRoot = dirname(__DIR__, 2);
load_env_file($projectRoot . DIRECTORY_SEPARATOR . '.env');

$appEnv = strtolower((string) env_value('APP_ENV', 'dev'));
$profiles = [
    'dev' => [
        'host' => 'localhost',
        'user' => 'root',
        'pass' => '',
        'name' => 'move_on',
        'port' => '3306',
    ],
    'uat' => [
        'host' => '',
        'user' => '',
        'pass' => '',
        'name' => '',
        'port' => '3306',
    ],
    'prod' => [
        'host' => '',
        'user' => '',
        'pass' => '',
        'name' => '',
        'port' => '3306',
    ],
];

if (!isset($profiles[$appEnv])) {
    $appEnv = 'dev';
}

$profile = $profiles[$appEnv];

$svname = env_value('DB_HOST', $profile['host']);
$usname = env_value('DB_USER', $profile['user']);
$pass = env_value('DB_PASS', $profile['pass']);
$db = env_value('DB_NAME', $profile['name']);
$port = (int) env_value('DB_PORT', $profile['port']);

if ($svname === '' || $usname === '' || $db === '') {
    api_error('Database configuration is incomplete for APP_ENV=' . $appEnv, 500);
}

if ($port <= 0) {
    $port = 3306;
}

$conn = new mysqli($svname, $usname, $pass, $db, (int)$port);

if ($conn->connect_error) {
    api_error('Database connection failed: ' . $conn->connect_error, 500);
}

$conn->set_charset('utf8');
date_default_timezone_set('Asia/Bangkok');
