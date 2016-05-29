package com.flyou.girls.ui.mainImageList.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.ImageList.domain
 * 文件名：ImageListDomain
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/19 15:47
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class ImageListDomain implements Parcelable, Serializable {
    private String linkUrl;
    private String imageUrl;
    private String imgaeTitle;
    private int showType; //0代表显示   -1 不显示；

    public int getShowType() {
        return showType;
    }

    public void setShowType(int showType) {
        this.showType = showType;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImgaeTitle() {
        return imgaeTitle;
    }

    public void setImgaeTitle(String imgaeTitle) {
        this.imgaeTitle = imgaeTitle;
    }

    public ImageListDomain(String linkUrl, String imageUrl, String imgaeTitle) {
        this.linkUrl = linkUrl;
        this.imageUrl = imageUrl;
        this.imgaeTitle = imgaeTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.linkUrl);
        dest.writeString(this.imageUrl);
        dest.writeString(this.imgaeTitle);
        dest.writeInt(this.showType);
    }

    protected ImageListDomain(Parcel in) {
        this.linkUrl = in.readString();
        this.imageUrl = in.readString();
        this.imgaeTitle = in.readString();
        this.showType = in.readInt();
    }

    public static final Parcelable.Creator<ImageListDomain> CREATOR = new Parcelable.Creator<ImageListDomain>() {
        @Override
        public ImageListDomain createFromParcel(Parcel source) {
            return new ImageListDomain(source);
        }

        @Override
        public ImageListDomain[] newArray(int size) {
            return new ImageListDomain[size];
        }
    };

    @Override
    public int hashCode() {
        return imageUrl.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof ImageListDomain) {
            ImageListDomain u = (ImageListDomain) obj;
            return hashCode() == u.hashCode();
        }
        return super.equals(obj);
    }
}
