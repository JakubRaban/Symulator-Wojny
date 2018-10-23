package pl.jakubraban.warsimulator;

public enum CardValue {

    TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE;

    public String toString() {
        switch(this) {
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            case ACE:
                return "A";
            default:
                return Integer.toString(ordinal() + 2);
        }
    }

}
