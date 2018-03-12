import java.lang.reflect.Array;
import java.util.*;


public class Desk {

    private final int NUMBER_OF_CARDS = 52;
    private final int NUMBER_OF_FIGURES = 13;
    private List<Card> desk;

    Desk() {
        int cardNumber = 2;
        for(; cardNumber <=  NUMBER_OF_FIGURES + 1 ; cardNumber++){
            List<Card> oneFigureCards = Arrays.asList(createOneFigureCards(cardNumber));
            desk.addAll(oneFigureCards);
        }
        if(desk.size() != NUMBER_OF_CARDS){
            throw new IllegalArgumentException("Bad number of cards");
        }
    }

    private Card [] createOneFigureCards(int cardNumber){
        return new Card[]{new Card(cardNumber, "Hearts"), new Card(cardNumber, "Diamonds"), new Card(cardNumber, "Clubes"), new Card(cardNumber, "Spades")};
    }

    public List<Card> getDesk() {
        return desk;
    }

    public void setDesk(List<Card> desk) {
        this.desk = desk;
    }
}
