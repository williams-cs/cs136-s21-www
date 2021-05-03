import structure5.*;
import java.util.Iterator;

class RecDFSComponentSize {

  public static <V,E> int DFS(Graph<V,E> g, V src) {
    g.visit(src);
    int count = 1;
    Iterator<V> neighbors = g.neighbors(src);
    while (neighbors.hasNext()) {
      V next = neighbors.next();
      if (!g.isVisited(next)) count+= DFS(g, next);
    }
    return count;
  }

  public static void main(String[] args) {
    // First an undirected graph
    Graph<String,Integer> g = new GraphListUndirected<String,Integer>();

    g.add("A"); g.add("B"); g.add("C"); g.add("D"); g.add("E");
    g.addEdge("A","B", 1);
    g.addEdge("A","C", 1);
    g.addEdge("B","C", 1);
    g.addEdge("C","D", 1);
    g.addEdge("D","B", 1);
    g.addEdge("D","E", 1);


    for( String v : g) {
      System.out.println("Number of vertices reachable from " +
      v + ": " + DFS(g,v) );
      g.reset(); // "unvisit" each vertex
    }

    System.out.println("\n\n");

    // Then a directed graph
    g =new GraphListDirected<String,Integer>();

    g.add("A"); g.add("B"); g.add("C"); g.add("D"); g.add("E");
    g.addEdge("A","B", 1);
    g.addEdge("A","C", 1);
    g.addEdge("B","C", 1);
    g.addEdge("C","D", 1);
    g.addEdge("D","B", 1);
    g.addEdge("D","E", 1);


    for( String v : g) {
      System.out.println("Number of vertices reachable from " +
      v + ": " + DFS(g,v) );
      g.reset(); // "unvisit" each vertex
    }
  }

}
