package com.example.weather_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weather_project.Modal.WeatherRVModal;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle==null){
            return;
        }

        WeatherRVModal weatherRVModal = (WeatherRVModal) bundle.get("weather");

        TextView tvtDeCon = findViewById(R.id.tvtDeName);
        TextView tvtDeTime = findViewById(R.id.tvtDeTime);
        TextView tvtDeTem = findViewById(R.id.tvtDeTemp);
        TextView tvtDeCloudy = findViewById(R.id.TVDeCloudy);
        TextView tvtDeHum = findViewById(R.id.TVDeTHum);
        TextView tvtDeWind = findViewById(R.id.TVDeWind);



        ImageView ImgDetail = findViewById(R.id.ImgDeCon);

        Picasso.get().load("http:".concat(weatherRVModal.getIcon())).into(ImgDetail);
        //tvtDetail.setText(weatherRVModal.getTemperature());
        tvtDeCon.setText(weatherRVModal.getText());
        tvtDeTem.setText(weatherRVModal.getTemperature()+"°");
        tvtDeCloudy.setText(weatherRVModal.getCloud());
        tvtDeHum.setText(weatherRVModal.getHumidity() + "RH");
        tvtDeWind.setText(weatherRVModal.getWindSpeed()+ "km/h");

        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output = new SimpleDateFormat("hh:mm aa");
        try {
            Date t =input.parse(weatherRVModal.getTime());
            tvtDeTime.setText(output.format(t));
        }catch (Exception e){
            e.printStackTrace();

        }
    }
}