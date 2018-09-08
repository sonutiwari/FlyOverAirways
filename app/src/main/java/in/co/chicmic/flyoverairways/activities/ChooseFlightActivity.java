package in.co.chicmic.flyoverairways.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;
import java.util.Locale;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.application.FlyOverAirways;
import in.co.chicmic.flyoverairways.dataModels.FlightDataModel;
import in.co.chicmic.flyoverairways.utilities.Utils;

public class ChooseFlightActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText mFileName;
    private Button mChooseFile;
    private TextView mSourceTV;
    private TextView mDestTV;
    private TextView mSeatsAvailable;
    private TextView mDepartureTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_flight);
        initViews();
        setListeners();
        updateUI(Utils.readFromFile(this, FlyOverAirways.fileName));
    }

    private void initViews() {
        mFileName = findViewById(R.id.tie_filename);
        mChooseFile = findViewById(R.id.btn_choose_file);
        mSourceTV = findViewById(R.id.tv_source);
        mDestTV = findViewById(R.id.tv_dest);
        mSeatsAvailable = findViewById(R.id.tv_seats_available);
        mDepartureTV = findViewById(R.id.tv_departure_time);
    }

    private void setListeners() {
        findViewById(R.id.btn_new_flight).setOnClickListener(this);
        mChooseFile.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_new_flight:
                startActivity(new Intent(this, NewFlightActivity.class));
                break;
            case R.id.btn_choose_file:
                verifyFileExistenceAndUpdateData();
                break;
        }
    }

    private void verifyFileExistenceAndUpdateData() {
        String fileName = mFileName.getText().toString().trim();
        if (fileName.isEmpty()){
            mFileName.setError("Please enter filename");
        } else {
            String data = Utils.readFromFile(this, fileName);
            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
            updateUI(data);
        }
    }

    private void updateUI(String data) {
        Gson gson = new Gson();
        FlightDataModel model = gson.fromJson(data, FlightDataModel.class);
        mDepartureTV.setText( String.format(Locale.getDefault(), "Departure Date: %s", model.getDatetime()));
        mSourceTV.setText(model.getSource());
        mDestTV.setText(model.getDestination());
        int seatsAvailable = getAvailableSeats(model);
        mSeatsAvailable.setText(String.format(Locale.getDefault(), "Seats Available: %d", seatsAvailable));
    }

    private int getAvailableSeats(FlightDataModel model) {
        List<Integer> mList = model.getSeats();
        int count  = 0;
        for (int i: mList){
            if (i == -1){
                count++;
            }
        }
        return count;
    }
}
