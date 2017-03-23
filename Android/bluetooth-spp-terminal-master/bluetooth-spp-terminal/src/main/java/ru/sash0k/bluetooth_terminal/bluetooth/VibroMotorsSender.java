package ru.sash0k.bluetooth_terminal.bluetooth;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.util.Random;

import ru.sash0k.bluetooth_terminal.activity.VibroMatrixActivity;

/**
 * Created by scb07 on 23.03.2017.
 */


public class VibroMotorsSender extends AsyncTask<Void, Void, Void>{
    String message;
    Activity activity;

    public VibroMotorsSender(String message, Activity activity) {
        this.message= message;
        this.activity = activity;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);

        Random r = new Random();
        ((VibroMatrixActivity) activity).matrixValue.setValue(r.nextInt(8), r.nextInt(8), (byte) r.nextInt(3));
        ((VibroMatrixActivity) activity).Invalidate();
    }

    @Override
    protected Void doInBackground(Void... params) {
        for(int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);

                publishProgress();
//                activity.runOnUiThread(new Runnable() {
//                    public void run() {
//                        Random r = new Random();
//                        ((VibroMatrixActivity) activity).matrixValue.setValue(r.nextInt(8), r.nextInt(8), (byte) r.nextInt(3));
//                        ((VibroMatrixActivity) activity).Invalidate();
//                    }
//                });
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;
    }
}
