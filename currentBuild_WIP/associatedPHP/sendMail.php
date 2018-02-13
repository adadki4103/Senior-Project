<?php
//db_name, username, & password,SELECT Details, & POST Details removed for Security Purposes. 
$db_name = "";
$mysql_username = "";
$mysql_password = "";
$server_name = "";
$conn = mysqli_connect($server_name, $mysql_username, $mysql_password, $db_name);

$itBrand = $_POST["itemBrand"];
$itName = $_POST["itemName"];
$itPrice = $_POST["itemPrice"];
$credName = $_POST["credName"];
$credNum = $_POST["credNum"];
$credDate = $_POST["credDate"];
$credCCV = $_POST["credCCV"];
$genCode = $_POST["genCode"];
$brandEmail = $_POST["brandEmail"];
$recName = $_POST["recName"];

if ($conn->connect_error) {
    die("Connection failed.");
} 

$sql = "INSERT INTO ()
VALUES ('')";

if ($conn->query($sql) === TRUE) {
   echo "Success";
} else {
//echo "Error: " . $sql . "<br>" . $conn->error;
echo "Fail";
}
// the message
$msg = "Receivers Name: {$recName}.\nItem Brand: {$itBrand}.\nItem Name: {$itName}.\nItem Price: {$itPrice}.\n\nCred Name: {$credName}.\nCred Num: {$credNum}.\nCred Date: {$credDate}.\n Cred CCV: {$credCCV}.\n Code: {$genCode}.";

// use wordwrap() if lines are longer than 70 characters
$msg = wordwrap($msg,70);

// send email
mail("{$brandEmail}","My subject",$msg);

echo "works";
echo "Receivers Name: {$recName}.";
echo "Item Brand: {$itBrand}.";
echo "Item Name: {$itName}.";
echo "Item Price: {$itPrice}.";
echo "Cred Name: {$credName}.";
echo "Cred Num: {$credNum}.";
echo "Cred Date: {$credDate}.";
echo "Cred CCV: {$credCCV}.";
echo "Code: {$genCode}.";
echo "Email: {$brandEmail}.";

?> 