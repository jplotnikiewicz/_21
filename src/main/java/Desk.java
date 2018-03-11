import java.lang.reflect.Array;
import java.util.*;


public class Desk {

    final int NUMBER_OF_CARDS = 52;
    final int NUMBER_OF_FIGURES = 13;
    final int NUMBER_OF_ONE_KIND_CARDS = 4;
    public List<Card> desk;

    public Desk() {
        int cardNumber = 2;
        for(; cardNumber <=  NUMBER_OF_FIGURES + 1 ; cardNumber++){

        }
        if(desk.size() != NUMBER_OF_CARDS){
            throw new IllegalArgumentException("Bad number of cards");
        }
    }


    Card [] createOneFigureCards(int cardNumber){
        return new Card[]{new Card(cardNumber, "Hearts"), new Card(cardNumber, "Diamonds"), new Card(cardNumber, "Clubes"), new Card(cardNumber, "Spades")};
    }


}
