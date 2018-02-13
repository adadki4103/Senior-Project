<?php
//db_name, username, & password,SELECT Details, & POST Details removed for Security Purposes. 
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);



$item_price = "Bagel";

// Check connection
if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "SELECT  FROM  WHERE  = '$item_price'";
$result = $conn->query($sql);

while($row = mysqli_fetch_array($result))
{
  echo $row[''];

} 

$conn->close();

?>
