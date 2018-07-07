package com.chosuwai.charlesandkeith.data.models;

import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.events.SuccessForceRefreshGetNewProductsEvent;
import com.chosuwai.charlesandkeith.events.SuccessGetNewProductsEvent;
import com.chosuwai.charlesandkeith.network.NewProductsDataAgent;
import com.chosuwai.charlesandkeith.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewProductsModel {

    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";
    private static NewProductsModel objInstance;
    private NewProductsDataAgent mDataAgent;

    private Map<String, NewProductsVO> mNewProductsMap;
    private int mPage;

    private NewProductsModel() {
        mDataAgent= RetrofitDataAgentImpl.getInstance();
        mNewProductsMap=new HashMap<>();
        EventBus.getDefault().register(this);
        mPage=1;
    }

    public static NewProductsModel getObjInstance() {
        if(objInstance==null){
            objInstance=new NewProductsModel();
        }
        return objInstance;
    }


    public void loadNewProductsList(){
        mDataAgent.loadNewProductsList(mPage, DUMMY_ACCESS_TOKEN, false);
    }

    public void forceRefreshNewProductsList() {
        mPage = 1;
        mDataAgent.loadNewProductsList(1,DUMMY_ACCESS_TOKEN, true);
    }

    public NewProductsVO getItemsById(String itemId){
        return null;//TODO remove this after testing empty view layout in news in details screen.
        //return mNewProductsMap.get(itemId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event) {
        setDataIntoRepository(event.getNewProductsList());
        mPage++;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetNewProducts(SuccessForceRefreshGetNewProductsEvent event) {
        setDataIntoRepository(event.getNewProductsList());
    }

    private void setDataIntoRepository(List<NewProductsVO> itemsList) {
        for (NewProductsVO newProducts : itemsList) {
            mNewProductsMap.put(newProducts.getProductId(), newProducts);
        }
    }

}
