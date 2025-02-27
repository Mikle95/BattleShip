package battleShip;

/**
 * 4 parts ship class
 */
public class BattleShip extends Ship {

    public BattleShip() {
        length = 4;
        hit[0] = hit[1] = hit[2] = hit[3] = false;
    }

    @Override
    String getShipType() {
        return "battleShip";
    }

    @Override
    protected Object clone() {
        Ship a = new BattleShip();
        a.setBowColumn(getBowColumn());
        a.setBowRow(getBowRow());
        a.setHorizontal(isHorizontal());
        return a;
    }

}
