package javax.net.ssl;

import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SSLParameters {
    private AlgorithmConstraints algorithmConstraints;
    private String[] applicationProtocols;
    private String[] cipherSuites;
    private String identificationAlgorithm;
    private boolean needClientAuth;
    private boolean preferLocalCipherSuites;
    private String[] protocols;
    private Map<Integer, SNIMatcher> sniMatchers;
    private Map<Integer, SNIServerName> sniNames;
    private boolean wantClientAuth;

    public SSLParameters() {
        this.sniNames = null;
        this.sniMatchers = null;
        this.applicationProtocols = new String[0];
    }

    public SSLParameters(String[] cipherSuites2) {
        this.sniNames = null;
        this.sniMatchers = null;
        this.applicationProtocols = new String[0];
        setCipherSuites(cipherSuites2);
    }

    public SSLParameters(String[] cipherSuites2, String[] protocols2) {
        this.sniNames = null;
        this.sniMatchers = null;
        this.applicationProtocols = new String[0];
        setCipherSuites(cipherSuites2);
        setProtocols(protocols2);
    }

    private static String[] clone(String[] s) {
        if (s == null) {
            return null;
        }
        return (String[]) s.clone();
    }

    public String[] getCipherSuites() {
        return clone(this.cipherSuites);
    }

    public void setCipherSuites(String[] cipherSuites2) {
        this.cipherSuites = clone(cipherSuites2);
    }

    public String[] getProtocols() {
        return clone(this.protocols);
    }

    public void setProtocols(String[] protocols2) {
        this.protocols = clone(protocols2);
    }

    public boolean getWantClientAuth() {
        return this.wantClientAuth;
    }

    public void setWantClientAuth(boolean wantClientAuth2) {
        this.wantClientAuth = wantClientAuth2;
        this.needClientAuth = false;
    }

    public boolean getNeedClientAuth() {
        return this.needClientAuth;
    }

    public void setNeedClientAuth(boolean needClientAuth2) {
        this.wantClientAuth = false;
        this.needClientAuth = needClientAuth2;
    }

    public AlgorithmConstraints getAlgorithmConstraints() {
        return this.algorithmConstraints;
    }

    public void setAlgorithmConstraints(AlgorithmConstraints constraints) {
        this.algorithmConstraints = constraints;
    }

    public String getEndpointIdentificationAlgorithm() {
        return this.identificationAlgorithm;
    }

    public void setEndpointIdentificationAlgorithm(String algorithm) {
        this.identificationAlgorithm = algorithm;
    }

    public final void setServerNames(List<SNIServerName> serverNames) {
        if (serverNames == null) {
            this.sniNames = null;
        } else if (!serverNames.isEmpty()) {
            this.sniNames = new LinkedHashMap(serverNames.size());
            for (SNIServerName serverName : serverNames) {
                if (this.sniNames.put(Integer.valueOf(serverName.getType()), serverName) != null) {
                    throw new IllegalArgumentException("Duplicated server name of type " + serverName.getType());
                }
            }
        } else {
            this.sniNames = Collections.emptyMap();
        }
    }

    public final List<SNIServerName> getServerNames() {
        Map<Integer, SNIServerName> map = this.sniNames;
        if (map == null) {
            return null;
        }
        if (!map.isEmpty()) {
            return Collections.unmodifiableList(new ArrayList(this.sniNames.values()));
        }
        return Collections.emptyList();
    }

    public final void setSNIMatchers(Collection<SNIMatcher> matchers) {
        if (matchers == null) {
            this.sniMatchers = null;
        } else if (!matchers.isEmpty()) {
            this.sniMatchers = new HashMap(matchers.size());
            for (SNIMatcher matcher : matchers) {
                if (this.sniMatchers.put(Integer.valueOf(matcher.getType()), matcher) != null) {
                    throw new IllegalArgumentException("Duplicated server name of type " + matcher.getType());
                }
            }
        } else {
            this.sniMatchers = Collections.emptyMap();
        }
    }

    public final Collection<SNIMatcher> getSNIMatchers() {
        Map<Integer, SNIMatcher> map = this.sniMatchers;
        if (map == null) {
            return null;
        }
        if (!map.isEmpty()) {
            return Collections.unmodifiableList(new ArrayList(this.sniMatchers.values()));
        }
        return Collections.emptyList();
    }

    public final void setUseCipherSuitesOrder(boolean honorOrder) {
        this.preferLocalCipherSuites = honorOrder;
    }

    public final boolean getUseCipherSuitesOrder() {
        return this.preferLocalCipherSuites;
    }

    public String[] getApplicationProtocols() {
        return (String[]) this.applicationProtocols.clone();
    }

    public void setApplicationProtocols(String[] protocols2) {
        if (protocols2 != null) {
            String[] tempProtocols = (String[]) protocols2.clone();
            for (String p : tempProtocols) {
                if (p == null || p.equals("")) {
                    throw new IllegalArgumentException("An element of protocols was null/empty");
                }
            }
            this.applicationProtocols = tempProtocols;
            return;
        }
        throw new IllegalArgumentException("protocols was null");
    }
}
