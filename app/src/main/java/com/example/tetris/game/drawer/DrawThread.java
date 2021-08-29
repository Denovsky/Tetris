package com.example.tetris.game.drawer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.tetris.GamesObjects.DrawField;
import com.example.tetris.GamesObjects.UpdateFigure;
import com.example.tetris.game.HandlerOfObjects;

import java.util.ArrayList;

public class DrawThread extends Thread {

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private int height, width, Y, X, blockSize, strokeWitch;

    private static HandlerOfObjects handlerOfObjects;
    private byte[][] field;

    public volatile boolean running = true; // flag to stop the thread

    public DrawThread(Paint paint, SurfaceHolder surfaceHolder, ArrayList<Integer> params) {
        this.paint = paint;
        this.surfaceHolder = surfaceHolder;

        blockSize = params.get(0);
        strokeWitch = params.get(1);
        width = params.get(2);
        height = params.get(3);

        X = width / blockSize;
        Y = height / blockSize;

        handlerOfObjects = new HandlerOfObjects();
    }

    public void requestStop() {         // метод, которым мы останавливаем поток
        running = false;
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            canvas = surfaceHolder.lockCanvas(); // we "capture" the canvas, block it so that there are no problems
            if (canvas != null) {
                try {
                    if (handlerOfObjects.getField() != null){
                        field = handlerOfObjects.getField();
                        drawField();
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

    public void drawField() {
        int blackBorderY = (height - (Y * blockSize)) / 2;
        int blackBorderX = (width - (X * blockSize)) / 2;
        paint.setStrokeWidth(strokeWitch);

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {

                int left = blackBorderX + (j * blockSize);
                int top = blackBorderY + (i * blockSize);
                int right = blackBorderX + (j * blockSize) + blockSize;
                int bottom = blackBorderY + (i * blockSize) + blockSize;

                if (field[i][j] == 0) {
                    paint.setColor(Color.rgb(131, 143, 111));
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(left, top, right, bottom, paint);

                    paint.setColor(Color.rgb(150, 161, 125));
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(left, top, right, bottom, paint);
                } else if (field[i][j] == 1) {
                    paint.setColor(Color.rgb(0, 0, 0));
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(left, top, right, bottom, paint);

                    paint.setColor(Color.rgb(150, 161, 125));
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(left, top, right, bottom, paint);
                } else if (field[i][j] == 2) {
                    paint.setColor(Color.rgb(0, 0, 255));
                    paint.setStyle(Paint.Style.FILL);
                    canvas.drawRect(left, top, right, bottom, paint);

                    paint.setColor(Color.rgb(150, 161, 125));
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(left, top, right, bottom, paint);
                }
            }
        }
    }
}
