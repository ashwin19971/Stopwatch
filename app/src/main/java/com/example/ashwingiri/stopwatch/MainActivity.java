//package com.example.ashwingiri.stopwatch;
//
//import android.os.Handler;
//import android.os.SystemClock;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
////start,stop,pause,reset,lap
//public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//
//    ArrayList<details> stp=new ArrayList<>();
//    RecyclerAdapter adapter;
//    RecyclerView lv;
//    TextView tvResult;
//    int milliseconds=0;
//    boolean running;
//    boolean wasRunning;
//    Button btStart,btStop,btLap,btReset;
//    Handler h=new Handler();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        lv=findViewById(R.id.rvSw);
//        adapter=new RecyclerAdapter(this,stp);
//        lv.setLayoutManager(new LinearLayoutManager(this));
//        lv.setAdapter(adapter);
//        btStart= findViewById(R.id.btStart);
//        btStop= findViewById(R.id.btStop);
//        btLap= findViewById(R.id.btLap);
//        btReset= findViewById(R.id.btReset);
//        tvResult= findViewById(R.id.tvResult);
//        btStart.setOnClickListener(this);
//        btStop.setOnClickListener(this);
//        btLap.setOnClickListener(this);
//        btReset.setOnClickListener(this);
//        runTimer();
//    }
//
//
//
//    private void runTimer() {
//        h.post(new Runnable() {
//            @Override
//            public void run() {
//                int secs= (int) (milliseconds/1000);
//                int mins=secs/60;
//                secs=secs%60;
//                int milliseconds1= (int) (milliseconds%1000);
//                String ans=""+mins+":"+String.format("%02d",secs)+":"+String.format("%03d",milliseconds1);
//                tvResult.setText(ans);
//                if(running){
//                    milliseconds++;
//                }
//                h.postDelayed(this,1);
//            }
//        });
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch(v.getId()) {
//            case R.id.btStart:
//                running=true;
//                break;
//            case R.id.btStop:
//                running=false;
//                break;
//            case R.id.btReset:
//                running=false;
//                milliseconds=0;
//                tvResult.setText("00:00:00");
//                stp.clear();
//                adapter.notifyDataSetChanged();
//                break;
//            case R.id.btLap:
//                String s=tvResult.getText().toString();
//                stp.add(new details(s));
//                adapter.notifyDataSetChanged();
//                break;
//        }
//    }
//
//
//}
package com.example.ashwingiri.stopwatch;

        import android.os.Handler;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import java.util.ArrayList;

//start,stop,pause,reset,lap
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<details> stp=new ArrayList<>();
    RecyclerAdapter adapter;
    RecyclerView lv;
    TextView tvResult;
    Button btStart,btStop,btLap,btReset;
    Handler handle=new Handler();
    long startTime=0L,timeInMilliseconds=0L,timeSwapBuff=0L,Updatetime=0L;
    Runnable updateTimerthread=new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds=SystemClock.uptimeMillis()-startTime;
            Updatetime=timeSwapBuff+timeInMilliseconds;
            int secs= (int) (Updatetime/1000);
            int mins=secs/60;
            secs=secs%60;
            int milliseconds= (int) (Updatetime%1000);
            String ans=String.format("%02d",mins)+":"+String.format("%02d",secs)+":"+String.format("%03d",milliseconds);
            tvResult.setText(ans);
            handle.postDelayed(this,0);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=findViewById(R.id.rvSw);
        adapter=new RecyclerAdapter(this,stp);
        //lv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setAdapter(adapter);
        btStart= findViewById(R.id.btStart);
        btStop= findViewById(R.id.btStop);
        btLap= findViewById(R.id.btLap);
        btReset= findViewById(R.id.btReset);
        tvResult= findViewById(R.id.tvResult);
        btStart.setOnClickListener(this);
        btStop.setOnClickListener(this);
        btLap.setOnClickListener(this);
        btReset.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btStart:
                startTime= SystemClock.uptimeMillis();
                handle.postDelayed(updateTimerthread,0);
                break;
            case R.id.btStop:
                timeSwapBuff=timeSwapBuff+timeInMilliseconds;
                handle.removeCallbacks(updateTimerthread);
                break;
            case R.id.btReset:
                handle.removeCallbacks(updateTimerthread);
                startTime=0L;
                timeInMilliseconds=0L;
                timeSwapBuff=0L;
                Updatetime=0L;
                tvResult.setText("00:00:000");
                stp.clear();
                adapter.notifyDataSetChanged();
                break;
            case R.id.btLap:
                String s=tvResult.getText().toString();
                stp.add(new details(s));
                adapter.notifyDataSetChanged();
                break;
        }
    }


}
