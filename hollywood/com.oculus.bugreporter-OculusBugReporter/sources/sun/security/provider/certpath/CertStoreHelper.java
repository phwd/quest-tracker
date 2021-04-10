package sun.security.provider.certpath;

import java.io.IOException;
import java.net.URI;
import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import sun.security.util.Cache;

public abstract class CertStoreHelper {
    private static final int NUM_TYPES = 2;
    private static Cache<String, CertStoreHelper> cache = Cache.newSoftMemoryCache(2);
    private static final Map<String, String> classMap = new HashMap(2);

    public abstract CertStore getCertStore(URI uri) throws NoSuchAlgorithmException, InvalidAlgorithmParameterException;

    public abstract boolean isCausedByNetworkIssue(CertStoreException certStoreException);

    public abstract X509CRLSelector wrap(X509CRLSelector x509CRLSelector, Collection<X500Principal> collection, String str) throws IOException;

    public abstract X509CertSelector wrap(X509CertSelector x509CertSelector, X500Principal x500Principal, String str) throws IOException;

    static {
        classMap.put("LDAP", "sun.security.provider.certpath.ldap.LDAPCertStoreHelper");
        classMap.put("SSLServer", "sun.security.provider.certpath.ssl.SSLServerCertStoreHelper");
    }

    public static CertStoreHelper getInstance(final String type) throws NoSuchAlgorithmException {
        CertStoreHelper helper = cache.get(type);
        if (helper != null) {
            return helper;
        }
        final String cl = classMap.get(type);
        if (cl != null) {
            try {
                return (CertStoreHelper) AccessController.doPrivileged(new PrivilegedExceptionAction<CertStoreHelper>() {
                    /* class sun.security.provider.certpath.CertStoreHelper.AnonymousClass1 */

                    @Override // java.security.PrivilegedExceptionAction
                    public CertStoreHelper run() throws ClassNotFoundException {
                        try {
                            CertStoreHelper csh = (CertStoreHelper) Class.forName(String.this, true, null).newInstance();
                            CertStoreHelper.cache.put(type, csh);
                            return csh;
                        } catch (IllegalAccessException | InstantiationException e) {
                            throw new AssertionError(e);
                        }
                    }
                });
            } catch (PrivilegedActionException e) {
                throw new NoSuchAlgorithmException(type + " not available", e.getException());
            }
        } else {
            throw new NoSuchAlgorithmException(type + " not available");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static boolean isCausedByNetworkIssue(java.lang.String r5, java.security.cert.CertStoreException r6) {
        /*
            int r0 = r5.hashCode()
            r1 = 84300(0x1494c, float:1.1813E-40)
            r2 = 2
            r3 = 1
            r4 = 0
            if (r0 == r1) goto L_0x002b
            r1 = 2331559(0x2393a7, float:3.26721E-39)
            if (r0 == r1) goto L_0x0021
            r1 = 133315663(0x7f23c4f, float:3.644756E-34)
            if (r0 == r1) goto L_0x0017
        L_0x0016:
            goto L_0x0035
        L_0x0017:
            java.lang.String r0 = "SSLServer"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0016
            r0 = r3
            goto L_0x0036
        L_0x0021:
            java.lang.String r0 = "LDAP"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0016
            r0 = r4
            goto L_0x0036
        L_0x002b:
            java.lang.String r0 = "URI"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0016
            r0 = r2
            goto L_0x0036
        L_0x0035:
            r0 = -1
        L_0x0036:
            if (r0 == 0) goto L_0x004a
            if (r0 == r3) goto L_0x004a
            if (r0 == r2) goto L_0x003d
            return r4
        L_0x003d:
            java.lang.Throwable r0 = r6.getCause()
            if (r0 == 0) goto L_0x0048
            boolean r1 = r0 instanceof java.io.IOException
            if (r1 == 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r3 = r4
        L_0x0049:
            return r3
        L_0x004a:
            sun.security.provider.certpath.CertStoreHelper r0 = getInstance(r5)     // Catch:{ NoSuchAlgorithmException -> 0x0053 }
            boolean r1 = r0.isCausedByNetworkIssue(r6)     // Catch:{ NoSuchAlgorithmException -> 0x0053 }
            return r1
        L_0x0053:
            r0 = move-exception
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.CertStoreHelper.isCausedByNetworkIssue(java.lang.String, java.security.cert.CertStoreException):boolean");
    }
}
