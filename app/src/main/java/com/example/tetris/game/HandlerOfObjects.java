package com.example.tetris.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import com.example.tetris.game.drawer.DrawThread;
import com.example.tetris.game.logics.Logic;
import com.example.tetris.game.logics.UpdatePerSec;

import java.util.ArrayList;
import java.util.Timer;

public class HandlerOfObjects {

    private Context context;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Integer> params; // have block size, stroke width, width and height

    // main holders of threads
    private Logic logic = null;
    private DrawThread drawThread = null;

    private static byte[][] field;

    public void setField(byte[][] field) {
        HandlerOfObjects.field = field;
    }

    public static byte[][] getField() {
        return field;
    }

    public HandlerOfObjects() {

    }

    public HandlerOfObjects(Context context, SurfaceHolder surfaceHolder, ArrayList<Integer> params) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        this.params = params;

        distributeOfTasks();
    }

    public void distributeOfTasks() {
        setParams();

        Timer timer = new Timer();

        UpdatePerSec updatePerSec = new UpdatePerSec(); // Creates new figure and updates him location
        timer.schedule(updatePerSec, 1000, 1000);

        Paint paint = new Paint();
        drawThread = new DrawThread(paint, surfaceHolder, params); // Draw field. Just draw field. No more.
        drawThread.start();

        logic = new Logic(params); // Creates field and detect movement of user
    }

    private void setParams() {
        Canvas canvas = surfaceHolder.lockCanvas();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        params.add(width);
        params.add(height);
        surfaceHolder.unlockCanvasAndPost(canvas);
    }
}
