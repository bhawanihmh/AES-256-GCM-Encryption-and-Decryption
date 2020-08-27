<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

	<h2 style="text-align: center;">AES-GCM APPLICATIONS</h2>
	<form action="/encrypt">
		<label id="plainTextLbl">Plain Text :</label><br> 
		<input type="text" id="plainText" name="plainText"><br> 
		<input type="submit" value="Encrypt">
	</form>
	<br><br>
	<form action="/decrypt">
		<label id="encryptedTextLbl">Encrypted Text :</label><br> 
		<input type="text" id="encryptedText" name="encryptedText"><br>
		<input type="submit" value="Decrypt">
	</form>
</body>
</html>
