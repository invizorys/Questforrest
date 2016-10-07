package com.mediahack.repository.db.model;

import io.realm.RealmObject;

/**
 * Created by Roma on 06.10.2016.
 */

public class RealmUser extends RealmObject {
    private String name;

    public RealmUser() {
    }

    public RealmUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
