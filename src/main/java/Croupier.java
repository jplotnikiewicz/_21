import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Croupier {
   private final int NUMBER_OF_CARDS = 52;

    private Desk desk;
    private List<Card> dealtCards;

    Croupier(){
        desk = new Desk();
        desk = shuffling(desk);
    }

    private Desk shuffling(Desk desk){
        List<Card> deskOfCards = desk.getDesk() ;
        dealtCards = new ArrayList<Card>();
        Random random = new Random();
        for(int index = 0 ; index<100 ; index++) {
            Card shufledCard = deskOfCards.remove(random.nextInt(NUMBER_OF_CARDS));
            deskOfCards.add(random.nextInt(NUMBER_OF_CARDS) ,shufledCard);
        }
        desk.setDesk(deskOfCards);
        return desk;
    }

    public Desk newDesk(){
        desk = new Desk();
        dealtCards = new ArrayList<Card>();
        desk = shuffling(desk);

        return desk;
    }

    public Card giveCard(){
        List<Card> deskOfCards = desk.getDesk();
        Card card = deskOfCards.remove(0);
        dealtCards.add(card);

        return card;
    }

    public void afterOneGame(){
        List<Card> deskOfCards = desk.getDesk();
        deskOfCards.addAll(dealtCards);
        desk.setDesk(deskOfCards);
        shuffling(desk);
        dealtCards = new ArrayList<Card>();
    }
}
