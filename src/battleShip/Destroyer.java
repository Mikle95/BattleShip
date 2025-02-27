package battleShip;

/**
 * 2 parts ship class
 */
public class Destroyer extends Ship {

    public Destroyer() {
        length = 2;
        hit[0] = hit[1] = false;
        hit[2] = hit[3] = true;
    }

    @Override
    String getShipType() {
        return "destroyer";
    }

    @Override
    protected Object clone() {
        Ship a = new Destroyer();
        a.setBowColumn(getBowColumn());
        a.setBowRow(getBowRow());
        a.setHorizontal(isHorizontal());
        return a;
    }
}
