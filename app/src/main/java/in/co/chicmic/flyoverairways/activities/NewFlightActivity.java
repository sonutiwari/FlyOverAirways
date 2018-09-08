package in.co.chicmic.flyoverairways.activities;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import in.co.chicmic.flyoverairways.R;
import in.co.chicmic.flyoverairways.dataModels.FlightDataModel;
import in.co.chicmic.flyoverairways.utilities.Utils;

public class NewFlightActivity extends AppCompatActivity
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private Button mDatePickerBTN;
    private TextInputEditText mSourceTIE;
    private TextInputEditText mDestinationTIE;
    private TextInputEditText mFileNameTIE;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flight);
        setTitle(this.getClass().getSimpleName());
        initViews();
        setListeners();
    }

    private void initViews(){
        mDatePickerBTN = findViewById(R.id.btn_date_picker);
        mSourceTIE = findViewById(R.id.tie_source_edit_text);
        mDestinationTIE = findViewById(R.id.tie_dest_edit_text);
        mFileNameTIE = findViewById(R.id.tie_file_name);
    }

    private void setListeners(){
        findViewById(R.id.btn_save).setOnClickListener(this);
        mDatePickerBTN.setOnClickListener(this);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_save:
                validateInputAndSave();
                break;
            case R.id.btn_date_picker:
                pickDate();
                break;
            case R.id.btn_cancel:
                onBackPressed();
                break;
        }
    }

    private void validateInputAndSave() {
        String source = mSourceTIE.getText().toString().trim();
        String dest = mDestinationTIE.getText().toString().trim();
        String fileName = mFileNameTIE.getText().toString().trim();
        String dateTime = mDatePickerBTN.getText().toString().trim();
        if (source.isEmpty()){
            mSourceTIE.setError("Please Enter source");
            return;
        }
        if (dest.isEmpty()){
            mDestinationTIE.setError("Please enter destination");
            return;
        }
        if (fileName.isEmpty()){
            mFileNameTIE.setError("Please enter file name.");
        }
        if (validFileName(fileName)){
            //data = "\"source\":\"" + source + "\", \"destination
            ArrayList<Integer> seats = new ArrayList<>();
            for (int i = 0; i < 30; i++){
                seats.add(-1);
            }

            FlightDataModel model = new FlightDataModel();
            model.setSeats(seats);
            model.setSource(source);
            model.setDatetime(dateTime);
            model.setDestination(dest);
            Gson gson = new Gson();
            data = gson.toJson(model);
            Utils.writeFile(this, fileName, data);
            String ret = Utils.readFromFile(this, fileName);
            Toast.makeText(this, ret, Toast.LENGTH_LONG).show();
            onBackPressed();
        } else {
            mFileNameTIE.setError("Invalid FileName");
        }
    }

    private boolean validFileName(String fileName) {
        File f = new File(fileName);
        try {
            String path = f.getCanonicalPath();
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    private void pickDate(){
        Calendar calendar = Calendar.getInstance();
        int  mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(this, this, mYear, mMonth, mDay);
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mDatePickerBTN.setText(String.format(Locale.getDefault(), "%d/%d/%d", dayOfMonth, month + 1, year));
    }
}
