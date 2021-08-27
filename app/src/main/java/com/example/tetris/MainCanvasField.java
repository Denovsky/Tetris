package com.example.tetris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.tetris.GamesObjects.DrawField;
import com.example.tetris.GamesObjects.UpdateFigure;

import java.util.Arrays;

class MainCanvasField extends Thread {

    public Context context; // поле для управления приложением
    public Canvas canvas;
    public SurfaceHolder surfaceHolder;    // приватное поле для держателя канваса
    public volatile boolean running = true;    // флаг для остановки потока

    public byte[][] field;

    private DrawField drawField = null;
    private UpdateFigure updateFigure;

    public void requestStop() {         // метод, которым мы останавливаем поток
        running = false;
    }

    public void setField(byte[][] field) {
        this.field = field;
    }

    public MainCanvasField(Context context, SurfaceHolder surfaceHolder) { // конструктор
        this.context = context;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            canvas = surfaceHolder.lockCanvas();     // "захватываем" канвас, блокируем его, чтобы не было проблем
            if (canvas != null) {
                try {
                    Paint paint = new Paint();
                    if (drawField == null) {
                        drawField = new DrawField(canvas, paint, field, 80, 10); // third elem is size of block
                    }
                    if (!drawField.frstPlay) {
                        drawField.setField(field);
                    }
                    drawField.drawEndingField();

                    if (updateFigure == null) {
                        updateFigure = new UpdateFigure(drawField.getField());
                    }
                    updateFigure.updateLocation();
                    field = updateFigure.getField();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}