package com.mediahack.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mediahack.R;

import java.util.List;

/**
 * Created by Roma on 07.10.2016.
 */

public class SomeAdapter extends RecyclerView.Adapter<SomeAdapter.ViewHolder>  {
    private List<Object> someList;
    private AdapterListener listener;

    public SomeAdapter(List<Object> someList, AdapterListener listener) {
        this.someList = someList;
        this.listener = listener;
    }

    @Override
    public SomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_some, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SomeAdapter.ViewHolder holder, int position) {
        Object someObject = someList.get(position);
//        holder.title.setText(someObject.getTitle());
    }

    @Override
    public int getItemCount() {
        return someList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    public interface AdapterListener {

    }
}
