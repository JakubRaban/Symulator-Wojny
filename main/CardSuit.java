public enum CardSuit {

    HEART, DIAMOND, CLUBS, SPADES;

    public String toString() {
        switch(this) {
            case HEART:
                return "Kier";
            case CLUBS:
                return "Trefl";
            case DIAMOND:
                return "Karo";
            case SPADES:
                return "Pik";
                default: return "";
        }
    }

}
