package com.discovery.voyager.aplication.service.implementation;

import com.discovery.voyager.aplication.constant.ConstantApplication;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.UserRepository;
import com.discovery.voyager.aplication.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAllUsers() {
        return  userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
       if (userRepository.existsById(user.getId())){
          return userRepository.save(user);
       }
       return null;
    }

    public User findByUsernameAndStatusActive(String username) {
        return userRepository.findByUsernameAndStatus(username, ConstantApplication.STATUS_A);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<String> findListUsername(String texto) {
        List<String> outList = new ArrayList<>();
        texto = "%"+ texto+"%";
        userRepository.findListUsernameByParam(texto).forEach( element ->{
            outList.add(element.getUsername());
        });
        return outList;
    }

}
