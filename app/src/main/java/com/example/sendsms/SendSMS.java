package com.example.sendsms;

import android.telephony.SmsManager;
import android.widget.Toast;

public class SendSMS {
    public String sendSMSMessage(String[] Texts) {
        String salenumber = "0356571611";
        String phoneNo = Texts[1].toString();
        String message = Texts[2].toString() + " " + Texts[3].toString() + " " + Texts[0].toString() + " " + Texts[4] + " " + Texts[5].toString() + " " + salenumber + " " + Texts[6].toString();
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, message, null, null);

            }
        catch (Exception e){e.printStackTrace();}

        return "SEND SMS TO"+ Texts[1].toString()+"SUCCESS";

    }
}
