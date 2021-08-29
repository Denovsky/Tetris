package com.example.tetris.game.logics;

import com.example.tetris.game.HandlerOfObjects;

import java.util.TimerTask;

public class UpdatePerSec extends TimerTask {

    private static HandlerOfObjects handlerOfObjects;

    private byte[][] field;

    private boolean figureStillMove = false;

    public UpdatePerSec() {
        handlerOfObjects = new HandlerOfObjects();
    }

    @Override
    public void run() {
        if (handlerOfObjects.getField() != null) {
            field = handlerOfObjects.getField();
            if (!figureStillMove) {
                createFigure();
            } else {
                updateFigure();
            }
            handlerOfObjects.setField(field);
        }
    }

    private void createFigure() {
        switch ((int) (Math.random() * 2)) {
            case 0:
                field[2][2] = 2;
                field[2][1] = 2;
                field[1][2] = 2;
                field[1][1] = 2;
                break;
            case 1:
                field[4][1] = 2;
                field[3][1] = 2;
                field[2][1] = 2;
                field[1][1] = 2;
                break;
        }
        figureStillMove = true;
    }

    private void updateFigure() {
        for (int i = field.length - 1; i > 0; i--) {
            int figure = 0;
            int underFigure = 0;
            for (int j = field[0].length - 1; j > 0; j--) { // detects the fall of the figure
                if (field[i][j] == 2 && field[i + 1][j] != 1) { // if under figure nothing
                    figure++;
                } else if (field[i][j] != 1 && field[i + 1][j] == 1) { // if under figure is border
                    figureStillMove = false;
                }
                if (field[i][j] == 2 && field[i + 1][j] == 2) { // if under figure another figure
                    underFigure++;
                }
            }
//            if (index == field[0].length - 3 && figureStillMove) { // if under figure nothing, it change position
//                for (int j = field[0].length - 1; j > 0; j--) {
//                    if (field[i][j] == 2 && field[i + 1][j] != 1) {
//                        field[i + 1][j] = field[i][j];
//                        field[i][j] = 0;
//                    } else if (field[i + 1][j] == 1) {
//                        figureStillMove = false;
//                    }
//                }
//            } else { // if under figure another figure, it doesn't change position
//                figureStillMove = false;
//            }
        }
    }

}
