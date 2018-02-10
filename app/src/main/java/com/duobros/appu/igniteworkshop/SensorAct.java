package com.duobros.appu.igniteworkshop;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SensorAct extends AppCompatActivity implements SensorListener{
    private static final String TAG="Sensor demo";
    private SensorManager sm;

    //private Sensor s;
    //String s_name=Context.SENSOR_SERVICE;
    private TextView outtext;

    private int sensor = SensorManager.SENSOR_ORIENTATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        outtext=(TextView)findViewById(R.id.textView1);

        //sm=(SensorManager)getSystemService(s_name);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"On Resume");
        sm.registerListener(SensorAct.this, sensor);

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"On Pause");
        sm.unregisterListener(SensorAct.this,sensor);

    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if(sensor == this.sensor) {
            float azimuth = Math.round(values[0]);
            float pitch = Math.round(values[1]);
            float roll = Math.round(values[2]);

            String outt = String.format("Armuth: %.2f\nPitch: %.2f\nRoll: %.2f", azimuth, pitch, roll);
            Log.d(TAG, outt);
            outtext.setText(outt);
        }
    
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}
