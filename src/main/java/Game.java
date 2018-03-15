import java.util.*;

public class Game {

    private static boolean isFirstGamePhaseEnded = false;
    private static boolean isPlayerRoundEnded = false;
    private static boolean isComputerroundEnded = false;

    private static int bet;

    public static void main(String args[]){
        Table table = new Table();
        Contestant humanPlayer = table.getHumanPlayer();
        Bank croupier = table.getCroupier();
        Scanner scanner = new Scanner(System.in);

        while (!isFirstGamePhaseEnded) {
            System.out.println("You got: " + humanPlayer.getMoney() + " $");
            System.out.println("How much do you want to bet ? ");
            bet = scanner.nextInt();
            bet(humanPlayer);
            if(bet == 0){
                continue;
            }
            System.out.println("You got: " + humanPlayer.getMoney() + " $");
            System.out.println("You bet: " + bet + " $");
            firstDistribution(croupier, humanPlayer);

            List<Card> playerCards = humanPlayer.getCards();
            System.out.print("Player Cards: ");
            cardsDisplaing(playerCards);
            System.out.print("Croupier Cards: ");
            List<Card> croupierCards = croupier.getCards();
            cardsDisplaing(croupierCards);

            System.out.println("Player got: " + pointsDisplayer(humanPlayer) + " points");
            System.out.println("Croupier got: " + pointsDisplayer(croupier) + " points");
        }
    }

    private static void bet(Contestant contestant){
        if(bet>contestant.getMoney()){
            System.out.println("To small amount of moneys");
            bet = 0 ;
            return;
        }
        contestant.setMoney(- bet);
    }

    private static void firstDistribution(Bank croupier, Contestant player){
        player.setCards(croupier.giveCard());
        croupier.setCards(croupier.giveCard());
        player.setCards(croupier.giveCard());
        croupier.setCards(croupier.giveCard());
    }

    private static void cardsDisplaing (List<Card> cards){

        for(int i = 0; i<cards.size()-1 ;i++) {
            System.out.print(cards.get(i) + " ,");
        }
        System.out.println(cards.get(cards.size()-1));
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
        isPlayerRoundEnded = false;
        isComputerroundEnded = false;
    }
}
