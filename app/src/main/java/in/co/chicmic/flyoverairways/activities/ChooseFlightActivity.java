package in.co.chicmic.flyoverairways.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import in.co.chicmic.flyoverairways.R;

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
