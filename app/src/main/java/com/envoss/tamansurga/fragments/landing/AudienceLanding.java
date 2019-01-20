package com.envoss.tamansurga.fragments.landing;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.envoss.tamansurga.LandingActivity;
import com.envoss.tamansurga.R;
import com.envoss.tamansurga.fragments.BaseFragment;
import com.envoss.tamansurga.helpers.ApiClient;
import com.envoss.tamansurga.helpers.ServiceGenerator;
import com.envoss.tamansurga.interfaces.ApiService;
import com.envoss.tamansurga.models.ApiError;
import com.envoss.tamansurga.models.Token;
import com.envoss.tamansurga.utils.ApiUtil;
import com.envoss.tamansurga.utils.ErrorUtil;
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
    private TextView tvUsername;
    private Context mContext;


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
        tryLogin();
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

                }else{
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
