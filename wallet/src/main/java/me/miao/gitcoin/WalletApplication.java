package me.miao.gitcoin;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

public class WalletApplication extends Application {
    private ActivityManager activityManager;

    @Override
    public void onCreate() {
        super.onCreate();
        activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
    }

    public int maxConnectedPeers(){
        return  activityManager.isLowRamDevice() ? 4 : 6;
    }

}
