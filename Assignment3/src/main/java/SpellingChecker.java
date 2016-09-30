// This program is copyright VUW.
// You are granted permission to use it to construct your answer to a COMP103 assignment.
// You may not distribute it in any other way without permission.

/* Code for COMP103, Assignment 3
 * Name:
 * Usercode:
 * ID:
 */

import ecs100.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/** SpellingChecker checks all the words in a file against a dictionary.
 *  The dictionary is read from the dictionary file into a set.
 *  When reading through the document with spelling errors, the program checks
 *  each word against the dictionary.
 *  Any word that is not in the dictionary is considered to be an error and
 *  is printed out.
 *
 *  The program records and prints out the total time taken to read
 *  all the words into the dictionary.
 *  It also records and prints out the total time taken to check all the words.
 *
 *  Note that the dictionary and the file to check are assumed to be all
 *  lowercase, with all punctuation removed.
 */

public class SpellingChecker{
    private static List<String> unsortedDictWords;
    private static List<String> sortedDictWords;
    private static List<String> plato = new ArrayList<>();
    public static void main(String[] arguments){
        UI.setDivider(1);
        try {
            sortedDictWords = Files.readAllLines(Paths.get("dictionarySorted.txt"));
            unsortedDictWords = Files.readAllLines(Paths.get("dictionaryUnsorted.txt"));
            Scanner s = new Scanner(new File("plato.txt"));
            s.forEachRemaining(plato::add);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UI.println(plato.size());
        //Its best to repeat a few times because java will try optimising and then you will find that it takes a differing amout of time.
        read5(new ArraySet<>(),true);
        read5(new ArraySet<>(),false);
        read5(new HashSet<>(),true);
        read5(new HashSet<>(),false);
        read5(new SortedArraySet<>(),true);
        read5(new SortedArraySet<>(),false);


        }
        private static long loading = 0;
        private static long checking = 0;
        private static long count = 0;
        private static final int iterAttemps = 1;
        private static void read5(Set<String> dictionary, boolean sorted) {
        UI.println("------------------");
        UI.println(sorted?"Sorted":"Unsorted");
        UI.println(dictionary.getClass());
        Trace.setVisible(true);
        loading = 0;
        checking = 0;
        count = 0;
        for (int i =0; i < iterAttemps; i++) {
        UI.println("Current Attempt: "+i);
        read(dictionary,sorted);
        }
        UI.println();
        UI.println("------------------");
        UI.printf("Average Loading: %.3f seconds\n", loading/iterAttemps/1000.0);
        UI.printf("Average Checking: %.3f seconds\n", checking/iterAttemps/1000.0);
        UI.printf("Words missing from dictionary:%d\n",count/iterAttemps);
        }
        static void read(Set<String> dictionary, boolean sorted) {
        //measure the set construction
        long start = System.currentTimeMillis();
        dictionary.addAll(sorted?sortedDictWords:unsortedDictWords);
        long loading = (System.currentTimeMillis()-start);
        //measure the set construction
        start = System.currentTimeMillis();
        List<String> notFound = plato.stream().filter(word -> !dictionary.contains(word)).collect(Collectors.toList());
        notFound.forEach(Trace::println);
        count += notFound.size();
        long checking = (System.currentTimeMillis()-start);
        Trace.println("------------------");
        Trace.println(sorted?"Sorted":"Unsorted");
        Trace.println(dictionary.getClass());
        SpellingChecker.loading+=loading;
        SpellingChecker.checking+=checking;
        }
        }
