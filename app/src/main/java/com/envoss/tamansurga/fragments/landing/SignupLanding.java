package com.envoss.tamansurga.fragments.landing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.envoss.tamansurga.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupLanding extends Fragment {


    public SignupLanding() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup_landing, container, false);
    }

}
