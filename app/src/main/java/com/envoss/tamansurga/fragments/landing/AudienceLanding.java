package com.envoss.tamansurga.fragments.landing;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.envoss.tamansurga.LandingActivity;
import com.envoss.tamansurga.R;
import com.envoss.tamansurga.TamanSurgaActivity;
import com.envoss.tamansurga.fragments.BaseFragment;
import com.envoss.tamansurga.helpers.ServiceGenerator;
import com.envoss.tamansurga.helpers.UserPreference;
import com.envoss.tamansurga.interfaces.ApiService;
import com.envoss.tamansurga.models.Token;
import com.envoss.tamansurga.models.User;
import com.envoss.tamansurga.utils.GenUsername;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AudienceLanding extends BaseFragment {
    public String identity;
    private ApiService apiClient;
    private TextView tvUsername, tvUserStatus;
    private Button login;

    private Context mContext;
    private int userStatus = 0;

    private User infoUser;
    private String token;


    public AudienceLanding() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = ServiceGenerator.createService(ApiService.class);
        mContext = getContext();
        Log.d(TAG, "onCreate: Fragment " + identity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audience_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUsername = view.findViewById(R.id.username);
        tvUserStatus = view.findViewById(R.id.tv_user_status);
        login = view.findViewById(R.id.process);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (userStatus){
                    case 0:{
                        guestCreate();
                        break;
                    }

                    case 1:{
                        tryLogin();
                        break;
                    }

                    case 2:{
                        endForm();
                        break;
                    }
                }
            }
        });

        tryLogin();
    }

    public void guestCreate(){
        Call<User> userCall = apiClient.addUser(tvUsername.getText().toString(), LandingActivity.guestID, 2, "-", "Audience", GenUsername.genUsername(LandingActivity.guestID));
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    infoUser = response.body();
                    userStatus = 1;
                    login.setText("CONTINUE WITH LOGIN");
                } else {

                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void endForm(){
        UserPreference userPref = new UserPreference(mContext);
        userPref.setUsername(infoUser.getUsername());
        userPref.setEmail(infoUser.getEmail());
        userPref.setId(infoUser.getId());
        userPref.setFirstname(infoUser.getFirstName());
        userPref.setRole(infoUser.getRole());
        userPref.setLastname(infoUser.getLastName());
        userPref.setToken(token);

        Intent intent = new Intent(mContext, TamanSurgaActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void tryLogin(){
        Baseprogress.showProgressDialog(mContext, "Loading...");
        String username = "@audience" + GenUsername.genUsername(LandingActivity.guestID);
        Call<Token> call = apiClient.loginUser(username, LandingActivity.guestID);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Baseprogress.hideProgressDialog();
                if(response.isSuccessful()){
                    userStatus = 2;
                    login.setText("NEXT");
                    tvUserStatus.setText("Your device already registered in this system, here is your username");
                    tvUsername.setText(response.body().getUser().getUsername());
                    infoUser = response.body().getUser();
                    token = response.body().getToken();
                }else{
                    userStatus = 0;
                    login.setText("SIG UP AS GUEST");
                    tvUserStatus.setText("Your Device ID never Registered to this system, this username will be used to make easy identify you");
                    tvUsername.setText("@audience" + GenUsername.genUsername(LandingActivity.guestID));
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Baseprogress.hideProgressDialog();
                toastErr(mContext, t.getLocalizedMessage());
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }
}
