package ru.sash0k.bluetooth_terminal.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import ru.sash0k.bluetooth_terminal.Matrix;
import ru.sash0k.bluetooth_terminal.VibroMatrix.VibroMotorsSenderTask;
import ru.sash0k.bluetooth_terminal.view.DrawMatrixView;

public class VibroMatrixActivity extends Activity {

    public Matrix matrixValue = new Matrix();
    private DrawMatrixView view;
    static VibroMotorsSenderTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vibro_matrix);
        view = new DrawMatrixView(this);
        setContentView(view);

        matrixValue.clear();

        task = new VibroMotorsSenderTask("Test", this);
        task.execute((Void) null);
    }

    @Override

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        final VibroMotorsSenderTask curTask = task;//The final modifier indicates that the value of this field cannot change.

        if (curTask !=null && task.getStatus()== AsyncTask.Status.RUNNING) {

            outState.putBoolean("mCancelled", true);
            curTask.cancel(true);

        } else {
            outState.putBoolean("mCancelled", false);
        }
    }

    public void Invalidate() {
        view.invalidate();
    }

}
