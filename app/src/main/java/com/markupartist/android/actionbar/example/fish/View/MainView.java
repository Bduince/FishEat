package com.markupartist.android.actionbar.example.fish.View;

import com.markupartist.android.actionbar.example.fish.ConstantUtil;
import com.markupartist.android.actionbar.example.fish.Object.BigFish;
import com.markupartist.android.actionbar.example.fish.Object.BigFishRight;
import com.markupartist.android.actionbar.example.fish.Object.MiddleFish;
import com.markupartist.android.actionbar.example.fish.Object.MiddleFishRight;
import com.markupartist.android.actionbar.example.fish.Object.MyFish;
import com.markupartist.android.actionbar.example.fish.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.os.Message;
import com.markupartist.android.actionbar.example.fish.Factory.GameObjectFactory;
import com.markupartist.android.actionbar.example.fish.Object.EnemyFish;
import com.markupartist.android.actionbar.example.fish.Object.GameObject;
import com.markupartist.android.actionbar.example.fish.Object.SmallFish;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/11/8.
 */
public class MainView extends BaseView {
    private boolean isPlay;			// 标记游戏运行状态
    private int speedTime;			// 游戏速度的倍数
    private List<EnemyFish> enemyFish;
    private GameObjectFactory factory;
    private MyFish myFish;		// 玩家

    private Bitmap background; 		// 背景图片

    private Bitmap playButton; 		// 开始/暂停游戏的按钮图片
    private Bitmap timeButton;
    private float bg_y;				// 图片的坐标

    private float play_bt_w;
    private float play_bt_h;

    private float time_w;
    private float time_h;
    private long skillTime;
    private float lastX = 0;
    private float lastY = 0;
    private int sumScore;			// 游戏总得分
    private boolean isTime;
    private  boolean isTouchPlane;
    
    public MainView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        isPlay = true;
        isTime = true;
        speedTime = 3;
        factory = new GameObjectFactory();						  //工厂类

        enemyFish = new ArrayList<EnemyFish>();
        //myfish
        myFish = (MyFish) factory.createMyFish(getResources());//生产玩家
        //小型size = 1
        for(int i = 0; i < SmallFish.sumCount; i++){

            SmallFish smallPlane = (SmallFish) factory.createSmallPlane(getResources());

            enemyFish.add(smallPlane);
        }
        for(int i = 0; i < MyFish.RightFish.sumCount; ++i){
            MyFish.RightFish rightFish = (MyFish.RightFish) factory.createRightFish(getResources());
            enemyFish.add(rightFish);
        }
        for(int i = 0; i < MiddleFish.sumCount; ++i){
            MiddleFish middleFish = (MiddleFish) factory.createMiddleFish(getResources());
            enemyFish.add(middleFish);
        }
        for(int i = 0; i < MiddleFishRight.sumCount; ++i){
            MiddleFishRight middleFishRight = (MiddleFishRight) factory.createMiddleFishRight(getResources());
            enemyFish.add(middleFishRight);
        }
        for(int i = 0; i < BigFish.sumCount; ++i){
            BigFish bigFish = (BigFish) factory.createBigFish(getResources());
            enemyFish.add(bigFish);
        }
        for(int i = 0; i < BigFishRight.sumCount; ++i){
            BigFishRight bigFishRight = (BigFishRight) factory.createBigFishRight(getResources());
            enemyFish.add(bigFishRight);
        }
        thread = new Thread(this);//开启线程
    }
    // 视图改变的方法
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
        super.surfaceChanged(arg0, arg1, arg2, arg3);
    }
    // 视图创建的方法
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        super.surfaceCreated(arg0);
        initBitmap(); // 初始化图片资源
        for(GameObject obj:enemyFish){
            obj.setScreenWH(screen_width,screen_height);
        }
        myFish.setScreenWH(screen_width,screen_height);
        myFish.setAlive(true);
        if(thread.isAlive()){
            thread.start();
        }
        else{
            thread = new Thread(this);
            thread.start();
        }
    }
    // 视图销毁的方法
    @Override
    public void surfaceDestroyed(SurfaceHolder arg0) {
        // TODO Auto-generated method stub
        super.surfaceDestroyed(arg0);
        release();
    }
    // 初始化图片资源方法
    @Override
    public void initBitmap() {
        // TODO Auto-generated method stub
        playButton = BitmapFactory.decodeResource(getResources(),R.drawable.play);
        background = BitmapFactory.decodeResource(getResources(), R.drawable.bg_02);
        timeButton = BitmapFactory.decodeResource(getResources(),R.drawable.time);
        scalex = screen_width / background.getWidth();
        scaley = screen_height / background.getHeight();
        play_bt_w = playButton.getWidth();
        play_bt_h = playButton.getHeight()/2;
        time_w = timeButton.getWidth();
        time_h = timeButton.getHeight()/2;
        bg_y = 0;


    }
    //初始化游戏对象
    public void initObject(){
        for(EnemyFish obj:enemyFish){
            //初始化小型敌机
            if(obj instanceof SmallFish){
                if(!obj.isAlive()){
                    obj.initial(speedTime,0,0);
                    break;
                }
            }
            //初始化中型敌机
            else if(obj instanceof MyFish.RightFish){
                if(!obj.isAlive()){
                    obj.initial(speedTime,0,0);
                    break;
                }
             }
            else if(obj instanceof MiddleFish){
                if(!obj.isAlive()){
                    obj.initial(speedTime,0,0);
                    break;
                }
            }
            else if(obj instanceof MiddleFishRight){
                if(!obj.isAlive()){
                    obj.initial(speedTime,0,0);
                    break;
                }
            }
            else if(obj instanceof BigFish){
                if(!obj.isAlive()){
                    obj.initial(speedTime,0,0);
                    break;
                }
            }
            else if(obj instanceof BigFishRight){
                if(!obj.isAlive()){
                    obj.initial(speedTime,0,0);
                    break;
                }
            }
  //          obj.setSpeed(obj.getSpeed()/spdtm);
        }
        //提升等级
        if(sumScore >= speedTime*1000 ){
            speedTime++;
        }
    }
    // 释放图片资源的方法
    @Override
    public void release() {
        // TODO Auto-generated method stub
        for(GameObject obj:enemyFish){
            obj.release();
        }
        myFish.release();
        if(!playButton.isRecycled()){
            playButton.recycle();
        }
        if(!background.isRecycled()){
            background.recycle();
        }
        if(!timeButton.isRecycled()){
            timeButton.recycle();
        }
    }
    // 绘图方法
    @Override
    public void drawSelf() {
        // TODO Auto-generated method stub
        try {
            canvas = sfh.lockCanvas();
            canvas.drawColor(Color.BLACK); // 绘制背景色
            canvas.save();
            // 计算背景图片与屏幕的比例
            canvas.scale(scalex, scaley, 0, 0);
            canvas.drawBitmap(background, 0, bg_y, paint);   // 绘制背景图
            canvas.restore();
            //绘制按钮
            canvas.save();
            canvas.clipRect(10, 10, screen_width,10 + play_bt_h);
            if(isPlay){
                canvas.drawBitmap(playButton, 10, 10, paint);
            }
            else{
                canvas.drawBitmap(playButton, 10, 10 - play_bt_h, paint);
            }
            if(isTime){
                canvas.drawBitmap(timeButton,screen_width/2+play_bt_w, 0,paint);
            }else{
                canvas.drawBitmap(timeButton,screen_width/2+play_bt_w, 0-time_h,paint);
            }

            canvas.restore();

            //绘制敌机
            for(EnemyFish obj:enemyFish){
                if(obj.isAlive()){
                    obj.drawSelf(canvas);
                    //检测敌机是否与玩家的飞机碰撞
                    if(obj.isCanCollide() && myFish.isAlive()){
                        if(obj.isCollide(myFish)){
                            if(obj.getSize() >= myFish.getSize()/10){
                                myFish.setAlive(false);
                            }
                          else if(obj.getSize() < myFish.getSize()/10){
                                obj.setAlive(false);
                                myFish.incSize();
                                //图片变大

                                addGameScore(obj.getScore());
                            }
                        }
                    }
                }
            }
            if(!myFish.isAlive()){
                threadFlag = false;
            }
            myFish.drawSelf(canvas);	//绘制玩家的飞机


            //绘制积分文字
            paint.setTextSize(30);
            paint.setColor(Color.rgb(235, 161, 1));
            canvas.drawText("积分:"+String.valueOf(sumScore), 30 + play_bt_w, 40, paint);		//绘制文字
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (canvas != null)
                sfh.unlockCanvasAndPost(canvas);
        }
    }
    // 线程运行的方法
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (threadFlag) {
            long startTime = System.currentTimeMillis();
            initObject();
            if(lastX!=0&&lastY!=0){
                if(myFish.getMiddle_x()!=lastX||myFish.getMiddle_y()!=lastY){
                    move(lastX,lastY);
                }
            }
            drawSelf();
            long endTime = System.currentTimeMillis();
            if(endTime - skillTime > 5000){
                isTime = true;
                speedTime = 3;
            }
            if(!isPlay){
                synchronized (thread) {
                    try {
                        thread.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                if (endTime - startTime < 50)
                    Thread.sleep(50 - (endTime - startTime));
            } catch (InterruptedException err) {
                err.printStackTrace();
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Message message = new Message();
        message.what = 	ConstantUtil.TO_END_VIEW;
        message.arg1 = Integer.valueOf(sumScore);
        mainActivity.getHandler().sendMessage(message);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP){
            isTouchPlane = false;
            return true;
        }
        else if(event.getAction() == MotionEvent.ACTION_DOWN){
            float x = event.getX();
            float y = event.getY();
            lastX =x;
            lastY = y;
            if(x< myFish.getMiddle_x()){
                //向左移动
                myFish.setAve(1);
            }else  if(x>myFish.getMiddle_x()){
                //向右移动
                myFish.setAve(0);
            }
           // if(x)
            if(x > 10 && x < 10 + play_bt_w && y > 10 && y < 10 + play_bt_h){
                if(isPlay){
                    isPlay = false;
                }
                else{
                    isPlay = true;
                    synchronized(thread){
                        thread.notify();
                    }
                }
                return true;
            }
            else if(isTime==true&&x > screen_width/2+play_bt_w && x < screen_width/2+play_bt_w+time_w &&  y < 10 + time_h){
                speedTime =1;
                isTime = false;
                skillTime = System.currentTimeMillis();
               return true;
            }
        }
        //响应手指在屏幕移动的事件
        else if(event.getAction() == MotionEvent.ACTION_MOVE && event.getPointerCount() == 1){
            //判断触摸点是否为玩家的飞机
            float v = (float)myFish.getSpeed();
            float x = event.getX();
            float y = event.getY();
            lastX = 0;
            lastY = 0;
            if(x > myFish.getMiddle_x() + 20){
                myFish.setAve(0);
                if(myFish.getMiddle_x() + v <= screen_width){
                    myFish.setMiddle_x(myFish.getMiddle_x() + v);
                }
            }
            else if(x < myFish.getMiddle_x() - 20){
                myFish.setAve(1);
                if(myFish.getMiddle_x() - v >= 0){
                    myFish.setMiddle_x(myFish.getMiddle_x() - v);
                }
            }
            if(y > myFish.getMiddle_y() + 20){
                if(myFish.getMiddle_y() + v <= screen_height){
                    myFish.setMiddle_y(myFish.getMiddle_y() + v);
                }
            }
            else if(y < myFish.getMiddle_y() - 20){
                if(myFish.getMiddle_y() - v >= 0){
                    myFish.setMiddle_y(myFish.getMiddle_y() - v);
                }
            }
            return true;
        }
        return false;
    }
    public void addGameScore(int score){
        sumScore += score;			// 游戏总得分
    }
    public void move(float x, float y){
        float xl = x - myFish.getMiddle_x();//x方向位移
        float yl = y - myFish.getMiddle_y();//y方向位移
        float vx;
        float vy;
        if(Math.abs(xl)<10||Math.abs(yl)<10){
            vx = myFish.getSpeed();
            vy = myFish.getSpeed();
        }else{
            double tg = Math.abs(Math.atan(yl/xl));
            myFish.setTg((float)Math.toDegrees(tg));
            int piece = (int)Math.sqrt(xl*xl + yl*yl)/myFish.getSpeed();
            vx = Math.abs(xl/piece);
            vy = Math.abs(yl/piece);
        }

       //右
        if(myFish.getMiddle_x() < x){
            myFish.setMiddle_x(myFish.getMiddle_x() + vx);
            if(myFish.getMiddle_x() > x){
                myFish.setMiddle_x(x);
            }
        }
        //左
        else if (myFish.getMiddle_x() > x){
            myFish.setMiddle_x(myFish.getMiddle_x() - vx);
            if(myFish.getMiddle_x() < x){
                myFish.setMiddle_x(x);
            }
        }
        //上
        if(myFish.getMiddle_y() < y){
            myFish.setMiddle_y(myFish.getMiddle_y() + vy);
            if(myFish.getMiddle_y() > y){
                myFish.setMiddle_y(y);
            }
        }
        else if(myFish.getMiddle_y() > y){
            myFish.setMiddle_y(myFish.getMiddle_y() - vy);
            if(myFish.getMiddle_y() < y){
                myFish.setMiddle_y(y);
            }
        }
    }
}

