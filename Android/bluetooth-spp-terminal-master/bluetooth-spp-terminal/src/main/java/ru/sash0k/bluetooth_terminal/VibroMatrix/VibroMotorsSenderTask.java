package ru.sash0k.bluetooth_terminal.VibroMatrix;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.Random;

import ru.sash0k.bluetooth_terminal.activity.DeviceControlActivity;
import ru.sash0k.bluetooth_terminal.activity.VibroMatrixActivity;
import ru.sash0k.bluetooth_terminal.bluetooth.DeviceConnector;

/**
 * Created by scb07 on 23.03.2017.
 */


public class VibroMotorsSenderTask extends AsyncTask<Void, SymbolPoint, Void>{
    byte[] message;
    Activity activity;
    DeviceConnector connector;

    public VibroMotorsSenderTask(byte[] message, Activity activity, DeviceConnector connector) {
        this.message= message;
        this.activity = activity;
        this.connector = connector;
    }

    @Override
    protected void onProgressUpdate(SymbolPoint... values) {
        super.onProgressUpdate(values);

        if(values[0].getX() == 100) {
            ((DeviceControlActivity) activity).matrixValue.clear();
        }else {
            ((DeviceControlActivity) activity).matrixValue.setValue(values[0].getX(), values[0].getY(), values[0].color);
        }
        ((DeviceControlActivity) activity).Invalidate();
//        Random r = new Random();
//        ((VibroMatrixActivity) activity).matrixValue.setValue(r.nextInt(8), r.nextInt(8), (byte) r.nextInt(3));
//        ((VibroMatrixActivity) activity).Invalidate();
    }

    @Override
    protected Void doInBackground(Void... params) {
        byte x, y, t;
        byte oldx = -1, oldy = -1;
        for(int i = 0; i < message.length; i+=3) {
            x = (byte) (message[i] - 1);
            y = (byte) (message[i + 1] - 1);
            t = message[i + 2];

            try {
                if(oldx != -1) {
                    publishProgress(new SymbolPoint(oldx, oldy, 1));
                }
                publishProgress(new SymbolPoint(x, y, 2));
                if(x != 100){
                    oldx = x;
                    oldy = y;
                }else{
                    oldx = -1;
                    oldy = -1;
                }
                if(connector != null)
                    connector.write(new byte[]{x, (byte) (6 - y), t});
                Thread.sleep(11*t);
            } catch (InterruptedException e) {
                return null;
            }
        }
//        final SymbolPoint[] points = PathSymbolGenerator.getPath('А');
//        for(int i = 0; i < points.length; i++) {
//            try {
//                Thread.sleep(100);
//
//                publishProgress(points[i]);
////                activity.runOnUiThread(new Runnable() {
////                    public void run() {
////
////                    }
////                });
//            } catch (InterruptedException e) {
//                return null;
//            }
//        }
        return null;
    }
}
