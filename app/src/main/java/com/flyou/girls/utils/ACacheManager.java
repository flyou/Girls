package com.flyou.girls.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.flyou.girls.R;
import com.flyou.girls.ui.mainImageList.domain.ImageListDomain;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

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
    private LinkedList<ImageListDomain> favoriteList;
    private static ACacheManager mManager = null;
    private ACache mACache;
    private Context mContext;

    public ACacheManager() {
        mManager = this;
    }


    public static ACacheManager getManager() {
        return mManager;
    }

    public void init(Context ctx) {
        mContext = ctx;
        mACache = ACache.get(ctx);
        try {
            if (TextUtils.isEmpty(mACache.getAsString(KEY_LIST))) {
                favoriteList = new LinkedList<>();
            } else {
                Object obj = ObjectSerializer.deserialize(mACache.getAsString(KEY_LIST));
                if (null == obj) {
                    favoriteList = new LinkedList<>();
                } else {
                    favoriteList = (LinkedList<ImageListDomain>) obj;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            favoriteList = new LinkedList<>();
        }
    }

    public void saveFavoriteDomian(ImageListDomain domain) {
        if (isContains(domain)) {
            Toast.makeText(mContext, R.string.had_favorites, Toast.LENGTH_SHORT).show();
        } else {
            favoriteList.add(domain);
            try {
                mACache.put(KEY_LIST, ObjectSerializer.serialize(favoriteList));
                Toast.makeText(mContext, R.string.favorites_success, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(mContext, R.string.favorites_failed, Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    public boolean isContains(ImageListDomain domain) {
        if (null == favoriteList) {
            favoriteList = new LinkedList<>();
        }
        return favoriteList.contains(domain);

    }

    public List<ImageListDomain> getFavoriteList() {
        return favoriteList;
    }

    public void removeItem(ImageListDomain imageListDomain) {
        if (isContains(imageListDomain)) {
            favoriteList.remove(imageListDomain);
            try {
                mACache.put(KEY_LIST, ObjectSerializer.serialize(favoriteList));
                Toast.makeText(mContext, R.string.fav_delete_success, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
