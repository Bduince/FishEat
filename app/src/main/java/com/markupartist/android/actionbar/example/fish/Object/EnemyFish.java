package com.markupartist.android.actionbar.example.fish.Object;

import android.content.res.Resources;
import android.graphics.Canvas;

/**
 * Created by PC on 2016/11/8.
 */
public class EnemyFish extends GameObject{
    protected int score;
    protected int multiple =1;
    protected boolean isExplosion;   			 // 判断是否爆炸
    protected boolean isVisible;		 		 //	 对象是否为可见状态
    protected float scale = 1;

    public EnemyFish(Resources resources) {
        super(resources);
        // TODO Auto-generated constructor stub
        initBitmap();			// 初始化图片资源
    }
    @Override
    public void initial(int arg0,float arg1,float arg2){

    }
    // 初始化图片资源
    @Override
    public void initBitmap() {
        // TODO Auto-generated method stub

    }
    // 对象的绘图函数
    @Override
    public void drawSelf(Canvas canvas) {
        // TODO Auto-generated method stub

    }
    // 释放资源
    @Override
    public void release() {
        // TODO Auto-generated method stub

    }
    // 对象的逻辑函数
    @Override
    public void logic() {
        // TODO Auto-generated method stub

    }

    // 检测碰撞
    @Override
    public boolean isCollide(GameObject obj) {
        float a = 0.99f;
        // 矩形1位于矩形2的左侧
        if (object_x <= obj.getObject_x()
                && object_x + object_width *a <= obj.getObject_x()) {
            return false;
        }
        // 矩形1位于矩形2的右侧
        else if (obj.getObject_x() <= object_x
                && obj.getObject_x() + obj.getObject_width() *a<= object_x) {
            return false;
        }
        // 矩形1位于矩形2的上方
        else if (object_y <= obj.getObject_y()
                && object_y + object_height *a<= obj.getObject_y()) {
            return false;
        }
        // 矩形1位于矩形2的下方
        else if (obj.getObject_y() <= object_y
                && obj.getObject_y() + obj.getObject_height()*a <= object_y) {
            return false;
        }
        return true;
    }
    // 判断能否被检测碰撞
    public boolean isCanCollide() {
        // TODO Auto-generated method stub
        return isAlive && !isExplosion && isVisible;
    }
    //getter和setter方法
    public int getScore() {
        // TODO Auto-generated method stub
        return score;
    }
    public void setScore(int score) {
        // TODO Auto-generated method stub
        this.score = score;
    }
    public boolean isExplosion() {
        // TODO Auto-generated method stub
        return isExplosion;
    }
}
