package battleShip;

import java.util.Random;

/**
 * game field class
 * contains methods to control game process
 */
public class Ocean {
    Ship[][] ships = new Ship[10][10];
    int shotsFired;
    int hitCount;
    int shipsSunk;

    /**
     * Class constructor
     * initialize variables
     * fills game field with EmptySeas
     */
    public Ocean() {
        shotsFired = 0;
        hitCount = 0;
        shipsSunk = 0;

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                ships[i][j] = new EmptySea();
                ships[i][j].setBowRow(i);
                ships[i][j].setBowColumn(j);
            }
    }

    /**
     * This method places 10 ships in random coordinates and orientation
     */
    void placeAllShipsRandomly() {
        Random rnd = new Random();
        int x, y;
        boolean horizontal;
        Ship[] types = {new BattleShip(), new Cruiser(), new Destroyer(), new Submarine()};

        for (Ship ship : types)
            for (int i = 0; i <= 4 - ship.getLength(); i++) {
                do {
                    x = rnd.nextInt(10);
                    y = rnd.nextInt(10);
                    horizontal = rnd.nextBoolean();
                } while (!ship.okToPlaceShipAt(x, y, horizontal, this));
                ship.placeShipAt(x, y, horizontal, this);
            }
    }

    /**
     * @param row
     * @param column
     * @return if cage in transferred coordinates is not empty
     */
    boolean isOccupied(int row, int column) {
        if (row < 0 || row > 9 || column < 0 || column > 9) return false;
        return !ships[row][column].getClass().equals(EmptySea.class);
    }

    /**
     * processes request to shoot at transferred coordinates
     * @param row
     * @param column
     * @return if shot was successful
     */
    boolean shootAt(int row, int column) {
        shotsFired++;
        if (row < 0 || row > 9 || column < 0 || column > 9) return false;

        if (ships[row][column].shootAt(row, column)) {
            hitCount++;
            System.out.println("hit");
            if (ships[row][column].isSunk()) {
                shipsSunk++;
                System.out.println("You just sank a " + ships[row][column].getShipType());
            }
            return true;
        }
        System.out.println("miss");
        return false;
    }

    int getShotsFired() {
        return shotsFired;
    }

    int getHitCount() {
        return hitCount;
    }

    int getShipsSunk() {
        return shipsSunk;
    }

    boolean isGameOver() {
        return getShipsSunk() >= 10;
    }

    Ship[][] getShipArray() {
        return ships;
    }

    /**
     * outputs current state of game field as text in console
     */
    void print() {
        System.out.println("- | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9");
        for (int i = 0; i < 10; i++) {
            String s = "" + i;
            for (int j = 0; j < 10; j++) {
                s += " | ";
                if (ships[i][j].isHited(i, j))
                    s += ships[i][j].toString();
                else
                    s += ".";
            }
            System.out.println(s);
        }
    }
}
