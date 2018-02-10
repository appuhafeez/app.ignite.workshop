package com.duobros.appu.igniteworkshop;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class LightSens extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor lightSensor;
    private String lightSensorReading;
    private String name = "";
    private String vendor;
    private String version;
    private String maxRange;
    private String minRange;
    private String resolution;
    private String power;
    private HardwareAdapter hardwareAdapter;
    private ListView lightSensorListView;
    private List<HardwareObject> lSensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_sens);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        //name = (String.valueOf(lightSensor.getName()).equals(""))? String.valueOf(lightSensor.getName()) : "";
        System.out.println("Sensor Name " + lightSensor.getName());
        name = lightSensor.getName();
        vendor = String.valueOf(lightSensor.getVendor());
        version = String.valueOf(lightSensor.getVersion());
        maxRange = String.valueOf(lightSensor.getMaximumRange());
        minRange = String.valueOf(lightSensor.getMinDelay());
        resolution = String.valueOf(lightSensor.getResolution());
        power = String.valueOf(lightSensor.getPower());
        if(lightSensor != null){
            sensorManager.registerListener(LightSens.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(LightSens.this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(LightSens.this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_LIGHT){
            lightSensorReading = String.valueOf(event.values[0]);
            lSensor = new ArrayList<HardwareObject>();
            lSensor.add(new HardwareObject("Light Sensor Reading", lightSensorReading));
            lSensor.add(new HardwareObject("Name", name));
            lSensor.add(new HardwareObject("Vendor", vendor));
            lSensor.add(new HardwareObject("Version", version));
            lSensor.add(new HardwareObject("Max Range", maxRange));
            lSensor.add(new HardwareObject("Min Delay", minRange));
            lSensor.add(new HardwareObject("Resolution", resolution));
            lSensor.add(new HardwareObject("Power", power));
            hardwareAdapter = new HardwareAdapter(LightSens.this, lSensor);
            lightSensorListView = (ListView)findViewById(R.id.light_sensor);
            lightSensorListView.setAdapter(hardwareAdapter);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
