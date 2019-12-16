import java.util.*;
import java.io.*;

//colton lipchak assignment 5 ------- Data Structures Professor Khattab 


@SuppressWarnings("unchecked")
public class Assig5{
  
  public static void main(String[] args){
    //creates the tree
    BinaryTree<Character> tree = new BinaryTree();
    
    //scanner to enter file
    System.out.println("Enter a text file to create the Huffman Tree (ex: huff1.txt)");
    Scanner sc = new Scanner(System.in);
    String fileName = sc.next();
    
    BinaryNode<Character> rootNode = new BinaryNode<Character>();
    
    //initializes the tree
    try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
      rootNode = createTree(rootNode,br);
      tree.setRootNode(rootNode.getLeftChild());
      tree.inorderTraverse();
      br.close();
    }catch(Exception e){
      System.out.println(e);
    }
    
    //initializes encoding table
    String[] table = returnEncodingTable(tree.getRootNode());
    int option;
    System.out.println("The Huffman Tree has been created\n");
    
    //do/while loop supports menu
    do{
      System.out.println();
      
      System.out.println("Please choose from the following");
      System.out.println("1) Encode a text String"
                           +"\n2) Decode a Huffman string"
                           +"\n3) Quit");
      Scanner sc2 = new Scanner(System.in);
      option = sc2.nextInt();
      switch(option){
        
        //case 1: entering a huffman string
        case 1:      
          System.out.println("Enter a string from the following characters: ");
          for(int i = 0;i<table.length;i++){
            System.out.print(table[i].substring(0,1));
          }
          Scanner scEncode = new Scanner(System.in);
          String encodeString = scEncode.next();
          System.out.println("Huffman String: ");
          System.out.print(encodeToString(encodeString,table));
          System.out.println();
          break;
          
          //case 2: using encoding table
        case 2:
          System.out.println("Here is the encoding table: ");
          
          for(int i = 0;i<table.length;i++){
            System.out.println(table[i]);
          }
          System.out.println("Please enter a Huffman string (one line no spaces)");
          Scanner scHuff = new Scanner(System.in);
          String huffString = scHuff.next();
          System.out.println("Text String: " + decodeString(huffString,table));
          break;
        case 3:
          break;
        default: 
          System.out.println("Not a valid number");
          option = 1;
          break;
      }
      
    }while(option!=3);
  }
  
  //encodes the string
  public static String encodeToString(String encode,String[] table){
    String returnString = "";
    String[] splitTableLetter = new String[table.length];
    String[] splitTableCode = new String[table.length];
    for(int i = 0;i<splitTableLetter.length;i++){
      String[] holder = table[i].split(" ");
      splitTableLetter[i] = holder[0];
      splitTableCode[i] = holder[2];
    }
    
    for(int i = 0; i<encode.length();i++){
      String letter = encode.substring(i,i+1);
      //System.out.println(letter);
      for(int j = 0;j<splitTableLetter.length;j++){
        if(letter.equalsIgnoreCase(splitTableLetter[j])){
          //System.out.println("TACO");
          returnString = returnString + "\n" + splitTableCode[j];
          break;
          
        }
      }
    }
    return returnString;
  }
  
  //decodes the string
  public static String decodeString(String huffString, String[] table){
    String decode = " ";
    String[] splitTableLetter = new String[table.length];
    String[] splitTableCode = new String[table.length];
    for(int i = 0;i<splitTableLetter.length;i++){
      String[] holder = table[i].split(" ");
      splitTableLetter[i] = holder[0];
      splitTableCode[i] = holder[2];
    }
    
    for(int i = 0;i<huffString.length()+1;i++){
      String huffHolder = huffString.substring(0,i);
      //System.out.println(huffHolder);
      for(int j = 0;j<splitTableCode.length;j++){
        if(huffHolder.equals(splitTableCode[j])){
          decode = decode + splitTableLetter[j];
          huffString = huffString.substring(i,huffString.length());
          i=0;
        }
      }
      
    }
    
    return decode;
    
  }
  
  
  
  //method to create the tree
  public static BinaryNode<Character> createTree(BinaryNode<Character> root,BufferedReader b) throws IOException{
    String line = b.readLine();
    
    if(line==null){
      return root;
    }
    if(line.substring(0,1).equals("I")){
      BinaryNode<Character> interiorNode = new BinaryNode('-');
      if(root.isLeaf()){
        root.setLeftChild(interiorNode);
      }
      else{
        root.setRightChild(interiorNode);
      }
      //recurse through left of subtree
      createTree(interiorNode,b);
      //recurse through right of subtree
      createTree(interiorNode,b); 
    }
    if(line.substring(0,1).equals("L")){
      String array1[]= line.split(" ");
      BinaryNode<Character> leafNode = new BinaryNode(array1[1]);
      if(root.isLeaf()){
        root.setLeftChild(leafNode);
      }
      else{
        root.setRightChild(leafNode);
      }
    }
    return root;
  }
  
  //returns encoding table
  public static String[] returnEncodingTable(BinaryNode<Character> node){
    StringBuilder sb = new StringBuilder();
    String[] traverseInput = new String[100];
    String[] table = inorderTraverse(node,sb,traverseInput);
    int tableSize = 0;
    for(int i = 0; i<table.length;i++){
      if(table[i]!=null){
        //System.out.println(table[i]);
        tableSize++;
      }
      
    }
    String[] returnTable = new String[tableSize];
    for(int i = 0;i<returnTable.length;i++){
      returnTable[i] = table[i];
    }
    return returnTable;
  }
  
  //traverses the tree to create table
  public static String[] inorderTraverse(BinaryNode<Character> node,StringBuilder builder,String[] table) {
    
    if(node!=null && node.isLeaf()){
      
      String builderPart = builder.toString();
      int i = 0;
      while(table[i]!=null) i++;
      table[i] = node.getData() + " . " + builderPart;
      
    }
    
    if (node != null) {
      inorderTraverse(node.getLeftChild(),builder.append("0"),table);
      builder.setLength(builder.length()-1);
      inorderTraverse(node.getRightChild(),builder.append("1"),table);
      builder.setLength(builder.length()-1);
    } // end if
    return table;
  } // end inorderTraverse
  
}

