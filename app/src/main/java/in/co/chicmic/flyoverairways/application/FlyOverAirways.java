package in.co.chicmic.flyoverairways.application;

import android.app.Application;

import com.google.gson.Gson;

import java.util.ArrayList;

import in.co.chicmic.flyoverairways.dataModels.FlightDataModel;
import in.co.chicmic.flyoverairways.utilities.Utils;

public class FlyOverAirways extends Application {
    public static String fileName  = "default";

    @Override
    public void onCreate() {
        super.onCreate();
            Utils.writeFile(this, fileName, getData());
    }

    public String getData() {
        ArrayList<Integer> seats = new ArrayList<>();
        for (int i = 0; i < 30; i++){
            seats.add(-1);
        }
        FlightDataModel model = new FlightDataModel();
        model.setSeats(seats);
        model.setSource("London");
        model.setDatetime("05/08/2018");
        model.setDestination("NewYork");
        Gson gson = new Gson();
        return gson.toJson(model);
    }
}
