package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListeContact extends AppCompatActivity {

    ListView mListView;
    List<Contact> liste=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_contact);
        mListView=findViewById(R.id.List);
        Intent intent=getIntent();
        Bundle b=intent.getExtras();
        String [] Chaine=b.getStringArray("info");
        Contact contact=new Contact(Chaine[0],Chaine[1],Chaine[2]);
        liste.add(contact);
        ContactAdapter adapter = new ContactAdapter(ListeContact.this, liste);
        mListView.setAdapter(adapter);
    }
}
