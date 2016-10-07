package com.mediahack;

import android.app.Application;

import com.mediahack.repository.db.datasource.UserDataSource;
import com.mediahack.repository.db.model.RealmUser;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Roma on 06.10.2016.
 */

public class MediaHackApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(getApplicationContext());

        UserDataSource userDataSource = new UserDataSource();
        userDataSource.addUser("user3");
        List<RealmUser> users = userDataSource.getUsers();

        for (RealmUser user : users) {
            String name = user.getName();
            String tt = "";
        }
        userDataSource.close();

    }
}
