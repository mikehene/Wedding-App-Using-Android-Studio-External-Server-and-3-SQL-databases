<? php
	$con=mysqli_connect("mysql7.000webhost.com", "a4202937_mikehen", "1Arsenal", "a4202937_cs385");

$username = $POST[username];
$password = $POST[password];

$statement mysqli_prepare($con, "SELECT * FROM User WHERE username = ? AND password = ?") ;
mysqli_bind_param($statement, "ss", $username, $password);
mysqli_stmt_execute($statement);

mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $user_id, $username, $password); 

$user = array();
while(mysqli_stmt_fetch($statement)){
	$user[username] = $username;
	$user[password] = $password; 
}

echo json_encode($user);

mysqli_stmt_close($statement); 

mysqli_close($con)

?>