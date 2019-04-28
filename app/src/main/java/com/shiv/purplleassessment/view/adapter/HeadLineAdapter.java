package com.shiv.purplleassessment.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.shiv.purplleassessment.model.Article;
import com.shiv.purplleassessment.R;
import com.shiv.purplleassessment.utils.Utils;

import java.util.ArrayList;

public class HeadLineAdapter extends RecyclerView.Adapter<HeadLineAdapter.MyViewHolder> {
    private ArrayList<Article> articleArrayList;
    private final Context context;
    private final IClick iClick;

    public HeadLineAdapter(ArrayList<Article> articleArrayList, Context context, IClick iClick) {
        this.articleArrayList = articleArrayList;
        this.context = context;
        this.iClick = iClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        Article model = articleArrayList.get(position);
        holder.model = model;
        holder.position = holder.getAdapterPosition();

        RequestOptions requestOptions = new RequestOptions();
        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.source.setText(String.format("%s:%s", context.getString(R.string.source), model.getSource().getName()));
        holder.time.setText(Utils.DateToTimeFormat(model.getPublishedAt()));
        holder.published_ad.setText(Utils.DateFormat(model.getPublishedAt()));
        holder.author.setText(model.getAuthor());
        if (model.isFavorite())
            holder.isFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_active));
        else
            holder.isFavorite.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_favorite_inactive));

    }

    @Override
    public int getItemCount() {
        return articleArrayList == null ? 0 : articleArrayList.size();
    }

    public void refreshAfterFav(Article article, int position) {
        this.articleArrayList.set(position, article);
        notifyItemChanged(position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, author, published_ad, source, time;
        ImageView imageView, isFavorite;
        ProgressBar progressBar;
        Article model;
        int position;

        MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);
            isFavorite = itemView.findViewById(R.id.iv_favorite);
            itemView.setOnClickListener(v -> iClick.onItemClick(model));
            isFavorite.setOnClickListener(v -> iClick.onFavorite(model, position));
        }
    }

    public interface IClick {
        void onItemClick(Article model);

        void onFavorite(Article article, int position);
    }
}
