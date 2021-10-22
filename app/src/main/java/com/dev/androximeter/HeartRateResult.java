package com.dev.androximeter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class HeartRateResult extends AppCompatActivity {

    private String user, Date;
    int HR;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    Date today = Calendar.getInstance().getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_result);

        Date = df.format(today);
        TextView RHR = this.findViewById(R.id.HRR);
        Button SHR = this.findViewById(R.id.SendHR);

        ResultsDB DB = new ResultsDB(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            HR = bundle.getInt("bpm");
            RHR.setText(String.valueOf(HR) + " bpm");
            boolean insert = DB.insertData("Ritmo Cardiaco", String.valueOf(HR)+"bpm", String.valueOf(Date));
            if(insert==true) {
                Toast.makeText(this, "Data Saved on Database.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error.", Toast.LENGTH_SHORT).show();
            }
        }

        SHR.setOnClickListener(v -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
            i.putExtra(Intent.EXTRA_SUBJECT, "Resultados AndrOximeter");
            i.putExtra(Intent.EXTRA_TEXT, "Ritmo Cardiaco: " + HR + "\n" + "Fecha: " + Date);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(HeartRateResult.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(HeartRateResult.this, MainActivity.class);
        i.putExtra("Usr", user);
        startActivity(i);
        finish();
    }
}
