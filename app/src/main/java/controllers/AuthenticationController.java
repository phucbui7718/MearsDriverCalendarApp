package controllers;

/**
 * Created by Phuc on 2/19/2016.
 */

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
@RequestMapping("/login")
public class AuthenticationController {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String  checkLoginInfo(@RequestParam("driver_num)") String driver_num, @RequestParam("password") String password ) {

        //Call to database to check whether or not driver_num or password are correct.
return null;


    }
}
