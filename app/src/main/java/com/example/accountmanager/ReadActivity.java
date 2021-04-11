package com.example.accountmanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listId;
    private List<Account> listTemp;
    private DatabaseReference myDataBase;
    private String baseKey = "Account";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_layout);
        init();
        accountDB();
        setOnClickItem();
    }

    private void init() {
        listView = findViewById(R.id.listView);
        listId = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listId);
        listView.setAdapter(adapter);
        myDataBase = FirebaseDatabase.getInstance().getReference(baseKey);
    }

    private void accountDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (listId.size() > 0) listId.clear();
                if (listTemp.size() > 0) listTemp.clear();
                for(DataSnapshot ds : snapshot.getChildren()) {
                    Account account = ds.getValue(Account.class);
                    assert account != null;
                    listId.add(account.name);
                    listTemp.add(account);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        myDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Account account = listTemp.get(position);
                Intent intent = new Intent(ReadActivity.this, ShowActivity.class);
                intent.putExtra(Constant.ACCOUNT_ID, account.id);
                intent.putExtra(Constant.ACCOUNT_NAME, account.name);
                intent.putExtra(Constant.ACCOUNT_BALANCE, account.balance);
                intent.putExtra(Constant.ACCOUNT_DATE, account.date);
                intent.putExtra(Constant.ACCOUNT_CURRENCY, account.currency);
                startActivity(intent);
            }
        });
    }
}
