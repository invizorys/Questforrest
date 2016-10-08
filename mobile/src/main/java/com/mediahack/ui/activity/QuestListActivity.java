package com.mediahack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mediahack.R;
import com.mediahack.model.Quest;
import com.mediahack.ui.adapter.QuestAdapter;

import java.util.ArrayList;

public class QuestListActivity extends AppCompatActivity implements QuestAdapter.AdapterListener {

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, QuestListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        ArrayList<Quest> quests = new ArrayList<>();
        quests.add(new Quest("http://i.imgur.com/mxeb2NK.png", "Title 1", "description 1"));
        quests.add(new Quest("https://upload.wikimedia.org/wikipedia/commons/1/1f/Theodor_Kittelsen,_Soria_Moria.jpg", "Title 2", "description 2"));
        quests.add(new Quest("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTszzxP_fU3TiFjPcd9Q6Oqw_HsZVwnoDqXGuPMppQ0lbkAVdwYMA", "Title 3", "description 3"));
        QuestAdapter adapter = new QuestAdapter(quests, this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(Quest quest) {
        QuestActivity.startActivity(this, quest);
    }
}
