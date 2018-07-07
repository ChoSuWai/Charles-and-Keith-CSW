package com.chosuwai.charlesandkeith.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.adapters.NewInAdapter;
import com.chosuwai.charlesandkeith.data.models.NewProductsModel;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;
import com.chosuwai.charlesandkeith.events.ApiErrorEvent;
import com.chosuwai.charlesandkeith.events.SuccessForceRefreshGetNewProductsEvent;
import com.chosuwai.charlesandkeith.events.SuccessGetNewProductsEvent;
import com.chosuwai.charlesandkeith.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements ItemsDelegate {

    private NewInAdapter mNewInAdapter;

    @BindView(R.id.rv_items)
    RecyclerView rvNewIn;

    @BindView(R.id.btn_one_col)
    ImageView ivOneCol;

    @BindView(R.id.btn_two_col)
    ImageView ivTwoCol;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

        mNewInAdapter = new NewInAdapter(this);

        rvNewIn.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean isListEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("NewInActivity", "onScrollListener: onScrollStateChanged " + newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE
                        && ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastCompletelyVisibleItemPosition() == recyclerView.getAdapter().getItemCount() - 1
                        && !isListEndReached) {
                    isListEndReached = true;
                    NewProductsModel.getObjInstance().loadNewProductsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("NewInActivity", "onScrollListener: onScrolled: dx: " + dx + " dy: " + dy);

                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems < totalItemCount) {
                    isListEndReached = false;
                }
            }
        });

        rvNewIn.setAdapter(mNewInAdapter);

        rvNewIn.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));


        ivOneCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvNewIn.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                        LinearLayoutManager.VERTICAL, false));
            }
        });


        ivTwoCol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rvNewIn.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            }
        });

        NewProductsModel.getObjInstance().forceRefreshNewProductsList();

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewProductsModel.getObjInstance().forceRefreshNewProductsList();
            }
        });

        vpEmpty.setEmptyData(R.drawable.ic_sorry, "No Data Available!");

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onTapItem(NewProductsVO item) {
        Intent intent = new Intent(getApplicationContext(), NewInDetailsActivity.class);
        intent.putExtra("newProductsId", item.getProductId());
        startActivity(intent);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        Log.d("onSuccessGetNewProducts", "onSuccessGetNewProducts : " + event.getNewProductsList().size());
        mNewInAdapter.appendNewProductsList(event.getNewProductsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetNewProducts(SuccessForceRefreshGetNewProductsEvent event) {
        mNewInAdapter.setNewProductsList(event.getNewProductsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetNewProducts(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(swipeRefreshLayout, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        if (mNewInAdapter.getItemCount() <= 0) {
            vpEmpty.setVisibility(View.VISIBLE);
        }

    }

}