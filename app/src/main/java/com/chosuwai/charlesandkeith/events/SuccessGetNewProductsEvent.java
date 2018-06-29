package com.chosuwai.charlesandkeith.events;

import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;

import java.util.List;

public class SuccessGetNewProductsEvent {

    private List<NewProductsVO> newProductsList;

    public SuccessGetNewProductsEvent(List<NewProductsVO> newProductsList) {
        this.newProductsList = newProductsList;
    }

    public List<NewProductsVO> getNewProductsList() {
        return newProductsList;
    }
}
