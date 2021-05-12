// Find a shortest path between each pair of vertices
// Using number of edges in path as path length
// Bill L 2019

/**
Algorithm: For each vertex v, perform a BFS of the graph from v.
Store the BFS tree T for v in a map that holds, for each u !=v,
the previous vertex to u on the path in T from v to u. This patb is a
shortest path in the graph from v to u.
*/

import structure5.*;
import java.util.Scanner;

public class ShortestPathsByEdgeCount {

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
      for( V u : neighbors)
        System.out.print(" -> " + u);
      System.out.println();
    }
  }

  // Perform a BFS on g from src to build a map that stores a representation of
  // the BFS tree of g from src.
  // Map entry (v,u) encodes the fact that u is the
  // predecessor of v on the the BFS tree path from src to v.

  public static <V,E> Map<V,V> singleSourcePaths(Graph<V,E> g, V src) {
    Map<V,V> routingTable = new Hashtable<V,V>();

    Queue<V> todo = new QueueList<V>(); int count = 0;
    g.visit(src);
    routingTable.put(src,src);
    todo.enqueue(src);
    while (!todo.isEmpty()) {
      V node = todo.dequeue();
      AbstractIterator<V> neighbors = (AbstractIterator<V>) g.neighbors(node);
      while (neighbors.hasNext()) {
        V next = neighbors.next();
        if (!g.isVisited(next)) {
          routingTable.put(next,node);
          g.visit(next);
          todo.enqueue(next);
        }
      }
    }
    return routingTable;
  }

  // Given the Map representing the BFS tree with root 'from', print out
  // the path in the tree from 'from' to 'to' recursively, so that it
  // appears in the correct order.
  public static <V> void printPath(V from, V to, Map<V,V> routingTable) {
    // If we are at the root, 'from', print 'from'
    if(from.equals(to) ) {
      System.out.print(from);
      return;
    }
    // Otherwise, recursively print tha path from 'from' to the predecessor
    // of 'to', then print 'to'
    printPath(from, routingTable.get(to), routingTable);
    System.out.print("-> " + to);
  }

  public static void main(String[] args) {

    // Program requires a command line argument naming an input file
    if(args.length == 0) {
      System.out.println("Usage: java ShortestPathsByEdgeCount <filename>");
      System.exit(0);
    }

    // Build the graph
    Graph<String,String> g = buildGraphFromFile(args[0]);
    printGraph(g);

    // Create the Map holding all of the BFS tree Maps
    Map<String,Map<String,String>> routingTables = new Hashtable<String,Map<String,String>>();

    // For each vertex, build its BFS map
    AbstractIterator<String> vertices = (AbstractIterator<String>) g.iterator();
    for(String v : vertices) {
      routingTables.put(v,singleSourcePaths(g,v));
      g.reset();
    }

    // Now let the user ask shortest path questions
    Scanner sc = new Scanner(System.in);
    while(true){
      System.out.println("Enter two vertex names (enter ctrl-c to quit)");
      String from = sc.next();
      String to = sc.next();

      // Get the map representing 'from' BFS tree to look up path
      Map<String,String> routeTable = routingTables.get(from);
      if(!routeTable.containsKey(to))
      System.out.println("Can't get to " + to + " from " + from + "!");
      else {
        printPath(from,to,routingTables.get(from));
        System.out.println();
      }
    }
  }
}
