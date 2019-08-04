package com.discovery.voyager.aplication.service.controller;

import com.discovery.voyager.aplication.model.dto.UserDTO;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private static Logger log = LogManager.getLogger(UserRestController.class);

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
        log.debug("entering method findById");
        log.debug("Id: "+ id);
        User userEnty = userService.findById(id);
        UserDTO user = new UserDTO(userEnty.getId(),userEnty.getUsername(), userEnty.getPassword());
        log.debug("UserDTO: "+ user);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @RequestMapping(value="/create", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> findById(@RequestBody User user){
        User userEnty = userService.createUser(user);
        UserDTO userDto = new UserDTO(userEnty.getId(),userEnty.getUsername(), userEnty.getPassword());
        return new ResponseEntity<UserDTO>(userDto, HttpStatus.CREATED);
    }

    @RequestMapping(value="/update", method = RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user){
        User userEnty = userService.updateUser(user);
        UserDTO userDto = new UserDTO(userEnty.getId(),userEnty.getUsername(), userEnty.getPassword());
        return new ResponseEntity<UserDTO>(userDto, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value="/findByUsername/{username}", method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<User> findByUsername(@PathVariable("username") String username){
        User userEnty = userService.findByUsername(username);
        return new ResponseEntity<User>(userEnty, HttpStatus.ACCEPTED);
    }

}
