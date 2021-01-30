package server.database.tables;

import javax.persistence.*;

@Entity
@Table(name="Login")
public class Login {
    public Login(){};
    public Login(String login, String password){
        this.login = login;
        this.password = password;
    }

    @Id
    private String login;

    public String getLogin() { return login;}
    public void setLogin(String login) { this.login = login;}

    @Column
    private String password;

    public String getPassword() { return password;}
    public void setPassword(String password) { this.password = password;}

    @Override
    public String toString() {
        return "Login: login="+login+"  password="+password;
    }
}
