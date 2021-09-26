<?php
include '../config.php';

if ($_POST) {
    $name = $_POST['name'];
    $age = $_POST['age'];
    $gender = $_POST['gender'];
    $breed = $_POST['breed'];
    $description = $_POST['description'];

    $sql = "INSERT INTO cat
            (name, age, gender, breed, description, status)
            VALUE ('$name', '$age', '$gender', '$breed', '$description', 1)";

    $query = mysqli_query($db, $sql);
    if ($query){
        $response["error"] = false;
        $response["error_msg"] = "Insert success";
        echo json_encode($response);

    } else{
        $response["error"] = false;
        $response["error_msg"] = "Insert failed";
        echo json_encode($response);
    }

} else {
    $response["error"] = true;
    $response["error_msg"] = "404";
    echo json_encode($response);
}

?>
