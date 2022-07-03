import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WordNet {
    private final Map<String, List<Integer>> nouns; // Used to map each word net noun to the synsets it is associated to.
    private final Map<Integer, String> synsets; // Used to map each sunset id to the synset itself.
    private int numSynsets;
    private final SAP sap;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        if (synsets == null || hypernyms == null) {
            throw new IllegalArgumentException();
        }
        this.nouns = new HashMap<>();
        this.synsets = new HashMap<>();
        // Read the first file of the synsets.
        File f = new File(synsets);
        Scanner sc;
        try {
            sc = new Scanner(f, "UTF-8");
        } catch (FileNotFoundException e1) {
            throw new IllegalArgumentException(e1);
        }
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] values = data.split(",");
            String synset = values[1];
            for (String word : synset.split(" ")) {
                if (!nouns.containsKey(word)) { // If the noun is not present in the map, put it and inialize its value
                                                // as an empty list.
                    nouns.put(word, new ArrayList<Integer>());
                }
                nouns.get(word).add(numSynsets);
            }
            this.synsets.put(numSynsets, synset);
            this.numSynsets++;
        }
        sc.close();
        Digraph G = new Digraph(numSynsets); // The number of vertices of the graph equals the number of synsets.
        // Reading the next file to add edges to the graph.
       
            try {
                sc = new Scanner(new File(hypernyms), "UTF-8");
            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        
        int v = 0; // To start with the first vertix.
        while (sc.hasNext()) {
            String data = sc.nextLine();
            String[] values = data.split(",");
            int len = values.length;
            for (int i = 1; i < len; i++) { // Loop over all the vertices connected to the current vertix.
                G.addEdge(v, Integer.parseInt(values[i]));
            }
            v += 1;
        }
        sc.close();
        this.sap = new SAP(G);
        DirectedCycle finder = new DirectedCycle(G);
        if (finder.hasCycle()) {
            throw new IllegalArgumentException();
        }
    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return nouns.keySet();
    }

    // is the word a WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException();
        return nouns.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();
        int d = sap.length(this.nouns.get(nounA), this.nouns.get(nounB));
        return d;
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA
    // and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null)
            throw new IllegalArgumentException();
        if (!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException();
        int a = sap.ancestor(this.nouns.get(nounA), this.nouns.get(nounB));
        return this.synsets.get(a);
    }

    // do unit testing of this class
    public static void main(String[] args) {
        /*
         * WordNet w = new WordNet("file1.txt, file2.txt");
         * System.out.println(w.nouns.containsKey("cat"));
         */
    }

}
