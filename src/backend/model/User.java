package backend.model;

public class User {
    private String id;
    private String firsname;
    private final String username;
    private final String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String id, String firsname, String username, String password) {
        this.id = id;
        this.firsname = firsname;
        this.username = username;
        this.password = password;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirsname() {
        return firsname;
    }

    public void setFirsname(String firsname) {
        this.firsname = firsname;
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        return password;
    }
}
