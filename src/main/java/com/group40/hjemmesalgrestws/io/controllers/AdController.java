package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.ads.request.AdDetailsModel;
import com.group40.hjemmesalgrestws.io.models.ads.response.AdRest;
import com.group40.hjemmesalgrestws.io.models.category.request.CategoryDetailsModel;
import com.group40.hjemmesalgrestws.io.models.shared.response.DeleteStatusModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.service.AdService;
import com.group40.hjemmesalgrestws.service.CategoryService;
import com.group40.hjemmesalgrestws.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("ads")
public class AdController {

    @Autowired
    AdService adService;

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @CrossOrigin(origins = "*")
    @PostMapping()
    public AdRest createAd(@RequestBody AdDetailsModel adDetails){

        UserDTO user = userService.getUserByEmail(adDetails.getEmail());
        CategoryDTO category = categoryService.getCatgoryByName(adDetails.getCategory());

        AdDTO adDTO = new AdDTO();
        BeanUtils.copyProperties(adDetails,adDTO);
        adDTO.setCategory(category);
        adDTO.setUser(user);

        AdDTO createdAd = adService.createAd(adDTO);
        ModelMapper modelMapper = new ModelMapper();
        AdRest returnValue = modelMapper.map(createdAd, AdRest.class);

        //BeanUtils.copyProperties(createdAd,returnValue);

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/{id}")
    public AdRest updateAd(@RequestBody AdDetailsModel adDetails, @PathVariable String id){
        AdRest returnValue;
        ModelMapper modelMapper = new ModelMapper();
        CategoryDTO category = categoryService.getCatgoryByName(adDetails.getCategory());
        UserDTO user = userService.getUserByEmail(adDetails.getEmail());
        AdDTO adDTO = new AdDTO();
        BeanUtils.copyProperties(adDetails,adDTO);
        adDTO.setCategory(category);
        adDTO.setUser(user);

        AdDTO updatedAd = adService.updateAd(adDTO, id);

        returnValue = modelMapper.map(updatedAd, AdRest.class);
        returnValue.setCategory(updatedAd.getCategory().getCategoryName());
        //BeanUtils.copyProperties(updatedAd, returnValue);

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public AdRest getAd(@PathVariable String id){
        AdRest returnValue = new AdRest();

        AdDTO adDTO = adService.getAdByadId(id);
        BeanUtils.copyProperties(adDTO,returnValue);
        returnValue.setCategory(adDTO.getCategory().getCategoryName());

        return returnValue;
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}")
    public DeleteStatusModel deleteAd(@PathVariable String id){
        DeleteStatusModel returnValue = new DeleteStatusModel();
        returnValue.setOperationName("Delete Ad by adId: " + id + ": ");

        boolean succesDelete = adService.deleteByAdId(id);
        if(succesDelete)
            returnValue.setOperationResult("SUCCES");
        else
            returnValue.setOperationResult("FAILED");

        return returnValue;
    }
    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<AdRest> getAllAds(@RequestParam(value="page",defaultValue="0") int page, @RequestParam(value="limit",defaultValue="25") int limit) {
        if(page>0) page-=1;
        List<AdRest> returnValue = new ArrayList<AdRest>();
        List<AdDTO> ads = adService.getAllAds(page, limit);

        for(AdDTO cat: ads ){
            AdRest adRest = new AdRest();
            BeanUtils.copyProperties(cat,adRest);
            adRest.setCategory(cat.getCategory().getCategoryName());
            returnValue.add(adRest);
        }
        return returnValue;

    }


    @GetMapping(path = "/categoryads")
    @CrossOrigin(origins = "*")
    public List<AdRest> getCategoryAds(@RequestBody CategoryDetailsModel categoryDetailsModel, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value="limit", defaultValue = "25") int limit) {

        //if(page>0) page-=1; //TODO skal måske revurderes
        List<AdRest> returnValue = new ArrayList<AdRest>();

        CategoryDTO categoryDTO = categoryService.getCatgoryByName(categoryDetailsModel.getCategoryName());
        System.out.println(categoryDTO.getCategoryId() + " er categoryID");
        List<AdDTO> ads = adService.getCategoryAds(page, limit, categoryDTO);

        ModelMapper modelMapper = new ModelMapper();
        for(AdDTO ad: ads ){
            System.out.println(ad.getHeader() + " " + ad.getUser().getEmail() + " " + ad.getCategory().getCategoryName());

            AdRest adRest;
            adRest = modelMapper.map(ad, AdRest.class);
            returnValue.add(adRest);
        }

        return returnValue;

    }

    @GetMapping(path = "/{userId}/userads")
    @CrossOrigin(origins = "*")
    public List<AdRest> getUsersAds(@PathVariable String userId ) {
        UserDTO user = userService.getUserByUserID(userId);
        List<AdRest> returnValue = new ArrayList<AdRest>();
        List<AdDTO> adDTOs = adService.getUserAds(user.getEmail());

        if(adDTOs!= null && !adDTOs.isEmpty()) {
            Type listType = new TypeToken<List<AdRest>>() {
            }.getType();
            returnValue = new ModelMapper().map(adDTOs, listType);
        }
        return returnValue;

    }
}
