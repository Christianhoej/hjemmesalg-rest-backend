package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.io.models.ads.request.AdDetailsModel;
import com.group40.hjemmesalgrestws.io.models.ads.response.AdRest;
import com.group40.hjemmesalgrestws.io.models.category.request.CategoryDetailsModel;
import com.group40.hjemmesalgrestws.io.models.shared.response.DeleteStatusModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.service.AdService;
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

    @CrossOrigin(origins = "*")
    @PostMapping()
    public AdRest createAd(@RequestBody AdDetailsModel adDetails){
        AdRest returnValue = new AdRest();

        UserDTO user = userService.getUserByEmail(adDetails.getEmail());

        AdDTO adDTO = new AdDTO();
        BeanUtils.copyProperties(adDetails,adDTO);
        adDTO.setUser(user);

        AdDTO createdAd = adService.createAd(adDTO);
        BeanUtils.copyProperties(createdAd,returnValue);
        //TODO RETURN LIST OF ADS
        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/{id}")
    public AdRest updateAd(@RequestBody AdDetailsModel adDetails, @PathVariable String id){
        AdRest returnValue = new AdRest();

        AdDTO adDTO = new AdDTO();
        BeanUtils.copyProperties(adDetails,adDTO);

        AdDTO updatedAd = adService.updateAd(adDTO, id);
        BeanUtils.copyProperties(updatedAd, returnValue);

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    public AdRest getAd(@PathVariable String id){
        AdRest returnValue = new AdRest();

        AdDTO adDTO = adService.getAdByadId(id);
        BeanUtils.copyProperties(adDTO,returnValue);

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
            returnValue.add(adRest);
        }
        return returnValue;

    }


    @GetMapping(path = "/categoryads")
    @CrossOrigin(origins = "*")
    public List<AdRest> getCategoryAds(@RequestBody CategoryDetailsModel categoryDetailsModel, @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value="limit", defaultValue = "25") int limit) {

        if(page>0) page-=1;
        List<AdRest> returnValue = new ArrayList<AdRest>();
        List<AdDTO> ads = adService.getCategoryAds(page, limit, categoryDetailsModel.getCategoryName());

        for(AdDTO cat: ads ){
            AdRest adRest = new AdRest();
            BeanUtils.copyProperties(cat,adRest);
            returnValue.add(adRest);
        }
        return returnValue;
                /*
        if(page>0) page-=1;
        List<CategoryRest> returnValue = new ArrayList<CategoryRest>();
        List<CategoryDTO> categories = categoryService.getCategories(page, limit);

        for(CategoryDTO cat: categories ){
            CategoryRest categoryRest = new CategoryRest();
            BeanUtils.copyProperties(cat,categoryRest);
            returnValue.add(categoryRest);
        }
        return returnValue;*/
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

                /*
        if(page>0) page-=1;
        List<CategoryRest> returnValue = new ArrayList<CategoryRest>();
        List<CategoryDTO> categories = categoryService.getCategories(page, limit);

        for(CategoryDTO cat: categories ){
            CategoryRest categoryRest = new CategoryRest();
            BeanUtils.copyProperties(cat,categoryRest);
            returnValue.add(categoryRest);
        }
        return returnValue;*/
    }
}
