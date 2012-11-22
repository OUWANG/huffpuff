public class treeNode {

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