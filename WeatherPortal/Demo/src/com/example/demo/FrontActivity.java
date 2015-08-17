package com.example.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class FrontActivity extends Activity {
	
  // Splash screen timer
    private static int SPLASH_TIME_OUT = 500;
    
 // Animation
    Animation animRotate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_front);
		
		// load the animation
        animRotate = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        
        //selecting an item
        //ImageView iw;
        //iw = (ImageView) findViewById(R.id.imageView2);
        
        
     // start the animation
        //iw.startAnimation(animRotate);
		
		new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(FrontActivity.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
	}

}
