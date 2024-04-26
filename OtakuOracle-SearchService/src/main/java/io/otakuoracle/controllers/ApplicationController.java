package io.otakuoracle.controllers;

import io.otakuoracle.models.APIResponse;
import io.otakuoracle.models.AnimeDetail;
import io.otakuoracle.services.AnimeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApplicationController {

    @Autowired
    private AnimeSearchService searchService;

//    @GetMapping("/search/{name}")
//    public APIResponse getAnime(@PathVariable("name") String name) {
//        return searchService.search(name);
//    }

    @GetMapping("/getlist/{name}")
    public AnimeDetail[] getlist(@PathVariable String name){

        List<AnimeDetail> list = searchService.search(name);
        AnimeDetail[] animeDetailsArr = list.toArray(AnimeDetail[]::new);
        System.out.println(Arrays.toString(animeDetailsArr));
        return animeDetailsArr;


    }

    @GetMapping("/test")
    public String test(){
        return "HELLO FROM SEARCH SERVICE";
    }
}
