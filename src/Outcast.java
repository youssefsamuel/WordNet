public class Outcast {
    private WordNet w;

    public Outcast(WordNet wordnet) // constructor takes a WordNet object
    {
        if (wordnet == null)
            throw new IllegalArgumentException();
        this.w = wordnet;
    }

    public String outcast(String[] nouns) // given an array of WordNet nouns, return an outcast
    {
        if (nouns == null)
            throw new IllegalArgumentException();
        String x = null;
        int min = 0;
        for (int i = 0; i < nouns.length; i++) {
            int d = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (j != i)
                    d += this.w.distance(nouns[i], nouns[j]);
            }
            if (d > min) {
                x = nouns[i];
                min = d;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        /*
         * WordNet w = new
         * WordNet("D:\\Algorithms,Part2\\Slides\\Week1\\wordnet\\synsets.txt",
         * "D:\\Algorithms,Part2\\Slides\\Week1\\wordnet\\hypernyms.txt"); Outcast o =
         * new Outcast(w); String[] l = new String[8];
         * 
         * l[0] = "water"; l[1] = "soda"; l[2] = "bed"; l[3] = "orange_juice"; l[4] =
         * "milk"; l[5] = "apple_juice"; l[6] = "coffee"; l[7] = "tea";
         * 
         * System.out.println(o.outcast(l));
         */
    }

}
