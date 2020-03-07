package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//EXO1 ET EXO2
public class Exo1 extends AppCompatActivity {

    private EditText edt1,edt2,edt3;
    private TextView txt1;
    private Button bt1;
    private int compteur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compteur=0;
        setContentView(R.layout.activity_exo1);
        edt1=findViewById(R.id.editText);
        edt2=findViewById(R.id.editText2);
        edt3=findViewById(R.id.editText3);
        bt1=findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(edt1.getText().toString()!=null && edt2.getText().toString()!=null && edt3.getText().toString()!=null) {

                    Intent intent = new Intent(Exo1.this, ListeContact.class);
                    String [] info = new String[3];
                    info[0]=edt1.getText().toString();
                    info[1]=edt2.getText().toString();
                    info[2]=edt3.getText().toString();
                    intent.putExtra("info",info);
                    startActivity(intent);

                }
            }
        });
    }


    @Override
    public void onResume()
    {
        super.onResume();

        ((TextView) findViewById(R.id.compteur)).setText("nombre d'affichage " + compteur ++);

    }
}
