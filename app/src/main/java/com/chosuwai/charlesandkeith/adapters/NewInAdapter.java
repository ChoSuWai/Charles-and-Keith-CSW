package com.chosuwai.charlesandkeith.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.data.vos.NewProductsVO;
import com.chosuwai.charlesandkeith.delegates.ItemsDelegate;
import com.chosuwai.charlesandkeith.viewholders.NewInViewHolder;

import java.util.ArrayList;
import java.util.List;

public class NewInAdapter extends RecyclerView.Adapter<NewInViewHolder> {

    private ItemsDelegate mItemDelegate;
    private List<NewProductsVO> mNewProductsList;

    public NewInAdapter(ItemsDelegate itemsDelegate) {
        mItemDelegate = itemsDelegate;
        mNewProductsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public NewInViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.view_holder_new_in, parent, false);
            return new NewInViewHolder(view, mItemDelegate);


    }

    @Override
    public void onBindViewHolder(@NonNull NewInViewHolder holder, int position) {
        holder.setNewProductsData(mNewProductsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNewProductsList.size();
    }


    public void setNewProductsList(List<NewProductsVO> newProducts) {
        mNewProductsList = newProducts;
        notifyDataSetChanged();
    }

    public void appendNewProductsList(List<NewProductsVO> itemsList){
        mNewProductsList.addAll(itemsList);
        notifyDataSetChanged();
    }
}
