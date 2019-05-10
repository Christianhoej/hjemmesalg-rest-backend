package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.shared.response.DeleteStatusModel;
import com.group40.hjemmesalgrestws.io.models.user.reponse.UserRest;
import com.group40.hjemmesalgrestws.io.models.user.request.UserDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users") //http://localhost:8080/Homely-ws/users
public class UserController {

    @Autowired
    UserService userService;

    @CrossOrigin(origins = "*")
    @PostMapping()
    @ApiOperation(value = "Opretter ny bruger",
            notes = "Returnerer true hvis det lykkedes at oprette brugeren og false hvis ikke det lykkes.\nFejler hvis der allerede findes en bruger tilknyttet den email der angives")
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
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert path variable \"{id}\". Der refereres til en kategori med et id som ikke findes")})
    @ApiOperation(value = "Hent en bestemt brugers oplysninger",
            notes = "Returnerer informationer på brugeren med det søgte \"{id}\". Fejler hvis der ikke findes en bruger til det søgte \"{id}\".")
    public UserRest getUser(@PathVariable String id){
        UserRest returnValue = new UserRest();

        UserDTO userDTO = userService.getUserByUserID(id);
        BeanUtils.copyProperties(userDTO,returnValue);

        return returnValue;
    }
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/login")//http://localhost:8080/Homely-ws/users/login
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert bruger input. Forkert email eller password")})
    @ApiOperation(value = "Login med en eksisterende bruger.",
            notes = "Returnerer informationer på brugeren der er logget ind på. Fejler hvis der ikke findes en bruger med den i requestbodyen angivne email.\n" +
                    "Fejler også hvis ikke email og password passer på oplysningerne")
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
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert path-variable \"{id}\". Der refereres til en kategori med et id som ikke findes")})
    @ApiOperation(value = "Opdaterer eksisterende brugers oplysninger",
            notes = "Returnerer informationer på netop opdaterede bruger. Fejler hvis der ikke findes en bruger til det tilhørende pathvariable\"{id}\".")
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
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert path-variable \"{userId}\". Der refereres til en kategori med et id som ikke findes")})
    @ApiOperation(value = "Sletter eksisterende bruger",
            notes = "Returnerer informationer på netop udførte operation. Fejler hvis der ikke findes en bruger med det angivne pathvariable \"{userId}\".\n" +
                    "Når en bruger slettes slettes salgsopslag fra brugeren også.")
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
