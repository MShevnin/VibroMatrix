package ru.sash0k.bluetooth_terminal.VibroMatrix;

public class PathSymbolGenerator {
    static Symbol[] symbols = {
            new Symbol('А', new SymbolPoint[]{
                    new SymbolPoint(0, 0),
                    new SymbolPoint(0, 1),
                    new SymbolPoint(0, 2),
                    new SymbolPoint(1, 3),
                    new SymbolPoint(1, 4),
                    new SymbolPoint(2, 5),
                    new SymbolPoint(2, 6),
                    new SymbolPoint(3, 7),
                    new SymbolPoint(4, 7),
                    new SymbolPoint(5, 6),
                    new SymbolPoint(5, 5),
                    new SymbolPoint(6, 4),
                    new SymbolPoint(6, 3),
                    new SymbolPoint(7, 2),
                    new SymbolPoint(7, 1),
                    new SymbolPoint(7, 0),
                    new SymbolPoint(1, 3),
                    new SymbolPoint(2, 3),
                    new SymbolPoint(3, 3),
                    new SymbolPoint(4, 3),
                    new SymbolPoint(5, 3),
                    new SymbolPoint(6, 3)
            }),
            new Symbol('Б', new SymbolPoint[]{
                    new SymbolPoint(0, 0),
                    new SymbolPoint(1, 1)}
            ),
            new Symbol('В', new SymbolPoint[]{
                    new SymbolPoint(0, 0),
                    new SymbolPoint(1, 1)}
            ),
            new Symbol('Г', new SymbolPoint[]{
                    new SymbolPoint(0, 0),
                    new SymbolPoint(1, 1)}
            ),
            new Symbol('Д', new SymbolPoint[]{
                    new SymbolPoint(0, 0),
                    new SymbolPoint(1, 1)}
            ),
    };

    static public SymbolPoint[] getPath(char leter){
        for(int i = 0; i < symbols.length; i++){
            if(Character.toUpperCase(symbols[i].getLeter()) == Character.toUpperCase(leter))
                return symbols[i].getPoint();
        }
        return null;
    }
}
