package com.example.tabtemplets.Adapters;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.bumptech.glide.Glide;
import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class WallpaperAdapter extends FirebaseRecyclerAdapter<FirebaseDataVeriables, WallpaperAdapter.viewholder> {

    boolean checkstatus = false;
    public WallpaperAdapter(@NonNull FirebaseRecyclerOptions<FirebaseDataVeriables> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull FirebaseDataVeriables firebaseDataVeriables) {
        Glide.with(holder.img.getContext()).load(firebaseDataVeriables.getURL()).into(holder.img);

        String imageURL = firebaseDataVeriables.getURL();
        String imageID = firebaseDataVeriables.getWallpaperID();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = user.getUid();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("Users").child(currentuser).child("WallpaperFavourite");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(imageID).exists()){
                    holder.likebtn.setImageResource(R.drawable.likeicon);
                    holder.likebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            reference.child(imageID).removeValue();
                            holder.likebtn.setImageResource(R.drawable.unlikeicon);
                            Toast.makeText(view.getContext(), "Removed from Favourite", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();
                        }
                    });

                }else{
                    holder.likebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            FirebaseDataVeriables data = new FirebaseDataVeriables(imageID, imageURL);
                            reference.child(imageID).setValue(data);
                            holder.likebtn.setImageResource(R.drawable.likeicon);
                            Toast.makeText(view.getContext(), "Added to Favourite", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpapercard,parent,false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView img, likebtn;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.posted_image);
            likebtn = itemView.findViewById(R.id.like_unlike);
        }
    }
}
