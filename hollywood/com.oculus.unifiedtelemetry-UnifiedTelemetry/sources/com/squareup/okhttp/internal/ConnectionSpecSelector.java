package com.squareup.okhttp.internal;

import com.squareup.okhttp.ConnectionSpec;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.UnknownServiceException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import javax.net.ssl.SSLSocket;

public final class ConnectionSpecSelector {
    public final List<ConnectionSpec> connectionSpecs;
    public boolean isFallback;
    public boolean isFallbackPossible;
    public int nextModeIndex = 0;

    public boolean connectionFailed(IOException iOException) {
        boolean z;
        this.isFallback = true;
        if (!this.isFallbackPossible || (iOException instanceof ProtocolException) || (iOException instanceof InterruptedIOException) || ((((z = iOException instanceof SSLHandshakeException)) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException))) {
            return false;
        }
        return z || (iOException instanceof SSLProtocolException);
    }

    private boolean isFallbackPossible(SSLSocket sSLSocket) {
        for (int i = this.nextModeIndex; i < this.connectionSpecs.size(); i++) {
            if (this.connectionSpecs.get(i).isCompatible(sSLSocket)) {
                return true;
            }
        }
        return false;
    }

    public ConnectionSpec configureSecureSocket(SSLSocket sSLSocket) throws IOException {
        int i = this.nextModeIndex;
        int size = this.connectionSpecs.size();
        while (i < size) {
            ConnectionSpec connectionSpec = this.connectionSpecs.get(i);
            i++;
            if (connectionSpec.isCompatible(sSLSocket)) {
                this.nextModeIndex = i;
                this.isFallbackPossible = isFallbackPossible(sSLSocket);
                Internal.instance.apply(connectionSpec, sSLSocket, this.isFallback);
                return connectionSpec;
            }
        }
        StringBuilder sb = new StringBuilder("Unable to find acceptable protocols. isFallback=");
        sb.append(this.isFallback);
        sb.append(", modes=");
        sb.append(this.connectionSpecs);
        sb.append(", supported protocols=");
        sb.append(Arrays.toString(sSLSocket.getEnabledProtocols()));
        throw new UnknownServiceException(sb.toString());
    }

    public ConnectionSpecSelector(List<ConnectionSpec> list) {
        this.connectionSpecs = list;
    }
}
