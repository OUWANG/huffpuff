import java.util.*;
import java.io.*;

public class encode {

	public static void encode(String text, ArrayList<huffTable> huff) throws IOException {
		BufferedWriter enout = new BufferedWriter(new FileWriter(new File("encoded.txt")));
		BufferedWriter treeout = new BufferedWriter(new	FileWriter(new File("tree.txt")));

		int indexOfChar, encodedLength = 0;
		for (int i = 0; i < text.length(); ++i) {
			indexOfChar = findHuffChar(text.charAt(i), huff);
			encodedLength += huff.get(indexOfChar).gethuffCode().length();
			// System.out.println(huff.get(indexOfChar).gethuffCode());
			enout.write(huff.get(indexOfChar).gethuffCode()+" ");
		}
		enout.close();

		for (huffTable currHuff : huff) {
			// encodedLength += h.gethuffCode().length() + 1 + 1;
			// System.out.println(currHuff.gethuffCode()+" "+currHuff.getChar());
			if (currHuff.getChar() == '\n') {
				System.out.println("hiiiiii");
				treeout.write("\\n" +" "+currHuff.gethuffCode()+"\n");
				continue;
			}
			if (currHuff.getChar() == ' ') {
				System.out.println("spaaace");
				treeout.write("\\s"+" "+currHuff.gethuffCode()+"\n");
				continue;
			}
			treeout.write(currHuff.getChar()+" "+currHuff.gethuffCode()+"\n");
		}
		treeout.close();

		double compressionRatio = (double) encodedLength / (text.length()*8);
		System.out.println(compressionRatio);
	}

	public static int findHuffChar(char currChar, ArrayList<huffTable> huff) {
		for (int i = 0; i < huff.size(); ++i) {
			if (huff.get(i).getChar() == currChar) {
				return i;
			}
		}
		return -1;
	}


}