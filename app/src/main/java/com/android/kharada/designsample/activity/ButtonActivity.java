package com.android.kharada.designsample.activity;

import android.os.Bundle;
import android.view.MenuItem;

import com.android.kharada.designsample.R;
import com.android.kharada.designsample.util.ActivityUtil;

/**
 * Created by kharada on 2017/02/22.
 */
public class ButtonActivity extends ListItemActivity {

    private ButtonPresenter mButtonPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButtonFragment buttonFragment = (ButtonFragment) getSupportFragmentManager()
                .findFragmentById(R.id.content_frame);

        if (buttonFragment == null) {
            buttonFragment = ButtonFragment.newInstance();

            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(),
                    buttonFragment, R.id.content_frame);
        }

        Bundle bundle = getIntent().getExtras();

        mButtonPresenter = new ButtonPresenter(bundle,buttonFragment);

    }

    @Override
    public void onResume() {
        super.onResume();
        mButtonPresenter.start();
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
    protected int getToolbarId() {
        return R.id.toolbar;
    }

    @Override
    protected int getHeaderImageId() {
        return R.drawable.default_image;
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