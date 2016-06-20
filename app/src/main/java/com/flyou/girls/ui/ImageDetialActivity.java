package com.flyou.girls.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.flyou.girls.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageDetialActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mDownLoad;
    private ImageView mImageView;
    private PhotoViewAttacher mAttacher;
    private TextView mLoading;
    private String mImageurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏

        setContentView(R.layout.activity_image_detial);
        initView();
        initDate();

    }

    private void initDate() {
        mImageurl = getIntent().getStringExtra("imageurl");
        Glide.with(ImageDetialActivity.this)
                .load(mImageurl)
//                .centerCrop()
                .crossFade()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        mLoading.setVisibility(View.GONE);
                        mDownLoad.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(mImageView);
        mAttacher = new PhotoViewAttacher(mImageView);

        mAttacher.update();


    }

    private void initView() {
        mDownLoad = (TextView) findViewById(R.id.download);
        mLoading = (TextView) findViewById(R.id.laoding);
        mImageView = (ImageView) findViewById(R.id.imageView);
        mDownLoad.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.download:
                Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        String fileName = System.currentTimeMillis() + ".jpg";
                        File file = new File(Environment.getExternalStorageDirectory() + File.separator+"Girls", fileName);

                        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

                            try {


                                if (!file.exists()){
                                    file.mkdirs();
                                }
                                // 从网络上获取图片
                                URL url = new URL(mImageurl);
                                HttpURLConnection conn = null;

                                conn = (HttpURLConnection) url.openConnection();
                                conn.setConnectTimeout(5000);
                                conn.setRequestMethod("GET");
                                conn.setDoInput(true);
                                if (conn.getResponseCode() == 200) {

                                    InputStream is = conn.getInputStream();
                                    FileOutputStream fos = new FileOutputStream(file);
                                    byte[] buffer = new byte[1024];
                                    int len = 0;
                                    while ((len = is.read(buffer)) != -1) {
                                        fos.write(buffer, 0, len);
                                    }
                                    is.close();
                                    fos.close();

                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            subscriber.onNext(file.getAbsolutePath());
                        } else {
                            Toast.makeText(ImageDetialActivity.this, "没有发现sd卡，无法下载", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                Subscriber<String> subscriber = new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(ImageDetialActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(ImageDetialActivity.this, "图片下载成功：" + s, Toast.LENGTH_SHORT).show();
                    }
                };
                observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(subscriber);
                break;
        }
    }
}
