package ru.sash0k.bluetooth_terminal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import ru.sash0k.bluetooth_terminal.Matrix;
import ru.sash0k.bluetooth_terminal.R;
import ru.sash0k.bluetooth_terminal.activity.DeviceControlActivity;
import ru.sash0k.bluetooth_terminal.activity.VibroMatrixActivity;


public class DrawMatrixView extends View {

    public DrawMatrixView(Context context) {
        super(context);
    }

    public DrawMatrixView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawMatrixView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
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

        int matrixSize = canvas.getHeight();
        int width = canvas.getWidth();

        Matrix matrix = ((DeviceControlActivity) getContext()).matrixValue;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                switch (matrix.getValue(i, j)) {
                    case 0:
                        curPaint = grayPaint;
                        break;
                    case 1:
                        curPaint = greenPaint;
                        break;
                    case 2:
                        curPaint = redPaint;
                        break;
                }
                canvas.drawCircle((width - matrixSize)/2 +  i * matrixSize / 6 + matrixSize / 12,
                        matrixSize - j * matrixSize / 7 - matrixSize / 14, matrixSize / 17, curPaint);
            }
        }

        fontPaint.setTextSize(fontSize);

        canvas.drawText(String.valueOf(canvas.getWidth()), 50, 50, fontPaint);
    }
}
