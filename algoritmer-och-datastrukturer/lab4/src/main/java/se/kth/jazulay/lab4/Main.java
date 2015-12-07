package se.kth.jazulay.lab4;

import java.util.Iterator;

import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Stopwatch;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        Trie trie = new Trie();

        URL url = ClassLoader.getSystemResource("kap1.txt");

        if (url != null) {
            System.out.println("Reading from: " + url);
        } else {
            System.out.println("Couldn't find file: kap1.txt");
        }

        In input = new In(url);

        Stopwatch sw = new Stopwatch();
        while (!input.isEmpty()) {
            String line = input.readLine().trim();
            String[] words = line.split("(\\. )|:|,|;|!|\\?|( - )|--|(\' )| ");
            String lastOfLine = words[words.length - 1];

            if (lastOfLine.endsWith(".")) {
                words[words.length - 1] = lastOfLine.substring(0, lastOfLine.length() - 1);
            }
               
            
            for (String word : words) {
                String word2 = word.replaceAll("\"|\\(|\\)", "");

                if (word2.isEmpty()) {
                    continue;
                }

                System.out.println(word2);
                trie.put(word2);
            }
        }
        System.out.println("\nAdded all words to trie in " + sw.elapsedTime() + " seconds");
        
        //Perform analysis
        System.out.println("1) What are the 10 words with the highest frequency?");
        System.out.println(tenWordsWithMaxFrequency(trie));
        
        System.out.println("2) What are the 10 words with the lowest frequency?");
        System.out.println(tenWordsWithMinFrequency(trie));
        
        System.out.println("3) Which prefix of length 2 has the highest frequency?");
        System.out.println(prefixLength2HightestFrequency(trie));
        
        System.out.println("4) What is the letter that the most different words start with? (Not frequency this time.)");
        System.out.println(mostCommonFirstLetter(trie));
    }
    
    public static String[] tenWordsWithMaxFrequency (Trie trie) {
        return new String[10];
    }
    
    public static String[] tenWordsWithMinFrequency (Trie trie) {
        return new String[10];
    }
    
    public static String prefixLength2HightestFrequency (Trie trie) {
        return "";
    }
    
    public static char mostCommonFirstLetter (Trie trie) {
        return ' ';
    }
}