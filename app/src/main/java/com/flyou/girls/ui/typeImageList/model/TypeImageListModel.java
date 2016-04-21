package com.flyou.girls.ui.typeImageList.model;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.typeImageList.model
 * 文件名：TypeImageListModel
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/20 14:42
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public interface TypeImageListModel {
    void getTypeImageList(String url,TypeImageListModelImpl.OnGetTypeImageListener litener);
}
