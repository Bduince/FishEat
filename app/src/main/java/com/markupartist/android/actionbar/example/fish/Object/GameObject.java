package com.markupartist.android.actionbar.example.fish.Object;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by PC on 2016/11/8.
 */
abstract public class GameObject {
    protected Paint paint; 			// 画笔对象
    protected Resources resources;  // 资源类

    protected float screen_width; 	// 屏幕的宽度
    protected float screen_height;  // 屏幕的高度

    protected int speed;
    protected float object_x;
    protected float object_y;
    protected float object_width;
    protected float object_height;
    protected boolean isAlive;

    protected int currentFrame;

    protected int size;

    public GameObject(Resources resources) {
        this.resources = resources;
        this.paint = new Paint();
    }
    // 设置屏幕宽度和高度
    public void setScreenWH(float screen_width, float screen_height) {
        this.screen_width = screen_width;
        this.screen_height = screen_height;
    }
    // 初始化数据//参数分别为:速度增加的倍数,x中心坐标,y中心坐标,
    public void initial(int arg0,float arg1,float arg2){}
    // 初始化图片资源的
    protected abstract void initBitmap();
    public abstract void drawSelf(Canvas canvas);
    public abstract void release();
    public boolean isCollide(GameObject obj) {
        return true;
    }
    public void logic(){}
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public float getObject_x() {
        return object_x;
    }
    public void setObject_x(float object_x) {
        this.object_x = object_x;
    }
    public float getObject_y() {
        return object_y;
    }
    public void setObject_y(float object_y) {
        this.object_y = object_y;
    }
    public float getObject_width() {
        return object_width;
    }
    public void setObject_width(float object_width) {
        this.object_width = object_width;
    }
    public float getObject_height() {
        return object_height;
    }
    public void setObject_height(float object_height) {
        this.object_height = object_height;
    }
    public boolean isAlive() {
        return isAlive;
    }
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    public void setSize(int size){this.size = size;}
    public int getSize(){return size;}
    public void incSize(){size++; }

}
