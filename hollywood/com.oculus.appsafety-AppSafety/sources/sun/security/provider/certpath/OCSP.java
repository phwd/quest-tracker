package sun.security.provider.certpath;

import java.io.IOException;
import java.net.URI;
import java.security.AccessController;
import java.security.cert.CRLReason;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateException;
import java.security.cert.Extension;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import sun.security.action.GetIntegerAction;
import sun.security.util.Debug;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.AccessDescription;
import sun.security.x509.AuthorityInfoAccessExtension;
import sun.security.x509.GeneralName;
import sun.security.x509.URIName;
import sun.security.x509.X509CertImpl;

public final class OCSP {
    private static final int CONNECT_TIMEOUT = initializeTimeout();
    private static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    static final ObjectIdentifier NONCE_EXTENSION_OID = ObjectIdentifier.newInternal(new int[]{1, 3, 6, 1, 5, 5, 7, 48, 1, 2});
    private static final Debug debug = Debug.getInstance("certpath");

    public interface RevocationStatus {

        public enum CertStatus {
            GOOD,
            REVOKED,
            UNKNOWN
        }

        CertStatus getCertStatus();

        CRLReason getRevocationReason();

        Date getRevocationTime();

        Map<String, Extension> getSingleExtensions();
    }

    private static int initializeTimeout() {
        Integer tmp = (Integer) AccessController.doPrivileged(new GetIntegerAction("com.sun.security.ocsp.timeout"));
        if (tmp == null || tmp.intValue() < 0) {
            return DEFAULT_CONNECT_TIMEOUT;
        }
        return tmp.intValue() * 1000;
    }

    private OCSP() {
    }

    public static RevocationStatus check(X509Certificate cert, X509Certificate issuerCert) throws IOException, CertPathValidatorException {
        X509CertImpl certImpl = X509CertImpl.toImpl(cert);
        URI responderURI = getResponderURI(certImpl);
        if (responderURI != null) {
            CertId certId = new CertId(issuerCert, certImpl.getSerialNumberObject());
            return check(Collections.singletonList(certId), responderURI, issuerCert, (X509Certificate) null, (Date) null, Collections.emptyList()).getSingleResponse(certId);
        }
        try {
            throw new CertPathValidatorException("No OCSP Responder URI in certificate");
        } catch (IOException | CertificateException e) {
            throw new CertPathValidatorException("Exception while encoding OCSPRequest", e);
        }
    }

    public static RevocationStatus check(X509Certificate cert, X509Certificate issuerCert, URI responderURI, X509Certificate responderCert, Date date) throws IOException, CertPathValidatorException {
        return check(cert, issuerCert, responderURI, responderCert, date, Collections.emptyList());
    }

    public static RevocationStatus check(X509Certificate cert, X509Certificate issuerCert, URI responderURI, X509Certificate responderCert, Date date, List<Extension> extensions) throws IOException, CertPathValidatorException {
        try {
            CertId certId = new CertId(issuerCert, X509CertImpl.toImpl(cert).getSerialNumberObject());
            return check(Collections.singletonList(certId), responderURI, issuerCert, responderCert, date, extensions).getSingleResponse(certId);
        } catch (IOException | CertificateException e) {
            throw new CertPathValidatorException("Exception while encoding OCSPRequest", e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:83:0x0134 A[SYNTHETIC, Splitter:B:83:0x0134] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x013e A[SYNTHETIC, Splitter:B:89:0x013e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static sun.security.provider.certpath.OCSPResponse check(java.util.List<sun.security.provider.certpath.CertId> r14, java.net.URI r15, java.security.cert.X509Certificate r16, java.security.cert.X509Certificate r17, java.util.Date r18, java.util.List<java.security.cert.Extension> r19) throws java.io.IOException, java.security.cert.CertPathValidatorException {
        /*
        // Method dump skipped, instructions count: 341
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.OCSP.check(java.util.List, java.net.URI, java.security.cert.X509Certificate, java.security.cert.X509Certificate, java.util.Date, java.util.List):sun.security.provider.certpath.OCSPResponse");
    }

    public static URI getResponderURI(X509Certificate cert) {
        try {
            return getResponderURI(X509CertImpl.toImpl(cert));
        } catch (CertificateException e) {
            return null;
        }
    }

    static URI getResponderURI(X509CertImpl certImpl) {
        AuthorityInfoAccessExtension aia = certImpl.getAuthorityInfoAccessExtension();
        if (aia == null) {
            return null;
        }
        for (AccessDescription description : aia.getAccessDescriptions()) {
            if (description.getAccessMethod().equals((Object) AccessDescription.Ad_OCSP_Id)) {
                GeneralName generalName = description.getAccessLocation();
                if (generalName.getType() == 6) {
                    return ((URIName) generalName.getName()).getURI();
                }
            }
        }
        return null;
    }
}
