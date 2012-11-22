import java.util.*;

public class decode {

	public static void decode() {

	}

	public static int findHuffChar(String huffCode, ArrayList<huffTable> huff) {
		for (int i = 0; i < huff.size(); ++i) {
			if (huffCode.equals(huff.get(i).gethuffCode())) {
				return i;
			}
		}
		// System.out.println("did not find "+huffCode+" in huffTable, returning -1");
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