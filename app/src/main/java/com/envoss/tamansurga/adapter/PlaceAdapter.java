package com.envoss.tamansurga.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.envoss.tamansurga.R;
import com.envoss.tamansurga.models.Place;

import java.util.List;

public abstract class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.PlaceHolder>  {
    private List<Place> placeList;
    private Context mContext;

    public class PlaceHolder extends RecyclerView.ViewHolder{
        private TextView tvName, tvAddress, tvDesc;
        private ImageView ivPoster;
        private AppCompatButton detail, share;

        public PlaceHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvAddress = itemView.findViewById(R.id.address);
            tvDesc = itemView.findViewById(R.id.desc);
            ivPoster = itemView.findViewById(R.id.poster);
            detail = itemView.findViewById(R.id.detail);
            share = itemView.findViewById(R.id.share);
        }

        public void bindMovie(final Place place){
            tvName.setText(place.getName());
            tvAddress.setText(place.getAddress());

            tvDesc.setText(place.getDescription());

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onShareClick(place);
                }
            });
            detail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDetailClick(place);
                }
            });

//            Picasso.get()
//                    .load(BASE_IMAGE_URL + movie.getPosterPath())
//                    .resize(100, 150)
//                    .centerCrop()
//                    .into(ivPoster);
        }
    }

    public PlaceAdapter(List<Place> placeList, Context mContext) {
        this.placeList = placeList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PlaceHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new PlaceHolder(inflater.inflate(R.layout.cardview_place, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceHolder movieHolder, int i) {
        onBindViewHolder(movieHolder, i, placeList.get(i));
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }
    protected abstract void onDetailClick(Place movieResult);

    protected abstract void onShareClick(Place movieResult);

    protected abstract void onBindViewHolder(@NonNull PlaceHolder holder, int position, @NonNull Place model);
}
