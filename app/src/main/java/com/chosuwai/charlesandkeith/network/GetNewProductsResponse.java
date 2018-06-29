package com.chosuwai.charlesandkeith.network;

import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetNewProductsResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("apiVersion")
    private String apiVersion;

    @SerializedName("page")
    private String page;

    @SerializedName("newProducts")
    private List<NewProductsVO> newProducts;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<NewProductsVO> getNewProducts() {
        return newProducts;
    }

    public boolean isResponseOk() {
        return (code == 200 && newProducts != null);
    }
}
