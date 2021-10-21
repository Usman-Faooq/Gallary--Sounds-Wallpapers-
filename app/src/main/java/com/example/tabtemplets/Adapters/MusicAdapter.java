package com.example.tabtemplets.Adapters;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MusicAdapter extends FirebaseRecyclerAdapter<FirebaseDataVeriables, MusicAdapter.holder> {

    public MusicAdapter(@NonNull FirebaseRecyclerOptions<FirebaseDataVeriables> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull FirebaseDataVeriables firebaseDataVeriables) {
        holder.tv.setText(firebaseDataVeriables.getMusicname());
        holder.audiobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mp = new MediaPlayer();
                try {
                    mp.setDataSource(firebaseDataVeriables.getMusicURL());
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

        String musicid = firebaseDataVeriables.getMusicID();
        String musicname = firebaseDataVeriables.getMusicname();
        String musicURL = firebaseDataVeriables.getMusicURL();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users").child(currentuser).child("SoundFavourite");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(musicid).exists()){
                    holder.likebtn.setImageResource(R.drawable.likeicon);
                    holder.likebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reference.child(musicid).removeValue();
                            holder.likebtn.setImageResource(R.drawable.unlikeicon);
                            Toast.makeText(view.getContext(), "Removed from Favourite", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDataVeriables data = new FirebaseDataVeriables(musicname, musicURL, musicid);
                reference.child(musicid).setValue(data);
                holder.likebtn.setImageResource(R.drawable.likeicon);
                Toast.makeText(view.getContext(), "Added to Favourite", Toast.LENGTH_SHORT).show();
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
        ImageView likebtn;
        public holder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.musictext);
            audiobtn = itemView.findViewById(R.id.playaudiobtn);
            likebtn = itemView.findViewById(R.id.music_like_unlike);
        }
    }
}
