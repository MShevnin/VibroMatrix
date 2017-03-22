package ru.sash0k.bluetooth_terminal.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import ru.sash0k.bluetooth_terminal.Matrix;
import ru.sash0k.bluetooth_terminal.R;

public class VibroMatrixActivity extends Activity {

    public Matrix matrixValue = new Matrix();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_vibro_matrix);
        setContentView(new DrawView(this));
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

            int fontSize = 30;

            int matrixSize = canvas.getWidth();

            Matrix matrix = ((VibroMatrixActivity) getContext()).matrixValue;
            matrix.clear();
            matrix.setValue(2,2, (byte) 1);
            matrix.setValue(2,3, (byte) 2);

            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    switch (matrix.getValue(i, j)){
                        case 0:
                            canvas.drawCircle(i*matrixSize/8+matrixSize/16, j*matrixSize/8+matrixSize/16, matrixSize/17, grayPaint);
                            break;
                        case 1:
                            canvas.drawCircle(i*matrixSize/8+matrixSize/16, j*matrixSize/8+matrixSize/16, matrixSize/17, redPaint);
                            break;
                        case 2:
                            canvas.drawCircle(i*matrixSize/8+matrixSize/16, j*matrixSize/8+matrixSize/16, matrixSize/17, greenPaint);
                            break;
                    }
                }
            }

            fontPaint.setTextSize(fontSize);

            canvas.drawText(String.valueOf(canvas.getWidth()), 50, 50, fontPaint);
        }

    }
}
