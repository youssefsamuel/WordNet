import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;

public class SAP {
    private final Digraph G;
    private int min;
    private int com;

    // constructor takes a Digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if (G == null)
            throw new IllegalArgumentException();
        this.G = new Digraph(G.V());
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v))
                this.G.addEdge(v, w);
        }
    }

    private void find(int v, int w) {
        this.com = -1;
        this.min = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);

        for (int x = 0; x < G.V(); x++) {
            if (bfsV.hasPathTo(x) && bfsW.hasPathTo(x)) {
                int g = bfsV.distTo(x) + bfsW.distTo(x);
                if (g < min) {
                    min = g;
                    com = x;
                }
            }
        }
        if (min == Integer.MAX_VALUE)
            min = -1;
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        if (v >= G.V() || w >= G.V() || v < 0 || w < 0)
            throw new IllegalArgumentException();
        find(v, w);
        return this.min;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path;
    // -1 if no such path
    public int ancestor(int v, int w) {
        if (v >= G.V() || w >= G.V() || v < 0 || w < 0)
            throw new IllegalArgumentException();
        find(v, w);
        return this.com;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in
    // w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException();
        if (!v.iterator().hasNext())
            throw new IllegalArgumentException();
        if (!w.iterator().hasNext())
            throw new IllegalArgumentException();
        min = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
        for (int x = 0; x < G.V(); x++) {
            if (bfsV.hasPathTo(x) && bfsW.hasPathTo(x)) {
                int g = bfsV.distTo(x) + bfsW.distTo(x);
                if (g < min)
                    min = g;
            }
        }
        if (min == Integer.MAX_VALUE)
            return -1;
        return min;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such
    // path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException();
        if (!v.iterator().hasNext())
            throw new IllegalArgumentException();
        if (!w.iterator().hasNext())
            throw new IllegalArgumentException();
        com = -1;
        min = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(this.G, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.G, w);
        for (int x = 0; x < G.V(); x++) {
            if (bfsV.hasPathTo(x) && bfsW.hasPathTo(x)) {
                int g = bfsV.distTo(x) + bfsW.distTo(x);
                if (g < min) {
                    min = g;
                    com = x;
                }
            }
        }
        return com;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        /*
          Digraph G = new Digraph(25); G.addEdge(1, 0); G.addEdge(2, 0); G.addEdge(3,
          1); G.addEdge(4, 1); G.addEdge(5, 2); G.addEdge(6, 2); G.addEdge(7, 3);
          G.addEdge(8, 3); G.addEdge(9, 3); G.addEdge(10, 5); G.addEdge(12, 5);
         G.addEdge(11, 5); G.addEdge(13, 7); G.addEdge(14, 7); G.addEdge(15, 9);
          G.addEdge(16, 9); G.addEdge(17, 10); G.addEdge(18, 10); G.addEdge(19, 12);
          G.addEdge(20, 12); G.addEdge(21, 16); G.addEdge(22, 16); G.addEdge(23, 20);
          G.addEdge(24, 20); SAP s = new SAP(G); ArrayList<Integer> list1 = new
          ArrayList<>(); 
          
          ArrayList<Integer> list2 = new ArrayList<>();
           System.out.println(s.length(list1, list2));
          System.out.println(s.ancestor(list1, list2)); G.addEdge(13, 0);
          System.out.println(s.length(list1, list2));
          System.out.println(s.ancestor(list1, list2));
         */

    }

}
