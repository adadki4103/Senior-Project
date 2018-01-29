 
<?php
//db_name, username, & password,INSERT Details, & POST Details removed for Security Purposes. 
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$user_name = $_POST[""];
$user_pass = $_POST[""];

// Check connection
if ($conn->connect_error) {
    die("Connection Failed");
} 

$sql = "INSERT INTO )
VALUES (')";

if ($conn->query($sql) === TRUE) {
   echo "Success! Try Logging in.";
} else {
echo  "User Name Taken";
}

$conn->close();