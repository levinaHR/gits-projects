<?php
include '../config.php';

//require_once('../config.php');

if ($_GET) {
    $status = $_GET['all'];
    $sql = mysqli_query($db, "Select * from cat");
//    $sql = mysqli_query($db, "Select * from cat where status='$status'");

    while($data = mysqli_fetch_assoc($sql)) {
        $arrayJson[] = $data;
    }

    $response = $arrayJson;
    echo json_encode($response);

} else {
    $response["error"] = true;
    $response["error_msg"] = "404";

    echo json_encode($response);
}

?>
