package sample.web.jsf.security;

import sample.web.jsf.model.CredentialsData;
import sample.web.jsf.restclient.AuthRestClient;
import sample.web.jsf.utils.JwtStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.security.enterprise.credential.Credential;
import javax.security.enterprise.credential.UsernamePasswordCredential;
import javax.security.enterprise.identitystore.CredentialValidationResult;
import javax.security.enterprise.identitystore.IdentityStore;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class JwtIdentityStore implements IdentityStore {

    @Inject
    AuthRestClient authRestClient;

    @Inject
    JwtStore jwtStore;

    @Override
    public Set<String> getCallerGroups(CredentialValidationResult validationResult) {
        return IdentityStore.super.getCallerGroups(validationResult);
    }

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (credential instanceof UsernamePasswordCredential) {
            UsernamePasswordCredential upCredential = (UsernamePasswordCredential) credential;

            // CredentialsData to moja klasa do przechowywania danych z autoryzacji
            // można po prostu mieć 2 Stringi czy jak się chce
            CredentialsData credentialsData = new CredentialsData();
            credentialsData.setLogin(upCredential.getCaller());
            credentialsData.setPassword(upCredential.getPasswordAsString());

            // wywołanie REST API do zalogowania
            Response response = authRestClient.login(credentialsData);
            try {

                if (response.getStatus() == 202) {
                    String jwt = response.readEntity(String.class);
                    jwtStore.setToken(jwt);
                    HashSet<String> groups = new HashSet<>(Arrays.asList(jwtStore.getRole()));
                    // przy zwróceniu tego obiektu kontener dostaje informacje o użytkowniku
                    return new CredentialValidationResult(jwtStore.getLogin(), groups);
                }
            } catch (Exception e) {
                // mój jwtStore rzuci wyjątkiem jeśli token jest niepoprawny
                return CredentialValidationResult.INVALID_RESULT;
            }
            // jeżeli nie zwróciło status 202
            return CredentialValidationResult.INVALID_RESULT;
        }

        // jeżeli credential nie jest instancją klasy UsernamePasswordCredential
        return CredentialValidationResult.NOT_VALIDATED_RESULT;
    }
}
