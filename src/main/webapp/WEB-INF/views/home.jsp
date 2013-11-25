<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/styles/welcome.css'/>">
</head>
<body>
<div class="container">
<div id="leftcolumn"></div>
<div id="main">
<h1 class="center">
	Welcome to Werewolves!  
</h1>

<img class="center" height="50%" width="50%" src="http://www.cs.wm.edu/~rfd/teaching/cs420/assignments/werewolf.jpg" />

<h3 class="center">The Rules</h3>
<div class="center"></div>
<ul >
	<li>There are werewolves and townspeople </li>
	<li>During the night, werewolves kill townspeople if they are nearby </li>
	<li>Werewolves cannot kill werewolves</li>
	
	<li>During the day, everyone votes to hang a player</li>
	<li>If there is a tie, the loser is randomly selected</li>
	<li>The person with the most votes dies</li>
	
	<li>If nobody votes, nobody dies </li>
	<li>Townspeople have a small chance of counterattacking, which kills the werewolf, and the player becomes a hunter</li>
	<li>Points are awarded for surviving, killing, and voting to hang a werewolf</li>
	
</ul>

<h4>Good luck...</h4>
</div>
<div id="rightcolumn"></div>
</div>
</body>
</html>
