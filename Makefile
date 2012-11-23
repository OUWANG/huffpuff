compile-all: encode decode

.PHONY: decode encode clean

encode: encode.java buildTree.java huffTable.java
	javac encode.java buildTree.java huffTable.java

decode: decode.java huffTable.java
	javac decode.java huffTable.java

clean:
	rm -f *.class