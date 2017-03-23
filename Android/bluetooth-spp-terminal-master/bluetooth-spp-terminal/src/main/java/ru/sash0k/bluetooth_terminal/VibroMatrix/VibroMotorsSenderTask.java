package ru.sash0k.bluetooth_terminal.VibroMatrix;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.Random;

import ru.sash0k.bluetooth_terminal.activity.VibroMatrixActivity;

/**
 * Created by scb07 on 23.03.2017.
 */


public class VibroMotorsSenderTask extends AsyncTask<Void, Void, Void>{
    private final Object mLock = new Object();
    String message;
    Activity activity;

    public VibroMotorsSenderTask(String message, Activity activity) {
        this.message= message;
        this.activity = activity;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

//        Random r = new Random();
//        ((VibroMatrixActivity) activity).matrixValue.setValue(r.nextInt(8), r.nextInt(8), (byte) r.nextInt(3));
//        ((VibroMatrixActivity) activity).Invalidate();
    }

    @Override
    protected Void doInBackground(Void... params) {
        final SymbolPoint[] points = PathSymbolGenerator.getPath('А');
        for(int i = 0; i < points.length; i++) {
            try {
                Thread.sleep(100);

//                publishProgress();
                final SymbolPoint point = points[i];
                activity.runOnUiThread(new Runnable() {
                    public void run() {
                        ((VibroMatrixActivity) activity).matrixValue.setValue(point.getX(), point.getY(), (byte) 1);
                        ((VibroMatrixActivity) activity).Invalidate();
                    }
                });
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;
    }
}
