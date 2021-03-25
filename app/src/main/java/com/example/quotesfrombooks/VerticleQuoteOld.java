package com.example.quotesfrombooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import me.kaelaela.verticalviewpager.VerticalViewPager;
import me.kaelaela.verticalviewpager.transforms.DefaultTransformer;

public class VerticleQuoteOld extends AppCompatActivity {
    private ViewPaperAdapter viewPaperAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verticle_quote_old);


        viewPaperAdapter = new ViewPaperAdapter(getApplicationContext());
        VerticalViewPager viewPager = (VerticalViewPager)findViewById(R.id.vertialpager);
        viewPager.setAdapter(viewPaperAdapter);
        viewPager.setPageTransformer(false, new DefaultTransformer());

    }
}