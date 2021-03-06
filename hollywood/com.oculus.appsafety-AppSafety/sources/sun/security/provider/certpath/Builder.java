package sun.security.provider.certpath;

import java.io.IOException;
import java.security.AccessController;
import java.security.GeneralSecurityException;
import java.security.cert.CertStore;
import java.security.cert.CertStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import sun.security.action.GetBooleanAction;
import sun.security.provider.certpath.PKIX;
import sun.security.util.Debug;
import sun.security.x509.GeneralNameInterface;
import sun.security.x509.GeneralNames;
import sun.security.x509.GeneralSubtrees;
import sun.security.x509.NameConstraintsExtension;
import sun.security.x509.SubjectAlternativeNameExtension;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;

public abstract class Builder {
    static final boolean USE_AIA = ((Boolean) AccessController.doPrivileged(new GetBooleanAction("com.sun.security.enableAIAcaIssuers"))).booleanValue();
    private static final Debug debug = Debug.getInstance("certpath");
    final PKIX.BuilderParams buildParams;
    private Set<String> matchingPolicies;
    final X509CertSelector targetCertConstraints;

    /* access modifiers changed from: package-private */
    public abstract void addCertToPath(X509Certificate x509Certificate, LinkedList<X509Certificate> linkedList);

    /* access modifiers changed from: package-private */
    public abstract Collection<X509Certificate> getMatchingCerts(State state, List<CertStore> list) throws CertStoreException, CertificateException, IOException;

    /* access modifiers changed from: package-private */
    public abstract boolean isPathCompleted(X509Certificate x509Certificate);

    /* access modifiers changed from: package-private */
    public abstract void removeFinalCertFromPath(LinkedList<X509Certificate> linkedList);

    /* access modifiers changed from: package-private */
    public abstract void verifyCert(X509Certificate x509Certificate, State state, List<X509Certificate> list) throws GeneralSecurityException;

    Builder(PKIX.BuilderParams buildParams2) {
        this.buildParams = buildParams2;
        this.targetCertConstraints = (X509CertSelector) buildParams2.targetCertConstraints();
    }

    static int distance(GeneralNameInterface base, GeneralNameInterface test, int incomparable) {
        Debug debug2;
        int constrains = base.constrains(test);
        if (constrains == -1) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("Builder.distance(): Names are different types");
            }
            return incomparable;
        } else if (constrains == 0) {
            return 0;
        } else {
            if (constrains == 1 || constrains == 2) {
                return test.subtreeDepth() - base.subtreeDepth();
            }
            if (constrains == 3 && (debug2 = debug) != null) {
                debug2.println("Builder.distance(): Names are same type but in different subtrees");
            }
            return incomparable;
        }
    }

    static int hops(GeneralNameInterface base, GeneralNameInterface test, int incomparable) {
        int baseRtest = base.constrains(test);
        if (baseRtest == -1) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("Builder.hops(): Names are different types");
            }
            return incomparable;
        } else if (baseRtest == 0) {
            return 0;
        } else {
            if (baseRtest == 1) {
                return test.subtreeDepth() - base.subtreeDepth();
            }
            if (baseRtest == 2) {
                return test.subtreeDepth() - base.subtreeDepth();
            }
            if (baseRtest != 3) {
                return incomparable;
            }
            if (base.getType() != 4) {
                Debug debug3 = debug;
                if (debug3 != null) {
                    debug3.println("Builder.hops(): hopDistance not implemented for this name type");
                }
                return incomparable;
            }
            X500Name baseName = (X500Name) base;
            X500Name testName = (X500Name) test;
            X500Name commonName = baseName.commonAncestor(testName);
            if (commonName == null) {
                Debug debug4 = debug;
                if (debug4 != null) {
                    debug4.println("Builder.hops(): Names are in different namespaces");
                }
                return incomparable;
            }
            return (baseName.subtreeDepth() + testName.subtreeDepth()) - (commonName.subtreeDepth() * 2);
        }
    }

    static int targetDistance(NameConstraintsExtension constraints, X509Certificate cert, GeneralNameInterface target) throws IOException {
        GeneralNames altNames;
        if (constraints == null || constraints.verify(cert)) {
            try {
                X509CertImpl certImpl = X509CertImpl.toImpl(cert);
                if (X500Name.asX500Name(certImpl.getSubjectX500Principal()).equals(target)) {
                    return 0;
                }
                SubjectAlternativeNameExtension altNameExt = certImpl.getSubjectAlternativeNameExtension();
                if (!(altNameExt == null || (altNames = altNameExt.get(SubjectAlternativeNameExtension.SUBJECT_NAME)) == null)) {
                    int n = altNames.size();
                    for (int j = 0; j < n; j++) {
                        if (altNames.get(j).getName().equals(target)) {
                            return 0;
                        }
                    }
                }
                NameConstraintsExtension ncExt = certImpl.getNameConstraintsExtension();
                if (ncExt == null) {
                    return -1;
                }
                if (constraints != null) {
                    constraints.merge(ncExt);
                } else {
                    constraints = (NameConstraintsExtension) ncExt.clone();
                }
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("Builder.targetDistance() merged constraints: " + String.valueOf(constraints));
                }
                GeneralSubtrees permitted = constraints.get(NameConstraintsExtension.PERMITTED_SUBTREES);
                GeneralSubtrees excluded = constraints.get(NameConstraintsExtension.EXCLUDED_SUBTREES);
                if (permitted != null) {
                    permitted.reduce(excluded);
                }
                Debug debug3 = debug;
                if (debug3 != null) {
                    debug3.println("Builder.targetDistance() reduced constraints: " + ((Object) permitted));
                }
                if (!constraints.verify(target)) {
                    throw new IOException("New certificate not allowed to sign certificate for target");
                } else if (permitted == null) {
                    return -1;
                } else {
                    int n2 = permitted.size();
                    for (int i = 0; i < n2; i++) {
                        int distance = distance(permitted.get(i).getName().getName(), target, -1);
                        if (distance >= 0) {
                            return distance + 1;
                        }
                    }
                    return -1;
                }
            } catch (CertificateException e) {
                throw new IOException("Invalid certificate", e);
            }
        } else {
            throw new IOException("certificate does not satisfy existing name constraints");
        }
    }

    /* access modifiers changed from: package-private */
    public Set<String> getMatchingPolicies() {
        if (this.matchingPolicies != null) {
            Set<String> initialPolicies = this.buildParams.initialPolicies();
            if (initialPolicies.isEmpty() || initialPolicies.contains("2.5.29.32.0") || !this.buildParams.policyMappingInhibited()) {
                this.matchingPolicies = Collections.emptySet();
            } else {
                this.matchingPolicies = new HashSet(initialPolicies);
                this.matchingPolicies.add("2.5.29.32.0");
            }
        }
        return this.matchingPolicies;
    }

    /* access modifiers changed from: package-private */
    public boolean addMatchingCerts(X509CertSelector selector, Collection<CertStore> certStores, Collection<X509Certificate> resultCerts, boolean checkAll) {
        X509Certificate targetCert = selector.getCertificate();
        if (targetCert == null) {
            boolean add = false;
            for (CertStore store : certStores) {
                try {
                    for (Certificate cert : store.getCertificates(selector)) {
                        if (!X509CertImpl.isSelfSigned((X509Certificate) cert, this.buildParams.sigProvider()) && resultCerts.add((X509Certificate) cert)) {
                            add = true;
                        }
                    }
                    if (!checkAll && add) {
                        return true;
                    }
                } catch (CertStoreException cse) {
                    Debug debug2 = debug;
                    if (debug2 != null) {
                        debug2.println("Builder.addMatchingCerts, non-fatal exception retrieving certs: " + ((Object) cse));
                        cse.printStackTrace();
                    }
                }
            }
            return add;
        } else if (!selector.match(targetCert) || X509CertImpl.isSelfSigned(targetCert, this.buildParams.sigProvider())) {
            return false;
        } else {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("Builder.addMatchingCerts: adding target cert\n  SN: " + Debug.toHexString(targetCert.getSerialNumber()) + "\n  Subject: " + ((Object) targetCert.getSubjectX500Principal()) + "\n  Issuer: " + ((Object) targetCert.getIssuerX500Principal()));
            }
            return resultCerts.add(targetCert);
        }
    }
}
