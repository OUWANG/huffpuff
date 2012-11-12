import java.util.*;
import java.io.*;

public class main{

	private static ArrayList<freqChart> = new ArrayList<freqChart>();

	public static void main(String args[]) throws Exception{
		Scanner fin = new Scanner(new File("juliuscaesar.txt"));
		
		String text = "";

		while(fin.hasNextLine()){
			text += fin.nextLine();
		}


	}

	public static void frequencyCount(String text){

	}
}

public class freqChart{
	
	private char ch;
	private int count;

	public freqChart(char currChar){
		ch = currChar;
		count = 1;
	}

	public ch getChar(){
		return ch;
	}

	public int getCount(){
		return count;
	}

	public int addCount(){
		++count;
		return count;
	}

	public boolean findChar(ArrayList<freqChart> fc, char currChar){
		for (int i = 0; i < fc.size(); ++i) {
			if (fc.get(i).getChar == currChar) {
				return true;
			}
		}
		return false;
	}
}