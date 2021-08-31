package com.hurata.api;

import com.hurata.entity.User;
import com.hurata.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Calendar;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        User user = new User();
        User secondUser = new User();

        //first user
        user.setName("İbrahim");
        user.setSurname("Hürata");
        user.setAddress("Ankara");
        user.setBirthday(Calendar.getInstance().getTime());
        user.setId("4280");
        userRepository.save(user);

        //second user
        secondUser.setName("Talha");
        secondUser.setSurname("Hürata");
        secondUser.setAddress("Ankara");
        secondUser.setBirthday(Calendar.getInstance().getTime());
        secondUser.setId("4281");
        userRepository.save(secondUser);
    }

    @GetMapping("/{search}")
    public ResponseEntity<List<User>>getUser(@PathVariable String search){
        List<User> users = userRepository.getByCustomQuery(search);
        return ResponseEntity.ok(users);
    }
}
