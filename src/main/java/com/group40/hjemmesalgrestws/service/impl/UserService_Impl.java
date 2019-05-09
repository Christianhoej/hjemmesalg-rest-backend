package com.group40.hjemmesalgrestws.service.impl;

import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.entitiy.UserEntity;
import com.group40.hjemmesalgrestws.exceptions.ErrorFixes;
import com.group40.hjemmesalgrestws.exceptions.ErrorMessages;
import com.group40.hjemmesalgrestws.exceptions.UserServiceException;
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

        if(check!=null)throw new UserServiceException(ErrorMessages.USER_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.USERNAME_ALREADY_EXIST_TIP.getErrorFix());

            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO, userEntity);
            userEntity.setUserId(utils.generateUserId(15));
            UserEntity storedUserDetails = userRepository.save(userEntity);
            UserDTO returnValue = new UserDTO();
            BeanUtils.copyProperties(storedUserDetails, returnValue);

            return returnValue;



    }

    @Override
    public UserDTO updateUser(UserDTO userDTO, String id) {

        UserDTO returnValue = new UserDTO();

        UserEntity userEntity = userRepository.findByUserId(id);

        if(!(userEntity.getEmail().equals(userDTO.getEmail()))) throw new UserServiceException(ErrorMessages.USER_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.CHANGE_EMAIL_OR_PUBLIC_USERID.getErrorFix() );

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

        UserDTO returnValue = new UserDTO();

        UserEntity userEntity = userRepository.findByEmail(userLoginModel.getEmail());

        if(!(userEntity.getEmail().equals(userLoginModel.getEmail()) && userEntity.getPassword().equals(userLoginModel.getPassword()))) {
            throw new UserServiceException(ErrorMessages.WRONG_LOGIN_CREDENTIALS.getErrorMessage(), ErrorFixes.WRONG_LOGIN_CREDENTIALS.getErrorFix());
        }

        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public UserDTO getUserByUserID(String id) {
        UserDTO returnValue = new UserDTO();
        UserEntity userEntity = userRepository.findByUserId(id);
        if(userEntity== null) throw new UserServiceException(ErrorMessages.USER_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.TRY_ANOTHER_USERID.getErrorFix());

        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public boolean deleteUserById(String userID) {
        boolean returnValue;
        UserEntity userEntity = userRepository.findByUserId(userID);

        if(userEntity== null) throw new UserServiceException(ErrorMessages.USER_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.TRY_ANOTHER_USERID.getErrorFix());

            returnValue = true;
        userRepository.delete(userEntity);

        return returnValue;
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        UserDTO returnValue = new UserDTO();
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity== null) throw new UserServiceException(ErrorMessages.USER_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.TRY_ANOTHER_EMAIL.getErrorFix());

        BeanUtils.copyProperties(userEntity,returnValue);
        return returnValue;
    }

    @Override
    public int getUsersCount() {
        return userRepository.countAllBy();
    }
}
