This is a Blackjack bot I implemented as on of the first weekly assignments for Software Engineering course taught by Prof. Jerome White in Spring 2016 at New York University Abu Dhabi. The implementation is done in Java and runs using Ant library. To launch the game with 3 players type

```bash
$>ant play -Dplayers=3
```

in the command prompt. The number of players can be chosen freely. The game will run automatically and only display the results.

Below is the original documentation describing the addition of betting and hitting strategies to the previous version of Blackjack.


# Blackjack with Strategy Pattern

## How was the strategy pattern used?

Relying on the design principle that says "Encapsulate what varies", I isolated two changing
behaviours - betting and hitting. Note that both of these behaviours change from player to player 
as each might wish to use a different game strategy. Thus, betting and hitting were taken out of the
'BlackjackPlayer' class and turned into two interfaces 'HittingStrategy' and 'BettingStrategy'.
Each interface lists a single method 'hit(Hand hand)' and  'bet(double wallet)', respectively.
I implemented three 'HittingStrategies' ('DealersHit', 'OptimisticHit', and 'CautiousHi't) as well
as two 'BettingStrategies' ('ProportionalBet' and 'HighBet').

The 'BlackJackPlayer' class now holds only a reference to 'HittingStrategy' and 'BettingStrategy' objects 
that are initialized with a particular implementation when the constructor is called.


## What was changed? 

First of all, as mentioned the constructors for 'BlackJackPlayer' were changed to initialize 'HittingStrategy' and 
'BettingStrategy' along with the player's name.

Secondly, the two key methods that changed are of course 'wager' and 'requestCard' in 'BlackJackPlayer' class.
Now both methods call upon the methods contained in the two new interfaces instead of making the decisions locally.

```java
    public double wager(){
        double bet = bettingStrategy.bet(wallet);
        wallet -= bet;
      return bet;
    }
```

```java
   public boolean requestCard(){
        return hittingStrategy.hit(playersHand);
    }
```

Thirdly, 'BlackJackTable' now contains two new lists, where one contains all betting strategy
implementations and the other contains all hitting implementations. One of each is assigned
to every new 'BlackJackPlayer' that is initialized in the 'BlackJackTable' constructor. 

Finally, the dealer player's hitting strategy is initialized with 'DealersHit' in the 'BlackJackDealer' 
class.

## How to add a new betting or hitting strategy?

A new betting strategy can be added by creating a new class that implements 'BettingStrategy'
interface, which requires only one method 'bet()'. To actually assign this method to players
one must add it to the 'betStr' list in the 'BlackJackTable', then  it will be assigned to players
when they are initialized. All the betting strategies are distributed equally between the players 
at the table. 

The process is exactlly the same for adding a new hitting strategy with the respective interface
and list in in the 'BlackJackTable'.

## Possible improvements

If I could add public methods 'setHitStrategy' and 'setBetStartegy', then one player could
alternate between different strategies throughout the game rather than sticking to one.

Also game could be more interactive if at each round player would be asked form the command line 
to choose which hit and bet strategy to adopt.

