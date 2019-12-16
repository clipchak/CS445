/**
 * This is the code for Assignment 1: the Player class used within the Snap game.
 * @author  Colton Lipchak
 * @version 1.0
 * @since   2018-01-29 
 */

public class Player{
  //player's card piles are MultiDS<Card> objects
  MultiDS<Card> faceUp;
  MultiDS<Card> faceDown;
  int cardAmount;
  
  //instantiates a card player
  public Player()
  {
    faceUp = new MultiDS<>(52);
    faceDown = new MultiDS<>(52);
    cardAmount = 0;
  }
  
  //returns a player's face up card pile
  public MultiDS<Card> getFaceUp()
  {
    return faceUp;
  }
  //returns a player's face down card pile
  public MultiDS<Card> getFaceDown()
  {
    return faceDown;
  }
  //shuffles the face up pile
  public void shuffleFaceUp()
  {
    faceUp.shuffle();
  }
  //allows for adding to a player's face up pile
  public void addToFaceUp(Card addition)
  {
    faceUp.addItem(addition);
  }
  //allows for adding to a player's face down pile
  public void addToFaceDown(Card addition)
  {
    faceDown.addItem(addition);
    cardAmount++;
  }
  //switches piles
  public void switchFaceUpAndFaceDown()
  {
    faceDown = faceUp;
    faceUp = new MultiDS<>(52);
  }
  
  //probabilities
  public boolean matchSnap()
  {
    boolean[] probability = {true,true,true,true,false,false,false,false,false,false};
    return probability[(int)(Math.random()*10)];
  }
  public boolean noMatchSnap()
  {
    boolean[] probability = new boolean[100];
    for(int i = 0; i<probability.length;i++){
      probability[i] = false;
    }
    probability[0] = true;
    return probability[(int)(Math.random()*100)];
  }
  
  //moves a card from the face down pile to the face up pile
  public void moveToFaceUp()
  {
    faceUp.addItem(faceDown.removeItem());
  }
  
  public String toString()
  {
    return "Face Down Pile "+ faceDown.toString()+ "\nFace Up Pile " + faceUp.toString(); 
  }
  
}