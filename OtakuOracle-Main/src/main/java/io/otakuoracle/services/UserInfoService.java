package io.otakuoracle.services;

import io.otakuoracle.models.UserInfo;
import io.otakuoracle.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    public UserInfo saveUser(UserInfo userInfo) {
        try {
            return userRepository.save(userInfo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public UserInfo getUserByName(String username) {
        Optional<UserInfo> userInfo = userRepository.getUserInfoByName(username);
        return userInfo.orElse(null);
    }
}
