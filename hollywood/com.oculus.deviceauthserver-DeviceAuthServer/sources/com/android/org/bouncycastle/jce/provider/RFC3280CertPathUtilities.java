package com.android.org.bouncycastle.jce.provider;

import com.android.org.bouncycastle.asn1.ASN1Encodable;
import com.android.org.bouncycastle.asn1.ASN1EncodableVector;
import com.android.org.bouncycastle.asn1.ASN1Integer;
import com.android.org.bouncycastle.asn1.ASN1ObjectIdentifier;
import com.android.org.bouncycastle.asn1.ASN1Primitive;
import com.android.org.bouncycastle.asn1.ASN1Sequence;
import com.android.org.bouncycastle.asn1.ASN1String;
import com.android.org.bouncycastle.asn1.ASN1TaggedObject;
import com.android.org.bouncycastle.asn1.DERSequence;
import com.android.org.bouncycastle.asn1.x500.RDN;
import com.android.org.bouncycastle.asn1.x500.X500Name;
import com.android.org.bouncycastle.asn1.x500.style.BCStyle;
import com.android.org.bouncycastle.asn1.x509.BasicConstraints;
import com.android.org.bouncycastle.asn1.x509.CRLDistPoint;
import com.android.org.bouncycastle.asn1.x509.DistributionPoint;
import com.android.org.bouncycastle.asn1.x509.DistributionPointName;
import com.android.org.bouncycastle.asn1.x509.Extension;
import com.android.org.bouncycastle.asn1.x509.GeneralName;
import com.android.org.bouncycastle.asn1.x509.GeneralNames;
import com.android.org.bouncycastle.asn1.x509.GeneralSubtree;
import com.android.org.bouncycastle.asn1.x509.IssuingDistributionPoint;
import com.android.org.bouncycastle.asn1.x509.NameConstraints;
import com.android.org.bouncycastle.asn1.x509.PolicyInformation;
import com.android.org.bouncycastle.jcajce.PKIXCRLStoreSelector;
import com.android.org.bouncycastle.jcajce.PKIXCertStoreSelector;
import com.android.org.bouncycastle.jcajce.PKIXExtendedBuilderParameters;
import com.android.org.bouncycastle.jcajce.PKIXExtendedParameters;
import com.android.org.bouncycastle.jcajce.util.JcaJceHelper;
import com.android.org.bouncycastle.jce.exception.ExtCertPathValidatorException;
import com.android.org.bouncycastle.util.Arrays;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLSelector;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.security.cert.X509Extension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class RFC3280CertPathUtilities {
    public static final String ANY_POLICY = "2.5.29.32.0";
    public static final String AUTHORITY_KEY_IDENTIFIER = Extension.authorityKeyIdentifier.getId();
    public static final String BASIC_CONSTRAINTS = Extension.basicConstraints.getId();
    public static final String CERTIFICATE_POLICIES = Extension.certificatePolicies.getId();
    public static final String CRL_DISTRIBUTION_POINTS = Extension.cRLDistributionPoints.getId();
    public static final String CRL_NUMBER = Extension.cRLNumber.getId();
    protected static final int CRL_SIGN = 6;
    private static final PKIXCRLUtil CRL_UTIL = new PKIXCRLUtil();
    public static final String DELTA_CRL_INDICATOR = Extension.deltaCRLIndicator.getId();
    public static final String FRESHEST_CRL = Extension.freshestCRL.getId();
    public static final String INHIBIT_ANY_POLICY = Extension.inhibitAnyPolicy.getId();
    public static final String ISSUING_DISTRIBUTION_POINT = Extension.issuingDistributionPoint.getId();
    protected static final int KEY_CERT_SIGN = 5;
    public static final String KEY_USAGE = Extension.keyUsage.getId();
    public static final String NAME_CONSTRAINTS = Extension.nameConstraints.getId();
    public static final String POLICY_CONSTRAINTS = Extension.policyConstraints.getId();
    public static final String POLICY_MAPPINGS = Extension.policyMappings.getId();
    public static final String SUBJECT_ALTERNATIVE_NAME = Extension.subjectAlternativeName.getId();
    protected static final String[] crlReasons = {"unspecified", "keyCompromise", "cACompromise", "affiliationChanged", "superseded", "cessationOfOperation", "certificateHold", "unknown", "removeFromCRL", "privilegeWithdrawn", "aACompromise"};

    RFC3280CertPathUtilities() {
    }

    protected static void processCRLB2(DistributionPoint dp, Object cert, X509CRL crl) throws AnnotatedException {
        GeneralName[] genNames;
        try {
            IssuingDistributionPoint idp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(crl, ISSUING_DISTRIBUTION_POINT));
            if (idp != null) {
                if (idp.getDistributionPoint() != null) {
                    DistributionPointName dpName = IssuingDistributionPoint.getInstance(idp).getDistributionPoint();
                    List names = new ArrayList();
                    if (dpName.getType() == 0) {
                        for (GeneralName generalName : GeneralNames.getInstance(dpName.getName()).getNames()) {
                            names.add(generalName);
                        }
                    }
                    if (dpName.getType() == 1) {
                        ASN1EncodableVector vec = new ASN1EncodableVector();
                        try {
                            Enumeration e = ASN1Sequence.getInstance(PrincipalUtils.getIssuerPrincipal(crl)).getObjects();
                            while (e.hasMoreElements()) {
                                vec.add((ASN1Encodable) e.nextElement());
                            }
                            vec.add(dpName.getName());
                            names.add(new GeneralName(X500Name.getInstance(new DERSequence(vec))));
                        } catch (Exception e2) {
                            throw new AnnotatedException("Could not read CRL issuer.", e2);
                        }
                    }
                    boolean matches = false;
                    if (dp.getDistributionPoint() != null) {
                        DistributionPointName dpName2 = dp.getDistributionPoint();
                        GeneralName[] genNames2 = null;
                        if (dpName2.getType() == 0) {
                            genNames2 = GeneralNames.getInstance(dpName2.getName()).getNames();
                        }
                        if (dpName2.getType() == 1) {
                            if (dp.getCRLIssuer() != null) {
                                genNames2 = dp.getCRLIssuer().getNames();
                            } else {
                                GeneralName[] genNames3 = new GeneralName[1];
                                try {
                                    genNames3[0] = new GeneralName(X500Name.getInstance(PrincipalUtils.getEncodedIssuerPrincipal(cert).getEncoded()));
                                    genNames2 = genNames3;
                                } catch (Exception e3) {
                                    throw new AnnotatedException("Could not read certificate issuer.", e3);
                                }
                            }
                            for (int j = 0; j < genNames2.length; j++) {
                                Enumeration e4 = ASN1Sequence.getInstance(genNames2[j].getName().toASN1Primitive()).getObjects();
                                ASN1EncodableVector vec2 = new ASN1EncodableVector();
                                while (e4.hasMoreElements()) {
                                    vec2.add((ASN1Encodable) e4.nextElement());
                                }
                                vec2.add(dpName2.getName());
                                genNames2[j] = new GeneralName(X500Name.getInstance(new DERSequence(vec2)));
                            }
                        }
                        if (genNames2 != null) {
                            int j2 = 0;
                            while (true) {
                                if (j2 >= genNames2.length) {
                                    break;
                                } else if (names.contains(genNames2[j2])) {
                                    matches = true;
                                    break;
                                } else {
                                    j2++;
                                }
                            }
                        }
                        if (!matches) {
                            throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                        }
                    } else if (dp.getCRLIssuer() != null) {
                        GeneralName[] genNames4 = dp.getCRLIssuer().getNames();
                        int j3 = 0;
                        while (true) {
                            if (j3 >= genNames4.length) {
                                break;
                            } else if (names.contains(genNames4[j3])) {
                                matches = true;
                                break;
                            } else {
                                j3++;
                            }
                        }
                        if (!matches) {
                            throw new AnnotatedException("No match for certificate CRL issuing distribution point name to cRLIssuer CRL distribution point.");
                        }
                    } else {
                        throw new AnnotatedException("Either the cRLIssuer or the distributionPoint field must be contained in DistributionPoint.");
                    }
                }
                try {
                    BasicConstraints bc = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Extension) cert, BASIC_CONSTRAINTS));
                    if (cert instanceof X509Certificate) {
                        if (idp.onlyContainsUserCerts() && bc != null && bc.isCA()) {
                            throw new AnnotatedException("CA Cert CRL only contains user certificates.");
                        } else if (idp.onlyContainsCACerts() && (bc == null || !bc.isCA())) {
                            throw new AnnotatedException("End CRL only contains CA certificates.");
                        }
                    }
                    if (idp.onlyContainsAttributeCerts()) {
                        throw new AnnotatedException("onlyContainsAttributeCerts boolean is asserted.");
                    }
                } catch (Exception e5) {
                    throw new AnnotatedException("Basic constraints extension could not be decoded.", e5);
                }
            }
        } catch (Exception e6) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e6);
        }
    }

    protected static void processCRLB1(DistributionPoint dp, Object cert, X509CRL crl) throws AnnotatedException {
        ASN1Primitive idp = CertPathValidatorUtilities.getExtensionValue(crl, ISSUING_DISTRIBUTION_POINT);
        boolean isIndirect = false;
        if (idp != null && IssuingDistributionPoint.getInstance(idp).isIndirectCRL()) {
            isIndirect = true;
        }
        try {
            byte[] issuerBytes = PrincipalUtils.getIssuerPrincipal(crl).getEncoded();
            boolean matchIssuer = false;
            if (dp.getCRLIssuer() != null) {
                GeneralName[] genNames = dp.getCRLIssuer().getNames();
                for (int j = 0; j < genNames.length; j++) {
                    if (genNames[j].getTagNo() == 4) {
                        try {
                            if (Arrays.areEqual(genNames[j].getName().toASN1Primitive().getEncoded(), issuerBytes)) {
                                matchIssuer = true;
                            }
                        } catch (IOException e) {
                            throw new AnnotatedException("CRL issuer information from distribution point cannot be decoded.", e);
                        }
                    }
                }
                if (matchIssuer && !isIndirect) {
                    throw new AnnotatedException("Distribution point contains cRLIssuer field but CRL is not indirect.");
                } else if (!matchIssuer) {
                    throw new AnnotatedException("CRL issuer of CRL does not match CRL issuer of distribution point.");
                }
            } else if (PrincipalUtils.getIssuerPrincipal(crl).equals(PrincipalUtils.getEncodedIssuerPrincipal(cert))) {
                matchIssuer = true;
            }
            if (!matchIssuer) {
                throw new AnnotatedException("Cannot find matching CRL issuer for certificate.");
            }
        } catch (IOException e2) {
            throw new AnnotatedException("Exception encoding CRL issuer: " + e2.getMessage(), e2);
        }
    }

    protected static ReasonsMask processCRLD(X509CRL crl, DistributionPoint dp) throws AnnotatedException {
        ReasonsMask reasonsMask;
        ReasonsMask reasonsMask2;
        try {
            IssuingDistributionPoint idp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(crl, ISSUING_DISTRIBUTION_POINT));
            if (idp != null && idp.getOnlySomeReasons() != null && dp.getReasons() != null) {
                return new ReasonsMask(dp.getReasons()).intersect(new ReasonsMask(idp.getOnlySomeReasons()));
            }
            if ((idp == null || idp.getOnlySomeReasons() == null) && dp.getReasons() == null) {
                return ReasonsMask.allReasons;
            }
            if (dp.getReasons() == null) {
                reasonsMask = ReasonsMask.allReasons;
            } else {
                reasonsMask = new ReasonsMask(dp.getReasons());
            }
            if (idp == null) {
                reasonsMask2 = ReasonsMask.allReasons;
            } else {
                reasonsMask2 = new ReasonsMask(idp.getOnlySomeReasons());
            }
            return reasonsMask.intersect(reasonsMask2);
        } catch (Exception e) {
            throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e);
        }
    }

    protected static Set processCRLF(X509CRL crl, Object cert, X509Certificate defaultCRLSignCert, PublicKey defaultCRLSignKey, PKIXExtendedParameters paramsPKIX, List certPathCerts, JcaJceHelper helper) throws AnnotatedException {
        CertPathBuilderException e;
        CertPathValidatorException e2;
        Exception e3;
        List certs;
        X509Certificate x509Certificate = defaultCRLSignCert;
        X509CertSelector certSelector = new X509CertSelector();
        try {
            certSelector.setSubject(PrincipalUtils.getIssuerPrincipal(crl).getEncoded());
            PKIXCertStoreSelector selector = new PKIXCertStoreSelector.Builder(certSelector).build();
            try {
                Collection<X509Certificate> coll = CertPathValidatorUtilities.findCertificates(selector, paramsPKIX.getCertificateStores());
                coll.addAll(CertPathValidatorUtilities.findCertificates(selector, paramsPKIX.getCertStores()));
                coll.add(x509Certificate);
                List validCerts = new ArrayList();
                List validKeys = new ArrayList();
                for (X509Certificate signingCert : coll) {
                    if (signingCert.equals(x509Certificate)) {
                        validCerts.add(signingCert);
                        validKeys.add(defaultCRLSignKey);
                    } else {
                        try {
                            PKIXCertPathBuilderSpi builder = new PKIXCertPathBuilderSpi();
                            X509CertSelector tmpCertSelector = new X509CertSelector();
                            tmpCertSelector.setCertificate(signingCert);
                            try {
                                PKIXExtendedParameters.Builder paramsBuilder = new PKIXExtendedParameters.Builder(paramsPKIX).setTargetConstraints(new PKIXCertStoreSelector.Builder(tmpCertSelector).build());
                                try {
                                    if (certPathCerts.contains(signingCert)) {
                                        paramsBuilder.setRevocationEnabled(false);
                                    } else {
                                        paramsBuilder.setRevocationEnabled(true);
                                    }
                                    certs = builder.engineBuild(new PKIXExtendedBuilderParameters.Builder(paramsBuilder.build()).build()).getCertPath().getCertificates();
                                    validCerts.add(signingCert);
                                } catch (CertPathBuilderException e4) {
                                    e = e4;
                                    throw new AnnotatedException("CertPath for CRL signer failed to validate.", e);
                                } catch (CertPathValidatorException e5) {
                                    e2 = e5;
                                    throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e2);
                                } catch (Exception e6) {
                                    e3 = e6;
                                    throw new AnnotatedException(e3.getMessage());
                                }
                                try {
                                    validKeys.add(CertPathValidatorUtilities.getNextWorkingKey(certs, 0, helper));
                                    x509Certificate = defaultCRLSignCert;
                                } catch (CertPathBuilderException e7) {
                                    e = e7;
                                    throw new AnnotatedException("CertPath for CRL signer failed to validate.", e);
                                } catch (CertPathValidatorException e8) {
                                    e2 = e8;
                                    throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e2);
                                } catch (Exception e9) {
                                    e3 = e9;
                                    throw new AnnotatedException(e3.getMessage());
                                }
                            } catch (CertPathBuilderException e10) {
                                e = e10;
                                throw new AnnotatedException("CertPath for CRL signer failed to validate.", e);
                            } catch (CertPathValidatorException e11) {
                                e2 = e11;
                                throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e2);
                            } catch (Exception e12) {
                                e3 = e12;
                                throw new AnnotatedException(e3.getMessage());
                            }
                        } catch (CertPathBuilderException e13) {
                            e = e13;
                            throw new AnnotatedException("CertPath for CRL signer failed to validate.", e);
                        } catch (CertPathValidatorException e14) {
                            e2 = e14;
                            throw new AnnotatedException("Public key of issuer certificate of CRL could not be retrieved.", e2);
                        } catch (Exception e15) {
                            e3 = e15;
                            throw new AnnotatedException(e3.getMessage());
                        }
                    }
                }
                Set checkKeys = new HashSet();
                AnnotatedException lastException = null;
                for (int i = 0; i < validCerts.size(); i++) {
                    boolean[] keyusage = ((X509Certificate) validCerts.get(i)).getKeyUsage();
                    if (keyusage == null || (keyusage.length >= 7 && keyusage[6])) {
                        checkKeys.add(validKeys.get(i));
                    } else {
                        lastException = new AnnotatedException("Issuer certificate key usage extension does not permit CRL signing.");
                    }
                }
                if (checkKeys.isEmpty() && lastException == null) {
                    throw new AnnotatedException("Cannot find a valid issuer certificate.");
                } else if (!checkKeys.isEmpty() || lastException == null) {
                    return checkKeys;
                } else {
                    throw lastException;
                }
            } catch (AnnotatedException e16) {
                throw new AnnotatedException("Issuer certificate for CRL cannot be searched.", e16);
            }
        } catch (IOException e17) {
            throw new AnnotatedException("Subject criteria for certificate selector to find issuer certificate for CRL could not be set.", e17);
        }
    }

    protected static PublicKey processCRLG(X509CRL crl, Set keys) throws AnnotatedException {
        Exception lastException = null;
        Iterator it = keys.iterator();
        while (it.hasNext()) {
            PublicKey key = (PublicKey) it.next();
            try {
                crl.verify(key);
                return key;
            } catch (Exception e) {
                lastException = e;
            }
        }
        throw new AnnotatedException("Cannot verify CRL.", lastException);
    }

    protected static X509CRL processCRLH(Set deltacrls, PublicKey key) throws AnnotatedException {
        Exception lastException = null;
        Iterator it = deltacrls.iterator();
        while (it.hasNext()) {
            X509CRL crl = (X509CRL) it.next();
            try {
                crl.verify(key);
                return crl;
            } catch (Exception e) {
                lastException = e;
            }
        }
        if (lastException == null) {
            return null;
        }
        throw new AnnotatedException("Cannot verify delta CRL.", lastException);
    }

    protected static Set processCRLA1i(Date currentDate, PKIXExtendedParameters paramsPKIX, X509Certificate cert, X509CRL crl) throws AnnotatedException {
        Set set = new HashSet();
        if (paramsPKIX.isUseDeltasEnabled()) {
            try {
                CRLDistPoint freshestCRL = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, FRESHEST_CRL));
                if (freshestCRL == null) {
                    try {
                        freshestCRL = CRLDistPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(crl, FRESHEST_CRL));
                    } catch (AnnotatedException e) {
                        throw new AnnotatedException("Freshest CRL extension could not be decoded from CRL.", e);
                    }
                }
                if (freshestCRL != null) {
                    List crlStores = new ArrayList();
                    crlStores.addAll(paramsPKIX.getCRLStores());
                    try {
                        crlStores.addAll(CertPathValidatorUtilities.getAdditionalStoresFromCRLDistributionPoint(freshestCRL, paramsPKIX.getNamedCRLStoreMap()));
                        try {
                            set.addAll(CertPathValidatorUtilities.getDeltaCRLs(currentDate, crl, paramsPKIX.getCertStores(), crlStores));
                        } catch (AnnotatedException e2) {
                            throw new AnnotatedException("Exception obtaining delta CRLs.", e2);
                        }
                    } catch (AnnotatedException e3) {
                        throw new AnnotatedException("No new delta CRL locations could be added from Freshest CRL extension.", e3);
                    }
                }
            } catch (AnnotatedException e4) {
                throw new AnnotatedException("Freshest CRL extension could not be decoded from certificate.", e4);
            }
        }
        return set;
    }

    protected static Set[] processCRLA1ii(Date currentDate, PKIXExtendedParameters paramsPKIX, X509Certificate cert, X509CRL crl) throws AnnotatedException {
        Set deltaSet = new HashSet();
        X509CRLSelector crlselect = new X509CRLSelector();
        crlselect.setCertificateChecking(cert);
        try {
            crlselect.addIssuerName(PrincipalUtils.getIssuerPrincipal(crl).getEncoded());
            PKIXCRLStoreSelector extSelect = new PKIXCRLStoreSelector.Builder(crlselect).setCompleteCRLEnabled(true).build();
            Date validityDate = currentDate;
            if (paramsPKIX.getDate() != null) {
                validityDate = paramsPKIX.getDate();
            }
            Set completeSet = CRL_UTIL.findCRLs(extSelect, validityDate, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores());
            if (paramsPKIX.isUseDeltasEnabled()) {
                try {
                    deltaSet.addAll(CertPathValidatorUtilities.getDeltaCRLs(validityDate, crl, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores()));
                } catch (AnnotatedException e) {
                    throw new AnnotatedException("Exception obtaining delta CRLs.", e);
                }
            }
            return new Set[]{completeSet, deltaSet};
        } catch (IOException e2) {
            throw new AnnotatedException("Cannot extract issuer from CRL." + e2, e2);
        }
    }

    protected static void processCRLC(X509CRL deltaCRL, X509CRL completeCRL, PKIXExtendedParameters pkixParams) throws AnnotatedException {
        if (deltaCRL != null) {
            try {
                IssuingDistributionPoint completeidp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(completeCRL, ISSUING_DISTRIBUTION_POINT));
                if (!pkixParams.isUseDeltasEnabled()) {
                    return;
                }
                if (PrincipalUtils.getIssuerPrincipal(deltaCRL).equals(PrincipalUtils.getIssuerPrincipal(completeCRL))) {
                    try {
                        IssuingDistributionPoint deltaidp = IssuingDistributionPoint.getInstance(CertPathValidatorUtilities.getExtensionValue(deltaCRL, ISSUING_DISTRIBUTION_POINT));
                        boolean match = false;
                        if (completeidp == null) {
                            if (deltaidp == null) {
                                match = true;
                            }
                        } else if (completeidp.equals(deltaidp)) {
                            match = true;
                        }
                        if (match) {
                            try {
                                ASN1Primitive completeKeyIdentifier = CertPathValidatorUtilities.getExtensionValue(completeCRL, AUTHORITY_KEY_IDENTIFIER);
                                try {
                                    ASN1Primitive deltaKeyIdentifier = CertPathValidatorUtilities.getExtensionValue(deltaCRL, AUTHORITY_KEY_IDENTIFIER);
                                    if (completeKeyIdentifier == null) {
                                        throw new AnnotatedException("CRL authority key identifier is null.");
                                    } else if (deltaKeyIdentifier == null) {
                                        throw new AnnotatedException("Delta CRL authority key identifier is null.");
                                    } else if (!completeKeyIdentifier.equals(deltaKeyIdentifier)) {
                                        throw new AnnotatedException("Delta CRL authority key identifier does not match complete CRL authority key identifier.");
                                    }
                                } catch (AnnotatedException e) {
                                    throw new AnnotatedException("Authority key identifier extension could not be extracted from delta CRL.", e);
                                }
                            } catch (AnnotatedException e2) {
                                throw new AnnotatedException("Authority key identifier extension could not be extracted from complete CRL.", e2);
                            }
                        } else {
                            throw new AnnotatedException("Issuing distribution point extension from delta CRL and complete CRL does not match.");
                        }
                    } catch (Exception e3) {
                        throw new AnnotatedException("Issuing distribution point extension from delta CRL could not be decoded.", e3);
                    }
                } else {
                    throw new AnnotatedException("Complete CRL issuer does not match delta CRL issuer.");
                }
            } catch (Exception e4) {
                throw new AnnotatedException("Issuing distribution point extension could not be decoded.", e4);
            }
        }
    }

    protected static void processCRLI(Date validDate, X509CRL deltacrl, Object cert, CertStatus certStatus, PKIXExtendedParameters pkixParams) throws AnnotatedException {
        if (pkixParams.isUseDeltasEnabled() && deltacrl != null) {
            CertPathValidatorUtilities.getCertStatus(validDate, deltacrl, cert, certStatus);
        }
    }

    protected static void processCRLJ(Date validDate, X509CRL completecrl, Object cert, CertStatus certStatus) throws AnnotatedException {
        if (certStatus.getCertStatus() == 11) {
            CertPathValidatorUtilities.getCertStatus(validDate, completecrl, cert, certStatus);
        }
    }

    protected static PKIXPolicyNode prepareCertB(CertPath certPath, int index, List[] policyNodes, PKIXPolicyNode validPolicyTree, int policyMapping) throws CertPathValidatorException {
        ASN1Sequence mappings;
        Map m_idp;
        Set s_idp;
        boolean idp_found;
        Set pq;
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        int i = certs.size() - index;
        try {
            ASN1Sequence pm = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, POLICY_MAPPINGS));
            if (pm == null) {
                return validPolicyTree;
            }
            ASN1Sequence mappings2 = pm;
            Map m_idp2 = new HashMap();
            Set<String> s_idp2 = new HashSet();
            for (int j = 0; j < mappings2.size(); j++) {
                ASN1Sequence mapping = (ASN1Sequence) mappings2.getObjectAt(j);
                String id_p = ((ASN1ObjectIdentifier) mapping.getObjectAt(0)).getId();
                String sd_p = ((ASN1ObjectIdentifier) mapping.getObjectAt(1)).getId();
                if (!m_idp2.containsKey(id_p)) {
                    HashSet hashSet = new HashSet();
                    hashSet.add(sd_p);
                    m_idp2.put(id_p, hashSet);
                    s_idp2.add(id_p);
                } else {
                    ((Set) m_idp2.get(id_p)).add(sd_p);
                }
            }
            PKIXPolicyNode _validPolicyTree = validPolicyTree;
            for (String id_p2 : s_idp2) {
                if (policyMapping > 0) {
                    Iterator nodes_i = policyNodes[i].iterator();
                    while (true) {
                        if (!nodes_i.hasNext()) {
                            idp_found = false;
                            break;
                        }
                        PKIXPolicyNode node = (PKIXPolicyNode) nodes_i.next();
                        if (node.getValidPolicy().equals(id_p2)) {
                            node.expectedPolicies = (Set) m_idp2.get(id_p2);
                            idp_found = true;
                            break;
                        }
                    }
                    if (!idp_found) {
                        Iterator nodes_i2 = policyNodes[i].iterator();
                        while (true) {
                            if (!nodes_i2.hasNext()) {
                                s_idp = s_idp2;
                                m_idp = m_idp2;
                                mappings = mappings2;
                                break;
                            }
                            PKIXPolicyNode node2 = (PKIXPolicyNode) nodes_i2.next();
                            if (ANY_POLICY.equals(node2.getValidPolicy())) {
                                try {
                                    Enumeration e = ((ASN1Sequence) CertPathValidatorUtilities.getExtensionValue(cert, CERTIFICATE_POLICIES)).getObjects();
                                    while (true) {
                                        if (!e.hasMoreElements()) {
                                            pq = null;
                                            break;
                                        }
                                        try {
                                            PolicyInformation pinfo = PolicyInformation.getInstance(e.nextElement());
                                            if (ANY_POLICY.equals(pinfo.getPolicyIdentifier().getId())) {
                                                try {
                                                    pq = CertPathValidatorUtilities.getQualifierSet(pinfo.getPolicyQualifiers());
                                                    break;
                                                } catch (CertPathValidatorException ex) {
                                                    throw new ExtCertPathValidatorException("Policy qualifier info set could not be decoded.", ex, certPath, index);
                                                }
                                            }
                                        } catch (Exception ex2) {
                                            throw new CertPathValidatorException("Policy information could not be decoded.", ex2, certPath, index);
                                        }
                                    }
                                    boolean ci = cert.getCriticalExtensionOIDs() != null ? cert.getCriticalExtensionOIDs().contains(CERTIFICATE_POLICIES) : false;
                                    PKIXPolicyNode p_node = (PKIXPolicyNode) node2.getParent();
                                    if (ANY_POLICY.equals(p_node.getValidPolicy())) {
                                        s_idp = s_idp2;
                                        m_idp = m_idp2;
                                        mappings = mappings2;
                                        PKIXPolicyNode c_node = new PKIXPolicyNode(new ArrayList(), i, (Set) m_idp2.get(id_p2), p_node, pq, id_p2, ci);
                                        p_node.addChild(c_node);
                                        policyNodes[i].add(c_node);
                                    } else {
                                        s_idp = s_idp2;
                                        m_idp = m_idp2;
                                        mappings = mappings2;
                                    }
                                } catch (AnnotatedException e2) {
                                    throw new ExtCertPathValidatorException("Certificate policies extension could not be decoded.", e2, certPath, index);
                                }
                            }
                        }
                    } else {
                        s_idp = s_idp2;
                        m_idp = m_idp2;
                        mappings = mappings2;
                    }
                } else {
                    String id_p3 = id_p2;
                    s_idp = s_idp2;
                    m_idp = m_idp2;
                    mappings = mappings2;
                    if (policyMapping <= 0) {
                        Iterator nodes_i3 = policyNodes[i].iterator();
                        while (nodes_i3.hasNext()) {
                            PKIXPolicyNode node3 = (PKIXPolicyNode) nodes_i3.next();
                            if (node3.getValidPolicy().equals(id_p3)) {
                                ((PKIXPolicyNode) node3.getParent()).removeChild(node3);
                                nodes_i3.remove();
                                for (int k = i - 1; k >= 0; k--) {
                                    List nodes = policyNodes[k];
                                    PKIXPolicyNode _validPolicyTree2 = _validPolicyTree;
                                    for (int l = 0; l < nodes.size(); l++) {
                                        PKIXPolicyNode node22 = (PKIXPolicyNode) nodes.get(l);
                                        if (!node22.hasChildren() && (_validPolicyTree2 = CertPathValidatorUtilities.removePolicyNode(_validPolicyTree2, policyNodes, node22)) == null) {
                                            break;
                                        }
                                    }
                                    _validPolicyTree = _validPolicyTree2;
                                }
                            }
                            id_p3 = id_p3;
                        }
                    }
                }
                s_idp2 = s_idp;
                m_idp2 = m_idp;
                mappings2 = mappings;
            }
            return _validPolicyTree;
        } catch (AnnotatedException ex3) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", ex3, certPath, index);
        }
    }

    protected static void prepareNextCertA(CertPath certPath, int index) throws CertPathValidatorException {
        try {
            ASN1Sequence pm = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_MAPPINGS));
            if (pm != null) {
                for (int j = 0; j < pm.size(); j++) {
                    try {
                        ASN1Sequence mapping = DERSequence.getInstance(pm.getObjectAt(j));
                        ASN1ObjectIdentifier issuerDomainPolicy = ASN1ObjectIdentifier.getInstance(mapping.getObjectAt(0));
                        ASN1ObjectIdentifier subjectDomainPolicy = ASN1ObjectIdentifier.getInstance(mapping.getObjectAt(1));
                        if (ANY_POLICY.equals(issuerDomainPolicy.getId())) {
                            throw new CertPathValidatorException("IssuerDomainPolicy is anyPolicy", null, certPath, index);
                        } else if (ANY_POLICY.equals(subjectDomainPolicy.getId())) {
                            throw new CertPathValidatorException("SubjectDomainPolicy is anyPolicy,", null, certPath, index);
                        }
                    } catch (Exception e) {
                        throw new ExtCertPathValidatorException("Policy mappings extension contents could not be decoded.", e, certPath, index);
                    }
                }
            }
        } catch (AnnotatedException ex) {
            throw new ExtCertPathValidatorException("Policy mappings extension could not be decoded.", ex, certPath, index);
        }
    }

    protected static void processCertF(CertPath certPath, int index, PKIXPolicyNode validPolicyTree, int explicitPolicy) throws CertPathValidatorException {
        if (explicitPolicy <= 0 && validPolicyTree == null) {
            throw new ExtCertPathValidatorException("No valid policy tree found when one expected.", null, certPath, index);
        }
    }

    protected static PKIXPolicyNode processCertE(CertPath certPath, int index, PKIXPolicyNode validPolicyTree) throws CertPathValidatorException {
        try {
            if (DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), CERTIFICATE_POLICIES)) == null) {
                return null;
            }
            return validPolicyTree;
        } catch (AnnotatedException e) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e, certPath, index);
        }
    }

    protected static void processCertBC(CertPath certPath, int index, PKIXNameConstraintValidator nameConstraintValidator) throws CertPathValidatorException {
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        int n = certs.size();
        int i = n - index;
        if (!CertPathValidatorUtilities.isSelfIssued(cert) || i >= n) {
            try {
                ASN1Sequence dns = DERSequence.getInstance(PrincipalUtils.getSubjectPrincipal(cert).getEncoded());
                try {
                    nameConstraintValidator.checkPermittedDN(dns);
                    nameConstraintValidator.checkExcludedDN(dns);
                    try {
                        GeneralNames altName = GeneralNames.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, SUBJECT_ALTERNATIVE_NAME));
                        RDN[] emails = X500Name.getInstance(dns).getRDNs(BCStyle.EmailAddress);
                        for (int eI = 0; eI != emails.length; eI++) {
                            GeneralName emailAsGeneralName = new GeneralName(1, ((ASN1String) emails[eI].getFirst().getValue()).getString());
                            try {
                                nameConstraintValidator.checkPermitted(emailAsGeneralName);
                                nameConstraintValidator.checkExcluded(emailAsGeneralName);
                            } catch (PKIXNameConstraintValidatorException ex) {
                                throw new CertPathValidatorException("Subtree check for certificate subject alternative email failed.", ex, certPath, index);
                            }
                        }
                        if (altName != null) {
                            try {
                                GeneralName[] genNames = altName.getNames();
                                for (int j = 0; j < genNames.length; j++) {
                                    try {
                                        nameConstraintValidator.checkPermitted(genNames[j]);
                                        nameConstraintValidator.checkExcluded(genNames[j]);
                                    } catch (PKIXNameConstraintValidatorException e) {
                                        throw new CertPathValidatorException("Subtree check for certificate subject alternative name failed.", e, certPath, index);
                                    }
                                }
                            } catch (Exception e2) {
                                throw new CertPathValidatorException("Subject alternative name contents could not be decoded.", e2, certPath, index);
                            }
                        }
                    } catch (Exception e3) {
                        throw new CertPathValidatorException("Subject alternative name extension could not be decoded.", e3, certPath, index);
                    }
                } catch (PKIXNameConstraintValidatorException e4) {
                    throw new CertPathValidatorException("Subtree check for certificate subject failed.", e4, certPath, index);
                }
            } catch (Exception e5) {
                throw new CertPathValidatorException("Exception extracting subject name when checking subtrees.", e5, certPath, index);
            }
        }
    }

    protected static PKIXPolicyNode processCertD(CertPath certPath, int index, Set acceptablePolicies, PKIXPolicyNode validPolicyTree, List[] policyNodes, int inhibitAnyPolicy) throws CertPathValidatorException {
        int i;
        String _policy;
        int i2;
        HashSet hashSet;
        List _nodes;
        int k;
        PKIXPolicyNode _node;
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        int n = certs.size();
        int i3 = n - index;
        try {
            ASN1Sequence certPolicies = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue(cert, CERTIFICATE_POLICIES));
            if (certPolicies == null || validPolicyTree == null) {
                return null;
            }
            Enumeration e = certPolicies.getObjects();
            HashSet hashSet2 = new HashSet();
            while (e.hasMoreElements()) {
                PolicyInformation pInfo = PolicyInformation.getInstance(e.nextElement());
                ASN1ObjectIdentifier pOid = pInfo.getPolicyIdentifier();
                hashSet2.add(pOid.getId());
                if (!ANY_POLICY.equals(pOid.getId())) {
                    try {
                        Set pq = CertPathValidatorUtilities.getQualifierSet(pInfo.getPolicyQualifiers());
                        if (!CertPathValidatorUtilities.processCertD1i(i3, policyNodes, pOid, pq)) {
                            CertPathValidatorUtilities.processCertD1ii(i3, policyNodes, pOid, pq);
                        }
                    } catch (CertPathValidatorException ex) {
                        throw new ExtCertPathValidatorException("Policy qualifier info set could not be build.", ex, certPath, index);
                    }
                }
            }
            if (acceptablePolicies.isEmpty() || acceptablePolicies.contains(ANY_POLICY)) {
                acceptablePolicies.clear();
                acceptablePolicies.addAll(hashSet2);
            } else {
                HashSet hashSet3 = new HashSet();
                for (Object o : acceptablePolicies) {
                    if (hashSet2.contains(o)) {
                        hashSet3.add(o);
                    }
                }
                acceptablePolicies.clear();
                acceptablePolicies.addAll(hashSet3);
            }
            if (inhibitAnyPolicy > 0 || (i3 < n && CertPathValidatorUtilities.isSelfIssued(cert))) {
                Enumeration e2 = certPolicies.getObjects();
                while (true) {
                    if (!e2.hasMoreElements()) {
                        i = i3;
                        break;
                    }
                    PolicyInformation pInfo2 = PolicyInformation.getInstance(e2.nextElement());
                    if (ANY_POLICY.equals(pInfo2.getPolicyIdentifier().getId())) {
                        Set _apq = CertPathValidatorUtilities.getQualifierSet(pInfo2.getPolicyQualifiers());
                        List _nodes2 = policyNodes[i3 - 1];
                        int k2 = 0;
                        while (k2 < _nodes2.size()) {
                            PKIXPolicyNode _node2 = (PKIXPolicyNode) _nodes2.get(k2);
                            for (Object _tmp : _node2.getExpectedPolicies()) {
                                if (_tmp instanceof String) {
                                    _policy = (String) _tmp;
                                } else if (_tmp instanceof ASN1ObjectIdentifier) {
                                    _policy = ((ASN1ObjectIdentifier) _tmp).getId();
                                }
                                Iterator _childrenIter = _node2.getChildren();
                                boolean _found = false;
                                while (_childrenIter.hasNext()) {
                                    if (_policy.equals(((PKIXPolicyNode) _childrenIter.next()).getValidPolicy())) {
                                        _found = true;
                                    }
                                    e2 = e2;
                                }
                                if (!_found) {
                                    Set _newChildExpectedPolicies = new HashSet();
                                    _newChildExpectedPolicies.add(_policy);
                                    k = k2;
                                    _nodes = _nodes2;
                                    hashSet = hashSet2;
                                    i2 = i3;
                                    PKIXPolicyNode _newChild = new PKIXPolicyNode(new ArrayList(), i3, _newChildExpectedPolicies, _node2, _apq, _policy, false);
                                    _node = _node2;
                                    _node.addChild(_newChild);
                                    policyNodes[i2].add(_newChild);
                                } else {
                                    _node = _node2;
                                    k = k2;
                                    _nodes = _nodes2;
                                    hashSet = hashSet2;
                                    i2 = i3;
                                }
                                _node2 = _node;
                                e2 = e2;
                                k2 = k;
                                _nodes2 = _nodes;
                                hashSet2 = hashSet;
                                i3 = i2;
                            }
                            k2++;
                        }
                        i = i3;
                    }
                }
            } else {
                i = i3;
            }
            PKIXPolicyNode _validPolicyTree = validPolicyTree;
            for (int j = i - 1; j >= 0; j--) {
                List nodes = policyNodes[j];
                for (int k3 = 0; k3 < nodes.size(); k3++) {
                    PKIXPolicyNode node = (PKIXPolicyNode) nodes.get(k3);
                    if (!node.hasChildren() && (_validPolicyTree = CertPathValidatorUtilities.removePolicyNode(_validPolicyTree, policyNodes, node)) == null) {
                        break;
                    }
                }
            }
            Set criticalExtensionOids = cert.getCriticalExtensionOIDs();
            if (criticalExtensionOids != null) {
                boolean critical = criticalExtensionOids.contains(CERTIFICATE_POLICIES);
                List nodes2 = policyNodes[i];
                for (int j2 = 0; j2 < nodes2.size(); j2++) {
                    ((PKIXPolicyNode) nodes2.get(j2)).setCritical(critical);
                }
            }
            return _validPolicyTree;
        } catch (AnnotatedException e3) {
            throw new ExtCertPathValidatorException("Could not read certificate policies extension from certificate.", e3, certPath, index);
        }
    }

    protected static void processCertA(CertPath certPath, PKIXExtendedParameters paramsPKIX, int index, PublicKey workingPublicKey, boolean verificationAlreadyPerformed, X500Name workingIssuerName, X509Certificate sign, JcaJceHelper helper) throws ExtCertPathValidatorException {
        GeneralSecurityException e;
        List certs = certPath.getCertificates();
        X509Certificate cert = (X509Certificate) certs.get(index);
        if (!verificationAlreadyPerformed) {
            try {
                try {
                    CertPathValidatorUtilities.verifyX509Certificate(cert, workingPublicKey, paramsPKIX.getSigProvider());
                } catch (GeneralSecurityException e2) {
                    e = e2;
                }
            } catch (GeneralSecurityException e3) {
                e = e3;
                throw new ExtCertPathValidatorException("Could not validate certificate signature.", e, certPath, index);
            }
        }
        try {
            cert.checkValidity(CertPathValidatorUtilities.getValidCertDateFromValidityModel(paramsPKIX, certPath, index));
            if (paramsPKIX.isRevocationEnabled()) {
                try {
                    checkCRLs(paramsPKIX, cert, CertPathValidatorUtilities.getValidCertDateFromValidityModel(paramsPKIX, certPath, index), sign, workingPublicKey, certs, helper);
                } catch (AnnotatedException e4) {
                    Throwable cause = e4;
                    if (e4.getCause() != null) {
                        cause = e4.getCause();
                    }
                    throw new ExtCertPathValidatorException(e4.getMessage(), cause, certPath, index);
                }
            }
            if (!PrincipalUtils.getEncodedIssuerPrincipal(cert).equals(workingIssuerName)) {
                throw new ExtCertPathValidatorException("IssuerName(" + PrincipalUtils.getEncodedIssuerPrincipal(cert) + ") does not match SubjectName(" + workingIssuerName + ") of signing certificate.", null, certPath, index);
            }
        } catch (CertificateExpiredException e5) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e5.getMessage(), e5, certPath, index);
        } catch (CertificateNotYetValidException e6) {
            throw new ExtCertPathValidatorException("Could not validate certificate: " + e6.getMessage(), e6, certPath, index);
        } catch (AnnotatedException e7) {
            throw new ExtCertPathValidatorException("Could not validate time of certificate.", e7, certPath, index);
        }
    }

    protected static int prepareNextCertI1(CertPath certPath, int index, int explicitPolicy) throws CertPathValidatorException {
        try {
            ASN1Sequence pc = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_CONSTRAINTS));
            if (pc != null) {
                Enumeration policyConstraints = pc.getObjects();
                while (true) {
                    if (!policyConstraints.hasMoreElements()) {
                        break;
                    }
                    try {
                        ASN1TaggedObject constraint = ASN1TaggedObject.getInstance(policyConstraints.nextElement());
                        if (constraint.getTagNo() == 0) {
                            int tmpInt = ASN1Integer.getInstance(constraint, false).getValue().intValue();
                            if (tmpInt < explicitPolicy) {
                                return tmpInt;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", e, certPath, index);
                    }
                }
            }
            return explicitPolicy;
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", e2, certPath, index);
        }
    }

    protected static int prepareNextCertI2(CertPath certPath, int index, int policyMapping) throws CertPathValidatorException {
        try {
            ASN1Sequence pc = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_CONSTRAINTS));
            if (pc != null) {
                Enumeration policyConstraints = pc.getObjects();
                while (true) {
                    if (!policyConstraints.hasMoreElements()) {
                        break;
                    }
                    try {
                        ASN1TaggedObject constraint = ASN1TaggedObject.getInstance(policyConstraints.nextElement());
                        if (constraint.getTagNo() == 1) {
                            int tmpInt = ASN1Integer.getInstance(constraint, false).getValue().intValue();
                            if (tmpInt < policyMapping) {
                                return tmpInt;
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        throw new ExtCertPathValidatorException("Policy constraints extension contents cannot be decoded.", e, certPath, index);
                    }
                }
            }
            return policyMapping;
        } catch (Exception e2) {
            throw new ExtCertPathValidatorException("Policy constraints extension cannot be decoded.", e2, certPath, index);
        }
    }

    protected static void prepareNextCertG(CertPath certPath, int index, PKIXNameConstraintValidator nameConstraintValidator) throws CertPathValidatorException {
        NameConstraints nc = null;
        try {
            ASN1Sequence ncSeq = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), NAME_CONSTRAINTS));
            if (ncSeq != null) {
                nc = NameConstraints.getInstance(ncSeq);
            }
            if (nc != null) {
                GeneralSubtree[] permitted = nc.getPermittedSubtrees();
                if (permitted != null) {
                    try {
                        nameConstraintValidator.intersectPermittedSubtree(permitted);
                    } catch (Exception ex) {
                        throw new ExtCertPathValidatorException("Permitted subtrees cannot be build from name constraints extension.", ex, certPath, index);
                    }
                }
                GeneralSubtree[] excluded = nc.getExcludedSubtrees();
                if (excluded != null) {
                    for (int i = 0; i != excluded.length; i++) {
                        try {
                            nameConstraintValidator.addExcludedSubtree(excluded[i]);
                        } catch (Exception ex2) {
                            throw new ExtCertPathValidatorException("Excluded subtrees cannot be build from name constraints extension.", ex2, certPath, index);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Name constraints extension could not be decoded.", e, certPath, index);
        }
    }

    private static void checkCRL(DistributionPoint dp, PKIXExtendedParameters paramsPKIX, X509Certificate cert, Date validDate, X509Certificate defaultCRLSignCert, PublicKey defaultCRLSignKey, CertStatus certStatus, ReasonsMask reasonMask, List certPathCerts, JcaJceHelper helper) throws AnnotatedException {
        Set crls;
        ReasonsMask reasonsMask;
        AnnotatedException e;
        X509CRL deltaCRL;
        Set criticalExtensions;
        ReasonsMask reasonsMask2 = reasonMask;
        Date currentDate = new Date(System.currentTimeMillis());
        if (validDate.getTime() <= currentDate.getTime()) {
            Set crls2 = CertPathValidatorUtilities.getCompleteCRLs(dp, cert, currentDate, paramsPKIX);
            Iterator crl_iter = crls2.iterator();
            boolean validCrlFound = false;
            AnnotatedException lastException = null;
            while (crl_iter.hasNext() && certStatus.getCertStatus() == 11 && !reasonMask.isAllReasons()) {
                try {
                    X509CRL crl = (X509CRL) crl_iter.next();
                    ReasonsMask interimReasonsMask = processCRLD(crl, dp);
                    if (!interimReasonsMask.hasNewReasons(reasonsMask2)) {
                        continue;
                    } else {
                        crls = crls2;
                        try {
                            PublicKey key = processCRLG(crl, processCRLF(crl, cert, defaultCRLSignCert, defaultCRLSignKey, paramsPKIX, certPathCerts, helper));
                            deltaCRL = null;
                            Date validityDate = currentDate;
                            if (paramsPKIX.getDate() != null) {
                                validityDate = paramsPKIX.getDate();
                            }
                            if (paramsPKIX.isUseDeltasEnabled()) {
                                deltaCRL = processCRLH(CertPathValidatorUtilities.getDeltaCRLs(validityDate, crl, paramsPKIX.getCertStores(), paramsPKIX.getCRLStores()), key);
                            }
                            if (paramsPKIX.getValidityModel() != 1) {
                                if (cert.getNotAfter().getTime() < crl.getThisUpdate().getTime()) {
                                    throw new AnnotatedException("No valid CRL for current time found.");
                                }
                            }
                            processCRLB1(dp, cert, crl);
                            processCRLB2(dp, cert, crl);
                            processCRLC(deltaCRL, crl, paramsPKIX);
                            processCRLI(validDate, deltaCRL, cert, certStatus, paramsPKIX);
                            processCRLJ(validDate, crl, cert, certStatus);
                            if (certStatus.getCertStatus() == 8) {
                                certStatus.setCertStatus(11);
                            }
                            reasonsMask = reasonMask;
                        } catch (AnnotatedException e2) {
                            e = e2;
                            reasonsMask = reasonMask;
                            lastException = e;
                            reasonsMask2 = reasonsMask;
                            crls2 = crls;
                        }
                        try {
                            reasonsMask.addReasons(interimReasonsMask);
                            Set criticalExtensions2 = crl.getCriticalExtensionOIDs();
                            if (criticalExtensions2 != null) {
                                Set criticalExtensions3 = new HashSet(criticalExtensions2);
                                criticalExtensions3.remove(Extension.issuingDistributionPoint.getId());
                                criticalExtensions3.remove(Extension.deltaCRLIndicator.getId());
                                if (!criticalExtensions3.isEmpty()) {
                                    throw new AnnotatedException("CRL contains unsupported critical extensions.");
                                }
                            }
                            if (!(deltaCRL == null || (criticalExtensions = deltaCRL.getCriticalExtensionOIDs()) == null)) {
                                Set criticalExtensions4 = new HashSet(criticalExtensions);
                                criticalExtensions4.remove(Extension.issuingDistributionPoint.getId());
                                criticalExtensions4.remove(Extension.deltaCRLIndicator.getId());
                                if (!criticalExtensions4.isEmpty()) {
                                    throw new AnnotatedException("Delta CRL contains unsupported critical extension.");
                                }
                            }
                            validCrlFound = true;
                        } catch (AnnotatedException e3) {
                            e = e3;
                            lastException = e;
                            reasonsMask2 = reasonsMask;
                            crls2 = crls;
                        }
                        reasonsMask2 = reasonsMask;
                        crls2 = crls;
                    }
                } catch (AnnotatedException e4) {
                    e = e4;
                    reasonsMask = reasonsMask2;
                    crls = crls2;
                    lastException = e;
                    reasonsMask2 = reasonsMask;
                    crls2 = crls;
                }
            }
            if (!validCrlFound) {
                throw lastException;
            }
            return;
        }
        throw new AnnotatedException("Validation time is in future.");
    }

    /* JADX WARNING: Removed duplicated region for block: B:64:0x0131  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x013e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static void checkCRLs(com.android.org.bouncycastle.jcajce.PKIXExtendedParameters r24, java.security.cert.X509Certificate r25, java.util.Date r26, java.security.cert.X509Certificate r27, java.security.PublicKey r28, java.util.List r29, com.android.org.bouncycastle.jcajce.util.JcaJceHelper r30) throws com.android.org.bouncycastle.jce.provider.AnnotatedException {
        /*
        // Method dump skipped, instructions count: 459
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.org.bouncycastle.jce.provider.RFC3280CertPathUtilities.checkCRLs(com.android.org.bouncycastle.jcajce.PKIXExtendedParameters, java.security.cert.X509Certificate, java.util.Date, java.security.cert.X509Certificate, java.security.PublicKey, java.util.List, com.android.org.bouncycastle.jcajce.util.JcaJceHelper):void");
    }

    protected static int prepareNextCertJ(CertPath certPath, int index, int inhibitAnyPolicy) throws CertPathValidatorException {
        int _inhibitAnyPolicy;
        try {
            ASN1Integer iap = ASN1Integer.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), INHIBIT_ANY_POLICY));
            if (iap == null || (_inhibitAnyPolicy = iap.getValue().intValue()) >= inhibitAnyPolicy) {
                return inhibitAnyPolicy;
            }
            return _inhibitAnyPolicy;
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Inhibit any-policy extension cannot be decoded.", e, certPath, index);
        }
    }

    protected static void prepareNextCertK(CertPath certPath, int index) throws CertPathValidatorException {
        try {
            BasicConstraints bc = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), BASIC_CONSTRAINTS));
            if (bc == null) {
                throw new CertPathValidatorException("Intermediate certificate lacks BasicConstraints");
            } else if (!bc.isCA()) {
                throw new CertPathValidatorException("Not a CA certificate");
            }
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e, certPath, index);
        }
    }

    protected static int prepareNextCertL(CertPath certPath, int index, int maxPathLength) throws CertPathValidatorException {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index))) {
            return maxPathLength;
        }
        if (maxPathLength > 0) {
            return maxPathLength - 1;
        }
        throw new ExtCertPathValidatorException("Max path length not greater than zero", null, certPath, index);
    }

    protected static int prepareNextCertM(CertPath certPath, int index, int maxPathLength) throws CertPathValidatorException {
        BigInteger _pathLengthConstraint;
        int _plc;
        try {
            BasicConstraints bc = BasicConstraints.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), BASIC_CONSTRAINTS));
            if (bc == null || (_pathLengthConstraint = bc.getPathLenConstraint()) == null || (_plc = _pathLengthConstraint.intValue()) >= maxPathLength) {
                return maxPathLength;
            }
            return _plc;
        } catch (Exception e) {
            throw new ExtCertPathValidatorException("Basic constraints extension cannot be decoded.", e, certPath, index);
        }
    }

    protected static void prepareNextCertN(CertPath certPath, int index) throws CertPathValidatorException {
        boolean[] _usage = ((X509Certificate) certPath.getCertificates().get(index)).getKeyUsage();
        if (_usage != null && !_usage[5]) {
            throw new ExtCertPathValidatorException("Issuer certificate keyusage extension is critical and does not permit key signing.", null, certPath, index);
        }
    }

    protected static void prepareNextCertO(CertPath certPath, int index, Set criticalExtensions, List pathCheckers) throws CertPathValidatorException {
        X509Certificate cert = (X509Certificate) certPath.getCertificates().get(index);
        Iterator tmpIter = pathCheckers.iterator();
        while (tmpIter.hasNext()) {
            try {
                ((PKIXCertPathChecker) tmpIter.next()).check(cert, criticalExtensions);
            } catch (CertPathValidatorException e) {
                throw new CertPathValidatorException(e.getMessage(), e.getCause(), certPath, index);
            }
        }
        if (!criticalExtensions.isEmpty()) {
            throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + criticalExtensions, null, certPath, index);
        }
    }

    protected static int prepareNextCertH1(CertPath certPath, int index, int explicitPolicy) {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index)) || explicitPolicy == 0) {
            return explicitPolicy;
        }
        return explicitPolicy - 1;
    }

    protected static int prepareNextCertH2(CertPath certPath, int index, int policyMapping) {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index)) || policyMapping == 0) {
            return policyMapping;
        }
        return policyMapping - 1;
    }

    protected static int prepareNextCertH3(CertPath certPath, int index, int inhibitAnyPolicy) {
        if (CertPathValidatorUtilities.isSelfIssued((X509Certificate) certPath.getCertificates().get(index)) || inhibitAnyPolicy == 0) {
            return inhibitAnyPolicy;
        }
        return inhibitAnyPolicy - 1;
    }

    protected static int wrapupCertA(int explicitPolicy, X509Certificate cert) {
        if (CertPathValidatorUtilities.isSelfIssued(cert) || explicitPolicy == 0) {
            return explicitPolicy;
        }
        return explicitPolicy - 1;
    }

    protected static int wrapupCertB(CertPath certPath, int index, int explicitPolicy) throws CertPathValidatorException {
        try {
            ASN1Sequence pc = DERSequence.getInstance(CertPathValidatorUtilities.getExtensionValue((X509Certificate) certPath.getCertificates().get(index), POLICY_CONSTRAINTS));
            if (pc != null) {
                Enumeration policyConstraints = pc.getObjects();
                while (policyConstraints.hasMoreElements()) {
                    ASN1TaggedObject constraint = (ASN1TaggedObject) policyConstraints.nextElement();
                    if (constraint.getTagNo() == 0) {
                        try {
                            if (ASN1Integer.getInstance(constraint, false).getValue().intValue() == 0) {
                                return 0;
                            }
                        } catch (Exception e) {
                            throw new ExtCertPathValidatorException("Policy constraints requireExplicitPolicy field could not be decoded.", e, certPath, index);
                        }
                    }
                }
            }
            return explicitPolicy;
        } catch (AnnotatedException e2) {
            throw new ExtCertPathValidatorException("Policy constraints could not be decoded.", e2, certPath, index);
        }
    }

    protected static void wrapupCertF(CertPath certPath, int index, List pathCheckers, Set criticalExtensions) throws CertPathValidatorException {
        X509Certificate cert = (X509Certificate) certPath.getCertificates().get(index);
        Iterator tmpIter = pathCheckers.iterator();
        while (tmpIter.hasNext()) {
            try {
                ((PKIXCertPathChecker) tmpIter.next()).check(cert, criticalExtensions);
            } catch (CertPathValidatorException e) {
                throw new ExtCertPathValidatorException(e.getMessage(), e, certPath, index);
            } catch (Exception e2) {
                throw new CertPathValidatorException("Additional certificate path checker failed.", e2, certPath, index);
            }
        }
        if (!criticalExtensions.isEmpty()) {
            throw new ExtCertPathValidatorException("Certificate has unsupported critical extension: " + criticalExtensions, null, certPath, index);
        }
    }

    protected static PKIXPolicyNode wrapupCertG(CertPath certPath, PKIXExtendedParameters paramsPKIX, Set userInitialPolicySet, int index, List[] policyNodes, PKIXPolicyNode validPolicyTree, Set acceptablePolicies) throws CertPathValidatorException {
        PKIXPolicyNode validPolicyTree2;
        int n = certPath.getCertificates().size();
        if (validPolicyTree == null) {
            if (!paramsPKIX.isExplicitPolicyRequired()) {
                return null;
            }
            throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, index);
        } else if (CertPathValidatorUtilities.isAnyPolicy(userInitialPolicySet)) {
            if (!paramsPKIX.isExplicitPolicyRequired()) {
                validPolicyTree2 = validPolicyTree;
            } else if (!acceptablePolicies.isEmpty()) {
                Set<PKIXPolicyNode> _validPolicyNodeSet = new HashSet();
                for (List _nodeDepth : policyNodes) {
                    for (int k = 0; k < _nodeDepth.size(); k++) {
                        PKIXPolicyNode _node = (PKIXPolicyNode) _nodeDepth.get(k);
                        if (ANY_POLICY.equals(_node.getValidPolicy())) {
                            Iterator _iter = _node.getChildren();
                            while (_iter.hasNext()) {
                                _validPolicyNodeSet.add(_iter.next());
                            }
                        }
                    }
                }
                for (PKIXPolicyNode _node2 : _validPolicyNodeSet) {
                    acceptablePolicies.contains(_node2.getValidPolicy());
                }
                validPolicyTree2 = validPolicyTree;
                for (int j = n - 1; j >= 0; j--) {
                    List nodes = policyNodes[j];
                    for (int k2 = 0; k2 < nodes.size(); k2++) {
                        PKIXPolicyNode node = (PKIXPolicyNode) nodes.get(k2);
                        if (!node.hasChildren()) {
                            validPolicyTree2 = CertPathValidatorUtilities.removePolicyNode(validPolicyTree2, policyNodes, node);
                        }
                    }
                }
            } else {
                throw new ExtCertPathValidatorException("Explicit policy requested but none available.", null, certPath, index);
            }
            return validPolicyTree2;
        } else {
            Set<PKIXPolicyNode> _validPolicyNodeSet2 = new HashSet();
            for (List _nodeDepth2 : policyNodes) {
                for (int k3 = 0; k3 < _nodeDepth2.size(); k3++) {
                    PKIXPolicyNode _node3 = (PKIXPolicyNode) _nodeDepth2.get(k3);
                    if (ANY_POLICY.equals(_node3.getValidPolicy())) {
                        Iterator _iter2 = _node3.getChildren();
                        while (_iter2.hasNext()) {
                            PKIXPolicyNode _c_node = (PKIXPolicyNode) _iter2.next();
                            if (!ANY_POLICY.equals(_c_node.getValidPolicy())) {
                                _validPolicyNodeSet2.add(_c_node);
                            }
                        }
                    }
                }
            }
            PKIXPolicyNode validPolicyTree3 = validPolicyTree;
            for (PKIXPolicyNode _node4 : _validPolicyNodeSet2) {
                if (!userInitialPolicySet.contains(_node4.getValidPolicy())) {
                    validPolicyTree3 = CertPathValidatorUtilities.removePolicyNode(validPolicyTree3, policyNodes, _node4);
                }
            }
            if (validPolicyTree3 != null) {
                for (int j2 = n - 1; j2 >= 0; j2--) {
                    List nodes2 = policyNodes[j2];
                    for (int k4 = 0; k4 < nodes2.size(); k4++) {
                        PKIXPolicyNode node2 = (PKIXPolicyNode) nodes2.get(k4);
                        if (!node2.hasChildren()) {
                            validPolicyTree3 = CertPathValidatorUtilities.removePolicyNode(validPolicyTree3, policyNodes, node2);
                        }
                    }
                }
            }
            return validPolicyTree3;
        }
    }
}
