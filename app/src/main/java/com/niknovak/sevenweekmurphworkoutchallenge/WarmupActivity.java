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

public class WarmupActivity extends AppCompatActivity {



    int tappedCircle;
    Intent intent6;

    public void onRollClick(View view){
        intent6 = new Intent(getApplicationContext(), TextInstructionsActivity.class);
        startActivity(intent6);
    }

    public void onJJClick(View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "jj");
        startActivity(intent6);
    }

    public void warmupDone(View view){

        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);

        intent.putExtra("tappedCircle", tappedCircle);
        startActivity(intent);
        //finish();
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
        setContentView(R.layout.activity_warmup);

        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3137351105878660~5901616023"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intetnt = getIntent();
        tappedCircle = intetnt.getIntExtra("tappedCircle", 0);

        int tc = tappedCircle + 1;
        setTitle("Warmup (day " + tc + ")");
    }
}
