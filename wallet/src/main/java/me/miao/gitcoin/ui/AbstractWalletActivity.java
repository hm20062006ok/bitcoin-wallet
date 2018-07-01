package me.miao.gitcoin.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import me.miao.gitcoin.WalletApplication;

public class AbstractWalletActivity extends FragmentActivity {

    private WalletApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        application = (WalletApplication) getApplication();
        super.onCreate(savedInstanceState);
    }

    public WalletApplication getWalletApplication() {
        return application;
    }
}
