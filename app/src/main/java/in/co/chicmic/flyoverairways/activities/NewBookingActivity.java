package in.co.chicmic.flyoverairways.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.application.FlyOverAirways;
import in.co.chicmic.flyoverairways.dataModels.FlightDataModel;
import in.co.chicmic.flyoverairways.utilities.Utils;

public class NewBookingActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener{
    private TextView mFromToTV;
    private TextView mSeatsAvailableTV;
    private TextView mDepartureTimeTV;
    private Spinner mSeatSelector;
    private Button mConfirmButton;
    private Button mCancelButton;
    private int mSeatNo  = 0;
    private int mPassengerCode;
    private FlightDataModel mModel;
    private boolean isSpinnerClicked = false;
    private HashMap<Integer, Integer> map = new HashMap<>();
    private int pPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);
        initViews();
        setListeners();
        if (getIntent().getExtras() != null){
            Bundle bundle = getIntent().getExtras();
            mPassengerCode = bundle.getInt("passenger_code");
        }

        String json = Utils.readFromFile(this, FlyOverAirways.fileName);
        mModel = Utils.jsonToFlightModel(json);
        checkIfPassengerBookedAlready();
        checkIfNoSeatsAvailable();
        updateUI();
        setSpinner();
    }

    private void checkIfNoSeatsAvailable() {
        if (getAvailableSeats() == 0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("No Seats Available").setMessage("No seats available in this flight.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    NewBookingActivity.this.finish();
                }
            });
            builder.show();
        }
    }

    private void checkIfPassengerBookedAlready() {
        List<Integer> seats = mModel.getSeats();
        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i) == mPassengerCode){
                showAlertAndExit(i);
            }
        }
    }

    private void showAlertAndExit(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Already Booked").setMessage("You already booked your seat in this flight and your seat number is " + i + ".");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NewBookingActivity.this.finish();
            }
        });
        builder.show();
    }

    private void updateUI() {
        mFromToTV.setText(String.format(Locale.getDefault(), "%s To %s", mModel.getSource(), mModel.getDestination()));
        mSeatsAvailableTV.setText(String.format(Locale.getDefault(), "Seats Available: %d", getAvailableSeats()));
        mDepartureTimeTV.setText(String.format(Locale.getDefault(), "Departure Date: %s", mModel.getDatetime()));
    }

    private int getAvailableSeats() {
        List<Integer> seats = mModel.getSeats();
        int count = 0;
        for (int i = 0; i < seats.size(); i++) {
            if (seats.get(i) == -1){
                count++;
            }
        }
        return count;
    }

    private void setSpinner() {
        ArrayList<Integer> seats = new ArrayList<>();
        List<Integer>  temp = mModel.getSeats();
        int count  = 0;
        for (int i = 1; i < temp.size(); i++) {
            if (temp.get(i) == -1) {
                seats.add(i);
                map.put(count, i);
                count++;
            }
        }
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, seats);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSeatSelector.setAdapter(adapter);
    }

    private void initViews(){
        mFromToTV = findViewById(R.id.tv_from_to);
        mSeatsAvailableTV = findViewById(R.id.tv_seats_available);
        mDepartureTimeTV = findViewById(R.id.tv_arrival_time);
        mSeatSelector = findViewById(R.id.sp_seat_selector);
        mConfirmButton = findViewById(R.id.btn_confirm);
        mCancelButton = findViewById(R.id.btn_cancel);
    }

    private void setListeners(){
        mConfirmButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);
        mSeatSelector.setOnItemSelectedListener(this);
        mSeatSelector.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isSpinnerClicked = true;
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirm:
                writeDataInLocalFile();
                break;
            case R.id.btn_cancel:
                isSpinnerClicked = false;
                onBackPressed();
        }

    }

    private void writeDataInLocalFile() {
        if (isSpinnerClicked){
            map.remove(pPosition);
            List<Integer> seats = mModel.getSeats();
            if (mSeatNo >= 0) {
                seats.set(mSeatNo, mPassengerCode);
            } else {
                Toast.makeText(this, "Please selcet seat no", Toast.LENGTH_SHORT).show();
                return;
            }
            mModel.setSeats(seats);
            Utils.writeFile(this, FlyOverAirways.fileName, Utils.objectToJSONFlight(mModel));
            isSpinnerClicked = false;
            Toast.makeText(this, "Seat Booked Successfully", Toast.LENGTH_SHORT).show();
            onBackPressed();
        } else {
            Toast.makeText(this, "Please Select Seat No", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSeatNo = map.get(position);
        pPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
