public class huffTable{
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