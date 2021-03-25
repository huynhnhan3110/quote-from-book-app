package com.example.quotesfrombooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuoteOld extends AppCompatActivity {
    myDbAdapter helper;
    List<Quote> data = new ArrayList<Quote>();
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote_old);
        helper = new myDbAdapter(this);


        data = helper.getData();

        lv = (ListView) findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Quote item = data.get(position);
                int sott = item.value1;

                Bundle bundle = new Bundle();
                bundle.putString("stt",Integer.toString(sott));

                Intent it = new Intent(QuoteOld.this, VerticleQuoteOld.class);
                it.putExtras(bundle);
                startActivity(it);
            }

        });



        ArrayAdapter<Quote> cheeseAdapter =
                new ArrayAdapter<Quote>(this, 0, data) {
                    @Override
                    public View getView(int position,
                                        View convertView,
                                        ViewGroup parent) {
                        Quote currentCheese = data.get(position);
                        if(convertView == null) {
                            convertView = getLayoutInflater()
                                    .inflate(R.layout.custom_item, null, false);
                            int min = 0;
                            int max = 3; // 1-4
                            int random_int = (int)(Math.random() * (max - min + 1) + min);
                            switch (random_int) {
                                case 0:
                                    convertView.setBackground(getDrawable(R.drawable.shape_card));
                                    break;
                                case 1:
                                    convertView.setBackground(getDrawable(R.drawable.shape_card2));
                                    break;
                                case 2:
                                    convertView.setBackground(getDrawable(R.drawable.shape_card3));
                                    break;
                                case 3:
                                    convertView.setBackground(getDrawable(R.drawable.shape_card4));
                                    break;
                                default:
                                    break;
                            }

                        }

                        TextView cheeseName =
                                (TextView)convertView.findViewById(R.id.cheese_name);
                        System.out.println("xxxxxxxxxxxxxx: "+currentCheese.value2);
                        String longString = currentCheese.value2;
                        String as = longString.substring(0,longString.lastIndexOf(" ",61)) +"...";

                        cheeseName.setText(as);
                        return convertView;
                    }
                };
        lv.setAdapter(cheeseAdapter);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Quote a = data.get(i);
                helper.delete(Integer.toString(a.value1));

                data.remove(i);
                cheeseAdapter.notifyDataSetChanged();

                Message.message(getApplicationContext(), "Deleted");


                return true;
            }
        });
    }
}