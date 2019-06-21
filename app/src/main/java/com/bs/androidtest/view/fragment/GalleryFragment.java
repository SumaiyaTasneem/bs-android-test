package com.bs.androidtest.view.fragment;


import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bs.androidtest.R;
import com.bs.androidtest.model.ImageList;
import com.bs.androidtest.presenter.adapter.GalleryImageAdapter;
import com.bs.androidtest.presenter.fragmentPresenter.GalleryPresenter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment implements GalleryPresenter.View {

    private static final String TAG = "GalleryFragment";

    private GalleryPresenter mPresenter;

    private RecyclerView mImageRecyclerView;

    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder mAlertDialog;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        mPresenter = new GalleryPresenter(this);

        mProgressDialog = new ProgressDialog(getContext());
        mAlertDialog = new AlertDialog.Builder(getContext());

        mImageRecyclerView = view.findViewById(R.id.recycler_view_images);

        mImageRecyclerView.setHasFixedSize(true);
        mImageRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        mPresenter.getImages();

        return view;
    }

    @Override
    public void setImages(List<ImageList> imageList) {
        Log.e(TAG, "setImages: ");
        GalleryImageAdapter adapter = new GalleryImageAdapter(getContext(), imageList, this);
        mImageRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgressDialog() {
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showAlertDialog(String message) {
        mAlertDialog.setTitle("Error")
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void callBack(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);

        SingleImageFragment singleImageFragment = new SingleImageFragment();
        singleImageFragment.setArguments(bundle);
        singleImageFragment.show(getFragmentManager(), "");
    }
}
