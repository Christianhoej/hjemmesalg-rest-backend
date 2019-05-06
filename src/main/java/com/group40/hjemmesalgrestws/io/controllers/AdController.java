package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.io.models.ads.request.AdDetailsModel;
import com.group40.hjemmesalgrestws.io.models.ads.response.AdRest;
import com.group40.hjemmesalgrestws.io.models.category.request.CategoryDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping("ads")
public class AdController {

    @Autowired
    AdService adService;

    @CrossOrigin(origins = "*")
    @PostMapping()
    public AdRest createAd(@RequestBody AdDetailsModel adDetails){
        AdRest returnValue = new AdRest();
        System.out.println(adDetails.getCategory());

        AdDTO adDTO = new AdDTO();
        BeanUtils.copyProperties(adDetails,adDTO);
        System.out.println(adDTO.getCategory());

        AdDTO createdAd = adService.createAd(adDTO);
        BeanUtils.copyProperties(createdAd,returnValue);

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/{id}")
    public AdRest updateAd(@RequestBody AdDetailsModel adDetails, @PathVariable String id){
        AdRest returnValue = new AdRest();
        BeanUtils.copyProperties(adDetails,returnValue);
        returnValue.setHeader(returnValue.getHeader() + " Dette har ændret sig");
        returnValue.setAdId(Integer.parseInt(id));
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
    public AdRest deleteAd(@RequestBody UserLoginModel userCreds, @PathVariable String id){
        userCreds.getEmail();
        userCreds.getPassword();
        AdRest returnValue = new AdRest();
        returnValue.setHeader(returnValue.getHeader() + " Dette er en specifik ad der er blevet slettet");
        returnValue.setAdId(Integer.parseInt(id));
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
}
