#!/bin/bash
javac -cp stanford-postagger.jar Item.java Main.java Parser.java Player.java POSTagger.java Room.java Synonyms.java World.java && java -cp ".:stanford-postagger.jar:lib/*" Main