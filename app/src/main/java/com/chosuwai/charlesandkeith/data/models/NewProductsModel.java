package com.chosuwai.charlesandkeith.data.models;

import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.events.SuccessGetNewProductsEvent;
import com.chosuwai.charlesandkeith.network.NewProductsDataAgent;
import com.chosuwai.charlesandkeith.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class NewProductsModel {

    private static final String DUMMY_ACCESS_TOKEN="b002c7e1a528b7cb460933fc2875e916";
    private static NewProductsModel objInstance;
    private NewProductsDataAgent mDataAgent;

    private Map<String, NewProductsVO> mNewProductsMap;

    private NewProductsModel() {
        mDataAgent= RetrofitDataAgentImpl.getInstance();
        mNewProductsMap=new HashMap<>();
        EventBus.getDefault().register(this);
    }

    public static NewProductsModel getObjInstance() {
        if(objInstance==null){
            objInstance=new NewProductsModel();
        }
        return objInstance;
    }

    public void loadNewProductsList(){
        mDataAgent.loadNewProductsList(1, DUMMY_ACCESS_TOKEN);
    }


    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNewProducts(SuccessGetNewProductsEvent event){
        for (NewProductsVO newProducts : event.getNewProductsList()) {
            mNewProductsMap.put(newProducts.getProductId(), newProducts);
        }
    }

}
