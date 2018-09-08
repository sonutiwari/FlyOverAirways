package in.co.chicmic.flyoverairways.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.adapters.OldCustomerRecyclerAdapter;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;

public class ChooseUserActivity extends AppCompatActivity {
    private RecyclerView mOldCustomeRecycler;
    private List<CustomerDetails> mCustomerDetailsList = new ArrayList<>();
    private OldCustomerRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        setUpRecycler();
    }

    private void setUpRecycler(){
        mOldCustomeRecycler = findViewById(R.id.rv_user_details);
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
