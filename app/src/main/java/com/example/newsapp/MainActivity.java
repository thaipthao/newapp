package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("news");
        ArrayList<News> newsArrayList = new ArrayList<>();
        
        Context context = null;

        recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ListAdapter adapter = new ListAdapter(newsArrayList, this);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren())
                {
                    News news = child.getValue(News.class);
                    newsArrayList.add(news);
                }

                recyclerView.setAdapter(adapter);

                adapter.OnRecyclerViewClickListener(new ListAdapter.OnRecyclerViewClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);

                        intent.putExtra("authorIntent", newsArrayList.get(position).getAuthor());
                        intent.putExtra("publishedAtIntent", newsArrayList.get(position).getPublishedAt());
                        intent.putExtra("titleIntent", newsArrayList.get(position).getTitle());
                        intent.putExtra("urlIntent", newsArrayList.get(position).getUrl());
                        intent.putExtra("imageIntent", newsArrayList.get(position).getUrlToImage());


                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });



        
    }
}