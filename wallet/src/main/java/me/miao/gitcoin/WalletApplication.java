package me.miao.gitcoin;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import org.bitcoinj.crypto.LinuxSecureRandom;

public class WalletApplication extends Application {
    private ActivityManager activityManager;

    @Override
    public void onCreate() {
        super.onCreate();

        new LinuxSecureRandom();

        Logging.init(getFilesDir());
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
    }

    public int maxConnectedPeers() {
        //低内存设备
        return activityManager.isLowRamDevice() ? 4 : 6;
    }

}
