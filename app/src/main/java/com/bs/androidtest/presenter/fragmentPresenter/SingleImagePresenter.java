package com.bs.androidtest.presenter.fragmentPresenter;

import android.os.Bundle;

import com.bs.androidtest.view.fragment.SingleImageFragment;

public class SingleImagePresenter {

    private View mView;

    public SingleImagePresenter (SingleImageFragment singleImageFragment) {
        mView = singleImageFragment;
    }

    public void checkBundle(Bundle bundle) {
        if (bundle != null) {
            mView.setImage(bundle.getString("url"));
        }
    }

    public interface View {
        void setImage(String url);
    }
}
