package com.discovery.voyager.aplication.mock;

import java.util.Date;

import com.discovery.voyager.aplication.model.entity.ImagesData;
import com.discovery.voyager.aplication.model.entity.Profile;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.ImagesDataRepository;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by jleon on 6/5/2018.
 */
@Component
public class MockUsuario implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ImagesDataRepository imagesDataRepository;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initUserData();
    }

    public void initUserData(){
        System.out.println("inicializando data de usuario");

        ImagesData data =ImageMockDataBase();

        User user = new User();
        user.setId(1);
        user.setUsername("ADMIN");
        user.setPassword("ADMIN123");
        Profile profile = new Profile();
        profile.setId(1);
        profile.setFirstName("Jose");
        profile.setLastName("De Leon");
        profile.setEmail("joseleon@gmail.com");
        profile.setMobilePhone("809-445-7563");
        user.setProfile(profile);
        user.getProfile().setImage(data);
        User userC = userService.createUser(user);
        System.out.println("Usuario: "+ userC );
        System.out.println("Terminando inicializando data de usuario");

    }

   
    public ImagesData ImageMockDataBase(){
        System.out.println("---------- ImageMockDataBase initialization --------------" );
        String fileName= "unknow-profile.png";
        ImagesData entity = new ImagesData();
        entity.setName(fileName);
        entity.setFileDownloadUri("http://localhost:8085/api/file/downloadFile/"+ fileName);
        entity.setFileType("png");
        entity.setCreationDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setFileViewUri("http://localhost:8085/api/file/view/image/"+fileName );
        entity =imagesDataRepository.save(entity);
        return entity;
    }

    public ImagesData ImageMockItemDataBase(){
        System.out.println("---------- ImageMockItemDataBase initialization --------------" );
        String fileName= "wall-box.jpg";
        ImagesData entity = new ImagesData();
        entity.setName(fileName);
        entity.setFileDownloadUri("http://localhost:8085/api/file/downloadFile/"+ fileName);
        entity.setFileType("png");
        entity.setCreationDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setFileViewUri("http://localhost:8085/api/file/view/image/"+fileName );
        entity =imagesDataRepository.save(entity);
        return entity;
    }

}
