var kitchen = {
	name: "kitchen",
	description: "waaa its a kitchen",
	door: {
		livingroom:null,
		bedroom:null
	},
	crackelicious: function() {
		return 1;
	}
}

var livingroom = {
	name: "livingroom",
	description: "tv be watched here",
	door: {
		kitchen:null,
		bedroom:null
	}
}

var bedroom = {
	name: "livingroom",
	description: "magic be doin' here",
	door: {
		kitchen:null,
		livingroom:null
	}
}

var gamestate = {
	p_name:null,
	room:null,
	crackers:0,
	commands:0
}

var validTags = [
	"JJ",
	"NN",
	"NNP",
	"NNPS",
	"NNS",
	"VB",
	"VBD",
	"VBG",
	"VBN",
	"VBP",
	"VBZ"]

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

var textfield = document.querySelector(".textfield");

function tiggeditag(input) {
	var words = new Lexer().lex(textfield.value);
	var taggedWords = new POSTagger().tag(words);
	var result = "";
	for(i in taggedWords) {
		var taggedWord = taggedWords[i];
		var word = taggedWord[0];
		var tag =taggedWord[1];
		if(validTags.indexOf(tag) != -1) {
			result += (word + " /" + tag + "\n");
		}
	}
	console.log(result);
	return result;
}

document.querySelector(".submit").addEventListener("click", function() {
	console.log(textfield.value); 
	var result = tiggeditag(textfield.value);

	textfield.value = "";
})













