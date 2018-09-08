package in.co.chicmic.flyoverairways.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


import in.co.chicmic.flyoverairways.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startTimer(3000);
    }

    void startTimer(int SPLASH_TIME_OUT) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(SplashScreenActivity.this, getString(R.string.welcome), Toast.LENGTH_LONG).show();
                Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
