// A program to use Dijkstra's algorithm to find the
// shortest road from here to thereName.
// (c) 1996, 2001 duane a. bailey

import structure5.*;
import java.io.*;
import java.util.Iterator;

public class Dijkstra {

    public static
        Map<String,ComparableAssociation<Integer,Edge<String,Integer>>>
        dijkstra(Graph<String,Integer> g, String start)
    // pre: g is a graph; start is source vertex
    // post: returns a dictionary of vertex-based results
    //       value is association (total-distance,prior-edge)
    {
        // keep a priority queue of distances from source
        PriorityQueue<ComparableAssociation<Integer,Edge<String,Integer>>>
            q = new SkewHeap<ComparableAssociation<Integer,
                                                Edge<String,Integer>>>();
        // results, sorted by vertex
        Map<String,ComparableAssociation<Integer,Edge<String,Integer>>>
            result = new Table<String,
                            ComparableAssociation<Integer,
                                               Edge<String,Integer>>>();
        String v = start;       // last vertex added
        // result is a (total-distance,previous-edge) pair
        ComparableAssociation<Integer,Edge<String,Integer>> possible =
            new ComparableAssociation<Integer,Edge<String,Integer>>(0,null);
        // as long as we add a new vertex...
        while (v != null)
        {
            if (!result.containsKey(v))
            {
                // visit node v - record incoming edge
                result.put(v,possible);
                // vDist is shortest distance to v
                int vDist = possible.getKey();

                // compute and consider distance to each neighbor
                Iterator<String> ai = g.neighbors(v);
                while (ai.hasNext())
                {
                    // get edge to neighbor
                    Edge<String,Integer> e = g.getEdge(v,ai.next());
                    // construct (distance,edge) pair for possible result
                    possible = new ComparableAssociation<Integer,
                                Edge<String,Integer>>(vDist+e.label(), e);
                    q.add(possible);    // add to priority queue
                }
            }
            // now, get closest (possibly unvisited) vertex
            if (!q.isEmpty())
            {
                possible = q.remove();
                // get destination vertex (take care w/undirected graphs)
                v = possible.getValue().there();
                if (result.containsKey(v))
                    v = possible.getValue().here();
            } else {
                // no new vertex (algorithm stops)
                v = null;
            }
        }
        return result;
    }

    static public void main(String[] args) {
        Graph<String,Integer> g = new GraphListUndirected<String,Integer>();

        // towns and their names
        String hereName, thereName;// two generic towns
        String startName, finishName; // the specific query

        // a road to be added or manipulated
        Integer length;
        ReadStream input = new ReadStream();

        // read in all roads, add two copies <-> bidirectional
        for (hereName = input.readString();
             !hereName.equals("end");
             hereName = input.readString()) {

            thereName = input.readString();
            input.readString();
            length = input.readInt();

            g.add(hereName);
            g.add(thereName);
            // for getting from hereTown to thereTown
            g.addEdge(hereName, thereName, length);
            // for getting from thereTown to hereTown
            g.addEdge(thereName, hereName, length);
        }

        // read in the source town
        startName = input.readString();

        Map<String,ComparableAssociation<Integer,Edge<String,Integer>>> results =
            dijkstra(g,startName);
        Iterator<ComparableAssociation<Integer,Edge<String,Integer>>> vi = results.values().iterator();
        for (String dest : results.keySet())
        {
            Association<Integer,Edge<String,Integer>> a = vi.next();
            if (dest.equals(startName)) continue;
            String source = a.getValue().here();
            if (source.equals(dest))
                source = a.getValue().there();
            int len = a.getKey();
            System.out.println(dest+" is "+len+" miles from "+startName+" (via "+source+")");
        }
    }
}
