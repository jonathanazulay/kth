package se.kth.jazulay.lab4;

import java.util.Iterator;

import edu.princeton.cs.introcs.In;
import java.net.URL;

public class Main {

   public static void main(String[] args) {
      URL url = ClassLoader.getSystemResource("kap1.txt");

      if (url != null) {
         System.out.println("Reading from: " + url);
      } else {
         System.out.println("Couldn't find file: kap1.txt");
      }

      In input = new In(url);

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
            // Add the word to the trie
         }
      }
   //Perform analysis
   }
   
   public static void jonathanTest () {
       Trie t = new Trie();
        t.put("hej");
        t.put("hej");
        t.put("hejda");
        t.put("hejdara");
        
        t.put("a");
        t.put("abc");
        t.put("abcd");
        t.put("abcde");
        t.put("abcdef");
        t.put("abcdef");
        t.put("abcdef");

        System.out.println(t.distinct("a"));
        System.out.println(t.distinct("abcd"));
        System.out.println(t.distinct("hej"));
        
        
        Iterator i = t.iterator("");
        while (i.hasNext()) {
            System.out.println(i.next());
        }
   }
}