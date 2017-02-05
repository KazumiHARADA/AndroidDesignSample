package com.android.kharada.designsample.activity;

import android.animation.Animator;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kharada.designsample.R;

/**
 * Created by haradakazumi on 2017/02/05.
 */

public abstract class ListItemActivity extends AppCompatActivity {

    protected View mRootView;
    protected CollapsingToolbarLayout mHeader;
    protected TextView mDescriptionText;
    protected ImageView mHeaderImage;
    protected Toolbar mToolbar;

    protected int mCircleX;
    protected int mCircleY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);

        mRootView = (View) findViewById(R.id.activity_bottom_sheet);
        mHeaderImage = (ImageView) findViewById(R.id.backdrop);
        mHeader = (CollapsingToolbarLayout) findViewById(R.id.header);
        mDescriptionText = (TextView) findViewById(R.id.overview_description);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();
        mHeader.setTitle(bundle.getString("title", ""));
        mDescriptionText.setText(bundle.getString("description", ""));
        mCircleX = bundle.getInt("circle_x");
        mCircleY = bundle.getInt("circle_y");

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if (savedInstanceState == null) {
            mRootView.setVisibility(View.INVISIBLE);
            ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity(mRootView, mCircleX, mCircleY);
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
                            mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        } else {
                            mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }
                });
            }
        }
    }

    private void circularRevealActivity(View rootLayout, int circleX, int circleY) {

        int cx = circleX;//rootLayout.getWidth() / 2;
        int cy = circleY;//rootLayout.getHeight() / 2;

        float finalRadius = Math.max(rootLayout.getWidth(), rootLayout.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator circularReveal = ViewAnimationUtils.createCircularReveal(rootLayout, cx, cy, 0, finalRadius);
        circularReveal.setDuration(500);

        // make the view visible and start the animation
        rootLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    protected abstract int getLayoutResId();
}
