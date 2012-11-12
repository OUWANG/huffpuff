compile-all: main encode decode

main: main.java
	javac main.java

decode: decode.java
	javac decode.java

encode: encode.java
	javac encode.java

clean:
	rm -f *.class