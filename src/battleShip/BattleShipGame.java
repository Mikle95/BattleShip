package battleShip;

import java.util.Scanner;

/**
 * This main class controls game process
 */
public class BattleShipGame {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        do {
            Ocean ocean = new Ocean();
            ocean.placeAllShipsRandomly();
            ocean.print();

            do {
                makeShot(scan, ocean);
                ocean.print();
            } while (!ocean.isGameOver());

            statistic(ocean);
            System.out.println("Enter \"restart\" to play again");
        } while (scan.next().toLowerCase().equals("restart"));
    }

    /**
     * This method is used to input coordinates and check them
     * @param scan input stream
     * @param ocean game field
     */
    static void makeShot(Scanner scan, Ocean ocean) {
        int x = -1, y = -1;
        do {
            try {
                x = scan.nextInt();
                y = scan.nextInt();

                if (x < 0 || x > 9 || y < 0 || y > 9)
                    System.out.println("Out of field");
            } catch (Exception ex) {
                System.out.println("Not a number");
                scan.nextLine();
            }
        } while (x < 0 || x > 9 || y < 0 || y > 9);
        ocean.shootAt(x, y);
    }


    /**
     * This method outputs statistic of transferred ocean
     * @param ocean
     */
    static void statistic(Ocean ocean) {
        System.out.println("Statistic:\n" + "Shots Fired: " + ocean.getShotsFired() + "\n" +
                "Hit Count: " + ocean.getHitCount() + "\n" +
                "Ships Sunk: " + ocean.getShipsSunk());
    }


    /**
     * This method can be used to check work of Ocean class
     */
    static void ShootAll() {
        Ocean oc = new Ocean();
        oc.placeAllShipsRandomly();

        for (int i = -2; i < 12; i++)
            for (int j = -5; j < 13; j++)
                oc.shootAt(i, j);

        oc.print();
        statistic(oc);
    }
}
