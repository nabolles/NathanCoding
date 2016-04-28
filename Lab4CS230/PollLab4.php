<!DOCTYPE html>
	<html>
		<head>
			<title>Poll</title>
			<link type='text/css' rel='stylesheet' href='layoutLab4.css'/>
			<script>
				function check(){
					if(isset($_POST['option']) == true){
						return true;
					}
					else { 
						return false;
					}
				}
			</script>
		</head>
		<body link ="white">
			<h1> Poll! </h1>
			<table style="width:100%">
				<tr>
					<td colspan="3">
						<p> <h2 font-size="35px">Pick a day in which you would love to play soccer!</p><br><br>
					</td>
				</tr>
				<tr>
					<td style="width:15%" valign="middle" valign="middle">
						<a href="Lab4FrontSplash.php">Back to the Splash Page!</a><br><br>
						<a href="PollLabResultsLab4.php">View the results! </a>
					</td>
					<td style="width:55%">
						<form onsubmit="check()" action="PollLabResultsLab4.php" method="POST">
							<input name="option" type="radio" value="Monday"></input> Monday 
							<br>
							<input name="option" type="radio" value="Tuesday"></input> Tuesday
							<br>
							<input name="option" type="radio" value="Wednesday"></input> Wednesday
							<br>
							<input name="option" type="radio" value="Thursday"></input>	Thursday
							<br>
							<input name="option" type="radio" value="Friday"></input> Friday
							<br>
							<input name="option" type="radio" value="EVERYDAY"></input> EVERYDAY!!
							<br><br>
							<button type="submit"> Send! </button>
						</form>
					</td>
					<td style="width:30%">
					</td>
					</tr>
			</table>
			<hr>
				<p>
					A Website by Nathan Bolles
				</p>
		</body>
	</html>