<? php
	$con=mysqli_connect("mysql7.000webhost.com", "a4202937_mikehen", "1Arsenal", "a4202937_cs385");

$username = $POST[username];
$password = $POST[password];

$statement = mysli_prepare($con, "INSERT INTO User(username, password) VALUES (?, ?) ");
mysqli_stmt_bind_param($statement,  "ss", $username, $password);
mysqli_stmt_execute($statement);
mysqli_stmt_close($statement);


mysqli_close($con)

?>