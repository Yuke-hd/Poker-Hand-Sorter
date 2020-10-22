package Model;

/*
    2 - 2
    3 - 3
    4 - 4
    5 - 5
    6 - 6
    7 - 7
    8 - 8
    9 - 9
    T - 10
    J - 11
    Q - 12
    K - 12
    A - 14
 */
public class Card implements Comparable<Card> {
    private final int val;
    private final char suit;

    public int getVal() {
        return val;
    }

    public char getSuit() {
        return suit;
    }

    public Card(char v, char s) {
        this.val = Helper.convertRank(v);
        this.suit = s;
    }


    @Override
    public int compareTo(Card o) {
        return this.val - o.getVal();
    }
}
