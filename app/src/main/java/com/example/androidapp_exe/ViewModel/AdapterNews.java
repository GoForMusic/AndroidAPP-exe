package com.example.androidapp_exe.ViewModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.androidapp_exe.Entity.News;
import com.example.androidapp_exe.Entity.NewsResponse;
import com.example.androidapp_exe.Model.NewsRepository;
import com.example.androidapp_exe.R;

import java.util.ArrayList;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.ViewHolder> {

    Context context;
    ArrayList<News> newsArrayList;
    NewsRepository repository;

    public AdapterNews(Context context, ArrayList<News> newsArrayList){
        this.context=context;
        this.newsArrayList=newsArrayList;
        this.repository=NewsRepository.getInstance();
    }

    @NonNull
    @Override
    public AdapterNews.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new AdapterNews.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNews.ViewHolder holder,@SuppressLint("RecyclerView") int position) {

        //News modal = newsArrayList.get(viewType);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, webView.class);
                intent.putExtra("url", newsArrayList.get(position).getUrl());
                context.startActivity(intent);
            }
        });


        holder.mtime.setText("Published at:-" + newsArrayList.get(position).getPublishedAt());
        holder.maouthor.setText(newsArrayList.get(position).getAuthor());
        holder.mheading.setText(newsArrayList.get(position).getTitle());
        holder.mcontent.setText(newsArrayList.get(position).getDescription());
        Glide.with(context).load(newsArrayList.get(position).getUrlToImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView mheading, mcontent,maouthor, mtime;
        CardView cardView;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mheading = itemView.findViewById(R.id.mainheading);
            mcontent = itemView.findViewById(R.id.content);
            maouthor = itemView.findViewById(R.id.author);
            //  mcategory = itemView.findViewById(R.id.category);
            mtime = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageview);
            cardView = itemView.findViewById(R.id.cardview);

        }
    }
}
