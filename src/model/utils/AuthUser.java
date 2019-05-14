package model.utils;

public abstract class AuthUser {
    private String name, password;
    private long id;

    public AuthUser( String name, String password, long id ) {
        this.name = name;
        this.password = password;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }
}