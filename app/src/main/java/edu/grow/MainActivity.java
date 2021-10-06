package edu.grow;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    Option op = new Option();
    ImageView iv;
    TextView money_tv;
    Button shop_btn, reset_btn;
    TimerTask TT;
    ProgressBar progressBar;
    Timer timer;
    DecimalFormat formatter = new DecimalFormat("###,###");
    int count, second;
    boolean fever;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = (ImageView) findViewById(R.id.maintouchview);
        money_tv = (TextView) findViewById(R.id.money);
        shop_btn = (Button)findViewById(R.id.shop_btn);
        reset_btn = (Button)findViewById(R.id.reset_btn);
        progressBar = findViewById(R.id.progressBar4);
        progressBar.setProgress(0); //프로그레스바 초기값 0
        timer = new Timer();

        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                op.setmoney(0);
                money_tv.setText("소지금 : "+formatter.format(op.getmoney())+ "원");
            }
        });

        SharedPreferences sf = getSharedPreferences("Data",MODE_PRIVATE);
        op.setmoney(sf.getLong("Money", 0));
        money_tv.setText("소지금 : "+formatter.format(op.getmoney())+ "원");

        op.setTouchmoney(300);
        iv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        op.setmoney(op.getmoney()+op.getTouchmoney());
                        money_tv.setText("소지금 : "+formatter.format(op.getmoney())+ "원");

                        if (fever) {
                            return true;
                        }

                        count++;
                        progressBar.setProgress(count);

                        if (count == 300) {
                            fever = true;
                            second = count;
                            op.setTouchmoney(op.getTouchmoney()*2);
                            TT = new TimerTask() {
                                @Override
                                public void run() {
                                    // 반복실행할 구문
                                    second-=5;
                                    count-=5;
                                    progressBar.setProgress(second);

                                    if (count == 0) {
                                        this.cancel();
                                        op.setTouchmoney(op.getTouchmoney()/2);
                                        fever = false;
                                    }
                                }
                            };
                            timer.schedule(TT, 0, 100); //Timer 실행
                        }
                }

//                op.setmoney(op.getmoney()+op.getTouchmoney());
//                money_tv.setText("소지금 : "+formatter.format(op.getmoney())+ "원");


                return true;
            }
        });

        tempTask();

        shop_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                View popupview = inflater.inflate(R.layout.shop_popup, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;

                PopupWindow shop_popup = new PopupWindow(popupview, width, height, true);
                shop_popup.showAtLocation(popupview, Gravity.CENTER, 0, 0);

                Button exit_btn = popupview.findViewById(R.id.exit_btn);
                exit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shop_popup.dismiss();
                    }
                });

                ListView list = popupview.findViewById(R.id.list);
                ArrayList<Shop> itemlist = new ArrayList<>();

                itemlist.add(new Shop("아이템1", 3500,R.drawable.img));
                itemlist.add(new Shop("아이템2", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템3", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템4", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템5", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템6", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템7", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템8", 4000,R.drawable.img));
                itemlist.add(new Shop("아이템9", 4000,R.drawable.img));

                ShopAdapter itemAdapter = new ShopAdapter(MainActivity.this, R.layout.shop_item, itemlist);
                list.setAdapter(itemAdapter);

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences sf = getSharedPreferences("Data",MODE_PRIVATE);
        SharedPreferences.Editor editor = sf.edit();
        editor.putLong("Money", op.getmoney());
        editor.commit();

    }

    public void tempTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                op.setmoney(op.getmoney()+op.getAutomoney());
            }
        };
        Timer tmr = new Timer();
        tmr.schedule(task, 0, 1000);
    }

}

