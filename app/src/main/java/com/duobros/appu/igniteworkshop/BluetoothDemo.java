package com.duobros.appu.igniteworkshop;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Set;

public class BluetoothDemo extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth_demo);
        tv=(TextView)findViewById(R.id.textView1);
        tv.setScrollContainer(true);
        BluetoothAdapter adapter=BluetoothAdapter.getDefaultAdapter();
        if(adapter!=null){
            StringBuilder text=new StringBuilder("");
            text.append("Start Scanning Devices.....\n");
            adapter.startDiscovery();
            text.append("Scanning Completed.....\n");
            Set<BluetoothDevice> device=adapter.getBondedDevices();
            //Set<Boolean> devices=adapter.isDiscovering();
            for(BluetoothDevice d:device){
                text.append("\nDevice: "+d);
                text.append("\nDeviceName: "+d.getName());
                text.append("\nDeviceAddress: "+d.getAddress());
            }
            tv.setText(text.toString());
        }
    }
}
