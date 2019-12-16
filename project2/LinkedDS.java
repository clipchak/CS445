/**
 * This is the code for Assignment 2: the LinkedDS class.
 * @author  Colton Lipchak
 * @version 1.0
 * @since   2018-02-19 
 */

public class LinkedDS<T> implements PrimQ<T>, Reorder{
  
  protected Node firstNode;
  protected int numOfEntries;
  
  public LinkedDS(){
    firstNode = null;
    numOfEntries = 0;
  }
  
  public LinkedDS(LinkedDS<T> oldList){
    firstNode = null;
    numOfEntries=0;
    Node current = oldList.firstNode;
    while(current!=null){
      addItem(current.data);
      current = current.next;
    }
  }
  
  // Add a new Object to end of the PrimQ<T>.  If
  // all goes well, return true.  
  public boolean addItem(T newEntry){
    Node newNode = new Node(newEntry);
    Node findLastNode = firstNode;
    if(empty()) firstNode = newNode;
    else{                             //add to end of non-empty list
      while(findLastNode.next!=null){
        findLastNode = findLastNode.next;
      }
      findLastNode.setNextNode(newNode); //make last node reference new node
    }
    
    numOfEntries++;
    return true;
  }
  
  // Remove and return the "oldest" item in the PrimQ.  If the PrimQ
  // is empty, return null.
  public T removeItem(){
    if(empty()) return null;
    Node returnVal = firstNode;
    firstNode = firstNode.next;
    numOfEntries--;
    return returnVal.data;
  }
  
  // Return true if the PrimQ is empty, and false otherwise
  public boolean empty(){
    return numOfEntries==0;
  }
  
  // Return the number of items currently in the PrimQ
  public int size(){
    return numOfEntries;
  }
  
  // Reset the PrimQ to empty status by reinitializing the variables
  // appropriately
  public void clear(){
    firstNode = null;
    numOfEntries = 0;
  }
  
  
  // Logically reverse the data in the Reorder object so that the item
  // that was logically first will now be logically last and vice
  // versa.  The physical implementation of this can be done in 
  // many different ways, depending upon how you actually implemented
  // your physical LinkedDS<T> class
  public void reverse(){
    Node previous = null;
    Node current = firstNode;
    Node next;
    while(current!=null){
      next = current.next;
      current.next = previous;
      previous = current;
      current = next;
    }
    firstNode = previous;
  }
  
  // Remove the logical last item of the DS and put it at the 
  // front.  As with reverse(), this can be done physically in
  // different ways depending on the underlying implementation.  
  public void shiftRight(){
    //If linked list is empty or it contains only one node then ends method 
    if(empty() || firstNode.next == null) return;
    
    //Initialize nodes needed to find the second to last node and last node
    Node secLast = null;
    Node last = firstNode;
    
    //Loop to find the second to last and last node
    while(last.next!=null){
      secLast = last;
      last = last.next; 
    }
    
    //set the next of second to last as null 
    secLast.next = null;
    
    //set the next of last as firstNode 
    last.next = firstNode;
    
    //changes the firstNode to point to last node. 
    firstNode = last;
  }
  
  // Remove the logical first item of the DS and put it at the
  // end.  As above, this can be done in different ways.
  public void shiftLeft(){
    //creating node holders
    Node firstHolder = firstNode;
    firstNode = firstNode.next;
    firstHolder.next = null;
    Node last = firstNode;
    
    //finding the last node
    while(last.next!=null){
      last=last.next;
    }
    
    last.next = firstHolder;
  }
  
  // Shift the contents of the DS num places to the left (assume the beginning 
  // is the leftmost node), removing the leftmost num nodes.  For example, if 
  // a list has 8 nodes in it (numbered from 1 to 8), a leftShift of 3 would 
  // shift out nodes 1, 2 and 3 and the old node 4 would now be node 1.  
  // If num <= 0 leftShift should do nothing and if num >= the length of the 
  // list, the result should be an empty list.
  public void leftShift(int num){
    if(num<=0) return;
    if(num>=numOfEntries) clear();
    for(int i = 0;i<num;i++){
      firstNode = firstNode.next;
      numOfEntries--;
    }
  }
  
  // Same idea as leftShift above, but in the opposite direction.  For example, 
  // if a list has 8 nodes in it (numbered from 1 to 8) a rightShift of 3 would 
  // shift out nodes 8, 7 and 6 and the old node 5 would now be the last node
  // in the list.  If num <= 0 rightShift should do nothing and if num >= the 
  // length of the list, the result should be an empty list.
  public void rightShift(int num){
    if(num<=0) return;
    if(num>=numOfEntries) clear();
    for(int i = 0;i<num;i++){
      shiftRight();
      firstNode = firstNode.next;
      numOfEntries--;
    }
  }
  
  // In this method you will still shift the contents of the list num places to 
  // the left, but rather than removing nodes from the list you will simply change 
  // their ordering in a cyclic way.  For example, if a list has 8 nodes in it 
  // numbered from 1 to 8), a leftRotate of 3 would shift nodes 1, 2 and 3 to the
  // end of the list, so that the old node 4 would now be node 1, and the old nodes 
  // 1, 2 and 3 would now be nodes 6, 7 and 8 (in that order).  The rotation should 
  // work modulo the length of the list, so, for example, if the list is length 8 then
  // a leftRotate of 10 should be equivalent to a leftRotate of 2.  If num < 0, the 
  // rotation should still be done but it will in fact be a right rotation rather than
  // a left rotation.
  public void leftRotate(int num){
    //more rotations than number of entries case
    if(num>numOfEntries){
      for(int i = 0;i<(num%numOfEntries);i++){
        shiftLeft();
      }
      return;
    }
    //negative case
    if(num<0){
      for(int i = 0;i<Math.abs(num);i++){
        shiftRight();
      }
    }
    //regular case
    else{
      for(int i = 0;i<num;i++){
        shiftLeft();
      }
    }
  }
  
  // Same idea as leftRotate above, but in the opposite direction.  For example, if a list 
  // has 8 nodes in it (numbered from 1 to 8), a rightRotate of 3 would shift nodes 8, 7 and 
  // 6 to the beginning of the list, so that the old node 8 would now be node 3, the old node 
  // 7 would now be node 2 and the old node 6 would now be node 1.  The behavior for num > the 
  // length of the list and for num < 0 should be analogous to that described above for leftRotate.
  public void rightRotate(int num){
    //more rotations than number of entries case
    if(num>numOfEntries){
      for(int i = 0;i<(num%numOfEntries);i++){
        shiftRight();
      }
      return;
    }
    //negative case
    if(num<0){
      for(int i = 0;i<Math.abs(num);i++){
        shiftLeft();
      }
      return;
    }
    //regular case
    for(int i = 0;i<num;i++){
      shiftRight();
    }
  }
  
  public String toString(){
    String result = "";
    Node currentNode = firstNode;
    while (currentNode != null) {
      result = result + currentNode.data + " ";
      currentNode = currentNode.next;
    }
    return result;
  }
  
  protected class Node{
    private T    data; // Entry of data in stack
    private Node next; // Link to next node
    
    protected Node(T dataPortion){
      this(dataPortion, null);
    } 
    
    protected Node(T dataPortion, Node linkPortion){
      data = dataPortion;
      next = linkPortion; 
    } 
    
    protected T getData(){
      return data;
    }
    
    protected void setData(T newData){
      data = newData;
    } 
    
    protected Node getNextNode(){
      return next;
    } 
    
    protected void setNextNode(Node nextNode){
      next = nextNode;
    } 
    
  } //end node class
  
}

