import java.util.ArrayList;
import java.util.List;

public class Bank extends Croupier implements Player {

    private List<Card> cards;
    private int points;

    Bank(){
        cards = new ArrayList<Card>();
        points = 0;
    }

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
