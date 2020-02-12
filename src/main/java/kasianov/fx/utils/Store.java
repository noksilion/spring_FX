package kasianov.fx.utils;

public interface Store {
    void setToken(String token);
    String getToken();
    void cleanToken();
    boolean ifTokenExists();
}
