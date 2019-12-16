Purpose: Now that we have looked at Merge Sort and Quick Sort (and discussed a couple Quick Sort variations), we'd like to empirically verify what we have discussed about their relative efficiencies. We will do this by timing the algorithms as well as counting the number of comparisons and data movements of each sort in different situations on different size arrays. We will then tabulate our results and compare the algorithms' performances. We hope to see differences in the relationships between the performance metrics and array sizes for the different algorithms, and possibly come to some conclusions about which versions are best in given situations and overall. 

Tested 9 different sorting algorithms:
1) QS1: Simple Quick Sort with A[last] as the pivot (in file Quick.java)
2) QS2: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 5)
3) QS3: Median of 3 Quick Sort as given in TextMergeQuick.java but with base case array size < 20
4) QS4: Median of 3 Quick Sort as given in TextMergeQuick.java, but with base case array size < 100
5) QS5: Random Pivot Quick Sort with base case array size < 5
6) QS6: Iterative Quick Sort developed by converting the recursive Quick Sort into iterative using an explicit stack per the instructions in Notes on converting recursion to iteration Notes on converting recursion to iteration.
7) MS1: Recursive Merge Sort as given in TextMergeQuick.java
8) MS2: Iterative Merge Sort as given in TextMergeQuick.java
9) MS3: Iterative Merge Sort developed by converting the recursive Merge Sort into iterative using an explicit stack per the instructions in Notes on converting recursion to iteration Notes on converting recursion to iteration.


Grade Received: 90% (turned in one day late for 10% reduction)