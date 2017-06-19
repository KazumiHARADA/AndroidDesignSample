package com.android.kharada.designsample.service;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;
import android.widget.Button;

import com.android.kharada.designsample.R;

public class HUD extends Service {
    private View mView;

    private WindowManager windowManager;
    boolean touchconsumedbyMove = false;
    int recButtonLastX;
    int recButtonLastY;
    int recButtonFirstX;
    int recButtonFirstY;

    private int dpScale;
    private WindowManager.LayoutParams mParams;

    @Override
    public IBinder
    onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // dipを取得
        dpScale = (int)getResources().getDisplayMetrics().density;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        LayoutInflater layoutInflater = LayoutInflater.from(this);

        //mButton = getManualButton();
        mParams = getButtonLayout();

        mView = layoutInflater.inflate(R.layout.service_product_tip, null);
        mView.setOnTouchListener(recButtonOnTouchListener);

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManager.addView(mView, mParams);

        return super.onStartCommand(intent,flags,startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mView != null)
        {
            ((WindowManager) getSystemService(WINDOW_SERVICE)).removeView(mView);
            mView = null;
        }
    }

    OnTouchListener recButtonOnTouchListener = new View.OnTouchListener() {
        @TargetApi(Build.VERSION_CODES.FROYO)
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            WindowManager.LayoutParams prm = getButtonLayout();
            int totalDeltaX = recButtonLastX - recButtonFirstX;
            int totalDeltaY = recButtonLastY - recButtonFirstY;

            switch(event.getActionMasked())
            {
                case MotionEvent.ACTION_DOWN:
                    recButtonLastX = (int) event.getRawX();
                    recButtonLastY = (int) event.getRawY();
                    recButtonFirstX = recButtonLastX;
                    recButtonFirstY = recButtonLastY;
                    break;
                case MotionEvent.ACTION_UP:
                    break;
                case MotionEvent.ACTION_MOVE:
                    int deltaX = (int) event.getRawX() - recButtonLastX;
                    int deltaY = (int) event.getRawY() - recButtonLastY;
                    recButtonLastX = (int) event.getRawX();
                    recButtonLastY = (int) event.getRawY();
                    if (Math.abs(totalDeltaX) >= 5  || Math.abs(totalDeltaY) >= 5) {
                        if (event.getPointerCount() == 1) {
                            prm.x += deltaX;
                            prm.y += deltaY;
                            touchconsumedbyMove = true;
                            windowManager.updateViewLayout(mView, prm);
                        }
                        else{
                            touchconsumedbyMove = false;
                        }
                    }else{
                        touchconsumedbyMove = false;
                    }
                    break;
                default:
                    break;
            }
            return touchconsumedbyMove;
        }
    };

    private WindowManager.LayoutParams getButtonLayout() {
        if (mParams != null) {
            return mParams;
        }
        mParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT,
                WindowManager.LayoutParams.FORMAT_CHANGED,
                PixelFormat.TRANSLUCENT);
        mParams.gravity = Gravity.TOP | Gravity.CENTER;
        mParams.setTitle("Load Average");
        return mParams;
    }

//    private Button getManualButton() {
//        if (mButton != null) {
//            return mButton;
//        }
//        mButton = new Button(this);
//        mButton.setText("Overlay button");
//        mButton.setOnTouchListener(recButtonOnTouchListener);
//
//        return mButton;
//    }
}
