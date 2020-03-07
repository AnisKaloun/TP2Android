package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class Exo3 extends AppCompatActivity {

        private EditText edt1,edt2,edt3;
        private TextView txt1;
        private Button bt1;
        private int compteur;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            compteur=0;
            setContentView(R.layout.activity_exo3);
            edt1=findViewById(R.id.editText);
            edt2=findViewById(R.id.editText2);
            edt3=findViewById(R.id.editText3);
            bt1=findViewById(R.id.button);
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edt1.getText().toString()!=null && edt2.getText().toString()!=null && edt3.getText().toString()!=null) {

                        Intent intent = new Intent(Exo3.this, ListContactExo3.class);
                        writeToFile(edt1.getText().toString()+","+edt2.getText().toString()+","+edt3.getText().toString(),Exo3.this);
                       Log.d("Ecriture","je suis la");
                        startActivity(intent);

                    }
                }
            });
        }

    private void writeToFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("contact.txt", Context.MODE_APPEND));
            outputStreamWriter.write(data);
            outputStreamWriter.append('\n');
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

        @Override
        public void onResume()
        {
            super.onResume();

            ((TextView) findViewById(R.id.compteur)).setText("nombre d'affichage " + compteur ++);

        }
    }

