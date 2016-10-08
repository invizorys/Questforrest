package com.mediahack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.mediahack.R;
import com.mediahack.presentation.presenter.QuestListPresenter;
import com.mediahack.presentation.view.QuestListView;
import com.mediahack.ui.adapter.QuestAdapter;
import com.mediahack.util.Util;
import com.questforrest.dto.QuestDto;

import java.util.List;

public class QuestListActivity extends AppCompatActivity implements QuestAdapter.AdapterListener,
        QuestListView {
    private QuestListPresenter presenter;
    private QuestAdapter adapter;

    public static void startActivity(Context context, boolean clearStack) {
        Intent intent = new Intent(context, QuestListActivity.class);
        if (clearStack) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_list);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

//        ArrayList<Quest> quests = new ArrayList<>();
//        quests.add(new Quest("http://i.imgur.com/mxeb2NK.png", "Title 1", "description 1"));
//        quests.add(new Quest("https://upload.wikimedia.org/wikipedia/commons/1/1f/Theodor_Kittelsen,_Soria_Moria.jpg", "Title 2", "description 2"));
//        quests.add(new Quest("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTszzxP_fU3TiFjPcd9Q6Oqw_HsZVwnoDqXGuPMppQ0lbkAVdwYMA", "Title 3", "description 3"));
        adapter = new QuestAdapter(this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);

        presenter = new QuestListPresenter(this);
        presenter.getQuestList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_quest_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {// handle arrow click here
            finish();
        }
        if (item.getItemId() == R.id.action_update) {
            presenter.getQuestList();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showQuestList(List<QuestDto> quests) {
        adapter.setQuests(quests);
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(this, text).show();
    }

    @Override
    public void onItemClicked(QuestDto quest) {
        QuestActivity.startActivity(this, quest);
    }
}
