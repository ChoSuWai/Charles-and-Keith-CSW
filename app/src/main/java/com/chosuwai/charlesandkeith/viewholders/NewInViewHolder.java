package com.chosuwai.charlesandkeith.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewInViewHolder extends RecyclerView.ViewHolder {

    private ItemsDelegate mItemDelegate;
    private NewProductsVO item;

    @BindView(R.id.iv_item_picture)
    ImageView itemPicture;

    @BindView(R.id.tv_item_name)
    TextView itemName;

    public NewInViewHolder(View itemView, ItemsDelegate itemsDelegate) {
        super(itemView);
        ButterKnife.bind(this,itemView);

        mItemDelegate=itemsDelegate;
       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               mItemDelegate.onTapItem(item);
           }
       });
        }

        public void setNewProductsData(NewProductsVO newProducts){
        item =newProducts;

        itemName.setText(newProducts.getProductTitle());

            if (!item.getProductImage().isEmpty()) {
                itemPicture.setVisibility(View.VISIBLE);
                Glide.with(itemPicture.getContext())
                        .load(item.getProductImage())
                        .into(itemPicture);
            } else {
                itemPicture.setVisibility(View.GONE);
            }

        }
    }

