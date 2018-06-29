package com.chosuwai.charlesandkeith.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.adapters.NewInAdapter;
import com.chosuwai.charlesandkeith.data.models.NewProductsModel;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;
import com.chosuwai.charlesandkeith.events.SuccessGetNewProductsEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
                      implements ItemsDelegate {

    private NewInAdapter mNewInAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this,this);

        RecyclerView rvNewIn=findViewById(R.id.rv_items);
        NewInAdapter newInAdapter=new NewInAdapter(this);
        rvNewIn.setAdapter(newInAdapter);
        rvNewIn.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        NewProductsModel.getObjInstance().loadNewProductsList();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTapItem() {
        Intent intent=new Intent(getApplicationContext(), NewInDetailsActivity.class);
        startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts : " + event.getNewProductsList().size());
        mNewInAdapter.setNewProductsList(event.getNewProductsList());
    }
}

