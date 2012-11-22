import java.util.*;
import java.io.*;

public class main{

	public static void main(String args[]) throws IOException{
		
		// String[] inputFiles = {"juliuscaesar.txt","macbeth.txt","othello.txt"};
		
		// for (int i = 0; i < inputFiles.length; ++i) {
		// 	Scanner fin = new Scanner(new File(inputFiles[i]));
		// 	doHuff(fin);
		// 	fin.close();
		// 	break;
		// }

		buildTree.build(args[0]);
		
		/** ** PROGRAM DESCRIPTION **
			* from the input file in the argument 
			* generate frequency table including 
			* number of occurences of each character
			* percent probability of occurence
			* 
			* build our huffman tree for the given
			* frequency table where the deepest node
			* is a character with the lowest probability of occurence
			* 
			* traverse the tree and add the binary huffman code for each leaf node
			* 0 represents a traversal left and 1 is right
			* 
			* put all these huffman codes in an arraylist for each character
			* and go through each character in the input file
			* writing the huffman code for that character to an output file
			* 
			* then undo this for the decoding, and replace each huffman code
			* with the original character representation, regenerating the original input file
			*/

		/** construct Comparator sort for our arrayList
		 	* implements custom Comparator of freqChart
			* compares values of Double probability
			* and then sorts them in ascending order
			*/ 
		// Collections.sort(fc, new freqProbComparator());
			

	}

	
}