package battleShip;

/**
 * This class super class for every cage of game field
 */
public class Ship implements Cloneable {
    int bowRow;
    int bowColumn;
    int length;
    boolean horizontal;
    boolean[] hit = new boolean[4];

    /**
     * @return length of the ship
     */
    int getLength() {
        return length;
    }

    /**
     * @return row coordinate of ships first part
     */
    int getBowRow() {
        return bowRow;
    }

    /**
     * @return column coordinate of ships first part
     */
    int getBowColumn() {
        return bowColumn;
    }

    /**
     * @return ship orientation
     */
    boolean isHorizontal() {
        return horizontal;
    }

    void setBowRow(int row) {
        bowRow = row;
    }

    void setBowColumn(int column) {
        bowColumn = column;
    }

    void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    String getShipType() {
        return "no Ship Type";
    }

    /**
     * This method checks possibility to place ship in transferred coordinates
     * @param row
     * @param column
     * @param horizontal
     * @param ocean game field
     * @return true if it's possible to place, otherwise false
     */
    boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        boolean check = true;

        if (row > 9 || row < 0 || column > 9 || column < 0) return false;
        if (horizontal && column + length > 10 || !horizontal && row + length > 10) return false;

        for (int i = -1; i < length + 1; i++)
            for (int j = -1; j < 2; j++)
                if (horizontal)
                    check = check && !ocean.isOccupied(row + j, column + i);
                else
                    check = check && !ocean.isOccupied(row + i, column + j);

        return check;
    }

    /**
     * This method places ship at transferred coordinates
     * @param row
     * @param column
     * @param horizontal
     * @param ocean game field
     */
    void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
        if (!okToPlaceShipAt(row, column, horizontal, ocean)) return;

        Ship ship = (Ship) this.clone();
        ship.setBowRow(row);
        ship.setBowColumn(column);
        ship.setHorizontal(horizontal);

        Ship[][] ships = ocean.getShipArray();

        for (int i = 0; i < length; i++) {
            if (horizontal)
                ships[row][column + i] = ship;
            else
                ships[row + i][column] = ship;
        }

    }

    /**
     * @return liter to place in hit part
     */
    public String toString() {
        if (isSunk()) return "X";
        else return "S";
    }

    /**
     * @return if ship was destroyed
     */
    boolean isSunk() {
        return hit[0] && hit[1] && hit[2] && hit[3];
    }

    /**
     * This method processes user shot
     * @param row
     * @param column
     * @return if the ship was hit
     */
    boolean shootAt(int row, int column) {
        int part = -1;

        if (row == bowRow && horizontal)
            part = column - bowColumn;
        else if (column == bowColumn && !horizontal)
            part = row - bowRow;
        else return false;

        if (part >= length || part < 0 || hit[part])
            return false;
        else
            return hit[part] = true;
    }

    /**
     * This method should be override.
     * Allows to clone ship-type object
     */
    @Override
    protected Object clone() {
        return new Ship();
    }

    /**
     * checks part in transferred coordinates for damage
     * @param row
     * @param column
     * @return true if destroyed, false otherwise
     */
    boolean isHited(int row, int column) {
        int part = -1;

        if (row == bowRow && horizontal)
            part = column - bowColumn;
        else if (column == bowColumn && !horizontal)
            part = row - bowRow;
        else return false;

        if (part >= length || part < 0)
            return false;
        else
            return hit[part];
    }
}
