package in.co.chicmic.flyoverairways.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.adapters.CancelFightRecyclerAdapter;
import in.co.chicmic.flyoverairways.application.FlyOverAirways;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;
import in.co.chicmic.flyoverairways.dataModels.FlightDataModel;
import in.co.chicmic.flyoverairways.listeners.CancelFlightClickListener;
import in.co.chicmic.flyoverairways.utilities.Utils;

public class CancelFlightActivity extends AppCompatActivity implements CancelFlightClickListener{
    private RecyclerView mFlightRV;
    private CancelFightRecyclerAdapter mAdapter;
    private ArrayList<Integer> seats = new ArrayList<>();
    private FlightDataModel model;
    private HashMap<Integer, Integer> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_flight);
        setTitle(this.getClass().getSimpleName());
        setUpRecycler();
    }

    private void setUpRecycler() {
        mFlightRV = findViewById(R.id.rv_cancel);
        showData();
        mAdapter = new CancelFightRecyclerAdapter(seats, this);
        mFlightRV.setLayoutManager(new LinearLayoutManager(this));
        mFlightRV.hasFixedSize();
        mFlightRV.setAdapter(mAdapter);
    }

    @Override
    public void onCancelClick(int adapterPosition) {
        Toast.makeText(this, "Clicked at " + adapterPosition, Toast.LENGTH_SHORT).show();
        int index = map.get(adapterPosition);
        seats.set(index, -1);
        mAdapter.notifyDataSetChanged();
        model.setSeats(seats);
        Gson gson = new Gson();
        String json = gson.toJson(model);
        Utils.writeFile(this, FlyOverAirways.fileName, json);
        map.remove(adapterPosition);
        onBackPressed();
    }

    private void showData() {
        String json = Utils.readFromFile(this, FlyOverAirways.fileName);
        Gson gson = new Gson();;
        model = gson.fromJson(json, FlightDataModel.class);
        seats.clear();
        List<Integer> temp = model.getSeats();
        Log.e("Sonu", "showData: " + temp.size() );
        seats.addAll(temp);
    }

    private void getAllSelectedData(){
        int count = 0;
        for (int i = 0; i < seats.size(); i++){
            if (seats.get(i) != -1){
                map.put(count, i);
            }
        }
    }

}
