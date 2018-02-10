package com.duobros.appu.igniteworkshop;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class Browser extends AppCompatActivity {
    EditText url;
    Button go;
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);
        url=(EditText)findViewById(R.id.url);
        go=(Button)findViewById(R.id.go);
        wv=(WebView)findViewById(R.id.webview);
        wv.loadUrl("https://www.google.co.in");
        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setSupportZoom(true);
        wv.getSettings().setAllowContentAccess(true);
        wv.getSettings().setAllowFileAccessFromFileURLs(true);
        wv.getSettings().setAllowUniversalAccessFromFileURLs(true);
        wv.getSettings().setAppCacheEnabled(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.setVerticalScrollBarEnabled(false);
        wv.setHorizontalScrollBarEnabled(false);
        wv.getSettings().setLoadsImagesAutomatically(true);
        wv.getSettings().setJavaScriptEnabled(true);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1=url.getText().toString();
                if(url1.indexOf("www.")>=0&&(url1.length()-url1.lastIndexOf("."))>0&&url1.lastIndexOf(".")!=url1.indexOf(".")){
                    wv.loadUrl("https://"+url1);
                }else{
                    wv.loadUrl("https://www.google.co.in/search?q="+url1);
                }
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        ConnectivityManager cm=(ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork=cm.getActiveNetworkInfo();
        boolean isConnected=activeNetwork!=null && activeNetwork.isConnectedOrConnecting();
        if(isConnected!=true){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("Please check your internet Connection");
            builder.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivityForResult(new Intent(Settings.ACTION_SETTINGS),0);
                }
            });
            builder.setNegativeButton("go back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog b=builder.create();
            b.show();
        }
    }
}
