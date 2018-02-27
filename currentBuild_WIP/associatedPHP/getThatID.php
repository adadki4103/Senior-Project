<?php
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$code = $_POST["theCode"];

// Check connection
if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "SELECT * FROM  WHERE  = '$code'";
$result=mysqli_query($conn,$sql);

while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
  echo $row['pickUpID'];
 // echo "\n.\n";
}
$conn->close();
?>
