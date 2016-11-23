package com.markupartist.android.actionbar.example.fish.Object;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.markupartist.android.actionbar.example.fish.R;

import java.util.Random;

/**
 * Created by PC on 2016/11/16.
 */
public class BigFishRight extends EnemyFish{
    private static int currentCount = 0;	 //	对象当前的数量
    private Bitmap smallPlane; // 对象图片
    public static int sumCount = 2;	 	 	 //	对象总的数量
    public  BigFishRight(Resources resources) {
        super(resources);
        // TODO Auto-generated constructor stub
        this.size = 8;
        this.score = 100 * size;		// 为对象设置分数
    }
    @Override
    public void initial(int arg0,float arg1,float arg2){
        isAlive = true;


        Random ran = new Random();
        speed = ran.nextInt(8) + 3 * arg0;
        object_y = ran.nextInt((int)(screen_height - object_height));
        object_x = screen_width + object_width* (currentCount * 2 + 1);
        currentCount++;
        if(currentCount >= sumCount){
            currentCount = 0;
        }
    }
    @Override
    public void initBitmap() {
        // TODO Auto-generated method stub
        smallPlane = BitmapFactory.decodeResource(resources, R.drawable.bigright);
        object_width = smallPlane.getWidth();			//获得每一帧位图的宽
        object_height = smallPlane.getHeight();		//获得每一帧位图的高
    }
    @Override
    public void logic() {
        // TODO Auto-generated method stub
        if(object_x > 0 ){
            object_x -= speed;
        }
        else {
            isAlive = false;
        }
        if(object_x + object_width > 0){
            isVisible = true;
        }
        else{
            isVisible = false;
        }
    }
    @Override
    public void drawSelf(Canvas canvas) {
        // TODO Auto-generated method stub
        //判断敌机是否死亡状态
        if(isAlive){
            //判断敌机是否为爆炸状态
            if(!isExplosion){
                if(isVisible){
                    canvas.save();
                    canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
                    canvas.drawBitmap(smallPlane, object_x, object_y,paint);
                    canvas.restore();
                }
                logic();
            }
            else{
                int y = (int) (currentFrame * object_height); // 获得当前帧相对于位图的Y坐标
                canvas.save();
                canvas.clipRect(object_x,object_y,object_x + object_width,object_y + object_height);
                canvas.drawBitmap(smallPlane, object_x, object_y - y,paint);
                canvas.restore();

                currentFrame++;
                if(currentFrame >= 3){
                    currentFrame = 0;
                    isExplosion = false;
                    isAlive = false;
                }
            }
        }
    }
    @Override
    public void release() {
        // TODO Auto-generated method stub
        if(!smallPlane.isRecycled()){
            smallPlane.recycle();
        }
    }
}
