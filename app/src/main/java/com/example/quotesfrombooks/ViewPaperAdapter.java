package com.example.quotesfrombooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPaperAdapter extends PagerAdapter {
    private Context context;
    public String englishWords[] = {
            "Emotion",
            "Extreme",
            "Special",
            "Holiday"
    };
    public String meaningWords[] = {
            "Cảm xúc",
            "Vô cùng",
            "Đặc biệt",
            "Kì nghỉ"
    };
    public String noteWords[] = {
      "Nothing",
      "Nothing",
      "Nothing",
            "Nothing"
    };

    public ViewPaperAdapter(Context context) {
        this.context = context;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflate = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        View view = inflate.inflate(R.layout.vertical_scrolling,container,false);

        TextView tacgia = (TextView)view.findViewById(R.id.tacgia);
        TextView noidung = (TextView)view.findViewById(R.id.noidung);
        TextView tensach = (TextView)view.findViewById(R.id.tensach);


        tacgia.setText(englishWords[position]);
        noidung.setText(meaningWords[position]);
        tensach.setText(noteWords[position]);
        container.addView(view);


        view.setRotation(-90f);
        return view;
    }

    @Override
    public int getCount() {
        return englishWords.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view ==(RelativeLayout)object);
    }

}
