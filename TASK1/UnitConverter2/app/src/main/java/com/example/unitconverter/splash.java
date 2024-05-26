package com.example.unitconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class splash extends AppCompatActivity {
    private boolean isFlipped = false;
    ImageView logo;
    TextView title , from , ownername;
    Animation topanim , bottomanim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.logo);
        title = findViewById(R.id.title);
        from = findViewById(R.id.from);
        ownername = findViewById(R.id.ownername);
        topanim = AnimationUtils.loadAnimation(this , R.anim.top_anim);
        bottomanim = AnimationUtils.loadAnimation(this , R.anim.bottom_anim);
        title.setAnimation(bottomanim);
        ownername.setAnimation(bottomanim);
        from.setAnimation(bottomanim);

        flipCard();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        } , 4000);
    }

    private void flipCard() {
        float start = isFlipped ? 180f : 0f;
        float end = isFlipped ? 0f : 180f;

        ObjectAnimator flipAnimator = ObjectAnimator.ofFloat(logo, "rotationY", start, end);
        flipAnimator.setDuration(1500); // Adjust the duration as needed

        flipAnimator.start();
        isFlipped = !isFlipped;}

}