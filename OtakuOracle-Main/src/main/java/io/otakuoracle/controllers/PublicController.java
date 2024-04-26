package io.otakuoracle.controllers;

import io.otakuoracle.models.APIResponse;
import io.otakuoracle.models.AnimeDetail;
import io.otakuoracle.models.AnimeObject;
import io.otakuoracle.models.UserInfo;
import io.otakuoracle.services.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserInfoService userService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return "TEST CALL";
    }

    @GetMapping("/call")
    public String call() {
        String url = "http://OtakuOracle-SearchService/test";
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/call1")
    public String call1() {
        String url = "http://OtakuOracle-AnimeDetailsService/test";
        return restTemplate.getForObject(url, String.class);
    }

    @PostMapping("/add")
    public APIResponse<Object> add(@RequestBody UserInfo userInfo) {

        if (userService.saveUser(userInfo) != null) {
            return APIResponse.builder().status(HttpStatus.CREATED).success(true).message(userInfo).build();
        } else {
            return APIResponse.builder().status(HttpStatus.EXPECTATION_FAILED).success(false).message("OOPS !! FAILED TO CREATE THE USER").build();
        }
    }

    @GetMapping("/getuser/{name}")
    public UserInfo getUserByName(@PathVariable String name) {
        return userService.getUserByName(name);
    }

    @PostMapping("/all")
    public List<Integer> all(@RequestBody UserInfo userInfo) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
        List<Integer> integers = new ArrayList<>();
        if (authentication.isAuthenticated()) {
            Random random = new Random();
            for (int i = 0; i < 100; i++) {
                integers.add(random.nextInt(10000));
            }
            return integers;
        }
        return null;
    }


    @PostMapping("/getanimelist/{animeName}")
    public AnimeDetail[] getanimelist(@RequestBody UserInfo userInfo, @PathVariable String animeName) throws Exception{
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userInfo.getUsername(),
                userInfo.getPassword()
        ));

        if (authentication.isAuthenticated()) {
            String serviceUrl = "http://OtakuOracle-SearchService/getlist/" + animeName;
            return restTemplate.getForObject(serviceUrl, AnimeDetail[].class);
        }
        return null;
    }


    @PostMapping("/getanimedetails/{animeId}")
    public AnimeObject getanimedetails(@RequestBody UserInfo userInfo, @PathVariable Integer animeId) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                userInfo.getUsername(),
                userInfo.getPassword()
        ));

        if (authentication.isAuthenticated()) {
            String serviceUrl = "http://OtakuOracle-AnimeDetailsService/getdetails/" + animeId;
            AnimeObject response = restTemplate.getForObject(serviceUrl, AnimeObject.class);
            return response;
        }
        return null;
    }

    @PostMapping("/animedetailstest/{animeId}")
    public AnimeObject animeObject(@RequestBody UserInfo userInfo) {
        String serviceUrl = "http://OtakuOracle-AnimeDetailsService/getdetails/2257";
        //String serviceUrl = "http://OtakuOracle-AnimeDetailsService/test";
        AnimeObject response = restTemplate.getForObject(serviceUrl, AnimeObject.class);
        System.out.println(response);
        return response;
    }

}
