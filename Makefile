compile-all: decode encode build

decode: decode.java huffTable.java
	javac decode.java huffTable.java

encode: encode.java
	javac encode.java

build: buildTree.java freqChart.java huffTable.java
	javac buildTree.java freqChart.java huffTable.java

clean:
	rm -f *.class