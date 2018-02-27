<?php
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$user_name = $_POST["user_name"];
$user_pass = $_POST["passwordEntered"];


// Check connection
if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "SELECT * FROM  WHERE  = '$user_name' AND  ='$user_pass'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {

   echo "Pass";
} else {
    echo "Fail";
}
$conn->close();
?>
