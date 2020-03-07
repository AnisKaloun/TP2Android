package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.cert.TrustAnchor;

public class Exo4 extends AppCompatActivity {

    private Database db;
    private EditText edt1,edt2,edt3;
    private TextView txt1;
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo4);
        edt1=findViewById(R.id.editText);
        edt2=findViewById(R.id.editText2);
        edt3=findViewById(R.id.editText3);
        bt1=findViewById(R.id.button);
        db= new Database(this);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt1.getText().toString()!=null && edt2.getText().toString()!=null && edt3.getText().toString()!=null) {
                    Contact cnt=new Contact(edt1.getText().toString(),edt2.getText().toString(),edt3.getText().toString());
                    addData(cnt);
                    edt1.setText("");
                    edt2.setText("");
                    edt3.setText("");

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"veuillez remplir les champs",Toast.LENGTH_SHORT).show();
                }
            }
        });


        bt2=findViewById(R.id.button4);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exo4.this, ListContactExo4.class);
                startActivity(intent);
            }
        });


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
}
