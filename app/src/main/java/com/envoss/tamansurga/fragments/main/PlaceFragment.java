package com.envoss.tamansurga.fragments.main;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.envoss.tamansurga.ListConferenceActivity;
import com.envoss.tamansurga.R;
import com.envoss.tamansurga.TamanSurgaActivity;
import com.envoss.tamansurga.adapter.PlaceAdapter;
import com.envoss.tamansurga.helpers.ServiceGenerator;
import com.envoss.tamansurga.interfaces.ApiService;
import com.envoss.tamansurga.models.Place;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceFragment extends Fragment {
    private Context mContext;
    private ApiService apiService;
    private final String TAG = "X-LOG";

    ProgressBar progressBar;
    RelativeLayout emptyLayout;
    TextView emptyMsg;
    ImageView icon;

    private List<Place> placeList;
    private PlaceAdapter placeAdapter;

    private static final String ARG_PARAM1 = "param1";
    private String endpointPath;
    private int pathID;



    public PlaceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        apiService = ServiceGenerator.createService(ApiService.class);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_place, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        icon = view.findViewById(R.id.empty_icon);
        emptyLayout = view.findViewById(R.id.empty_layout);
        progressBar = view.findViewById(R.id.progress);
        emptyMsg = view.findViewById(R.id.empty_msg);

        initRecyc(view);
        fetchPlaces();

    }

    private void initRecyc(View view){
        placeList = new ArrayList<>();
        RecyclerView recyclerView = view.findViewById(R.id.recView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        placeAdapter = new PlaceAdapter(placeList, mContext) {
            @Override
            protected void onBindViewHolder(@NonNull PlaceHolder holder, int position, @NonNull final Place model) {
                holder.bindMovie(model);
            }

            @Override
            protected void onDetailClick(Place place) {
                Intent intent = new Intent(mContext, ListConferenceActivity.class);
                intent.putExtra("ID", place.getId());
                intent.putExtra("CATEGORY", "places");
                intent.putExtra("TITLE", place.getName());
                startActivity(intent);
            }

            @Override
            protected void onShareClick(Place movieResult) {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms: 085205225932"));
                Uri uri = Uri.parse("hello");

                intent.putExtra("uri", uri);
                //intent.putExtra("MSG", movieResult.getOverview());
                startActivity(intent);
            }
        };

        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(placeAdapter);
    }

    private void fetchPlaces(){
        progressBar.setVisibility(View.VISIBLE);

        Call<List <Place> > call = apiService.getPlaces(TamanSurgaActivity.token);

        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                progressBar.setVisibility(View.GONE);
                if(response.isSuccessful()){
                    emptyLayout.setVisibility(View.GONE);
                    placeList.clear();
                    placeList.addAll(response.body());
                    placeAdapter.notifyDataSetChanged();
                }else{
                    Log.d(TAG, "onResponse: " + response.message());
                    icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_error_outline));
                    emptyMsg.setText(getString(R.string.msg_error));
                }
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_error_outline));
                emptyMsg.setText(getString(R.string.msg_error));
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });

    }

}
