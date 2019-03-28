package com.example.kuldeep.shoppingcart.db.model;

import com.example.kuldeep.shoppingcart.networking.model.Product;
//import com.example.kuldeep.shoppingcart.
//import info.androidhive.paytmgateway.networking.model.Product;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import java.util.UUID;

public class CartItem extends RealmObject {
    @PrimaryKey
    public String id = UUID.randomUUID().toString();
    public Product product;
    public int quantity = 0;
}
