package com.example.weather_project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather_project.DetailActivity;
import com.example.weather_project.Modal.WeatherRVModal;
import com.example.weather_project.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<WeatherRVModal> weatherRVModalArrayList;

    public WeatherRVAdapter(Context context, ArrayList<WeatherRVModal> weatherRVModalArrayList) {
        this.context = context;
        this.weatherRVModalArrayList = weatherRVModalArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        WeatherRVModal modal = weatherRVModalArrayList.get(position);
        if(modal == null){
            return;
        }
        holder.temperatureTV.setText(modal.getTemperature()+"°C");
        holder.windTV.setText(modal.getWindSpeed()+"km/h");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.conditionIV);
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try{
            Date t =input.parse(modal.getTime());
            holder.timeTV.setText(output.format(t));
        }
        catch (ParseException e){
            e.printStackTrace();

        }
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDetail(modal);
            }
        });

    }

    private void onClickDetail(WeatherRVModal modal){
        Intent intent = new Intent(context , DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("weather", modal);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return weatherRVModalArrayList.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{
        private RelativeLayout relativeLayout;
        private TextView windTV, temperatureTV, timeTV;
        private ImageView conditionIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            windTV = itemView.findViewById(R.id.idTVWindSeed);
            temperatureTV = itemView.findViewById(R.id.idTVTemperatures);
            timeTV = itemView.findViewById(R.id.idTVTime);
            conditionIV  = itemView.findViewById(R.id.idIVConditions);
            relativeLayout = itemView.findViewById(R.id.layout_item);
        }
    }
}
