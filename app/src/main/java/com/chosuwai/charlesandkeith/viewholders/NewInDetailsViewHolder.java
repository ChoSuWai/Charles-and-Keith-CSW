package com.chosuwai.charlesandkeith.viewholders;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewInDetailsViewHolder extends RecyclerView.ViewHolder {


    public NewInDetailsViewHolder(View itemView) {

        super(itemView);
    }

/*
    private ItemsDelegate mItemsDelegate;
    private NewProductsVO mitems;

    @BindView(R.id.iv_item_picture)
    ImageView ivItemPicture;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    public NewInDetailsViewHolder(final View itemView, ItemsDelegate itemsDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        mItemsDelegate = itemsDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mItemsDelegate.onTapItem(mitems);
            }
        });


    }


    public void setItemsData(NewProductsVO items) {
        tvItemName.setText(items.getProductTitle());

        Glide.with(ivItemPicture.getContext())
                .load(items.getProductImage())
                .into(ivItemPicture);
    }*/
}
