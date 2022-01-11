package sample.web.jsf.jsfexamples.model;

import java.util.UUID;

public class UserAdmin extends User<UserAdmin> {


    public UserAdmin(UUID id, String login, String password, String name, String surname) {
        super(id, login, password, name, surname);
    }

    public UserAdmin(UserAdmin user) {
        super(user);
    }

    @Override
    public UserAdmin copy() {
        return new UserAdmin(this);
    }

    @Override
    public int getPermissionLevel() {
        return 0;
    }
}
