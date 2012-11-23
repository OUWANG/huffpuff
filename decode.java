import java.util.*;
import java.io.*;

public class decode {

	public static ArrayList<huffTable> huff = new ArrayList<huffTable>();

	public static void main(String[] args) throws IOException, FileNotFoundException {
		ArrayList<String> huffCodeList = new ArrayList<String>();

		Scanner encin = null;
		Scanner treein = null;

		BufferedWriter deout = null;

		try {
			encin = new Scanner(new File("encoded.txt"));
			treein = new Scanner(new File("tree.txt"));
			deout = new BufferedWriter(new FileWriter(new File("decoded.txt")));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}

		while(encin.hasNext()) {
			huffCodeList.add(encin.next());
		}
		encin.close();

		while(treein.hasNextLine()) {
			String[] currLine = treein.nextLine().split(" ");
			if (currLine[0].equals("\\n")) {
				huff.add(new huffTable('\n', currLine[1]));
				continue;
			}
			if(currLine[0].equals("\\s")) {
				huff.add(new huffTable(' ', currLine[1]));
				continue;
			}
			System.out.println(currLine[0]+" "+currLine[1]);
			huff.add(new huffTable(currLine[0].charAt(0), currLine[1]));
			// huff.add(new huffTable(treein.next().charAt(0), treein.next()));
		}
		treein.close();

		int indexOfCode;
		for (String huffCode : huffCodeList) {
			indexOfCode = findHuffChar(huffCode);
			deout.write(huff.get(indexOfCode).getChar());
		}
		deout.close();
	}

	public static int findHuffChar(String huffCode) {
		for (int i = 0; i < huff.size(); ++i) {
			if (huffCode.equals(huff.get(i).gethuffCode())) {
				return i;
			}
		}
		System.out.println("did not find "+huffCode+" in huffTable, returning -1");
		return -1;
	}
}


/*
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
deout.close();
*/