package com.example.quotesfrombooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private static final String TAG = "Swipe Position";
    float x1, x2, y1, y2;
    public static int MIN_DISTANCE = 150;
    private GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.gestureDetector = new GestureDetector(MainActivity.this, this);

        ImageView truotlen = (ImageView) findViewById(R.id.truotlen);
        ImageView hinh = (ImageView)findViewById(R.id.hinh);
        ImageView infoicon = (ImageView)findViewById(R.id.infoicon);
        infoicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoicon.startAnimation(buttonClick);
                Intent it = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(it);
            }
        });
//        THAY DOI BACKGROUND THEO MOI NGAY TRONG THANG.
        RelativeLayout bgElement = (RelativeLayout) findViewById(R.id.basebackground);
//        bgElement.setBackgroundResource(R.drawable.backgroundhome1);
        int arr_bg[] = {R.drawable.backgroundhome1,
                        R.drawable.backgroundhome2,
                        R.drawable.backgroundhome3,
                        R.drawable.backgroundhome4,
                R.drawable.backgroundhome5,
                R.drawable.backgroundhome6,
                R.drawable.backgroundhome7,
                R.drawable.backgroundhome8,
                R.drawable.backgroundhome9,
                R.drawable.backgroundhome10,
                R.drawable.backgroundhome11,
                R.drawable.backgroundhome12,
                R.drawable.backgroundhome13,
                R.drawable.backgroundhome14,
                R.drawable.backgroundhome15,
                R.drawable.backgroundhome16,
                R.drawable.backgroundhome17,
                R.drawable.backgroundhome18,
                R.drawable.backgroundhome19,
                R.drawable.backgroundhome20,
                R.drawable.backgroundhome21,
                R.drawable.backgroundhome22,
                R.drawable.backgroundhome23,
                R.drawable.backgroundhome24,
                R.drawable.backgroundhome25,
                R.drawable.backgroundhome26,
                R.drawable.backgroundhome27,
                R.drawable.backgroundhome28,
                R.drawable.backgroundhome29,
                R.drawable.backgroundhome30,
                R.drawable.backgroundhome31
        };
        java.util.Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
        Log.d("ccc","ngayyyyyyyyyyyy"+dayofmonth);
        bgElement.setBackgroundResource(arr_bg[dayofmonth-1]);
        Animation animFade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);

        truotlen.startAnimation(animFade);
        LottieAnimationView animation_popup = (LottieAnimationView)findViewById(R.id.animation_popup);
        hinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                animation_popup.playAnimation();

                Intent it = new Intent(MainActivity.this, hienthicau.class);

                startActivity(it);
            }
        });
        truotlen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                truotlen.startAnimation(buttonClick);
                startActivity(new Intent(MainActivity.this, QuoteOld.class));
                overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();

                break;
            case MotionEvent.ACTION_UP:
                x2 = event.getX();
                y2 = event.getY();

                float valueX = x2 - x1;
                float valueY = y2 - y1;

                if(Math.abs(valueY) > MIN_DISTANCE) {
                    // detect top to bottom
                    if(y2<=y1) {
                        startActivity(new Intent(MainActivity.this, QuoteOld.class));
                        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
                    }
                }


        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


}