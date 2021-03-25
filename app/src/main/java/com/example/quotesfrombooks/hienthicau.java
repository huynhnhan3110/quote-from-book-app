package com.example.quotesfrombooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class hienthicau extends AppCompatActivity {
    myDbAdapter helper;
    private RelativeLayout backgroundhienthi;
    private LinearLayout barbottom;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    ImageView watermarkLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hienthicau);
        watermarkLogo = (ImageView)findViewById(R.id.logowatermark);
        backgroundhienthi = (RelativeLayout)findViewById(R.id.backgroundhienthi);
        barbottom = (LinearLayout)findViewById(R.id.barbottom);
        CheckBox heart = (CheckBox)findViewById(R.id.heart);
        CheckBox morebtn = (CheckBox) findViewById(R.id.morebutton);
        BottomNavigationView bt = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        bt.setItemIconTintList(null);
        bt.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int mMenuId = item.getItemId();
                for (int i = 0; i < bt.getMenu().size(); i++) {
                    MenuItem menuItem = bt.getMenu().getItem(i);
                    boolean isChecked = menuItem.getItemId() == item.getItemId();
                    menuItem.setChecked(isChecked);
                }

                switch (item.getItemId()) {
                    case R.id.share: {
//                        Message.message(getApplicationContext(), "");
                        bt.setVisibility(View.INVISIBLE);
                        barbottom.setVisibility(View.INVISIBLE);

                        share();
                        bt.setVisibility(View.VISIBLE);
                        barbottom.setVisibility(View.VISIBLE);
                    }
                    break;
                    case R.id.downloads: {
                        bt.setVisibility(View.INVISIBLE);
                        barbottom.setVisibility(View.INVISIBLE);
                        watermarkLogo.setVisibility(View.VISIBLE);
                        ActivityCompat.requestPermissions(hienthicau.this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                1);
                    }

                    break;
                    case R.id.contact: {
                        bt.setVisibility(View.INVISIBLE);
                        Intent it = new Intent(hienthicau.this, phanhoi.class);
                        startActivity(it);
                    }
                    break;
                }
                return true;
            }
        });

        morebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()) {
                    bt.setVisibility(View.VISIBLE);
                } else {
                    bt.setVisibility(View.INVISIBLE);
                }

            }
        });
        RelativeLayout bght = (RelativeLayout) findViewById(R.id.backgroundhienthi);
        int arr_bght[] = {R.drawable.q1,
                R.drawable.q2,
                R.drawable.q3,
                R.drawable.q4,
                R.drawable.q5,
                R.drawable.q6,
                R.drawable.q7,
                R.drawable.q8,
                R.drawable.q9,
                R.drawable.q10,
                R.drawable.q12,
                R.drawable.q13,
                R.drawable.q14,
                R.drawable.q15,
                R.drawable.q16,
                R.drawable.q17,
                R.drawable.q18,
                R.drawable.q19,
                R.drawable.q21,
                R.drawable.q22,
                R.drawable.q23,
                R.drawable.q24,
                R.drawable.q27,
                R.drawable.q29,
                R.drawable.q31,
                R.drawable.q32,
                R.drawable.q34,
                R.drawable.q35,
                R.drawable.q37,
                R.drawable.q38,
                R.drawable.q39,
                R.drawable.q40,
                R.drawable.q41,
                R.drawable.q43,
                R.drawable.q44,
                R.drawable.q45,
                R.drawable.q46,
                R.drawable.q47,
                R.drawable.q48,
                R.drawable.q49,
                R.drawable.q50,
                R.drawable.backgroundhome16,
                R.drawable.backgroundhome17,
                R.drawable.backgroundhome19,
                R.drawable.backgroundhome20,
                R.drawable.backgroundhome31,
                R.drawable.backgroundhome27,
                R.drawable.backgroundhome5,
                R.drawable.backgroundhome7,
                R.drawable.backgroundhome9,
                R.drawable.backgroundhome10,
        };
        int minbg = 0;
        int maxbg = 50; // 1-4
        int random_intbg = (int)(Math.random() * (maxbg - minbg + 1) + minbg);
        bght.setBackgroundResource(arr_bght[random_intbg]);
        helper = new myDbAdapter(this);
            ArrayList<String> TenTacGia = new ArrayList<String>();
            ArrayList<String>TenSach = new ArrayList<String>();
            ArrayList<String>STT = new ArrayList<String>();
            ArrayList<String>NoiDung = new ArrayList<String>();
            try {
                InputStream is = getBaseContext().getAssets().open("2.csv");
                InputStreamReader reader = new InputStreamReader(is, Charset.forName("UTF-8"));

                CSVReader csvReader = new CSVReader(reader);
                String[] line;
                // throw away the header
                csvReader.readNext();
                while ((line = csvReader.readNext()) != null) {
                    TenTacGia.add(line[0]);
                    TenSach.add(line[1]);
                    STT.add(line[2]);
                    NoiDung.add(line[3]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        Bundle bundle = getIntent().getExtras();
        int min = 0;
        int max = NoiDung.size()-1;
        int random_int = (int)(Math.random() * (max - min + 1) + min);
        if (bundle != null) {
            String value = bundle.getString("stt");
            random_int = Integer.parseInt(value)-1;
            heart.setChecked(true);
            heart.setEnabled(false);
        }
        System.out.println(random_int+ " ++++" + "Max: "+max);
        System.out.println(TenTacGia.get(random_int) + " " +TenSach.get(random_int) + " " + STT.get(random_int) +" " + NoiDung.get(random_int));

        TextView tacgia = (TextView)findViewById(R.id.tacgia);
        TextView noidung = (TextView)findViewById(R.id.noidung);
        TextView tensach = (TextView)findViewById(R.id.tensach);

        tacgia.setText("- "+TenTacGia.get(random_int)+" -");
        noidung.setText(NoiDung.get(random_int));
        tensach.setText("("+TenSach.get(random_int)+")");
        ImageButton backhome = (ImageButton)findViewById(R.id.backhome);
        LottieAnimationView a = (LottieAnimationView)findViewById(R.id.animationView);

        int finalRandom_int = random_int;
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()) {
                    a.setVisibility(View.VISIBLE);
                    a.playAnimation();
                    a.addAnimatorListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animation) {

                        }
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            a.setVisibility(View.INVISIBLE);
                        }
                        @Override
                        public void onAnimationCancel(Animator animation) {

                        }
                        @Override
                        public void onAnimationRepeat(Animator animation) {

                        }});
                    String noidungchuyendi = NoiDung.get(finalRandom_int);
                    String sottchuyendi = STT.get(finalRandom_int);
                    long id = helper.insertData(sottchuyendi,noidungchuyendi);

                } else {
                    a.cancelAnimation();
                    a.setVisibility(View.INVISIBLE);
                }
            }
        });
        backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backhome.startAnimation(buttonClick);
                finish();
            }
        });

    }

    private void share() {
        watermarkLogo.setVisibility(View.VISIBLE);
        Bitmap bitmap =getBitmapFromView(backgroundhienthi);
        watermarkLogo.setVisibility(View.INVISIBLE);
        try {
            File file = new File(this.getExternalCacheDir(),"background.png");
            FileOutputStream fout = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG,100,fout);
            fout.flush();
            fout.close();
            file.setReadable(true, false);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri uri = Uri.fromFile(fileImagePath);
            Uri uri = FileProvider.getUriForFile(hienthicau.this, BuildConfig.APPLICATION_ID + ".provider",file);
            intent.putExtra(Intent.EXTRA_STREAM, uri);
            intent.setType("image/png");
            startActivity(Intent.createChooser(intent,"share by"));
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void shareScreenShot() {
        Bitmap bitmap =getBitmapFromView(backgroundhienthi);
        String root = Environment.getExternalStorageDirectory().toString();

//        String root = this.getExternalFilesDir(null).getPath();
        File myDir = new File(root + "/saved_quotes");
//        File myDir = new File(this.getExternalFilesDir(null)+File.separator+"saved_quotes");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file); // out is your output file
            mediaScanIntent.setData(contentUri);
            this.sendBroadcast(mediaScanIntent);


    }
    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap= Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas cv = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if(bgDrawable != null) {
            bgDrawable.draw(cv);
        }else {
            cv.drawColor(Color.WHITE);
        }
        view.draw(cv);
        return returnedBitmap;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    shareScreenShot();

                    barbottom.setVisibility(View.VISIBLE);
                    Message.message(getApplicationContext(), "Đã lưu hình ảnh vào thư viện");

                } else {
                    barbottom.setVisibility(View.VISIBLE);

                    Message.message(getApplicationContext(),"Bạn chưa cấp quyền để lưu ảnh vào bộ nhớ, hãy thực hiện lại!");
                }
                watermarkLogo.setVisibility(View.INVISIBLE);
                return;
            }
        }

    }


}