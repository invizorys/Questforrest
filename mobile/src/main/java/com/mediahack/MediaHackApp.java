package com.mediahack;

import android.app.Application;

import com.vk.sdk.VKSdk;

/**
 * Created by Roma on 06.10.2016.
 */

public class MediaHackApp extends Application {
    private static MediaHackApp instance;

    public MediaHackApp() {
        instance = this;
    }

    public static MediaHackApp instance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        VKSdk.initialize(this);

//        Realm.init(getApplicationContext());
//
//        UserDataSource userDataSource = new UserDataSource();
//        userDataSource.addUser("user3");
//        List<RealmUser> users = userDataSource.getUsers();
//
//        for (RealmUser user : users) {
//            String name = user.getName();
//            String tt = "";
//        }
//        userDataSource.close();

    }
}
