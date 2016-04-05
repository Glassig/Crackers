http://cmusphinx.sourceforge.net/wiki/tutorialsphinx4
tutorial!!!!


How you are supposed to compile it, but NOPE!
javac -cp \* TranscriberDemo.java
java -cp .:\* TranscriberDemo 


Bara lite tydligare (funkar inte heller):
1. javac -cp ".:sphinx4-core-1.0.0.jar:sphinx4-data-1.0.0.jar:" TranscriberDemo.java
2. java -cp ".:sphinx4-core-1.0.0.jar:sphinx4-data-1.0.0.jar:" TranscriberDemo
