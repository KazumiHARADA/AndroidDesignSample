package com.android.kharada.designsample.activity;

import android.graphics.Bitmap;

import com.android.kharada.designsample.BasePresenter;
import com.android.kharada.designsample.BaseView;

/**
 * Created by kharada on 2017/06/01.
 */

public interface ListItemContract {

    interface View extends BaseView<Presenter> {
        void setTitle(String title);
        void setDescription(String description);
    }

    interface Presenter extends BasePresenter {

    }
}
