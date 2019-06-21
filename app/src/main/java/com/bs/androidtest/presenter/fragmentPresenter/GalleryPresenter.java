package com.bs.androidtest.presenter.fragmentPresenter;

import android.util.Log;

import com.bs.androidtest.model.ImageList;
import com.bs.androidtest.presenter.service.ApiClient;
import com.bs.androidtest.presenter.service.ApiService;
import com.bs.androidtest.view.fragment.GalleryFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryPresenter {

    private static final String TAG = "GalleryPresenter";

    private View mView;

    public GalleryPresenter (GalleryFragment galleryFragment) {
        mView = galleryFragment;
    }

    public void getImages() {
        mView.showProgressDialog();

        ApiService apiService = ApiClient.getApiClient().create(ApiService.class);

        Call<List<ImageList>> call = apiService.getAllImages();
        call.enqueue(new Callback<List<ImageList>>() {
            @Override
            public void onResponse(Call<List<ImageList>> call, Response<List<ImageList>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<ImageList> imageList = new ArrayList<>();
                        imageList.addAll(response.body());
                        Log.e(TAG, "onResponse: " + imageList.size());
                        mView.setImages(imageList);
                    } else {
                        mView.showAlertDialog(response.message());
                    }

                    mView.dismissProgressDialog();
                } catch (Exception e) {
                    mView.dismissProgressDialog();
                    mView.showAlertDialog(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<ImageList>> call, Throwable t) {
                mView.dismissProgressDialog();
                mView.showAlertDialog(t.getMessage());
            }
        });
    }

    public interface View {
        void setImages(List<ImageList> imageList);
        void showProgressDialog();
        void dismissProgressDialog();
        void showAlertDialog(String message);
    }
}
