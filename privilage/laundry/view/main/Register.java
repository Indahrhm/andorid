package com.privilage.laundry.view.main;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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

public class Register extends AppCompatActivity {

    Button btnRegister;
    EditText ed_name, ed_email, ed_password, ed_addres;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        setTitle("Register");
        btnRegister = findViewById(R.id.btnSave);
        ed_name = findViewById(R.id.tvName);
        ed_email = findViewById(R.id.tvEmail);
        ed_password = findViewById(R.id.tvPassword);
        ed_addres = findViewById(R.id.tvAddres);
        progressDialog = new ProgressDialog(this);


        btnRegister.setOnClickListener(v ->
                createAccount()
        );

    }

    private void createAccount(){
        String name = ed_name.getText().toString();
        String email = ed_email.getText().toString();
        String password = ed_password.getText().toString();
        String addres = ed_addres.getText().toString();

        if (TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please write your name...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please write your email...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your password...", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please write your addres...", Toast.LENGTH_SHORT).show();
        }else {
            progressDialog.setTitle("Create Account");
            progressDialog.setTitle("Please wait, while we are checking the credentials.");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            validateEmail(name, email, password, addres);
        }
    }

    private void validateEmail(String name, String email, String password, String addres){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent (new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot){
                if (!(dataSnapshot.child("User").child(email).exists())){
                    HashMap<String, Object> userdataMap = new HashMap<>();
                    userdataMap.put("email", email);
                    userdataMap.put("password", password);
                    userdataMap.put("addres", addres);
                    userdataMap.put("name", name);


                    RootRef.child("User").child(email).updateChildren(userdataMap)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()){
                                    Toast.makeText( Register.this, "Congratulastions, your account has been created.", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();

                                    Intent intent = new Intent (Register.this, Login.class);
                                    startActivity(intent);


                                }else {
                                    progressDialog.dismiss();
                                    Toast.makeText( Register.this, "Network Eror: Please try again later.", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else {
                    Toast.makeText( Register.this, "This" + email + "already exists.",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText( Register.this, "Please try again using another phone number.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent (Register.this, Login.class);
                    startActivity(intent);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError){

            }
        });
    }
}

