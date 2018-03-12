import java.util.List;

public class Contestant implements Player{

    private int money;
    private List<Card> cards;
    private int points;


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public List<Card> getCards(){
        return this.cards ;
    }

    public void setCards(Card card){
        this.cards.add(card);
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
