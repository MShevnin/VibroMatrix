package ru.sash0k.bluetooth_terminal.VibroMatrix;

/**
 * Created by admin on 23.03.2017.
 */

public class Symbol {
    SymbolPoint[] point;
    char leter;

    public Symbol(){
    }

    public Symbol(char leter, SymbolPoint[] point) {
        this.point = point;
        this.leter = leter;
    }

    public SymbolPoint[] getPoint() {
        return point;
    }

    public void setPoint(SymbolPoint[] point) {
        this.point = point;
    }

    public char getLeter() {
        return leter;
    }

    public void setLeter(char leter) {
        this.leter = leter;
    }
}
