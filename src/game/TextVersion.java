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
	private Scanner scan;
	private POSTagger tagger;
	private Synonyms synonyms;
	private Parser parser;

	public TextVersion(World world, Player player) {
		this.world = world;
		this.bunny = player;
		scan = new Scanner(System.in);
		tagger = new POSTagger("models/english-left3words-distsim.tagger");
		synonyms = new Synonyms();
		parser = new Parser();
	}

	public void run() throws IOException {
		
		System.out.println("What is your name?");
		System.out.print("Name (use keyboard): ");
		String name = scan.nextLine();

		List<String> lines = new ArrayList<String>();
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
		String filename = "textlog/" + ft.format(date) + "-" + name + ".txt";
		Path file = Paths.get(filename);

		System.out.println("\nPLOT:\n" + world.getPlot() + "\n");
		System.out.println("INSTRUCTIONS:\n" + world.getInst() + "\n");

		System.out.println("Type \"start\" when ready to play!");
		String s = scan.nextLine();

		long startTime = date.getTime();
		// This runs until you have eaten all 3 crackers!
		while (bunny.getCrackers() < 3) {
			System.out.print("@" + bunny.getCurrentRoom().getName() + ": ");
			String com = scan.nextLine();
			com = com.toLowerCase();
			lines.add(com);
			bunny.updateCommandCount();

			if (com.contains("quit") || com.contains("exit")) {
				System.out.println("Do you want to quit playing? [yes/no]");
				System.out.print("Answer: ");
				String ans = scan.nextLine();
				ans.toLowerCase();
				if (ans.equals("no")) {
					System.out.println(); // newline
					continue;
				} else if (ans.equals("yes")) {
					System.out.println("Aww okay, bye! :D");
					lines.add(playtime(startTime));
					writeToFile(lines, file);
					System.exit(0);
				} else {
					System.out.println("I'll take that as a no.\n");
					continue;
				}
			} else if (com.contains("help")) {
				System.out.println(world.getInst() + "\n");
				System.out.println("You have eaten " + bunny.getCrackers() + " crackers. \n");
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
		String time = playtime(startTime);
		System.out.println("Victory! You are no longer hungry.\n" + "Commands used: " + bunny.getCommandCount() + "\n"
				+ time + "\n" + "Thank you for playing! <3\n");
		lines.add(playtime(startTime));
		writeToFile(lines, file);
		System.exit(0);
	}

	public void writeToFile(List<String> lines, Path file) throws IOException {
		lines.add("Number of commands: " + bunny.getCommandCount());
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

	public String playtime(long startTime) {
		Date date2 = new Date();
		long endTime = date2.getTime();
		long totalTimeInSeconds2 = (endTime-startTime)/1000;
		int totalTimeInSeconds = (int) ((endTime - startTime) / 1000);
		int minutes = totalTimeInSeconds / 60;
		int seconds = totalTimeInSeconds - minutes * 60;
		return "Time: " + minutes + "m " + seconds + "s";
	}
}
