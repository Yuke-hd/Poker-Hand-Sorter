import Model.Hand;
import Model.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        boolean debug = false;
        if (args.length > 0)
            debug = args[0].equals("debug");

        int winsPlayer1 = 0;
        int winsPlayer2 = 0;
        int game = 0;
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String input = br.readLine();
                if (input == null)
                    break;

                String s1 = input.substring(0, 15);
                String s2 = input.substring(15);

                Hand handA = new Hand(s1);
                Hand handB = new Hand(s2);

                boolean Awins = handA.getHandScore() > handB.getHandScore();
                boolean Bwins = handA.getHandScore() < handB.getHandScore();
                if (Awins)
                    winsPlayer1++;
                else if (Bwins)
                    winsPlayer2++;
                // debug
                if (debug) {
                    System.out.printf("GAME %d:%n", ++game);
                    System.out.println(s1);
                    System.out.println(s2);
                    System.out.printf("Normalized: %n");
                    System.out.printf("score\t1\t2\t3\t4\t5%n");
                    System.out.println(handA + "\n" + handB);
                    System.out.println("Winner: " + (Awins ? "1" : "2"));
                    System.out.println("=============");
                }
            }
            //debug
            if (debug) {
                System.out.println("debug info:\nHand Category(score)");
                System.out.println(Helper.CATEGORY);
                System.out.println("RESULT:");
            }
            System.out.printf("Player 1: %d%n",winsPlayer1);
            System.out.printf("Player 2: %d%n",winsPlayer2);

            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
