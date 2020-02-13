package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


public class WorkoutActivity extends AppCompatActivity {
    Button squatsButton;
    Button pushupsButton;
    Button chinupsButton;
    int squatsAmount;
    int pushupsAmount;
    int chinupsAmount;
    boolean restDay = false;


    public static int squatsReps;
    public static int restSquatsReps;

    public static int pushupsReps;
    public static int restPushupsReps;

    public static int chinupReps;
    public static int restChinupReps;

    public static int squatsMultiplier;
    public static int pushupsMultiplier;
    public static int chinupsMultiplier;

    int tappedCircle;
    Intent intent6;


    public void onSquatClick (View view){
    if(Build.VERSION.SDK_INT > 18)
        intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
    else
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
    intent6.putExtra("exercise", "squat");
    startActivity(intent6);
    }

    public void onPushupClick (View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
    intent6.putExtra("exercise", "pushup");
    startActivity(intent6);
    }

    public void onChinupClick (View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
    intent6.putExtra("exercise", "chinup");
    startActivity(intent6);
    }


    public void workoutDone(View view){
        if (restDay) {
            Intent intent = new Intent(getApplicationContext(), StretchActivity.class);
            intent.putExtra("tappedCircle", tappedCircle);
            startActivity(intent);
        } else {
            Intent intent = new Intent(getApplicationContext(), RestTimerActivity.class);
            intent.putExtra("set", "first");
            intent.putExtra("tappedCircle", tappedCircle);
            startActivity(intent);
        }
    }

    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.titleColor));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);

        //Sample AdMob App ID: ca-app-pub-3940256099942544~3347511713
        //Real AdMob App ID: ca-app-pub-3137351105878660~5901616023

        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intetnt = getIntent();
        tappedCircle = intetnt.getIntExtra("tappedCircle", 0);


        if (MainActivity.modeP == 1) {
            squatsReps = 30;
            restSquatsReps = 10;

            pushupsReps = 20;
            restPushupsReps = 7;

            chinupReps = 10;
            restChinupReps = 4;

            squatsMultiplier = 3;
            pushupsMultiplier = 2;
            chinupsMultiplier = 1;
        } else if (MainActivity.modeP == 2){
            squatsReps = 60;
            restSquatsReps = 20;

            pushupsReps = 40;
            restPushupsReps = 14;

            chinupReps = 20;
            restChinupReps = 7;

            squatsMultiplier = 6;
            pushupsMultiplier = 4;
            chinupsMultiplier = 2;
        } else if (MainActivity.modeP == 3){
            squatsReps = 84;
            restSquatsReps = 30;

            pushupsReps = 56;
            restPushupsReps = 20;

            chinupReps = 28;
            restChinupReps = 10;

            squatsMultiplier =  9;
            pushupsMultiplier = 6;
            chinupsMultiplier = 3;
        }


        if(tappedCircle % 2 == 0){
            squatsAmount = squatsReps + squatsMultiplier*(tappedCircle/2);
            pushupsAmount = pushupsReps + pushupsMultiplier*(tappedCircle/2);
            chinupsAmount = chinupReps + chinupsMultiplier*(tappedCircle/2);
            squatsAmount = squatsAmount / 4;
            pushupsAmount = pushupsAmount / 4;
            chinupsAmount = chinupsAmount / 4;
        } else {
            restDay = true;
            squatsAmount = restSquatsReps;
            pushupsAmount = restPushupsReps;
            chinupsAmount = restChinupReps;
        }



        int tc = tappedCircle + 1;
        if(restDay)
            setTitle("Workout (day " + tc + ")");
        else
            setTitle("First Set (day " + tc + ")");

        squatsButton = findViewById(R.id.chinupButton);
        pushupsButton = findViewById(R.id.peckStretchButton);
        chinupsButton = findViewById(R.id.quadStretchButton);
        String squatsButtonText = String.format("%d  SQUATS", squatsAmount);
        String pushupsButtonText = String.format("%d  PUSHUPS", pushupsAmount);
        String chinupsButtonText = String.format("%d  PULLUPS", chinupsAmount);
        squatsButton.setText(squatsButtonText);
        pushupsButton.setText(pushupsButtonText);
        chinupsButton.setText(chinupsButtonText);
        squatsButton.setTextSize(20);
        pushupsButton.setTextSize(20);
        chinupsButton.setTextSize(20);
    }
}
