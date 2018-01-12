package com.example.morgane.projetandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.morgane.projetandroid.R.layout.option;

/**
 * Created by morgane on 01/03/17.
 */

public class Option extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected int m_Image = 1;
    protected int m_Size ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(option);

        //// spinner tuto https://www.tutorialspoint.com/android/android_spinner_control.htm ////

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("3 x 3");
        categories.add("4 x 4");
        categories.add("6 x 6");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        ////////////////////////////////////////////////////////////////////////////////////////:

        // récupération des images
        ImageView arbre = (ImageView) findViewById(R.id.imageView1);
        arbre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_Image = 1;
                Toast.makeText(Option.this, "Image 'arbre' selectionnée",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView pont = (ImageView) findViewById(R.id.imageView2);
        pont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_Image = 2;
                Toast.makeText(Option.this, "Image 'pont' selectionnée",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView canyon = (ImageView) findViewById(R.id.imageView3);
        canyon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_Image = 3;
                Toast.makeText(Option.this, "Image 'canyon' selectionnée",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView bordeaux = (ImageView) findViewById(R.id.imageView4);
        bordeaux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_Image = 4;
                Toast.makeText(Option.this, "Image 'bordeaux' selectionnée",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView chien = (ImageView) findViewById(R.id.imageView5);
        chien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_Image = 5;
                Toast.makeText(Option.this, "Image 'chien' selectionnée",Toast.LENGTH_SHORT).show();
            }
        });
        ImageView chat = (ImageView) findViewById(R.id.imageView6);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                m_Image = 6;
                Toast.makeText(Option.this, "Image 'chat' selectionnée",Toast.LENGTH_SHORT).show();
            }
        });

        // Bouton valider : envoie du résultat  http://vogella.developpez.com/tutoriels/android/utilisation-intents/
        Button valider = (Button)findViewById(R.id.button);
        valider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent();
                data.putExtra("bitmap", m_Image);
                data.putExtra("size", m_Size);
                Option.this.setResult(RESULT_OK, data);
                Option.this.finish();

            }
        });

    }

    //Selection de la taille
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int item = (int) parent.getSelectedItemId();
        switch (item){
            case 0 :
                m_Size= 3 ;
                break;
            case 1:
                m_Size=4;
                break;
            case 2:
                m_Size=6;
                break;
        }
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}


