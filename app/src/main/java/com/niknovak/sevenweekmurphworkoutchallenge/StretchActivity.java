package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.niknovak.sevenweekmurphworkoutchallenge.SetReminderActivity.alarmManager;
import static com.niknovak.sevenweekmurphworkoutchallenge.SetReminderActivity.pendingIntent;

public class StretchActivity extends AppCompatActivity {
    int tappedCircle;
    Intent intent6;

    public void onBackClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "back");
        startActivity(intent6);
    }

    public void onPeckClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "peck");
        startActivity(intent6);
    }

    public void onQuadClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "quad");
        startActivity(intent6);
    }

    public void onForwardFoldClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "forward fold");
        startActivity(intent6);
    }

    public void stretchDone(View view){
        if (MainActivity.onDay == 1){
            Intent intent = new Intent(getApplicationContext(), SetReminderActivity.class);
            startActivity(intent);
            //set reminder activity
        }
        if (MainActivity.onDay == 49){
            try{
                alarmManager.cancel(pendingIntent);
                Intent intent = new Intent(getApplicationContext(), RateAppActivity.class);
                startActivity(intent);
            }catch(Exception e){}
        }

        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        Intent intent2 = new Intent(getApplicationContext(), SetReminderActivity.class);

        if (MainActivity.onDay == 1 && Build.VERSION.SDK_INT < 99) { //28
            if (tappedCircle + 1 == MainActivity.onDay)
                MainActivity.onDay++;
            startActivity(intent2);
        }else {
            if (tappedCircle + 1 == MainActivity.onDay)
                MainActivity.onDay++;
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
        setContentView(R.layout.activity_stretch);

        //Sample AdMob App ID: ca-app-pub-3940256099942544~3347511713
        //Real AdMob App ID: ca-app-pub-3137351105878660~5901616023

        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intetnt = getIntent();
        tappedCircle = intetnt.getIntExtra("tappedCircle", 0);


        int tc = tappedCircle + 1;
        setTitle("Stretch! (day " + tc + ")");
    }
}
