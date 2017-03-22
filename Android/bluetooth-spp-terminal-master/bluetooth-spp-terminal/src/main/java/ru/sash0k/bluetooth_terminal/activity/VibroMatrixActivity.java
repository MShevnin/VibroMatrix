package ru.sash0k.bluetooth_terminal.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import ru.sash0k.bluetooth_terminal.R;

public class VibroMatrixActivity extends Activity {

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

            Paint greenPaint = new Paint();
            greenPaint.setColor(Color.GREEN);

            int fontSize = 30;

            int matrixSize = canvas.getWidth();

            canvas.drawRect(0, 0, matrixSize, matrixSize,  greenPaint);


            fontPaint.setTextSize(fontSize);
            fontPaint.setStyle(Paint.Style.STROKE);

            canvas.drawText(String.valueOf(canvas.getWidth()), 50, 50, fontPaint);
        }

    }
}
