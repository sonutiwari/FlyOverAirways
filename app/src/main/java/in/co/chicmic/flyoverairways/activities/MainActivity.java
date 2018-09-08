package in.co.chicmic.flyoverairways.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListeners();
    }

    private void setListeners() {
        findViewById(R.id.btn_customer_details).setOnClickListener(this);
        findViewById(R.id.btn_new_flight).setOnClickListener(this);
        findViewById(R.id.btn_cancel_flight).setOnClickListener(this);
        findViewById(R.id.btn_flight_details).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_customer_details:
                startActivity(new Intent(this, CustomerDetailsActivity.class));
                break;
            case R.id.btn_new_flight:
                startActivity(new Intent(this, ChooseUserActivity.class));
                break;
            case R.id.btn_cancel_flight:
                startActivity(new Intent(this, CancelFlightActivity.class));
                break;
            case R.id.btn_flight_details:
                startActivity(new Intent(this, ChooseFlightActivity.class));
                break;
        }
    }
}
