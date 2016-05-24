package com.flyou.girls;

import android.app.Application;

import com.flyou.girls.utils.ACacheManager;

/**
 * ==================================================
 * 项目名称：Girls
 * 创建人：wangxiaolong
 * 创建时间：16/5/20 下午6:51
 * 备注：
 * Version：
 * ==================================================
 */
public class GirlsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ACacheManager.getmManager().init(this);
    }
}
