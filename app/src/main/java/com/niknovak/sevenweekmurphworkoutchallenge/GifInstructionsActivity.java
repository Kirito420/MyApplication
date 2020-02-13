package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GifInstructionsActivity extends AppCompatActivity {
    WebView webView;
    TextView textView;
    String exercise;
    String text5;
    String text3;
    String text2;
    String text;
    String text4;

    public void setTitle(String title) {
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
        setContentView(R.layout.activity_gif_instructions);

        setTitle("Instructions");

        webView = findViewById(R.id.gifWebView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intetnt = getIntent();
        exercise = intetnt.getStringExtra("exercise");
        try {
            switch (exercise) {
                case ("loginChinup"):
                    String file5 = "file:android_asset/resizedchinup.gif";
                    webView.loadUrl(file5); //ne dela na api 18
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text5 = "▸ hands shoulder width apart\n▸ squeeze your butt and abs\n▸ chin above bar\n▸ exhale on the way up";
                    else
                        text5 = "- hands shoulder width apart\n- squeeze your butt and abs\n- chin above bar\n- exhale on the way up";
                    textView.setText(text5);
                    break;
                case ("chinup"):
                    String file = "file:android_asset/resizedchinup.gif";
                    webView.loadUrl(file); //ne dela na api 18
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text = "▸ hands shoulder width apart\n▸ squeeze your butt and abs\n▸ chin above bar\n▸ exhale on the way up\n▸ go to the next exercise only when you complete all the repetitions of this one";
                    else
                        text = "- hands shoulder width apart\n- squeeze your butt and abs\n- chin above bar\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
                    textView.setText(text);
                    break;
                case ("pushup"):
                    String file2 = "file:android_asset/resizedpushup.gif";
                    webView.loadUrl(file2);
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text2 = "▸ fully extend your elbows\n▸ hands below shoulders\n▸ squeeze your butt and abs\n▸ exhale on the way up\n▸ go to the next exercise only when you complete all the repetitions of this one";
                    else
                        text2 = "- fully extend your elbows\n- hands below shoulders\n- squeeze your butt and abs\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
                    textView.setText(text2);
                    break;
                case ("squat"):
                    String file3 = "file:android_asset/resizedsqut.gif";
                    webView.loadUrl(file3);
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text3 = "▸ keep your back straight\n▸ don't let your knees pastyour toes\n▸ squeeze your butt when youcome up\n▸ exhale on the way up\n▸ go to the next exercise only when you complete all the repetitions of this one";
                    else
                        text3 = "- keep your back straight\n- don't let your knees pastyour toes\n- squeeze your butt when youcome up\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
                    textView.setText(text3);
                    break;
                case ("jj"):
                    String file4 = "file:android_asset/resizedjj.gif";
                    webView.loadUrl(file4);
                    textView = findViewById(R.id.instructionsTextView);
                    String text4 = "Perform this exercise\nfor about a minute.";
                    textView.setText(text4);
                    break;
                default:
                    //nothing
            }
        }catch(Error e){

        }
    }
}
