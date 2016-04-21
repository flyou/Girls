package com.flyou.girls.ui.mainImageList.widget.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * ============================================================
 * 项目名称：HenuCenterPro
 * 包名称：com.flyou.flying.ui.frgment
 * 文件名：BaseFragment
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2015/9/23 10:36
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 */
public abstract class BaseFragment extends Fragment {
    public Activity context;
    public View rootView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutResource(), container, false);

        }

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        initView();
        initData();
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getLayoutResource();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    public void onAttach(Activity activity) {
        this.context = activity;
        super.onAttach(activity);
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }

    public void onResume() {
        super.onResume();

    }
    public void onPause() {
        super.onPause();

    }

//    /*
//  * 对象转 json
//  * obj 对象
//  * */
//    public String toJson(Object obj) {
//
//        Gson gson = new Gson();
//        return gson.toJson(obj);
//    }
//
//    /*
//    * json转对象
//    * classOfT 对象类型
//    * */
//    public <T> T toObj(String json, Type typeOfT) {
//        Gson gson = new Gson();
//        T t = gson.fromJson(json, typeOfT);
//        return t;
//    }
}
