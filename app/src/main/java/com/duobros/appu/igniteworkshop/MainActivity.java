package com.duobros.appu.igniteworkshop;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
    String[] myArray={"Sensor Demo","Notification Demo","Light Sensor","System Tasks","Browser","Camera","Server","Service","Bluetooth","GPS Location"};
    String[] nameAct={"SensorAct","NotiAct","LightSens","SysTasks","Browser","Camera","ServConn","ServiceMan","BluetoothDemo","SystemSett"};
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv=(ListView)findViewById(R.id.listview);
       // setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, myArray));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_2, android.R.id.text1, myArray);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mystr=nameAct[position];
                try{
                    Class myActivity=Class.forName("com.duobros.appu.igniteworkshop."+mystr);
                    Intent intent=new Intent(MainActivity.this,myActivity);
                    startActivity(intent);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

/*    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String mystr=nameAct[position];
        try{
            Class myActivity=Class.forName("com.duobros.appu.igniteworkshop."+mystr);
            Intent intent=new Intent(MainActivity.this,myActivity);
            startActivity(intent);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }*/
}
