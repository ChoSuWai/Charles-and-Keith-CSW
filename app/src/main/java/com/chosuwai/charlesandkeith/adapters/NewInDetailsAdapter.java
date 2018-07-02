package com.chosuwai.charlesandkeith.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.activities.NewInDetailsActivity;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;
import com.chosuwai.charlesandkeith.viewholders.NewInDetailsViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewInDetailsAdapter extends RecyclerView.Adapter<NewInDetailsViewHolder> {

    private List<NewProductsVO> itemsList;
    private ItemsDelegate mItemDelegate;

/*
    public NewInDetailsAdapter(ItemsDelegate itemsDelegate){
        mItemDelegate=itemsDelegate;
        itemsList=new ArrayList<>();
    }*/

    public NewInDetailsAdapter(NewInDetailsActivity mNewInDetailActivity){

    }

    @NonNull
    @Override
    public NewInDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.view_holder_new_in_details, parent, false);
        return new NewInDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewInDetailsViewHolder holder, int position) {
        //holder.setItemsData(itemsList.get(position));
    }



    @Override
    public int getItemCount() {
        return 7;
    }

    /*
    public void setItemsList(List<NewProductsVO> itemsList){
        this.itemsList=itemsList;
        notifyDataSetChanged();
    }*/
}
