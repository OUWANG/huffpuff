compile-all: encode.class decode.class

encode.class: encode.java buildTree.java freqChart.java huffTable.java
	javac encode.java buildTree.java freqChart.java huffTable.java

decode.class: decode.java huffTable.java
	javac decode.java huffTable.java

.PHONY: clean
clean:
	rm -f *.class