package com.android.kharada.designsample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.kharada.designsample.R;
import com.android.kharada.designsample.gson.MainItemModel;

import java.util.List;

/**
 * Created by haradakazumi on 2017/01/29.
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>{

    private List<MainItemModel> mData;
    private LayoutInflater mInflater;
    private RecyclerView mRecyclerView;
    private OnItemClickListener mListener;

    public MainAdapter(Context context, List<MainItemModel> data) {
        this.mData = data;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(mInflater.inflate(R.layout.listitem_main, viewGroup, false),this);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.title.setText(mData.get(i).getTitle());
        viewHolder.description.setText(mData.get(i).getDescription());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected void onClick(View v) {
        if (mRecyclerView == null) {
            return;
        }

        if (mListener != null) {
            int position = mRecyclerView.getChildAdapterPosition(v);
            MainItemModel item = mData.get(position);
            mListener.onItemClick(this, position, item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MainAdapter adapter, int position, MainItemModel item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        MainAdapter mAdapter;

        final TextView title;
        final TextView description;
        final ImageView icon;
        final RelativeLayout rootView;
        public ViewHolder(View view, MainAdapter adapter) {
            super(view);
            rootView = (RelativeLayout) view.findViewById(R.id.item_root_view);
            title = (TextView) view.findViewById(R.id.text_primary);
            description = (TextView) view.findViewById(R.id.text_secondary);
            icon = (ImageView) view.findViewById(R.id.image_icon);
            mAdapter = adapter;
            view.setOnClickListener(this);
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDescription() {
            return description;
        }

        public ImageView getIcon() {
            return icon;
        }

        public RelativeLayout getRootView() {
            return rootView;
        }

        @Override
        public void onClick(View v) {
            if (mAdapter == null) {
                return;
            }
            mAdapter.onClick(v);
        }
    }

}
