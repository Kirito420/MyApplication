package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.niknovak.sevenweekmurphworkoutchallenge.R;

public class MainActivity extends AppCompatActivity {
    public static int onDay;

    public static SharedPreferences prefrences;
    public static SharedPreferences modePrefrence;
    public static SharedPreferences modePrefrence3;

    public static SharedPreferences settings;
    public static SharedPreferences settings3;
    public static SharedPreferences.Editor editor3;

    public static Boolean firstTime;

    public static Intent intent2;

    public static int mode;
    public static int modeP;





    //public static View circleView;

    public void choose(View view){

        int tappedCircle = Integer.parseInt(view.getTag().toString());
        if (tappedCircle + 1 <= onDay) {
            Intent intent = new Intent(getApplicationContext(), WarmupActivity.class);
            intent.putExtra("tappedCircle", tappedCircle);
            startActivity(intent);
            //finish();
        }
    }

    @Override
    public void onBackPressed() {
        /*if(onDay == 2 && Build.VERSION.SDK_INT < 28){
            Intent intent5 = new Intent(getApplicationContext(), SetReminderActivity.class);
            startActivity(intent5);
        }*/
        //do nothing
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
        setContentView(R.layout.activity_main);


        setTitle("7 Week Workout Challenge");


        try{
            MainActivity.modePrefrence3 = getApplicationContext().getSharedPreferences("PREFS_NAME3", 0);
            editor3 = MainActivity.settings3.edit();
            editor3.putInt("modePr3", MainActivity.onDay);
            editor3.apply();
        }catch(Exception e){}

        try{
            settings3 = getApplicationContext().getSharedPreferences("PREFS_NAME3", 0);
            onDay = settings3.getInt("modePr3", 0);
        }catch(Exception e){}

        settings = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
        modeP = settings.getInt("modePr", 0);

        //check if app is opened for the first time
        prefrences = getSharedPreferences("PREFRENCES", MODE_PRIVATE);
        firstTime = prefrences.getBoolean("firstTimeInstall", true);

        //onDay je tmno modre barve(tag == onDay), beli so ze narjeni (tag < onDay), plavi so se zaklenjeni (tag > onDay)

        //na vseh tagih (razen 0), imas 3 plasti krogcov, ki se ppelajo dol
        //int circleTag = Integer.parseInt(circleView.getTag().toString());

        StringBuilder buttonName = new StringBuilder();
        buttonName.append("imageButton");
        StringBuilder darkBlueButtonName = new StringBuilder();
        darkBlueButtonName.append("darkblueButton"); //original: "darkblueButton" odvisno od dizajna
        StringBuilder onDAYButtonName  = new StringBuilder();
        onDAYButtonName.append("imageButton");
        try {
            for (int b = 1; b < onDay; b++) {
                //peel two lears
                buttonName.append(b);
                darkBlueButtonName.append(b);
                String bn = buttonName.toString();
                String dbbn = darkBlueButtonName.toString();
                ImageButton circleButton = findViewById(getResources().getIdentifier(bn, "id", getPackageName()));
                ImageButton darkblueCircleButton = findViewById(getResources().getIdentifier(dbbn, "id", getPackageName()));
                circleButton.setAlpha(0);
                darkblueCircleButton.setAlpha(0);
                if (Integer.toString(b).length() == 2) {
                    buttonName.deleteCharAt(buttonName.length() - 1);
                    darkBlueButtonName.deleteCharAt(darkBlueButtonName.length() - 1);
                }
                buttonName.deleteCharAt(buttonName.length() - 1);
                darkBlueButtonName.deleteCharAt(darkBlueButtonName.length() - 1);
            }
            //peel one layer
            onDAYButtonName.append(onDay);
            String odbn = onDAYButtonName.toString();
            ImageButton onDayCircleButton = findViewById(getResources().getIdentifier(odbn, "id", getPackageName()));
            onDayCircleButton.setAlpha(0);

        }catch(Exception e){}


        if(firstTime){
            //if app was opened for the first time k
            onDay = 1;
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
