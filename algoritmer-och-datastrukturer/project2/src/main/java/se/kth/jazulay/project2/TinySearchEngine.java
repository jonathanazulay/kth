package se.kth.jazulay.project2;

import java.util.HashMap;
import java.util.List;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Sentence;
import se.kth.id1020.util.Word;

public class TinySearchEngine implements TinySearchEngineBase {

    java.util.HashMap<Word, Attributes> words;
    QueryParser parser = new QueryParser();

    public TinySearchEngine() {
        this.words = new HashMap();
    }

    @Override
    public void preInserts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Sentence sntnc, Attributes atrbts) {
        for (Word w : sntnc.getWords()) {
            this.words.put(w, atrbts);
        }
    }

    @Override
    public void postInserts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Document> search(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String infix(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
