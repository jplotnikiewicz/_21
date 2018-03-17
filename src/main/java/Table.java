public final class Table {

    private Contestant humanPlayer;
    private Bank croupier;

    Table(){
        humanPlayer = new Contestant();
        croupier = new Bank();
    }

    public Bank getCroupier() {
        return croupier;
    }
//
    public Contestant getHumanPlayer() {
        return humanPlayer;
    }

}
