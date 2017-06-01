package com.android.kharada.designsample.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.kharada.designsample.R;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by kharada on 2017/06/01.
 */

public class ButtonPresenter implements ListItemContract.Presenter{

    @NonNull
    private final ListItemContract.View mButtonView;

    private final Bundle mBundle;

    public ButtonPresenter(Bundle bundle, @NonNull ListItemContract.View buttonView) {
        mButtonView = checkNotNull(buttonView);
        mBundle = bundle;
        mButtonView.setPresenter(this);
    }

    @Override
    public void start() {
        mButtonView.setTitle(mBundle.getString("title"));
        mButtonView.setDescription(mBundle.getString("description"));
    }
}
