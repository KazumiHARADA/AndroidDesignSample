package com.android.kharada.designsample.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.kharada.designsample.R;

public class BottomSheetActivity extends ListItemActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_bottom_sheet;
    }

    @Override
    protected int getRootViewId() {
        return R.id.activity_bottom_sheet;
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
