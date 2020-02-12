package kasianov.fx.utils.impl;

import kasianov.fx.utils.Store;
import org.springframework.stereotype.Service;

@Service
public class StoreImpl implements Store {
    private String token;
    private Boolean isTokenExists = false;

    @Override
    public void setToken(String token) {
        this.token = token;
        this.isTokenExists = true;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public void cleanToken() {
        this.token = null;
        this.isTokenExists = false;
    }

    @Override
    public boolean ifTokenExists() {
        return this.isTokenExists;
    }
}
