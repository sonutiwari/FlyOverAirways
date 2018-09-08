package in.co.chicmic.flyoverairways.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.co.chicmic.flyoverairways.R;

public class NewBookingActivity extends AppCompatActivity implements View.OnClickListener, Spinner.OnItemSelectedListener{
    private TextView mFromToTV;
    private TextView mSeatsAvailableTV;
    private TextView mDepartureTimeTV;
    private Spinner mSeatSelector;
    private Button mConfirmButton;
    private Button mCancelButton;
    private int mSeatNo  = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_booking);
        setTitle(this.getClass().getSimpleName());
        initViews();
        setListeners();
        setSpinner();
    }

    private void setSpinner() {
        ArrayList<Integer> seats = new ArrayList<>();
        for (int i = 1; i < 30; i++) {
            seats.add(i);
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
        mConfirmButton.setOnClickListener(this);
        mSeatSelector.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mSeatNo = position + 1;
        Toast.makeText(this, "You selected Seat No: " + mSeatNo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
