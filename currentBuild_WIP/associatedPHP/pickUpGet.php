<?php

$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);


// Check connection
if ($conn->connect_error) {
    die("Connection failed.");
} 
$value = $_POST["value"];
$name = $_POST["name"];


$sql ="SELECT * FROM  WHERE  ='$value' AND  = '$name' LIMIT 1";
$result=mysqli_query($conn,$sql);


// Associative array
while($row=mysqli_fetch_array($result,MYSQLI_ASSOC)){
  echo $row['genCode'];

}
$conn->close();
?>