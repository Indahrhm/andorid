package com.privilage.laundry.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.privilage.laundry.R;

import java.util.HashMap;

public class Akun extends AppCompatActivity {

    EditText ad_name;
    EditText ad_pass;
    EditText ad_email;
    EditText ad_add;
    CheckBox ad_man;
    CheckBox ad_women;
    Button btnAbout;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.akun);

        ad_name = findViewById(R.id.tvName);
        ad_pass = findViewById(R.id.tvPassword);
        ad_email = findViewById(R.id.tvEmail);
        ad_add = findViewById(R.id.tvAddres);
        ad_man = findViewById(R.id.checkBox);
        ad_women = findViewById(R.id.checkBox2);
        btnAbout = findViewById(R.id.btnAbout);
        progressDialog = new ProgressDialog(this);

        btnAbout.setOnClickListener(v ->
                saveAccount()
        );
    }

    private void saveAccount() {
        String name = ad_name.getText().toString();
        String password = ad_pass.getText().toString();
        String email = ad_email.getText().toString();
        String addres = ad_add.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(addres)){
            Toast.makeText(this, "Please write your addres...", Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.setTitle("Setting Account");
            progressDialog.setTitle("Please wait, while we are checking the credentials.");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            validateEmail(name, email, password, addres);
        }
    }

    private void validateEmail(String name, String email, String password, String addres) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("User").child(email).exists())) {
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", email);
                    userdataMap.put("password", password);
                    userdataMap.put("addres", addres);
                    userdataMap.put("name", name);


                    RootRef.child("User").child(email).updateChildren(userdataMap)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Akun.this, "Congratulastions, your account has been saved.", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(Akun.this, Login.class);
                                    startActivity(intent);

                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Akun.this, "Network Eror: Please try again later.", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(Akun.this, "This" + email + "already exists.", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(Akun.this, "Please try again using another email.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Akun.this, Setting.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
