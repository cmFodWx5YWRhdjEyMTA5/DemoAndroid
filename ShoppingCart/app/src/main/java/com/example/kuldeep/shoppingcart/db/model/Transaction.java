package com.example.kuldeep.shoppingcart.db.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Transaction extends RealmObject {
    @PrimaryKey
    int id;
}
