package com.android.kharada.designsample.activity;

import android.animation.Animator;
import android.os.Build;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.kharada.designsample.R;

public class BottomSheetActivity extends AppCompatActivity {

    CoordinatorLayout mRootView;
    ImageView mHeaderImage;
    CollapsingToolbarLayout mHeader;
    TextView mDescriptionText;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);

        mRootView = (CoordinatorLayout) findViewById(R.id.activity_bottom_sheet);
        mHeaderImage = (ImageView) findViewById(R.id.backdrop);
        mHeader = (CollapsingToolbarLayout) findViewById(R.id.header);
        mDescriptionText = (TextView) findViewById(R.id.overview_description);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(bundle.getString("title", "title"));

        mHeader.setTitle(bundle.getString("title", ""));
        mDescriptionText.setText(bundle.getString("description", ""));
        int circleX = bundle.getInt("circle_x");
        int circleY = bundle.getInt("circle_y");

        if (savedInstanceState == null) {
            mRootView.setVisibility(View.INVISIBLE);

            ViewTreeObserver viewTreeObserver = mRootView.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity(mRootView,circleX,circleY);
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

    private void circularRevealActivity(View rootLayout,int circleX,int circleY) {

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
}
