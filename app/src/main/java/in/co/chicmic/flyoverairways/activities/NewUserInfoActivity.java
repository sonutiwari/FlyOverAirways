package in.co.chicmic.flyoverairways.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.dataModels.CustomerDetails;
import in.co.chicmic.flyoverairways.database.TinyDB;

public class NewUserInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private TextInputEditText mPassengerCodeEditText;
    private TextInputEditText mFullNameEditText;
    private TextInputEditText mAddressEditText;
    private CheckBox mEatHalalCheckBox;
    private TinyDB mTinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_info);
        mTinyDB = new TinyDB(getApplicationContext());
        initViews();
        setListeners();
    }

    private void initViews() {
        mPassengerCodeEditText = findViewById(R.id.tie_passenger_code);
        mFullNameEditText = findViewById(R.id.tie_full_name);
        mAddressEditText = findViewById(R.id.tie_address);
        mEatHalalCheckBox = findViewById(R.id.cb_halal);
    }

    private void setListeners(){
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                verifyInputAndSave();
                break;
            case R.id.btn_cancel:
                onBackPressed();
                break;
        }
    }

    private void verifyInputAndSave() {
        String passengerCode;
        String fullName;
        String address;
        if (mPassengerCodeEditText.getText() != null) {
            passengerCode = mPassengerCodeEditText.getText().toString().trim();
        } else {
            mPassengerCodeEditText.setError("Please enter passenger code.");
            return;
        }

        if (mFullNameEditText.getText() != null) {
            fullName = mFullNameEditText.getText().toString().trim();
        } else {
            mFullNameEditText.setError("Please enter your full name.");
            return;
        }

        if (mAddressEditText.getText() != null) {
            address = mAddressEditText.getText().toString().trim();
        } else {
            mAddressEditText.setError("Please enter address.");
            return;
        }

        if (passengerCode.length() < 6){
            mPassengerCodeEditText.setError("Passenger code should be 6 digit long.");
            return;
        } else if (fullName.isEmpty()){
            mFullNameEditText.setError("Please enter your full name.");
            return;
        } else if (address.isEmpty()){
            mAddressEditText.setError("Please enter address.");
            return;
        }
        CustomerDetails model = new CustomerDetails();
        model.setPassengerCode(Integer.parseInt(passengerCode));
        model.setFullName(fullName);
        model.setAddress(address);
        model.setEatsHalal(mEatHalalCheckBox.isChecked());
        if (mTinyDB.getListObject("user", CustomerDetails.class) != null){
            ArrayList<Object> list = mTinyDB.getListObject("user", CustomerDetails.class);
            for(Object item: list){
                CustomerDetails customerDetails = (CustomerDetails) item;
               if (customerDetails.getPassengerCode().equals(model.getPassengerCode())){
                   Toast.makeText(this, "Already Present", Toast.LENGTH_SHORT).show();
                   return;
               }
            }
            list.add(model);
            mTinyDB.putListObject("user", list);
        }else {
            ArrayList<Object> list = new ArrayList<>();
            list.add(model);
            mTinyDB.putListObject("user", list);
        }
        showData();
        onBackPressed();
    }

    private void showData() {
        if (mTinyDB.getListObject("user", CustomerDetails.class) != null){
            ArrayList<Object> list = mTinyDB.getListObject("user", CustomerDetails.class);
            Toast.makeText(this, String.valueOf(list.size()), Toast.LENGTH_SHORT).show();
        }
    }
}
