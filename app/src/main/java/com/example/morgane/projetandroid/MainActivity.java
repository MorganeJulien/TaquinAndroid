package com.example.morgane.projetandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static final int OPTIONS = 1;

    //Image par défaut
    private int m_ImageFile = 1;
    //Taille par défaut 3 x 3
    private int m_size = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button quit = (Button) findViewById(R.id.menu_quit);
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainActivity.this.finish();
            }
        });

        final Button option = (Button) findViewById(R.id.menu_options);
        option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, Option.class);
                startActivityForResult(intent,OPTIONS);

            }
        });
        final  Button play = (Button) findViewById(R.id.menu_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent start_Game =new Intent(MainActivity.this, Game.class);
                start_Game.putExtra("size", m_size);
                start_Game.putExtra("image", m_ImageFile);
                startActivity(start_Game);

            }
        });


    }
    @Override
    protected void onActivityResult(int request_code, int result_code, Intent data)
    {
        super.onActivityResult(request_code, result_code, data);

        switch(request_code)
        {
            case OPTIONS:
                if(result_code == RESULT_OK)
                {
                    m_ImageFile = data.getIntExtra("bitmap",0);
                    m_size = data.getIntExtra("size", 0);
                }
                break;
        }
    }

}
