package com.example.dipesh.myloginpage;

/**
 * Created by dipesh on 16-12-2017.
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

public class EpisodeList extends ArrayAdapter<EpisodesClass> {
    private Activity context;
    List<EpisodesClass> epioselist;

    public EpisodeList(Activity context, List<EpisodesClass> tracks) {
        super(context, R.layout.list, tracks);
        this.context = context;
        this.epioselist = tracks;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
       View viewResult= inflater.inflate(R.layout.episodeadapter,null,true);
        TextView TxtTitle=(TextView) viewResult.findViewById(R.id.txttitle);
        TextView TxtUrl=(TextView) viewResult.findViewById(R.id.txturl);
        EpisodesClass episode=epioselist.get(position);
        TxtTitle.setText(episode.getTitle());
        TxtUrl.setText(episode.getUrl());

        return viewResult;
    }


}
