//kitchen items
var table = new Item("table", 
	"A tall table, and you can see a cracker laying on it! If only you could reach it...", 
	null);
var chair = new Item("chair", 
	"A kitchen chair, but it isn't at the table for some reason.", 
	"not moved");
var crackerKitchen = new Item("cracker", 
	"A cracker!", 
	null);
var refrigerator = new Item("refrigerator", 
	"A refrigerator, shame you don't store crackers in those.", 
	null);

//livingroom items
var cat = new Item("cat", 
	"A scary-looking cat. It's laying right next to a cracker! Maybe you could lure it away somehow...", 
	"guarding");
 
//bedroom items


//rooms
var kitchen = {
	name: "kitchen",
	description: "waaa it's a kitchen",
	items: [var cat = new Item()]
	door: {
		livingroom:null,
		bedroom:null
	},
	crackelicious: function() {

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
	name: "bedroom",
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













