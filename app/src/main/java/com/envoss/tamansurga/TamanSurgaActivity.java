package com.envoss.tamansurga;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.envoss.tamansurga.fragments.main.PlaceFragment;
import com.envoss.tamansurga.fragments.main.ProfileFragment;
import com.envoss.tamansurga.fragments.main.SpeakerFragment;
import com.envoss.tamansurga.helpers.UserPreference;
import com.envoss.tamansurga.models.User;

public class TamanSurgaActivity extends AppCompatActivity {
    private UserPreference userPref;
    private Context mContext;
    public static User userInfo;
    public static String token;

    private FragmentManager fm;
    private ProfileFragment profileFragment;
    private PlaceFragment placeFragment;
    private SpeakerFragment speakerFragment;

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = fm.beginTransaction();
            switch (item.getItemId()) {
                case R.id.nav_profile:
                    transaction.replace(R.id.container, profileFragment);
                    transaction.commit();
                    setTitle("TS - Profile");
                    return true;
                case R.id.nav_place:
                    if(placeFragment == null){
                        placeFragment = new PlaceFragment();
                    }
                    transaction.replace(R.id.container, placeFragment);
                    transaction.commit();
                    setTitle("TS - List Place");
                    return true;
                case R.id.nav_speaker:
                    if(speakerFragment == null){
                        speakerFragment = new SpeakerFragment();
                    }
                    transaction.replace(R.id.container, speakerFragment);
                    transaction.commit();
                    setTitle("TS - List Speaker");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taman_surga);

        mContext = this;
        userPref = new UserPreference(mContext);
        userInfo = new User();
        userInfo.setUsername(userPref.getUsername());
        userInfo.setFirstName(userPref.getFristname());
        userInfo.setLastName(userPref.getLastname());
        userInfo.setEmail(userPref.getEmail());
        userInfo.setRole(userPref.getRole());
        userInfo.setId(userPref.getId());
        token = userPref.getToken();

        fm = getSupportFragmentManager();
        profileFragment = new ProfileFragment();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, profileFragment);
        transaction.commit();

        setTitle("TS - Profile");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
