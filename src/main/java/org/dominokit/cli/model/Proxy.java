package org.dominokit.cli.model;

public class Proxy {
    private String name;
    private String token;
    private boolean autoReveal;
    private boolean singleton;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isAutoReveal() {
        return autoReveal;
    }

    public void setAutoReveal(boolean autoReveal) {
        this.autoReveal = autoReveal;
    }

    public boolean isSingleton() {
        return singleton;
    }

    public void setSingleton(boolean singleton) {
        this.singleton = singleton;
    }
}
