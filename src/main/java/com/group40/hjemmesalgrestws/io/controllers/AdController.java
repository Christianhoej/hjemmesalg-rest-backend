package com.group40.hjemmesalgrestws.io.controllers;

import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.dtos.UserDTO;
import com.group40.hjemmesalgrestws.exceptions.AdServiceException;
import com.group40.hjemmesalgrestws.exceptions.ErrorMessages;
import com.group40.hjemmesalgrestws.io.models.ads.request.AdDetailsModel;
import com.group40.hjemmesalgrestws.io.models.ads.response.AdRest;
import com.group40.hjemmesalgrestws.io.models.category.request.CategoryDetailsModel;
import com.group40.hjemmesalgrestws.io.models.shared.response.DeleteStatusModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserDetailsModel;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import com.group40.hjemmesalgrestws.service.AdService;
import com.group40.hjemmesalgrestws.service.CategoryService;
import com.group40.hjemmesalgrestws.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "Laver nyt salgsopslag",
    notes = "Returnerer informationer på netop oprettede salgsopslag. Fejler hvis der ikke findes en bruger til det tilhørende opslag. (email)")
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

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @PutMapping(path = "/{id}")
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert bruger input. Der refereres til en instans som ikke findes")})
    @ApiOperation(value = "Opdaterer eksisterende salgsopslag",
            notes = "Returnerer informationer på netop opdaterede salgsopslag. Fejler hvis der salgsopslaget (med id) ikke findes")
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

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @GetMapping(path = "/{id}")
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert bruger input. Der refereres til en instans som ikke findes")})
    @ApiOperation(value = "Hent et specifikt salgsopslag",
            notes = "Returnerer informationer på bestemt salgsopslag. Fejler hvis ikke salgsopsalget findes")
    public AdRest getAd(@PathVariable String id){
        AdRest returnValue = new AdRest();

        AdDTO adDTO = adService.getAdByadId(id);
        BeanUtils.copyProperties(adDTO,returnValue);
        returnValue.setCategory(adDTO.getCategory().getCategoryName());

        return returnValue;
    }
    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/{id}")
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert bruger input. Der refereres til en instans som ikke findes")})
    @ApiOperation(value = "Sletter eksisterende salgsopslag",
            notes = "Returnerer informationer på status for det slettede salgsopslag. Fejler hvis ikke der findes et salgsopslag med angivne id.")
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
    @ApiOperation(value = "Henter en page (bestemt mængde) med salgsopslag",
            notes = "Returnerer en liste med salgsopslag (ufiltrerede resultater). Returnerer en tom list hvis ikke der findes nogle opslag.")
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


    @GetMapping(path = "/categoryads/{id}")
    @CrossOrigin(origins = "*")
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert bruger input. Der refereres til en kategori som ikke findes")})
    @ApiOperation(value = "Hent alle kategorier i en bestemt kategori",
            notes = "Returnerer en liste med alle salgsopslag i en bestemt kategori. Fejler hvis der ikke findes en kategori, som den der angives i requestbody (categoryDetailsModel).")

    public List<AdRest> getCategoryAds(@PathVariable String id,/*@RequestBody CategoryDetailsModel categoryDetailsModel,*/ @RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value="limit", defaultValue = "25") int limit) {


        //if(page>0) page-=1; //TODO skal måske revurderes
        List<AdRest> returnValue = new ArrayList<AdRest>();

        CategoryDTO categoryDTO = categoryService.getCatgoryByName(id);
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
    @ApiResponses(value = { @ApiResponse(code = 666, message = "Forkert bruger input. Der refereres til en bruger som ikke findes")})
    @ApiOperation(value = "Hent alle salgsopslag fra en bestemt bruger",
            notes = "Returnerer en liste med den bestemte brugers salgsopslag. Fejler hvis brugeren der tilkendegives med \"userId\" ikke findes.")
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
