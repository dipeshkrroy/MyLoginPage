package com.example.dipesh.myloginpage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.BaseAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 private static final int RC_SIGN_IN=0;
    public static final String ARTIST_NAME = "com.example.dipesh.myloginpage.artistname";
    public static final String ARTIST_ID = "com.example.dipesh.myloginpage.artistid";
  StorageReference mref;

    //view objects
    EditText animeName;
    EditText animeyear;
    EditText animeepisode;
    Button buttonAddAnime;
ListView lst;
    List<Animes> animes;
    static List<Animes> animes1;

    //our database reference object
    DatabaseReference databaseAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // mref=FirebaseStorage.getInstance().getReference("Anime");


        //getting the reference of artists node
        databaseAnime = FirebaseDatabase.getInstance().getReference("Animes");

        //getting views
        animeName = (EditText) findViewById(R.id.animename);
        animeyear = (EditText) findViewById(R.id.animeyear);
        animeepisode = (EditText) findViewById(R.id.animeno0fepisode);
       buttonAddAnime = (Button) findViewById(R.id.btnadd);
       lst= (ListView) findViewById(R.id.listview);

        //list to store artists
        animes = new ArrayList<>();

        animes1 = new ArrayList<>();
        //adding an onclicklistener to button
        buttonAddAnime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addAnime();
            }
        });


       lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               startActivity(new Intent(getApplicationContext(),Main2ActivityEpisodes.class));
               Main2ActivityEpisodes.c=i;

           }
       });

    }
    private void addAnime() {
        //getting the values to save
        String name = animeName.getText().toString().trim();
        String year = animeyear.getText().toString();
        String noofepisode =  animeepisode.getText().toString();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseAnime.push().getKey();

            //creating an Artist Object
            Animes artist = new Animes(id, name, year,noofepisode);

            //Saving the Artist
            databaseAnime.child(id).setValue(artist);

            //setting edittext to blank again
            animeName.setText("");
            animeyear.setText("");
            animeepisode.setText("");

            //displaying a success toast
            Toast.makeText(this, "Anime added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseAnime.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(MainActivity.this, dataSnapshot.getValue(Animes.class).getAnimeName(), Toast.LENGTH_SHORT).show();

                //clearing the previous artist list
                animes1.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting artist
                    Animes artist = postSnapshot.getValue(Animes.class);
                    //Toast.makeText(MainActivity.this, postSnapshot.getValue(Animes.class).getAnimeName()+"\n"+
                          //  postSnapshot.getValue(Animes.class).getAnimeyear() +"\n"+ postSnapshot.getValue(Animes.class).getNoofepisode()+"\n", Toast.LENGTH_SHORT).show();
                    //adding artist to the list
                    animes1.add(artist);

                }
               // Toast.makeText(MainActivity.this,String .valueOf(animes1.size()), Toast.LENGTH_SHORT).show();
                //creating adapter
                AnimeList artistAdapter = new AnimeList(MainActivity.this,animes1);
                //attaching adapter to the listview
                lst.setAdapter(artistAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public  class custom extends BaseAdapter{

        @Override
        public int getCount() {
            return animes1.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View viewResult=getLayoutInflater().inflate(R.layout.list,null);
            TextView textViewName = (TextView) viewResult.findViewById(R.id.animetext);
            TextView textViewGenre = (TextView) viewResult.findViewById(R.id.animeyeartxt);
           Animes anim=animes1.get(i);
            textViewName.setText(anim.getAnimeName());
            textViewGenre.setText(anim.getAnimeyear());

            return viewResult;
        }
    }
}
