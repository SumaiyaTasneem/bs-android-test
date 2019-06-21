package com.bs.androidtest.presenter.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bs.androidtest.R;
import com.bs.androidtest.model.ImageList;
import com.bs.androidtest.view.fragment.GalleryFragment;
import com.bs.androidtest.view.fragment.SingleImageFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ViewHolder> {

    private Context mContext;
    private List<ImageList> mImageList;
    private GalleryFragment mGalleryFragment;

    private  OnGetImage mOnGetImage;
    public interface OnGetImage {
        void callBack();
    }

    public GalleryImageAdapter(Context mContext, List<ImageList> mImageList, GalleryFragment galleryFragment) {
        this.mContext = mContext;
        this.mImageList = mImageList;
        mGalleryFragment = galleryFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.single_image_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Picasso.get()
                .load(mImageList.get(position).getDownloadUrl())
                .placeholder(R.drawable.ic_action_person)
                .into(viewHolder.mImageView);
        viewHolder.mAuthorTextView.setText(mImageList.get(position).getAuthor());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryFragment.callBack(mImageList.get(position).getDownloadUrl());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageView;
        private TextView mAuthorTextView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mAuthorTextView = itemView.findViewById(R.id.text_view_author);
        }
    }
}
