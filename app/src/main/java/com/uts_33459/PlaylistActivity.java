package com.uts_33459;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaylistActivity extends AppCompatActivity {
    ActionBar actionBar;
    Dialog diaPopup;
    RecyclerView recyclerView;
    ArrayList<MainModel> mainModels;
    MainAdapter mainAdapter;
    private  long backPressedTime;
    private Toast backToast;
    // Session Manager Class
    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        recyclerView = findViewById(R.id.recyclerView);
        actionBar = getSupportActionBar();
        // Session class instance
        session = new SessionManagement(getApplicationContext());

        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        session.checkLogin();

        // get user data from session
        HashMap<String, String> user = session.getUserDetails();

        // name
        String name = user.get(SessionManagement.KEY_NAME);

        openDialog();

        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF000000")));
        //integer array for album cover
        Integer[] albumCover = {
                R.drawable.beautiful_day,
                R.drawable.mirrors,
                R.drawable.sweater_weather,
                R.drawable.friday_im_in_love,
                R.drawable.elevate,
                R.drawable.run_the_world,
                R.drawable.kings_and_queens,
                R.drawable.i_lived,
                R.drawable.mr_brightside,
                R.drawable.little_of,
                R.drawable.i_was,
                R.drawable.always,
                R.drawable.crash_into_me,
                R.drawable.with_or_without_you,
                R.drawable.whats_love,
                R.drawable.always_be_my_baby,
                R.drawable.every_breath,
                R.drawable.forgotten,
                R.drawable.we_just_cant,
                R.drawable.someday,
                R.drawable.sekali_lagi
        };

        //String array for song title
        String[] songTitle = {
                "Beautiful Day",
                "Mirrors",
                "Sweater Weather",
                "Friday I'm In Love",
                "Elevate (feat. Denzel Curry, YBN Cordae, SwaVay & Trevor Rich)",
                "Run The World (Girls)",
                "Kings and Queens",
                "I Lived",
                "Mr. Brightside",
                "Little of Your Love",
                "I Was Made For Lovin' You",
                "Always",
                "Crash Into Me",
                "With Or Without You",
                "What's Love Got To Do With It",
                "Always Be My Baby",
                "Every Breath You Take",
                "Forgotten",
                "Mother, We just can't get enough",
                "Someday We'll Know",
                "Sekali Lagi"
        };

        //String array for song singer
        String[] songSinger = {
                "U2",
                "Justin Timberlake",
                "The Neighbourhood",
                "The Cure",
                "DJ Khalil",
                "Beyoncé",
                "Thirty Seconds to Mars",
                "OneRepublic",
                "The Killers",
                "HAIM",
                "KISS",
                "Blink-182",
                "Dave Matthews Band",
                "U2",
                "DNCE",
                "Mariah Carey",
                "The Police",
                "Linkin Park",
                "New Radicals",
                "Mandy Moore feat. Jonathan Foreman",
                "Isyana Sarasvati"
        };

        //initialize ArrayList
        mainModels = new ArrayList<>();
        for (int i=0;i<albumCover.length;i++) {
            MainModel model = new MainModel(albumCover[i], songTitle[i], songSinger[i]);
            mainModels.add(model);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //initialize MainAdapter
        mainAdapter = new MainAdapter(PlaylistActivity.this, mainModels);
        //set MainAdapter to RecyclerView
        recyclerView.setAdapter(mainAdapter);
    }

    private void openDialog() {
        diaPopup = new Dialog(PlaylistActivity.this);
        diaPopup.setContentView(R.layout.popup);

        diaPopup.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        diaPopup.setCancelable(false);

        Button btn_ok = diaPopup.findViewById(R.id.button_ok_popup);

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void  onClick(View v) {
                diaPopup.dismiss();
            }
        });
        diaPopup.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {

        if(backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            //super.onBackPressed();
            finishAffinity();
            System.exit(0);
            return;
        }else{
            backToast = Toast.makeText(getBaseContext(),"Press again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.option_profil) {
            Intent i = new Intent(PlaylistActivity.this, ProfilActivity.class);
            startActivity(i);
//            finish();
        }
        if (id==R.id.option_logout) {
            startActivity(new Intent(PlaylistActivity.this, MainActivity.class));
            session.logoutUser();
        }
        return super.onOptionsItemSelected(item);
    }
}