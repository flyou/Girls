package com.flyou.girls.utils;

import android.content.Context;

import com.flyou.girls.ui.mainImageList.domain.ImageListDomain;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.lang.reflect.Type;
/**
 * ==================================================
 * 项目名称：Girls
 * 创建人：wangxiaolong
 * 创建时间：16/5/20 下午6:41
 * 备注：
 * Version：
 * ==================================================
 */
public class ACacheManager {
    private final String KEY_LIST = "list";
    private JsonArray mCacheListDomain;
    private static ACacheManager mManager = null;
    private ACache mACache;
    private Context mContext;


    public static ACacheManager getmManager() {
        synchronized (ACacheManager.class.getSimpleName()) {
            if (null == mManager) {
                synchronized (ACacheManager.class) {
                    if (null == mManager) {
                        mManager = new ACacheManager();
                    }
                }
            }
        }
        return mManager;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mACache = ACache.get(ctx);
        mACache.getAsJSONArray(KEY_LIST);

    }

    public void saveFavoriteDomian(ImageListDomain domain) {
        mCacheListDomain.add(domain.getImageUrl());
//        mCacheListDomain.contains(domain.getImageUrl());
        mACache.put(domain.getImageUrl(), domain);
//        mACache.put(KEY_LIST ,);
    }

    private <T> T parse(String value, Type type) {
        Gson mGson = new Gson();
        T t = mGson.fromJson(value, type);
        return t;
    }

    private String toJson(Object obj) {
        Gson mGson = new Gson();
        return mGson.toJson(obj);
    }

}
