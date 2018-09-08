package in.co.chicmic.flyoverairways.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.adapters.OldCustomerRecyclerAdapter;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;
import in.co.chicmic.flyoverairways.database.TinyDB;
import in.co.chicmic.flyoverairways.listeners.ChooseUserListener;

public class ChooseUserActivity extends AppCompatActivity implements ChooseUserListener{
    private RecyclerView mOldCustomeRecycler;
    private List<CustomerDetails> mCustomerDetailsList = new ArrayList<>();
    private OldCustomerRecyclerAdapter mAdapter;
    private TinyDB mTinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_user);
        mTinyDB = new TinyDB(getApplicationContext());
        setUpRecycler();
    }

    private void setUpRecycler(){
        mOldCustomeRecycler = findViewById(R.id.rv_user_details);
        mOldCustomeRecycler.setLayoutManager(new LinearLayoutManager(this));
        mOldCustomeRecycler.hasFixedSize();
        mAdapter = new OldCustomerRecyclerAdapter(mCustomerDetailsList, this);
        mOldCustomeRecycler.setAdapter(mAdapter);
        putDummyData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
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

    private void showData() {
        if (mTinyDB.getListObject("user", CustomerDetails.class) != null){
            mCustomerDetailsList.clear();
            ArrayList<Object> list = mTinyDB.getListObject("user", CustomerDetails.class);
            for (Object item : list){
                mCustomerDetailsList.add((CustomerDetails) item);
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void viewClickAtPosition(int adapterPosition) {
        Intent intent = new Intent(this, NewBookingActivity.class);
        intent.putExtra("passenger_code", mCustomerDetailsList.get(adapterPosition).getPassengerCode());
        startActivity(intent);
    }
}
