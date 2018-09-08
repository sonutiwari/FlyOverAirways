package in.co.chicmic.flyoverairways.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.adapters.CancelFightRecyclerAdapter;
import in.co.chicmic.flyoverairways.adapters.OldCustomerRecyclerAdapter;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;
import in.co.chicmic.flyoverairways.dataModels.FlightDataModel;
import in.co.chicmic.flyoverairways.listeners.CancelFlightClickListener;

public class CancelFlightActivity extends AppCompatActivity implements CancelFlightClickListener{
    private RecyclerView mFlightRV;
    private List<FlightDataModel> mFlightList = new ArrayList<>();
    private CancelFightRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_flight);
        setTitle(this.getClass().getSimpleName());
        setUpRecycler();
    }

    private void setUpRecycler() {
        mFlightRV = findViewById(R.id.rv_cancel);
        putDummyData();
        mAdapter = new CancelFightRecyclerAdapter(mFlightList.get(0).getSeats(), this);
        mFlightRV.setLayoutManager(new LinearLayoutManager(this));
        mFlightRV.hasFixedSize();
        mFlightRV.setAdapter(mAdapter);
    }

    private void putDummyData() {
        FlightDataModel model = new FlightDataModel();
        model.setDatetime("21/08/2018 12:34:50");
        model.setDestination("London");
        model.setSource("New Delhi");
        ArrayList<Integer> seats = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            if (i % 5 == 0){
                seats.add(-1);
            } else {
                seats.add(i);
            }
        }
        model.setSeats(seats);
        mFlightList.add(model);
    }

    @Override
    public void onCancelClick(int adapterPosition) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
    }
}
