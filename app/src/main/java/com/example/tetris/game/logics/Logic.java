package com.example.tetris.game.logics;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.tetris.game.HandlerOfObjects;

import java.util.ArrayList;

public class Logic {

    private static HandlerOfObjects handlerOfObjects = null;


    private int
            blockSize,
            width,
            height,
            Y,
            X;

    private byte[][] field;

    public byte[][] getField() {
        return field;
    }

    public void setField(byte[][] field) {
        this.field = field;
    }

    public Logic(ArrayList<Integer> params) {
        blockSize = params.get(0);
        width = params.get(2);
        height = params.get(3);

        X = width / blockSize;
        Y = height / blockSize;

        handlerOfObjects = new HandlerOfObjects();

        createField();
        handlerOfObjects.setField(field);
    }



    private void createField() {
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

    public void HandlerUserMovement() {

    }
}
