package com.android.org.bouncycastle.jce.provider;

import com.android.org.bouncycastle.asn1.x500.X500Name;
import com.android.org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import com.android.org.bouncycastle.asn1.x509.Extension;
import com.android.org.bouncycastle.asn1.x509.TBSCertificate;
import com.android.org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import com.android.org.bouncycastle.jcajce.PKIXExtendedParameters;
import com.android.org.bouncycastle.jcajce.util.BCJcaJceHelper;
import com.android.org.bouncycastle.jcajce.util.JcaJceHelper;
import com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import com.android.org.bouncycastle.x509.ExtendedPKIXParameters;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertPathValidatorResult;
import java.security.cert.CertPathValidatorSpi;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXCertPathValidatorResult;
import java.security.cert.PKIXParameters;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PKIXCertPathValidatorSpi extends CertPathValidatorSpi {
    private final JcaJceHelper helper = new BCJcaJceHelper();

    private static class NoPreloadHolder {
        private static final CertBlacklist blacklist = new CertBlacklist();

        private NoPreloadHolder() {
        }
    }

    /* JADX INFO: Multiple debug info for r0v56 com.android.org.bouncycastle.jcajce.PKIXExtendedParameters: [D('paramsPKIXBldr' com.android.org.bouncycastle.jcajce.PKIXExtendedParameters$Builder), D('paramsPKIX' com.android.org.bouncycastle.jcajce.PKIXExtendedParameters)] */
    @Override // java.security.cert.CertPathValidatorSpi
    public CertPathValidatorResult engineValidate(CertPath certPath, CertPathParameters params) throws CertPathValidatorException, InvalidAlgorithmParameterException {
        PKIXExtendedParameters paramsPKIX;
        List certs;
        CertPath certPath2;
        AnnotatedException e;
        int explicitPolicy;
        int inhibitAnyPolicy;
        int policyMapping;
        CertPath certPath3;
        IllegalArgumentException ex;
        PublicKey workingPublicKey;
        X500Name workingIssuerName;
        Set criticalExtensions;
        X509Certificate cert;
        int policyMapping2;
        List pathCheckers;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi;
        List[] policyNodes;
        PublicKey workingPublicKey2;
        int inhibitAnyPolicy2;
        int inhibitAnyPolicy3;
        int policyMapping3;
        Set criticalExtensions2;
        CertPathValidatorException e2;
        PKIXCertPathValidatorSpi pKIXCertPathValidatorSpi2 = this;
        CertPath certPath4 = certPath;
        if (params instanceof PKIXParameters) {
            PKIXExtendedParameters.Builder paramsPKIXBldr = new PKIXExtendedParameters.Builder((PKIXParameters) params);
            if (params instanceof ExtendedPKIXParameters) {
                ExtendedPKIXParameters extPKIX = (ExtendedPKIXParameters) params;
                paramsPKIXBldr.setUseDeltasEnabled(extPKIX.isUseDeltasEnabled());
                paramsPKIXBldr.setValidityModel(extPKIX.getValidityModel());
            }
            paramsPKIX = paramsPKIXBldr.build();
        } else if (params instanceof PKIXExtendedBuilderParameters) {
            paramsPKIX = ((PKIXExtendedBuilderParameters) params).getBaseParameters();
        } else if (params instanceof PKIXExtendedParameters) {
            paramsPKIX = (PKIXExtendedParameters) params;
        } else {
            throw new InvalidAlgorithmParameterException("Parameters must be a " + PKIXParameters.class.getName() + " instance.");
        }
        if (paramsPKIX.getTrustAnchors() != null) {
            List pathCheckers2 = certPath.getCertificates();
            int n = pathCheckers2.size();
            Throwable th = null;
            if (!pathCheckers2.isEmpty()) {
                X509Certificate cert2 = (X509Certificate) pathCheckers2.get(0);
                if (cert2 != null) {
                    BigInteger serial = cert2.getSerialNumber();
                    if (NoPreloadHolder.blacklist.isSerialNumberBlackListed(serial)) {
                        String message = "Certificate revocation of serial 0x" + serial.toString(16);
                        System.out.println(message);
                        AnnotatedException e3 = new AnnotatedException(message);
                        throw new CertPathValidatorException(e3.getMessage(), e3, certPath4, 0);
                    }
                }
                Set userInitialPolicySet = paramsPKIX.getInitialPolicies();
                try {
                    TrustAnchor trust = CertPathValidatorUtilities.findTrustAnchor((X509Certificate) pathCheckers2.get(pathCheckers2.size() - 1), paramsPKIX.getTrustAnchors(), paramsPKIX.getSigProvider());
                    if (trust != null) {
                        checkCertificate(trust.getTrustedCert());
                        PKIXExtendedParameters paramsPKIX2 = new PKIXExtendedParameters.Builder(paramsPKIX).setTrustAnchor(trust).build();
                        List[] policyNodes2 = new ArrayList[(n + 1)];
                        for (int j = 0; j < policyNodes2.length; j++) {
                            policyNodes2[j] = new ArrayList();
                        }
                        Set policySet = new HashSet();
                        policySet.add(RFC3280CertPathUtilities.ANY_POLICY);
                        PKIXPolicyNode validPolicyTree = new PKIXPolicyNode(new ArrayList(), 0, policySet, null, new HashSet(), RFC3280CertPathUtilities.ANY_POLICY, false);
                        policyNodes2[0].add(validPolicyTree);
                        PKIXNameConstraintValidator nameConstraintValidator = new PKIXNameConstraintValidator();
                        Set acceptablePolicies = new HashSet();
                        if (paramsPKIX2.isExplicitPolicyRequired()) {
                            explicitPolicy = 0;
                        } else {
                            explicitPolicy = n + 1;
                        }
                        if (paramsPKIX2.isAnyPolicyInhibited()) {
                            inhibitAnyPolicy = 0;
                        } else {
                            inhibitAnyPolicy = n + 1;
                        }
                        if (paramsPKIX2.isPolicyMappingInhibited()) {
                            policyMapping = 0;
                        } else {
                            policyMapping = n + 1;
                        }
                        X509Certificate sign = trust.getTrustedCert();
                        if (sign != null) {
                            try {
                                workingIssuerName = PrincipalUtils.getSubjectPrincipal(sign);
                                workingPublicKey = sign.getPublicKey();
                            } catch (IllegalArgumentException e4) {
                                ex = e4;
                                certPath3 = certPath4;
                            }
                        } else {
                            try {
                                workingIssuerName = PrincipalUtils.getCA(trust);
                                workingPublicKey = trust.getCAPublicKey();
                            } catch (IllegalArgumentException e5) {
                                ex = e5;
                                certPath3 = certPath4;
                                throw new ExtCertPathValidatorException("Subject of trust anchor could not be (re)encoded.", ex, certPath3, -1);
                            }
                        }
                        try {
                            AlgorithmIdentifier workingAlgId = CertPathValidatorUtilities.getAlgorithmIdentifier(workingPublicKey);
                            workingAlgId.getAlgorithm();
                            workingAlgId.getParameters();
                            if (paramsPKIX2.getTargetConstraints() != null) {
                                if (!paramsPKIX2.getTargetConstraints().match((Certificate) ((X509Certificate) pathCheckers2.get(0)))) {
                                    throw new ExtCertPathValidatorException("Target certificate in certification path does not match targetConstraints.", null, certPath4, 0);
                                }
                            }
                            List<PKIXCertPathChecker> pathCheckers3 = paramsPKIX2.getCertPathCheckers();
                            for (PKIXCertPathChecker pKIXCertPathChecker : pathCheckers3) {
                                pKIXCertPathChecker.init(false);
                            }
                            X509Certificate cert3 = null;
                            int policyMapping4 = policyMapping;
                            int maxPathLength = n;
                            Set acceptablePolicies2 = acceptablePolicies;
                            PublicKey workingPublicKey3 = workingPublicKey;
                            int maxPathLength2 = inhibitAnyPolicy;
                            int explicitPolicy2 = explicitPolicy;
                            PKIXPolicyNode validPolicyTree2 = validPolicyTree;
                            int index = pathCheckers2.size() - 1;
                            while (index >= 0) {
                                if (!NoPreloadHolder.blacklist.isPublicKeyBlackListed(workingPublicKey3)) {
                                    int i = n - index;
                                    X509Certificate cert4 = (X509Certificate) pathCheckers2.get(index);
                                    boolean verificationAlreadyPerformed = index == pathCheckers2.size() + -1;
                                    try {
                                        checkCertificate(cert4);
                                        RFC3280CertPathUtilities.processCertA(certPath, paramsPKIX2, index, workingPublicKey3, verificationAlreadyPerformed, workingIssuerName, sign, pKIXCertPathValidatorSpi2.helper);
                                        RFC3280CertPathUtilities.processCertBC(certPath4, index, nameConstraintValidator);
                                        PKIXPolicyNode validPolicyTree3 = RFC3280CertPathUtilities.processCertE(certPath4, index, RFC3280CertPathUtilities.processCertD(certPath, index, acceptablePolicies2, validPolicyTree2, policyNodes2, maxPathLength2));
                                        RFC3280CertPathUtilities.processCertF(certPath4, index, validPolicyTree3, explicitPolicy2);
                                        if (i != n) {
                                            if (cert4 != null) {
                                                if (cert4.getVersion() == 1) {
                                                    if (i != 1 || !cert4.equals(trust.getTrustedCert())) {
                                                        throw new CertPathValidatorException("Version 1 certificates can't be used as CA ones.", th, certPath4, index);
                                                    }
                                                    inhibitAnyPolicy3 = maxPathLength2;
                                                    policyMapping3 = policyMapping4;
                                                    inhibitAnyPolicy2 = maxPathLength;
                                                    policyNodes = policyNodes2;
                                                    pathCheckers = pathCheckers3;
                                                    pKIXCertPathValidatorSpi = this;
                                                    cert = cert4;
                                                }
                                            }
                                            RFC3280CertPathUtilities.prepareNextCertA(certPath4, index);
                                            policyNodes = policyNodes2;
                                            PKIXPolicyNode validPolicyTree4 = RFC3280CertPathUtilities.prepareCertB(certPath4, index, policyNodes, validPolicyTree3, policyMapping4);
                                            RFC3280CertPathUtilities.prepareNextCertG(certPath4, index, nameConstraintValidator);
                                            int explicitPolicy3 = RFC3280CertPathUtilities.prepareNextCertH1(certPath4, index, explicitPolicy2);
                                            int policyMapping5 = RFC3280CertPathUtilities.prepareNextCertH2(certPath4, index, policyMapping4);
                                            int inhibitAnyPolicy4 = RFC3280CertPathUtilities.prepareNextCertH3(certPath4, index, maxPathLength2);
                                            int explicitPolicy4 = RFC3280CertPathUtilities.prepareNextCertI1(certPath4, index, explicitPolicy3);
                                            int policyMapping6 = RFC3280CertPathUtilities.prepareNextCertI2(certPath4, index, policyMapping5);
                                            maxPathLength2 = RFC3280CertPathUtilities.prepareNextCertJ(certPath4, index, inhibitAnyPolicy4);
                                            RFC3280CertPathUtilities.prepareNextCertK(certPath4, index);
                                            int maxPathLength3 = RFC3280CertPathUtilities.prepareNextCertM(certPath4, index, RFC3280CertPathUtilities.prepareNextCertL(certPath4, index, maxPathLength));
                                            RFC3280CertPathUtilities.prepareNextCertN(certPath4, index);
                                            Set criticalExtensions3 = cert4.getCriticalExtensionOIDs();
                                            if (criticalExtensions3 != null) {
                                                Set criticalExtensions4 = new HashSet(criticalExtensions3);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                                criticalExtensions4.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                                criticalExtensions2 = criticalExtensions4;
                                            } else {
                                                criticalExtensions2 = new HashSet();
                                            }
                                            pathCheckers = pathCheckers3;
                                            RFC3280CertPathUtilities.prepareNextCertO(certPath4, index, criticalExtensions2, pathCheckers);
                                            sign = cert4;
                                            workingIssuerName = PrincipalUtils.getSubjectPrincipal(sign);
                                            try {
                                                policyMapping2 = policyMapping6;
                                                pKIXCertPathValidatorSpi = this;
                                                try {
                                                    workingPublicKey2 = CertPathValidatorUtilities.getNextWorkingKey(certPath.getCertificates(), index, pKIXCertPathValidatorSpi.helper);
                                                    AlgorithmIdentifier workingAlgId2 = CertPathValidatorUtilities.getAlgorithmIdentifier(workingPublicKey2);
                                                    workingAlgId2.getAlgorithm();
                                                    workingAlgId2.getParameters();
                                                    cert = cert4;
                                                    maxPathLength = maxPathLength3;
                                                    explicitPolicy2 = explicitPolicy4;
                                                    validPolicyTree2 = validPolicyTree4;
                                                    certPath4 = certPath4;
                                                    nameConstraintValidator = nameConstraintValidator;
                                                    acceptablePolicies2 = acceptablePolicies2;
                                                    pathCheckers3 = pathCheckers;
                                                    pathCheckers2 = pathCheckers2;
                                                    policySet = policySet;
                                                    trust = trust;
                                                    workingPublicKey3 = workingPublicKey2;
                                                    index--;
                                                    pKIXCertPathValidatorSpi2 = pKIXCertPathValidatorSpi;
                                                    cert3 = cert;
                                                    n = n;
                                                    policyNodes2 = policyNodes;
                                                    policyMapping4 = policyMapping2;
                                                    th = null;
                                                } catch (CertPathValidatorException e6) {
                                                    e2 = e6;
                                                    throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath4, index);
                                                }
                                            } catch (CertPathValidatorException e7) {
                                                e2 = e7;
                                                throw new CertPathValidatorException("Next working key could not be retrieved.", e2, certPath4, index);
                                            }
                                        } else {
                                            inhibitAnyPolicy3 = maxPathLength2;
                                            policyMapping3 = policyMapping4;
                                            inhibitAnyPolicy2 = maxPathLength;
                                            policyNodes = policyNodes2;
                                            pathCheckers = pathCheckers3;
                                            pKIXCertPathValidatorSpi = this;
                                            cert = cert4;
                                        }
                                        validPolicyTree2 = validPolicyTree3;
                                        workingPublicKey2 = workingPublicKey3;
                                        policyMapping2 = policyMapping3;
                                        maxPathLength = inhibitAnyPolicy2;
                                        explicitPolicy2 = explicitPolicy2;
                                        maxPathLength2 = inhibitAnyPolicy3;
                                        certPath4 = certPath4;
                                        nameConstraintValidator = nameConstraintValidator;
                                        acceptablePolicies2 = acceptablePolicies2;
                                        pathCheckers3 = pathCheckers;
                                        pathCheckers2 = pathCheckers2;
                                        policySet = policySet;
                                        trust = trust;
                                        workingPublicKey3 = workingPublicKey2;
                                        index--;
                                        pKIXCertPathValidatorSpi2 = pKIXCertPathValidatorSpi;
                                        cert3 = cert;
                                        n = n;
                                        policyNodes2 = policyNodes;
                                        policyMapping4 = policyMapping2;
                                        th = null;
                                    } catch (AnnotatedException e8) {
                                        throw new CertPathValidatorException(e8.getMessage(), e8.getUnderlyingException(), certPath4, index);
                                    }
                                } else {
                                    String message2 = "Certificate revocation of public key " + workingPublicKey3;
                                    System.out.println(message2);
                                    AnnotatedException e9 = new AnnotatedException(message2);
                                    throw new CertPathValidatorException(e9.getMessage(), e9, certPath4, index);
                                }
                            }
                            int explicitPolicy5 = RFC3280CertPathUtilities.wrapupCertB(certPath4, index + 1, RFC3280CertPathUtilities.wrapupCertA(explicitPolicy2, cert3));
                            Set criticalExtensions5 = cert3.getCriticalExtensionOIDs();
                            if (criticalExtensions5 != null) {
                                Set criticalExtensions6 = new HashSet(criticalExtensions5);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.KEY_USAGE);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.CERTIFICATE_POLICIES);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.POLICY_MAPPINGS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.INHIBIT_ANY_POLICY);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.POLICY_CONSTRAINTS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.BASIC_CONSTRAINTS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.SUBJECT_ALTERNATIVE_NAME);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.NAME_CONSTRAINTS);
                                criticalExtensions6.remove(RFC3280CertPathUtilities.CRL_DISTRIBUTION_POINTS);
                                criticalExtensions6.remove(Extension.extendedKeyUsage.getId());
                                criticalExtensions = criticalExtensions6;
                            } else {
                                criticalExtensions = new HashSet();
                            }
                            RFC3280CertPathUtilities.wrapupCertF(certPath4, index + 1, pathCheckers3, criticalExtensions);
                            PKIXPolicyNode intersection = RFC3280CertPathUtilities.wrapupCertG(certPath, paramsPKIX2, userInitialPolicySet, index + 1, policyNodes2, validPolicyTree2, acceptablePolicies2);
                            if (explicitPolicy5 > 0 || intersection != null) {
                                return new PKIXCertPathValidatorResult(trust, intersection, cert3.getPublicKey());
                            }
                            throw new CertPathValidatorException("Path processing failed on policy.", null, certPath4, index);
                        } catch (CertPathValidatorException e10) {
                            throw new ExtCertPathValidatorException("Algorithm identifier of public key of trust anchor could not be read.", e10, certPath4, -1);
                        }
                    } else {
                        certs = pathCheckers2;
                        certPath2 = certPath4;
                        try {
                            throw new CertPathValidatorException("Trust anchor for certification path not found.", null, certPath2, -1);
                        } catch (AnnotatedException e11) {
                            e = e11;
                            throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath2, certs.size() - 1);
                        }
                    }
                } catch (AnnotatedException e12) {
                    e = e12;
                    certs = pathCheckers2;
                    certPath2 = certPath4;
                    throw new CertPathValidatorException(e.getMessage(), e.getUnderlyingException(), certPath2, certs.size() - 1);
                }
            } else {
                throw new CertPathValidatorException("Certification path is empty.", null, certPath4, -1);
            }
        } else {
            throw new InvalidAlgorithmParameterException("trustAnchors is null, this is not allowed for certification path validation.");
        }
    }

    static void checkCertificate(X509Certificate cert) throws AnnotatedException {
        try {
            TBSCertificate.getInstance(cert.getTBSCertificate());
        } catch (CertificateEncodingException e) {
            throw new AnnotatedException("unable to process TBSCertificate", e);
        } catch (IllegalArgumentException e2) {
            throw new AnnotatedException(e2.getMessage());
        }
    }
}
