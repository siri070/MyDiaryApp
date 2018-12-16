package com.example.irisb.mydiaryapp;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
      /*  calendar.setOnDateChangeListener(CalendarView , year, month, dayOfMonth) {
            String data = year + "/" + month + "/"+ dayOfMonth;
            Log.d(TAG, "onSelectedDayChange: yyyy/mm/dd"+ data);
            Intent intent = new Intent(MainActivity.this.onClickAdd().class);
            Intent lastIntent = getIntent();
            intent.putExtra("Titel", lastIntent.getStringExtra("Titel"));
            intent.putExtra("year", year);
            intent.putExtra("month", month);
            intent.putExtra("dayOfMonth", dayOfMonth);
            startActivity(intent);
        }*/
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

                       /*
                       //https://stackoverflow.com/questions/22583122/how-to-set-focus-on-a-specific-date-in-calendarview-knowing-date-is-dd-mm-yyyy
                Calendar calendar = Calendar.getInstance();
                calendar.set(calendar.YEAR, year);
                calendar.set(calendar.MONTH, month);
                calendar.set(calendar.DAY_OF_MONTH, day);
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
