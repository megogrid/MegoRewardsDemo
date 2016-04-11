package com.megogrid.megoward;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


import com.megogrid.megoauth.MegoAuthorizer;
import com.megogrid.megorewards.MegorewardsController;



public class SplashActivity extends Activity {

    private Animation animation;
    private ImageView logo;
    private TextView title2_txt;
    private TextView title_txt;
    String appId, secretKey;
    MegoAuthorizer megoAuthorizer;
//    private ActionSdkIntializer actionSdkIntializer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MegoAuthorizer megoAuthorizer = new MegoAuthorizer(SplashActivity.this);
        megoAuthorizer.intializeSDK();


        MegorewardsController.initialiseSDK(SplashActivity.this);

        logo = (ImageView) findViewById(R.id.logo_img);
        title_txt = (TextView) findViewById(R.id.track_txt);
        title2_txt = (TextView) findViewById(R.id.pro_txt);

        if (savedInstanceState == null) {
            flyIn();
        }

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                endSplash();
            }
        }, 3000);
    }

    private void flyIn() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        logo.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation);
        title2_txt.startAnimation(animation);
    }

    private void endSplash() {
        animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation_back);
        logo.startAnimation(animation);

        animation = AnimationUtils.loadAnimation(this, R.anim.pro_animation_back);
        title2_txt.startAnimation(animation);

        animation.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationStart(Animation arg0) {
            }
        });

    }

    @Override
    public void onBackPressed() {
        // Do nothing
    }

}
