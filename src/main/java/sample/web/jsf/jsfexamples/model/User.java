package sample.web.jsf.jsfexamples.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.UUID;

//@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
abstract public class User<T extends User<T>>{

    @EqualsAndHashCode.Include
    @Getter @Setter
    private UUID id;

    @Getter @Setter
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "Login must be 3-20 characters long and contain only letters and numbers")
    private String login;

    @Getter @Setter
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]{3,20}$", message = "Password must be 3-20 characters long and contain only letters and numbers")
    private String password;

    @Getter @Setter
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "First name must be 3-20 characters long and contain only letters")
    private String name;

    @Getter @Setter
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z]{3,20}$", message = "Last name must be 3-20 characters long and contain only letters")
    private String surname;

    @Getter @Setter
    private boolean isActive = true;

    @Getter @Setter
    @JsonIgnore
    private int permissionLevel;


    public User(UUID id, String login, String password, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public User(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.isActive = user.isActive();
    }

    abstract public T copy();

    abstract public int getPermissionLevel();

}
