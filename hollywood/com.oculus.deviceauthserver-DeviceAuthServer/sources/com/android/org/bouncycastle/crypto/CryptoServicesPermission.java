package com.android.org.bouncycastle.crypto;

import java.security.Permission;
import java.util.HashSet;
import java.util.Set;

public class CryptoServicesPermission extends Permission {
    public static final String DEFAULT_RANDOM = "defaultRandomConfig";
    public static final String GLOBAL_CONFIG = "globalConfig";
    public static final String THREAD_LOCAL_CONFIG = "threadLocalConfig";
    private final Set<String> actions = new HashSet();

    public CryptoServicesPermission(String name) {
        super(name);
        this.actions.add(name);
    }

    public boolean implies(Permission permission) {
        if (!(permission instanceof CryptoServicesPermission)) {
            return false;
        }
        CryptoServicesPermission other = (CryptoServicesPermission) permission;
        if (!getName().equals(other.getName()) && !this.actions.containsAll(other.actions)) {
            return false;
        }
        return true;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CryptoServicesPermission) || !this.actions.equals(((CryptoServicesPermission) obj).actions)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.actions.hashCode();
    }

    public String getActions() {
        return this.actions.toString();
    }
}
