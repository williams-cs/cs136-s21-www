// Count word frequencies
// Updated and simplified Fall 2020

import structure5.*;

public class GenWordFreq {
    public static void main(String[] args) {

	     Vector<Association<String,Integer>> vocab =
        new Vector<Association<String,Integer>>(1000);

       java.util.Scanner r = new java.util.Scanner(System.in);

       // for each word on input (user types ctrl-d to end input)
       while (r.hasNext()) {
  	    // read in and tally instance of a word
  	    String word = r.next();

        // Build an association with word as getKey
        // Give it frequency 1 in case it's a new word
        Association<String,Integer> wordInfo =
          new Association<String,Integer>(word,1);

  	    // Search for word in vocab
        // If found, update it's frequency
        // otherwise, add it to vocab vector
        int loc = vocab.indexOf(wordInfo);
        if(loc > -1) {
          // get the association
      		wordInfo = vocab.get(loc);
          // update the getValue
          wordInfo.setValue(wordInfo.getValue() + 1);
        }else {
          vocab.add(wordInfo);
        }
  	}

  	// print out the accumulated word frequencies
    // Use fancy for-each syntax because we are the cool kids
  	for (Association<String,Integer> wordInfo : vocab) {
      System.out.println(
      wordInfo.getKey()+" occurs "+
      wordInfo.getValue()+" times.");
    }
  }
}
/*
four score and seven years ago our fathers brought forth on this
continent a new nation conceived in liberty and dedicated to the
proposition that all men are created equal now we are engaged in a
great civil war testing whether that nation or any nation so conceived
and so dedicated can long endure we are met on a great battlefield of
that war we have come to dedicate a portion of that field as a final
resting place for those who here gave their lives that that nation
might live it is altogether fitting and proper that we should do this
but in a larger sense we cannot dedicate we cannot consecrate we
cannot hallow this ground the brave men living and dead who struggled
here have consecrated it far above our poor power to add or detract
the world will little note nor long remember what we say here but it
can never forget what they did here it is for us the living rather to
be dedicated here to the unfinished work which they who fought here
have thus far so nobly advanced it is rather for us to be here
dedicated to the great task remaining before us that from these
honored dead we take increased devotion to that cause for which they
gave the last full measure of devotion that we here highly resolve
that these dead shall not have died in vain that this nation under God
shall have a new birth of freedom and that government of the people by
the people for the people shall not perish from the earth
four occurs 1 times.
score occurs 1 times.
and occurs 6 times.
seven occurs 1 times.
years occurs 1 times.
ago occurs 1 times.
our occurs 2 times.
fathers occurs 1 times.
brought occurs 1 times.
forth occurs 1 times.
on occurs 2 times.
this occurs 4 times.
continent occurs 1 times.
a occurs 7 times.
new occurs 2 times.
nation occurs 5 times.
conceived occurs 2 times.
in occurs 4 times.
liberty occurs 1 times.
dedicated occurs 4 times.
to occurs 8 times.
the occurs 11 times.
proposition occurs 1 times.
that occurs 13 times.
all occurs 1 times.
men occurs 2 times.
are occurs 3 times.
created occurs 1 times.
equal occurs 1 times.
now occurs 1 times.
we occurs 10 times.
engaged occurs 1 times.
great occurs 3 times.
civil occurs 1 times.
war occurs 2 times.
testing occurs 1 times.
whether occurs 1 times.
or occurs 2 times.
any occurs 1 times.
so occurs 3 times.
can occurs 2 times.
long occurs 2 times.
endure occurs 1 times.
met occurs 1 times.
battlefield occurs 1 times.
of occurs 5 times.
have occurs 5 times.
come occurs 1 times.
dedicate occurs 2 times.
portion occurs 1 times.
field occurs 1 times.
as occurs 1 times.
final occurs 1 times.
resting occurs 1 times.
place occurs 1 times.
for occurs 5 times.
those occurs 1 times.
who occurs 3 times.
here occurs 8 times.
gave occurs 2 times.
their occurs 1 times.
lives occurs 1 times.
might occurs 1 times.
live occurs 1 times.
it occurs 5 times.
is occurs 3 times.
altogether occurs 1 times.
fitting occurs 1 times.
proper occurs 1 times.
should occurs 1 times.
do occurs 1 times.
but occurs 2 times.
larger occurs 1 times.
sense occurs 1 times.
cannot occurs 3 times.
consecrate occurs 1 times.
hallow occurs 1 times.
ground occurs 1 times.
brave occurs 1 times.
living occurs 2 times.
dead occurs 3 times.
struggled occurs 1 times.
consecrated occurs 1 times.
far occurs 2 times.
above occurs 1 times.
poor occurs 1 times.
power occurs 1 times.
add occurs 1 times.
detract occurs 1 times.
world occurs 1 times.
will occurs 1 times.
little occurs 1 times.
note occurs 1 times.
nor occurs 1 times.
remember occurs 1 times.
what occurs 2 times.
say occurs 1 times.
never occurs 1 times.
forget occurs 1 times.
they occurs 3 times.
did occurs 1 times.
us occurs 3 times.
rather occurs 2 times.
be occurs 2 times.
unfinished occurs 1 times.
work occurs 1 times.
which occurs 2 times.
fought occurs 1 times.
thus occurs 1 times.
nobly occurs 1 times.
advanced occurs 1 times.
task occurs 1 times.
remaining occurs 1 times.
before occurs 1 times.
from occurs 2 times.
these occurs 2 times.
honored occurs 1 times.
take occurs 1 times.
increased occurs 1 times.
devotion occurs 2 times.
cause occurs 1 times.
last occurs 1 times.
full occurs 1 times.
measure occurs 1 times.
highly occurs 1 times.
resolve occurs 1 times.
shall occurs 3 times.
not occurs 2 times.
died occurs 1 times.
vain occurs 1 times.
under occurs 1 times.
God occurs 1 times.
birth occurs 1 times.
freedom occurs 1 times.
government occurs 1 times.
people occurs 3 times.
by occurs 1 times.
perish occurs 1 times.
earth occurs 1 times.
*/
