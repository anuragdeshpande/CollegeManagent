package com.collegeprojects.ankur.collegemanagent;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Attendance.OnFragmentInteractionListener, Events.OnFragmentInteractionListener, Home.OnFragmentInteractionListener {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle(getString(R.string.title_fragment_home));
                    Home home = Home.newInstance("Index", "1") ;
                    transaction.replace(R.id.main_activity, home);
                    transaction.commit();
                    return true;
                case R.id.id_attendance:
                    setTitle(getString(R.string.title_fragment_attendance));
                    Attendance attendance = Attendance.newInstance("Index", "2");
                    transaction.replace(R.id.main_activity, attendance);
                    transaction.commit();
                    return true;
                case R.id.id_events:
                    setTitle(getString(R.string.title_fragment_events));
                    Events events = Events.newInstance("Index", "3");
                    transaction.replace(R.id.main_activity, events);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {
        // default implementation
    }
}
