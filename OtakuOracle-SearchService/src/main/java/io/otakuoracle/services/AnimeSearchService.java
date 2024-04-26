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
            //String jsonString = "[{\"title\":\"OnePieceFilm:Z\",\"description\":\"TheStrawHatPiratesentertheroughseasoftheNewWorldinsearchofthehiddentreasuresofthePirateKing,GolD.Roger－OnePiece.Ontheirvoyage,thepiratescomeacrossaterrifying,powerfu...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/6/44297.jpg?s=ab00c4fc2882d3bb72c1985ada0af886\",\"myanimelist_url\":\"https://myanimelist.net/anime/12859/One_Piece_Film__Z\",\"myanimelist_id\":12859},{\"title\":\"OnePiece\",\"description\":\"Barelysurvivinginabarrelafterpassingthroughaterriblewhirlpoolatsea,carefreeMonkeyD.Luffyendsupaboardashipunderattackbyfearsomepirates.Despitebeinganaive-lookingteenager,...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/6/73245.jpg?s=f792b8c9e28534ae455d06b15e686a14\",\"myanimelist_url\":\"https://myanimelist.net/anime/21/One_Piece\",\"myanimelist_id\":21},{\"title\":\"OnePieceMovie14:Stampede\",\"description\":\"MonkeyD.LuffyandtheStrawHatsarriveaboardtheSunnytothePiratesFestival,theworld'slargestcelebrationcreatedbyandforpirates.BuenaFesta,thefestivalorganizer,invitestheStrawH...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1221/100550.jpg?s=0a8df0aea5d9091e628bab80b0dfae26\",\"myanimelist_url\":\"https://myanimelist.net/anime/38234/One_Piece_Movie_14__Stampede\",\"myanimelist_id\":38234},{\"title\":\"OnePieceFilm:StrongWorld\",\"description\":\"UponhearingnewsthatislandsinEastBluearebeingdestroyed,MonkeyD.Luffyandhiscrewgotoinvestigate.Ontheirway,however,anoutlandishpirateshipappearsoutofthesky,helmedbythe...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1192/116784.jpg?s=ab8dc5d03ecadd39b7b9ddff4cfbfb82\",\"myanimelist_url\":\"https://myanimelist.net/anime/4155/One_Piece_Film__Strong_World\",\"myanimelist_id\":4155},{\"title\":\"OnePiece:EpisodeofMerry-MouHitorinoNakamanoMonogatari\",\"description\":\"AfterBrookquestionstheoriginoftheMiniMerryname,UsoppandChopperTonyTonyrecalltheadventuresoftheStrawHatCrew'sbelovedship,friend,andcrewmember—theGoingMerry.Ussopabandons...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/9/61015.jpg?s=423da3514c8cd99731d53c53d42dc0de\",\"myanimelist_url\":\"https://myanimelist.net/anime/19123/One_Piece__Episode_of_Merry_-_Mou_Hitori_no_Nakama_no_Monogatari\",\"myanimelist_id\":19123},{\"title\":\"OnePiece:EpisodeofNami-KoukaishinoNamidatoNakamanoKizuna\",\"description\":\"NamibetraystheStrawHatcrewandstealstheirshipandtreasures,escapingtoArlongPark.Luffyandhiscrewfollowher,onlytocomefacetofacewiththeinfamouspirateArlong,aruthlessfish-...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/5/41415.jpg?s=a361909199c47e119eae1657912630dc\",\"myanimelist_url\":\"https://myanimelist.net/anime/15323/One_Piece__Episode_of_Nami_-_Koukaishi_no_Namida_to_Nakama_no_Kizuna\",\"myanimelist_id\":15323},{\"title\":\"Horimiya:Piece\",\"description\":\"AsthegraduationceremonyatKatagiriHighSchoolcomestoanend,KyoukoHori,herboyfriendIzumiMiyamura,andtheirfriendsbegintolookbackontheirtimeasstudents.Themomentstheysharedt...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1007/136277.jpg?s=d22b7899092ba971fcb188f8d69885e3\",\"myanimelist_url\":\"https://myanimelist.net/anime/54856/Horimiya__Piece\",\"myanimelist_id\":54856},{\"title\":\"Dr.Stone:StoneWars\",\"description\":\"Senkuuhasmadeithisgoaltobringbacktwomillionyearsofhumanachievementandrevivetheentiretyofthoseturnedtostatues.However,onemanstandsinhisway:TsukasaShishiou,whobelieves...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1711/110614.jpg?s=a94924d10d3c81a9e4e32a06863e28c1\",\"myanimelist_url\":\"https://myanimelist.net/anime/40852/Dr_Stone__Stone_Wars\",\"myanimelist_id\":40852},{\"title\":\"KoukakuKidoutai:StandAloneComplex-SolidStateSociety\",\"description\":\"A.D.2034.IthasbeentwoyearssinceMotokoKusanagileftSection9.Togusaisnowthenewleaderoftheteam,thathasconsiderablyincreaseditsappointedpersonnel.TheexpandednewSection9con...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/4/29816.jpg?s=fa171addfee4a69ef254136867ca9dae\",\"myanimelist_url\":\"https://myanimelist.net/anime/1566/Koukaku_Kidoutai__Stand_Alone_Complex_-_Solid_State_Society\",\"myanimelist_id\":1566},{\"title\":\"KoukakuKidoutai:StandAloneComplex\",\"description\":\"Inthenotsodistantfuture,mankindhasadvancedtoastatewherecompletebodytransplantsfromfleshtomachineispossible.Thisallowsforgreatincreasesinbothphysicalandcyberneticprowess...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/11/50857.jpg?s=4ac2b90079ffba4dee9a72852d852779\",\"myanimelist_url\":\"https://myanimelist.net/anime/467/Koukaku_Kidoutai__Stand_Alone_Complex\",\"myanimelist_id\":467},{\"title\":\"DetectiveConan:EpisodeOne-TheGreatDetectiveTurnedSmall\",\"description\":\"The\\\"real\\\"episode1ofDetectiveConanwrittenbytheMangakaGoshoAoyama.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/5/81968.jpg?s=1defa5600c30b7e04ac1832371029e2f\",\"myanimelist_url\":\"https://myanimelist.net/anime/34036/Detective_Conan__Episode_One_-_The_Great_Detective_Turned_Small\",\"myanimelist_id\":34036},{\"title\":\"Dr.Stone:Ryuusui\",\"description\":\"Nowthatbrainsandbrawnshaveunitedforces,thenextstepinSenkuu'splantounravelthemysterybehindthegreenlightthatoncepetrifiedhumanityistogototheothersideoftheEarthandinv...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1071/124921.jpg?s=61126752169e984061acc9d322fc45ed\",\"myanimelist_url\":\"https://myanimelist.net/anime/50612/Dr_Stone__Ryuusui\",\"myanimelist_id\":50612},{\"title\":\"OnePunchMan\",\"description\":\"TheseeminglyunimpressiveSaitamahasaratheruniquehobby:beingahero.Inordertopursuehischildhooddream,Saitamarelentlesslytrainedforthreeyears,losingallofhishairintheprocess....readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/12/76049.jpg?s=40b6c7dbbbb94c44675116d301150078\",\"myanimelist_url\":\"https://myanimelist.net/anime/30276/One_Punch_Man\",\"myanimelist_id\":30276},{\"title\":\"KoukakuKidoutai:StandAloneComplex-TheLaughingMan\",\"description\":\"In2024,theterroristincidentknownas\\\"TheLaughingManIncident\\\"occurredinwhichErnestSerano,presidentofthegroundbreakingmicromachinecompany,SeranoGenomics,waskidnappedandransomed....readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/3/21835.jpg?s=bc0b396c882f30879a767eca9e17cc38\",\"myanimelist_url\":\"https://myanimelist.net/anime/2449/Koukaku_Kidoutai__Stand_Alone_Complex_-_The_Laughing_Man\",\"myanimelist_id\":2449},{\"title\":\"HachimitsutoClover\",\"description\":\"YuutaTakemoto,asophomoreatanartscollege,sharesacheapapartmentwithtwoseniors—theeccentricShinobuMorita,whokeepsfailingtograduateduetohisabsenteeism,andthesensibleTakumiMa...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1301/133577.jpg?s=fb39f0f4453987a67aa1cd9cffdac92f\",\"myanimelist_url\":\"https://myanimelist.net/anime/16/Hachimitsu_to_Clover\",\"myanimelist_id\":16},{\"title\":\"Dr.Stone:NewWorld\",\"description\":\"WiththeambitiousRyuusuiNanamionboard,SenkuuIshigamiandhisteamarealmostreadytosailtheseasandreachtheothersideoftheworld—wherethebizarregreenlightthatpetrifiedhumanityo...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1316/136268.jpg?s=c4b6b9da3690ee70ba58b27ba0b93195\",\"myanimelist_url\":\"https://myanimelist.net/anime/48549/Dr_Stone__New_World\",\"myanimelist_id\":48549},{\"title\":\"Dr.Stone:NewWorldPart2\",\"description\":\"ParttwoofDr.Stone:NewWorld.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1236/138696.jpg?s=08b9eaa01bb86177f2ce9a57169e3bc3\",\"myanimelist_url\":\"https://myanimelist.net/anime/55644/Dr_Stone__New_World_Part_2\",\"myanimelist_id\":55644},{\"title\":\"HachimitsutoCloverII\",\"description\":\"BackfromhisjourneyacrossJapan,YuutaTakemotoreminiscesabouthiscollegelifesofar.Hehasmaturedsignificantlysincehissecondyearandismotivatedtomoveforward.Feelingmoreconfident...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/6/11040.jpg?s=c8b269199a0efa9f933508cd6a730269\",\"myanimelist_url\":\"https://myanimelist.net/anime/1142/Hachimitsu_to_Clover_II\",\"myanimelist_id\":1142},{\"title\":\"MobPsycho100\",\"description\":\"Eighth-graderShigeo\\\"Mob\\\"Kageyamahastappedintohisinnerwellspringofpsychicprowessatayoungage.Butthepowerquicklyprovestobealiabilitywhenherealizesthepotentialdangerinhis...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/8/80356.jpg?s=1d7f8902c64d166b821e50ea68267c2a\",\"myanimelist_url\":\"https://myanimelist.net/anime/32182/Mob_Psycho_100\",\"myanimelist_id\":32182},{\"title\":\"KoukakuKidoutai:StandAloneComplex2ndGIG\",\"description\":\"Followingtheclosureofthe\\\"LaughingMan\\\"case,Section9isre-establishedbyJapan'snewlyelectedPrimeMinister,YoukoKayabuki,tocombatthepersistentthreatofcyber-terrorism.Agroupcalli...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1646/135390.jpg?s=7e946d0e4d47a6784da3336c4977b6d5\",\"myanimelist_url\":\"https://myanimelist.net/anime/801/Koukaku_Kidoutai__Stand_Alone_Complex_2nd_GIG\",\"myanimelist_id\":801},{\"title\":\"Evangelion:1.0YouAre(Not)Alone\",\"description\":\"Inapost-apocalypticworld,thelastremaininghumansettlementinJapanistheheavilyfortifiedcityofTokyo-3.Fourteen-year-oldShinjiIkariisbroughttotheheadquartersofNERV,anundergroun...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/7/74975.jpg?s=9e3cbb7beed9ed30841608d7d3817e74\",\"myanimelist_url\":\"https://myanimelist.net/anime/2759/Evangelion__10_You_Are_Not_Alone\",\"myanimelist_id\":2759},{\"title\":\"OneOuts\",\"description\":\"TouaTokuchiisaprodigywhenitcomestobothbaseballandgambling.Pitchingnothingbutmediocrefastballs,hehasmadeanameforhimselfbyattaining499consecutivevictoriesinthegameof\\\"On...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/7/21065.jpg?s=f072084cc11c8f6d110959a0b894d57c\",\"myanimelist_url\":\"https://myanimelist.net/anime/5040/One_Outs\",\"myanimelist_id\":5040},{\"title\":\"MobPsycho100II\",\"description\":\"Shigeo\\\"Mob\\\"Kageyamaisnowmaturingandunderstandinghisroleasasupernaturalpsychicthathasthepowertodrasticallyaffectthelivelihoodofothers.HeandhismentorReigenAratakacontinue...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1918/96303.jpg?s=b5b51cff7ba201e4f1acf37f4f44e224\",\"myanimelist_url\":\"https://myanimelist.net/anime/37510/Mob_Psycho_100_II\",\"myanimelist_id\":37510},{\"title\":\"MobPsycho100III\",\"description\":\"Afterfoilingaworld-threateningplot,Shigeo\\\"Mob\\\"Kageyamareturnstotacklethemoreexhaustingaspectsofhismundanelife—startingwithfillingouthisschool'snerve-rackingcareerform.Meanwh...readmore.\",\"picture_url\":\"https://cdn.myanimelist.net/r/50x70/images/anime/1228/125011.jpg?s=33cd9099a0ad56c9d80989ca0e1cc2ec\",\"myanimelist_url\":\"https://myanimelist.net/anime/50172/Mob_Psycho_100_III\",\"myanimelist_id\":50172}]";

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
