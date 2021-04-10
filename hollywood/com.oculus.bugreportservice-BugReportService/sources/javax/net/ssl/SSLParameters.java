package javax.net.ssl;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SSLParameters {
    private String[] applicationProtocols = new String[0];
    private String[] cipherSuites;
    private boolean needClientAuth;
    private String[] protocols;
    private Map sniMatchers = null;
    private Map sniNames = null;
    private boolean wantClientAuth;

    private static String[] clone(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        return (String[]) strArr.clone();
    }

    public String[] getCipherSuites() {
        return clone(this.cipherSuites);
    }

    public void setCipherSuites(String[] strArr) {
        this.cipherSuites = clone(strArr);
    }

    public String[] getProtocols() {
        return clone(this.protocols);
    }

    public void setProtocols(String[] strArr) {
        this.protocols = clone(strArr);
    }

    public boolean getWantClientAuth() {
        return this.wantClientAuth;
    }

    public void setWantClientAuth(boolean z) {
        this.wantClientAuth = z;
        this.needClientAuth = false;
    }

    public boolean getNeedClientAuth() {
        return this.needClientAuth;
    }

    public void setNeedClientAuth(boolean z) {
        this.wantClientAuth = false;
        this.needClientAuth = z;
    }

    public final void setServerNames(List list) {
        if (list == null) {
            this.sniNames = null;
        } else if (!list.isEmpty()) {
            this.sniNames = new LinkedHashMap(list.size());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SNIServerName sNIServerName = (SNIServerName) it.next();
                if (this.sniNames.put(Integer.valueOf(sNIServerName.getType()), sNIServerName) != null) {
                    throw new IllegalArgumentException("Duplicated server name of type " + sNIServerName.getType());
                }
            }
        } else {
            this.sniNames = Collections.emptyMap();
        }
    }

    public void setApplicationProtocols(String[] strArr) {
        if (strArr != null) {
            String[] strArr2 = (String[]) strArr.clone();
            for (String str : strArr2) {
                if (str == null || str.equals("")) {
                    throw new IllegalArgumentException("An element of protocols was null/empty");
                }
            }
            this.applicationProtocols = strArr2;
            return;
        }
        throw new IllegalArgumentException("protocols was null");
    }
}
