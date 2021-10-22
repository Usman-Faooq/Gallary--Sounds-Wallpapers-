package com.example.tabtemplets.Fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.tabtemplets.Adapters.FavouriteWallpaperAdapter;
import com.example.tabtemplets.DataVeriables.FirebaseDataVeriables;
import com.example.tabtemplets.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FavouriteWallpaper extends BottomSheetDialogFragment {

    RecyclerView recyclerView;
    FavouriteWallpaperAdapter adapter;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference reference;

    public FavouriteWallpaper() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite_wallpaper, container, false);

        recyclerView = view.findViewById(R.id.fav_wallpaper);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        user = FirebaseAuth.getInstance().getCurrentUser();
        String currentuser = user.getUid();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Users").child(currentuser).child("WallpaperFavourite");

        FirebaseRecyclerOptions<FirebaseDataVeriables> options =
                new FirebaseRecyclerOptions.Builder<FirebaseDataVeriables>()
                        .setQuery(reference, FirebaseDataVeriables.class).build();

        adapter = new FavouriteWallpaperAdapter(options);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}