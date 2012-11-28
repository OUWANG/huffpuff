# Huffman Coding

```no-highlight
O Romeo, Romeo! wherefore art thou Romeo?
Deny thy father and refuse thy name;
Or, if thou wilt not, be but sworn my love,
And I'll no longer be a Capulet.
```
## Introduction:
This assignment tasked us with implementing a Huffman coding tree structure for lossless file compression. 

## Usage:
Unzip the contents of the project, run the Makefile, and execute either of the included bash scripts: `encode` or `decode`. 
`encode` requires a file name as an argument for the input.

## Example:
```no-highlight
$ make
javac encode.java buildTree.java huffTable.java
javac decode.java huffTable.java
$ ./encode juliuscaesar.txt 
0.608413526701003
$ ./decode
$ diff juliuscaesar.txt decoded.txt
```

## Data Structures
### Frequency Chart
	#### Contains:
		* char ch
		* int count
		* double probability

	#### Description:
		The frequency chart stores the character, its number of occurences in the input file and the percentage probability that it occurs in that file.

### Huffman Coding Tree
	#### Contains:
		* char ch
		* double probability
		* treeNode left, right
		* String huffCode

	#### Description:
		Leaf nodes in the huffman tree store the character, its respective probability and the huffman code that represents the traversal from the root node to that leaf.
		Every other node contains a null character but the sum of the probabilities of its children, and a left and right node.

### Huffman Code Table
	#### Contains:
		* char ch
		* String huffCode

	#### Description:
		Contains the character and its respective huffman binary code. This is also how we store the tree to rebuild in our decode function.
		##### Excerpt example of stored tree:
		```no-highlight
		n 0000
		s 0001
		c 001000
		O 00100100
		P 001001010
		x 0010010110
		& 00100101110000
		```
		
## Algorithm Analysis
### ENCODING	
	#### build freq chart - O(n log n)
		As we go through the file character by character, we search for that character in our current frequency chart and if not found, we add a new character to the freqChart with count 1, otherwise we increment the count for that character
	#### build huffman tree - O(log n)
		Using our frequency chart, we create a node for each character and place it into our priority queue. The tree is then built from this single queue in log n time.
	#### encode file - O(n log n)
		For each character in the input file we search for it in the frequency chart and then get its respective huffman code and write it out to our encoded.txt file.

### DECODING
	#### rebuilding tree - O(num unique characters in input file)
		For each unique character in our input file we rebuild our huffTable data structure to enable us to decode the file.
	#### reproducing original file - O(n log n)
		For each huffman code in the encoded.txt, we search for that code in our huffTable and write the respective character to our decoded.txt, reproducing the original input file in a lossless compression fashion.

## Compression Ratios
<table>
<tr>
<td>File</td><td>Ratio</td><td>Percent size of original file</td></tr>
<tr>
<td>juliuscaesar.txt</td><td>0.608413526701003</td><td>60.84%</td></tr>
<tr>
<td>othello.txt</td><td>0.6089420514033327</td><td>60.89%</td></tr>
<tr>
<td>hamlet.txt</td><td>0.6118503568279284</td><td>61.18%</td></tr>
<tr>
<td>macbeth.txt</td><td>0.6096081779801718</td><td>60.96%</td></tr>
</table>

## Credits
#### This is our team: ####
* Vivian Santos
* Salem Jean
* Robert Daniel
* James Cary