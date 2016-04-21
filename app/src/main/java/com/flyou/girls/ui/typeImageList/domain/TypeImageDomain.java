package com.flyou.girls.ui.typeImageList.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.typeImageList.domain
 * 文件名：TypeImageDomain
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/20 14:36
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class TypeImageDomain implements Parcelable {
    private int width;
    private int height;
    private String url;
    private String fullSizeUrl;

    public TypeImageDomain(int width, int height, String url, String fullSizeUrl) {
        this.width = width;
        this.height = height;
        this.url = url;
        this.fullSizeUrl = fullSizeUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFullSizeUrl() {
        return fullSizeUrl;
    }

    public void setFullSizeUrl(String fullSizeUrl) {
        this.fullSizeUrl = fullSizeUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.width);
        dest.writeInt(this.height);
        dest.writeString(this.url);
        dest.writeString(this.fullSizeUrl);
    }

    protected TypeImageDomain(Parcel in) {
        this.width = in.readInt();
        this.height = in.readInt();
        this.url = in.readString();
        this.fullSizeUrl = in.readString();
    }

    public static final Parcelable.Creator<TypeImageDomain> CREATOR = new Parcelable.Creator<TypeImageDomain>() {
        @Override
        public TypeImageDomain createFromParcel(Parcel source) {
            return new TypeImageDomain(source);
        }

        @Override
        public TypeImageDomain[] newArray(int size) {
            return new TypeImageDomain[size];
        }
    };
}
