package io.otakuoracle.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.otakuoracle.models.AnimeObject;
import io.otakuoracle.models.CharacterAndVoiceActor;
import io.otakuoracle.models.CharacterObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class AnimeDetailsService {

    @Value("${api-url}")
    private String API_URL;

    @Value("${rapid-api-key}")
    private String RAPID_API_KEY;

    @Value("${rapid-api-host}")
    private String RAPID_API_HOST;

    @Autowired
    private AnimeObject animeObject;


    public AnimeObject getDetails(Integer id) throws IOException {

        OkHttpClient client = new OkHttpClient();

        //Request Object
        Request request = new Request.Builder()
                .url(API_URL + id)
                .header("X-RapidAPI-Key", RAPID_API_KEY)
                .header("X-RapidAPI-Host", RAPID_API_HOST)
                .build();

        Response response = client.newCall(request).execute();
        assert response.body() != null;
        String jsonString = response.body().string();

        //String jsonString = "{\"title_ov\":\"InitialDFinalStage\",\"title_en\":\"\",\"synopsis\":\"InthefinalmatchagainsttheKanagawastreetracingteamSidewinder,ProjectDstreetracerTakumiFujiwaraisfacedwithasurprisingchallenger:ShinjiInui,a17-year-oldhighschoolstudent.Beginningtodriveataveryyoungage,Shinjiisnotonlyfamiliarwiththemountainpasswheretheraceistakingplace,buthealsodrivesaToyotaAE86—thesamecarmodelTakumiuses.\\n\\r\\nInitiallyreluctanttoparticipateduetoalackofenthusiasm,ShinjibecomesmotivatedafterwitnessingthefierybattlebetweenKeisukeTakahashiandGouHoujou.UndefeatedinhishomecourseknownastheTsubakiLine,ShinjimightprovetobeTakumi'smostformidableopponentyet.\\n\\r\\n[WrittenbyMALRewrite]\",\"alternative_titles\":{\"japanese\":\"頭文字DFinalStage\"},\"information\":{\"type\":[{\"name\":\"TV\",\"url\":\"https://myanimelist.nethttps://myanimelist.net/topanime.php?type=tv\"}],\"episodes\":\"4\",\"status\":\"FinishedAiring\",\"aired\":\"May16,2014toJun22,2014\",\"premiered\":[{\"name\":\"Spring2014\",\"url\":\"https://myanimelist.nethttps://myanimelist.net/anime/season/2014/spring\"}],\"broadcast\":\"Unknown\",\"producers\":[{\"name\":\"AvexEntertainment\",\"url\":\"https://myanimelist.net/anime/producer/52/Avex_Entertainment\"}],\"licensors\":\"None\",\"studios\":[{\"name\":\"SynergySP\",\"url\":\"https://myanimelist.net/anime/producer/118/SynergySP\"}],\"source\":\"Manga\",\"genre\":\"None\",\"theme\":[{\"name\":\"Racing\",\"url\":\"https://myanimelist.net/anime/genre/3/Racing\"}],\"duration\":\"27min.perep.\",\"rating\":\"PG-13-Teens13orolder\",\"demographic\":[{\"name\":\"Seinen\",\"url\":\"https://myanimelist.net/anime/genre/42/Seinen\"}]},\"statistics\":{\"score\":8.29,\"ranked\":271,\"popularity\":1579,\"members\":144176,\"favorites\":967},\"characters\":[{\"name\":\"Fujiwara,Takumi\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/14/296425.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/2819/Takumi_Fujiwara\",\"voice_actor_name\":\"Miki,Shinichiro\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/2/70845.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/22/Shinichiro_Miki\"},{\"name\":\"Takahashi,Ryousuke\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/12/296426.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/2821/Ryousuke_Takahashi\",\"voice_actor_name\":\"Koyasu,Takehito\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/1/63375.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/160/Takehito_Koyasu\"},{\"name\":\"Takahashi,Keisuke\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/15/207581.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/2822/Keisuke_Takahashi\",\"voice_actor_name\":\"Seki,Tomokazu\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/1/55486.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/1/Tomokazu_Seki\"},{\"name\":\"Inui,Shinji\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/16/203277.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/82771/Shinji_Inui\",\"voice_actor_name\":\"Abe,Atsushi\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/3/54612.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/991/Atsushi_Abe\"},{\"name\":\"Fujiwara,Bunta\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/16/496975.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/2820/Bunta_Fujiwara\",\"voice_actor_name\":\"Ishizuka,Unshou\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/2/17135.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/357/Unshou_Ishizuka\"},{\"name\":\"Takeuchi,Itsuki\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/5/337406.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/7955/Itsuki_Takeuchi\",\"voice_actor_name\":\"Iwata,Mitsuo\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/3/24250.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/399/Mitsuo_Iwata\"},{\"name\":\"Satou,Mako\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/11/211785.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/11652/Mako_Satou\",\"voice_actor_name\":\"Neya,Michiko\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/3/54361.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/176/Michiko_Neya\"},{\"name\":\"Shouji,Shingo\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/11/286166.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/32008/Shingo_Shouji\",\"voice_actor_name\":\"Fujiwara,Keiji\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/3/57226.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/63/Keiji_Fujiwara\"},{\"name\":\"Nakazato,Takeshi\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/9/286165.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/3867/Takeshi_Nakazato\",\"voice_actor_name\":\"Hiyama,Nobuyuki\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/2/10862.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/257/Nobuyuki_Hiyama\"},{\"name\":\"Iketani,Kouichirou\",\"picture_url\":\"https://cdn.myanimelist.net/images/characters/15/525511.jpg\",\"myanimelist_url\":\"https://myanimelist.net/character/15003/Kouichirou_Iketani\",\"voice_actor_name\":\"Yao,Kazuki\",\"voice_actor_picture_url\":\"https://cdn.myanimelist.net/images/voiceactors/2/34103.jpg\",\"voice_actor_myanimelist_url\":\"https://myanimelist.net/people/131/Kazuki_Yao\"}],\"picture_url\":\"https://cdn.myanimelist.net/images/anime/1404/101364.jpg\"}";
        Gson gson = new Gson();
        Object x = gson.fromJson(jsonString, Object.class);

        Map<String, Object> map = (Map<String, Object>) x;
        Object information = map.get("information");

        animeObject.setTitle((String) map.get("title_ov"));
        animeObject.setDescription(map.get("synopsis"));
        animeObject.setPicture_url((String) map.get("picture_url"));

        Map<String, Object> informationmap = (Map<String, Object>) information;

        animeObject.setEpisodeNo((String) informationmap.get("episodes"));
        animeObject.setStatus((String) informationmap.get("status"));
        animeObject.setAiredDate((String) informationmap.get("aired"));

        Object charactersObjList = map.get("characters");
        String jsonArrayString = new Gson().toJson(charactersObjList);
        ArrayList<CharacterObject> characters = new Gson().fromJson(jsonArrayString, new TypeToken<ArrayList<CharacterObject>>() {
        }.getType());

        List<CharacterAndVoiceActor> characterAndVoiceActorList = new ArrayList<>();

        for (CharacterObject c : characters) {
            CharacterAndVoiceActor cv = new CharacterAndVoiceActor(c.getName(), c.getPicture_url(), c.getVoice_actor_name(), c.getVoice_actor_picture_url());
            characterAndVoiceActorList.add(cv);
        }

        animeObject.setCharacterAndVoiceActorList(characterAndVoiceActorList);

        Object statistics = map.get("statistics");
        Map<String, Object> statisticsMap = (Map<String, Object>) statistics;
        animeObject.setScore((Double) statisticsMap.get("score"));
        animeObject.setRanked((Double) statisticsMap.get("ranked"));
        return animeObject;
    }
}

