package com.bs.androidtest.view.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bs.androidtest.R;
import com.bs.androidtest.presenter.fragmentPresenter.SingleImagePresenter;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleImageFragment extends DialogFragment implements SingleImagePresenter.View {

    private static final String TAG = "SingleImageFragment";

    private SingleImagePresenter mPresenter;

    private ImageView mSingleImageView;

    public SingleImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_single_image, container, false);

        mPresenter = new SingleImagePresenter(this);

        mSingleImageView = view.findViewById(R.id.image_view_single);

        Bundle bundle = getArguments();
        mPresenter.checkBundle(bundle);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.AppTheme);
    }

    @Override
    public void setImage(String url) {
//        Log.e(TAG, "setImage: " + url);
        Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_action_person)
                .into(mSingleImageView);
    }
}
