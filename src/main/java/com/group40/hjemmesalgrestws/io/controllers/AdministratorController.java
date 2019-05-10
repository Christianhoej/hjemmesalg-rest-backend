package com.group40.hjemmesalgrestws.io.controllers;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import com.group40.hjemmesalgrestws.dtos.AdminStatsDTO;
import com.group40.hjemmesalgrestws.io.models.administrator.request.AdminDetailsModel;
import com.group40.hjemmesalgrestws.io.models.administrator.response.AdminStatisticsRest;
import com.group40.hjemmesalgrestws.service.AdminService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RestController
@RequestMapping("admin") //http://localhost:8080/Homely-ws/admin
public class AdministratorController {

    @Autowired
    AdminService adminService;

    @CrossOrigin(origins = "*")
    @PostMapping(consumes = {MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Log ind som administrator",
            notes = "Returnerer true hvis brugerautorisation lykkes gennem Jakobs JB-auth. Returnerer false hvis ikke det lykkedes")
    public boolean logIn(@RequestBody AdminDetailsModel adminLoginModel){
        boolean returnValue = false;
        Brugeradmin ba = null;
        try {
            ba = (Brugeradmin) Naming.lookup("rmi://javabog.dk/brugeradmin");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            Bruger b = ba.hentBruger(adminLoginModel.getEmail(), adminLoginModel.getPassword());
            if(b!=null)
                returnValue = true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return returnValue;

    }
    @CrossOrigin(origins = "*")
    @GetMapping()
    @ApiOperation(value = "Hent overblik/data/statistik på databasen",
            notes = "Returnerer et overblik over: antal brugere, antal salgsopslag, antal salgsopslag pr. kategori og antal kategorier.")
    public AdminStatisticsRest getStatistics(){
        AdminStatisticsRest returnValue;
        AdminStatsDTO adminDTO = adminService.getStatistics();
        ModelMapper modelMapper = new ModelMapper();
        returnValue = modelMapper.map(adminDTO, AdminStatisticsRest.class);

        return returnValue;

    }

}
