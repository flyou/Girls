package com.flyou.girls.ui.typeImageList.view;

import com.flyou.girls.ui.typeImageList.domain.TypeImageDomain;

import java.util.List;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.typeImageList.view
 * 文件名：TypeImageListView
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/20 14:46
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public interface TypeImageListView {
    void showLaoding();

    void hideLoading();

    void showLoadFaild( Exception e);

    void receiveImageList(List<TypeImageDomain> typeImageDomains);

}
