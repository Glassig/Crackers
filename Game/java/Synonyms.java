import java.util.HashMap;
import java.util.ArrayList;

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
	private HashMap<String, Integer> interact;
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
		interact = new HashMap<String, Integer>();
		look = new HashMap<String, Integer>();
		addGo();
		addInteract();
		addTake();
		addEat();
		addLook();
	}

	private void addGo() {
		go.put("go", 1);
		go.put("walk", 1);
		go.put("enter", 1);
		go.put("move", 2);
	}

	private void addTake() {
		take.put("get", 1);
		take.put("acquire", 1);
		take.put("take", 1);
		take.put("grab", 1);
		take.put("pick", 1);
		take.put("hold", 1);
		take.put("grip", 1);
		take.put("gather", 1);
		take.put("attain", 1);
		take.put("gain", 1);
		take.put("snag", 1);
		take.put("seize", 1);
	}

	private void addInteract() {
		interact.put("use", 1);
		interact.put("utilize", 1);
		interact.put("interact", 1);
		interact.put("throw", 1);
		interact.put("push", 1);
		interact.put("put", 1);
	}

	private void addLook() {
		look.put("look", 1);
		look.put("see", 1);
		look.put("examine", 1);
		look.put("discover", 1);
		look.put("watch", 1);
		look.put("glance", 1);
		look.put("stare", 1);
		look.put("study", 1);
		look.put("view", 1);
		look.put("peer", 1);
		look.put("read", 1);
		look.put("admire", 1);
		look.put("behold", 1);
		look.put("contemplate", 1);
		look.put("survey", 1);
		look.put("observe", 1);
		look.put("scan", 1);
		look.put("inspect", 1);
		look.put("gaze", 1);
	}

	private void addEat() {
		eat.put("eat", 1);
		eat.put("consume", 1);
		eat.put("munch", 1);
		eat.put("bite", 1);
		eat.put("chew", 1);
		eat.put("devour", 1);
		eat.put("feed", 1);
		eat.put("absorb", 1);
		eat.put("snack", 1);
		eat.put("chow", 1);
		eat.put("gobble", 1);
	}

	public ArrayList<String> verbTags(ArrayList<String> inputVerbs) {
		ArrayList<String> resultVerb = new ArrayList<String>();
		for (String verb: inputVerbs) {
			resultVerb.add(verbs(verb));
		}
		return resultVerb;
	}

	public String verbs(String vb) {
		if(go.containsKey(vb)) {
			return "go";
		} else if(interact.containsKey(vb)) {
			return "interact";
		} else if(take.containsKey(vb)) {
			return "take";
		} else if(look.containsKey(vb)) {
			return "look";
		} else if(eat.containsKey(vb)) {
			return "eat";
		} else {
			//null
		}
		return null;
	}
}











