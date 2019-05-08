package com.group40.hjemmesalgrestws.io.controllers;

import brugerautorisation.data.Bruger;
import brugerautorisation.transport.rmi.Brugeradmin;
import com.group40.hjemmesalgrestws.io.models.administrator.request.AdminDetailsModel;
import com.group40.hjemmesalgrestws.io.models.administrator.response.AdminRest;
import com.group40.hjemmesalgrestws.io.models.user.request.UserLoginModel;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

@RestController
@RequestMapping("admin") //http://localhost:8080/Homely-ws/admin
public class AdministratorController {

    @CrossOrigin(origins = "*")
    @PostMapping()
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

}
