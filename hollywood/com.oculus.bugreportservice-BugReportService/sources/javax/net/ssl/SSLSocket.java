package javax.net.ssl;

import java.net.Socket;

public abstract class SSLSocket extends Socket {
    public abstract String[] getEnabledCipherSuites();

    public abstract String[] getEnabledProtocols();

    public abstract boolean getNeedClientAuth();

    public abstract SSLSession getSession();

    public abstract String[] getSupportedCipherSuites();

    public abstract boolean getWantClientAuth();

    public abstract void setEnabledCipherSuites(String[] strArr);

    public abstract void setEnabledProtocols(String[] strArr);

    public abstract void setNeedClientAuth(boolean z);

    public abstract void setWantClientAuth(boolean z);

    public abstract void startHandshake();

    protected SSLSocket() {
    }

    public SSLParameters getSSLParameters() {
        SSLParameters sSLParameters = new SSLParameters();
        sSLParameters.setCipherSuites(getEnabledCipherSuites());
        sSLParameters.setProtocols(getEnabledProtocols());
        if (getNeedClientAuth()) {
            sSLParameters.setNeedClientAuth(true);
        } else if (getWantClientAuth()) {
            sSLParameters.setWantClientAuth(true);
        }
        return sSLParameters;
    }

    public void setSSLParameters(SSLParameters sSLParameters) {
        String[] cipherSuites = sSLParameters.getCipherSuites();
        if (cipherSuites != null) {
            setEnabledCipherSuites(cipherSuites);
        }
        String[] protocols = sSLParameters.getProtocols();
        if (protocols != null) {
            setEnabledProtocols(protocols);
        }
        if (sSLParameters.getNeedClientAuth()) {
            setNeedClientAuth(true);
        } else if (sSLParameters.getWantClientAuth()) {
            setWantClientAuth(true);
        } else {
            setWantClientAuth(false);
        }
    }

    public String getApplicationProtocol() {
        throw new UnsupportedOperationException();
    }

    @Override // java.net.Socket
    public String toString() {
        return "SSL" + super.toString();
    }
}
