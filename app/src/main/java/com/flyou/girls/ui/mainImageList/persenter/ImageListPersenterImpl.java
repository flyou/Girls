package com.flyou.girls.ui.mainImageList.persenter;

import com.flyou.girls.ui.mainImageList.domain.ImageListDomain;
import com.flyou.girls.ui.mainImageList.model.ImageListModel;
import com.flyou.girls.ui.mainImageList.model.ImageListModelImpl;
import com.flyou.girls.ui.mainImageList.view.ImageListView;

import java.util.List;

/**
 * ============================================================
 * 项目名称：Girls
 * 包名称：com.flyou.girls.ui.ImageList.persenter
 * 文件名：ImageListPersenterImpl
 * 类描述：
 * 创建人：flyou
 * 邮箱：fangjaylong@gmail.com
 * 创建时间：2016/4/19 16:48
 * 修改备注：
 * 版本：@version  V1.0
 * ============================================================
 **/
public class ImageListPersenterImpl implements ImageListModelImpl.GetImageListenter,ImageListPersenter{
    private ImageListView imageListView;
    private ImageListModel imageListModel;

    public ImageListPersenterImpl(ImageListView imageListView) {
        this.imageListView = imageListView;
        this.imageListModel=new ImageListModelImpl();
    }


    @Override
    public void onSuccess(List<ImageListDomain> imageList) {
        imageListView.receiveImageList(imageList);
        imageListView.hideLoading();
    }

    @Override
    public void OnError(Exception e) {
        imageListView.showLoadFaild(e);
        imageListView.hideLoading();
    }

    @Override
    public void startGetImageList(String type,int page) {
        imageListView.showLaoding();
        imageListModel.GetImageList(type,page,this);
    }
}
