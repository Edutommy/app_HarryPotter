package com.camila.app_harrypotter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    String name, specie, gender, house, birth, ancestry, patronus, alive, actor, img;
    TextView ch_name, ch_alive, ch_specie, ch_gender, ch_ancestry, ch_house, ch_patronus, ch_birth, ch_actor;
    CircleImageView ch_image;
    ImageView img_house;
    MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_character);

        name = getIntent().getStringExtra("NAME");
        specie = getIntent().getStringExtra("SPECIE");
        gender = getIntent().getStringExtra("GENDER");
        house = getIntent().getStringExtra("HOUSE");
        birth = getIntent().getStringExtra("BIRTH");
        ancestry = getIntent().getStringExtra("ANC");
        patronus = getIntent().getStringExtra("PATRONUS");
        alive = getIntent().getStringExtra("ALIVE");
        actor = getIntent().getStringExtra("ACTOR");
        img = getIntent().getStringExtra("IMAGE");

        ch_name = findViewById(R.id.ch_name);
        ch_alive = findViewById(R.id.ch_alive);
        ch_specie = findViewById(R.id.ch_specie);
        ch_gender = findViewById(R.id.ch_gender);
        ch_ancestry = findViewById(R.id.ch_ancestry);
        ch_house = findViewById(R.id.ch_house);
        ch_patronus = findViewById(R.id.ch_patronus);
        ch_birth = findViewById(R.id.ch_birth);
        ch_actor = findViewById(R.id.ch_actor);
        ch_image = findViewById(R.id.ch_image);
        img_house = findViewById(R.id.img_house);

        ch_name.setText(name);
        ch_specie.setText(ucF(specie));
        ch_gender.setText(ucF(gender));
        ch_actor.setText(actor);

        Glide.with(this).load(img).into(ch_image);

        if (birth.isEmpty()){
            ch_birth.setText("Unknown");
        }else{
            ch_birth.setText(birth);
        }

        if (patronus.isEmpty()){
            ch_patronus.setText("Unknown");
        }else{
            ch_patronus.setText(ucF(patronus));
        }

        if (house.isEmpty()){
            ch_house.setText("Unknown");
        }else{
            ch_house.setText(ucF(house));
        }

        if (ancestry.isEmpty()){
            ch_ancestry.setText("Unknown");
        }else{
            ch_ancestry.setText(ucF(ancestry));
        }

        if (alive.equalsIgnoreCase("true")){
            ch_alive.setText("Alive");
        }else{
            ch_alive.setText("Dead");
            ch_alive.setTextColor(Color.parseColor("#EF1000"));
        }

        if (house.equalsIgnoreCase("gryffindor")){
            Glide.with(this).load(R.drawable.gry_house).into(img_house);
        }else if (house.equalsIgnoreCase("slytherin")){
            Glide.with(this).load(R.drawable.sly_house).into(img_house);
        }else if (house.equalsIgnoreCase("hufflepuff")){
            Glide.with(this).load(R.drawable.huf_house).into(img_house);
        }else if (house.equalsIgnoreCase("ravenclaw")){
            Glide.with(this).load(R.drawable.rav_house).into(img_house);
        }else {
            Glide.with(this).load(R.drawable.hp).into(img_house);
        }

        if (mPlayer != null){
            mPlayer.release();
        }

        mPlayer = MediaPlayer.create(this,R.raw.cancion);
        mPlayer.seekTo(2000);
        mPlayer.setLooping(true);
        mPlayer.start();


    }

    public void onPause(){
        super.onPause();

        if (mPlayer != null){
            mPlayer.release();
        }
    }

    public static String ucF(String str){
        if (str.isEmpty()){
            return str;
        }else{
            return str.substring(0,1).toUpperCase() + str.substring(1);
        }
    }

}