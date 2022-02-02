package sample.web.jsf.beans;

import lombok.Getter;
import sample.web.jsf.model.CredentialsData;
import sample.web.jsf.restclient.AuthRestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;

@Named
@RequestScoped
public class LoginBean {

    @Getter
    private CredentialsData credentialsData = new CredentialsData();


    public String login() {
        Response res = AuthRestClient.login(credentialsData);

        System.out.println(res.getStatus());

        if (res.getStatus() == 202) {
            System.out.println("Login successful");
            return "success";
        }
        else {
            System.out.println("Login failed");
        }

        return "failure";

    }


}
