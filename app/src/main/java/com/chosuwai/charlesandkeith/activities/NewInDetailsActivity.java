package com.chosuwai.charlesandkeith.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.adapters.NewInDetailsAdapter;
import com.chosuwai.charlesandkeith.data.models.NewProductsModel;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewInDetailsActivity extends BaseActivity
                        implements ItemsDelegate{

    @BindView(R.id.iv_item_picture)
    ImageView ivItemPicture;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_details);
        ButterKnife.bind(this,this);


        String itemId = getIntent().getStringExtra("itemId");
        Log.d("NewIn Details Activity", "itemId: " + itemId);

        NewProductsVO items= NewProductsModel.getObjInstance().getItemsById(itemId);
        bindData(items);

        /*RecyclerView rvDetailsItems=findViewById(R.id.rv_details_items);
        NewInDetailsAdapter newInDetailsAdapter=new NewInDetailsAdapter();
        rvDetailsItems.setAdapter(newInDetailsAdapter);
        rvDetailsItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));*/
    }



    private void bindData(NewProductsVO items){
        tvItemName.setText(items.getProductTitle());

        Glide.with(ivItemPicture.getContext())
                .load(items.getProductImage())
                .into(ivItemPicture);

    }


    @Override
    public void onTapItem(NewProductsVO item) {

    }
}
