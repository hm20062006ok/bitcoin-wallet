package me.miao.gitcoin.ui;


import android.os.Bundle;

import me.miao.gitcoin.R;

public class WalletActivity extends AbstractWalletActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_content);
    }
}