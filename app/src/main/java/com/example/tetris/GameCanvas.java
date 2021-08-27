package com.example.tetris;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GameCanvas extends SurfaceView implements SurfaceHolder.Callback {

    private MainCanvasField mainCanvasField; // поле для хранения экземпляра потока

    public GameCanvas(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        mainCanvasField = new MainCanvasField(getContext(), getHolder()); // создаем экземпляр потока, передаем туда контекст и холдер
        mainCanvasField.start();    // стартуем поток(запускаем метод run)
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        mainCanvasField.requestStop();  // кидаем запрос на остановку потока
        boolean retry = true;  // поднимаем флаг повтора попытки остановки
        while (retry) {     // циклим по флагу
            try {   // пробуем остановить поток
                mainCanvasField.join();  // попытка
                retry = false;  // если на предыдущем шаге не вылетает исключение, то опускаем флаг
            } catch (InterruptedException e) {
                //ловим исключение
            }
        }
    }
}
