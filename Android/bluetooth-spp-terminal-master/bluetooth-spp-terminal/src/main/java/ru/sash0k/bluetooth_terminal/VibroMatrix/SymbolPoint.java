package ru.sash0k.bluetooth_terminal.VibroMatrix;

/**
 * Created by admin on 23.03.2017.
 */

public class SymbolPoint{
    byte x, y;
    byte color = 0;

    public SymbolPoint(int xx, int yy, int c)
    {
        x = (byte) xx;
        y = (byte) yy;
        color = (byte) c;
    }

    public SymbolPoint(int xx, int yy)
    {
        x = (byte) xx;
        y = (byte) yy;
    }

    public SymbolPoint(byte xx, byte yy){
        x = xx;
        y = yy;
    }

    public byte getX() {
        return x;
    }

    public void setX(byte x) {
        this.x = x;
    }

    public byte getY() {
        return y;
    }

    public void setY(byte y) {
        this.y = y;
    }
}
