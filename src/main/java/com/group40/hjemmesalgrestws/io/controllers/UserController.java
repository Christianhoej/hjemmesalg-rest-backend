package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.shared.response.DeleteStatusModel;
import com.group40.hjemmesalgrestws.io.models.user.reponse.UserRest;
import com.group40.hjemmesalgrestws.io.models.user.request.UserDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users") //http://localhost:8080/Homely-ws/users
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping()
    public boolean createUser(@RequestBody UserDetailsModel userdetails){
        UserRest returnValue = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userdetails,userDTO);
        UserDTO createdUser = userService.createUser(userDTO);
        BeanUtils.copyProperties(createdUser, returnValue);

        if(returnValue.getEmail()==null){
            return false;
        }
        else
            return true;
        // return returnValue;
    }
    @CrossOrigin(origins = "*")
    @GetMapping(path ="/{id}")
    public UserRest getUser(@PathVariable String id){
        UserRest returnValue = new UserRest();

        UserDTO userDTO = userService.getUserByUserID(id);
        BeanUtils.copyProperties(userDTO,returnValue);

        return returnValue;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path ="/checkEmail/{email}")
    public boolean getUserByEmail(@PathVariable String email){
      //  UserRest returnValue = new UserRest();

        boolean userExist = userService.checkIfUserExist(email);
        if(!userExist){
            return false;
        } else {
            return true;
        }
       // BeanUtils.copyProperties(userDTO,returnValue);

        //return returnValue;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login")//http://localhost:8080/Homely-ws/users/login
    public UserRest logIn(@RequestBody UserLoginModel userLoginModel){
        UserRest returnValue = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userLoginModel,userDTO);

        UserDTO loggedInUser = userService.logInUser(userLoginModel);
        BeanUtils.copyProperties(loggedInUser, returnValue);

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @PutMapping(path = "{id}")
    public UserRest updateUserInfo(@PathVariable String id , @RequestBody UserDetailsModel userdetails){
        UserRest returnValue = new UserRest();

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userdetails,userDTO);
        System.out.println(userDTO.getFirstName());
        UserDTO updatedUser = userService.updateUser(userDTO, id);
        BeanUtils.copyProperties(updatedUser, returnValue);

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "{userID}")
    public DeleteStatusModel deleteUser(@PathVariable String userID){
        DeleteStatusModel returnValue = new DeleteStatusModel();
        returnValue.setOperationName("Delete User by userID: " + userID + ": ");

        boolean succesDelete = userService.deleteUserById(userID);
        if(succesDelete)
        returnValue.setOperationResult("SUCCES");
        else
            returnValue.setOperationResult("FAILED");

        return returnValue;

    }





}
