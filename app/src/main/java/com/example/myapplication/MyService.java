package com.example.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

    List<Contact> listeFichier=new ArrayList<>();
    List<Contact> listBD=new ArrayList<>();
    Database db;
    Contact contact;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"Service started",Toast.LENGTH_SHORT).show();
        readFromFile(this);
        db=new Database(this);
        Cursor data=db.getContact();
        while (data.moveToNext())
        {
            contact=new Contact(data.getString(1),data.getString(2),data.getString(3));
            listBD.add(contact);

        }

        for(int i=0;i<listeFichier.size();i++)
        {
          if(!listBD.contains(listeFichier.get(i)))
          {
              Log.d("ServiceAjoutBd", "onStartCommand: "+listeFichier.get(i).toString());
              addData(listeFichier.get(i));
          }

        }

        return START_STICKY;
    }

    public void addData(Contact contact)
    {
        boolean insertion=db.AjouterContact(contact);

        if(insertion)
        {
            Toast.makeText(getApplicationContext(),"contact Ajouté",Toast.LENGTH_SHORT).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"erreur lors de l'insertion",Toast.LENGTH_SHORT).show();

        }
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

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    //traitement du fichier
                    stringBuilder.append("\n").append(receiveString);
                    Log.d("contacts",""+receiveString);
                    String[] tab=receiveString.split(",");
                    contact=new Contact(tab[0],tab[1],tab[2]);
                    Log.d("nom",tab[0]);
                    Log.d("prenom",tab[1]);
                    Log.d("numero",tab[2]);
                    receiveString.replaceAll("","");
                    listeFichier.add(contact);

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
