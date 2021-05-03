import structure5.*;
import java.util.Iterator;
import java.util.Scanner;

public class TrianglesFile {

  /**
  * Determine whether vertex src has two neighbors that form an edge
  * If so, return such an edge, else return null
  */
  public static <V,E> Edge<V,E> findEdgeAmongNeighbors(Graph<V,E> g, V src) {
    Edge<V,E> result = null;

    // Create a vector of the neighbors of src
    Iterator<V> nIt = g.neighbors(src);
    Vector<V> neighbors = new Vector<V>(g.degree(src));
    while(nIt.hasNext()) neighbors.add(nIt.next());

    // Compare each pair of neighbors looking for an edge
    for(int u = 0; u < neighbors.size(); u++) {
      for(int w = u+1; w < neighbors.size(); w++) {
        result = g.getEdge(neighbors.get(u), neighbors.get(w));
        // If an edge is found, return it
        if(result != null) return result;
      }
    }
    // No edge found: return null
    return result;
  }

  /**
  * Searches graph g for a trianlge: 3 pairwise-adjacent vertices
  * Outputs a triangle if one exists, elsed declares g triangle-free
  */
  public static <V,E> void findATriangle(Graph<V,E> g) {

    // Check each vertex until one is found with
    // two neighbors that form an edge
    for(V vert : g) {
      Edge<V,E> e = findEdgeAmongNeighbors(g, vert);
      // If an edge between neighbors of vert was found
      // Output the triangle
      if(e != null) {
        System.out.println("The vertices " + vert +
        ", " + e.here() + ", " + e.there() + " form a triangle");
        return;
      }
    }
    // No triangles exist
    System.out.println("The graph is triangle-free");
  }

  // Build a graph g from a file. File is assumed to have a sequence of edges,
  // each given by a pair of Strings representing the two vertices forming the edge

  public static Graph<String,String>  buildGraphFromFile(String filename) {
    Scanner s = new Scanner(new FileStream(filename));
    Graph<String,String> g = new GraphListUndirected<String,String>();

    while(s.hasNext()) {
      String v1 = s.next();
      String v2 = s.next();
      g.add(v1); g.add(v2);
      g.addEdge(v1,v2,"");
    }
    return g;
  }

  // Print the graph in a legible fashion, one adjacency list per line

  public static <V,E> void printGraph(Graph<V,E> g) {
    AbstractIterator<V> vertIter = (AbstractIterator<V>) g.iterator();

    for( V v : vertIter) {
      System.out.print(v + ": ");
      AbstractIterator<V> neighbors = (AbstractIterator<V>) g.neighbors(v);

      for( V u : neighbors) System.out.print(" -> " + u);
      System.out.println();
    }
  }

  public static void main(String[] args) {
      // Program requires a command line argument naming an input file
    if(args.length == 0) {
      System.out.println("Usage: java FindComponents <filename>");
      System.exit(0);
    }

    // Build the graph
    Graph<String,String> g = buildGraphFromFile(args[0]);

    printGraph(g);

    findATriangle(g);
  }
}
