/**
 * This is the completed implementation for Assignment 2: the ReallyLongHex class.
 * @author  Colton Lipchak
 * @version 1.0
 * @since   2018-02-19 
 */
public class ReallyLongHex  extends LinkedDS<Character> 
       implements Comparable<ReallyLongHex>
{
 // Instance variables are inherited.  You may not add any new instance variables
 
 // Default constructor
 private ReallyLongHex()
 {
  super();
 }

 // Note that we are adding the digits here in the END. This results in the 
    // MOST significant digit first in the chain.  
    // It is assumed that String s is a valid representation of an
 // unsigned integer with no leading zeros.
 public ReallyLongHex(String s)
 {
  super();
  char c;
  // Iterate through the String, getting each character and adding it 
        // at the end of the list.  
  for (int i = 0; i < s.length(); i++)
  {
   c = s.charAt(i);
   if ((('0' <= c) && (c <= '9')) || (('A' <= c) && (c <= 'F')))
   {
    this.addItem(c);
   }
   else throw new NumberFormatException("Illegal digit " + c);
  }
 }

 // Simple call to super to copy the nodes from the argument ReallyLongHex
 // into a new one.
 public ReallyLongHex(ReallyLongHex rightOp)
 {
  super(rightOp);
 }
 
 // Method to put digits of number into a String.  We traverse the chain 
    // to add the digits to a StringBuilder. 
 public String toString()
 {
  StringBuilder sb = new StringBuilder();
  if (numOfEntries > 0)
  {
   sb.append("0x");
   for (Node curr = firstNode; curr != null; 
     curr = curr.getNextNode())
   {
    sb.append(curr.getData());
   }
  }
  return sb.toString();
 }

 // You must implement the methods below.  See the descriptions in the
 // assignment sheet

 public ReallyLongHex add(ReallyLongHex rightOp)
 {
   //initializing the return variable
   ReallyLongHex returnHex = new ReallyLongHex("");
   
   //reversing the nodes in order to get the right addends
   rightOp.reverse();
   this.reverse();
   
   //initializing nodes used to traverse the two linked chains
   Node current = firstNode;
   Node rightOpCurrent = rightOp.firstNode;

   //initializing variables used in the loop
   int addend1 = 0;
   int addend2 = 0;
   int carryOver = 0;
   int sum = 0;
   char sumChar = ' ';
   
   while(current!=null && rightOpCurrent!=null){
     
     //retrieving the two numbers to be added together and put into returnHex
     addend1 = Character.getNumericValue(current.getData());
     addend2 = Character.getNumericValue(rightOpCurrent.getData());
     
     //sum to be put into return hex
     sum = addend1 + addend2 + carryOver;
     
     //chooses which character to be added to returnHex based on the integer sum
     switch(sum){
       case 15: 
         sumChar = 'F';
         break;
       case 14: 
         sumChar = 'E';
         break;
       case 13: 
         sumChar = 'D';
         break;
       case 12: 
         sumChar = 'C';
         break;
       case 11: 
         sumChar = 'B';
         break;
       case 10: 
         sumChar = 'A';
         break;
       case 9: 
         sumChar = '9';
         break;
       case 8: 
         sumChar = '8';
         break;
       case 7: 
         sumChar = '7';
         break;
       case 6: 
         sumChar = '6';
         break;
       case 5: 
         sumChar = '5';
         break;
       case 4: 
         sumChar = '4';
         break;
       case 3:
         sumChar = '3';
         break;
       case 2: 
         sumChar = '2';
         break;
       case 1: 
         sumChar = '1';
         break;
       case 0: 
         sumChar = '0';
         break;
         
         //carries over the additional addends to the next spot if the original sum is over 15.
         //the character of the sum must then be "1"
       default: 
         carryOver = sum-15;
         sumChar = '1';
         break;
     }
     
     //adding the final sum to the returnHex
     returnHex.addItem(sumChar);
     
     //traversing to next node
     current = current.getNextNode();
     rightOpCurrent = rightOpCurrent.getNextNode();
   }
   
   //adding zeros to the end of returnHex if it still is not greater than the two addends
   while(this.compareTo(returnHex)>0 || rightOp.compareTo(returnHex)>0){
     returnHex.addItem('0'); 
   }
   
   rightOp.reverse();
   this.reverse();
   return returnHex;
 }
 
 public ReallyLongHex subtract(ReallyLongHex rightOp)
 { 
   if(rightOp.compareTo(this)>0) {
     throw new ArithmeticException("Invalid Difference -- Negative Number");
   }
   
   //initializing the return variable
   ReallyLongHex returnHex = new ReallyLongHex("");
   
   //reversing the nodes in order to get the right addends
   rightOp.reverse();
   this.reverse();
   for(int i = 0;i<numOfEntries-rightOp.numOfEntries;i++){
     rightOp.addItem('0');
   }
   //initializing nodes used to traverse the two linked chains
   Node current = firstNode;
   Node rightOpCurrent = rightOp.firstNode;
   
   //initializing variables used in loop
   int minuend = 0;
   int subtrahend = 0;
   int carryOver = 0;
   int finalAddend = 0;
   char finalAddendChar = ' ';
   
   while(rightOpCurrent!=null){
     //retrieving the two numbers to be added together and put into returnHex
     minuend = Character.getNumericValue(current.getData());
     subtrahend = Character.getNumericValue(rightOpCurrent.getData());
     if(minuend<subtrahend){
       finalAddend = (carryOver + minuend+16)-subtrahend;
       carryOver = -1;
     }
     else{
       finalAddend = (carryOver+minuend)-subtrahend;
       carryOver = 0;
     }
     //final addend to be put into return hex
     
     char[] charArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
     switch(finalAddend){
       case 15: 
         finalAddendChar = 'F';
         returnHex.addItem(finalAddendChar);
         break;
       case 14: 
         finalAddendChar = 'E';
         returnHex.addItem(finalAddendChar);
         break;
       case 13: 
         finalAddendChar = 'D';
         returnHex.addItem(finalAddendChar);
         break;
       case 12: 
         finalAddendChar = 'C';
         returnHex.addItem(finalAddendChar);
         break;
       case 11: 
         finalAddendChar = 'B';
         returnHex.addItem(finalAddendChar);
         break;
       case 10: 
         finalAddendChar = 'A';
         returnHex.addItem(finalAddendChar);
         break;
       case 9: 
         finalAddendChar = '9';
         returnHex.addItem(finalAddendChar);
         break;
       case 8: 
         finalAddendChar = '8';
         returnHex.addItem(finalAddendChar);
         break;
       case 7: 
         finalAddendChar = '7';
         returnHex.addItem(finalAddendChar);
         break;
       case 6: 
         finalAddendChar = '6';
         returnHex.addItem(finalAddendChar);
         break;
       case 5: 
         finalAddendChar = '5';
         returnHex.addItem(finalAddendChar);
         break;
       case 4: 
         finalAddendChar = '4';
         returnHex.addItem(finalAddendChar);
         break;
       case 3:
         finalAddendChar = '3';
         returnHex.addItem(finalAddendChar);
         break;
       case 2: 
         finalAddendChar = '2';
         returnHex.addItem(finalAddendChar);
         break;
       case 1: 
         finalAddendChar = '1';
         returnHex.addItem(finalAddendChar);
         break;
       case 0: 
         //finalAddendChar = '0';
         break;
         
       default: 
         //carryOver = finalAddend-15;
         finalAddendChar = '1';
         returnHex.addItem(finalAddendChar);
         break;
     }
      //adding the final addend to the returnHex
     
     
     //traversing to next node
     current = current.getNextNode();
     rightOpCurrent = rightOpCurrent.getNextNode();
   }
   
   this.reverse();
   rightOp.reverse();
   returnHex.reverse();
   return returnHex;
 }

 public int compareTo(ReallyLongHex rOp)
 {
   //initializing nodes to traverse the linked chains
   Node current = firstNode;
   Node rOpCurrent = rOp.firstNode;
   
   //will return equality if no greater or lesser hex was found
   int returnVal = 0;
   
   //if one hex has greater or lesser digits than the other, will return smaller or larger
   if(numOfEntries > rOp.numOfEntries) returnVal = 1;
   if(numOfEntries < rOp.numOfEntries) returnVal = -1;
   
   //if their entries are equal amounts, loops through and finds the greater hex
   if(numOfEntries == rOp.numOfEntries){
     while(current!=null && rOpCurrent!=null){
       if(Character.getNumericValue(current.getData())>Character.getNumericValue(rOpCurrent.getData())){
         returnVal = 1;
         break;
       }
       if(Character.getNumericValue(current.getData())<Character.getNumericValue(rOpCurrent.getData())){
         returnVal = -1;
         break;
       }
       current = current.getNextNode();
       rOpCurrent = rOpCurrent.getNextNode();
     }
   }
   return returnVal;
 }
 
 public boolean equals(Object rightOp)
 {
   return (this.compareTo((ReallyLongHex)rightOp) == 0);
 }

 public void mult16ToThe(int num)
 {
   for(int i = 0; i<num; i++){
     this.addItem('0');
   }
 }

 public void div16ToThe(int num)
 {
   rightShift(num);
 }
}
