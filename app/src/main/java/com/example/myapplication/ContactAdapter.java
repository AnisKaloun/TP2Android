package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class ContactAdapter extends ArrayAdapter <Contact> {

    public ContactAdapter(Context context, List<Contact> contact) {
        super(context, 0, contact);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contactinfo,parent, false);
        }

        ContactViewHolder viewHolder = (ContactViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new ContactViewHolder();
            viewHolder.Nom =  convertView.findViewById(R.id.nom);
            viewHolder.Prenom =  convertView.findViewById(R.id.prenom);
            viewHolder.Numero = convertView.findViewById(R.id.num);
            convertView.setTag(viewHolder);
        }

        Contact contact = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.Nom.setText(contact.Nom);
        viewHolder.Prenom.setText(contact.Prenom);
        viewHolder.Numero.setText(contact.Numero);

        return convertView;
    }

    private class ContactViewHolder{
        public TextView Nom;
        public TextView Prenom;
        public TextView Numero;
    }
}
