package com.android.kharada.designsample.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.kharada.designsample.R;

/**
 * Created by kharada on 2017/06/01.
 */

public class ButtonFragment extends Fragment implements ListItemContract.View{

    private TextView mDescriptionText;
    private CollapsingToolbarLayout mHeader;

    private ListItemContract.Presenter mPresenter;

    public static ButtonFragment newInstance() {
        return new ButtonFragment();
    }

    @Override
    public void setPresenter(ListItemContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                      Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_button, container, false);
        mDescriptionText = (TextView) root.findViewById(R.id.overview_description);
        mHeader = (CollapsingToolbarLayout) root.findViewById(R.id.header);
        return root;
    }

    @Override
    public void setTitle(String title) {
        mHeader.setTitle(title);
    }

    @Override
    public void setDescription(String description) {
        mDescriptionText.setText(description);
    }
}
