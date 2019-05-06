package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.io.models.administrator.request.AdminDetailsModel;
import com.group40.hjemmesalgrestws.io.models.administrator.response.AdminRest;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin") //http://localhost:8080/Homely-ws/admin
public class AdministratorController {

    @CrossOrigin(origins = "*")
    @PostMapping()
    public AdminRest logIn(@RequestBody AdminDetailsModel adminLoginModel){
        //TODO lav RMI kald til jakobs server LAV DENNE TIL SIDST!
        AdminRest returnValue = new AdminRest();
        String test = "123Test";
        BeanUtils.copyProperties(adminLoginModel,returnValue);
        returnValue.setAdminID(test);


        return returnValue;

    }

}
