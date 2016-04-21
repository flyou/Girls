package com.flyou.girls.ui.typeImageList.persenter;

import com.flyou.girls.ui.typeImageList.domain.TypeImageDomain;
import com.flyou.girls.ui.typeImageList.model.TypeImageListModel;
import com.flyou.girls.ui.typeImageList.model.TypeImageListModelImpl;
import com.flyou.girls.ui.typeImageList.view.TypeImageListView;

import java.util.List;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.typeImageList.persenter
 * 文件名：TypeImageListPersenterImpl
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/20 14:48
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class TypeImageListPersenterImpl implements TypeImageListModelImpl.OnGetTypeImageListener,TypeImageListPersenter {
    private TypeImageListView mTypeImageListView;
    private TypeImageListModel mTypeImageListModel;

    public TypeImageListPersenterImpl(TypeImageListView typeImageListView) {
        mTypeImageListView = typeImageListView;
        mTypeImageListModel=new TypeImageListModelImpl();
    }

    @Override
    public void onSuccess(List<TypeImageDomain> typeImageDomains) {
        mTypeImageListView.hideLoading();
        mTypeImageListView.receiveImageList(typeImageDomains);
    }

    @Override
    public void OnError(Exception e) {
        mTypeImageListView.hideLoading();
    }



    @Override
    public void startGetImageList(String url) {
        mTypeImageListModel.getTypeImageList(url,this);
        mTypeImageListView.showLaoding();

    }
}
