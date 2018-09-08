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

public class ChooseFlightActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight);
        setListeners();
    }

    private void setListeners() {
        findViewById(R.id.btn_new_flight);
        findViewById(R.id.btn_choose_file);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_flight:
                startActivity(new Intent(this, NewFlightActivity.class));
                break;
            case R.id.btn_choose_file:
                break;
        }
    }
}
