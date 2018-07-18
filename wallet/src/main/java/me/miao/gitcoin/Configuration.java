package me.miao.gitcoin;

import android.content.SharedPreferences;
import android.content.res.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by HM on 7/5/2018.
 */

public class Configuration {
    private static final String PREFS_KEY_LAST_VERSION = "last_version";
    private static final String PREFS_KEY_LAST_BLUETOOTH_ADDRESS = "last_bluetooth_address";


    private final SharedPreferences prefs;
    private final Resources res;
    private final int lastVersionCode;
    private static final Logger log = LoggerFactory.getLogger(Configuration.class);

    public Configuration(final SharedPreferences prefs, final Resources res) {
        this.prefs = prefs;
        this.res = res;

        this.lastVersionCode = prefs.getInt(PREFS_KEY_LAST_VERSION, 0);
    }

    public void updateLastVersionCode(final int versionCode) {
        prefs.edit().putInt(PREFS_KEY_LAST_VERSION, versionCode).apply();
        if (versionCode > lastVersionCode) {
            log.info("detected app upgrade: " + lastVersionCode + " -> " + versionCode);
        }else if(versionCode < lastVersionCode){
            log.warn("detected app downgrade: " + lastVersionCode + " -> " + versionCode);
        }
    }

    public void updateLastBluetoothAddress(final String bluetoothAddress){
        if (bluetoothAddress != null) {
            prefs.edit().putString(PREFS_KEY_LAST_BLUETOOTH_ADDRESS, bluetoothAddress).apply();
        }
    }
}
