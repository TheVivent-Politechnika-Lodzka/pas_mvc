package sample.web.jsf.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.UUID;

//@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class User{

    @EqualsAndHashCode.Include
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    private String login;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String surname;

    @Getter @Setter
    private boolean isActive = true;

    private String permissionLevel;

    @JsonIgnore
    public String getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(String permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

}
