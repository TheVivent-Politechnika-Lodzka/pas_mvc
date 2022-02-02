package sample.web.jsf.beans;

import sample.web.jsf.utils.ContextUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class UtilsBean {

    public void invalidateSession() {
        ContextUtils.invalidateSession();
    }

}
