import java.util.*;
/**
 * This is the code for Assignment 1: the MultiDS class used within the Snap game.
 * @author  Colton Lipchak
 * @version 1.0
 * @since   2018-01-29 
 */
public class MultiDS<T> implements PrimQ<T>, Reorder{
  
  private final T[] items;
  private int numberOfEntries;
  private static final int DEFAULT_CAPACITY = 1;
  private boolean initialized = false;
  private static final int MAX_CAPACITY = 52;
  
  public MultiDS(){
    this(DEFAULT_CAPACITY);
  }
  
  @SuppressWarnings("unchecked")
  public MultiDS(int capacity)
  {
    if(capacity>MAX_CAPACITY){
      throw new IllegalArgumentException("Maximum capacity of items has been exceeded"); 
    }
    if(capacity<0){
      throw new IllegalArgumentException("A negative capacity of items is used"); 
    }
    //the newly created array contains entries of value null
    items = (T[])new Object[capacity];
    numberOfEntries = 0;
    initialized = true;
  }
  
 // Add a new Object to the PrimQ<T> in the next available location.  If
 // all goes well, return true.  If there is no room in the PrimQ for
 // the new item, return false (you should NOT resize it)
  public boolean addItem(T item){
    checkInitialization();
    boolean result = false;
    if(numberOfEntries == items.length) return result;
    
      items[numberOfEntries] = item;
      numberOfEntries++;
      //System.out.println(numberOfEntries);
      result = true;
    
    
    return result;
    
  }
  
  // Remove and return the "oldest" item in the PrimQ.  If the PrimQ
  // is empty, return null.
  public T removeItem(){
    checkInitialization();
    T result = null;
    int spot = 0;
    if(empty()) return result;
    for(int i = 0; i<items.length;i++){
      if(items[i]!=null){
        spot = i;
        //System.out.println(spot);
        break;
      }
    }
    
    result = items[spot];
    items[spot] = null;
    numberOfEntries--;
    
    shiftItems();
    return result;
    
  }
  
  // Returns the "oldest" item in the PrimQ.  If the PrimQ
  // is empty, return null.
  public T top()
  {
    checkInitialization();
    T result = null;
    if(!empty()){
      result = items[0];
    }
    return result;
  }
  
  //returns the newest item in the PrimQ
  public T bottom()
  {
    checkInitialization();
    T result = null;
    if(!empty()){
      result = items[numberOfEntries-1];
    }
    return result;
  }
  
  // Return true if the PrimQ is full, and false otherwise
  public boolean full(){
    return numberOfEntries==items.length;
  }
  
  // Return true if the PrimQ is empty, and false otherwise
  public boolean empty(){
    return numberOfEntries==0;
  }
  
  // Return the number of items currently in the PrimQ
  public int size(){
    return numberOfEntries;
  }
  
  // Reset the PrimQ to empty status by reinitializing the variables
  // appropriately
  public void clear(){
    for(int i = 0; i<items.length; i++){
     items[i] = null; 
    }
    numberOfEntries = 0;
  }
  
 // Logically reverse the data in the Reorder object so that the item
 // that was logically first will now be logically last and vice
 // versa.  The physical implementation of this can be done in 
 // many different ways, depending upon how you actually implemented
 // your physical MultiDS<T> class
 public void reverse()
 {
   T temp;

   for (int i = 0; i<numberOfEntries/2; i++) {
     temp = items[i];
     items[i] = items[numberOfEntries-1-i];
     items[numberOfEntries-1-i] = temp;
   }
 }
 
 // Remove the logical last item of the DS and put it at the 
 // front.  As with reverse(), this can be done physically in
 // different ways depending on the underlying implementation.  
 public void shiftRight(){
   int spot = 0;
   for(int i = 0;i<items.length;i++){
     if(items[i]==null){
       spot = i-1;
       break;
     }
     else{ 
       spot = items.length-1;
     }
   }
   T holder = items[spot];
   items[spot]=null;
   for(int i = items.length-1;i>0;i--){
     T holder2;
     holder2 = items[i-1];
     items[i-1] = items[i];
     items[i] = holder2;
   }
   items[0] = holder;
 }

 // Remove the logical first item of the DS and put it at the
 // end.  As above, this can be done in different ways.
 public void shiftLeft(){
   addItem(removeItem());
 }
 
 public void shuffle(){
//   List<T> list = Arrays.asList(items);
//   Collections.shuffle(list);
//   for(int i = 0;i<list.size();i++){
//     items[i] = list.get(i);
//     
//   }
   
   //fisher yates shuffle method
   int helper = items.length;
   Random randomObject = new Random();
   for(int i = 0;i<items.length;i++){
     int rSpot = i + randomObject.nextInt(helper - i);
     T randomItem = items[rSpot];
     items[rSpot] = items[i];
     items[i] = randomItem;
   }
   
 }
 // Reorganize the items in the object in a pseudo-random way.  The exact
 // way is up to you but it should utilize a Random object (see Random in 
 // the Java API).  Thus, after this operation the "oldest" item in the
 // DS could be arbitrary.
 
 private void checkInitialization(){
   if(!initialized){
     throw new SecurityException("An attempt to access an uninitialized object of MultiDS has been blocked");
   }
 }
 
 public String toString(){
  String retval = "Contents:\n";
  for(int i = 0; i<items.length;i++){
    if(items[i]!=null)
      retval += items[i] + " ";
  }
  return retval;
 
 }
 
 //shifts all items over after the oldest item is removed
 public void shiftItems(){
   if(items[0]==null){
     for(int i = 1; i<items.length;i++){
       items[i-1]=items[i];
     }
   }
 }
 
}