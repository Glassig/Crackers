package game;

import java.util.List;
import java.util.Scanner;
import java.util.Date;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import edu.stanford.nlp.ling.TaggedWord;

public class TextVersion {

	private World world;
	private Player bunny;
	private static Scanner scan;
	private static POSTagger tagger;
	private static Synonyms synonyms;
	private static Parser parser;

	public TextVersion(World world, Player player) {
		this.world = world;
		this.bunny = player;
		scan = new Scanner(System.in);
		tagger = new POSTagger("models/english-left3words-distsim.tagger");
		synonyms = new Synonyms();
		parser = new Parser();
	}

	public void run() throws IOException {

		List<String> lines = new ArrayList<String>();
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
		String filename = "textlog/" + ft.format(date) + ".txt";
		Path file = Paths.get(filename);

		System.out.println("\nPLOT:\n" + world.getPlot() + "\n");
		System.out.println("INSTRUCTIONS:\n" + world.getInst() + "\n");

		// This runs until you have eaten all 3 crackers!
		while (bunny.getCrackers() < 3) {
			System.out.print("@" + bunny.getCurrentRoom().getName() + ": ");
			String com = scan.nextLine();
			com = com.toLowerCase();
			lines.add(com);
			bunny.updateCommandCount();

			if (com.contains("quit") || com.contains("exit")) {
				writeToFile(lines, file);
				System.exit(0);
			} else if (com.contains("help")) {
				System.out.println(world.getInst() + "\n");
				continue;
			} else if (com.contains("items") || com.contains("inventory")) {
				System.out.println("Your inventory contains:");
				if (bunny.getInventory().isEmpty()) {
					System.out.println("nothing at all.");
				} else {
					for (Item item : bunny.getInventory()) {
						System.out.println(item.getName());
					}
				}
				System.out.println(""); // needed for newline
				continue;
			} else if (com.equals("look around")) {
				System.out.println("You look around the room and see:");
				for (Item item : bunny.getCurrentRoom().getItems()) {
					if (item.getViewable()) {
						System.out.println(item.getName());
					}
				}
				System.out.println("");
				continue;
			}

			// handle user input here!
			String[] comWords = com.split("[\\W]");
			List<TaggedWord> tmp = tagger.postag(comWords); // ONLY VB AND NN
															// INCLUDED

			List<TaggedWord> comTagged = synonyms.betterTagging(tmp);

			boolean foundValidVerb = false;
			String vb = null;
			for (TaggedWord tw : comTagged) {
				if (tw.tag().startsWith("VB")) {
					foundValidVerb = true;
					vb = tw.word();
				} else if (tw.tag().startsWith("NN") && foundValidVerb) {
					parser.parse(bunny, world, vb, tw.word());
					foundValidVerb = false; // might not be needed
				}
			}

		}

		// victory!
		System.out.println("Victory! You are no longer hungry.\n" + "You completed the game using "
				+ bunny.getCommandCount() + "commands. \n" + "Thank you for playing! <3\n");
		writeToFile(lines, file);
		System.exit(0);
	}

	public void writeToFile(List<String> lines, Path file) throws IOException {
		lines.add("Number of commands: " + bunny.getCommandCount());
		Files.write(file, lines, Charset.forName("UTF-8"));
	}
}
