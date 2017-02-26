/*
THIS CODE IS MY OWN WORK, IT WAS WRITTEN WITHOUT CONSULTING
A TUTOR OR CODE WRITTEN BY OTHER STUDENTS.
Saad Akhtar and Yikan Ge

Collaboration statement:
Saad wrote much of the code for adding a friend, removing a friend, and finding the shortest path in "Facespace_run" and also wrote the Addfriend,
Removefriend, and Shortest methods in "Facespace". Saad also edited much of the code in "Facespace_run" and "Facespace" and was the one who obtained
the "AdjMatrixGraph" code from the Graph Powerpoint presented in class. Yikan wrote the code for adding a user and updating a user and wrote the insert
and findExact methods in "Facespace". He was also the one who created the Facespace_run and Facespace classes and much of the code written is based off
of the organization of his initial draft of the Facespace_run and Facespace classes. Both of us added our own comments as we saw fit. We both acknowledge
that the work was divided fairly between ourselves.
*/

/*
As mentioned in the collaboration statement, the code below is basically the code for adjacency lists that was provided in the Graph Powerpoint that
was presented in class. However, the removeEdge method is entirely of Saad's creation, though it is based off of the addEdge method provided in the code.
You can find the original code here: http://algs4.cs.princeton.edu/41undirected/AdjMatrixGraph.java.html
You can also find the same code by clicking the hyperlink directly below "Adjacency Matrix representation" on Slide 26 of 120 in the Graph Powerpoint.
*/

import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjMatrixGraph {
    private int V;
    private int E;
    private boolean[][] adj;
    
    // empty graph with V vertices
    public AdjMatrixGraph(int V) {
        if (V < 0) throw new RuntimeException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        this.adj = new boolean[V][V];
    }

    // number of vertices and edges
    public int V() { return V; }
    public int E() { return E; }

    // add undirected edge v-w
    public void addEdge(int v, int w) {
        if (!adj[v][w]) E++;
        adj[v][w] = true;
        adj[w][v] = true;
    }
    
    // remove undirected edge v-w
    public void removeEdge(int v, int w) {
    	if (adj[v][w]) E--;
    	adj[v][w] = false;
    	adj[w][v] = false;
    }

    // does the graph contain the edge v-w?
    public boolean contains(int v, int w) {
        return adj[v][w];
    }

    // return list of neighbors of v
    public Iterable<Integer> adj(int v) {
        return new AdjIterator(v);
    }

    // support iteration over graph vertices
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        int v, w = 0;
        AdjIterator(int v) { this.v = v; }

        public Iterator<Integer> iterator() { return this; }

        public boolean hasNext() {
            while (w < V) {
                if (adj[v][w]) return true;
                w++;
            }
            return false;
        }

        public Integer next() {
            if (hasNext()) { return w++;                         }
            else           { throw new NoSuchElementException(); }
        }

        public void remove()  { throw new UnsupportedOperationException();  }
    }

    // string representation of Graph - takes quadratic time
    public String toString() {
        String NEWLINE = System.getProperty("line.separator");
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj(v)) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}