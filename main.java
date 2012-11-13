import java.util.*;
import java.io.*;

public class main{

	public static void main(String args[]) throws IOException{
		
		String[] inputFiles = {"juliuscaesar.txt","macbeth.txt","othello.txt"};
		
		for (int i = 0; i < inputFiles.length; ++i) {
			Scanner fin = new Scanner(new File(inputFiles[i]));
			doHuff(fin);	
		}
		// Scanner fin = new Scanner(new File("newlinetest.txt"));
		// doHuff(fin);	
		/** construct Comparator sort for our arrayList
		 	* implements custom Comparator of freqChart
			* compares values of Double probability
			* and then sorts them in ascending order
			* Collections.sort(fc, new freqProbComparator());
			*/

	}
	
	public static void doHuff(Scanner fin) throws IOException{
		ArrayList<freqChart> fc = new ArrayList<freqChart>();
		ArrayList<huffTable> huff = new ArrayList<huffTable>();

		BufferedWriter enout = new BufferedWriter(new FileWriter(new File("encoded.txt")));
		BufferedWriter deout = new BufferedWriter(new FileWriter(new File("decoded.txt")));
		String text = "";

		while(fin.hasNextLine()){
			text += fin.nextLine();
			text += "\n";
		}

		frequencyCount(text, fc);

		for (int i = 0; i < fc.size(); ++i)
			System.out.print(fc.get(i).getChar()+" "+fc.get(i).getCount()+" "+fc.get(i).getProb()+"\n");
		System.out.println(fc.size());

		treeNode root = buildTree(fc);
		
		String huffCode = "";
		
		traverseTree(root, huffCode, huff);
		
		// encode
		int indexOfChar, encodedLength = 0;
		for (int i = 0; i < text.length(); ++i) {
			indexOfChar = findHuffChar(text.charAt(i), huff);
			encodedLength += huff.get(indexOfChar).gethuffCode().length();
			System.out.println(huff.get(indexOfChar).gethuffCode());
			enout.write(huff.get(indexOfChar).gethuffCode()+" ");
		}

		// decode
		Scanner encfin = new Scanner(new File("encoded.txt"));
		ArrayList<String> huffCodeList = new ArrayList<String>();
		while(encfin.hasNext()) {
			huffCodeList.add(encfin.next());
		}
		// System.out.println(huffCodeList.get(0));
		int indexOfCode;
		for (int i = 0; i < huffCodeList.size(); ++i) {
			// System.out.println(i);
			indexOfCode = findHuffChar(huffCodeList.get(i), huff);
			deout.write(huff.get(indexOfCode).getChar());
		}

		double compressionRatio = (double) encodedLength / (text.length()*8);
		System.out.println(compressionRatio);
	}
	
	public static void frequencyCount(String text, ArrayList<freqChart> fc) {
		char currChar;
		int indexOfChar;
		
		// if the character is not already in our frequency table
		// add it and set the frequency count to 1
		// else increase the frequency count for that char
		for (int i = 0; i < text.length(); ++i) {
			currChar = text.charAt(i);
			indexOfChar = findChar(currChar, fc);
			if (indexOfChar != -1)
				fc.get(indexOfChar).addCount();
			else
				fc.add(new freqChart(currChar));
		}

		// after we find the count of all the characters in our input set
		// set the probability/freq of all characters
		for (int i = 0; i < fc.size(); ++i) {
			fc.get(i).setProb(text.length());
		}
	}

	public static int findChar(char currChar, ArrayList<freqChart> fc) {
		// searches for currChar in all instances of freqChart
		// if found returns index of currChar in ArrayList fc
		// else returns -1

		// System.out.print(fc.size()+":");
		for (int i = 0; i < fc.size(); ++i) {
			// System.out.print(fc.get(i).getChar()+"*");
			if (fc.get(i).getChar() == currChar) {
				// System.out.println("\nfound "+currChar+" in text at "+i);
				return i;
			}
		}
		// System.out.println("\n"+currChar+" not found returning -1");
		return -1;
	}

	public static int findHuffChar(char currChar, ArrayList<huffTable> huff) {
		for (int i = 0; i < huff.size(); ++i) {
			if (huff.get(i).getChar() == currChar) {
				return i;
			}
		}
		return -1;
	}

	public static int findHuffChar(String huffCode, ArrayList<huffTable> huff) {
		for (int i = 0; i < huff.size(); ++i) {
			// if (huff.get(i).gethuffCode() == huffCode) {
			// 	return i;
			// }
			if (huffCode.equals(huff.get(i).gethuffCode())) {
				return i;
			}
		}
		System.out.println("did not find "+huffCode+" in huffTable, returning -1");
		return -1;
	}

	public static treeNode buildTree(ArrayList<freqChart> fc) {
		PriorityQueue<treeNode> pq = new PriorityQueue<treeNode>(fc.size(), new pqProbComparator());

		for (int i = 0; i < fc.size(); ++i) {
			treeNode tn = new treeNode(fc.get(i).getChar(), fc.get(i).getProb(), null, null);
			pq.offer(tn);
		}
		while	(pq.size() > 1){
			treeNode temp1 = pq.poll();
			treeNode temp2 = pq.poll();
			treeNode tn = new treeNode('\0', temp1.getProb() + temp2.getProb(), temp1, temp2);
			pq.offer(tn);
		}

		return pq.poll();
	}

	public static void traverseTree(treeNode node, String huffCode, ArrayList<huffTable> huff) {
		if (node == null)
			return;

		if (node.getChar() != '\0') { // || if(node.getLeft() == null && node.getRight() == null)
			// System.out.println(node.getChar()+" "+huffCode);
			huff.add(new huffTable(node.getChar(), huffCode));
		}
		// System.out.println(node.getChar()+" "+node.getProb());
		traverseTree(node.getLeft(), huffCode+"0", huff);
		traverseTree(node.getRight(), huffCode+"1", huff);
	}
}

class freqChart{	
	private char ch;
	private int count;
	private double probability;

	public freqChart(char ch) {
		this.ch = ch;
		this.count = 1;
		this.probability = 0.0;
	}

	public char getChar() {
		return ch;
	}

	public int getCount() {
		return count;
	}

	// this get method must be object type Double
	// in order to implement custom comparator
	// because we cannot use primitive types 
	// that is, double has no such method compareTo(), etc.
	public Double getProb() {
		return probability;
	}

	public void setProb(int size) {
		probability = (double) count / size;
		// System.out.println("prob: "+((double)count/size)+" = "+count+"/"+size);
	}

	public void addCount() {
		++count;
	}
}

// class freqProbComparator implements Comparator<freqChart> {
// 	public int compare(freqChart fc1, freqChart fc2) {
// 		return fc1.getProb().compareTo(fc2.getProb());
// 	}
// }

class pqProbComparator implements Comparator<treeNode> {
	public int compare(treeNode tn1, treeNode tn2) {
		return tn1.getProb().compareTo(tn2.getProb());
	}
}

class treeNode{
	private char ch;
	private double probability;
	private treeNode left;
	private treeNode right;
	private String huffCode;

	public treeNode(char ch, double probability, treeNode left, treeNode right){
		this.ch = ch;
		this.probability = probability ;
		this.left = left;
		this.right = right;
		this.huffCode = null;
	}

	public char getChar() {
		return this.ch;
	}

	public Double getProb() {
		return this.probability;
	}

	public treeNode getLeft() {
		return this.left;
	}

	public treeNode getRight() {
		return this.right;
	}

	public void sethuffCode(String huffCode) {
		this.huffCode = huffCode;
	}
}

class huffTable{
	private char ch;
	private String huffCode;

	public huffTable(char ch, String huffCode){
		this.ch = ch;
		this.huffCode = huffCode;
	}

	public char getChar() {
		return this.ch;
	}

	public String gethuffCode() {
		return this.huffCode;
	}
}