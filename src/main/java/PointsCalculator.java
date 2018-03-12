import java.util.List;

public class PointsCalculator {

    private boolean isEndOfHumanPlayerTurn = false;
    private boolean isEndOfComputerPlayerTurn = false;

    public void setEndOfHumanPlayerTurn(boolean endOfHumanPlayerTurn) {
        this.isEndOfHumanPlayerTurn = endOfHumanPlayerTurn;
    }

    public void setEndOfComputerPlayerTurn(boolean endOfComputerPlayerTurn) {
        isEndOfComputerPlayerTurn = endOfComputerPlayerTurn;
    }

    public String gameResult(Contestant humanPlayer, Bank computerPlayer){
        pointsCalculator(humanPlayer);
        pointsCalculator(computerPlayer);
        if (isEndOfComputerPlayerTurn) {
            return winnerDetermination(humanPlayer, computerPlayer);
        }
        return "Game is stil going on";
    }

    private void pointsCalculator(Player player){
        List<Card> playerCards = player.getCards();
        boolean isCrupierPointsShowed = isEndOfHumanPlayerTurn && player instanceof Bank;
        int points;
        if(isCrupierPointsShowed) {
            points = sumCardsPoints(playerCards);
        }else{
            points = playerCards.get(0).getValue();
        }
        player.setPoints(points);
    }

    private String winnerDetermination(Contestant humanPlayer, Bank computerPlayer){
        boolean isDraw = (isBlackJack(humanPlayer) && isBlackJack(computerPlayer)) || draw(humanPlayer, computerPlayer);
        boolean isHumanPlayerWin = (isBlackJack(humanPlayer) && !isBlackJack(computerPlayer)) || humanWin(humanPlayer, computerPlayer);
        if (isDraw) {
            return "Draw";
        } else if (isHumanPlayerWin) {
            return "Contestant";
        }
        return "Bank";
    }

    private int sumCardsPoints(List<Card> playerCards){
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

    private boolean isMoreThan21(int points){
        return points > 21;
    }

    private boolean isBlackJack(Player player){
        return player.getPoints() == 21;
    }
    private boolean draw(Contestant humanPlayer, Bank computerPlayer) {
        return humanPlayer.getPoints() == computerPlayer.getPoints();
    }
    private boolean humanWin(Contestant humanPlayer, Bank computerPlayer){
        return humanPlayer.getPoints()> computerPlayer.getPoints();
    }

}
