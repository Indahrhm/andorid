package com.privilage.laundry.view.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.privilage.laundry.R;

public class About extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    ImageView sulthon;
    ImageView gue;
    ImageView ucup;
    ImageView riri;
    ImageView jrun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        setLayout();
    }

    private void setLayout() {
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        sulthon = findViewById(R.id.sulthon);
        gue = findViewById(R.id.gue);
        ucup = findViewById(R.id.ucup);
        riri = findViewById(R.id.riri);
    }
}
