<?php

if (!defined('APP_ROOT')) {
    define('APP_ROOT', __DIR__);
}

if (!defined('PROJECT_ROOT')) {
    define('PROJECT_ROOT', dirname(__DIR__));
}

if (!defined('APP_BASE_URL')) {
    define('APP_BASE_URL', '/' . basename(PROJECT_ROOT));
}

function app_url(string $path = ''): string
{
    $normalized = ltrim(str_replace('\\', '/', $path), '/');
    return $normalized === '' ? APP_BASE_URL : APP_BASE_URL . '/' . $normalized;
}

function app_origin(): string
{
    $isHttps = !empty($_SERVER['HTTPS']) && $_SERVER['HTTPS'] !== 'off';
    $scheme = $isHttps ? 'https' : 'http';
    $host = $_SERVER['HTTP_HOST'] ?? ($_SERVER['SERVER_NAME'] ?? 'localhost');
    return $scheme . '://' . $host;
}

function app_absolute_url(string $path = ''): string
{
    $url = app_url($path);
    return app_origin() . $url;
}

function app_path(string $path = ''): string
{
    $normalized = ltrim(str_replace(['/', '\\'], DIRECTORY_SEPARATOR, $path), DIRECTORY_SEPARATOR);
    return $normalized === '' ? PROJECT_ROOT : PROJECT_ROOT . DIRECTORY_SEPARATOR . $normalized;
}

function asset_url(?string $path): string
{
    if ($path === null || $path === '') {
        return '';
    }

    if (preg_match('#^(https?:)?//#', $path)) {
        return $path;
    }

    $normalized = str_replace('\\', '/', trim($path));

    if (strpos($normalized, '/move_on/') === 0) {
        $normalized = substr($normalized, strlen('/move_on'));
    } elseif (strpos($normalized, 'move_on/') === 0) {
        $normalized = substr($normalized, strlen('move_on'));
    }

    return app_absolute_url(ltrim($normalized, '/'));
}

function api_json($payload, int $statusCode = 200): void
{
    http_response_code($statusCode);
    header('Content-Type: application/json; charset=UTF-8');
    echo json_encode($payload, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);
    exit;
}

function api_success($data = null, string $message = 'Success', array $extra = []): void
{
    api_json(array_merge([
        'value' => '1',
        'message' => $message,
        'data' => $data,
    ], $extra));
}

function api_error(string $message = 'Error', int $statusCode = 400, $data = null, array $extra = []): void
{
    api_json(array_merge([
        'value' => '0',
        'message' => $message,
        'data' => $data,
    ], $extra), $statusCode);
}

function api_required(array $source, array $keys): array
{
    $values = [];
    foreach ($keys as $key) {
        if (!array_key_exists($key, $source)) {
            api_error("Missing parameter: {$key}", 422);
        }
        $values[$key] = trim((string) $source[$key]);
    }
    return $values;
}

function api_optional(array $source, string $key, string $default = ''): string
{
    if (!array_key_exists($key, $source)) {
        return $default;
    }
    return trim((string) $source[$key]);
}

function api_int(array $source, string $key): int
{
    $value = api_optional($source, $key, '');
    if ($value === '' || filter_var($value, FILTER_VALIDATE_INT) === false) {
        api_error("Invalid parameter: {$key}", 422);
    }
    return (int) $value;
}

function api_fetch_all(mysqli_result $result, ?callable $map = null): array
{
    $rows = [];
    while ($row = mysqli_fetch_assoc($result)) {
        $rows[] = $map ? $map($row) : $row;
    }
    return $rows;
}

function api_first(mysqli_result $result, ?callable $map = null)
{
    $row = mysqli_fetch_assoc($result);
    if ($row === null) {
        return null;
    }
    return $map ? $map($row) : $row;
}


function app_current_user_id(): int
{
    return isset($_SESSION['userid']) ? (int) $_SESSION['userid'] : 0;
}

function app_current_username(string $fallback = 'Guest'): string
{
    if (isset($_SESSION['username']) && trim((string) $_SESSION['username']) !== '') {
        return trim((string) $_SESSION['username']);
    }
    return $fallback;
}

function app_is_logged_in(): bool
{
    return app_current_user_id() > 0 && app_current_username('') !== '';
}