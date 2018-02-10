package com.duobros.appu.igniteworkshop;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class ServConn extends Activity {
    Button btn;
    EditText et1;
    TextView tv1;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serv_conn);
        btn=(Button)findViewById(R.id.btn1);
        et1=(EditText) findViewById(R.id.editText2);
        tv1=(TextView)findViewById(R.id.tv3);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data;

                setProgressBarIndeterminateVisibility(true);
                try {
                    Background1 bg = new Background1(ServConn.this);
                    data = URLEncoder.encode("name", "UTF-8")
                            + "=" + URLEncoder.encode(et1.getText().toString(), "UTF-8");
                    String result = bg.execute(data, "request").get().toString();
                    tv1.setText(result);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    class Background1 extends AsyncTask {
        Activity ac;
        String result="";
        public AsyncResponse delegate = null;
        ProgressDialog progress;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
      /*  progress=new ProgressDialog(ac);
        progress.setTitle("Connecting Server");
        progress.setMessage("Please wait until get response complete");
        progress.setIndeterminate(true);
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();*/
            pb.setVisibility(View.VISIBLE);
            pb.showContextMenu();
            Log.e("AsyncStatus", "spinner shown");
        }

        Background1(ServConn sc){
            ac=sc;
            //progress=pd;
        }
        @Override
        protected Object doInBackground(Object[] params) {
            try {

                try {

                    //Toast.makeText(cn.getApplicationContext(), "m1 pass", Toast.LENGTH_LONG).show();

                    URL url;
                    url=new URL("https://appukck.000webhostapp.com/ignite/serverreq.php");
                    HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                    //Toast.makeText(cn.getApplicationContext(),"m2 pass", Toast.LENGTH_LONG).show();
                    Log.d("data", params[0].toString());
                    bufferedWriter.write(params[0].toString());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    result = "";
                    String line = "";
                    //Toast.makeText(cn.getApplicationContext(), "m3 pass", Toast.LENGTH_LONG).show();
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    Log.d("result", result);
                    //Toast.makeText(cn.getApplicationContext(), result, Toast.LENGTH_LONG).show();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    //Toast.makeText(cn.getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
                //Toast.makeText(cn.getApplicationContext(), e.getStackTrace().toString(), Toast.LENGTH_LONG).show();
            }

            return result;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            //progress.dismiss();
            pb.setVisibility(View.GONE);
        }
    }

}
