package java.net;

import java.util.List;

public abstract class SecureCacheResponse extends CacheResponse {
    public abstract String getCipherSuite();

    public abstract List getLocalCertificateChain();

    public abstract List getServerCertificateChain();
}
