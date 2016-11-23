package com.markupartist.android.actionbar.example.fish.Object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.markupartist.android.actionbar.example.fish.Factory.GameObjectFactory;
import com.markupartist.android.actionbar.example.fish.R;
import com.markupartist.android.actionbar.example.fish.interfaces.Interface;

import java.util.Random;

/**
 * Created by PC on 2016/11/14.
 */
public class MyFish extends GameObject implements Interface{

    private GameObjectFactory factory;
    private float tg;
    private float middle_x;// 中心坐标
    private float middle_y;
    private int time =1;
    private int ave = 1;//0右,1左
    private float scale =0.5f;
    private Bitmap myfish;			 // 鱼的图片

    private long startTime;	 	 	 // 开始的时间

    public MyFish(Resources resources) {
        super(resources);
        // TODO Auto-generated constructor stub
        initBitmap();
        this.speed = 15;
        this.setSize(21);
        factory = new GameObjectFactory();

    }
    // 设置屏幕宽度和高度
    @Override
    public void setScreenWH(float screen_width, float screen_height) {
        super.setScreenWH(screen_width, screen_height);
        object_x = screen_width/2 - object_width/2;
        object_y = screen_height/2 - object_height*scale;
        middle_x = object_x + object_width/2;
        middle_y = object_y + object_height/2;
    }
    // 初始化图片资源的
    @Override
    public void initBitmap() {
        // TODO Auto-generated method stub
        myfish = BitmapFactory.decodeResource(resources, R.drawable.me);

        object_width = myfish.getWidth()  / 2; // 获得每一帧位图的宽
        object_height = myfish.getHeight(); 	// 获得每一帧位图的高
    }
    // 对象的绘图方法
    @Override
    public void drawSelf(Canvas canvas) {
        // TODO Auto-generated method stub
        if(isAlive){
            int x = (int) (currentFrame * object_width); // 获得当前帧相对于位图的X坐标
 //           if((size/10)>((size-1)/10)&&time==1){
            if(time==1){
                if(size==50||size==90){
                    scale += 0.2;
                    object_x += object_width -object_x*scale;
                    object_y += object_height -object_y*scale;
                    time =0;
                }

            }
            else if((size/10)==((size-1)/10)&&time!=1){
                time =1;
            }
            canvas.scale(scale,scale,middle_x,middle_y);
            canvas.clipRect(object_x, object_y, object_x + object_width, object_y + object_height);
            if(ave == 0){
                canvas.drawBitmap(myfish, object_x , object_y, paint);
            }else if(ave == 1){
                canvas.drawBitmap(myfish, object_x -object_width, object_y, paint);
            }
            canvas.rotate(tg);
            canvas.restore();
            currentFrame++;
            if (currentFrame >= 2) {
                currentFrame = 0;
            }
        }
    }
    // 释放资源的方法
    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!myfish.isRecycled()){
            myfish.recycle();
        }

    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    @Override
    public float getMiddle_x() {
        return middle_x;
    }
    @Override
    public void setMiddle_x(float middle_x) {
        this.middle_x = middle_x;
        this.object_x = middle_x - object_width/2;
    }
    @Override
    public float getMiddle_y() {
        return middle_y;
    }
    @Override
    public void setMiddle_y(float middle_y) {
        this.middle_y = middle_y;
        this.object_y = middle_y - object_height/2;
    }
    public float getScale(){return scale;}
    public void setAve(int i){this.ave = i;}
    public void setTg(float tg){this.tg = tg;}
}
