package com.example.kuldeep.shoppingcart.db;

import io.realm.Realm;
import io.realm.RealmResults;

import com.example.kuldeep.shoppingcart.db.model.CartItem;
import com.example.kuldeep.shoppingcart.db.model.User;
import com.example.kuldeep.shoppingcart.networking.model.Product;
import com.example.kuldeep.shoppingcart.networking.model.AppConfig;

import java.util.List;


public class AppDatabase {
    public AppDatabase() {
    }

    public static void saveUser(final User user) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(User.class); // deleting previous user data
                realm.copyToRealmOrUpdate(user);
            }
        });
    }

    public static User getUser() {
        return Realm.getDefaultInstance().where(User.class).findFirst();
    }

    public static void saveAppConfig(final AppConfig appConfig) {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(appConfig);
            }
        });
    }

    public static AppConfig getAppConfig() {
        return Realm.getDefaultInstance().where(AppConfig.class).findFirst();
    }

    public static void saveProducts(final List<Product> products) {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Product product : products) {
                    realm.copyToRealmOrUpdate(product);
                }
            }
        });
    }

    public static RealmResults<Product> getProducts() {
        return Realm.getDefaultInstance().where(Product.class).findAll();
    }

    /**
     * Adding product to cart
     * Will create a new cart entry if there is no cart created yet
     * Will increase the product quantity count if the item exists already
     */
    public static void addItemToCart(Product product) {
        initNewCart(product);
    }

    private static void initNewCart(final Product product) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CartItem cartItem = realm.where(CartItem.class).equalTo("product.id", product.id).findFirst();
                if (cartItem == null) {
                    CartItem ci = new CartItem();
                    ci.product = product;
                    ci.quantity = 1;
                    realm.copyToRealmOrUpdate(ci);
                } else {
                    cartItem.quantity += 1;
                    realm.copyToRealmOrUpdate(cartItem);
                }
            }
        });
    }

    public static void removeCartItem(final Product product) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CartItem cartItem = realm.where(CartItem.class).equalTo("product.id", product.id).findFirst();
                if (cartItem != null) {
                    if (cartItem.quantity == 1) {
                        cartItem.deleteFromRealm();
                    } else {
                        cartItem.quantity -= 1;
                        realm.copyToRealmOrUpdate(cartItem);
                    }
                }
            }
        });
    }

    public static void removeCartItem(final CartItem cartItem) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(CartItem.class).equalTo("product.id", cartItem.product.id).findAll().deleteAllFromRealm();
            }
        });
    }

    public static void clearCart() {
        Realm.getDefaultInstance().executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(CartItem.class);
            }
        });
    }

    public static void clearData() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
    }
}
