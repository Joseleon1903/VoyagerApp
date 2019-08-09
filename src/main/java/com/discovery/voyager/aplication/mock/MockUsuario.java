package com.discovery.voyager.aplication.mock;

import com.discovery.voyager.aplication.constant.ConstantApplication;
import com.discovery.voyager.aplication.model.entity.*;
import com.discovery.voyager.aplication.repository.ImagesDataRepository;
import com.discovery.voyager.aplication.repository.RoleRepository;
import com.discovery.voyager.aplication.service.implementation.EmailTemplateService;
import com.discovery.voyager.aplication.service.implementation.ErrorExceptionService;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private EmailTemplateService emailTemplateService;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){
        initUserData();
    }

    public void initUserData(){

        ImagesData data =ImageMockDataBase();

        CatalogEmailTemplateInit();

        creationRoleCatalog();

        catalogErrorInit();

        System.out.println("inicializando data de usuario");
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        String pass = bCryptPasswordEncoder.encode("admin123");
        user.setPassword(pass);
        user.setStatus(ConstantApplication.STATUS_A);
        Profile profile = new Profile();
        profile.setId(1);
        profile.setFirstName("Jose");
        profile.setLastName("De Leon");
        profile.setEmail("joseleon@gmail.com");
        user.setProfile(profile);
        user.getProfile().setImage(data);
        Role rol =roleRepository.findById(new Long(4)).get();
        user.setRole(rol);
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

    public ImagesData ImageMockItemDataBase() {
        System.out.println("---------- ImageMockItemDataBase initialization --------------");
        String fileName = "wall-box.jpg";
        ImagesData entity = new ImagesData();
        entity.setName(fileName);
        entity.setFileDownloadUri("http://localhost:8085/api/file/downloadFile/" + fileName);
        entity.setFileType("png");
        entity.setCreationDate(new Date());
        entity.setUpdateDate(new Date());
        entity.setFileViewUri("http://localhost:8085/api/file/view/image/" + fileName);
        entity = imagesDataRepository.save(entity);
        return entity;
    }

    public void creationRoleCatalog(){
        System.out.println("entering method creationRoleCatalog");

        List<Role> listR = new ArrayList<>();

        Role rol = new Role();
        rol.setId(new  Long(1));
        rol.setName("ROLE_ADMIN");
        System.out.println("role: "+ rol);
        listR.add(rol);

        rol = new Role();
        rol.setId(new  Long(2));
        rol.setName("ROLE_USER");
        System.out.println("role: "+ rol);
        listR.add(rol);

        rol = new Role();
        rol.setId(new  Long(3));
        rol.setName("ROLE_GUEST");
        System.out.println("role: "+ rol);
        listR.add(rol);

        rol = new Role();
        rol.setId(new  Long(4));
        rol.setName("ROLE_MANAGEMENT");
        listR.add(rol);
        System.out.println("role: "+ rol);
        for (Role r: listR) {
            roleRepository.save(r);
        }
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
        errorE.setDescription("There are required data not provided");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(801);
        errorE.setDescription("The password format does not represent a format accepted by the system");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(802);
        errorE.setDescription("The email format does not represent a format accepted by the system");
        errorE.setStatus(true);
        listError.add(errorE);

        errorE = new ErrorException();
        errorE.setCode(504);
        errorE.setDescription("Otp code does not match");
        errorE.setStatus(true);
        listError.add(errorE);

        ///registration error
        System.out.println("List Error : "+listError );
        for (ErrorException var : listError) {
            errorExceptionService.save(var);
        }
    }

    public void CatalogEmailTemplateInit(){
        System.out.println("---------- Inizializando data Email --------------" );
        EmailTemplate  eTemplate = new EmailTemplate();
        eTemplate.setCode("Email_new_registration");
        eTemplate.setHeader("Welcome thanks for your registration");
        eTemplate.setContent("The welcome email one of the worlds leading aplication, "
                +"is quite discreet. This type of email isn’t usually recommended, as there is very little hook to the message.");
        emailTemplateService.save(eTemplate);

        eTemplate = new EmailTemplate();
        eTemplate.setCode("Update_user_profile");
        eTemplate.setHeader("Welcome thanks for update your profile");
        eTemplate.setContent("Hello, we thank you for updating your profile data.");
        emailTemplateService.save(eTemplate);
    }
}
