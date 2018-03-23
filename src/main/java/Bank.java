import java.util.ArrayList;
import java.util.List;

public class Bank extends Croupier implements Player {

    private List<Card> cards;
    private int points;
    private Card hiidenCard;

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

    public void setHiidenCard(Card hiidenCard) {
        this.hiidenCard = hiidenCard;
    }

    public Card getHiidenCard() {
        if(hiidenCard == null){
            throw new IllegalArgumentException("Croupier don't get hidden card");
        }
        Card card = hiidenCard;
        hiidenCard = null;
        return card;
    }
}
