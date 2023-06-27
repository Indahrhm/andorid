package com.privilage.laundry.view.main;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.privilage.laundry.R;

public class Payment extends AppCompatActivity {

    TextView pembayaran;
    Spinner spPembayaran;
    TextView location;
    CheckBox delivery;
    CheckBox self;
    Button btOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment);

        pembayaran = findViewById(R.id.pembayaran);
        spPembayaran = findViewById(R.id.spPembayaran);
        location = findViewById(R.id.location);
        delivery = findViewById(R.id.delivery);
        self = findViewById(R.id.self);
        btOrder = findViewById(R.id.btnOrder);

    }

}
