package controllers;

/**
 * Created by Phuc on 2/19/2016.
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import services.AuthenticationService;

//
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

   @RequestMapping(value = "/login", method = RequestMethod.PUT)
    public ResponseEntity login(){
        //Return a response entity.


       //return 200 if authenticated
       // 401 or 403 if not authorized.
       return null;
   }

}
