package com.envoss.tamansurga;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;


import com.envoss.tamansurga.adapter.ConferenceAdapter;
import com.envoss.tamansurga.helpers.ServiceGenerator;
import com.envoss.tamansurga.interfaces.ApiService;
import com.envoss.tamansurga.models.Conference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListConferenceActivity extends AppCompatActivity {
    private Context mContext;
    private ApiService apiService;
    private final String TAG = "X-LOG";
    private int id;
    private String category;

    private List<Conference> conferenceList;
    private ConferenceAdapter conferenceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_conference);
        mContext = this;

        Bundle bundle = getIntent().getExtras();
        id = bundle.getInt("ID");
        category = bundle.getString("CATEGORY");
        setTitle("TS - " + bundle.getString("TITLE"));

        apiService = ServiceGenerator.createService(ApiService.class);
        initRecyc();
        fetchConferences();
    }

    private void fetchConferences(){
        Call<List<Conference>> call = apiService.getConferenceBy(category, id, TamanSurgaActivity.token);
        call.enqueue(new Callback<List<Conference>>() {
            @Override
            public void onResponse(Call<List<Conference>> call, Response<List<Conference>> response) {
                if(response.isSuccessful()){
                    conferenceList.clear();
                    conferenceList.addAll(response.body());
                    conferenceAdapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Conference>> call, Throwable t) {

            }
        });
    }

    private void initRecyc(){
        conferenceList = new ArrayList<>();
        RecyclerView recyclerView = findViewById(R.id.recView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        conferenceAdapter = new ConferenceAdapter(conferenceList, mContext) {
            @Override
            protected void onBindViewHolder(@NonNull ConferenceHolder holder, int position, @NonNull final Conference model) {
                holder.bindMovie(model);
            }

            @Override
            protected void onDetailClick(Conference conference) {
                Intent intent = new Intent(mContext, ListConferenceActivity.class);
                //intent.putExtra(DetailMovieActivity.EXTRA_MOVIE_DATA, movieResult.getId());
                startActivity(intent);
            }

            @Override
            protected void onShareClick(Conference conference) {
                String data;
                data = "Bismillah...\nDengan hanya mengharap ridho Allah, Hadirilah Kajian:\n";
                data += conference.getTitle() + "\n";
                data += "Bersama: " + conference.getSpeaker().getFirstName() + "\n";
                data += "Tempat di: " + conference.getPlace().getName() + "\n";
                data += "Alamat: " + conference.getPlace().getAddress() + "\n\n";
                data += "Yang insyaAllah pada: " + conference.getStartAt() + "\n";
                data += ".::Semoga Allah Mudahkan Langkah Kita::..";

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, data);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
            }
        };

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(conferenceAdapter);
    }
}
