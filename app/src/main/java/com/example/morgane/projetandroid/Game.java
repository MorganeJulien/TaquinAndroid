package com.example.morgane.projetandroid;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.Toast;

import java.util.Collections;

/**
 * Created by morgane on 04/03/17.
 */

public class Game extends AppCompatActivity implements AdapterView.OnItemClickListener{
    //Image par défaut
    private int m_ImageFile = 1;
    //Taille par défaut 3 x 3
    private int m_size = 3;
    private ImageAdapter adapter;
    private GridView gridview ;
    private int taille ;
    private Chronometer chronometer ;
    private int hours;
    private int minutes;
    private int seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent res = getIntent();
        m_ImageFile =res.getIntExtra("image",1);
        m_size = res.getIntExtra("size", 3);


        gridview = (GridView) findViewById(R.id.GridView1);
        Bitmap img = BitmapFactory.decodeResource(this.getResources(), R.drawable.arbre);
        switch (m_ImageFile){
            case 1 :
                img = BitmapFactory.decodeResource(this.getResources(), R.drawable.arbre);
                break;
            case 2 :
                img = BitmapFactory.decodeResource(this.getResources(), R.drawable.pont);
                break;
            case 3 :
                img = BitmapFactory.decodeResource(this.getResources(), R.drawable.canyon);
                break;
            case 4 :
                img = BitmapFactory.decodeResource(this.getResources(), R.drawable.bordeaux);
                break;
            case 5:
                img = BitmapFactory.decodeResource(this.getResources(), R.drawable.chien);
                break;
            case 6 :
                img = BitmapFactory.decodeResource(this.getResources(), R.drawable.chat);
                break;
        }


        adapter =  new ImageAdapter(getBaseContext(),img,m_size);
        gridview.setAdapter(adapter);
        gridview.setNumColumns(m_size);
        gridview.setOnItemClickListener(this);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        chronometer.start();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.move(position);
        gridview.setAdapter(adapter);
        if (adapter.finished()){
            chronometer.stop();
            setScore();
            AlertDialog alert = new AlertDialog.Builder(this).setTitle("Victoire ! ").setMessage("Votre score : " + hours + " : " + minutes + " : " + seconds)
            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
            alert.setCanceledOnTouchOutside(false);

        }

    }

    public void setScore(){
        long timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase(); //For example
        hours = (int) (timeElapsed / 3600000);
        minutes = (int) (timeElapsed - hours * 3600000) / 60000;
        seconds = (int) (timeElapsed - hours * 3600000 - minutes * 60000) / 1000;
    }



}
