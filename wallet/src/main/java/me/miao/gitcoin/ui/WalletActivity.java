package me.miao.gitcoin.ui;


import android.os.Bundle;
import android.view.Menu;

import me.miao.gitcoin.Configuration;
import me.miao.gitcoin.R;
import me.miao.gitcoin.WalletApplication;

public class WalletActivity extends AbstractWalletActivity {

    private WalletApplication application;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = getWalletApplication();
        final Configuration config = application.getConfiguration();

        setContentView(R.layout.wallet_content);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.wallet_options,menu);
        return true;
    }
}