import java.util.*;

public class Card {

    private static List<String> cardsNames = new ArrayList<String>(Arrays.asList("2", "3", "4", "5", "6", "7", "8", "9", "10", "J" ,"Q", "K", "A"));

    private int value ;
    private String name ;
    private String color ;

    Card(int cardNumber, String color) throws  IllegalArgumentException{

        if(cardNumber <2  || cardNumber > 14){
            throw new IllegalArgumentException("bad card");
        }
        if(cardNumber <11) {
            this.value = cardNumber;
        }else if(cardNumber <14){
            this.value = 10;
        }else {
            this.value = 11;
        }
        this.name = cardsNames.get(cardNumber-1);
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
