package Model;

public class Helper {
    public static String CATEGORY =
            "    10 - Royal flush\n" +
                    "    9 - Straight flush (special case of royal flush, really)\n" +
                    "    8 - Four of a kind\n" +
                    "    7 - Full house\n" +
                    "    6 - Flush\n" +
                    "    5 - Straight\n" +
                    "    4 - Three of a kind\n" +
                    "    3 - Two pair\n" +
                    "    2 - Pair\n" +
                    "    0 - High card";

    public static int convertRank(char v) {
        switch (v) {
            case 'A':
                return 14;
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            default:
                return Integer.parseInt(v + "");
        }
    }

    public static String revertRank(int v) {
        switch (v) {
            case 14:
                return "A";
            case 13:
                return "K";
            case 12:
                return "Q";
            case 11:
                return "J";
            case 10:
                return "T";
            default:
                return Integer.toString(v);

        }
    }
}
