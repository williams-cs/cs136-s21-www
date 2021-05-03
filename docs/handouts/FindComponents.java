// Find the connected components of a graph
// Bill L 2019

/**
For each vertex v, build a map that holds, for each u !=v,
the previous vertex to u on the computed shortest path from
v to u
*/

import structure5.*;
import java.util.Scanner;

public class FindComponents {

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

  // Perform a BFS of g starting from vertex src to make a copy of the
  // connected component of g containing src

  public static <V,E> Graph<V,E> findComponent(Graph<V,E> g, V src) {
    // The graph that will hold the connected component
    Graph<V,E> component = new GraphListUndirected<V,E>();

    Queue<V> todo = new QueueList<V>(); int count = 0;
    g.visit(src);
    component.add(src);
    todo.enqueue(src);
    while (!todo.isEmpty()) {
      V node = todo.dequeue();
      AbstractIterator<V> neighbors = (AbstractIterator<V>) g.neighbors(node);
      while (neighbors.hasNext()) {
        V next = neighbors.next();
        if (!g.isVisited(next)) {
          // Add newly-visited vertex to component
          component.add(next);
          g.visit(next);
          todo.enqueue(next);
        }
        // Be sure to add ALL edges from the component (not just BFS edges)
        component.addEdge(node,next,null);
      }
    }
    return component;
  }

  public static void main(String[] args) {

    // Program requires a command line argument naming an input file
    if(args.length == 0) {
      System.out.println("Usage: java FindComponents <filename>");
      System.exit(0);
    }

    // Build the graph
    Graph<String,String> g = buildGraphFromFile(args[0]);

    // Build the components
    AbstractIterator<String> vertices = (AbstractIterator<String>) g.iterator();
    for(String v : vertices)
      // Only search from v if v is not already in a found component
      if(!g.isVisited(v)) {
        printGraph(findComponent(g,v));
        System.out.println();
      }
  }
}
