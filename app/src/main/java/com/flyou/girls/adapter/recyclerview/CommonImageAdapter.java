package com.flyou.girls.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.flyou.girls.R;
import com.flyou.girls.ui.typeImageList.domain.TypeImageDomain;
import com.flyou.girls.ui.typeImageList.widget.ScaleImageView;

import java.util.ArrayList;
import java.util.List;

public class CommonImageAdapter extends RecyclerView.Adapter<CommonImageAdapter.ImageViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<TypeImageDomain> mDatas = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public CommonImageAdapter(Context context, int layoutId, List<TypeImageDomain> datas) {
        mContext = context;
        mLayoutId = layoutId;
        mDatas.addAll(datas);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(View.inflate(mContext, mLayoutId, null));
    }


    @Override
    public void onBindViewHolder(ImageViewHolder holder, final int position) {
        final TypeImageDomain mImageDomain = mDatas.get(position);
        holder.view.setDefaultValue(mImageDomain.getHeight() * 1f / mImageDomain.getWidth());
        Glide.with(mContext)
                .load(mImageDomain.getUrl())
                .placeholder(R.drawable.pic_loading)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .into(holder.view);
        holder.view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(null, v, mImageDomain.getFullSizeUrl(), position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    class ImageViewHolder extends RecyclerView.ViewHolder {
        ScaleImageView view;

        public ImageViewHolder(View itemView) {
            super(itemView);
            view = (ScaleImageView) itemView.findViewById(R.id.imageView);
        }

    }


}
