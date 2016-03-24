var kitchen = {
	description: "waaa its a kitchen",
	door: {
		livingroom:null,
		bedroom:null
	},
	crackelicious: function() {

	}
}

var livingroom = {
	description: "tv be watched here",
	door: {
		kitchen:null,
		bedroom:null
	}
}

var bedroom = {
	description: "magic be doin' here",
	door: {
		kitchen:null,
		livingroom:null
	}
}

var gamestate = {
	room:null,
	crackers:0,
	commands:0
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
		result += (word + " /" + tag + "\n");
	}
	console.log(result);
	return result;
}

document.querySelector(".submit").addEventListener("click", function() {
	console.log(textfield.value); //nli magic -> parse result -> act
	var result = tiggeditag(textfield.value);

	textfield.value = "";
})













