package sun.security.provider.certpath;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.cert.CRLException;
import java.security.cert.CRLSelector;
import java.security.cert.CertSelector;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.CertStoreParameters;
import java.security.cert.CertStoreSpi;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import javax.security.auth.x500.X500Principal;
import sun.security.action.GetIntegerAction;
import sun.security.provider.certpath.PKIX;
import sun.security.util.Cache;
import sun.security.util.Debug;
import sun.security.x509.AccessDescription;
import sun.security.x509.GeneralNameInterface;
import sun.security.x509.URIName;

class URICertStore extends CertStoreSpi {
    private static final int CACHE_SIZE = 185;
    private static final int CHECK_INTERVAL = 30000;
    private static final int CRL_CONNECT_TIMEOUT = initializeTimeout();
    private static final int DEFAULT_CRL_CONNECT_TIMEOUT = 15000;
    private static final Cache<URICertStoreParameters, CertStore> certStoreCache = Cache.newSoftMemoryCache(185);
    private static final Debug debug = Debug.getInstance("certpath");
    private Collection<X509Certificate> certs = Collections.emptySet();
    private X509CRL crl;
    private final CertificateFactory factory;
    private long lastChecked;
    private long lastModified;
    private boolean ldap = false;
    private CertStore ldapCertStore;
    private CertStoreHelper ldapHelper;
    private String ldapPath;
    private URI uri;

    private static int initializeTimeout() {
        Integer tmp = (Integer) AccessController.doPrivileged(new GetIntegerAction("com.sun.security.crl.timeout"));
        if (tmp == null || tmp.intValue() < 0) {
            return DEFAULT_CRL_CONNECT_TIMEOUT;
        }
        return tmp.intValue() * 1000;
    }

    URICertStore(CertStoreParameters params) throws InvalidAlgorithmParameterException, NoSuchAlgorithmException {
        super(params);
        if (params instanceof URICertStoreParameters) {
            this.uri = ((URICertStoreParameters) params).uri;
            if (this.uri.getScheme().toLowerCase(Locale.ENGLISH).equals("ldap")) {
                this.ldap = true;
                this.ldapHelper = CertStoreHelper.getInstance("LDAP");
                this.ldapCertStore = this.ldapHelper.getCertStore(this.uri);
                this.ldapPath = this.uri.getPath();
                if (this.ldapPath.charAt(0) == '/') {
                    this.ldapPath = this.ldapPath.substring(1);
                }
            }
            try {
                this.factory = CertificateFactory.getInstance("X.509");
            } catch (CertificateException e) {
                throw new RuntimeException();
            }
        } else {
            throw new InvalidAlgorithmParameterException("params must be instanceof URICertStoreParameters");
        }
    }

    static synchronized CertStore getInstance(URICertStoreParameters params) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        CertStore ucs;
        synchronized (URICertStore.class) {
            if (debug != null) {
                Debug debug2 = debug;
                debug2.println("CertStore URI:" + ((Object) params.uri));
            }
            ucs = certStoreCache.get(params);
            if (ucs == null) {
                ucs = new UCS(new URICertStore(params), null, "URI", params);
                certStoreCache.put(params, ucs);
            } else if (debug != null) {
                debug.println("URICertStore.getInstance: cache hit");
            }
        }
        return ucs;
    }

    static CertStore getInstance(AccessDescription ad) {
        if (!ad.getAccessMethod().equals((Object) AccessDescription.Ad_CAISSUERS_Id)) {
            return null;
        }
        GeneralNameInterface gn = ad.getAccessLocation().getName();
        if (!(gn instanceof URIName)) {
            return null;
        }
        try {
            return getInstance(new URICertStoreParameters(((URIName) gn).getURI()));
        } catch (Exception ex) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("exception creating CertStore: " + ((Object) ex));
                ex.printStackTrace();
            }
            return null;
        }
    }

    /* JADX DEBUG: Type inference failed for r9v5. Raw type applied. Possible types: java.util.Collection<? extends java.security.cert.Certificate>, java.util.Collection<java.security.cert.X509Certificate> */
    /* JADX DEBUG: Type inference failed for r1v4. Raw type applied. Possible types: java.util.Collection<? extends java.security.cert.Certificate>, java.util.Collection<java.security.cert.X509Certificate> */
    @Override // java.security.cert.CertStoreSpi
    public synchronized Collection<X509Certificate> engineGetCertificates(CertSelector selector) throws CertStoreException {
        Throwable th;
        if (this.ldap) {
            X509CertSelector xsel = (X509CertSelector) selector;
            try {
                return this.ldapCertStore.getCertificates(this.ldapHelper.wrap(xsel, xsel.getSubject(), this.ldapPath));
            } catch (IOException ioe) {
                throw new CertStoreException(ioe);
            }
        } else {
            long time = System.currentTimeMillis();
            if (time - this.lastChecked < 30000) {
                if (debug != null) {
                    debug.println("Returning certificates from cache");
                }
                return getMatchingCerts(this.certs, selector);
            }
            this.lastChecked = time;
            URLConnection connection = this.uri.toURL().openConnection();
            if (this.lastModified != 0) {
                connection.setIfModifiedSince(this.lastModified);
            }
            long oldLastModified = this.lastModified;
            InputStream in = connection.getInputStream();
            try {
                this.lastModified = connection.getLastModified();
                if (oldLastModified != 0) {
                    try {
                        if (oldLastModified == this.lastModified) {
                            if (debug != null) {
                                debug.println("Not modified, using cached copy");
                            }
                            Collection<X509Certificate> matchingCerts = getMatchingCerts(this.certs, selector);
                            if (in != null) {
                                try {
                                    $closeResource(null, in);
                                } catch (IOException | CertificateException e) {
                                    if (debug != null) {
                                        debug.println("Exception fetching certificates:");
                                        e.printStackTrace();
                                    }
                                    this.lastModified = 0;
                                    this.certs = Collections.emptySet();
                                    return this.certs;
                                }
                            }
                            return matchingCerts;
                        } else if ((connection instanceof HttpURLConnection) && ((HttpURLConnection) connection).getResponseCode() == 304) {
                            if (debug != null) {
                                debug.println("Not modified, using cached copy");
                            }
                            Collection<X509Certificate> matchingCerts2 = getMatchingCerts(this.certs, selector);
                            if (in != null) {
                                $closeResource(null, in);
                            }
                            return matchingCerts2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            throw th;
                        } catch (Throwable th3) {
                            if (in != null) {
                                $closeResource(th, in);
                            }
                            throw th3;
                        }
                    }
                }
                if (debug != null) {
                    debug.println("Downloading new certificates...");
                }
                this.certs = this.factory.generateCertificates(in);
                if (in != null) {
                    $closeResource(null, in);
                }
                return getMatchingCerts(this.certs, selector);
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        }
    }

    private static /* synthetic */ void $closeResource(Throwable x0, AutoCloseable x1) {
        if (x0 != null) {
            try {
                x1.close();
            } catch (Throwable th) {
                x0.addSuppressed(th);
            }
        } else {
            x1.close();
        }
    }

    private static Collection<X509Certificate> getMatchingCerts(Collection<X509Certificate> certs2, CertSelector selector) {
        if (selector == null) {
            return certs2;
        }
        List<X509Certificate> matchedCerts = new ArrayList<>(certs2.size());
        for (X509Certificate cert : certs2) {
            if (selector.match(cert)) {
                matchedCerts.add(cert);
            }
        }
        return matchedCerts;
    }

    /* JADX DEBUG: Type inference failed for r1v8. Raw type applied. Possible types: java.util.Collection<? extends java.security.cert.CRL>, java.util.Collection<java.security.cert.X509CRL> */
    @Override // java.security.cert.CertStoreSpi
    public synchronized Collection<X509CRL> engineGetCRLs(CRLSelector selector) throws CertStoreException {
        Throwable th;
        if (this.ldap) {
            try {
                try {
                    return this.ldapCertStore.getCRLs(this.ldapHelper.wrap((X509CRLSelector) selector, (Collection<X500Principal>) null, this.ldapPath));
                } catch (CertStoreException cse) {
                    throw new PKIX.CertStoreTypeException("LDAP", cse);
                }
            } catch (IOException ioe) {
                throw new CertStoreException(ioe);
            }
        } else {
            long time = System.currentTimeMillis();
            if (time - this.lastChecked < 30000) {
                if (debug != null) {
                    debug.println("Returning CRL from cache");
                }
                return getMatchingCRLs(this.crl, selector);
            }
            this.lastChecked = time;
            URLConnection connection = this.uri.toURL().openConnection();
            if (this.lastModified != 0) {
                connection.setIfModifiedSince(this.lastModified);
            }
            long oldLastModified = this.lastModified;
            connection.setConnectTimeout(CRL_CONNECT_TIMEOUT);
            InputStream in = connection.getInputStream();
            try {
                this.lastModified = connection.getLastModified();
                if (oldLastModified != 0) {
                    try {
                        if (oldLastModified == this.lastModified) {
                            if (debug != null) {
                                debug.println("Not modified, using cached copy");
                            }
                            Collection<X509CRL> matchingCRLs = getMatchingCRLs(this.crl, selector);
                            if (in != null) {
                                try {
                                    $closeResource(null, in);
                                } catch (IOException | CRLException e) {
                                    if (debug != null) {
                                        debug.println("Exception fetching CRL:");
                                        e.printStackTrace();
                                    }
                                    this.lastModified = 0;
                                    this.crl = null;
                                    throw new PKIX.CertStoreTypeException("URI", new CertStoreException(e));
                                }
                            }
                            return matchingCRLs;
                        } else if ((connection instanceof HttpURLConnection) && ((HttpURLConnection) connection).getResponseCode() == 304) {
                            if (debug != null) {
                                debug.println("Not modified, using cached copy");
                            }
                            Collection<X509CRL> matchingCRLs2 = getMatchingCRLs(this.crl, selector);
                            if (in != null) {
                                $closeResource(null, in);
                            }
                            return matchingCRLs2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            throw th;
                        } catch (Throwable th3) {
                            if (in != null) {
                                $closeResource(th, in);
                            }
                            throw th3;
                        }
                    }
                }
                if (debug != null) {
                    debug.println("Downloading new CRL...");
                }
                this.crl = (X509CRL) this.factory.generateCRL(in);
                if (in != null) {
                    $closeResource(null, in);
                }
                return getMatchingCRLs(this.crl, selector);
            } catch (Throwable th4) {
                th = th4;
                throw th;
            }
        }
    }

    private static Collection<X509CRL> getMatchingCRLs(X509CRL crl2, CRLSelector selector) {
        if (selector == null || (crl2 != null && selector.match(crl2))) {
            return Collections.singletonList(crl2);
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public static class URICertStoreParameters implements CertStoreParameters {
        private volatile int hashCode = 0;
        private final URI uri;

        URICertStoreParameters(URI uri2) {
            this.uri = uri2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof URICertStoreParameters)) {
                return false;
            }
            return this.uri.equals(((URICertStoreParameters) obj).uri);
        }

        public int hashCode() {
            if (this.hashCode == 0) {
                this.hashCode = (17 * 37) + this.uri.hashCode();
            }
            return this.hashCode;
        }

        @Override // java.security.cert.CertStoreParameters
        public Object clone() {
            try {
                return super.clone();
            } catch (CloneNotSupportedException e) {
                throw new InternalError(e.toString(), e);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class UCS extends CertStore {
        protected UCS(CertStoreSpi spi, Provider p, String type, CertStoreParameters params) {
            super(spi, p, type, params);
        }
    }
}
