package it.unibo.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedList;
import static java.util.concurrent.TimeUnit.NANOSECONDS;
import java.util.HashMap;


/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    private static final int START = 1000;
    private static final int STOP = 2000;
    private static final int ELEMENTS = 100000;
    private static final int READS = 1000;
    private static final long AFRICA_POPULATION = 1110635000L;
    private static final long AMERICAS_POPULATION = 972005000L;
    private static final long ANTARCTICA_POPULATION = 0L;
    private static final long ASIA_POPULATION = 4298723000L;
    private static final long EUROPE_POPULATION = 742452000L;
    private static final long OCEANIA_POPULATION = 38304000L;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */
        final List<Integer> arraylist = new ArrayList<>();
        for (int i = START; i < STOP; i++) {
            arraylist.add(i);
        }
        /*
        

         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */
        final List<Integer> linkedL = new LinkedList<>(arraylist);
        log(linkedL);
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */
        final int elem = arraylist.get(arraylist.size()-1);
        arraylist.set(arraylist.size()-1, arraylist.get(0));
        arraylist.set(0, elem );
        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        final var builder = new StringBuilder();
        for (final int i : arraylist) {
            builder.append(i);
            builder.append((", "));
        }
        log(builder);
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        long time = System.nanoTime();
        for (int i = 0; i < ELEMENTS; i++) {
            arraylist.add(0, i);
        }

        time = System.nanoTime() - time;
        log("Inserting " + ELEMENTS + " in the head of an arraylist took " + timeToString(time));

        time = System.nanoTime();
        for (int i = 0; i < ELEMENTS; i++) {
            linkedL.add(0, i);
        }
        time = System.nanoTime() - time;
        log("Inserting " + ELEMENTS + " in the head of a linkedlist took " + timeToString(time));

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example TestPerformance.java.
         */
        time = System.nanoTime();
        for (int i = 0; i < READS; i++) {
            arraylist.get(arraylist.size() / 2);            
        }
        time = System.nanoTime() - time;
        log("Reading " + READS + " times an element in the middle of an arraylist took " + timeToString(time));

        time = System.nanoTime();
        for (int i = 0; i < READS; i++) {
            linkedL.get(linkedL.size() / 2);            
        }
        time = System.nanoTime() - time;
        log("Reading " + READS + " times an element in the middle of a linkedlist took " + timeToString(time));

        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */
        final Map<String, Long> world = new HashMap<>();
        world.put("Africa", AFRICA_POPULATION);
        world.put("Americas", AMERICAS_POPULATION);
        world.put("Antarctica", ANTARCTICA_POPULATION);
        world.put("Asia", ASIA_POPULATION);
        world.put("Europe", EUROPE_POPULATION);
        world.put("Oceania", OCEANIA_POPULATION);
        /*
         * 8) Compute the population of the world
         */
        long populationSum = 0;
        for (final long population : world.values()) {
            populationSum += population;
        }

        log("We are ~" + populationSum);



    }
    private static void log(final Object message) {
        System.out.println(message); 
        }
        private static String timeToString(final long nanosec) {
            return nanosec + "ns (" + NANOSECONDS.toMillis(nanosec) + "ms)";
        }    
}
