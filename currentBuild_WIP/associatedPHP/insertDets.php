<?php
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$itBrand = $_POST["itemBrand"];
$itName = $_POST["itemName"];
$genCode = $_POST["genCode"];
$recName = $_POST["recName"];


if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "INSERT INTO (, , , g)
VALUES ('$recName','$itName','$itBrand','$genCode')";
if ($conn->query($sql) === TRUE) {
   echo "Success";
}
else {
echo "Fail";
}

?> 