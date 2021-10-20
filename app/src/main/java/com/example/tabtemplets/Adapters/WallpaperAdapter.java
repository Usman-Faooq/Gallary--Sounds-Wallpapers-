package com.example.tabtemplets.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class WallpaperAdapter extends FirebaseRecyclerAdapter<FirebaseDataVeriables, WallpaperAdapter.viewholder> {

    public WallpaperAdapter(@NonNull FirebaseRecyclerOptions<FirebaseDataVeriables> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull FirebaseDataVeriables firebaseDataVeriables) {
        Glide.with(holder.img.getContext()).load(firebaseDataVeriables.getURL()).into(holder.img);
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallpapercard,parent,false);
        return new viewholder(view);
    }

    public class viewholder extends RecyclerView.ViewHolder{
        ImageView img;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.postedimage);
        }
    }
}
