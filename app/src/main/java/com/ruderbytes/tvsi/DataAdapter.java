package com.ruderbytes.tvsi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by muhammad on 10/07/17.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<Channel> channel;
    private Context context;

    public DataAdapter(ArrayList<Channel> channel, Context context) {
        this.channel = channel;
        this.context = context;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_rows, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_name.setText(channel.get(i).getChannel());
        viewHolder.tv_version.setText(channel.get(i).getImage());
        viewHolder.tv_api_level.setText(channel.get(i).getUrl());

        Glide.with(context).load(channel.get(i).getImage())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.tv_image);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Detail.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("channel",viewHolder.tv_name.getText().toString());
                intent.putExtra("image",viewHolder.tv_version.getText().toString());
                intent.putExtra("url",viewHolder.tv_api_level.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return channel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_name,tv_version,tv_api_level;
        private ImageView tv_image;
        public ViewHolder(View view) {
            super(view);

            tv_name = (TextView)view.findViewById(R.id.tv_channel);
            tv_version = (TextView)view.findViewById(R.id.tv_image);
            tv_api_level = (TextView)view.findViewById(R.id.tv_url);
            tv_image = (ImageView) view.findViewById(R.id.tv_image_main);

        }
    }

}