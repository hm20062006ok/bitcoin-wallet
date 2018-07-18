package me.miao.gitcoin.util;


import android.bluetooth.BluetoothAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nullable;

/**
 * Created by HM on 7/5/2018.
 */

public class Bluetooth {
    private static final Logger log = LoggerFactory.getLogger(Bluetooth.class);

    /**
     * Android 6 以上会返回此Mac地址
     */
    private static final String MARSHMELLOW_FAKE_MAC = "02:00:00:00:00:00";

    /**
     * 使用反射获取 Android 6 以上的真实 MAC 地址
     *
     * @param adapter
     * @return
     */
    public static @Nullable
    String getAddress(final BluetoothAdapter adapter) {
        if (adapter == null) {
            return null;
        }
        final String address = adapter.getAddress();
        if (!MARSHMELLOW_FAKE_MAC.equals(address)) {
            return address;
        }
        try {
            final Field mServiceField = BluetoothAdapter.class.getDeclaredField("mService");
            mServiceField.setAccessible(true);
            final Object mService = mServiceField.get(adapter);
            if (mService == null) {
                return null;
            }
            return (String) mService.getClass().getMethod("getAddress").invoke(mService);
        } catch (InvocationTargetException e) {
            log.info("使用反射获取蓝牙MAC地址失败");
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
