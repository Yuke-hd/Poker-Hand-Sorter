package Model;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/*
    Hand Category
    10 - Royal flush
    9 - Straight flush (special case of royal flush, really)
    8 - Four of a kind
    7 - Full house
    6 - Flush
    5 - Straight
    4 - Three of a kind
    3 - Two pair
    2 - Pair
    0 - High card
 */

public class Hand {
    private final Card[] handWSuit = new Card[5];   // hand array w/ suit information
    private final int[] hand = new int[5];          // hand array w/o suit information
    private int handScore = 0;                      // The final score of this hand

    // Hand Category
    private boolean flush;
    private boolean straight;
    private boolean sFlush;
    private boolean rFlush;
    private int category = 0;

    public Hand(String cards) {
        String[] arr = cards.split(" ");
        for (int i = 0; i < arr.length; i++) {
            handWSuit[i] = new Card(arr[i].charAt(0), arr[i].charAt(1));
        }
        this.normalize();
        this.eval();
    }

    // CORE Algorithm
    private void normalize() {
        // Sort the handWSuit array base on the value of the cards, in descending order
        Arrays.sort(handWSuit);
        // freq array: index is the card value, the actual value represents the frequencies
        // E.g. 5 5 7 A A -> A[5] = 3, A[7] = 3, A[14] = 2
        int[] freq = new int[15];
        for (Card card : handWSuit) {
            freq[card.getVal()]++;
        }

        // Mapping <frequency - card value> starting from the back of the freq array
        /*
            E.g. A[5] = 2, A[7] = 1, A[14] = 2
                2 - "A5" <- this is why starts from the back, or you get "5A"
                1 - "7"
         */
        TreeMap<Integer, String> map = new TreeMap<>(Collections.reverseOrder());
        for (int i = freq.length - 1; i > 1; i--) {
            if (freq[i] == 0)
                continue;
            String rank = Helper.revertRank(i);
            map.put(freq[i], map.getOrDefault(freq[i], "") + rank);
        }

        // For each set in the treemap, add each character of the string i times;
        /*
            E.g.    i   -   string
                    2   -   "A5"
                    1   -   "7"
            Result: A A
                    A A 5 5
                    A A 5 5 7
         */
        int index = 0;
        for (Map.Entry<Integer, String> e : map.entrySet()) {
            String ranks = e.getValue();
            int freqz = e.getKey();
            for (char ch : ranks.toCharArray()) {
                for (int i = 0; i < freqz; i++) {
                    hand[index++] = Helper.convertRank(ch);
                }
            }
        }
    }

    private void eval() {
        /*
            cat     0       1       2       3       4
            3       14      14      5       5       7
            24-bit representation of the final hand score
            0010    1110    1110    0101    0101    0111
            decimal score
            3073367
        */
        handScore = isFlush() ? 6 : handScore;
        handScore = isStraight() ? 5 : handScore;
        handScore = isStraightFlush() ? 9 : handScore;
        handScore = isRoyalFlush() ? 10 : handScore;
        handScore = is22s() ? 3 : handScore;
        handScore = is3s() ? 4 : handScore;
        handScore = is2s() ? 2 : handScore;
        handScore = is4s() ? 8 : handScore;
        handScore = isFullHouse() ? 7 : handScore;
        category = handScore;
        int index = 20;
        handScore <<= index;
        for (int x : hand) {
            index -= 4;
            handScore += x << index;
        }
    }

    public int getHandScore() {
        return handScore;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(category + "\t");
        for (int x : hand) {
            s.append(x).append("\t");
        }
        return s.toString();
    }

    // TODO 1
    // 2
    private boolean is2s() {
        boolean flag1 = hand[0] == hand[1];
        for (int i = 1; i < 3; i++) {
            if (hand[i] == hand[i + 1])
                return false;
        }
        return flag1;
    }

    // 3
    private boolean is22s() {
        boolean flag1 = hand[0] == hand[1];
        boolean flag2 = hand[2] == hand[3];
        boolean flag3 = hand[0] != hand[2];
        boolean flag4 = hand[1] != hand[4];
        return flag1 && flag2 && flag3 && flag4;
    }

    // 4
    // TODO also see isFullHouse()
    private boolean is3s() {
        for (int i = 0; i < 2; i++) {
            if (hand[i] != hand[i + 1]) {
                return false;
            }
        }
        return hand[3] != hand[4];  //  Three of a kind and no Pair
    }

    // 5
    private boolean isStraight() {
        straight = true;
        for (int i = 0; i < 4; i++) {
            if (hand[i] - hand[i + 1] != 1) {
                straight = false;
                break;
            }
        }
        return straight;    // The cards are increasing continuously in rank
    }

    // 6
    private boolean isFlush() {
        flush = true;
        for (int i = 0; i < 4; i++) {
            if (handWSuit[i].getSuit() != handWSuit[i + 1].getSuit()) {
                flush = false;
                break;
            }
        }
        return flush;   // All cards has same suit
    }

    // 7
    // TODO also see is3s()
    private boolean isFullHouse() {
        for (int i = 0; i < 2; i++) {
            if (hand[i] != hand[i + 1]) {
                return false;
            }
        }
        return hand[3] == hand[4];  //  Three of a kind and a Pair
    }

    // 8
    private boolean is4s() {
        int sum = 0;
        for (int c : hand) {
            sum += c;
        }
        boolean flag1 = sum - hand[0] == 4 * hand[4];
        boolean flag2 = sum - hand[1] == 4 * hand[0];
        boolean flag3 = sum != 5 * hand[0];
        return flag1 && flag2 && flag3;     // Four cards of the same value
    }

    // 9
    private boolean isStraightFlush() {
        sFlush = flush && straight;
        return sFlush;  //  All five cards in consecutive value order, with the same suit
    }

    // 10
    private boolean isRoyalFlush() {
        rFlush = sFlush && (hand[0] == 14);    // The first card in the hand is Ace(14)
        return rFlush;  //  Ten, Jack, Queen, King and Ace in the same suit
    }


}
