package com.example.kuldeep.emailpermissionfirebase.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.LayoutRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class AppCompatPreferenceActivity extends PreferenceActivity {
    private AppCompatDelegate mappCompatDelegate;

    @Override
    public void onCreate(Bundle saveInstanceState)
    {
        getDelegate().installViewFactory();
        getDelegate().onCreate(saveInstanceState);
        super.onCreate(saveInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle saveInstanceState)
    {
        super.onPostCreate(saveInstanceState);
        getDelegate().onPostCreate(saveInstanceState);
    }

    ActionBar getSupportActionBar() {
        return getDelegate().getSupportActionBar();
    }

    @Override
    public MenuInflater getMenuInflater()
    {
        return getDelegate().getMenuInflater();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResIdD)
    {
        getDelegate().setContentView(layoutResIdD);
    }

    @Override
    public void setContentView(View view)
    {
        getDelegate().setContentView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params)
    {
        getDelegate().setContentView(view,params);
    }

    @Override
    public void addContentView(View view, ViewGroup.LayoutParams params)
    {
        getDelegate().addContentView(view,params);
    }

    @Override
    protected void onPostResume()
    {
        super.onPostResume();
        getDelegate().onPostResume();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        getDelegate().setTitle(title);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getDelegate().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getDelegate().onDestroy();
    }

    public void invalidateOptionsMenu() {
        getDelegate().invalidateOptionsMenu();
    }

    private AppCompatDelegate getDelegate() {
        if (mappCompatDelegate == null) {
            mappCompatDelegate = AppCompatDelegate.create(this, null);
        }
        return mappCompatDelegate;
    }

}
