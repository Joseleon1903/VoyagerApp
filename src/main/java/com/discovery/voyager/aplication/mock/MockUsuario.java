package com.discovery.voyager.aplication.mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.discovery.voyager.aplication.model.entity.ErrorException;
import com.discovery.voyager.aplication.model.entity.ImagesData;
import com.discovery.voyager.aplication.model.entity.Profile;
import com.discovery.voyager.aplication.model.entity.Role;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.repository.ImagesDataRepository;
import com.discovery.voyager.aplication.repository.RoleRepository;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ErrorExceptionService errorExceptionService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initUserData();
    }

    public void initUserData(){

        ImagesData data =ImageMockDataBase();

        creationRoleCatalog();

        catalogErrorInit();

        System.out.println("inicializando data de usuario");
        User user = new User();
        user.setId(1);
        user.setUsername("ADMIN");
        String pass = bCryptPasswordEncoder.encode("ADMIN123");
        user.setPassword(pass);
        Profile profile = new Profile();
        profile.setId(1);
        profile.setFirstName("Jose");
        profile.setLastName("De Leon");
        profile.setEmail("joseleon@gmail.com");
        profile.setMobilePhone("809-445-7563");
        user.setProfile(profile);
        user.getProfile().setImage(data);

        Role rol =roleRepository.findById(new Long(1)).get();
      
        user.getRoles().add(rol);

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



    public void creationRoleCatalog(){
        System.out.println("entering method creationRoleCatalog");
        Role rol = new Role();
        rol.setId(new  Long(1));
        rol.setName("ROLE_ADMIN");
        System.out.println("role: "+ rol);

        Role rol1 = new Role();
        rol1.setId(new  Long(2));
        rol1.setName("ROLE_USER");
        System.out.println("role: "+ rol1);

        Role rol2 = new Role();
        rol2.setId(new  Long(3));
        rol2.setName("ROLE_GUEST");
        System.out.println("role: "+ rol2);

        roleRepository.save(rol);
        roleRepository.save(rol1);
        roleRepository.save(rol2);
        
    }

    public void catalogErrorInit(){
        List<ErrorException> listError = new ArrayList<>();
        ErrorException  errorE = null;
        //invalid Autentication Error
        errorE = new ErrorException();
        errorE.setCode(501);
        errorE.setDescription("Check that you have used the correct email and password combination for the account you are trying to access.");
        errorE.setStatus(true);
        listError.add(errorE);

        //invalid confirm password
        errorE = new ErrorException();
        errorE.setCode(502);
        errorE.setDescription("Password does not match the confirm password.");
        errorE.setStatus(true);
        listError.add(errorE);

        //invalid username
        errorE = new ErrorException();
        errorE.setCode(503);
        errorE.setDescription("Duplicate Username: the username already exists");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(800);
        errorE.setDescription("there are required data not provided");
        errorE.setStatus(true);
        listError.add(errorE);

        ///registration error
        System.out.println("List Error : "+listError );
        for (ErrorException var : listError) {
            errorExceptionService.save(var);
        }

    }

}
