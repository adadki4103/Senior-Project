<?php
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$selectedItem = (int)$_POST["idPicked"];

if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "UPDATE  SET  = '' WHERE ='$selectedItem'";

if ($conn->query($sql) === TRUE) {
    echo "Pass";
} else {
    echo "Fail";
}
$conn->close();
?>