public class freqChart {
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