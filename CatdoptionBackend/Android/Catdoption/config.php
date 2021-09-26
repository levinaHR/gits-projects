<?php

$server = "localhost";
$user = "root";
$password = "";
$db_name = "catdoption";

$db = mysqli_connect($server, $user, $password, $db_name);

if (!$db) {
    die("Gagal terhubung ke database: ".mysqli_connect_error());
}

?>
