package in.co.chicmic.flyoverairways.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import in.co.chicmic.flyoverairways.R;

public class NewFlightActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flight);
        setListeners();
    }

    private void setListeners(){
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_date_picker).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                onBackPressed();
                break;
            case R.id.btn_date_picker:
                onBackPressed();
                break;
            case R.id.btn_cancel:
                onBackPressed();
                break;
        }
    }
}
