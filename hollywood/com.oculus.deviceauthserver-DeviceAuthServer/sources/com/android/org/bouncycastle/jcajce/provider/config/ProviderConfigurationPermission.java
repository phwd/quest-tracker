package com.android.org.bouncycastle.jcajce.provider.config;

import com.android.org.bouncycastle.util.Strings;
import java.security.BasicPermission;
import java.security.Permission;
import java.util.StringTokenizer;

public class ProviderConfigurationPermission extends BasicPermission {
    private static final int ACCEPTABLE_EC_CURVES = 16;
    private static final String ACCEPTABLE_EC_CURVES_STR = "acceptableeccurves";
    private static final int ADDITIONAL_EC_PARAMETERS = 32;
    private static final String ADDITIONAL_EC_PARAMETERS_STR = "additionalecparameters";
    private static final int ALL = 63;
    private static final String ALL_STR = "all";
    private static final int DH_DEFAULT_PARAMS = 8;
    private static final String DH_DEFAULT_PARAMS_STR = "dhdefaultparams";
    private static final int EC_IMPLICITLY_CA = 2;
    private static final String EC_IMPLICITLY_CA_STR = "ecimplicitlyca";
    private static final int THREAD_LOCAL_DH_DEFAULT_PARAMS = 4;
    private static final String THREAD_LOCAL_DH_DEFAULT_PARAMS_STR = "threadlocaldhdefaultparams";
    private static final int THREAD_LOCAL_EC_IMPLICITLY_CA = 1;
    private static final String THREAD_LOCAL_EC_IMPLICITLY_CA_STR = "threadlocalecimplicitlyca";
    private final String actions;
    private final int permissionMask;

    public ProviderConfigurationPermission(String name) {
        super(name);
        this.actions = ALL_STR;
        this.permissionMask = ALL;
    }

    public ProviderConfigurationPermission(String name, String actions2) {
        super(name, actions2);
        this.actions = actions2;
        this.permissionMask = calculateMask(actions2);
    }

    private int calculateMask(String actions2) {
        StringTokenizer tok = new StringTokenizer(Strings.toLowerCase(actions2), " ,");
        int mask = 0;
        while (tok.hasMoreTokens()) {
            String s = tok.nextToken();
            if (s.equals(THREAD_LOCAL_EC_IMPLICITLY_CA_STR)) {
                mask |= 1;
            } else if (s.equals(EC_IMPLICITLY_CA_STR)) {
                mask |= 2;
            } else if (s.equals(THREAD_LOCAL_DH_DEFAULT_PARAMS_STR)) {
                mask |= 4;
            } else if (s.equals(DH_DEFAULT_PARAMS_STR)) {
                mask |= 8;
            } else if (s.equals(ACCEPTABLE_EC_CURVES_STR)) {
                mask |= 16;
            } else if (s.equals(ADDITIONAL_EC_PARAMETERS_STR)) {
                mask |= 32;
            } else if (s.equals(ALL_STR)) {
                mask |= ALL;
            }
        }
        if (mask != 0) {
            return mask;
        }
        throw new IllegalArgumentException("unknown permissions passed to mask");
    }

    public String getActions() {
        return this.actions;
    }

    public boolean implies(Permission permission) {
        if (!(permission instanceof ProviderConfigurationPermission) || !getName().equals(permission.getName())) {
            return false;
        }
        int i = this.permissionMask;
        int i2 = ((ProviderConfigurationPermission) permission).permissionMask;
        if ((i & i2) == i2) {
            return true;
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProviderConfigurationPermission)) {
            return false;
        }
        ProviderConfigurationPermission other = (ProviderConfigurationPermission) obj;
        if (this.permissionMask != other.permissionMask || !getName().equals(other.getName())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return getName().hashCode() + this.permissionMask;
    }
}
