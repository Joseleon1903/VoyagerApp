package com.discovery.voyager.aplication.controller.profile;

import java.io.IOException;
import java.security.Principal;
import com.discovery.voyager.aplication.constant.ConstantAplication;
import com.discovery.voyager.aplication.mapping.ProfileMapping;
import com.discovery.voyager.aplication.model.dto.EmailDTO;
import com.discovery.voyager.aplication.model.dto.form.ProfileFormData;
import com.discovery.voyager.aplication.model.entity.EmailTemplate;
import com.discovery.voyager.aplication.model.entity.ImagesData;
import com.discovery.voyager.aplication.model.entity.User;
import com.discovery.voyager.aplication.service.implementation.EmailServiceImpl;
import com.discovery.voyager.aplication.service.implementation.EmailTemplateService;
import com.discovery.voyager.aplication.service.implementation.ImageServiceImpl;
import com.discovery.voyager.aplication.service.implementation.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProfileController {

   @Autowired
   private UserServiceImpl userService;

   @Autowired
   private EmailServiceImpl emailServiceImpl;

   @Autowired
   private EmailTemplateService emailTemplateService;

   @Autowired
   private ImageServiceImpl imageServiceImpl;

   @Autowired
   private ProfileFormData profileFormData;

   @RequestMapping(value = "/profile", method = RequestMethod.GET)
   public String DisplayUserProfile(Model model, Principal principal) {
      System.out.println("entering DisplayUserProfile");
      User user = userService.findByUsernameAndStatusActive(principal.getName());
      System.out.println("user : " + user);
      profileFormData = ProfileMapping.convertFromEntity(user);
      model.addAttribute("profileBean", profileFormData);
      return "profile/UserProfile";
   }

   @RequestMapping(value = "/profile/update", method = RequestMethod.POST)
   public String updateUserForm(@ModelAttribute(value = "user") ProfileFormData user, Model model, Principal pincipal) {
      System.out.println("entering updateUserForm");
      User usuarioUpdate = userService.findByUsernameAndStatusActive(pincipal.getName());
      usuarioUpdate.getProfile().setFirstName(user.getFirstName());
      usuarioUpdate.getProfile().setLastName(user.getLastName());
      usuarioUpdate.getProfile().setEmail(user.getEmail());
      userService.updateUser(usuarioUpdate);

      // enviando email usuario actualizado con exito.
      EmailTemplate template = emailTemplateService.findByCode(ConstantAplication.UPDATE_USER_PROFILE);

      EmailDTO email = new EmailDTO();
      email.setHeader(template.getHeader());
      email.setDestinationEmail(usuarioUpdate.getProfile().getEmail());
      email.setContent(template.getContent());
      //emailServiceImpl.sendEmailTo(email);
      profileFormData = ProfileMapping.convertFromEntity(usuarioUpdate);
      model.addAttribute("profileBean", profileFormData);
      model.addAttribute("username", usuarioUpdate.getUsername());
      return "profile/UserProfile";
   }

   @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
   public String updateProfilePicture(@RequestParam("myFile") MultipartFile myFile, Model model) {
      System.out.println("entering updateProfilePicture");
      System.out.println("file name : " + myFile.getOriginalFilename());
      ImagesData image = null;
      try {
         image = imageServiceImpl.createImage(myFile);
      } catch (IOException ex) {
         System.out.println("error update image");
      }
      if (image != null) {
         User usuarioUpdate = userService.findByUsernameAndStatusActive(profileFormData.getUsername());
         usuarioUpdate.getProfile().setImage(image);
         usuarioUpdate = userService.updateUser(usuarioUpdate);
         profileFormData = ProfileMapping.convertFromEntity(usuarioUpdate);
      }

      model.addAttribute("profileBean", profileFormData);
      model.addAttribute("username", profileFormData.getUsername());
      // Redirect to a successful upload page
      return "profile/UserProfile";
   }

}
