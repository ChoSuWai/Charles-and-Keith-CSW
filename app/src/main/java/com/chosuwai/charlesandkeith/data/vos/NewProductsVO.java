package com.chosuwai.charlesandkeith.data.vos;

import com.google.gson.annotations.SerializedName;

public class NewProductsVO {

    @SerializedName("product-id")
    private String productId;

    @SerializedName("product-image")
    private String productImage;

    @SerializedName("product-title")
    private String productTitle;

    public String getProductId() {
        return productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductTitle() {
        return productTitle;
    }
}
