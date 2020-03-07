package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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

public class ListContactExo3 extends AppCompatActivity {

    ListView mListView;
    List<Contact> liste=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_contact_exo3);
        Log.d("Act2","on lance la seconde activité");
        readFromFile(ListContactExo3.this);
        mListView=findViewById(R.id.List1);
        ContactAdapter adapter = new ContactAdapter(ListContactExo3.this, liste);
        mListView.setAdapter(adapter);
    }

    private String readFromFile(Context context) {

        String ret = "";
        Contact contact;

        try {
            InputStream inputStream = context.openFileInput("contact.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                int i=0;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                  //traitement du fichier
                    stringBuilder.append("\n").append(receiveString);
                    Log.d("contacts","ligne "+i);
                    Log.d("contacts",""+receiveString);
                    String[] tab=receiveString.split(",");
                    contact=new Contact(tab[0],tab[1],tab[2]);
                    Log.d("nom",tab[0]);
                    Log.d("prenom",tab[1]);
                    Log.d("numero",tab[2]);
                    receiveString.replaceAll("","");
                    liste.add(contact);
                    i++;
                }

                inputStream.close();
                stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
