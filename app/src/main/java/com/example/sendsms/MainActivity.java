package com.example.sendsms;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    Button sendsms,getFile;
    String [] tokens;
    String FileName;
    String res = null;
    Spinner DelayToSendSms,NumberOfMess;
    int TimeDelay,NumberMess, i=0;
    private static final int PICK_CSV_FILE_REQUEST = 1;
    private static final int READ_REQUEST_CODE = 42;

    SendSMS sendMes  = new SendSMS();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sendsms = findViewById(R.id.button);
        getFile = findViewById(R.id.getFile);

        DelayToSendSms = findViewById(R.id.spinner_delay_time);
        NumberOfMess = findViewById(R.id.NumberOfMess);

        ArrayAdapter<CharSequence> adapter_delay_time = ArrayAdapter.createFromResource(this,R.array.Delay_to_send_SMS, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter_NumberOfMess = ArrayAdapter.createFromResource(this,R.array.Delay_to_send_SMS2, android.R.layout.simple_spinner_item);
        adapter_delay_time.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DelayToSendSms.setAdapter(adapter_delay_time);
        adapter_NumberOfMess.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        NumberOfMess.setAdapter(adapter_NumberOfMess);

        DelayToSendSms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){ Toast.makeText(getApplicationContext(), "Please Insert Time Detlay", Toast.LENGTH_LONG).show(); }
                else if (position==1){TimeDelay=1;}
                else if (position==2){TimeDelay=2;}
                else if (position==3){TimeDelay=5;}
                else if (position==4){TimeDelay=10;}
                else if (position==5){TimeDelay=30;}
                else if (position==6){TimeDelay=60;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        NumberOfMess.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==0){ Toast.makeText(getApplicationContext(), "Please Insert Number Of Message", Toast.LENGTH_LONG).show(); }
                else if (position==1){NumberMess=1;}
                else if (position==2){NumberMess=5;}
                else if (position==3){NumberMess=10;}
                else if (position==4){NumberMess=100;}
                else if (position==5){NumberMess=300;}
                else if (position==6){NumberMess=600;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sendsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<Integer,String[]> data = new HashMap<>();
                ReadCSV readFile = new ReadCSV();

                InputStream is = getResources().openRawResource(R.raw.data);
                File file = new File(Environment.getExternalStorageDirectory(),res);
                data = readFile.readCSV(file);
                for(int i = 0; i <NumberMess; i++){
                    String state = sendMes.sendSMSMessage(data.get(i));
                    sendsms.setText(state);
                    try {
                        TimeUnit.MINUTES.sleep(TimeDelay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        getFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("text/*");
                startActivityForResult(intent,READ_REQUEST_CODE);
            }
        });

    }

    @SuppressLint("Range")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == READ_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if (data != null) {
                Uri uri = data.getData();
                if(uri.getScheme().equals("content")) {
                    Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                    try {
                        if (cursor != null && cursor.moveToFirst()) {
                            FileName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                            res = "Download"+"/"+FileName;
                        }
                    } finally {
                        cursor.close();
                    }
                    if (res == null) {
                        res = uri.getPath();
                        int cutt = res.lastIndexOf('/');
                        if (cutt != -1) {
                            res = res.substring(cutt + 1);
                        }
                    }
                }
                sendsms.setText(res);
                File new1 = new File(FileName).getAbsoluteFile();
                Toast.makeText(getApplicationContext(), ""+ new1, Toast.LENGTH_LONG).show();
            }
        }
    }
}