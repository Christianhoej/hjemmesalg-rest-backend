package com.group40.hjemmesalgrestws.service.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.group40.hjemmesalgrestws.dtos.AdDTO;
import com.group40.hjemmesalgrestws.entitiy.AdEntity;
import com.group40.hjemmesalgrestws.repository.AdRepository;
import com.group40.hjemmesalgrestws.service.AdService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService_Impl implements AdService {
    @Autowired
    AdRepository adRepository;

    @Override
    public AdDTO createAd(AdDTO adDetails) {
        AdEntity adEntity = new AdEntity();
        BeanUtils.copyProperties(adDetails, adEntity);
        AdEntity storedAdDetails = adRepository.save(adEntity);
        AdDTO returnValue = new AdDTO();
        BeanUtils.copyProperties(storedAdDetails, returnValue);

        return returnValue;

    }

    @Override
    public List<AdDTO> getAllAds(int page, int limit) {
        List<AdDTO> returnValue = new ArrayList<>();

        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<AdEntity> categoriesPage = adRepository.findAll(pageableRequest);
        List<AdEntity> ads = categoriesPage.getContent();

        for(AdEntity ad: ads){
            AdDTO adDTO = new AdDTO();
            BeanUtils.copyProperties(ad, adDTO);
            returnValue.add(adDTO);
        }
        return returnValue;
    }

    @Override
    public List<AdDTO> getCategoryAds(int page, int limit, String categoryName) {
        List<AdDTO> returnValue = new ArrayList<>();
        System.out.println();
        Pageable pageableRequest = PageRequest.of(page, limit);

        Page<AdEntity> categoriesPage = adRepository.findAll(pageableRequest);
        List<AdEntity> ads = categoriesPage.getContent();

        for(AdEntity ad: ads){
            AdDTO adDTO = new AdDTO();
            BeanUtils.copyProperties(ad, adDTO);
            returnValue.add(adDTO);
        }
        return returnValue;
    }

    @Override
    public AdDTO getAdByadId(String id) {
        AdDTO returnValue = new AdDTO();
        AdEntity adEntity = adRepository.findByAdId(Integer.parseInt(id));
        if(adEntity== null) // TODO throw new usernamenotfoundexception
            return null;

        BeanUtils.copyProperties(adEntity,returnValue);
        return returnValue;
    }

    @Override
    public AdDTO updateAd(AdDTO adDTO, String id) {
        AdDTO returnValue = new AdDTO();

        AdEntity adEntity = adRepository.findByAdId(Integer.parseInt(id));

        if(!(adEntity.getAdId() == Integer.parseInt(id))) {
            //throw new /*NoSuchUser*/Exception;
            return null;
        }
        adEntity.setCategory(adDTO.getCategory());
        adEntity.setDate(adDTO.getDate());
        adEntity.setDescription(adDTO.getDescription());
        adEntity.setHeader(adDTO.getHeader());
        adEntity.setImageURL(adDTO.getImageURL());
        adEntity.setPrice(adDTO.getPrice());

        AdEntity updatedUserDetails = adRepository.save(adEntity);
        BeanUtils.copyProperties(updatedUserDetails,returnValue);
        return returnValue;
    }

    @Override
    public boolean deleteByAdId(String id) {
        boolean returnValue;
        AdEntity adEntity = adRepository.findByAdId(Integer.parseInt(id));

        if(adEntity== null) // TODO throw new adNotFoundException
            returnValue = false;
        else
            returnValue = true;
        adRepository.delete(adEntity);

        return returnValue;    }


}
