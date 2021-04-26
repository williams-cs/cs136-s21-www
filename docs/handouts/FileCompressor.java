import structure5.*;
import java.io.*;

public class FileCompressor {

  // Read bytes from a file into a byte array
  public static byte[] readFile(String filename) {

    byte[] theBytes = null;

    try {
      InputStream istream = new FileInputStream(filename);
      long byteCount = (new File(filename)).length();
      theBytes = new byte[(int) byteCount];
      istream.read(theBytes);
    }
    catch (FileNotFoundException fnf) {
      System.out.println("File not found.");
      System.exit(0);
    }
    catch (IOException ioe) {
      System.out.println("I/O error on file " + filename);
    }
    return theBytes;
  }

  public static void main(String[] args) {
    if(args.length < 1) usage();

    byte[] bytes = readFile(args[0]);
    int unencodedLength = 8 * bytes.length;
    int encodedLength = 0;

    System.out.println("File length in bits: " + unencodedLength);

    Vector<Byte> vb = new Vector<Byte>(bytes.length);
    for(byte b : bytes) vb.add(b);

    HeapHuffman<Byte> hh = new HeapHuffman<Byte>(vb);
    encodedLength = hh.encodingLength();
    System.out.println("Encoded length in bits : " + encodedLength);
    System.out.println("File Compression Ratio : " +
                        (1.0 * unencodedLength)/encodedLength);

    // Now try using 16-bit bytes as alphabet
    int[] bytePairs = new int[bytes.length/2];
    for(int i = 0; i < bytePairs.length; i++)
      // build 16-bit int from consecutive bytes
      bytePairs[i] = ((int) bytes[2*i]) <<  8 | (int) bytes[2*i+1];

    // Build a vector from the bytePair array
    Vector<Integer> vb2 = new Vector<Integer>(bytePairs.length);
    for(Integer i : bytePairs) vb2.addLast(i);

    // Now run Huffman
    unencodedLength = 16 * bytePairs.length;
    HeapHuffman<Integer> hh2 = new HeapHuffman<Integer>(vb2);
    encodedLength = hh2.encodingLength();
    System.out.println("Encoded length in bits : " + encodedLength);
    System.out.println("File Compression Ratio : " +
                        (1.0 * unencodedLength)/encodedLength);

  }

  public static void usage() {
      System.out.println("Usage: FileCompressor filename");
      System.exit(0);
 }

 // Compare two vectors, value by value, for equality
 public static boolean checkEquality( Vector<Byte> original, Vector<Byte> copy) {

   if(original.size() != copy.size()) return false;
   for(int i = 0; i < original.size(); i++)
     if(!original.get(i).equals(copy.get(i))) return false;
   return true;
 }

}
