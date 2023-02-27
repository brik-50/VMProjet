package Enums;

public enum Coins {
    HALF_ONE_COIN(0.5),
    ONE_COIN(1),
    TWO_COIN(2),
    FIVE_COIN(5),
    TEN_COIN(10)
    ;

    private double value;

    private Coins(double value) {  this.value = value; }

    public double getValue() {
        return value;
    }
}
