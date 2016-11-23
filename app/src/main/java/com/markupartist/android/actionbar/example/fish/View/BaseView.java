package com.markupartist.android.actionbar.example.fish.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.markupartist.android.actionbar.example.fish.MainActivity;

/**
 * Created by PC on 2016/11/6.
 */
public class BaseView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    protected MainActivity mainActivity;
    protected SurfaceHolder sfh;

    protected float scalex;					// 背景图片的缩放比例
    protected float scaley;
    protected float screen_width;
    protected float screen_height;
    protected boolean threadFlag;
    protected Paint paint;
    protected Canvas canvas;
    protected Thread thread;

    public BaseView(Context context){
        super(context);
        this.mainActivity = (MainActivity)context;
        sfh = this.getHolder();
        sfh.addCallback(this);
        paint = new Paint();
    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub

    }
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        screen_width = this.getWidth();
        screen_height = this.getHeight();
        threadFlag = true;
    }
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        threadFlag = false;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub

    }
    public void initBitmap() {}
    public void setThreadFlag(boolean threadFlag){
        this.threadFlag = threadFlag;
    }
    public void release() {}
    public void drawSelf() {}
}
