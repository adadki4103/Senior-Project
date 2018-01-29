<?php
//db_name, username, & password,SELECT Details, & POST Details removed for Security Purposes. 
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$user_name = $_POST[""];
$user_pass = $_POST[""];

// Check connection
if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "SELECT  FROM WHERE = '' AND  =''";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
   echo "Pass";
} else {
    echo "Fail";
}
$conn->close();
?>