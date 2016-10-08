package com.mediahack.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediahack.R;
import com.mediahack.model.Quest;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Roma on 07.10.2016.
 */

public class QuestAdapter extends RecyclerView.Adapter<QuestAdapter.ViewHolder> {
    private Context context;
    private List<Quest> quests;
    private AdapterListener listener;

    public QuestAdapter(List<Quest> quests, AdapterListener listener) {
        this.quests = quests;
        this.listener = listener;
    }

    @Override
    public QuestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_quest, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(QuestAdapter.ViewHolder holder, int position) {
        final Quest quest = quests.get(position);
        Picasso.with(context).load(quest.getImageUrl()).into(holder.imageView);
        holder.title.setText(quest.getTitle());
        holder.description.setText(quest.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(quest);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title, description;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_view);
            title = (TextView) view.findViewById(R.id.text_view_title);
            description = (TextView) view.findViewById(R.id.text_view_description);
        }
    }

    public interface AdapterListener {
        void onItemClicked(Quest quest);
    }
}
