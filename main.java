import java.util.*;
import java.io.*;

public class main{

	private static ArrayList<freqChart> fc = new ArrayList<freqChart>();

	public static void main(String args[]) throws Exception{
		Scanner fin = new Scanner(new File("juliuscaesar.txt"));
		
		String text = "";

		while(fin.hasNextLine())
			text += fin.nextLine();
		
		// System.out.println(text);
		
		frequencyCount(text);

		// construct Comparator sort for our arrayList
		// implements custom Comparator of freqChart
		// compares values of Double probability
		// and then sorts them in ascending order
		Collections.sort(fc, new freqProbComparator());

		for (int i = 0; i < fc.size(); ++i) {
			System.out.print(fc.get(i).getChar()+" "+fc.get(i).getCount()+" "+fc.get(i).getProb()+"\n");
		}

		buildTree();
	}

	public static void frequencyCount(String text) {
		char currChar;
		int indexOfChar;
		
		// if the character is not already in our frequency table
		// add it and set the frequency count to 1
		// else increase the frequency count for that char
		for (int i = 0; i < text.length(); ++i) {
			currChar = text.charAt(i);
			indexOfChar = findChar(currChar);
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

	public static int findChar(char currChar) {
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

	public static void buildTree() {

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

class freqProbComparator implements Comparator<freqChart> {
	public int compare(freqChart fc1, freqChart fc2) {
		return fc1.getProb().compareTo(fc2.getProb());
	}
}

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

	public treeNode(char ch, double probability, treeNode left, treeNode right){
		this.ch = ch;
		this.probability = probability;
		this.left = left;
		this.right = right;
	}

	public char getChar() {
		return this.ch;
	}

	public Double getProb() {
		return this.probability;
	}
}