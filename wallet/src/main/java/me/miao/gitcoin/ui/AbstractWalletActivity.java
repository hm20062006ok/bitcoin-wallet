package me.miao.gitcoin.ui;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;
import android.view.WindowManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.miao.gitcoin.R;
import me.miao.gitcoin.WalletApplication;

public class AbstractWalletActivity extends FragmentActivity {

    private WalletApplication application;
    protected static final Logger log = LoggerFactory.getLogger(AbstractWalletActivity.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        application = (WalletApplication) getApplication();
        if (Build.VERSION.SDK_INT >=  Build.VERSION_CODES.LOLLIPOP) {
            setTaskDescription(new ActivityManager.TaskDescription(null,null, getResources().getColor(R.color.bg_action_bar)));
        }
        super.onCreate(savedInstanceState);
    }

    public WalletApplication getWalletApplication() {
        return application;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void reportFullyDrawn() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            super.reportFullyDrawn();
        } else {
            // work around bug in KitKat
            try {
                super.reportFullyDrawn();
            } catch (final SecurityException x) {
                // swallow
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void setShowWhenLocked(final boolean showWhenLocked) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1)
            super.setShowWhenLocked(showWhenLocked);
        else if (showWhenLocked)
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        else
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
    }
}
