import java.util.List;

public class Game {

    Contestant humanPlayer = new Contestant();
    Bank computerPlayer = new Bank();
    boolean endOfTheGame = false;

    boolean isBlackJack(Player player){
        return player.getPoints() == 21;
    }

    Boolean isHumanWinner(){
        if(endOfTheGame && humanWin()){
            return true ;
        }else if (endOfTheGame && !humanWin()){
            return false ;
        }

        return null;
    }

    boolean humanWin(){
        return humanPlayer.getPoints()> computerPlayer.getPoints();
    }


    void pointsCalculator(Player player){
        List<Card> playerCards = player.getCards();
        int points = sumCardsPoints(playerCards);
        player.setPoints(points);
    }

    public int sumCardsPoints(List<Card> playerCards){
        int points = 0;
        for(Card card : playerCards){
            points += card.getValue();
        }
        return points;
    }



}
