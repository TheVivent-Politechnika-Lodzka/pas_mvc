package sample.web.jsf.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import java.time.Instant;

@FacesConverter("dateConverter")
public class DateConverter implements javax.faces.convert.Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        // date get in as dd.MM.yyyy

        if (s == null || s.isEmpty()) {
            return null;
        }


        try {
            String[] date = s.split("\\.");
            String year = date[2];
            String month = date[1];
            String day = date[0];
            Instant actualDate = Instant.parse(year + "-" + month + "-" + day + "T00:00:00Z");
            return actualDate;
        }   catch (Exception e) {
            FacesMessage msg = new FacesMessage("Error converting date",
                    "Invalid date format");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(msg);
        }

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}
