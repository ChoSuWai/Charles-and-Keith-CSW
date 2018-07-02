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

    private NewInDetailsAdapter newInDetailsAdapter;
    //TextView tvItemName=findViewById(R.id.tv_item_name);
    //ImageView ivItemPicture=findViewById(R.id.iv_item_picture);
/*
    @BindView(R.id.rv_details_items)
    RecyclerView rvDetailsItems;

    @BindView(R.id.iv_item_picture)
    ImageView ivItemPicture;

    public NewInDetailsActivity() {
       // this.newInDetailsAdapter = newInDetailsAdapter;
        ButterKnife.bind(this,this);
    }

    @BindView(R.id.tv_item_name)
    TextView tvItemName;*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_details);
        //ButterKnife.bind(this, this);

        newInDetailsAdapter=new NewInDetailsAdapter(this);

        RecyclerView rvDetailsItems=findViewById(R.id.rv_details_items);
        rvDetailsItems.setAdapter(newInDetailsAdapter);
        rvDetailsItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        //String newProductsId = getIntent().getStringExtra("newProductsId");
        //Log.d("NewIn Details Activity", "newProductsId: " + newProductsId);

        //NewProductsVO items= NewProductsModel.getObjInstance().getItemsById(newProductsId);
        // bindData(items, newInDetailsAdapter);

    }


/*
    private void bindData(NewProductsVO items, NewInDetailsAdapter newInDetailsAdapter){
        tvItemName.setText(items.getProductTitle());

        Glide.with(ivItemPicture.getContext())
                .load(items.getProductImage())
                .into(ivItemPicture);

        newInDetailsAdapter.setItemsList(NewProductsModel.getObjInstance().getItemsList());

    }
*/

    @Override
    public void onTapItem(NewProductsVO item) {

    }
}
