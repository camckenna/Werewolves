import requests
import time

def test_werewolves():
	r = requests.get("https://infinite-earth-3076.herokuapp.com/")
	print("The URL of the app is: ")
	print(r.url)

	raw_input(">")

	print("Currently the day/night frequency is 1 minute")
	raw_input(">")

	print("Player Castiel has been set to werewolf. Player joenash has been set to townsperson. User Admin has been set to Admin.")
	print("Admin is about to restart the game with scent range = 1km and kill range = 0.1")
	raw_input(">")
	payload = {'frequency': "60", 'killRange': "0.1", 'scentRange': "0.1"}
	r = requests.post('https://infinite-earth-3076.herokuapp.com/restart', data=payload, auth=('Admin', 'letmein'))
	print("Admin restarted. The status is ", r.status_code)

	raw_input(">")

	print("This next line will be the information for a Player named Castiel")
	raw_input(">")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/info', auth=('Castiel', 'letmein'))
	print("The status is ", r.status_code)
	print(r.text)

	raw_input(">")

	print("Castiel is not an Admin and cannot restart the game")
	payload = {'frequency': "60", 'killRange': "0.1", 'scentRange': "0.1"}
	r = requests.get('https://infinite-earth-3076.herokuapp.com/restart', auth=('Castiel', 'letmein'))
	print(r.url)
	print("The status is ", r.status_code)
	

	raw_input(">")
	print("This next line is the high score list")
	
	raw_input(">")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/scores', auth=('Castiel', 'letmein'))
	print(r.text)

	raw_input(">")

	print("Points are awarded for surviving each night, for voting to kill a werewolf and having that werewolf hang, for a successful kill and for a counterattack")
		
	raw_input(">")

	print("This next line will update the player's location to 37.2708 N, 76.7069 W")
	
	raw_input(">")
	
	payload = {'lat': 37.2708, 'lng': 76.7069}
	r = requests.post("https://infinite-earth-3076.herokuapp.com/location", data=payload, auth=('Castiel', 'letmein'))
	print(r.text)
	
	raw_input(">")

	print("The next line will show the updated position")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/info', auth=('Castiel', 'letmein'))
	print(r.text)
	
	raw_input(">")

	print("This is a list of voteable players")
	
	raw_input(">")

	r = requests.get('https://infinite-earth-3076.herokuapp.com/players/alive', auth=('Castiel', 'letmein'))
	print(r.text)

	print("This is a list of alive players")
	
	raw_input(">")

	r = requests.get('https://infinite-earth-3076.herokuapp.com/players/alive', auth=('Castiel', 'letmein'))
	print(r.text)

	raw_input(">")
	print("Admin: Switching to Day")
	r = requests.post("https://infinite-earth-3076.herokuapp.com/nightToDay", auth=('Admin', 'letmein'))
	print("This next line will attempt to vote for Asset")

	raw_input(">")

	payload = {'username': "Asset"}
	r = requests.post("https://infinite-earth-3076.herokuapp.com/voteForPlayer", data=payload, auth=('Castiel', 'letmein'))
	
	print(r.text)
	
	raw_input(">")
	print("Admin: Switching to Night, because hanging takes place at sundown")
	r = requests.post("https://infinite-earth-3076.herokuapp.com/dayToNight", auth=('Admin', 'letmein'))


	print("This is a list of alive players, again")
	
	raw_input(">")

	r = requests.get('https://infinite-earth-3076.herokuapp.com/players/alive', auth=('Castiel', 'letmein'))
	print(r.text)

	raw_input(">")

	print("Here is info again")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/info', auth=('Castiel', 'letmein'))
	print(r.text)

	raw_input(">")
	
	print("Here is a list of nearby players. Because all other players are at (0,0), it will be blank.")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/nearby', auth=('Castiel', 'letmein'))
	print(r.text)

	print("This next line will update the player's location back to 0 N, 0 W")
	
	raw_input(">")
	
	payload = {'lat': 0, 'lng': 0}
	r = requests.post("https://infinite-earth-3076.herokuapp.com/location", data=payload, auth=('Castiel', 'letmein'))
	print(r.text)

	raw_input(">")
	
	print("Here is a list of nearby players.")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/nearby', auth=('Castiel', 'letmein'))
	print(r.text)

	raw_input(">")

	print("Here is a list of nearby players for joenash, a townsperson. It is empty because townspeople do not have this functionality.")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/nearby', auth=('joenash', 'letmein'))
	print(r.text)

	raw_input(">")

	print("Admin: Switching to Night")
	r = requests.post("https://infinite-earth-3076.herokuapp.com/dayToNight", auth=('Admin', 'letmein'))
	
	print("Now Castiel will attempt to kill joenash.")
	
	payload = {'username': "joenash"}
	r = requests.post("https://infinite-earth-3076.herokuapp.com/killPlayer", data=payload, auth=('Castiel', 'letmein'))
		
	
	raw_input(">")
	print("If joenash is dead, Castiel killed him. However, if Castiel is dead, joenash counterattacked.")
	r = requests.get('https://infinite-earth-3076.herokuapp.com/players/alive', auth=('Castiel', 'letmein'))
	print(r.text)

	payload = {'frequency': "60", 'killRange': "0.1", 'scentRange': "0.1"}
	r = requests.post('https://infinite-earth-3076.herokuapp.com/restart', data=payload, auth=('Admin', 'letmein'))


if __name__ == "__main__":
   
    test_werewolves()
