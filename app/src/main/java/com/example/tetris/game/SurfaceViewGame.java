package com.example.tetris.game;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SurfaceViewGame extends SurfaceView implements SurfaceHolder.Callback {

    private HandlerOfObjects handlerOfObjects = null;

    public SurfaceViewGame(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        ArrayList<Integer> params = new ArrayList<>();
        params.add(80);
        params.add(10);
        handlerOfObjects = new HandlerOfObjects(getContext(), getHolder(), params);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
