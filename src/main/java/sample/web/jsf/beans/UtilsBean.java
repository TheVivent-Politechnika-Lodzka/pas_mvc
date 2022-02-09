package sample.web.jsf.beans;

import sample.web.jsf.model.User;
import sample.web.jsf.restclient.UserRestClient;
import sample.web.jsf.utils.ContextUtils;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
@RequestScoped
public class UtilsBean {

    @Inject
    JwtStore jwtStore;

    @Inject
    private UserRestClient userRestClient;

    @Inject
    private UserSettingsBean userSettingsBean;

    public String invalidateSession() {

        ContextUtils.invalidateSession();
        return "mainSite";
    }

    public String userSettings(){
        UUID uuid = UUID.fromString(jwtStore.getUserId());
        User user = userRestClient.getById(uuid);
        userSettingsBean.setUser(user);
        return "userSettings";
    }

    public String getLogin(){
        String user = jwtStore.getLogin();
        if (user == null || user.isEmpty()){
            return "Konto";
        }
        return user;
    }

    public String getLvl(){
        return jwtStore.getRole();
    }

    public boolean isClient(){
        return "CLIENT".equals(jwtStore.getRole());
    }

    public boolean isLogedIn(){
        String user = jwtStore.getLogin();
        return user != null && !user.isEmpty();
    }

    public boolean isUserInRole(String xxx){
        if(xxx==null){
            return false;
        }
        return xxx.equals(jwtStore.getRole());
    }

}
