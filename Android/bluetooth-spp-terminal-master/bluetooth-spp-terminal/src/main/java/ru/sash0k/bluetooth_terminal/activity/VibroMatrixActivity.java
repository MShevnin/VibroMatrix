package ru.sash0k.bluetooth_terminal.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import ru.sash0k.bluetooth_terminal.Matrix;
import ru.sash0k.bluetooth_terminal.VibroMatrix.VibroMotorsSenderTask;

public class VibroMatrixActivity extends Activity {

    public Matrix matrixValue = new Matrix();
    private DrawView view;
    static VibroMotorsSenderTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vibro_matrix);
        view = new DrawView(this);
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

    class DrawView extends View {

        public DrawView(Context context) {
            super(context);
        }


        @Override
        protected void onDraw(Canvas canvas) {
            canvas.drawColor(Color.BLACK);

            Paint fontPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

            Paint grayPaint = new Paint();
            grayPaint.setColor(Color.GRAY);

            Paint greenPaint = new Paint();
            greenPaint.setColor(Color.GREEN);

            Paint redPaint = new Paint();
            redPaint.setColor(Color.RED);

            Paint curPaint = grayPaint;
            int fontSize = 30;

            int matrixSize = canvas.getWidth();

            Matrix matrix = ((VibroMatrixActivity) getContext()).matrixValue;

            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    switch (matrix.getValue(i, j)){
                        case 0:
                            curPaint = grayPaint;
                            break;
                        case 1:
                            curPaint = redPaint;
                            break;
                        case 2:
                            curPaint = greenPaint;
                            break;
                    }
                    canvas.drawCircle(i*matrixSize/8+matrixSize/16, matrixSize - j*matrixSize/8+matrixSize/16, matrixSize/17, curPaint);
                }
            }

            fontPaint.setTextSize(fontSize);

            canvas.drawText(String.valueOf(canvas.getWidth()), 50, 50, fontPaint);
        }

    }
}
