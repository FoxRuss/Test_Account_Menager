package com.example.accountmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    private EditText edAccountName, edAccountBalance, edOpeningDate, edAccountCurrency;
    private DatabaseReference myDataBase;
    private String baseKey = "Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        edAccountName = findViewById(R.id.edAccountName);
        edAccountBalance = findViewById(R.id.edAccountBalance);
        edOpeningDate = findViewById(R.id.edOpeningDate);
        edAccountCurrency = findViewById(R.id.edAccountCurrency);
        myDataBase = FirebaseDatabase.getInstance().getReference(baseKey);
    }

    public void onClickSave(View view) {
        String id = myDataBase.getKey();
        String name = edAccountName.getText().toString();
        String balance = edAccountBalance.getText().toString();
        String date = edOpeningDate.getText().toString();
        String currency = edAccountCurrency.getText().toString();
        Account newAccount = new Account(id, name, balance, date, currency);

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(balance) && !TextUtils.isEmpty(date)) {
            myDataBase.push().setValue(newAccount);
            Toast.makeText(this, "Счёт сохранён", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Остались незаполненные поля", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickRead(View view) {
        Intent intent = new Intent(MainActivity.this, ReadActivity.class);
        startActivity(intent);
    }
}