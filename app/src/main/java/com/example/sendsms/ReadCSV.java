package com.example.sendsms;

import android.graphics.Color;
import android.os.Environment;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class ReadCSV {
    Button sendsms,getFile;
    int i=0;
    String [] tokens;
    public HashMap<Integer,String[]> readCSV(File input) {
        //File file = new File(Environment.getExternalStorageDirectory(), String.valueOf(input));
        HashMap<Integer, String[]> data = new HashMap<>();
        String line = "";
            try {
                BufferedReader reader = new BufferedReader( new FileReader(input));
                for ( i = 0; i < 300; i++) {
                    if ((line = reader.readLine()) != null) {
                        tokens = line.split(",");
                        data.put(i,tokens);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                }
    return data;
    }
}
