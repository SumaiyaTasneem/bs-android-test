package com.bs.androidtest.presenter.activityPresenter;

import android.content.Context;
import android.view.MenuItem;

import com.bs.androidtest.R;

public class MainPresenter {

    private Context mContext;
    private View mView;

    public MainPresenter(View view) {
        this.mContext = (Context) view;
        this.mView = view;
    }

    public void checkMenu(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.menu_info) {
            mView.showInfoActivity();
        }
    }

    public interface View {
        void showInfoActivity();
    }
}
