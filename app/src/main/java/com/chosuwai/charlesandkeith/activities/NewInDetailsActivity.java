package com.chosuwai.charlesandkeith.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chosuwai.charlesandkeith.R;
import com.chosuwai.charlesandkeith.adapters.NewInDetailsAdapter;

public class NewInDetailsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_in_details);

        RecyclerView rvDetailsItems=findViewById(R.id.rv_details_items);
        NewInDetailsAdapter newInDetailsAdapter=new NewInDetailsAdapter();
        rvDetailsItems.setAdapter(newInDetailsAdapter);
        rvDetailsItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }
}
