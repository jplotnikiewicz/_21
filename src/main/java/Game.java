import java.util.*;

public class Game extends  PointsCalculator {

    private static boolean isFirstGamePhaseEnded = false;
    private static boolean isPlayerRoundEnded = false;
    private static boolean isComputerroundEnded = false;

    private static int bet;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        Table table = new Table();
        String result;
        Contestant humanPlayer = table.getHumanPlayer();
        Bank croupier = table.getCroupier();
        boolean isEndGame;

        while (!isFirstGamePhaseEnded) {
            bet(humanPlayer);
            if(bet == 0){
                continue;
            }
            firstDistribution(croupier, humanPlayer);
            cardsDisplaing(croupier, humanPlayer);
            result = PointsCalculator.gameResult(humanPlayer, croupier);
            pointDisplaing(croupier, humanPlayer);

            isEndGame = result.equals("Bank") || result.equals("Contestant");
            if(isEndGame){

            }
        }
    }

    private static void bet(Contestant humanPlayer){
        System.out.println("You got: " + humanPlayer.getMoney() + " $");
        System.out.println("How much do you want to bet ? ");
        bet = scanner.nextInt();
        if(bet>humanPlayer.getMoney()){
            System.out.println("To small amount of moneys");
            bet = 0 ;
            return;
        }
        humanPlayer.setMoney(- bet);
    }

    private static void firstDistribution(Bank croupier, Contestant humanPlayer){
        System.out.println("You got: " + humanPlayer.getMoney() + " $");
        System.out.println("You bet: " + bet + " $");
        humanPlayer.setCards(croupier.giveCard());
        croupier.setCards(croupier.giveCard());
        humanPlayer.setCards(croupier.giveCard());
        croupier.setCards(croupier.giveCard());
    }

    private static void cardsDisplaing (Bank croupier, Contestant humanPlayer){
        List<Card> playerCards = humanPlayer.getCards();
        System.out.print("Player Cards: ");
        dispay(playerCards);
        System.out.print("Croupier Cards: ");
        List<Card> croupierCards = new ArrayList<Card>(croupier.getCards()) ;
        if(croupierCards.get(1).getValue()<=10) {
            croupierCards.remove(1);
        }
        dispay(croupierCards);
    }

    private static void dispay (List<Card> cards){

        for(int i = 0; i<cards.size()-1 ;i++) {
            System.out.print(cards.get(i) + " ,");
        }
        System.out.println(cards.get(cards.size()-1));
    }

    private static void pointDisplaing (Bank croupier, Contestant humanPlayer){
        System.out.println("Player got: " + humanPlayer.getPoints() + " points");
        System.out.println("Croupier got: " + croupier.getPoints() + " points");
    }

    private static void cardForUser(Bank croupier, Contestant humanPlayer) {
        if (!isPlayerRoundEnded) {
            humanPlayer.setCards(croupier.giveCard());
        }
    }

    private static void cardsForCroupier(Bank croupier, int playerPoints){
        boolean isComputerGotMorePoints = croupier.getPoints() > playerPoints ;
        if(!isComputerroundEnded && !isComputerGotMorePoints){
            croupier.setCards(croupier.giveCard());
        }
    }

    private static void afterGame(String result, Contestant humanPlayer){
        if(result.equals("Contestant")) {
            humanPlayer.setMoney(2 * bet);
        }else if(result.equals("Draw")){
            humanPlayer.setMoney(bet);
        }
        bet = 0;
        isPlayerRoundEnded = false;
        isComputerroundEnded = false;
    }
}
