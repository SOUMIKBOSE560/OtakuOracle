package io.otakuoracle.controllers;

import io.otakuoracle.models.AnimeObject;
import io.otakuoracle.services.AnimeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AnimeDetailsController {

    @Autowired
    private AnimeDetailsService animeDetailsService;

    @GetMapping("/getdetails/{animeId}")
    public AnimeObject getDetails(@PathVariable Integer animeId) throws IOException {
        return animeDetailsService.getDetails(animeId);
    }

    @GetMapping("/test")
    public String test() {
        return "HELLO FROM ANIME DETAILS SERVICE";
    }
}
