package com.example.dipesh.myloginpage;

/**
 * Created by dipesh on 15-12-2017.
 */
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AnimeList extends ArrayAdapter<Animes>  {
    private Activity context;
    List<Animes> animes;

    public AnimeList(Activity context, List<Animes> animes)
    {
        super(context,R.layout.list,animes);
        this.context=context;
        this.animes=animes;

    }


    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.animetext);
        TextView textViewGenre = (TextView) listViewItem.findViewById(R.id.animeyeartxt);

        Animes anime = animes.get(position);
        textViewName.setText(anime.getAnimeName());
        textViewGenre.setText(anime.getAnimeyear());

        return listViewItem;
    }
}
