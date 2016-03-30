
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

import edu.stanford.nlp.ling.Sentence;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

/** This demo shows user-provided sentences (i.e., {@code List<HasWord>})
 *  being tagged by the tagger. The sentences are generated by direct use
 *  of the DocumentPreprocessor class.
 *
 *  @author Christopher Manning
 */
public class POSTagger {

  MaxentTagger tagger;

  public POSTagger(String taggerModel) {
    tagger = new MaxentTagger(taggerModel);
  }

  public void postag(String[] comWords){
    ArrayList<String> words = new ArrayList<String>();

    List<HasWord> sent = Sentence.toWordList(comWords);
    List<TaggedWord> taggedSent = tagger.tagSentence(sent);
    for(TaggedWord tw : taggedSent){
      if(tw.tag().startsWith("VB") || tw.tag().startsWith("NN")){
        System.out.println(tw.word());
      }
    }
  }
}
