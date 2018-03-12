import java.util.List;

public class Bank implements Player {

    private List<Card> cards;
    private int points;

    public List<Card> getCards(){
        return cards ;
    }
    public void setCards(Card card){
        cards.add(card);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
