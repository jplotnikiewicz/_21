import java.util.*;

public class Game extends  PointsCalculator {

    private static boolean isFirstGamePhaseEnded = false;
    public static boolean isPlayerRoundEnded = false;
    public static boolean isComputerRoundEnded = false;

    private static int bet;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]){
        Table table = new Table();
        String result;
        Contestant humanPlayer = table.getHumanPlayer();
        Bank croupier = table.getCroupier();
        boolean isEndGame = false;

        while (!isFirstGamePhaseEnded) {
            bet(humanPlayer);
            if(bet == 0){
                continue;
            }
            firstDistribution(croupier, humanPlayer);
            cardsDisplaing(croupier, humanPlayer);
            result = PointsCalculator.gameResult(humanPlayer, croupier);
            pointDisplaing(croupier, humanPlayer);
            isEndGame = endGameResult(result, humanPlayer);
            isFirstGamePhaseEnded = true;
            System.out.println("result = "+ result);
        }
        while (!isPlayerRoundEnded ){
            System.out.println("Do you want a card ? ");
            System.out.println("Say Yes or No");
            String doYouWantCard = scanner.next();
            doYouWantCard = doYouWantCard.toLowerCase();
            if(doYouWantCard.toLowerCase().equals("no") || isEndGame ) {
                isPlayerRoundEnded = true;
            }else if(doYouWantCard.toLowerCase().equals("yes")){
                cardForUser(croupier, humanPlayer);
                result = PointsCalculator.gameResult(humanPlayer, croupier);
                cardsDisplaing(croupier, humanPlayer);
                pointDisplaing(croupier, humanPlayer);
                isEndGame = endGameResult(result, humanPlayer);
            }else{
                System.out.println("Wrong answer");
            }
        }
        while (!isComputerRoundEnded) {
            cardsForCroupier(croupier, humanPlayer.getPoints());
            result = PointsCalculator.gameResult(humanPlayer, croupier);
            cardsDisplaing(croupier, humanPlayer);
            pointDisplaing(croupier, humanPlayer);
            endGameResult(result, humanPlayer);
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
        if(croupierCards.get(0).getValue()<10) {
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

    private static boolean endGameResult(String result, Contestant humanPlayer){
        boolean isEndGame = !result.equals("Game is stil going on");
        if(isEndGame){
            resultDispaling(result);
            afterGame(result, humanPlayer);
            isPlayerRoundEnded = true;
            isComputerRoundEnded = true;
        }
        return isEndGame;
    }

    private static void resultDispaling(String result){
        System.out.println(result.equals("Draw") ? "Resluts of the game  is a " + result : "The winner is "+ result);
    }

    private static void afterGame(String result, Contestant humanPlayer){
        if(result.equals("Contestant")) {
            humanPlayer.setMoney(2 * bet);
        }else if(result.equals("Draw")){
            humanPlayer.setMoney(bet);
        }
        bet = 0;
        isPlayerRoundEnded = false;
        isComputerRoundEnded = false;
    }

    private static void cardForUser(Bank croupier, Contestant humanPlayer) {
        if (!isPlayerRoundEnded) {
            humanPlayer.setCards(croupier.giveCard());
        }
    }

    private static void cardsForCroupier(Bank croupier, int playerPoints){
        while (!isComputerRoundEnded) {
            boolean isComputerGotMorePoints = croupier.getPoints() > playerPoints;
            boolean isComputerGot20Points = croupier.getPoints() >= 21;
            if (!isComputerGotMorePoints && !isComputerGot20Points) {
                croupier.setCards(croupier.giveCard());
            }else {
                isComputerRoundEnded = true;
            }
        }
    }


}
