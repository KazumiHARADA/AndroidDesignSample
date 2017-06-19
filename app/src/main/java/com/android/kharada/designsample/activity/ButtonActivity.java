package com.android.kharada.designsample.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import com.android.kharada.designsample.R;
import com.android.kharada.designsample.service.HUD;
import com.android.kharada.designsample.util.ActivityUtil;

/**
 * Created by kharada on 2017/02/22.
 */
public class ButtonActivity extends Activity {

    private ButtonPresenter mButtonPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = null;
        if (!canDrawOverlay(getApplicationContext())) {
            intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${getPackageName()}"));
            this.startActivity(intent);
            finish();
        } else {
            Intent svc = new Intent(this, HUD.class);
            startService(svc);
            finish();
        }
    }

    private boolean canDrawOverlay(Context context) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        return Settings.canDrawOverlays(context);
    }
}