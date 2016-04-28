<!DOCTYPE html>
	<html>
		<head>
			<title>Poll results</title>
			<link type='text/css' rel='stylesheet' href='layoutLab4.css'/>
		</head>
		<body link ="white">
			<h1> Results of the Poll! </h1>
			<table style="width:100%">
				<tr>
					<td colspan="3">
						<p> <h2 font-size="35px">This is the results page from the poll about times to play soccer for Soccer Club!</p><br><br>
					</td>
				</tr>
				<tr>
					<td style="width:15%" valign="middle" valign="middle">
						<a href="Lab4FrontSplash.php">Back to the Splash Page!</a><br><br>
						<a href="PollLab4.php">View the Poll! </a>
					</td>
					<td>
						This is what you selected for the poll: <br>
						<?php	
							$thenumb = $_POST["option"];
							$theFile = fopen("text.txt" , "a") or die("Unable to open the file");
							fwrite($theFile, $thenumb . "\n");
							fclose($theFile);
							echo $thenumb;
						?> <br><br><br><br><br>
					</td>
					<td>
						<?php
							$option1=0; $option2=0; $option3=0;
							$option4=0; $option5=0; $option6=0;
							$total=0;
							$theFile = fopen("text.txt" , "r") or die("Unable to open the file");
							while(!feof($theFile)){
								$numb = fgets($theFile);
								$numb = trim($numb);
								// THIS TRIM RIGHT HERE TOOK ME HOURS TO REALIZE THIS IS WHAT I NEEDED
								//IMPLEMENTING TRIM WAS THE ONLY THING I WAS MISSING AND I HAD ATTEMPTED SO MANY DIFFERENT THINGS
								//I TRIED EXPLODING INTO STRINGS AND MAKING ARRAYS BUT THIS TRIM SAVED MY LIFE
								
								if(strcmp($numb, "Monday") == 0){
									$option1++;
									$total++;
								}
								elseif(strcmp($numb, "Tuesday") == 0){
									$option2++;
									$total++;
								}
								elseif(strcmp($numb, "Wednesday") == 0){
									$option3++;
									$total++;
								}
								elseif(strcmp($numb, "Thursday") == 0){
									$option4++;
									$total++;
								}
								elseif(strcmp($numb, "Friday") == 0){
									$option5++;
									$total++;
								}
								elseif(strcmp($numb, "EVERYDAY") == 0){
									$option6++;
									$total++;
								}
									
							}
						?>
						
						Monday: <?php
									echo $option1;
								?>~ ~
								<?php
									echo round($option1/$total*100,0);
								?>%<br>
						Tuesday: <?php
									echo $option2;
								?>~ ~
								<?php
									echo round($option2/$total*100,0);
								?>%<br>
						Wednesday: <?php
									echo $option3;
								?>~ ~
								<?php
									echo round($option3/$total*100,0);
								?>%<br>
						Thursday: <?php
									echo $option4;
								?>~ ~
								<?php
									echo round($option4/$total*100,0);
								?>%<br>
						Friday: <?php
									echo $option5;
								?>~ ~
								<?php
									echo round($option5/$total*100,0);
								?>%<br>
						EVERYDAY: <?php
									echo $option6;
								?>~ ~
								<?php
									echo round($option6/$total*100,0);
								?>%<br><br>
						total votes:<?php
									echo $total;
									?>
						
					</td>
				</tr>
			</table>
			<hr>
				<p>
					A Website by Nathan Bolles
				</p>
		</body>
	</html>
								