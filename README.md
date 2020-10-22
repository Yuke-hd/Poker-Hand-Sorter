
# Poker-Hand-Sorter
A command line program that takes, via STDIN, a "stream" of hands for a two-player poker game. At the completion of the stream, your program should print to STDOUT the number of hands won by Player 1, and the number of hands won by Player 2.
## Usage
There is a pre-built jar file included or you can build your own from the source code.

On linux: 
``$ cat poker-hands.txt | java -jar Poker-Hand-Sorter.jar``

On Windows: 
``PS > type poker-hands.txt | java -jar Poker-Hand-Sorter.jar``

### Arguments
``debug`` enable debug outputs

Example:
``PS > type poker-hands.txt | java -jar Poker-Hand-Sorter.jar debug``

## Credits
[Reference](https://stackoverflow.com/questions/42380183/algorithm-to-give-a-value-to-a-5-card-poker-hand)

[Theory 1](http://www.mathcs.emory.edu/~cheung/Courses/170/Syllabus/10/pokerCheck.html)

[Theory 2](https://github.com/victorliafook/poker-hand-sorter)
