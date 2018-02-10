<?php 
	include("conn.php");
	
	$name = $_POST["name"];
   //    echo $name;
        mysqli_select_db($conn,"id1710455_testapp");
	//$conn = mysqli_connect("localhost","root","","fcm");

	$sql = " SELECT res FROM ignitedemo WHERE req='$name';";
//	echo $sql;

	$result = mysqli_query($conn,$sql);
	//$rows = array();
	
	if(mysqli_num_rows($result) > 0 ){

		while ($row = mysqli_fetch_assoc($result)) {
			$rows = $row["res"];
		}
	//$jsonfile = json_encode($rows);
   //     echo $message_status;
	echo $rows;
}else{
echo "No response";
}
	mysqli_close($conn);
 ?>