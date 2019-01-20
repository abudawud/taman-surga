package com.envoss.tamansurga;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.envoss.tamansurga.fragments.landing.AudienceLanding;

public class UserLandingActivity extends AppCompatActivity {
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_landing);

        Fragment audienceFragment = new AudienceLanding();

        fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, audienceFragment);
        transaction.commit();
    }
}
