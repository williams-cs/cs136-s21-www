// A program to solve Mazes with linear structures.
// (c) 1996, 2001 duane a. bailey
// for reading:
import java.io.*;
import structure5.*;
import java.util.Iterator;
/*
####################          ####################
#s#       #f   #   #          #s#       #f...#...#
# ####### #### # # #          #.####### ####.#.#.#
#         #  # ### #          #.........#..#.###.#
##### ### #        #          ##### ###.#........#
#   # #   ####### ##          #   # #...#######.##
#   # # ### #   #  #          #   # #.### #...#..#
#   # # #   # # ## #          #   # #.#...#.#.##.#
#     #   #   #    #          #     #...#...#....#
####################          ####################
*/
public class MazeRunner {
	public static void usage() {
		System.out.println("Please provide a single filename that contains a");
		System.out.println("maze specification as the firste argument, and");
		System.out.println("optionally specify a Stack (s) or Queue (q):");
		System.out.println("\tjava MazeRunner maze.txt q");
		System.exit(0);
	}

	public static void main(String[] arguments) {
		if (arguments.length < 1) {
			usage();
			return;
		}

		Maze m = new Maze(arguments[0]); // the maze
		// Maze m = new Maze("MazeRunner.input"); // the maze
		Position goal = m.finish(); // where the finish is

		Position square = null; // the current position
		// a linear structure to manage search
		Linear<Position> todo;
		if (arguments.length > 1 && arguments[1].equals("q")) {
			todo = new QueueList<Position>();
		} else {
			todo = new StackList<Position>();
		}

		int rounds = 0;

		// begin by priming the queue(stack) w/starting position
		todo.add(m.start());
		while (!todo.isEmpty()) { // while we haven't finished exploring
		// take the next position and check for finish
			square = todo.remove();
			if (m.isVisited(square)) {
				continue; // been here before, skip it
			}

			rounds++;

			if (square.equals(goal)) {
				System.out.println(m); // print solution
				System.out.println("Maze solved in " + rounds + " rounds");
				return;
			}

			// not finished.
			// visit this location, and add neighbors to pool
			m.visit(square);

			// Print the maze to document our journey:
			System.out.println(m);

			// add the reachable neighbors to our todo list (if they have
			// already been visited, we just ignore them in our loop)

			if (m.isClear(square.north())) todo.add(square.north());
			if (m.isClear(square.west())) todo.add(square.west());
			if (m.isClear(square.south())) todo.add(square.south());
			if (m.isClear(square.east())) todo.add(square.east());

			// Iterator<Position> neighbors = m.neighbors(square);
			// while(neighbors.hasNext()) todo.add(neighbors.next());
		}
		System.out.println("Gave up on maze after " + rounds + " rounds");
		System.out.println(m);
	}
}

// A class for maintaining positions
// August 1996 kimberly tabtiang, duane bailey

// Position is a two-value pair: row & column
// Feature: knows about compass-relations

class Position
{
	protected int row, col;     // where it's at

	public Position(int r, int c)
	// pre: r & c are position (note order)
	// post: constructs position [r][c]
	{
		row = r;
		col = c;
	}

	public int col()
	// post: returns column/horizontal of position
	{
		return col;
	}

	public int row()
	// post: returns row/vertical of position
	{
		return row;
	}

	public Position north()
	// post: returns position above
	{
		return new Position(row-1,col);
	}

	public Position south()
	// post: returns position below
	{
		return new Position(row+1,col);
	}

	public Position east()
	// post: returns position to right
	{
		return new Position(row,col+1);
	}

	public Position west()
	// post: returns position to left
	{
		return new Position(row,col-1);
	}

	public boolean equals(Object other)
	// post: returns true iff objects represent same position
	{
		Position that = (Position)other;
		return ((this.row == that.row) &&
		(this.col == that.col));
	}

	public String toString()
	// post: returns string representation of Position
	{
		return "["+row+"]["+col+"]";
	}
}

// A class for reading and manipulating mazes.
// August 1996 kimberly tabtiang, duane bailey

/* An example maze:
10
####################
#s#       #f   #   #
# ####### #### # # #
#         #  # ### #
##### ### #        #
#   # #   ####### ##
#   # # ### #   #  #
#   # # #   # # ## #
#     #   #   #    #
####################
*/


// an invisible structure for olding maze state
// about a position
class cell
{
	boolean clear = true;       // this location has no wall
	boolean visited = false;    // we've seen this before
}

// the main maze class.
class Maze
{
	protected int nrows;        // number of rows in maze
	protected cell[][] map;     // array of cell arrays; can be "ragged"
	protected Position start;   // location of the start
	protected Position finish;  // coordinates of the finish

	public Maze(String filename)
	// pre: filename is the name of a maze file. # is a wall.
	//      's' marks the start, 'f' marks the finish.
	// post: reads and constructs maze from file <filename>
	{
		ReadStream rstream = null; // eventually, the reader

		try // exception occurs on FileInputStream or File
		{
			rstream = new ReadStream(new FileInputStream(new File(filename)));
		}
		catch (FileNotFoundException e)
		{
			Assert.fail("File could be found.");
		}
		try
		{
			// we read # of rows.  the # of cols depends on line length
			nrows = rstream.readInt();
			rstream.readLine();

			// allocate rows.  Understand this.
			map = new cell[nrows][]; // allocate row pointers

			// for each row, read it.
			for (int row=0; row<nrows; row++) { // read rows
				// read in a line
				String s = rstream.readLine();
				// allocate array of cell refs for each line
				int len = s.length();
				map[row] = new cell[len];
				// now, initialize each cell reference to a cell
				for (int col=0; col<len; col++) {
					char c = s.charAt(col);
					map[row][col] = new cell();
					// process each character
					switch(c) {
						case 's':
						start = new Position(row,col);
						break;
						case 'f':
						finish = new Position(row,col);
						break;
						case ' ':
						case '.': // means visited, but clear
						break;
						case '#':
						default:
						map[row][col].clear = false;
					}
				}
			}

		}
		catch (Exception e)
		{
			Assert.fail("IOException on maze read.");
		}
		Assert.condition((start != null) && (finish != null),
		"start & finish must be defined in maze.");
	}

	public void visit(Position p)
	// pre: p is a position within the maze
	// post: cell at position p is set to visited
	{
		if (isValid(p))
		{
			map[p.row()][p.col()].visited = true;
		}
	}

	public boolean isVisited(Position p)
	// pre: p is a position within the maze
	// pos: returns true if the position has been visited
	{
		return isValid(p) && map[p.row()][p.col()].visited;
	}

	public Position start()
	// post: returns start position
	{
		return start;
	}

	public Position finish()
	// post: returns finish position
	{
		return finish;
	}

	protected boolean isValid(Position p)
	// post: returns true iff p is a location within the maze
	{
		int row = p.row();
		int col = p.col();
		return (row >= 0) && (row < nrows) &&
		(col >= 0) && (col < map[row].length);
	}

	public boolean isClear(Position p)
	// post: returns true iff p is a clear location within the maze
	{
		return isValid(p) && map[p.row()][p.col()].clear;
	}

	public String toString()
	// post: returns string representing maze state
	//       "." is used to mark visited non-start/finish locations
	{
		// a mutable string
		StringBuffer s = new StringBuffer();

		s.append(nrows).append('\n');
		for (int row=0; row < nrows; row++) {
			for (int col=0; col < map[row].length; col++) {
				Position here = new Position(row,col);
				// determine character to represent location
				// note --- no implementation dependent actions
				if (here.equals(start)) s.append('s');
				else if (here.equals(finish)) s.append('f');
				else if (isVisited(here)) s.append('.');
				else if (isClear(here)) s.append(' ');
				else s.append('#');
			}
			s.append('\n');
		}
		return s.toString();
	}

	public Iterator<Position> neighbors(Position p)
	// pre: p is a valid, clear position
	// post: returns valid and clear neighbors in compass order
	{
		Assert.pre(isValid(p),"Seek neighbors of clear positions.");
		Vector<Position> v = new Vector<Position>(4);
		if (isClear(p.north())) v.add(p.north());
		if (isClear(p.west())) v.add(p.west());
		if (isClear(p.south())) v.add(p.south());
		if (isClear(p.east())) v.add(p.east());
		// v.elements: an iterator over v
		return v.iterator();
	}

	public static void main(String args[])
	{
		System.out.println(new Maze("MazeRunner.input"));
	}
}
/*
10
####################
#s#       #f...#...#
#.####### ####.#.#.#
#.........#..#.###.#
##### ###.#........#
#   # #...#######.##
#   # #.### #...#..#
#   # #.#...#.#.##.#
#     #...#...#....#
####################


*/
