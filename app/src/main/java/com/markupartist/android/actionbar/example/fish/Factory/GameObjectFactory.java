package com.markupartist.android.actionbar.example.fish.Factory;

import android.content.res.Resources;

import com.markupartist.android.actionbar.example.fish.Object.BigFish;
import com.markupartist.android.actionbar.example.fish.Object.BigFishRight;
import com.markupartist.android.actionbar.example.fish.Object.GameObject;
import com.markupartist.android.actionbar.example.fish.Object.MiddleFish;
import com.markupartist.android.actionbar.example.fish.Object.MiddleFishRight;
import com.markupartist.android.actionbar.example.fish.Object.MyFish;
import com.markupartist.android.actionbar.example.fish.Object.SmallFish;

/**
 * Created by PC on 2016/11/8.
 */
public class GameObjectFactory {
    public GameObject createSmallPlane(Resources resources){
        return new SmallFish(resources);
    }

    //创建玩家飞机的方法
    public GameObject createMyFish(Resources resources){
        return new MyFish(resources);
    }
    public GameObject createMiddleFish(Resources resources){return new MiddleFish(resources);}
    public GameObject createMiddleFishRight(Resources resources){return new MiddleFishRight(resources);}
    public GameObject createBigFish(Resources resources){return new BigFish(resources);}
    public GameObject createBigFishRight(Resources resources){return new BigFishRight(resources);}
}
