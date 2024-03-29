import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.TaggedWord;

/*
HashMap commands:
	put(key, value) = add
	get(key) = value
	keySet() = set with keys
	containsKey(key) = true if key is present
*/
public class Synonyms {

	// VERBS
	private HashMap<String, Integer> go;
	private HashMap<String, Integer> take;
	private HashMap<String, Integer> use;
	private HashMap<String, Integer> look;
	private HashMap<String, Integer> eat;

	//NOUNS maybe?


	/*
	key = String
	value = int: 
		if 1: only 1 meaning
		if 2: two possible meanins, look at noun. (for example, move to livingroom AND move the chair)
	*/
	public Synonyms() {
		createLists();
	}

	private void createLists() {
		go = new HashMap<String, Integer>();
		take = new HashMap<String, Integer>();
		eat = new HashMap<String, Integer>();
		use = new HashMap<String, Integer>();
		look = new HashMap<String, Integer>();
		addGo();
		addUse();
		addTake();
		addEat();
		addLook();
	}

	private void addGo() {
		go.put("enter", 1);
		go.put("go", 1);
		go.put("head", 1);
		go.put("jump", 1);
		go.put("move", 1);
		go.put("run", 1);
		go.put("stand", 1);
		go.put("visit", 1);
		go.put("walk", 1);
	}

	private void addTake() {
		take.put("acquire", 1);
		take.put("attain", 1);
		take.put("gain", 1);
		take.put("gather", 1);
		take.put("get", 1);
		take.put("grab", 1);
		take.put("grip", 1);
		take.put("hold", 1);
		take.put("pick", 1);
		take.put("seize", 1);
		take.put("snag", 1);
		take.put("steal", 1);
		take.put("take", 1);
	}

	private void addEat() {
		eat.put("absorb", 1);
		eat.put("bite", 1);
		eat.put("chew", 1);
		eat.put("chow", 1);
		eat.put("consume", 1);
		eat.put("devour", 1);
		eat.put("eat", 1);
		eat.put("feed", 1);
		eat.put("gobble", 1);
		eat.put("ingest", 1);
		eat.put("munch", 1);
		eat.put("snack", 1);
		eat.put("swallow", 1);
	}

	private void addLook() {
		look.put("admire", 1);
		look.put("behold", 1);
		look.put("check", 1);
		look.put("contemplate", 1);
		look.put("discover", 1);
		look.put("examine", 1);
		look.put("gaze", 1);
		look.put("glance", 1);
		look.put("inspect", 1);
		look.put("look", 1);
		look.put("observe", 1);
		look.put("peer", 1);
		look.put("read", 1);
		look.put("scan", 1);
		look.put("search", 1);
		look.put("see", 1);
		look.put("stare", 1);
		look.put("study", 1);
		look.put("survey", 1);
		look.put("view", 1);
		look.put("watch", 1);
		look.put("open", 1); //for the open locker-command
	}

	private void addUse() {
		use.put("apply", 1);
		use.put("give", 1);
		use.put("insert", 1);
		use.put("practice", 1);
		use.put("throw", 1);
		use.put("unlock", 1);
		use.put("use", 1);
		use.put("utilize", 1);
	}

/*
@param: a list of tagged words
@return: A list of better tagged words, where some nouns are now verbs because they should be that.

So, for each word in input, we see if it should be a verb by using the "verbs" method. If it
returns something, we create a new tagged word with that verb and the tag verb, we remove the old one 
(there is no replace-function) and then add the new tagged word.
*/
	public List<TaggedWord> betterTagging(List<TaggedWord> input) {
		for(int i = 0; i < input.size(); i++) {
			TaggedWord wd = input.get(i);
			String verbifyed = verbs(wd.word());
			if(verbifyed != null) {
				TaggedWord res = new TaggedWord(verbifyed, "VB");
				input.remove(i); //if we don't remove the word, it will get moved one spot.
				input.add(i, res); //insert the new verb.
			}
		}
		return input;
	}

	public String verbs(String vb) {
		if(go.containsKey(vb)) {
			return "go";
		} else if(use.containsKey(vb)) {
			return "use";
		} else if(take.containsKey(vb)) {
			return "take";
		} else if(look.containsKey(vb)) {
			return "look";
		} else if(eat.containsKey(vb)) {
			return "eat";
		}
		return null;
	}
}











