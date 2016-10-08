package com.mediahack.repository.db.datasource;
import android.content.Context;

import com.mediahack.repository.db.model.RealmUser;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Roma on 06.10.2016.
 */

public class UserDataSource {
    private Realm realm;

    public UserDataSource() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("MediaHack.realm")
                .schemaVersion(1)
//                .modules(new MyCustomSchema())
                .build();
        realm = Realm.getInstance(config);
    }

    public void addUser(String name) {
        realm.beginTransaction();
        RealmUser user = realm.createObject(RealmUser.class);
        user.setName(name);
        realm.commitTransaction();
    }

    public List<RealmUser> getUsers() {
        List<RealmUser> result = realm.where(RealmUser.class).findAll();
        return result;
    }

    public void close() {
        realm.close();
    }
}
