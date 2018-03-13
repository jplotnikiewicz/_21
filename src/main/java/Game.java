import java.util.*;

public class Game {

    private static boolean isPlayerRoundEnded = false;
    private static boolean isComputerroundEnded = false;
    private static int bet;

    public static void main(String args[]){
        Table table = new Table();
    }

    private static void bet(int money, Contestant contestant){
        if(money>contestant.getMoney()){
            System.out.println("To small amount of moneys");
            return;
        }
        bet = money;
        contestant.setMoney(- money);
    }

    private static void firstDistribution(Bank croupier, Contestant player){
        player.setCards(croupier.giveCard());
        croupier.setCards(croupier.giveCard());
        player.setCards(croupier.giveCard());
        croupier.setCards(croupier.giveCard());
    }

    private static int pointsDisplayer(Player player){
        if(player instanceof Contestant || isPlayerRoundEnded){
            return player.getPoints();
        }
        List <Card> bankCards = player.getCards();
        Card secoundHiddenCard = bankCards.get(1);
        int valuOfSecoundHiddenCard = secoundHiddenCard.getValue();

        return player.getPoints() - valuOfSecoundHiddenCard;
    }

    private static void cardForUser(Bank croupier, Contestant player) {
        if (!isPlayerRoundEnded) {
            player.setCards(croupier.giveCard());
        }
    }

    private static void cardsForCroupier(Bank croupier, int playerPoints){
        boolean isComputerGotMorePoints = croupier.getPoints() > playerPoints ;
        if(!isComputerroundEnded && !isComputerGotMorePoints){
            croupier.setCards(croupier.giveCard());
        }
    }

    private static void afterGame(String result, Contestant contestant){
        if(result.equals("Contestant")) {
            contestant.setMoney(2 * bet);
        }else if(result.equals("Draw")){
            contestant.setMoney(bet);
        }
        bet = 0;
    }


}
