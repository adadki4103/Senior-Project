<?php
//db_name, username, & password,SELECT Details, & POST Details removed for Security Purposes. 
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql ="SELECT * FROM ";
$result=mysqli_query($conn,$sql);

while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
  echo $row[''];
  echo "\n.\n";
}
$conn->close();
?>