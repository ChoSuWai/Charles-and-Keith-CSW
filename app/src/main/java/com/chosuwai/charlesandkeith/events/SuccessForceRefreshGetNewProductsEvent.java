package com.chosuwai.charlesandkeith.events;

import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;

import java.util.List;

public class SuccessForceRefreshGetNewProductsEvent extends SuccessGetNewProductsEvent {
    public SuccessForceRefreshGetNewProductsEvent(List<NewProductsVO> newProductsList) {
        super(newProductsList);
    }
}
