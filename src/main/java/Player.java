import java.util.List;

public interface Player {

    void setPoints(int points);
    int getPoints();
    void setCards(Card card);
    List<Card> getCards();


}
