

/**
 * This is the code for Assignment 1: the Snap card game.
 * @author  Colton Lipchak
 * @version 1.0
 * @since   2018-01-29 
 */

public class Snap{
  
  public static void main(String[] args){
    int nPlayers = 0;
    int nRounds = 0;
    if(args.length!=2){
      System.out.println("Usage: program<#rounds> <#players(2-7)>");
      return;
    }
    nRounds = Integer.parseInt(args[0]);
    nPlayers = Integer.parseInt(args[1]);
    //declare and instantiate our card piles
    System.out.println("Welcome to the game of snap! You have chosen to deal cards through:");
    System.out.println(nRounds + " rounds and "+ nPlayers + " players!");
    System.out.println("Here are the players and their initial piles:\n");
    MultiDS<Card> deck = makeDeck();
    deck.shuffle();
    
    MultiDS<Card> snapPool = new MultiDS<>(52);
    Player[] players = new Player[nPlayers];
    for(int i = 0; i<players.length; i++){
      players[i] = new Player();
    }
    
    //spreading the initial deck evenly to each player's facedown pile
    for(int j = 0; j< 52/nPlayers;j++){
      for (int i = 0; i < nPlayers; i++){
        players[i].addToFaceDown(deck.removeItem());
        //faceUpPiles.addItem(new MultiDS<Card>(52));
      }
    }
    
    //adding extra cards to the snap pool
    for(int i = 0; i<52%nPlayers;i++){
      snapPool.addItem(deck.removeItem());
    }
    
    // initializing variables used for the game
    int rounds = 0;
    boolean gameOver = false;
    Card[] comparisonCards = new Card[nPlayers];
    int firstMatch = 0;
    int secondMatch = 0;
    boolean match = false;
    for(int i = 0; i<players.length;i++){
      System.out.println("Player " + i + "\n" + players[i] + "\n");
    }
    System.out.println("Here is the Snap Pool: \n" + snapPool + "\n");
    
    
    while(!gameOver && rounds<nRounds){
      
      //moving one card to the face up pile
      for(int i = 0; i<players.length;i++){
        players[i].moveToFaceUp(); 
      }
      //moving player's first faceUp cards into an array to make the comparison easier
      for(int i = 0; i<players.length;i++){
        comparisonCards[i] = players[i].getFaceUp().bottom();
      }
      
      
      //check for matches
      for(int i = 0;i<comparisonCards.length;i++){
        for(int j = comparisonCards.length-1; j>0;j--){
          if(comparisonCards[i]!=null && comparisonCards[j]!=null&& j!=i && comparisonCards[i].compareTo(comparisonCards[j]) ==0){
            firstMatch = i;
            secondMatch = j;
            match = true;
          }
          if(snapPool.bottom()!=null && comparisonCards[i]!=null && comparisonCards[j]!=null && comparisonCards[i].compareTo(snapPool.bottom()) ==0){
            firstMatch = i;
            secondMatch = -1;
            match = true;
          }
        }
      }
      
      //check for winner
      int correctSnapCallers = 0;
      int snapCaller = 0;
      if(match){
        System.out.println("Round #" + rounds + ": There is a match");
        for(int i = 0; i<players.length;i++){
          //calls upon the probability for a player yelling snap during a match
          if(players[i].matchSnap()){
            correctSnapCallers++;
            snapCaller = i;
          }
        }
        //if the amount of snap callers is over 1, don't move any cards
        if(correctSnapCallers>1 || correctSnapCallers == 0)
          System.out.println("  -There were " +correctSnapCallers + " callers, so no cards move.");
      }else{
        System.out.println("Round #" + rounds + ": No match");
      }
      
      //if only one person made the correct call, moves the matching piles to their pile and shuffles. Deciphers whether they were a part of the winning match to split up piles
      if(correctSnapCallers ==1 && secondMatch!=-1){
        System.out.println("  -Player " + firstMatch + "'s " + players[firstMatch].getFaceUp().bottom() + " matches Player " + secondMatch + "'s " + players[secondMatch].getFaceUp().bottom());
        System.out.println("  -Player " + snapCaller + " was the only correct snap call in round "+ rounds + ". They receive the matching piles!");
        for(int i = 0; i< players[firstMatch].getFaceUp().size();i++){
          if(snapCaller==firstMatch){
            players[snapCaller].addToFaceDown(players[secondMatch].getFaceUp().removeItem());
            players[snapCaller].getFaceDown().shuffle();
            break;
          }
          if(snapCaller==secondMatch){
            players[snapCaller].addToFaceDown(players[firstMatch].getFaceUp().removeItem());
            players[snapCaller].getFaceDown().shuffle();
            break;
          }
          if(snapCaller!=firstMatch && snapCaller != secondMatch){
            players[snapCaller].addToFaceDown(players[firstMatch].getFaceUp().removeItem());
            players[snapCaller].addToFaceDown(players[secondMatch].getFaceUp().removeItem());
            players[snapCaller].getFaceDown().shuffle();
            break;
          }
        }
      }
      
      //Snap pool match code. If only one person made the correct call, moves the matching piles to their pile from SNAP POOL then shuffles
      if(correctSnapCallers ==1 && secondMatch==-1){
        System.out.println("  -Player " + firstMatch + "'s " + players[firstMatch].getFaceUp().bottom() + " matches the snap pool's " + snapPool.bottom());
        System.out.println("  -Player " + snapCaller + " was the only correct snap call in round "+ rounds + ". They receive the matching piles!");
        for(int i = 0; i< snapPool.size();i++){
          players[snapCaller].addToFaceDown(snapPool.removeItem());
          players[snapCaller].getFaceDown().shuffle();
        }
      }
      
      //check for incorrect snaps when there is no match
      if(!match){
        for(int i = 0; i<players.length;i++){
          //calls upon probability of calling an incorrect snap, moves players cards to snap pool if they made an incorrect call
          if(players[i].noMatchSnap()){
            System.out.println("  -Player " + i + " incorrectly called a snap in round "+ rounds + ". His cards from face up move now to the snap pool...");
            for(int j = 0; j<players[i].getFaceUp().size();j++){
              snapPool.addItem(players[i].getFaceUp().removeItem());
            }
            correctSnapCallers++;
            snapCaller = i;
          }
        }
      }
      
      //check for empty piles
      for(int i = 0; i<players.length;i++){
        //checks if a player has no cards remaining, ending the game
        if(players[i].getFaceDown().empty()&&players[i].getFaceUp().empty()){
          gameOver = true;
          System.out.println("Player " + i + " has no cards left! Ending game...");
          break;
        }
        //checks to see if a player's facedown pile is empty. If so, shuffles their faceup pile and moves that into their facedown pile
        if(players[i].getFaceDown().empty()){
          players[i].shuffleFaceUp();
          players[i].switchFaceUpAndFaceDown();
        }
      }
      
      //resetting the variables  used and increasing rounds
      firstMatch = 0;
      secondMatch = 0;
      correctSnapCallers = 0;
      snapCaller = 0;
      match = false;
      rounds++;
    }
    
    //decide the winners
    String winnerReturn = "\nAfter " + rounds+ " rounds: \n";
    for(int i = 0; i<players.length;i++){
      int helper = 0;
      helper += players[i].getFaceUp().size();
      helper += players[i].getFaceDown().size();
      winnerReturn += "Player " + i + " ends with " + helper + " cards.\n";
    }
    System.out.println(winnerReturn);
    System.out.println("Snap Pool ends with " + snapPool.size());
  }
  
  //makes the initial card deck
  public static MultiDS<Card> makeDeck(){
    Card c1 = new Card(Card.Suits.Diamonds, Card.Ranks.Two);
    Card c2 = new Card(Card.Suits.Hearts, Card.Ranks.Two);
    Card c3 = new Card(Card.Suits.Spades, Card.Ranks.Two);
    Card c4 = new Card(Card.Suits.Clubs, Card.Ranks.Two); 
    
    Card c5 = new Card(Card.Suits.Diamonds, Card.Ranks.Three);
    Card c6 = new Card(Card.Suits.Hearts, Card.Ranks.Three);
    Card c7 = new Card(Card.Suits.Spades, Card.Ranks.Three);
    Card c8 = new Card(Card.Suits.Clubs, Card.Ranks.Three); 
    
    Card c9 = new Card(Card.Suits.Diamonds, Card.Ranks.Four);
    Card c10 = new Card(Card.Suits.Hearts, Card.Ranks.Four);
    Card c11 = new Card(Card.Suits.Spades, Card.Ranks.Four);
    Card c12 = new Card(Card.Suits.Clubs, Card.Ranks.Four); 
    
    Card c13 = new Card(Card.Suits.Diamonds, Card.Ranks.Five);
    Card c14 = new Card(Card.Suits.Hearts, Card.Ranks.Five);
    Card c15 = new Card(Card.Suits.Spades, Card.Ranks.Five);
    Card c16 = new Card(Card.Suits.Clubs, Card.Ranks.Five); 
    
    Card c17 = new Card(Card.Suits.Diamonds, Card.Ranks.Six);
    Card c18 = new Card(Card.Suits.Hearts, Card.Ranks.Six);
    Card c19 = new Card(Card.Suits.Spades, Card.Ranks.Six);
    Card c20 = new Card(Card.Suits.Clubs, Card.Ranks.Six); 
    
    Card c21 = new Card(Card.Suits.Diamonds, Card.Ranks.Seven);
    Card c22 = new Card(Card.Suits.Hearts, Card.Ranks.Seven);
    Card c23 = new Card(Card.Suits.Spades, Card.Ranks.Seven);
    Card c24 = new Card(Card.Suits.Clubs, Card.Ranks.Seven); 
    
    Card c25 = new Card(Card.Suits.Diamonds, Card.Ranks.Eight);
    Card c26 = new Card(Card.Suits.Hearts, Card.Ranks.Eight);
    Card c27 = new Card(Card.Suits.Spades, Card.Ranks.Eight);
    Card c28 = new Card(Card.Suits.Clubs, Card.Ranks.Eight); 
    
    Card c29 = new Card(Card.Suits.Diamonds, Card.Ranks.Nine);
    Card c30 = new Card(Card.Suits.Hearts, Card.Ranks.Nine);
    Card c31 = new Card(Card.Suits.Spades, Card.Ranks.Nine);
    Card c32 = new Card(Card.Suits.Clubs, Card.Ranks.Nine); 
    
    Card c33 = new Card(Card.Suits.Diamonds, Card.Ranks.Ten);
    Card c34 = new Card(Card.Suits.Hearts, Card.Ranks.Ten);
    Card c35 = new Card(Card.Suits.Spades, Card.Ranks.Ten);
    Card c36 = new Card(Card.Suits.Clubs, Card.Ranks.Ten); 
    
    Card c37 = new Card(Card.Suits.Diamonds, Card.Ranks.Jack);
    Card c38 = new Card(Card.Suits.Hearts, Card.Ranks.Jack);
    Card c39 = new Card(Card.Suits.Spades, Card.Ranks.Jack);
    Card c40 = new Card(Card.Suits.Clubs, Card.Ranks.Jack); 
    
    Card c41 = new Card(Card.Suits.Diamonds, Card.Ranks.Queen);
    Card c42 = new Card(Card.Suits.Hearts, Card.Ranks.Queen);
    Card c43 = new Card(Card.Suits.Spades, Card.Ranks.Queen);
    Card c44 = new Card(Card.Suits.Clubs, Card.Ranks.Queen); 
    
    Card c45 = new Card(Card.Suits.Diamonds, Card.Ranks.King);
    Card c46 = new Card(Card.Suits.Hearts, Card.Ranks.King);
    Card c47 = new Card(Card.Suits.Spades, Card.Ranks.King);
    Card c48 = new Card(Card.Suits.Clubs, Card.Ranks.King); 
    
    Card c49 = new Card(Card.Suits.Diamonds, Card.Ranks.Ace);
    Card c50 = new Card(Card.Suits.Hearts, Card.Ranks.Ace);
    Card c51 = new Card(Card.Suits.Spades, Card.Ranks.Ace);
    Card c52 = new Card(Card.Suits.Clubs, Card.Ranks.Ace); 
    
    MultiDS<Card> deck = new MultiDS<>(52);
    deck.addItem(c1);
    deck.addItem(c2);
    deck.addItem(c3);
    deck.addItem(c4);
    
    deck.addItem(c5);
    deck.addItem(c6);
    deck.addItem(c7);
    deck.addItem(c8);
    
    deck.addItem(c9);
    deck.addItem(c10);
    deck.addItem(c11);
    deck.addItem(c12);
    
    deck.addItem(c13);
    deck.addItem(c14);
    deck.addItem(c15);
    deck.addItem(c16);
    
    deck.addItem(c17);
    deck.addItem(c18);
    deck.addItem(c19);
    deck.addItem(c20);
    
    deck.addItem(c21);
    deck.addItem(c22);
    deck.addItem(c23);
    deck.addItem(c24);
    
    deck.addItem(c25);
    deck.addItem(c26);
    deck.addItem(c27);
    deck.addItem(c28);
    
    deck.addItem(c29);
    deck.addItem(c30);
    deck.addItem(c31);
    deck.addItem(c32);
    
    deck.addItem(c33);
    deck.addItem(c34);
    deck.addItem(c35);
    deck.addItem(c36);
    
    deck.addItem(c37);
    deck.addItem(c38);
    deck.addItem(c39);
    deck.addItem(c40);
    
    deck.addItem(c41);
    deck.addItem(c42);
    deck.addItem(c43);
    deck.addItem(c44);
    
    deck.addItem(c45);
    deck.addItem(c46);
    deck.addItem(c47);
    deck.addItem(c48);
    
    deck.addItem(c49);
    deck.addItem(c50);
    deck.addItem(c51);
    deck.addItem(c52);
    deck.shuffle();
    return deck;
  }
  
}