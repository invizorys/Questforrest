package com.mediahack.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mediahack.R;
import com.mediahack.presentation.presenter.QuestListPresenter;
import com.mediahack.presentation.view.QuestListView;
import com.mediahack.ui.adapter.QuestAdapter;
import com.mediahack.util.SharedPrefHelper;
import com.mediahack.util.Util;
import com.questforrest.dto.QuestProgressResponseDto;
import com.questforrest.dto.QuestShortInfoDto;

import java.util.List;

public class QuestListActivity extends AppCompatActivity implements QuestAdapter.AdapterListener,
        QuestListView {
    private QuestListPresenter presenter;
    private QuestAdapter adapter;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, QuestListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    private void showJoinQuestDialog(final Long questId, final String questName) {
        AlertDialog.Builder builder = Util.getStandardDialog(this, getString(R.string.join_to_quest));

        LinearLayout dialogView = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_join_to_quest, null);
        builder.setView(dialogView);

        final EditText editTextCreateNewTeam = (EditText) dialogView.findViewById(R.id.et_create_new_team);
        final EditText editTextJoinToTeam = (EditText) dialogView.findViewById(R.id.et_join_to_team);

        editTextCreateNewTeam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    editTextJoinToTeam.setEnabled(false);
                } else {
                    editTextJoinToTeam.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editTextJoinToTeam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    editTextCreateNewTeam.setEnabled(false);
                } else {
                    editTextCreateNewTeam.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String teamName = editTextCreateNewTeam.getText().toString();
                if (!TextUtils.isEmpty(teamName)) {
                    presenter.createTeam(questId, teamName, questName);
                } else {
                    String teamCode = editTextJoinToTeam.getText().toString();
                    presenter.enrollQuest(questId, teamCode, questName);
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.create().show();
    }

    @Override
    public void showQuestList(List<QuestShortInfoDto> quests) {
        adapter.setQuests(quests);
    }

    @Override
    public void showMessageDialog(String text) {
        Util.getStandardDialog(this, text).show();
    }

    @Override
    public void showSelectedQuest(QuestProgressResponseDto questProgressResponseDto, String questName) {
        if (questProgressResponseDto.getTaskProgresses() != null && questProgressResponseDto.getTaskProgresses().size() > 0) {
            QuestActivity.startActivity(this, questProgressResponseDto, questName);
        } else {
            Util.getStandardDialog(this, "No tasks");
        }
    }

    @Override
    public void onSuccessfulCreateTeam(QuestProgressResponseDto questProgressResponseDto,
                                       String questName) {
        QuestActivity.startActivity(this, questProgressResponseDto, questName);
    }

    @Override
    public void onFailureCreateTeam() {
        Toast.makeText(this, R.string.failure_create_team_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccessfulEnrollQuest(QuestProgressResponseDto questProgressResponseDto, String questName) {
        QuestActivity.startActivity(this, questProgressResponseDto, questName);
    }

    @Override
    public void onFailureEnrollQuest() {
        Toast.makeText(this, R.string.failure_enroll_quest_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureRetrieveQuests() {
        Toast.makeText(this, R.string.failure_retrieve_quests_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void redirectToLoginScreen() {
        SharedPrefHelper.saveToken("");
        Intent intent = new Intent(this, SignInActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onItemClicked(QuestShortInfoDto quest) {
        presenter.getQuest(quest.getId(), quest.getName());
//        QuestActivity.startActivity(this, quest);
    }

    @Override
    public void onJoinClicked(Long questId, String questName) {
        showJoinQuestDialog(questId, questName);
    }
}
