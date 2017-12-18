package com.example.dipesh.myloginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main2ActivityEpisodes extends AppCompatActivity {
   static int c;
   EditText edittitle,editurl;
   TextView textanime;
   Button addepisode;
   DatabaseReference episodeRef;
   List<EpisodesClass> episodelist;
   ListView lst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_episodes);
        Animes anime=MainActivity.animes1.get(c);
        episodelist=new ArrayList<>();
        lst=(ListView) findViewById(R.id.lstepisode);
        textanime=(TextView) findViewById(R.id.textanime);edittitle=(EditText) findViewById(R.id.edittitle);
        editurl=(EditText)  findViewById(R.id.editurl); addepisode=(Button) findViewById(R.id.btnaddurl);
        //REFERENCE TO DATABASE
        episodeRef= FirebaseDatabase.getInstance().getReference("Episodes").child(anime.getAnimeId());
        textanime.setText(anime.getAnimeName());
        addepisode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEpisode();
            }
        });

    }
    public void addEpisode(){
        String tilte=edittitle.getText().toString().trim();
        String url=editurl.getText().toString().trim();
        if(!TextUtils.isEmpty(tilte)){
            String id=episodeRef.push().getKey();
            EpisodesClass episode=new EpisodesClass(tilte,url,id);
            episodeRef.child(id).setValue(episode);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this, "Tilte can not be empty", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,ActivityHome.class));
        }
        editurl.setText("");edittitle.setText("");

    }

    @Override
    protected void onStart() {
        super.onStart();
    episodeRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            episodelist.clear();
            for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
           EpisodesClass episode=postSnapshot.getValue(EpisodesClass.class);
           episodelist.add(episode);
            }
            EpisodeList episodeadapter=new EpisodeList(Main2ActivityEpisodes.this,episodelist);
            lst.setAdapter(episodeadapter);


        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }
}
