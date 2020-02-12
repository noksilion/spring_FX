package kasianov.fx.services.autorization;

public interface AuthService {
    void login(String email, String password);
    void signup(String email, String password,String userName);
    void logout();
}
