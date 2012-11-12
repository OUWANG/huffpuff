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

		for (int i = 0; i < fc.size(); ++i) {
			System.out.print(fc.get(i).getChar()+" "+fc.get(i).getCount()+" "+fc.get(i).getProb()+"\n");
		}
	}

	public static void frequencyCount(String text) {
		char currChar;
		int indexOfChar;
		
		// if the character is not already in our frequency table
		// add it and set the frequency count to 1
		// else increase the frequency count for that char
		for (int i = 0; i < text.length(); ++i) {
			currChar = text.charAt(i);
			indexOfChar = findChar(fc, currChar);
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

	public static int findChar(ArrayList<freqChart> fc, char currChar) {
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
}

class freqChart{
	
	private char ch;
	private int count;
	private double probability;

	public freqChart(char currChar) {
		ch = currChar;
		count = 1;
		probability = 0.0;
	}

	public char getChar() {
		return ch;
	}

	public int getCount() {
		return count;
	}

	public double getProb() {
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