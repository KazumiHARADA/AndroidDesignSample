package com.android.kharada.designsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.kharada.designsample.R;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollView;
import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;

public class BottomSheetActivity extends AppCompatActivity implements ObservableScrollViewCallbacks {

    ImageView mHeaderImage;
    TextView mHeaderText;
    TextView mDescriptionText;
    LinearLayout mParallaxArea;
    Button mExecButton;

    Toolbar mToolbar;

    ObservableScrollView mScrollView;

    private int mParallaxHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_sheet);

        mHeaderImage = (ImageView) findViewById(R.id.image_header);
        mHeaderText = (TextView) findViewById(R.id.text_header);
        mDescriptionText = (TextView) findViewById(R.id.text_description);
        mExecButton = (Button) findViewById(R.id.btn_exec);
        mParallaxArea = (LinearLayout)findViewById(R.id.parallax_area);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mScrollView = (ObservableScrollView) findViewById(R.id.scrollView);

        Bundle bundle = getIntent().getExtras();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(bundle.getString("title","title"));

        mHeaderText.setText(bundle.getString("title",""));
        mDescriptionText.setText(bundle.getString("description",""));

        mScrollView.setScrollViewCallbacks(this);

        // Toolbarの背景色をalpha=0でセットする
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.colorPrimary)));
        mToolbar.setTitleTextColor(ScrollUtils.getColorWithAlpha(0, getResources().getColor(R.color.colorPrimary)));

        // 画像領域の高さを取得
        mParallaxHeight = getResources().getDimensionPixelSize(R.dimen.parallax_image_height);

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
        onScrollChanged(mScrollView.getCurrentScrollY(), false, false);
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        int baseColor = getResources().getColor(R.color.colorPrimary);
        int baseTextColor = getResources().getColor(android.R.color.white);

        // 現在のスクロール量からalpha値を計算する(0~1.0)
        float alpha = Math.min(1, (float) scrollY / mParallaxHeight);
        mToolbar.setBackgroundColor(ScrollUtils.getColorWithAlpha(alpha, baseColor));
        mToolbar.setTitleTextColor(ScrollUtils.getColorWithAlpha(alpha, baseTextColor));

        mHeaderImage.setTranslationY(scrollY / 2);
        mParallaxArea.setTranslationY(scrollY / 2);
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
