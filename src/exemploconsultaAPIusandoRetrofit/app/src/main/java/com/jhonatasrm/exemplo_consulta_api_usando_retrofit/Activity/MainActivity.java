package com.jhonatasrm.exemplo_consulta_api_usando_retrofit.Activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.jhonatasrm.exemplo_consulta_api_usando_retrofit.Adapter.JsonPlaceHolderAPI;
import com.jhonatasrm.exemplo_consulta_api_usando_retrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView city, weather, latitude, longitude, windSpeed, description, tempMin, tempMax, temp;
    private TextView humidity, atm, loading;
    private JsonPlaceHolderAPI jsonPlaceHolderAPI;
    private int timeAnimation;
    private ProgressBar progressBar;
    private LinearLayout linear1, linear2, linear3, linear4, linear5, linear6, linear8, linear7, linear9, linear10;

    // método onCreate
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intitializeFinds();
        setTitle(R.string.title);

        // inicialização do Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://samples.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI.class);
        timeAnimation = getResources().getInteger(android.R.integer.config_longAnimTime);

        new Thread(new Runnable() {
            @Override
            public void run() {
                getWeather();
            }
        }).start();

    }

    // método para buscar na API 
    public void getWeather() {
        Call<JsonObject> call = jsonPlaceHolderAPI.getWeather();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                if (!response.isSuccessful()) {
                    fail();
                    return;
                }

                JsonObject object = response.body();
                String aCity = response.body().get("name").getAsString();
                aCity = aCity.replace('"', ' ');
                city.setText(aCity);
                latitude.setText(String.valueOf(object.get("coord").getAsJsonObject().get("lat").getAsFloat()));
                longitude.setText(String.valueOf(object.get("coord").getAsJsonObject().get("lon").getAsFloat()));
                windSpeed.setText(String.valueOf(object.get("wind").getAsJsonObject().get("speed").getAsString()));
                weather.setText(String.valueOf(object.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString()));
                description.setText(String.valueOf(object.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString()));
                humidity.setText(String.valueOf(object.get("main").getAsJsonObject().get("humidity").getAsString()));
                atm.setText(String.valueOf(object.get("main").getAsJsonObject().get("pressure").getAsString()));
                temp.setText(String.valueOf(object.get("main").getAsJsonObject().get("temp").getAsString()));
                tempMin.setText(String.valueOf(object.get("main").getAsJsonObject().get("temp_min").getAsString()));
                tempMax.setText(String.valueOf(object.get("main").getAsJsonObject().get("temp_max").getAsString()));

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setVisibility();
                    }
                });
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                fail();
            }
        });
    }

    // incializa os findViewById
    public void intitializeFinds() {
        city = findViewById(R.id.city);
        weather = findViewById(R.id.weather);
        latitude = findViewById(R.id.latitude);
        longitude = findViewById(R.id.longitude);
        windSpeed = findViewById(R.id.windSpeed);
        description = findViewById(R.id.description);
        temp = findViewById(R.id.temp);
        tempMin = findViewById(R.id.tempMin);
        tempMax = findViewById(R.id.tempMax);
        humidity = findViewById(R.id.humidity);
        atm = findViewById(R.id.atm);
        progressBar = findViewById(R.id.progressBar);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear3 = findViewById(R.id.linear3);
        linear4 = findViewById(R.id.linear4);
        linear5 = findViewById(R.id.linear5);
        linear6 = findViewById(R.id.linear6);
        linear7 = findViewById(R.id.linear7);
        linear8 = findViewById(R.id.linear8);
        linear9 = findViewById(R.id.linear9);
        linear10 = findViewById(R.id.linear10);
        loading = findViewById(R.id.loading);
    }

    // caso não for possivel carregar o campo apresenta erro
    public void fail() {
        city.setText("Falha ao carregar");
        weather.setText("Falha ao carregar");
        latitude.setText("Falha ao carregar");
        longitude.setText("Falha ao carregar");
        windSpeed.setText("Falha ao carregar");
        description.setText("Falha ao carregar");
    }

    // visivel e alterar alpha
    public void setVisibility() {
        city.setVisibility(View.VISIBLE);
        latitude.setVisibility(View.VISIBLE);
        longitude.setVisibility(View.VISIBLE);
        windSpeed.setVisibility(View.VISIBLE);
        weather.setVisibility(View.VISIBLE);
        description.setVisibility(View.VISIBLE);
        humidity.setVisibility(View.VISIBLE);
        atm.setVisibility(View.VISIBLE);
        temp.setVisibility(View.VISIBLE);
        tempMin.setVisibility(View.VISIBLE);
        tempMax.setVisibility(View.VISIBLE);
        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.VISIBLE);
        linear3.setVisibility(View.VISIBLE);
        linear4.setVisibility(View.VISIBLE);
        linear5.setVisibility(View.VISIBLE);
        linear6.setVisibility(View.VISIBLE);
        linear7.setVisibility(View.VISIBLE);
        linear8.setVisibility(View.VISIBLE);
        linear9.setVisibility(View.VISIBLE);
        linear10.setVisibility(View.VISIBLE);

        city.setAlpha(0.0f);
        latitude.setAlpha(0.0f);
        longitude.setAlpha(0.0f);
        windSpeed.setAlpha(0.0f);
        weather.setAlpha(0.0f);
        description.setAlpha(0.0f);
        humidity.setAlpha(0.0f);
        atm.setAlpha(0.0f);
        temp.setAlpha(0.0f);
        tempMin.setAlpha(0.0f);
        tempMax.setAlpha(0.0f);
        linear1.setAlpha(0.0f);
        linear2.setAlpha(0.0f);
        linear3.setAlpha(0.0f);
        linear4.setAlpha(0.0f);
        linear5.setAlpha(0.0f);
        linear6.setAlpha(0.0f);
        linear7.setAlpha(0.0f);
        linear8.setAlpha(0.0f);
        linear9.setAlpha(0.0f);
        linear10.setAlpha(0.0f);

        city.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        latitude.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        longitude.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        windSpeed.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        weather.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        description.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        humidity.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        atm.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        temp.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        tempMin.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        tempMax.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear1.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear2.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear3.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear4.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear5.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear6.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear7.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear8.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear9.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);
        linear10.animate()
                .alpha(1.0f)
                .setDuration(timeAnimation)
                .setListener(null);

        progressBar.animate()
                .alpha(0.0f)
                .setDuration(timeAnimation)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        progressBar.setVisibility(View.GONE);
                    }
                });
        loading.animate()
                .alpha(0.0f)
                .setDuration(timeAnimation)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        loading.setVisibility(View.GONE);
                    }
                });
    }
}
