package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListContactExo4 extends AppCompatActivity {

    Database db;
    ListView mListView;
    Contact contact;
    List<Contact> liste=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact_exo4);
        Log.d("Act2","on lance la seconde activité");
        mListView=findViewById(R.id.List1);

        db=new Database(this);
        Cursor data=db.getContact();
        while (data.moveToNext())
        {
            contact=new Contact(data.getString(1),data.getString(2),data.getString(3));
            liste.add(contact);

        }

        ContactAdapter adapter = new ContactAdapter(ListContactExo4.this, liste);
        mListView.setAdapter(adapter);
    }


}
