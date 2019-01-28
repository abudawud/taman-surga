package com.envoss.tamansurga.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.envoss.tamansurga.R;
import com.envoss.tamansurga.models.Conference;
import com.envoss.tamansurga.models.Place;

import java.util.List;

public abstract class ConferenceAdapter extends RecyclerView.Adapter<ConferenceAdapter.ConferenceHolder>  {
    private List<Conference> conferenceList;
    private Context mContext;

    public class ConferenceHolder extends RecyclerView.ViewHolder{
        private TextView
                tvDateStart, tvTitle, tvDesc, tvFirstname, tvLastName,
                tvSRank, tvPlace, tvAddress, tvPlaceDesc;
        private ImageView ivPoster;
        private LinearLayout layoutMore;
        private AppCompatButton more, share;
        private boolean isMore = false;

        public ConferenceHolder(@NonNull View itemView) {
            super(itemView);
            tvDateStart = itemView.findViewById(R.id.date_start);
            tvTitle = itemView.findViewById(R.id.title);
            tvDesc = itemView.findViewById(R.id.desc);
            tvFirstname = itemView.findViewById(R.id.first_name);
            tvLastName = itemView.findViewById(R.id.last_name);
            tvSRank = itemView.findViewById(R.id.rank);
            tvPlace = itemView.findViewById(R.id.place);
            tvAddress = itemView.findViewById(R.id.address);
            tvPlaceDesc = itemView.findViewById(R.id.place_desc);

            more = itemView.findViewById(R.id.detail);
            share = itemView.findViewById(R.id.share);
            layoutMore = itemView.findViewById(R.id.layout_more);
        }

        public void bindMovie(final Conference conference){
            tvDateStart.setText(conference.getStartAt());
            tvTitle.setText(conference.getTitle());
            tvDesc.setText(conference.getDescription());
            tvFirstname.setText(conference.getSpeaker().getFirstName());
            tvLastName.setText(conference.getSpeaker().getLastName());
            tvSRank.setText(conference.getSpeaker().getRank());
            tvPlace.setText(conference.getPlace().getName());
            tvAddress.setText(conference.getPlace().getAddress());
            tvPlaceDesc.setText(conference.getPlace().getDescription());

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onShareClick(conference);
                }
            });
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isMore){
                        layoutMore.setVisibility(View.GONE);
                        more.setText("MORE");
                        isMore = false;
                    }else{
                        isMore = true;
                        more.setText("LESS");
                        layoutMore.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public ConferenceAdapter(List<Conference> conferenceList, Context mContext) {
        this.conferenceList = conferenceList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ConferenceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ConferenceHolder(inflater.inflate(R.layout.cardview_conference, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConferenceHolder conferenceHolder, int i) {
        onBindViewHolder(conferenceHolder, i, conferenceList.get(i));
    }

    @Override
    public int getItemCount() {
        return conferenceList.size();
    }
    protected abstract void onDetailClick(Conference movieResult);

    protected abstract void onShareClick(Conference movieResult);

    protected abstract void onBindViewHolder(@NonNull ConferenceHolder holder, int position, @NonNull Conference model);
}
