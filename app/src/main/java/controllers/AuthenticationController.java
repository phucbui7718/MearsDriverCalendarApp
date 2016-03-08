package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity<String> login(@PathVariable String driverNum,  @PathVariable String password){

       if (authenticationService.isDriverAuthenticated(driverNum, password) == true){
           return new ResponseEntity<String>(driverNum  + "is authenticated!", HttpStatus.OK);
       }
       else
       return new ResponseEntity<String>(HttpStatus.FORBIDDEN);
   }

}
