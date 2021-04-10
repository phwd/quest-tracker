package java.security;

import java.io.Serializable;

public abstract class Permission implements Guard, Serializable {
    private String name;

    public abstract String getActions();

    public Permission(String str) {
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }
}
