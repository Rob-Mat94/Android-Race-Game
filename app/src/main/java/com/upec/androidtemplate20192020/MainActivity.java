package com.upec.androidtemplate20192020;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity  {

    private SensorManager sm;
    private GameContentView gameview;
    private static boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Sensor s = null;
        sm = (SensorManager)getSystemService(SENSOR_SERVICE);
        if(sm != null)
            s = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

       InitGamePanel();

    }

    private void InitGamePanel(){
        gameview = (GameContentView)findViewById(R.id.content);
        gameview.setMainActivity(this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        gameview.setStart(metrics.widthPixels,metrics.heightPixels);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //test android studio et git
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(sm != null)
            sm.registerListener(gameview,sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),sm.SENSOR_DELAY_GAME);
        stop = false;
       InitGamePanel();
    }

    @Override
    public void onPause(){
        super.onPause();
        sm.unregisterListener(gameview);
    }

    public static boolean getStop(){
        return stop;
    }
    public static void  setStop(){
         stop = true;
    }

}

