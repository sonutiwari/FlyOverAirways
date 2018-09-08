package in.co.chicmic.flyoverairways.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.adapters.OldCustomerRecyclerAdapter;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;

public class CustomerDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private RecyclerView mOldCustomeRecycler;
    private List<CustomerDetails> mCustomerDetailsList = new ArrayList<>();
    private OldCustomerRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        findViewById(R.id.btn_new_user).setOnClickListener(this);
        setUpRecycler();
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, NewUserInfoActivity.class));
    }

    private void setUpRecycler(){
        mOldCustomeRecycler = findViewById(R.id.rv_old_customer);
        mOldCustomeRecycler.setLayoutManager(new LinearLayoutManager(this));
        mOldCustomeRecycler.hasFixedSize();
        mAdapter = new OldCustomerRecyclerAdapter(mCustomerDetailsList);
        mOldCustomeRecycler.setAdapter(mAdapter);
        putDummyData();
    }

    private void putDummyData() {
        for (int i = 0; i < 15; i++) {
            CustomerDetails customerDetails = new CustomerDetails();
            customerDetails.setAddress("Ramnagar");
            customerDetails.setEatsHalal(false);
            customerDetails.setFullName("Passenger" + i);
            customerDetails.setPassengerCode(i);
            mCustomerDetailsList.add(customerDetails);
        }
        mAdapter.notifyDataSetChanged();
    }
}
