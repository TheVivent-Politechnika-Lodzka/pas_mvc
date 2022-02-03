package sample.web.jsf.beans;

import sample.web.jsf.model.User;
import sample.web.jsf.restclient.UserRestClient;
import sample.web.jsf.utils.ContextUtils;
import sample.web.jsf.utils.JwtUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
@RequestScoped
public class UtilsBean {

    @Inject
    private UserSettingsBean userSettingsBean;

    public String invalidateSession() {

        ContextUtils.invalidateSession();
        return "mainSite";
    }

    public String userSettings(){
        UUID uuid = UUID.fromString(JwtUtils.getUserId());
        User user = UserRestClient.getById(uuid);
        userSettingsBean.setUser(user);
        return "userSettings";
    }

    public String getLogin(){
        String user = JwtUtils.getUserName();
        if (user == null || user.isEmpty()){
            return "Konto";
        }
        return user;
    }

    public String getLvl(){
        return JwtUtils.getUserRole();
    }

    public boolean isClient(){
        return "CLIENT".equals(JwtUtils.getUserRole());
    }

    public boolean isLogedIn(){
        String user = JwtUtils.getUserName();
        return user != null && !user.isEmpty();
    }

    public boolean isUserInRole(String xxx){
        if(xxx==null){
            return false;
        }
        return xxx.equals(JwtUtils.getUserRole());
    }



}
