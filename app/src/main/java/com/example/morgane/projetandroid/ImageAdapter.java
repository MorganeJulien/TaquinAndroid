package com.example.morgane.projetandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by morgane on 04/03/17.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap tab[];
    private int m_size;
    private int rannul;
    private ArrayList<Integer> listeInts = new ArrayList<Integer>();
    private  ArrayList<Integer> finish = new ArrayList<Integer>();
    private int[] position = {0 , 0 , 0 , 0 } ;
    public ImageAdapter(Context c,Bitmap img, int size) {
        mContext = c;
        m_size=size;
        this.tab= new Bitmap[m_size*m_size];
        for(int i=0;i< ( m_size * m_size ) ;i++)
        {
            this.listeInts.add(i);
            this.finish.add(i);
        }
        decouper(img);

        rannul=m_size*m_size-1; // correspond à la position de la case blanche dans listeInts
        Log.e("rrr" , listeInts.toString() );
        melanger(tab);
        Log.e("rrr" , listeInts.toString() );
    }

    public void decouper (Bitmap image){
        int taille = image.getWidth()/m_size;
        System.out.println(taille);
        int b=0;
        for (int y=0;y < m_size ;y++){
            for( int x =0;x<m_size; x++){
                tab[b]=Bitmap.createBitmap(image, x*taille, y*taille,taille,taille);
                b++;

            }

        }
        tab[tab.length-1]=Bitmap.createBitmap(taille,taille, Bitmap.Config.ALPHA_8);


    }
    public void melanger(Bitmap[] tab){
        /*for (int i=0; i<100;i++){
            int n = (int)(Math.random() * tab.length);
            if(n>0 && n<tab.length-1 ){
                Bitmap img = tab[n];
                tab[n]=tab[n+1];
                tab[n+1]=img;
                int x = listeInts.get(n);
                listeInts.set(n,listeInts.get(n+1));
                listeInts.set(n+1,x);
            }
            else if(n==0){
                Bitmap img = tab[n];
                tab[n]=tab[n+1];
                tab[n+1]=img;
                int x = listeInts.get(n);
                listeInts.set(n,listeInts.get(n+1));
                listeInts.set(n+1,x);

            }
            else if(n==tab.length-1){
                Bitmap img = tab[n];
                tab[n]=tab[n-1];
                tab[n-1]=img;
                int x = listeInts.get(n);
                listeInts.set(n,listeInts.get(n-1));
                listeInts.set(n-1,x);
            }
        }*/

        for (int i=0; i<500*m_size;i++){
            int n = (int)(Math.random() * tab.length);
            move(n);
        }
    }
    public int getCount() {
        return tab.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            switch (m_size){
                case 3:
                    imageView.setLayoutParams(new GridView.LayoutParams(350, 350));
                    break;
                case 4 :
                    imageView.setLayoutParams(new GridView.LayoutParams(260, 260));
                    break;
                case 6:
                    imageView.setLayoutParams(new GridView.LayoutParams(170, 170));
                    break;
            }

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1,1,1,1);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(tab[position]);
        return imageView;
    }

    public void move( int position){
        //tester position posible !
        if( position % m_size == 0){
            if ((position-m_size)>=0 && (position+m_size)<=(listeInts.size()-1)){
                //deplacement possible droite, bas, haut
                if(listeInts.get(position+1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+1];
                    tab[position+1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+1));
                    listeInts.set(position+1,x);
                }
                else if (listeInts.get(position+m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+m_size];
                    tab[position+m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+m_size));
                    listeInts.set(position+m_size,x);
                }
                else if (listeInts.get(position-m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-m_size];
                    tab[position-m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-m_size));
                    listeInts.set(position-m_size,x);
                }

            }
            else if ((position-m_size)<=0){
                if(listeInts.get(position+1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+1];
                    tab[position+1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+1));
                    listeInts.set(position+1,x);
                }
                else if (listeInts.get(position+m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+m_size];
                    tab[position+m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+m_size));
                    listeInts.set(position+m_size,x);
                }
            }
            else if (position+m_size>=listeInts.size()-1){

                if(listeInts.get(position+1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+1];
                    tab[position+1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+1));
                    listeInts.set(position+1,x);
                }
                else if (listeInts.get(position-m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-m_size];
                    tab[position-m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-m_size));
                    listeInts.set(position-m_size,x);
                }

            }
        }
        else if (position % m_size == (m_size-1)){
            if ((position-m_size)>=0 && (position+m_size)<=(listeInts.size()-1)){
                //deplacement possible droite, bas, haut
                if(listeInts.get(position-1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-1];
                    tab[position-1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-1));
                    listeInts.set(position-1,x);
                }
                else if (listeInts.get(position+m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+m_size];
                    tab[position+m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+m_size));
                    listeInts.set(position+m_size,x);
                }
                else if (listeInts.get(position-m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-m_size];
                    tab[position-m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-m_size));
                    listeInts.set(position-m_size,x);
                }

            }
            if (position-m_size<=0){
                if(listeInts.get(position-1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-1];
                    tab[position-1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-1));
                    listeInts.set(position-1,x);
                }
                else if (listeInts.get(position+m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+m_size];
                    tab[position+m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+m_size));
                    listeInts.set(position+m_size,x);
                }

            }
            else if (position+m_size>=listeInts.size()-1){
                if(listeInts.get(position-1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-1];
                    tab[position-1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-1));
                    listeInts.set(position-1,x);
                }
                else if (listeInts.get(position-m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-m_size];
                    tab[position-m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-m_size));
                    listeInts.set(position-m_size,x);
                }
            }
        }
        else {
            if ((position-m_size)>=0 && (position+m_size)<=(listeInts.size()-1)){
                //deplacement possible droite, bas, haut ,gauche
                if(listeInts.get(position-1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-1];
                    tab[position-1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-1));
                    listeInts.set(position-1,x);
                }
                else if (listeInts.get(position+m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+m_size];
                    tab[position+m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+m_size));
                    listeInts.set(position+m_size,x);
                }
                else if (listeInts.get(position-m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-m_size];
                    tab[position-m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-m_size));
                    listeInts.set(position-m_size,x);
                }
                else if(listeInts.get(position+1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+1];
                    tab[position+1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+1));
                    listeInts.set(position+1,x);
                }

            }
            else if ((position-m_size)<0){
                if(listeInts.get(position-1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-1];
                    tab[position-1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-1));
                    listeInts.set(position-1,x);
                }
                else if (listeInts.get(position+m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+m_size];
                    tab[position+m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+m_size));
                    listeInts.set(position+m_size,x);
                }

                else if(listeInts.get(position+1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+1];
                    tab[position+1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+1));
                    listeInts.set(position+1,x);
                }
            }
            else if (position+m_size>listeInts.size()-1){
                if(listeInts.get(position-1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-1];
                    tab[position-1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-1));
                    listeInts.set(position-1,x);
                }
                else if (listeInts.get(position-m_size)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position-m_size];
                    tab[position-m_size]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position-m_size));
                    listeInts.set(position-m_size,x);
                }
                else if(listeInts.get(position+1)==rannul){
                    Bitmap img = tab[position];
                    tab[position]=tab[position+1];
                    tab[position+1]=img;
                    int x = listeInts.get(position);
                    listeInts.set(position,listeInts.get(position+1));
                    listeInts.set(position+1,x);
                }
            }


        }


    }

    public boolean finished() {
        for (int i =0 ; i<finish.size()-1; i++){
            if (finish.get(i)!=listeInts.get(i)){
                return false;
            }
        }
        return true ;
    }
}


