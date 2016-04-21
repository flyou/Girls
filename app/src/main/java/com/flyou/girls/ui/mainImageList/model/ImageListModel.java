package com.flyou.girls.ui.mainImageList.model;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.ImageList.domain
 * 文件名：ImageListModel
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/19 15:39
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public interface ImageListModel {
    void GetImageList(String type,int page,ImageListModelImpl.GetImageListenter listener);
}
