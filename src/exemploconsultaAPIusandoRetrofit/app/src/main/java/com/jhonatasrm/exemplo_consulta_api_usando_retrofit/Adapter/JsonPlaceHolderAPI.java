package com.jhonatasrm.exemplo_consulta_api_usando_retrofit.Adapter;

import com.google.gson.JsonObject;
// import retrofit 2
import retrofit2.Call;
import retrofit2.http.GET;

//

public interface JsonPlaceHolderAPI {
    String apiKey = ""; // API Key
    // address + API
    @GET("weather?q=london&appid="+apiKey)
    Call<JsonObject> getWeather();
}
