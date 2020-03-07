package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


/*

L'EXO 5 Prends le Fichier utilisé pour l'exo 3 et la base de donnée de l'exo 4 comme Entrée

 */
public class ListContactExo5 extends AppCompatActivity {

    Database db;
    ListView mListView;
    Contact contact;
    List<Contact> liste = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact_exo5);
        Log.d("Act2", "on lance la seconde activité");
        mListView = findViewById(R.id.List1);

        db = new Database(this);
        Cursor data = db.getContact();
        while (data.moveToNext()) {
            contact = new Contact(data.getString(1), data.getString(2), data.getString(3));
            liste.add(contact);

        }

        ContactAdapter adapter = new ContactAdapter(ListContactExo5.this, liste);
        mListView.setAdapter(adapter);
    }



}
