package com.group40.hjemmesalgrestws.service.impl;

import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.dtos.CategoryDTO;
import com.group40.hjemmesalgrestws.entitiy.AdEntity;
import com.group40.hjemmesalgrestws.entitiy.CategoryEntity;
import com.group40.hjemmesalgrestws.entitiy.UserEntity;
import com.group40.hjemmesalgrestws.exceptions.AdServiceException;
import com.group40.hjemmesalgrestws.exceptions.ErrorFixes;
import com.group40.hjemmesalgrestws.exceptions.ErrorMessages;
import com.group40.hjemmesalgrestws.exceptions.UserServiceException;
import com.group40.hjemmesalgrestws.io.models.administrator.response.CategoryAdStatsRest;
import com.group40.hjemmesalgrestws.repository.AdRepository;
import com.group40.hjemmesalgrestws.repository.CategoryRepository;
import com.group40.hjemmesalgrestws.repository.UserRepository;
import com.group40.hjemmesalgrestws.service.AdService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class AdService_Impl implements AdService {
    @Autowired
    AdRepository adRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public AdDTO createAd(AdDTO adDetails) {
        ModelMapper modelMapper = new ModelMapper();
        AdEntity adEntity = modelMapper.map(adDetails, AdEntity.class);
        AdEntity storedAdDetails = adRepository.save(adEntity);
        AdDTO returnValue = modelMapper.map(storedAdDetails, AdDTO.class);

        return returnValue;

    }

    @Override
    public List<AdDTO> getAllAds(int page, int limit) {
        List<AdDTO> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<AdEntity> categoriesPage = adRepository.findAll(pageableRequest);

        List<AdEntity> ads = categoriesPage.getContent();

        for(AdEntity ad: ads){
            ModelMapper modelMapper = new ModelMapper();
            AdDTO adDTO =  modelMapper.map(ad, AdDTO.class);


            returnValue.add(adDTO);
        }
        return returnValue;

    }

    @Override
    public List<AdDTO> getCategoryAds(int page, int limit, CategoryDTO category) {
        List<AdDTO> returnValue = new ArrayList<>();
        CategoryEntity categoryEntity = categoryRepository.findByCategoryId(category.getCategoryId());
        List<AdEntity> allAdsInCategory = categoryEntity.getAds();
        /*List<AdEntity> ads = new ArrayList<>();
        for(int i = 0; i<limit; i++){ //TODO implementer med limit og page hvis det ønskes
            int specificAdNumber = page
            ads.add(allAdsInCategory.get());
        }*/


        ModelMapper modelMapper = new ModelMapper();
        for(AdEntity ad: allAdsInCategory){
            AdDTO adDTO = modelMapper.map(ad,AdDTO.class);

            returnValue.add(adDTO);
        }
        return returnValue;

    }

    @Override
    public AdDTO getAdByadId(String id) {
        //AdDTO returnValue = new AdDTO();
        AdEntity adEntity = adRepository.findByAdId(Integer.parseInt(id));
        if(adEntity== null) throw new AdServiceException(ErrorMessages.AD_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.AD_BY_ID_DOES_NOT_EXIST.getErrorFix());

        ModelMapper modelMapper = new ModelMapper();
        AdDTO returnValue = modelMapper.map(adEntity, AdDTO.class);
        BeanUtils.copyProperties(adEntity,returnValue);
        return returnValue;
    }

    @Override
    public AdDTO updateAd(AdDTO adDTO, String id) {

        AdEntity adEntity = adRepository.findByAdId(Integer.parseInt(id));

        if(adEntity==null)throw new AdServiceException(ErrorMessages.AD_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.AD_BY_ID_DOES_NOT_EXIST.getErrorFix());


        ModelMapper modelMapper = new ModelMapper();
        CategoryEntity categoryEntity = modelMapper.map(adDTO.getCategory(), CategoryEntity.class);
        adEntity.setCategory(categoryEntity);
        adEntity.setDate(adDTO.getDate());
        adEntity.setDescription(adDTO.getDescription());
        adEntity.setHeader(adDTO.getHeader());
        adEntity.setImageURL(adDTO.getImageURL());
        adEntity.setPrice(adDTO.getPrice());

        AdEntity updatedAdDetails = adRepository.save(adEntity);

        AdDTO returnValue = modelMapper.map(updatedAdDetails, AdDTO.class);
        return returnValue;
    }

    @Override
    public boolean deleteByAdId(String id) {
        AdEntity adEntity = adRepository.findByAdId(Integer.parseInt(id));

        if(adEntity== null) throw new AdServiceException(ErrorMessages.USER_DOES_NOT_EXIST.getErrorMessage(), ErrorFixes.AD_BY_ID_DOES_NOT_EXIST.getErrorFix());

        adRepository.delete(adEntity);

        return true;    }

    @Override
    public List<AdDTO> getUserAds(String email) {
    List<AdDTO> returnValue = new ArrayList<>();
    UserEntity user = userRepository.findByEmail(email);
    //TEST
    List<AdEntity> ads = user.getAds();

        for(AdEntity adEntity : ads){
            System.out.println(adEntity.getAdId() + " " + adEntity.getCategory());
            ModelMapper modelMapper = new ModelMapper();
            AdDTO ad = modelMapper.map(adEntity, AdDTO.class);
            returnValue.add(ad);
        }

        return returnValue;
    }

    @Override
    public int getAdsCount() {
        return adRepository.countAllBy();
    }

    @Override
    public List<CategoryAdStatsRest> getCountOfAdsByCategory() {
        List<CategoryAdStatsRest> returnValue = new ArrayList<CategoryAdStatsRest>();
        List<CategoryEntity> categories = (List<CategoryEntity>) categoryRepository.findAll();

                for(CategoryEntity cat : categories){
                    CategoryAdStatsRest c = new CategoryAdStatsRest();
                    c.setCategoryName(cat.getCategoryName());
                    c.setNumbers(cat.getAds().size());
                    returnValue.add(c);
        }
        return returnValue;
    }


}
