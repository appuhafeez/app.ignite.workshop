<?php
$db_name = "id1710455_testapp";
$mysql_username = "id1710455_bloodbank123";
$mysql_password = "bloodbank123";
$server_name = "localhost";
$conn = mysqli_connect($server_name,$mysql_username,$mysql_password,$db_name);
 $conn2 = new PDO("mysql:host=$server_name;dbname=$db_name", $mysql_username, $mysql_password);

?>