package me.miao.gitcoin;

import android.app.ActivityManager;
import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;

import org.bitcoinj.crypto.LinuxSecureRandom;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.WalletFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

import me.miao.gitcoin.util.Bluetooth;

public class WalletApplication extends Application {
    private ActivityManager activityManager;

    private File walletFile;
    private WalletFiles walletFiles;
    private Configuration config;

    public static final String ACTION_WALLET_REFERENCE_CHANGED = WalletApplication.class.getPackage().getName() + ".wallet_reference_changed";
    public static final long time_create_application = System.currentTimeMillis();
    private static final String BIP39_WORDLIST_FILENAME = "bip39-wordlist.txt";

    private static final Logger log = LoggerFactory.getLogger(WalletApplication.class);

    private PackageInfo packageInfo;


    @Override
    public void onCreate() {
        super.onCreate();
        // 从 /dev/urandom 文件读取一个随机数
        new LinuxSecureRandom();
        Logging.init(getFilesDir());
        //启用严格模式， 设置线程监控策略：监控所有，排除磁盘读，写， 将日志添加到系统日志
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().permitDiskReads().permitDiskWrites().penaltyLog().build());

        //bitcoinj 某种策略设置
        Threading.throwOnLockCycles();
        org.bitcoinj.core.Context.enableStrictMode();
        // 传播？
        org.bitcoinj.core.Context.propagate(Constants.CONTEXT);

        log.info("=== starting app useing configuration: {}, {}", Constants.TEST ? "test" : "prod", Constants.NETWORK_PARAMETERS.getId());
        super.onCreate();

        final PackageInfo packageInfo = packageInfo();
        Threading.uncaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread t, Throwable e) {
                log.info("bitcoinj uncaught exception", e);
                // TODO: 7/5/2018  add CrashReporter to project
//                CrashReporter.saveBackgroundTrace(throwable, packageInfo);
            }
        };
        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        walletFile = getFileStreamPath(Constants.Files.WALLET_FILENAME_PROTOBUF);
        config = getConfiguration();
        config.updateLastVersionCode(packageInfo.versionCode);

        final BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            config.updateLastBluetoothAddress(Bluetooth.getAddress(bluetoothAdapter));
        }

        cleanupFiles();

        // TODO: 7/5/2018  添加通知功能
        //initNotificationManager();
    }

    private void cleanupFiles() {
        // fileList(): 返回此程序的所有私有文件名
        for (final String filename : fileList()) {
            if (filename.startsWith(Constants.Files.WALLET_KEY_BACKUP_BASE58) || filename.startsWith(Constants.Files.WALLET_FILENAME_PROTOBUF + '.') || filename.endsWith(".tmp")) {
                final File file = new File(getFilesDir(), filename);
                log.info("removing obsolete file: '{}'", file);
                file.delete();
            }
        }
    }

    public synchronized Configuration getConfiguration() {
        if (config == null) {
            config = new Configuration(PreferenceManager.getDefaultSharedPreferences(this), getResources());
        }
        return config;
    }

    public int maxConnectedPeers() {
        //低内存设备
        return activityManager.isLowRamDevice() ? 4 : 6;
    }


    public synchronized PackageInfo packageInfo() {
        if (packageInfo == null) {
            try {
                packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            } catch (PackageManager.NameNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return packageInfo;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
