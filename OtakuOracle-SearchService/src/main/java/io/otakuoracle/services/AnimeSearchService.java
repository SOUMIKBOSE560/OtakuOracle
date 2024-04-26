package io.otakuoracle.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.otakuoracle.models.APIResponse;
import io.otakuoracle.models.AnimeDetail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class AnimeSearchService {

    @Value("${rapid-api-key}")
    private String RAPID_API_KEY;

    @Value("${rapid-api-host}")
    private String RAPID_API_HOST;

    @Value("${default-size}")
    private int DEFAULT_SEARCH_SIZE;

    @Value("${default-score}")
    private int DEFAULT_SCORE;

    @Value("${url}")
    public String URL;


    public List<AnimeDetail> search(String name) {
        try {
            //CREATE A OKHTTP CLIENT
            OkHttpClient client = new OkHttpClient();

            //CREATE REQUEST OBJECT
            Request request = new Request.Builder()
                    .url(URL + name +
                            "&n=" + DEFAULT_SEARCH_SIZE + "&score=" + DEFAULT_SCORE)
                    .header("X-RapidAPI-Key", RAPID_API_KEY)
                    .header("X-RapidAPI-Host", RAPID_API_HOST)
                    .build();

            //EXECUTE CALL
            Response response = client.newCall(request).execute();
            String jsonString = response.body().string();


            //CONVERT JSON STRING TO JSON ARRAY
            JSONArray jsonArray = new JSONArray(jsonString);
            List<AnimeDetail> animeDetailsList = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                AnimeDetail animeDetail = new AnimeDetail();
                animeDetail.setTitle(jsonObject.getString("title"));
                animeDetail.setDescription(jsonObject.getString("description"));
                animeDetail.setPicture_url(jsonObject.getString("picture_url"));
                animeDetail.setMyanimelist_url(jsonObject.getString("myanimelist_url"));
                animeDetail.setMyanimelist_id(jsonObject.getInt("myanimelist_id"));
                animeDetailsList.add(animeDetail);
            }


            return animeDetailsList;


        } catch (IOException e) {
            throw new RuntimeException(e);

        }

    }
}
