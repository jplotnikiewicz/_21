import java.util.List;

public class PointsCalculator {

    public static String gameResult(Contestant humanPlayer, Bank computerPlayer){
        pointsCalculator(humanPlayer);
        pointsCalculator(computerPlayer);
        boolean isSomeoneGotMoreThan21Points = isMoreThan21(humanPlayer) || isMoreThan21(computerPlayer);
        boolean isSomeongeGotBlackJack = isBlackJack(humanPlayer) || isBlackJack(computerPlayer);
        if (Game.isComputerRoundEnded || isSomeoneGotMoreThan21Points || isSomeongeGotBlackJack){
            return winnerDetermination(humanPlayer, computerPlayer);
        }
        return "Game is stil going on";
    }

    private static void pointsCalculator(Player player){
        List<Card> playerCards = player.getCards();
        boolean isCrupierPointsShowed = player instanceof Bank && (playerCards.get(0).getValue() > 9 || Game.isPlayerRoundEnded);
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

    private static boolean isMoreThan21(Player player){ return player.getPoints() > 21; }
    private static boolean isMoreThan21(int points) {return points > 21;}
    private static boolean isBlackJack(Player player){
        return player.getPoints() == 21;
    }
    private static boolean draw(Contestant humanPlayer, Bank computerPlayer) {
        return humanPlayer.getPoints() == computerPlayer.getPoints();
    }
    private static boolean humanWin(Contestant humanPlayer, Bank computerPlayer){
        return humanPlayer.getPoints()> computerPlayer.getPoints();
    }

}
