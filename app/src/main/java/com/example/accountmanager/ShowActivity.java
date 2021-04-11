package com.example.accountmanager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView textId, textName, textBalance, textDate, textCurrency;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_layout);
        init();
        getIntentShow();
    }

    private void init() {
        textId = findViewById(R.id.textId);
        textName = findViewById(R.id.textName);
        textBalance = findViewById(R.id.textBalance);
        textDate = findViewById(R.id.textDate);
        textCurrency = findViewById(R.id.textCurrency);
    }

    private void getIntentShow(){
        Intent intent = getIntent();
        if(intent != null) {
            textId.setText(intent.getStringExtra(Constant.ACCOUNT_ID));
            textName.setText(intent.getStringExtra(Constant.ACCOUNT_NAME));
            textBalance.setText(intent.getStringExtra(Constant.ACCOUNT_BALANCE));
            textDate.setText(intent.getStringExtra(Constant.ACCOUNT_DATE));
            textCurrency.setText(intent.getStringExtra(Constant.ACCOUNT_CURRENCY));
        }
    }
}
