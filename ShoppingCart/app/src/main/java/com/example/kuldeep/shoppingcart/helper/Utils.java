package com.example.kuldeep.shoppingcart.helper;

import com.example.kuldeep.shoppingcart.db.model.CartItem;
import io.realm.RealmResults;
import timber.log.Timber;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static float getCartPrice(RealmResults<CartItem> cartItems) {
        float price = 0f;
        for (CartItem item : cartItems) {
            price += item.product.price * item.quantity;
        }
        return price;
    }

    public static String getOrderTimestamp(String timestamp) {
        SimpleDateFormat inputFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat outputFormatter = new SimpleDateFormat("d MMM, YYYY");
        try {
            Date date = inputFormatter.parse(timestamp);
            return outputFormatter.format(date);
        } catch (ParseException e) {
            Timber.e("Exception: %s", e);
        }

        return "";

    }
}
