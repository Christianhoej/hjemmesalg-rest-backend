package com.group40.hjemmesalgrestws.service.impl;

import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.entitiy.UserEntity;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.repository.UserRepository;
import com.group40.hjemmesalgrestws.service.UserService;
import com.group40.hjemmesalgrestws.shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService_Impl implements UserService {

    @Autowired
    Utils utils;
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity check = userRepository.findByEmail(userDTO.getEmail());
        System.out.println("helloooo" + check);
        if(check!=null){
            return null;
            // TODO check for bruger oprettet
        }
        else{
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO, userEntity);
            userEntity.setUserId(utils.generateUserId(15));
            UserEntity storedUserDetails = userRepository.save(userEntity);
            UserDTO returnValue = new UserDTO();
            BeanUtils.copyProperties(storedUserDetails, returnValue);

            return returnValue;
        }


    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String id) {

        UserDTO returnValue = new UserDTO();

        UserEntity userEntity = userRepository.findByEmail(userDTO.getEmail());

        if(!userEntity.getEmail().equals(userDTO.getEmail())) {
            //throw new /*NoSuchUser*/Exception;
        }
        userEntity.setAddress(userDTO.getAddress());
        userEntity.setBirthday(userDTO.getBirthday());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setGender(userDTO.getGender());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setPhonenumber(userDTO.getPhonenumber());
        userEntity.setZipCode(userDTO.getZipCode());

        UserEntity updatedUserDetails = userRepository.save(userEntity);
        BeanUtils.copyProperties(updatedUserDetails,returnValue);
        return returnValue;
    }

    @Override
    public UserDTO logInUser(UserLoginModel userLoginModel) {
        System.out.println(userLoginModel.getEmail() + " DETTE ER EMAIL");

        UserDTO returnValue = new UserDTO();

        UserEntity userEntity = userRepository.findByEmail(userLoginModel.getEmail());

        System.out.println(userEntity.getEmail()+ " hentet fra database");
        if(!(userEntity.getEmail().equals(userLoginModel.getEmail()) && userEntity.getPassword().equals(userLoginModel.getPassword()))) {
            //throw new /*NoSuchUser*/Exception;
            //TODO kast en exception.
            return null;
        }

        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDTO getUserByUserID(String id) {
        UserDTO returnValue = new UserDTO();
        UserEntity userEntity = userRepository.findByUserId(id);
        if(userEntity== null) // TODO throw new usernamenotfoundexception
        return null;

        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public boolean deleteUserById(String userID) {
        boolean returnValue;
        UserEntity userEntity = userRepository.findByUserId(userID);

        if(userEntity== null) // TODO throw new usernamenotfoundexception
            returnValue = false;
        else
            returnValue = true;
        userRepository.delete(userEntity);

        return returnValue;
    }
}
