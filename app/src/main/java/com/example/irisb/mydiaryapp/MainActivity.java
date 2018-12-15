package com.example.irisb.mydiaryapp;
import android.app.PendingIntent;
import android.app.Service;
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
import android.content.SharedPreferences;
import android.app.AlarmManager;
import android.os.SystemClock;
import android.preference.PreferenceManager;
public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        calendar = (CalendarView) findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), dayOfMonth + "/" +month +"/" + year, Toast.LENGTH_LONG).show();

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

            }
        };
       neuerTermin.setOnClickListener(speichernListener);

    }
    public void onStop() {
        // super.onResume();

        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //int minutes = prefs.getInt("interval", 20);
        //AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        //Intent i = new Intent(this, NotificationService.class);
        //startService(i);
        
       // PendingIntent pi = PendingIntent.getService(this, 0, i, 0);

        //am.cancel(pi); // by my own convention, minutes <= 0 means notifications are disabled
        //if (minutes > 0) {
          //  am.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime() + minutes * 60 * 1000, minutes * 60 * 1000, pi);
        //}
        super.onStop();
    }
}
