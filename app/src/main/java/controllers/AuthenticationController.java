package controllers;

/**
 * Created by Phuc on 2/19/2016.
 */


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//
@RestController
@RequestMapping("/login")
public class AuthenticationController {


   @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(){

       return "login";
   }

}
