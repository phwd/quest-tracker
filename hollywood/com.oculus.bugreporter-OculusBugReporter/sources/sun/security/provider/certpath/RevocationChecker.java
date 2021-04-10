package sun.security.provider.certpath;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.AccessController;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivilegedAction;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CRLReason;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateRevokedException;
import java.security.cert.CollectionCertStoreParameters;
import java.security.cert.Extension;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.TrustAnchor;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import sun.security.provider.certpath.OCSP;
import sun.security.provider.certpath.PKIX;
import sun.security.util.Debug;
import sun.security.x509.AccessDescription;
import sun.security.x509.AuthorityInfoAccessExtension;
import sun.security.x509.PKIXExtensions;
import sun.security.x509.X509CRLEntryImpl;
import sun.security.x509.X509CertImpl;

class RevocationChecker extends PKIXRevocationChecker {
    private static final boolean[] ALL_REASONS = {true, true, true, true, true, true, true, true, true};
    private static final boolean[] CRL_SIGN_USAGE = {false, false, false, false, false, false, true};
    private static final String HEX_DIGITS = "0123456789ABCDEFabcdef";
    private static final long MAX_CLOCK_SKEW = 900000;
    private static final Debug debug = Debug.getInstance("certpath");
    private TrustAnchor anchor;
    private int certIndex;
    private List<CertStore> certStores;
    private boolean crlDP;
    private boolean crlSignFlag;
    private X509Certificate issuerCert;
    private boolean legacy = false;
    private Mode mode = Mode.PREFER_OCSP;
    private List<Extension> ocspExtensions;
    private Map<X509Certificate, byte[]> ocspResponses;
    private boolean onlyEE;
    private PKIX.ValidatorParams params;
    private PublicKey prevPubKey;
    private X509Certificate responderCert;
    private URI responderURI;
    private boolean softFail;
    private LinkedList<CertPathValidatorException> softFailExceptions = new LinkedList<>();

    /* access modifiers changed from: private */
    public enum Mode {
        PREFER_OCSP,
        PREFER_CRLS,
        ONLY_CRLS,
        ONLY_OCSP
    }

    /* access modifiers changed from: private */
    public static class RevocationProperties {
        boolean crlDPEnabled;
        boolean ocspEnabled;
        String ocspIssuer;
        String ocspSerial;
        String ocspSubject;
        String ocspUrl;
        boolean onlyEE;

        private RevocationProperties() {
        }
    }

    RevocationChecker() {
    }

    RevocationChecker(TrustAnchor anchor2, PKIX.ValidatorParams params2) throws CertPathValidatorException {
        init(anchor2, params2);
    }

    /* access modifiers changed from: package-private */
    public void init(TrustAnchor anchor2, PKIX.ValidatorParams params2) throws CertPathValidatorException {
        X509Certificate x509Certificate;
        RevocationProperties rp = getRevocationProperties();
        URI uri = getOcspResponder();
        this.responderURI = uri == null ? toURI(rp.ocspUrl) : uri;
        X509Certificate cert = getOcspResponderCert();
        if (cert == null) {
            x509Certificate = getResponderCert(rp, params2.trustAnchors(), params2.certStores());
        } else {
            x509Certificate = cert;
        }
        this.responderCert = x509Certificate;
        Set<PKIXRevocationChecker.Option> options = getOptions();
        for (PKIXRevocationChecker.Option option : options) {
            int i = AnonymousClass2.$SwitchMap$java$security$cert$PKIXRevocationChecker$Option[option.ordinal()];
            if (!(i == 1 || i == 2 || i == 3 || i == 4)) {
                throw new CertPathValidatorException("Unrecognized revocation parameter option: " + ((Object) option));
            }
        }
        this.softFail = options.contains(PKIXRevocationChecker.Option.SOFT_FAIL);
        if (this.legacy) {
            this.mode = rp.ocspEnabled ? Mode.PREFER_OCSP : Mode.ONLY_CRLS;
            this.onlyEE = rp.onlyEE;
        } else {
            if (options.contains(PKIXRevocationChecker.Option.NO_FALLBACK)) {
                if (options.contains(PKIXRevocationChecker.Option.PREFER_CRLS)) {
                    this.mode = Mode.ONLY_CRLS;
                } else {
                    this.mode = Mode.ONLY_OCSP;
                }
            } else if (options.contains(PKIXRevocationChecker.Option.PREFER_CRLS)) {
                this.mode = Mode.PREFER_CRLS;
            }
            this.onlyEE = options.contains(PKIXRevocationChecker.Option.ONLY_END_ENTITY);
        }
        if (this.legacy) {
            this.crlDP = rp.crlDPEnabled;
        } else {
            this.crlDP = true;
        }
        this.ocspResponses = getOcspResponses();
        this.ocspExtensions = getOcspExtensions();
        this.anchor = anchor2;
        this.params = params2;
        this.certStores = new ArrayList(params2.certStores());
        try {
            this.certStores.add(CertStore.getInstance("Collection", new CollectionCertStoreParameters(params2.certificates())));
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException e) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("RevocationChecker: error creating Collection CertStore: " + ((Object) e));
            }
        }
    }

    private static URI toURI(String uriString) throws CertPathValidatorException {
        if (uriString == null) {
            return null;
        }
        try {
            return new URI(uriString);
        } catch (URISyntaxException e) {
            throw new CertPathValidatorException("cannot parse ocsp.responderURL property", e);
        }
    }

    private static RevocationProperties getRevocationProperties() {
        return (RevocationProperties) AccessController.doPrivileged(new PrivilegedAction<RevocationProperties>() {
            /* class sun.security.provider.certpath.RevocationChecker.AnonymousClass1 */

            @Override // java.security.PrivilegedAction
            public RevocationProperties run() {
                RevocationProperties rp = new RevocationProperties();
                String onlyEE = Security.getProperty("com.sun.security.onlyCheckRevocationOfEECert");
                boolean z = true;
                rp.onlyEE = onlyEE != null && onlyEE.equalsIgnoreCase("true");
                String ocspEnabled = Security.getProperty("ocsp.enable");
                if (ocspEnabled == null || !ocspEnabled.equalsIgnoreCase("true")) {
                    z = false;
                }
                rp.ocspEnabled = z;
                rp.ocspUrl = Security.getProperty("ocsp.responderURL");
                rp.ocspSubject = Security.getProperty("ocsp.responderCertSubjectName");
                rp.ocspIssuer = Security.getProperty("ocsp.responderCertIssuerName");
                rp.ocspSerial = Security.getProperty("ocsp.responderCertSerialNumber");
                rp.crlDPEnabled = Boolean.getBoolean("com.sun.security.enableCRLDP");
                return rp;
            }
        });
    }

    private static X509Certificate getResponderCert(RevocationProperties rp, Set<TrustAnchor> anchors, List<CertStore> stores) throws CertPathValidatorException {
        if (rp.ocspSubject != null) {
            return getResponderCert(rp.ocspSubject, anchors, stores);
        }
        if (rp.ocspIssuer != null && rp.ocspSerial != null) {
            return getResponderCert(rp.ocspIssuer, rp.ocspSerial, anchors, stores);
        }
        if (rp.ocspIssuer == null && rp.ocspSerial == null) {
            return null;
        }
        throw new CertPathValidatorException("Must specify both ocsp.responderCertIssuerName and ocsp.responderCertSerialNumber properties");
    }

    private static X509Certificate getResponderCert(String subject, Set<TrustAnchor> anchors, List<CertStore> stores) throws CertPathValidatorException {
        X509CertSelector sel = new X509CertSelector();
        try {
            sel.setSubject(new X500Principal(subject));
            return getResponderCert(sel, anchors, stores);
        } catch (IllegalArgumentException e) {
            throw new CertPathValidatorException("cannot parse ocsp.responderCertSubjectName property", e);
        }
    }

    private static X509Certificate getResponderCert(String issuer, String serial, Set<TrustAnchor> anchors, List<CertStore> stores) throws CertPathValidatorException {
        X509CertSelector sel = new X509CertSelector();
        try {
            sel.setIssuer(new X500Principal(issuer));
            try {
                sel.setSerialNumber(new BigInteger(stripOutSeparators(serial), 16));
                return getResponderCert(sel, anchors, stores);
            } catch (NumberFormatException e) {
                throw new CertPathValidatorException("cannot parse ocsp.responderCertSerialNumber property", e);
            }
        } catch (IllegalArgumentException e2) {
            throw new CertPathValidatorException("cannot parse ocsp.responderCertIssuerName property", e2);
        }
    }

    private static X509Certificate getResponderCert(X509CertSelector sel, Set<TrustAnchor> anchors, List<CertStore> stores) throws CertPathValidatorException {
        for (TrustAnchor anchor2 : anchors) {
            X509Certificate cert = anchor2.getTrustedCert();
            if (cert != null && sel.match(cert)) {
                return cert;
            }
        }
        for (CertStore store : stores) {
            try {
                Collection<? extends Certificate> certs = store.getCertificates(sel);
                if (!certs.isEmpty()) {
                    return (X509Certificate) certs.iterator().next();
                }
            } catch (CertStoreException e) {
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("CertStore exception:" + ((Object) e));
                }
            }
        }
        throw new CertPathValidatorException("Cannot find the responder's certificate (set using the OCSP security properties).");
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public void init(boolean forward) throws CertPathValidatorException {
        PublicKey publicKey;
        if (!forward) {
            TrustAnchor trustAnchor = this.anchor;
            if (trustAnchor != null) {
                this.issuerCert = trustAnchor.getTrustedCert();
                X509Certificate x509Certificate = this.issuerCert;
                if (x509Certificate != null) {
                    publicKey = x509Certificate.getPublicKey();
                } else {
                    publicKey = this.anchor.getCAPublicKey();
                }
                this.prevPubKey = publicKey;
            }
            this.crlSignFlag = true;
            PKIX.ValidatorParams validatorParams = this.params;
            if (validatorParams == null || validatorParams.certPath() == null) {
                this.certIndex = -1;
            } else {
                this.certIndex = this.params.certPath().getCertificates().size() - 1;
            }
            this.softFailExceptions.clear();
            return;
        }
        throw new CertPathValidatorException("forward checking not supported");
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.CertPathChecker
    public boolean isForwardCheckingSupported() {
        return false;
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public Set<String> getSupportedExtensions() {
        return null;
    }

    @Override // java.security.cert.PKIXRevocationChecker
    public List<CertPathValidatorException> getSoftFailExceptions() {
        return Collections.unmodifiableList(this.softFailExceptions);
    }

    @Override // java.security.cert.PKIXCertPathChecker
    public void check(Certificate cert, Collection<String> unresolvedCritExts) throws CertPathValidatorException {
        check((X509Certificate) cert, unresolvedCritExts, this.prevPubKey, this.crlSignFlag);
    }

    private void check(X509Certificate xcert, Collection<String> unresolvedCritExts, PublicKey pubKey, boolean crlSignFlag2) throws CertPathValidatorException {
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("RevocationChecker.check: checking cert\n  SN: " + Debug.toHexString(xcert.getSerialNumber()) + "\n  Subject: " + ((Object) xcert.getSubjectX500Principal()) + "\n  Issuer: " + ((Object) xcert.getIssuerX500Principal()));
        }
        try {
            if (!this.onlyEE || xcert.getBasicConstraints() == -1) {
                int i = AnonymousClass2.$SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode[this.mode.ordinal()];
                if (i == 1 || i == 2) {
                    checkOCSP(xcert, unresolvedCritExts);
                    updateState(xcert);
                    return;
                }
                if (i == 3 || i == 4) {
                    checkCRLs(xcert, unresolvedCritExts, null, pubKey, crlSignFlag2);
                }
                updateState(xcert);
                return;
            }
            if (debug != null) {
                debug.println("Skipping revocation check; cert is not an end entity cert");
            }
        } catch (CertPathValidatorException e) {
            if (e.getReason() != CertPathValidatorException.BasicReason.REVOKED) {
                boolean eSoftFail = isSoftFailException(e);
                if (eSoftFail) {
                    if (this.mode == Mode.ONLY_OCSP || this.mode == Mode.ONLY_CRLS) {
                        return;
                    }
                } else if (this.mode == Mode.ONLY_OCSP || this.mode == Mode.ONLY_CRLS) {
                    throw e;
                }
                if (debug != null) {
                    Debug debug3 = debug;
                    debug3.println("RevocationChecker.check() " + e.getMessage());
                    debug.println("RevocationChecker.check() preparing to failover");
                }
                try {
                    int i2 = AnonymousClass2.$SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode[this.mode.ordinal()];
                    if (i2 == 1) {
                        checkCRLs(xcert, unresolvedCritExts, null, pubKey, crlSignFlag2);
                    } else if (i2 == 3) {
                        checkOCSP(xcert, unresolvedCritExts);
                    }
                } catch (CertPathValidatorException x) {
                    if (debug != null) {
                        debug.println("RevocationChecker.check() failover failed");
                        Debug debug4 = debug;
                        debug4.println("RevocationChecker.check() " + x.getMessage());
                    }
                    if (x.getReason() == CertPathValidatorException.BasicReason.REVOKED) {
                        throw x;
                    } else if (!isSoftFailException(x)) {
                        e.addSuppressed(x);
                        throw e;
                    } else if (!eSoftFail) {
                        throw e;
                    }
                }
            } else {
                throw e;
            }
        } finally {
            updateState(xcert);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: sun.security.provider.certpath.RevocationChecker$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$java$security$cert$PKIXRevocationChecker$Option = new int[PKIXRevocationChecker.Option.values().length];
        static final /* synthetic */ int[] $SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode = new int[Mode.values().length];

        static {
            try {
                $SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode[Mode.PREFER_OCSP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode[Mode.ONLY_OCSP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode[Mode.PREFER_CRLS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$sun$security$provider$certpath$RevocationChecker$Mode[Mode.ONLY_CRLS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$security$cert$PKIXRevocationChecker$Option[PKIXRevocationChecker.Option.ONLY_END_ENTITY.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$security$cert$PKIXRevocationChecker$Option[PKIXRevocationChecker.Option.PREFER_CRLS.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$security$cert$PKIXRevocationChecker$Option[PKIXRevocationChecker.Option.SOFT_FAIL.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$security$cert$PKIXRevocationChecker$Option[PKIXRevocationChecker.Option.NO_FALLBACK.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    private boolean isSoftFailException(CertPathValidatorException e) {
        if (!this.softFail || e.getReason() != CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS) {
            return false;
        }
        this.softFailExceptions.addFirst(new CertPathValidatorException(e.getMessage(), e.getCause(), this.params.certPath(), this.certIndex, e.getReason()));
        return true;
    }

    private void updateState(X509Certificate cert) throws CertPathValidatorException {
        this.issuerCert = cert;
        PublicKey pubKey = cert.getPublicKey();
        if (PKIX.isDSAPublicKeyWithoutParams(pubKey)) {
            pubKey = BasicChecker.makeInheritedParamsKey(pubKey, this.prevPubKey);
        }
        this.prevPubKey = pubKey;
        this.crlSignFlag = certCanSignCrl(cert);
        int i = this.certIndex;
        if (i > 0) {
            this.certIndex = i - 1;
        }
    }

    private void checkCRLs(X509Certificate cert, Collection<String> collection, Set<X509Certificate> stackedCerts, PublicKey pubKey, boolean signFlag) throws CertPathValidatorException {
        checkCRLs(cert, pubKey, null, signFlag, true, stackedCerts, this.params.trustAnchors());
    }

    private void checkCRLs(X509Certificate cert, PublicKey prevKey, X509Certificate prevCert, boolean signFlag, boolean allowSeparateKey, Set<X509Certificate> stackedCerts, Set<TrustAnchor> anchors) throws CertPathValidatorException {
        CertStoreException e;
        boolean[] reasonsMask;
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("RevocationChecker.checkCRLs() ---checking revocation status ...");
        }
        if (stackedCerts == null || !stackedCerts.contains(cert)) {
            Set<X509CRL> possibleCRLs = new HashSet<>();
            Set<X509CRL> approvedCRLs = new HashSet<>();
            X509CRLSelector sel = new X509CRLSelector();
            sel.setCertificateChecking(cert);
            CertPathHelper.setDateAndTime(sel, this.params.date(), MAX_CLOCK_SKEW);
            CertPathValidatorException networkFailureException = null;
            for (CertStore store : this.certStores) {
                try {
                    for (CRL crl : store.getCRLs(sel)) {
                        possibleCRLs.add((X509CRL) crl);
                    }
                } catch (CertStoreException e2) {
                    Debug debug3 = debug;
                    if (debug3 != null) {
                        debug3.println("RevocationChecker.checkCRLs() CertStoreException: " + e2.getMessage());
                    }
                    if (networkFailureException == null && CertStoreHelper.isCausedByNetworkIssue(store.getType(), e2)) {
                        networkFailureException = new CertPathValidatorException("Unable to determine revocation status due to network error", e2, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                    }
                }
            }
            Debug debug4 = debug;
            if (debug4 != null) {
                debug4.println("RevocationChecker.checkCRLs() possible crls.size() = " + possibleCRLs.size());
            }
            boolean[] reasonsMask2 = new boolean[9];
            if (!possibleCRLs.isEmpty()) {
                approvedCRLs.addAll(verifyPossibleCRLs(possibleCRLs, cert, prevKey, signFlag, reasonsMask2, anchors));
            }
            Debug debug5 = debug;
            if (debug5 != null) {
                debug5.println("RevocationChecker.checkCRLs() approved crls.size() = " + approvedCRLs.size());
            }
            if (approvedCRLs.isEmpty() || !Arrays.equals(reasonsMask2, ALL_REASONS)) {
                try {
                    if (this.crlDP) {
                        try {
                            reasonsMask = reasonsMask2;
                            try {
                                approvedCRLs.addAll(DistributionPointFetcher.getCRLs(sel, signFlag, prevKey, prevCert, this.params.sigProvider(), this.certStores, reasonsMask, anchors, null));
                            } catch (CertStoreException e3) {
                                e = e3;
                            }
                        } catch (CertStoreException e4) {
                            e = e4;
                            if (e instanceof PKIX.CertStoreTypeException) {
                            }
                            throw new CertPathValidatorException(e);
                        }
                    } else {
                        reasonsMask = reasonsMask2;
                    }
                    if (!approvedCRLs.isEmpty() && Arrays.equals(reasonsMask, ALL_REASONS)) {
                        checkApprovedCRLs(cert, approvedCRLs);
                    } else if (allowSeparateKey) {
                        try {
                            verifyWithSeparateSigningKey(cert, prevKey, signFlag, stackedCerts);
                        } catch (CertPathValidatorException cpve) {
                            if (networkFailureException != null) {
                                throw networkFailureException;
                            }
                            throw cpve;
                        }
                    } else if (networkFailureException != null) {
                        throw networkFailureException;
                    } else {
                        throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                    }
                } catch (CertStoreException e5) {
                    e = e5;
                    if ((e instanceof PKIX.CertStoreTypeException) || !CertStoreHelper.isCausedByNetworkIssue(((PKIX.CertStoreTypeException) e).getType(), e)) {
                        throw new CertPathValidatorException(e);
                    }
                    throw new CertPathValidatorException("Unable to determine revocation status due to network error", e, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                }
            } else {
                checkApprovedCRLs(cert, approvedCRLs);
            }
        } else {
            Debug debug6 = debug;
            if (debug6 != null) {
                debug6.println("RevocationChecker.checkCRLs() circular dependency");
            }
            throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
        }
    }

    private void checkApprovedCRLs(X509Certificate cert, Set<X509CRL> approvedCRLs) throws CertPathValidatorException {
        if (debug != null) {
            BigInteger sn = cert.getSerialNumber();
            debug.println("RevocationChecker.checkApprovedCRLs() starting the final sweep...");
            Debug debug2 = debug;
            debug2.println("RevocationChecker.checkApprovedCRLs() cert SN: " + sn.toString());
        }
        CRLReason cRLReason = CRLReason.UNSPECIFIED;
        for (X509CRL crl : approvedCRLs) {
            X509CRLEntry e = crl.getRevokedCertificate(cert);
            if (e != null) {
                try {
                    X509CRLEntryImpl entry = X509CRLEntryImpl.toImpl(e);
                    Debug debug3 = debug;
                    if (debug3 != null) {
                        debug3.println("RevocationChecker.checkApprovedCRLs() CRL entry: " + entry.toString());
                    }
                    Set<String> unresCritExts = entry.getCriticalExtensionOIDs();
                    if (unresCritExts != null && !unresCritExts.isEmpty()) {
                        unresCritExts.remove(PKIXExtensions.ReasonCode_Id.toString());
                        unresCritExts.remove(PKIXExtensions.CertificateIssuer_Id.toString());
                        if (!unresCritExts.isEmpty()) {
                            throw new CertPathValidatorException("Unrecognized critical extension(s) in revoked CRL entry");
                        }
                    }
                    CRLReason reasonCode = entry.getRevocationReason();
                    if (reasonCode == null) {
                        reasonCode = CRLReason.UNSPECIFIED;
                    }
                    Date revocationDate = entry.getRevocationDate();
                    if (revocationDate.before(this.params.date())) {
                        Throwable t = new CertificateRevokedException(revocationDate, reasonCode, crl.getIssuerX500Principal(), entry.getExtensions());
                        throw new CertPathValidatorException(t.getMessage(), t, null, -1, CertPathValidatorException.BasicReason.REVOKED);
                    }
                } catch (CRLException ce) {
                    throw new CertPathValidatorException(ce);
                }
            }
        }
    }

    private void checkOCSP(X509Certificate cert, Collection<String> collection) throws CertPathValidatorException {
        IOException e;
        CertId certId;
        OCSPResponse response;
        URI responderURI2;
        try {
            X509CertImpl currCert = X509CertImpl.toImpl(cert);
            try {
                if (this.issuerCert != null) {
                    certId = new CertId(this.issuerCert, currCert.getSerialNumberObject());
                } else {
                    certId = new CertId(this.anchor.getCA(), this.anchor.getCAPublicKey(), currCert.getSerialNumberObject());
                }
                byte[] responseBytes = this.ocspResponses.get(cert);
                if (responseBytes != null) {
                    if (debug != null) {
                        debug.println("Found cached OCSP response");
                    }
                    OCSPResponse response2 = new OCSPResponse(responseBytes);
                    byte[] nonce = null;
                    try {
                        for (Extension ext : this.ocspExtensions) {
                            if (ext.getId().equals("1.3.6.1.5.5.7.48.1.2")) {
                                nonce = ext.getValue();
                            }
                        }
                        response2.verify(Collections.singletonList(certId), this.issuerCert, this.responderCert, this.params.date(), nonce);
                        response = response2;
                    } catch (IOException e2) {
                        e = e2;
                        throw new CertPathValidatorException("Unable to determine revocation status due to network error", e, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                    }
                } else {
                    if (this.responderURI != null) {
                        responderURI2 = this.responderURI;
                    } else {
                        responderURI2 = OCSP.getResponderURI(currCert);
                    }
                    if (responderURI2 != null) {
                        response = OCSP.check(Collections.singletonList(certId), responderURI2, this.issuerCert, this.responderCert, (Date) null, this.ocspExtensions);
                    } else {
                        throw new CertPathValidatorException("Certificate does not specify OCSP responder", null, null, -1);
                    }
                }
                OCSP.RevocationStatus rs = response.getSingleResponse(certId);
                OCSP.RevocationStatus.CertStatus certStatus = rs.getCertStatus();
                if (certStatus == OCSP.RevocationStatus.CertStatus.REVOKED) {
                    Date revocationTime = rs.getRevocationTime();
                    if (revocationTime.before(this.params.date())) {
                        Throwable t = new CertificateRevokedException(revocationTime, rs.getRevocationReason(), response.getSignerCertificate().getSubjectX500Principal(), rs.getSingleExtensions());
                        throw new CertPathValidatorException(t.getMessage(), t, null, -1, CertPathValidatorException.BasicReason.REVOKED);
                    }
                } else if (certStatus == OCSP.RevocationStatus.CertStatus.UNKNOWN) {
                    throw new CertPathValidatorException("Certificate's revocation status is unknown", null, this.params.certPath(), -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                }
            } catch (IOException e3) {
                e = e3;
                throw new CertPathValidatorException("Unable to determine revocation status due to network error", e, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
            }
        } catch (CertificateException ce) {
            throw new CertPathValidatorException(ce);
        }
    }

    private static String stripOutSeparators(String value) {
        char[] chars = value.toCharArray();
        StringBuilder hexNumber = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (HEX_DIGITS.indexOf(chars[i]) != -1) {
                hexNumber.append(chars[i]);
            }
        }
        return hexNumber.toString();
    }

    static boolean certCanSignCrl(X509Certificate cert) {
        boolean[] keyUsage = cert.getKeyUsage();
        if (keyUsage != null) {
            return keyUsage[6];
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00c3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.Collection<java.security.cert.X509CRL> verifyPossibleCRLs(java.util.Set<java.security.cert.X509CRL> r20, java.security.cert.X509Certificate r21, java.security.PublicKey r22, boolean r23, boolean[] r24, java.util.Set<java.security.cert.TrustAnchor> r25) throws java.security.cert.CertPathValidatorException {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.RevocationChecker.verifyPossibleCRLs(java.util.Set, java.security.cert.X509Certificate, java.security.PublicKey, boolean, boolean[], java.util.Set):java.util.Collection");
    }

    private void verifyWithSeparateSigningKey(X509Certificate cert, PublicKey prevKey, boolean signFlag, Set<X509Certificate> stackedCerts) throws CertPathValidatorException {
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("RevocationChecker.verifyWithSeparateSigningKey() ---checking " + "revocation status" + "...");
        }
        if (stackedCerts != null && stackedCerts.contains(cert)) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("RevocationChecker.verifyWithSeparateSigningKey() circular dependency");
            }
            throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
        } else if (!signFlag) {
            buildToNewKey(cert, null, stackedCerts);
        } else {
            buildToNewKey(cert, prevKey, stackedCerts);
        }
    }

    private void buildToNewKey(X509Certificate currCert, PublicKey prevKey, Set<X509Certificate> stackedCerts) throws CertPathValidatorException {
        Set<TrustAnchor> newAnchors;
        InvalidAlgorithmParameterException iape;
        Set<X509Certificate> stackedCerts2;
        PublicKey prevKey2;
        Set<X509Certificate> stackedCerts3;
        CertPathBuilder builder;
        int i;
        PublicKey newKey;
        List<? extends Certificate> cpList;
        X509Certificate newCert;
        PublicKey newKey2;
        CertPathValidatorException cpve;
        X509Certificate cert;
        List<AccessDescription> adList;
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("RevocationChecker.buildToNewKey() starting work");
        }
        Set<PublicKey> badKeys = new HashSet<>();
        if (prevKey != null) {
            badKeys.add(prevKey);
        }
        X509CertSelector certSel = new RejectKeySelector(badKeys);
        certSel.setSubject(currCert.getIssuerX500Principal());
        certSel.setKeyUsage(CRL_SIGN_USAGE);
        TrustAnchor trustAnchor = this.anchor;
        if (trustAnchor == null) {
            newAnchors = this.params.trustAnchors();
        } else {
            newAnchors = Collections.singleton(trustAnchor);
        }
        try {
            PKIXBuilderParameters builderParams = new PKIXBuilderParameters(newAnchors, certSel);
            builderParams.setInitialPolicies(this.params.initialPolicies());
            builderParams.setCertStores(this.certStores);
            builderParams.setExplicitPolicyRequired(this.params.explicitPolicyRequired());
            builderParams.setPolicyMappingInhibited(this.params.policyMappingInhibited());
            builderParams.setAnyPolicyInhibited(this.params.anyPolicyInhibited());
            builderParams.setDate(this.params.date());
            builderParams.setCertPathCheckers(this.params.getPKIXParameters().getCertPathCheckers());
            builderParams.setSigProvider(this.params.sigProvider());
            int i2 = 0;
            builderParams.setRevocationEnabled(false);
            int i3 = 1;
            if (Builder.USE_AIA) {
                X509CertImpl currCertImpl = null;
                try {
                    currCertImpl = X509CertImpl.toImpl(currCert);
                } catch (CertificateException ce) {
                    Debug debug3 = debug;
                    if (debug3 != null) {
                        debug3.println("RevocationChecker.buildToNewKey: error decoding cert: " + ((Object) ce));
                    }
                }
                AuthorityInfoAccessExtension aiaExt = null;
                if (currCertImpl != null) {
                    aiaExt = currCertImpl.getAuthorityInfoAccessExtension();
                }
                if (!(aiaExt == null || (adList = aiaExt.getAccessDescriptions()) == null)) {
                    for (AccessDescription ad : adList) {
                        CertStore cs = URICertStore.getInstance(ad);
                        if (cs != null) {
                            Debug debug4 = debug;
                            if (debug4 != null) {
                                debug4.println("adding AIAext CertStore");
                            }
                            builderParams.addCertStore(cs);
                        }
                    }
                }
            }
            try {
                CertPathBuilder builder2 = CertPathBuilder.getInstance("PKIX");
                Set<X509Certificate> stackedCerts4 = stackedCerts;
                while (true) {
                    try {
                        if (debug != null) {
                            try {
                                debug.println("RevocationChecker.buildToNewKey() about to try build ...");
                            } catch (InvalidAlgorithmParameterException e) {
                                iape = e;
                            } catch (CertPathBuilderException e2) {
                                throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                            }
                        }
                        PKIXCertPathBuilderResult cpbr = (PKIXCertPathBuilderResult) builder2.build(builderParams);
                        if (debug != null) {
                            debug.println("RevocationChecker.buildToNewKey() about to check revocation ...");
                        }
                        if (stackedCerts4 == null) {
                            stackedCerts2 = new HashSet<>();
                        } else {
                            stackedCerts2 = stackedCerts4;
                        }
                        try {
                            stackedCerts2.add(currCert);
                            TrustAnchor ta = cpbr.getTrustAnchor();
                            PublicKey prevKey22 = ta.getCAPublicKey();
                            if (prevKey22 == null) {
                                try {
                                    prevKey2 = ta.getTrustedCert().getPublicKey();
                                } catch (InvalidAlgorithmParameterException e3) {
                                    iape = e3;
                                    throw new CertPathValidatorException(iape);
                                } catch (CertPathBuilderException e4) {
                                    throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                                }
                            } else {
                                prevKey2 = prevKey22;
                            }
                            List<? extends Certificate> cpList2 = cpbr.getCertPath().getCertificates();
                            try {
                                int i4 = cpList2.size() - i3;
                                PublicKey prevKey23 = prevKey2;
                                boolean signFlag = true;
                                while (i4 >= 0) {
                                    try {
                                        cert = (X509Certificate) cpList2.get(i4);
                                        if (debug != null) {
                                            try {
                                                Debug debug5 = debug;
                                                StringBuilder sb = new StringBuilder();
                                                try {
                                                    sb.append("RevocationChecker.buildToNewKey() index ");
                                                    sb.append(i4);
                                                    sb.append(" checking ");
                                                    sb.append((Object) cert);
                                                    debug5.println(sb.toString());
                                                } catch (CertPathValidatorException e5) {
                                                    stackedCerts3 = stackedCerts2;
                                                    builder = builder2;
                                                    i = 1;
                                                }
                                            } catch (CertPathValidatorException e6) {
                                                stackedCerts3 = stackedCerts2;
                                                builder = builder2;
                                                i = i3;
                                                badKeys.add(cpbr.getPublicKey());
                                                i3 = i;
                                                builder2 = builder;
                                                stackedCerts4 = stackedCerts3;
                                                i2 = 0;
                                            }
                                        }
                                        stackedCerts3 = stackedCerts2;
                                        builder = builder2;
                                        i = 1;
                                    } catch (CertPathValidatorException e7) {
                                        stackedCerts3 = stackedCerts2;
                                        builder = builder2;
                                        i = i3;
                                        badKeys.add(cpbr.getPublicKey());
                                        i3 = i;
                                        builder2 = builder;
                                        stackedCerts4 = stackedCerts3;
                                        i2 = 0;
                                    }
                                    try {
                                        checkCRLs(cert, prevKey23, null, signFlag, true, stackedCerts3, newAnchors);
                                        signFlag = certCanSignCrl(cert);
                                        prevKey23 = cert.getPublicKey();
                                        i4--;
                                        cpList2 = cpList2;
                                        i3 = 1;
                                        builder2 = builder;
                                        stackedCerts2 = stackedCerts3;
                                    } catch (CertPathValidatorException e8) {
                                        badKeys.add(cpbr.getPublicKey());
                                        i3 = i;
                                        builder2 = builder;
                                        stackedCerts4 = stackedCerts3;
                                        i2 = 0;
                                    }
                                }
                                stackedCerts3 = stackedCerts2;
                                builder = builder2;
                                i = i3;
                                try {
                                    newKey2 = newKey;
                                    try {
                                        checkCRLs(currCert, newKey, newCert, true, false, null, this.params.trustAnchors());
                                        return;
                                    } catch (CertPathValidatorException e9) {
                                        cpve = e9;
                                    }
                                } catch (CertPathValidatorException e10) {
                                    cpve = e10;
                                    newKey2 = newKey;
                                    if (cpve.getReason() != CertPathValidatorException.BasicReason.REVOKED) {
                                        badKeys.add(newKey2);
                                        i3 = i;
                                        builder2 = builder;
                                        stackedCerts4 = stackedCerts3;
                                        i2 = 0;
                                    } else {
                                        throw cpve;
                                    }
                                }
                            } catch (CertPathValidatorException e11) {
                                stackedCerts3 = stackedCerts2;
                                builder = builder2;
                                i = i3;
                                badKeys.add(cpbr.getPublicKey());
                                i3 = i;
                                builder2 = builder;
                                stackedCerts4 = stackedCerts3;
                                i2 = 0;
                            }
                            try {
                                if (debug != null) {
                                    debug.println("RevocationChecker.buildToNewKey() got key " + ((Object) cpbr.getPublicKey()));
                                }
                                newKey = cpbr.getPublicKey();
                                if (cpList2.isEmpty()) {
                                    cpList = cpList2;
                                    newCert = null;
                                } else {
                                    cpList = cpList2;
                                    newCert = (X509Certificate) cpList.get(i2);
                                }
                            } catch (InvalidAlgorithmParameterException e12) {
                                iape = e12;
                                throw new CertPathValidatorException(iape);
                            } catch (CertPathBuilderException e13) {
                                throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                            }
                            i3 = i;
                            builder2 = builder;
                            stackedCerts4 = stackedCerts3;
                            i2 = 0;
                        } catch (InvalidAlgorithmParameterException e14) {
                            iape = e14;
                            throw new CertPathValidatorException(iape);
                        } catch (CertPathBuilderException e15) {
                            throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                        }
                    } catch (InvalidAlgorithmParameterException e16) {
                        iape = e16;
                        throw new CertPathValidatorException(iape);
                    } catch (CertPathBuilderException e17) {
                        throw new CertPathValidatorException("Could not determine revocation status", null, null, -1, CertPathValidatorException.BasicReason.UNDETERMINED_REVOCATION_STATUS);
                    }
                }
            } catch (NoSuchAlgorithmException nsae) {
                throw new CertPathValidatorException(nsae);
            }
        } catch (InvalidAlgorithmParameterException iape2) {
            throw new RuntimeException(iape2);
        }
    }

    @Override // java.security.cert.PKIXCertPathChecker, java.security.cert.PKIXRevocationChecker, java.security.cert.PKIXRevocationChecker
    public RevocationChecker clone() {
        RevocationChecker copy = (RevocationChecker) super.clone();
        copy.softFailExceptions = new LinkedList<>(this.softFailExceptions);
        return copy;
    }

    /* access modifiers changed from: private */
    public static class RejectKeySelector extends X509CertSelector {
        private final Set<PublicKey> badKeySet;

        RejectKeySelector(Set<PublicKey> badPublicKeys) {
            this.badKeySet = badPublicKeys;
        }

        @Override // java.security.cert.X509CertSelector, java.security.cert.CertSelector
        public boolean match(Certificate cert) {
            if (!super.match(cert)) {
                return false;
            }
            if (this.badKeySet.contains(cert.getPublicKey())) {
                if (RevocationChecker.debug != null) {
                    RevocationChecker.debug.println("RejectKeySelector.match: bad key");
                }
                return false;
            } else if (RevocationChecker.debug == null) {
                return true;
            } else {
                RevocationChecker.debug.println("RejectKeySelector.match: returning true");
                return true;
            }
        }

        @Override // java.security.cert.X509CertSelector
        public String toString() {
            return "RejectKeySelector: [\n" + super.toString() + ((Object) this.badKeySet) + "]";
        }
    }
}
