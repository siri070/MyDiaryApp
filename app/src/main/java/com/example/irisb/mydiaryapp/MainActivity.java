package com.example.irisb.mydiaryapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //https://applandeo.com/blog/material-calendar-view-customized-calendar-widget-android/

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_todo:
                    mTextMessage.setText(R.string.title_todo);
                    return true;
               /* case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;*/
                case R.id.navigation_kalender:
                    mTextMessage.setText(R.string.title_kalender);
                    return true;
            }
            return false;
        }
    };

    CalendarView calendar;
    private List<TerminData> terminData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        calendar = (CalendarView) findViewById(R.id.calendar);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
               Toast.makeText(getApplicationContext(), dayOfMonth + "/" +month +"/" + year, Toast.LENGTH_LONG).show();
                       /* Intent intent = new Intent(MainActivity.this, TagesansichtActivity.class);
                       //https://github.com/Applandeo/Material-Calendar-View
                        startActivity(intent);
                        Intent intent = new Intent(getApplicationContext(), TagesansichtActivity.class);
                        startActivity(intent);
                        */
            }
        });
        onClickAdd();
    }

    private void onClickAdd(){
        Button neuerTermin = (Button) findViewById(R.id.terminHinzu);
        View.OnClickListener speichernListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), terminHinzufuegen.class);
                startActivity(intent);
                /*Intent intent = new Intent(getApplicationContext(), TagesansichtActivity.class);
                startActivity(intent);*/
            }
        };
       neuerTermin.setOnClickListener(speichernListener);
    }
}
