package com.example.calculator;

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
        from = findViewById(R.id.from);
        ownername = findViewById(R.id.ownername);
        topanim = AnimationUtils.loadAnimation(this , R.anim.top_anim);
        bottomanim = AnimationUtils.loadAnimation(this , R.anim.bottom_anim);
        logo.setAnimation(topanim);
        ownername.setAnimation(bottomanim);
        from.setAnimation(bottomanim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this , MainActivity.class);
                startActivity(intent);
                finish();
            }
        } , 4000);
    }



}