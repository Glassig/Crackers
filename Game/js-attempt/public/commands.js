var go = ["go", "walk", "enter", "move"]; //"move to" might be one.... but "move chair" is also a thing all the ones for changing room
var take = ["take", "grab", "pick", "get", "acquire", "steal"]; //for taking an object
var look = ["look", "see", "inspect", "check", "examine"]; //for looking at room or inventory
var eat = "eat"; //for actually using the cracker, i think
var otherActions = ["open", "move", "use", "push"]; //here we want "move chair bladibla"
var actions = go + take + look + otherActions;

function interpretCommands(inputArray) {
	var word = "";
	var gameObject = null;
	for(var i = 0; i < inputArray.length; i++) {
		word = inputArray[i];
		if(actions.indexOf(word) >=0) {
			//we want to do something
			if(gameObject != null) {
				executeAction(word, gameObject);
			} else {
				//executeAction(word, ) with the rest of the list
			}
		} else if(gamestate.room.SOME_OBJECT == word) { //this is not correct code
			//something about an object in the room?
			gameObject = word; //the player might say "cracker get"
		}
	}
	
}

function executeAction(command, rest) {
	if(go.indexOf(word) >= 0) {
		var room = null;
		for(var i = 0; i < rest.length; i++) {
			if(gamestate.room.door[rest[i]] != null) {
				changeRoom(rest[i]);
				return;
			}
		}
		//we want to leave room
		//but where?
	} else if(take.indexOf(word) >= 0) {
		//we want an object
	} else if(look.indexOf(word) >= 0) {
		//we want to see something
	} else if(otherActions.indexOf(word) >=0) {
		//that bs
	} else {
		//we should never get here....s
	}
}

function changeRoom(newRoom) {
	if(newRoom == gamestate.room.name) {
		console.log("you are already in that room, stupid");
	} else if(gamestate.room.door[newRoom] != null) {
		gamestate.room = gamestate.room.door[newRoom];
		console.log(gamestate.room.description);
	} else {
		console.log("the room doesn't exist.")
	}
}
