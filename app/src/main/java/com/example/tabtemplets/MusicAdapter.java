package com.example.tabtemplets;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MusicAdapter extends FirebaseRecyclerAdapter<Model, MusicAdapter.holder> {

    public MusicAdapter(@NonNull FirebaseRecyclerOptions<Model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull Model model) {
        holder.tv.setText(model.getMusicname());
        holder.audiobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(model.getMusicURL());
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mediaPlayer) {
                            mp.start();
                        }
                    });
                    mp.prepare();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.musiccard,parent,false);
        return new holder(view);
    }

    public class holder extends RecyclerView.ViewHolder{
        FloatingActionButton audiobtn;
        TextView tv;
        public holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.musictext);
            audiobtn = itemView.findViewById(R.id.playaudiobtn);
        }
    }
}
