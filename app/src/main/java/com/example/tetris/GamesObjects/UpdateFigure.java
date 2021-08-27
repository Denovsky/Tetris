package com.example.tetris.GamesObjects;

public class UpdateFigure {

    private byte[][] field;
    public boolean flag = true;

    public UpdateFigure(byte[][] field) {
        this.field = field;
        if (flag) {
            spawnOfFigure();
        }
    }

    public void spawnOfFigure() {
        flag = false;
        field[2][1] = 2;
        field[2][2] = 2;
        field[1][1] = 2;
        field[1][2] = 2;
    }

    public void updateLocation() {
        for (int i = field.length - 1; i > 0; i--) {
            for (int j = field[0].length - 1; j > 0; j--) {
                if (field[i][j] == 2) {
                    field[i][j] = field[i + 1][j];
                    field[i][j] = 0;
                }
            }
        }
    }

    public byte[][] getField() {
        return field;
    }

    public void setField(byte[][] field) {
        this.field = field;
    }
}
