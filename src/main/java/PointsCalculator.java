import java.util.List;

public class PointsCalculator {

    static boolean isEndOfHumanPlayerTurn = false;
    static boolean isEndOfComputerPlayerTurn = false;

    public static String gameResult(Contestant humanPlayer, Bank computerPlayer){
        pointsCalculator(humanPlayer);
        pointsCalculator(computerPlayer);
        if (isEndOfComputerPlayerTurn) {
            return winnerDetermination(humanPlayer, computerPlayer);
        }
        return "Game is stil going on";
    }

    private static void pointsCalculator(Player player){
        List<Card> playerCards = player.getCards();
        boolean isCrupierPointsShowed = isEndOfHumanPlayerTurn && player instanceof Bank;
        boolean isHumanPlayer = player instanceof Contestant;
        int points;
        if(isCrupierPointsShowed || isHumanPlayer) {
            points = sumCardsPoints(playerCards);
        }else{
            points = playerCards.get(0).getValue();
        }
        player.setPoints(points);
    }

    private static String winnerDetermination(Contestant humanPlayer, Bank computerPlayer){
        boolean isDraw = (isBlackJack(humanPlayer) && isBlackJack(computerPlayer)) || draw(humanPlayer, computerPlayer);
        boolean isHumanPlayerWin = (isBlackJack(humanPlayer) && !isBlackJack(computerPlayer)) || humanWin(humanPlayer, computerPlayer);
        if (isDraw) {
            return "Draw";
        } else if (isHumanPlayerWin) {
            return "Contestant";
        }
        return "Bank";
    }
//
    private static int sumCardsPoints(List<Card> playerCards){
        int points = 0;
        for(Card card : playerCards){
            points += card.getValue();
            boolean isAceValueOnePoint = isMoreThan21(points) && card.getName().equals("A");
            if(isAceValueOnePoint) {
                points = points - 10;
            }
        }
        return points;
    }

    public static boolean isMoreThan21(int points){
        return points > 21;
    }

    public static boolean isBlackJack(Player player){
        return player.getPoints() == 21;
    }
    public static boolean draw(Contestant humanPlayer, Bank computerPlayer) {
        return humanPlayer.getPoints() == computerPlayer.getPoints();
    }
    public static boolean humanWin(Contestant humanPlayer, Bank computerPlayer){
        return humanPlayer.getPoints()> computerPlayer.getPoints();
    }

}
