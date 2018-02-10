package com.duobros.appu.igniteworkshop;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URL;

public class SysTasks extends AppCompatActivity {
    EditText ph,msg,emailText;
    Button phNum,msgSend,emailSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sys_tasks);
        ph=(EditText)findViewById(R.id.num);
        msg=(EditText)findViewById(R.id.msgtext);
        phNum=(Button)findViewById(R.id.call);
        msgSend=(Button)findViewById(R.id.msg);
        emailText=(EditText)findViewById(R.id.emailId);
        emailSend=(Button)findViewById(R.id.emailSend);
        phNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+ph.getText().toString()));
                if (ActivityCompat.checkSelfPermission(SysTasks.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(i);
            }
        });
        msgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Getting intent and PendingIntent instance
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

                //Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms=SmsManager.getDefault();
                sms.sendTextMessage(ph.getText().toString(), null, msg.getText().toString(), pi,null);
            }
        });
        emailSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{emailText.getText().toString()});
                email.putExtra(Intent.EXTRA_SUBJECT, "Testing");
                email.putExtra(Intent.EXTRA_TEXT, msg.getText().toString());

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Sharing client client :"));
            }
        });
    }
}
