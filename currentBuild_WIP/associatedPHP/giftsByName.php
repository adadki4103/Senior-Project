<?php

$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);
$myName = $_POST["value"];
//$myName = "ada";
if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql ="SELECT * FROM WHERE  = '$myName' AND a = ''";
$result=mysqli_query($conn,$sql);

while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
  echo $row['itemName'];
  echo "\n.\n";
}
$conn->close();
?>