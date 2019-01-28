package com.envoss.tamansurga.fragments.main;


import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.envoss.tamansurga.R;
import com.envoss.tamansurga.TamanSurgaActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvUsername, tvEmail, tvFirstname, tvLastname, tvRole;

        tvUsername = view.findViewById(R.id.username);
        tvEmail = view.findViewById(R.id.email);
        tvFirstname = view.findViewById(R.id.first_name);
        tvLastname = view.findViewById(R.id.last_name);
        tvRole = view.findViewById(R.id.role);

        tvUsername.setText(TamanSurgaActivity.userInfo.getUsername());
        tvEmail.setText(TamanSurgaActivity.userInfo.getEmail());
        tvFirstname.setText(TamanSurgaActivity.userInfo.getFirstName());
        tvLastname.setText(TamanSurgaActivity.userInfo.getLastName());
        switch (TamanSurgaActivity.userInfo.getRole()){
            case 1:{
                tvRole.setText("Admin");
                break;
            }

            case 2:{
                tvRole.setText("Guest");
                break;
            }
        }

    }
}
