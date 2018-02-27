<?php
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$itemName = $_POST["item_name"];
$recev = $_POST["name"];
$myCode = $_POST["code"];


if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "DELETE FROM  WHERE  ='$recev' AND  = '$itemName' AND  = '$myCode'";

if ($conn->query($sql) === TRUE) {
    echo "Pass";
} else {
    echo "Fail";
}
$conn->close();
?>
