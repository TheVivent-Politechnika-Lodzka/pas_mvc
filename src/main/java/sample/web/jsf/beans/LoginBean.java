package sample.web.jsf.beans;

import lombok.Getter;
import sample.web.jsf.model.CredentialsData;
import sample.web.jsf.restclient.AuthRestClient;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.enterprise.AuthenticationStatus;
import javax.security.enterprise.SecurityContext;
import javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.Password;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import static javax.security.enterprise.authentication.mechanism.http.AuthenticationParameters.withParams;

@Named
@RequestScoped
public class LoginBean {

    // CredentialsData to własna, prosta klasa, która
    // jedynie sobie przechowuje login i hasło
    @Getter
    private final CredentialsData credentialsData = new CredentialsData();

    // ciekawostka. Intellij marudzi, że powinien być tutaj @Context
    // zamiast @Inject, ale jeżeli da się @Context to wyrzuca NullPointerException
    // ¯\_(ツ)_/¯
    @Inject
    private SecurityContext securityContext;

    // TUTAJ MUSI BYĆ @Inject
    @Inject
    private HttpServletRequest servletRequest;

    // TUTAJ MUSI BYĆ @Context
    @Context
    private HttpServletResponse servletResponse;

    public String login() {

        // identycznie jak w rest-api
        // tworzony jest obiekt Credential z javax.security.enterprise
        Credential credential = new UsernamePasswordCredential(
                credentialsData.getLogin(), new Password(credentialsData.getPassword()));

        // dzięki securityContext.authenticate kontener pośredniczy w uwierzytelnianiu
        // i wywoływana jest metoda .validate na IdenityStore
        AuthenticationStatus status = securityContext.authenticate(
                servletRequest, servletResponse, withParams().credential(credential));

        if(status.equals(AuthenticationStatus.SUCCESS)) {
            return "loginSuccess";
        }
        // tutaj nie jestem pewien jak zwraca przy poszczególnych sytuacjach failowania
        else if (status.equals(AuthenticationStatus.SEND_FAILURE)) {
            return "loginFailure";
        }
        return "error";

    }

}
