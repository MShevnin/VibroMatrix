package ru.sash0k.bluetooth_terminal;

/**
 * Created by Admin on 23.03.2017.
 */

public class Matrix {
    byte [][] matrixValue = new byte[8][8];

    public byte getValue(int i, int j){
        return matrixValue[i][j];
    }
    public void setValue(int i, int j, byte v){
        matrixValue[i][j] = v;
    }

    public void clear(){
        for(int i=0; i<8; i++){
            for(int j=0; j<8; j++){
                matrixValue[i][j] = 0;
            }
        }
    }
}
