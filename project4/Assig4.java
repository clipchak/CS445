import java.util.*;
import java.io.*;



// **************** //iterative quicksort use Text Merge Quick pivot
  //random quicksort use QUick.java pivot*************************************************************

public class Assig4{
  
  public static void main(String[] args){
    int arraySize;
    int trials;
    Integer[] dataSorted;
    Integer[] dataReverseSorted;
    String outputFileName;
    
    //getting the size of the arrays and initializing them
    System.out.println("What size arrays should be used?");
    arraySize = getInt("   It should be an integer value greater than or equal to 1.");
    //dataRandom = generateRandomArray(arraySize);
    dataSorted = new Integer[arraySize];
    dataReverseSorted = new Integer[arraySize];
    for(int i = 0; i<arraySize;i++){
      int highToLow = arraySize-1-i;
      dataReverseSorted[i] = highToLow;
      dataSorted[i] = i;
    }
    
    //printing the array
    //System.out.println("Your data: " + getString(dataRandom));
    
    //getting the amount of trials
    System.out.println("How many trials?");
    trials = getInt("   It should be an integer value greater than or equal to 1.");
    
    //getting the file name
    System.out.println("Enter the name of the file to output results to (ex: test25k.txt):");
    Scanner output = new Scanner(System.in);
    outputFileName = output.next();
    
    System.out.println("array size: " + arraySize + "\ntrials: " + trials + "\noutputFileName: " + outputFileName);

    //printing to file
    try{
      FileOutputStream fout = new FileOutputStream(outputFileName);
      fout.close();
      System.out.println("success...");    
    }
    catch(Exception e){
      System.out.println(e);
    }
    
    //initializing random data to be used in each sorting algorithm
     Integer[][] randomHolders =  new Integer[trials][arraySize];
     for(int i = 0; i<trials;i++){
       randomHolders[i] = generateRandomArray(arraySize);
     }
     
     //initializing sorted data to be used in sorting methods
     Integer[][] dataSortedHolders = new Integer[trials][arraySize];
     for(int i = 0; i<trials;i++){
       dataSortedHolders[i] = Arrays.copyOf(dataSorted,dataSorted.length);
     }
     
     //initializing reverse sorted data to be used in sorting methods     
     Integer[][] dataReverseSortedHolders = new Integer[trials][arraySize];
     for(int i = 0; i<trials;i++){
       dataReverseSortedHolders[i] = Arrays.copyOf(dataReverseSorted,dataReverseSorted.length);
     }
     
     if(arraySize<=100000){
     //RANDOM QS1: Simple Quick Sort with A[last] as the pivot
     runSimpleQuickSort(outputFileName,"Random",randomHolders,trials);
     
     //SORTED QS1: Simple Quick Sort with A[last] as the pivot
     runSimpleQuickSort(outputFileName,"Sorted",dataSortedHolders,trials);
     
     //REVERSE SORTED QS1: Simple Quick Sort with A[last] as the pivot
     runSimpleQuickSort(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials);
     }
     //RANDOM QS2: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 5) 
     runMedianOf3QuickSort(outputFileName,"Random",randomHolders,trials,5);
     
     //SORTED QS2: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 5) 
     runMedianOf3QuickSort(outputFileName,"Sorted",dataSortedHolders,trials,5);
     
     //REVERSE SORTED QS2: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 5) 
     runMedianOf3QuickSort(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials,5);
     
     //RANDOM QS3: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 20) 
     runMedianOf3QuickSort(outputFileName,"Random",randomHolders,trials,20);
     
     //SORTED QS3: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 20) 
     runMedianOf3QuickSort(outputFileName,"Sorted",dataSortedHolders,trials,20);
     
     //REVERSE SORTED QS3: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 20) 
     runMedianOf3QuickSort(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials,20);
     
     //RANDOM QS4: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 100) 
     runMedianOf3QuickSort(outputFileName,"Random",randomHolders,trials,100);
     
     //SORTED QS4: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 100) 
     runMedianOf3QuickSort(outputFileName,"Sorted",dataSortedHolders,trials,100);
     
     //REVERSE SORTED QS4: Median of 3 Quick Sort as given in TextMergeQuick.java (base case array size < 100) 
     runMedianOf3QuickSort(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials,100);
     
     //RANDOM QS5: Random pivot quick sort
     runRandomPivotQuickSort(outputFileName, "Random", randomHolders,trials);
     
     //SORTED QS5: Random pivot quick sort
     runRandomPivotQuickSort(outputFileName, "Sorted", dataSortedHolders,trials);
     
     //REVERSE SORTED QS5: Random pivot quick sort
     runRandomPivotQuickSort(outputFileName, "Reverse Sorted", dataReverseSortedHolders,trials);
     
     //RANDOM QS6: Iterative quick sort w/stack
     runQuickSortIterative(outputFileName,"Random",randomHolders,trials);
     
     //SORTED QS6: Iterative quick sort w/stack
     runQuickSortIterative(outputFileName,"Sorted",dataSortedHolders,trials);
     
     //REVERSE SORTED QS6: Iterative quick sort w/stack
     runQuickSortIterative(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials);
     
     //RANDOM MS7: Recursive merge sort
     runRecursiveMergeSort(outputFileName,"Random",randomHolders,trials);
     
     //SORTED MS7: Recursive merge sort
     runRecursiveMergeSort(outputFileName,"Sorted",dataSortedHolders,trials);
     
     //REVERSE SORTED MS7: Recursive merge sort
     runRecursiveMergeSort(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials);
     
     //RANDOM MS8: Iterative merge sort
     runIterativeMergeSort(outputFileName,"Random",randomHolders,trials);
     
     //SORTED MS8: Iterative merge sort
     runIterativeMergeSort(outputFileName,"Sorted",dataSortedHolders,trials);
     
     //REVERSE SORTED MS8: Iterative merge sort
     runIterativeMergeSort(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials);
     
     //RANDOM MS9: Iterative merge sort w/stack
     runIterativeMergeSortStack(outputFileName,"Random",randomHolders,trials);
     
     //SORTED MS9: Iterative merge sort w/stack
     runIterativeMergeSortStack(outputFileName,"Sorted",dataSortedHolders,trials);
     
     //REVERSE SORTED MS9: Iterative merge sort w/stack
     runIterativeMergeSortStack(outputFileName,"Reverse Sorted",dataReverseSortedHolders,trials);
     
     
  }
  
  public static void runIterativeMergeSortStack(String outputFileName, String order, Integer[][] data, int trials){
    //printing to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm: Iterative Merge Sort with the Stack");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    
    //sorting and tracking time
    TextMergeQuick qs1 = new TextMergeQuick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Iterative MS w/ stack trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.mergeSortIterativeStack(arrayHolder,arrayHolder.length);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during " +order+ " Iterative MS w/stack trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
    if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  public static void runQuickSortIterative(String outputFileName, String order, Integer[][] data, int trials){
    //printing to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm: Iterative Quick Sort with the Stack");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    
    //sorting and tracking time
    TextMergeQuick qs1 = new TextMergeQuick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Iterative QS w/ stack trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.quickSortIterative(arrayHolder,0,data[i].length-1,5);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during " +order+ " Iterative QS w/stack trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
    if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  public static void runIterativeMergeSort(String outputFileName, String order, Integer[][] data, int trials){
    //printing to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm: Iterative Merge Sort");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    
    //sorting and tracking time
    TextMergeQuick qs1 = new TextMergeQuick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Iterative merge sort trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.iterativeMergeSort(arrayHolder,data[i].length);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
    if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  public static void runRecursiveMergeSort(String outputFileName, String order, Integer[][] data, int trials){
    //printing to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm: Recursive Merge Sort");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    
    //sorting and tracking time
    TextMergeQuick qs1 = new TextMergeQuick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Recursive merge sort trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.mergeSort(arrayHolder,data[i].length);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
    if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  public static void runRandomPivotQuickSort(String outputFileName, String order, Integer[][] data, int trials){
    //printing to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm: Random Pivot Quick Sort");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    
    //sorting and tracking time
    Quick qs1 = new Quick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Random pivot QS trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.quickSortRandom(arrayHolder,data[i].length);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
    if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  
  public static void runMedianOf3QuickSort(String outputFileName,String order, Integer[][] data, int trials, int baseCaseSize){
      try(FileWriter fw = new FileWriter(outputFileName, true);
          BufferedWriter bw = new BufferedWriter(fw);
          PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm:  Median of 3 Quick Sort (base case array size < " + baseCaseSize + ")");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    //sorting and tracking time
    TextMergeQuick qs1 = new TextMergeQuick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Median of 3 QS trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.quickSort(arrayHolder,arrayHolder.length,baseCaseSize);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
   if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  //simple quick sort method
  public static void runSimpleQuickSort(String outputFileName,String order, Integer[][] data, int trials){
    //printing to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Algorithm: Simple Quick Sort");
      out.println("Array Size: " +data[0].length);
      out.println("Order: " +order);
      out.println("Number of trials: " +trials);
    } catch (IOException e) {
      System.out.println(e);
    }
    
    //sorting and tracking time
    Quick qs1 = new Quick();
    long sortTime = 0;
    for(int i = 0; i<trials;i++){
      if(data[i].length<=20) System.out.println("Data before sort during " +order+ " Simple Quick sort trial " + (i+1) +":" + getString(data[i]));
      long start = System.nanoTime();
      Integer[] arrayHolder = Arrays.copyOf(data[i],data[i].length);
      qs1.quickSort(arrayHolder,data[i].length);
      long finish = System.nanoTime();
      sortTime += finish - start;
      if(data[i].length<=20) System.out.println("Data after sort during trial " + (i+1) +":" + getString(arrayHolder) + "\n");
    }
    
    //calculating final results and printing to console
    sortTime = sortTime/trials;
    double finalSortTime = (double)sortTime/1000000000;
    double finalComparisons = (double)qs1.getTotalComparisons()/trials;
    double finalDataMoves = (double)qs1.getTotalDataMoves()/trials;
    if(data[0].length<=20){
    System.out.println("Average comparisons made: " + finalComparisons + " comparisons");
    System.out.println("Average data moves made: " + finalDataMoves + " data moves");
    System.out.println("Average sort time: " + finalSortTime + " seconds");
    }
    //printing final results to file
    try(FileWriter fw = new FileWriter(outputFileName, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw))
    {
      out.println("Average Comparisons: " + finalComparisons + " comparisons");
      out.println("Average data moves made: " + finalDataMoves + " data moves");
      out.println("Average sort time: " + finalSortTime + " seconds");
      out.println();
    } catch (IOException e) {
      System.out.println(e);
    }
  }
  public static void writeToFile(Integer[] data, String fileName, String sortType, String order,
             int trials, double time, double comparisons, double dataMoves){
    
  }
  
  private static String getString(Object [] data)
  {
    String result = new String("[ ");
    
    for(int i = 0; i< data.length; i++)
    {
      result = result + data[i].toString() + " ";
    }
    
    result = result + "]";
    
    return result;
  }
  
  private static Integer[] generateRandomArray(int size)
  {
    Integer result[] = new Integer[size];
    Random generator = new Random();
    
    for(int i = 0; i< size; i++)
    {
      int value = generator.nextInt(size);
      result[i] = new Integer(value);
    }
    
    return result;
  }
  
  private static int getInt(String rangePrompt)
  {
    Scanner input;
    int result = 10;        //default value is 10
    try
    {
      input = new Scanner(System.in);
      System.out.println(rangePrompt);
      result = input.nextInt();
      
    }
    catch(NumberFormatException e)
    {
      System.out.println("Could not convert input to an integer");
      System.out.println(e.getMessage());
      System.out.println("Will use 10 as the default value");
    }        
    catch(Exception e)
    {
      System.out.println("There was an error with System.in");
      System.out.println(e.getMessage());
      System.out.println("Will use 10 as the default value");
    }
    return result;
    
  }
}