<?php
include '../config.php';

if ($_POST) {
    $id = $_POST['id'];

    $sql = "DELETE from cat where id='$id'";
    $query = mysqli_query($db, $sql);
    if ($query) {
        $response["error"] = false;
        $response["error_msg"] = "Delete success";
        $response["regID"] = $id;
        echo json_encode($response);

    } else {
        $response["error"] = false;
        $response["error_msg"] = "Delete failed";
        echo json_encode($response);
    }

} else {
    $response["error"] = true;
    $response["error_msg"] = "404";
    echo json_encode($response);
}

?>
