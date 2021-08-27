package com.example.tetris.GamesObjects;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Arrays;

public class DrawField {

    private Canvas canvas;
    private Paint paint;
    private int blockSize;
    private int strokeWitch;

    private int width, height;

    private int Y, X;
    public boolean frstPlay = true;

    private byte[][] field;

    public DrawField(Canvas canvas, Paint paint, byte[][]field, int blockSize, int strokeWitch) {
        width = canvas.getWidth();
        height = canvas.getHeight();

        this.canvas = canvas;
        this.paint = paint;
        this.blockSize = blockSize;
        this.strokeWitch = strokeWitch;
        this.field = field;

        Y = height / blockSize;
        X = width / blockSize;

        if (frstPlay) {
            drawStartingField();
        }
    }

    private void drawStartingField() {
        field = new byte[Y][X];
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (j == 0) { // borders
                    field[i][j] = 1;
                } else if (j == X - 1) {
                    field[i][j] = 1;
                } else if (i == 0) {
                    field[i][j] = 1;
                } else if (i == Y - 1) {
                    field[i][j] = 1;
                }
            }
        }
    }

    public void drawEndingField() {
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
                    paint.setColor(Color.rgb(131,143,111));
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
        frstPlay = false;
    }

    public byte[][] getField() {
        return field;
    }

    public void setField(byte[][] field) {
        this.field = field;
    }
}