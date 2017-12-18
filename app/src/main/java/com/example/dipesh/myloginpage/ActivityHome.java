package com.example.dipesh.myloginpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ActivityHome extends AppCompatActivity {
    List<Animes> animeList;
    RecyclerView recyclerView;
    AnimeAdapter ada;
    static int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        animeList=new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.animerecycleview);
        recyclerView.setHasFixedSize(true);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(mLayoutManager);
        ada=new AnimeAdapter(MainActivity.animes1,this);
        recyclerView.setAdapter(ada);


        //animeadd();
    }
}
