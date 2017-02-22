package com.android.kharada.designsample.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.kharada.designsample.R;

/**
 * Created by kharada on 2017/02/22.
 */
public class ButtonActivity extends ListItemActivity {
/*
Activity追加時にすること
1.ManifestにActivityを追加する。
<activity
            android:name=".activity.ButtonActivity"
            android:launchMode="singleTask"
            android:theme="@style/Theme.Transparent" />
            
2.assets/main_list.jsonに追記する
,{
    "title":"Default",
    "description":"デフォルトテキスト",
    "activityName":"com.android.kharada.designsample.activity.ButtonActivity"
}
*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_default;
    }

    @Override
    protected int getRootViewId() {
        return R.id.activity_default_root_view;
    }

    @Override
    protected int getHeaderImageId() {
        return R.id.backdrop;
    }

    @Override
    protected int getToolbarLayoutId() {
        return R.id.header;
    }

    @Override
    protected int getDescriptionId() {
        return R.id.overview_description;
    }

    @Override
    protected int getToolbarId() {
        return R.id.toolbar;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        boolean result = true;

        switch (id) {
            case android.R.id.home:
                finish();
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }

        return result;
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}