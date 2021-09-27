package com.example.newsapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    Context context;
    List<News> newsList;
    Activity activity;


    //OnClick Item in recycleview
    private OnRecyclerViewClickListener listener;

    public interface OnRecyclerViewClickListener{
        void OnItemClick(int position);
    }

    public void OnRecyclerViewClickListener (OnRecyclerViewClickListener listener){
        this.listener = listener;
    }


    public ListAdapter(List<News> newsList,Context context) {
        this.newsList = newsList;
        activity = (Activity) context;
    }

    public ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view, listener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ListAdapter.MyViewHolder holder, int position) {

        String url = newsList.get(position).getUrlToImage();

        holder.description.setText(newsList.get(position).getDescription());
        holder.author.setText(newsList.get(position).getAuthor());
        Glide.with(activity).load(url).into(holder.img);
        holder.publishedAt.setText(newsList.get(position).getPublishedAt());
        holder.title.setText(newsList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView author, publishedAt, title, description;
        private ImageView img;

        public MyViewHolder(View itemView, OnRecyclerViewClickListener listener) {
            super(itemView);

            author = (TextView)itemView.findViewById(R.id.author);
            publishedAt = (TextView)itemView.findViewById(R.id.publishedAt);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.desc);

            img = (ImageView) itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null && getAbsoluteAdapterPosition() != RecyclerView.NO_POSITION){
                        listener.OnItemClick(getAbsoluteAdapterPosition());
                    }
                }
            });

        }
    }
}
