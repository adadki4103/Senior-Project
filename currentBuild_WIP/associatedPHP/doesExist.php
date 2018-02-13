<?php
//db_name, username, & password,SELECT Details, & POST Details removed for Security Purposes. 
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$nameToCheck = $_POST["nameToCheck"];


// Check connection
if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "SELECT  FROM  WHERE  = '$nameToCheck'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {

   echo "User Name Exist";
} else {
    echo "User Name Does Not Exist";
}
$conn->close();
?>
