package com.flyou.girls.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.flyou.girls.ui.typeImageList.domain.TypeImageDomain;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import uk.co.senab.photoview.PhotoViewAttacher;

public class ImageViewPagerActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TextView mNumTV;

    private ArrayList<TypeImageDomain> mImageList;
    private int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//实现半透明状态栏
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_image_view_pager);
        mImageList = getIntent().getParcelableArrayListExtra("imagelist");
        mPosition = getIntent().getIntExtra("position", 0);
        if (mImageList == null || mImageList.isEmpty()) {
            finish();
            return;
        }
        initView();
        initListener();
    }

    private void initView() {
        mNumTV = (TextView) findViewById(R.id.numTV);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mNumTV.setText(mPosition + 1 + "/" + mImageList.size());
        mViewPager.setAdapter(new ImagePagerAdapter());
        mViewPager.setCurrentItem(mPosition);
    }

    private void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mNumTV.setText((position + 1) + "/"
                        + mImageList.size());
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    class ImagePagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return mImageList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View parent = LayoutInflater.from(container.getContext()).inflate(R.layout.activity_image_detial, container, false);
            container.addView(parent);
            View downLoad = parent.findViewById(R.id.download);
            View loading = parent.findViewById(R.id.laoding);
            downLoad.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    downloadImage(mImageList.get(position).getFullSizeUrl());
                }
            });
            downLoad.setTag(mImageList.get(position).getFullSizeUrl());
            ImageView imageView = (ImageView) parent.findViewById(R.id.imageView);
            displayImage(mImageList.get(position).getFullSizeUrl(),imageView,loading,downLoad);
            return parent;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        void displayImage(final String url,final ImageView imageView,final View loading,final View download) {
            Glide.with(ImageViewPagerActivity.this)
                    .load(url)
//                .centerCrop()
                    .crossFade()
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            loading.setVisibility(View.GONE);
                            download.setVisibility(View.VISIBLE);
                            PhotoViewAttacher attacher = new PhotoViewAttacher(imageView);
//                            mAttacher.update();
                            return false;
                        }
                    })
                    .into(imageView);
        }

        void downloadImage(final String imageUrl) {
            Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
                @Override
                public void call(Subscriber<? super String> subscriber) {
                    String fileName = System.currentTimeMillis() + ".jpg";
                    File folader = new File(Environment.getExternalStorageDirectory() + File.separator + "Girls");
                    File file = new File(Environment.getExternalStorageDirectory() + File.separator + "Girls", fileName);

                    if (Environment.getExternalStorageState().equals(

                            Environment.MEDIA_MOUNTED)) {

                        try {
                            if (!folader.exists()) {
                                folader.mkdirs();
                            }
                            // 从网络上获取图片
                            URL url = new URL(imageUrl);
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
                        Toast.makeText(ImageViewPagerActivity.this, "没有发现sd卡，无法下载", Toast.LENGTH_SHORT).show();
                    }

                }
            });
            Subscriber<String> subscriber = new Subscriber<String>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    Toast.makeText(ImageViewPagerActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNext(String s) {
                    Toast.makeText(ImageViewPagerActivity.this, "图片下载成功：" + s, Toast.LENGTH_SHORT).show();
                }
            };
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        }
    }

}
