package io.github.vipul_08.retrofituploadexample;

import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.HashMap;

import io.github.vipul_08.retrofitupload.RetrofitUpload;
import io.github.vipul_08.retrofitupload.RetrofitUtils;

public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);

        RetrofitUtils.URL = "http://.ngrok.io";
        RetrofitUtils.ROUTE = "/uploads/image";
        RetrofitUtils.JSON_PARAM="json";
        RetrofitUtils.IMAGE_PARAM="image";

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(Environment.getExternalStorageDirectory()+"/DocumentScanner/Aadhar Card/imageDir");
                File files[] = file.listFiles();
                HashMap<String,Object> map = new HashMap<>();
                map.put("name","Vipul Singh Raghuvanshi");
                map.put("father's name","Brij Bihari Singh");
                map.put("dob","08/11/1997");
                map.put("pan","CSUPR6644H");

                final File ff = files[0];
                final HashMap mm = map;

                AsyncTask.execute(new Runnable() {
                    @Override
                    public void run() {
                        String str = RetrofitUpload.upload(ff,mm);
                        Toast.makeText(MainActivity.this , "str: "+str , Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
