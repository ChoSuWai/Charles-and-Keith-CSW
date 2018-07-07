package com.chosuwai.charlesandkeith.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.adapters.NewInDetailsAdapter;
import com.chosuwai.charlesandkeith.data.models.NewProductsModel;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;
import com.chosuwai.charlesandkeith.utils.GlideApp;
import com.chosuwai.charlesandkeith.viewpods.EmptyViewPod;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewInDetailsActivity extends BaseActivity
                        implements ItemsDelegate{

    private NewInDetailsAdapter newInDetailsAdapter;
    private NewProductsVO mNewProduct;
    private List<NewProductsVO> newProductsList;

    //TextView tvItemName=findViewById(R.id.tv_item_name);
    //ImageView ivItemPicture=findViewById(R.id.iv_item_picture);

    @BindView(R.id.rv_details_items)
    RecyclerView rvDetailsItems;
/*
    @BindView(R.id.iv_item_picture)
    ImageView ivItemPicture;

    @BindView(R.id.tv_item_name)
    TextView tvItemName;
    */

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    /*
    public NewInDetailsActivity() {
       // this.newInDetailsAdapter = newInDetailsAdapter;
        ButterKnife.bind(this,this);
    }

    */

//EmptyViewPod vpEmpty=findViewById(R.id.vp_empty);
//CoordinatorLayout coordinatorLayout=findViewById(R.id.coordinator_layout);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_details);
        ButterKnife.bind(this, this);

        newInDetailsAdapter=new NewInDetailsAdapter(this);

        //RecyclerView rvDetailsItems=findViewById(R.id.rv_details_items);

        rvDetailsItems.setAdapter(newInDetailsAdapter);
        rvDetailsItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
               LinearLayoutManager.VERTICAL, false));

        String newProductsId = getIntent().getStringExtra("newProductsId");
        Log.d("NewIn Details Activity", "newProductsId: " + newProductsId);

        NewProductsVO items= NewProductsModel.getObjInstance().getItemsById(newProductsId);

        if(items!=null){
            vpEmpty.setVisibility(View.GONE);
            coordinatorLayout.setVisibility(View.VISIBLE);
            //bindData(items);
        }else{
            vpEmpty.setVisibility(View.VISIBLE);
            coordinatorLayout.setVisibility(View.GONE);
            vpEmpty.setEmptyData("http://3.bp.blogspot.com/_8UEKseUOQ68/TFjXla4rUGI/AAAAAAAAAY0/KHhIGBii61g/s1600/Creepy_Little_Baby_17.jpg",
                    getApplicationContext().getResources().getString(R.string.empty_details_msg));
        }


    }


/*
    private void bindData(NewProductsVO items){

        tvItemName.setText(items.getProductTitle());

        GlideApp.with(ivItemPicture.getContext())
                .load(items.getProductImage())
                .into(ivItemPicture);

    }
*/

    @Override
    public void onTapItem(NewProductsVO item) {

    }
}
