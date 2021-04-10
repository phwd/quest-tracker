package sun.security.provider.certpath;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.PublicKey;
import java.security.cert.CertPathBuilderException;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathBuilderSpi;
import java.security.cert.CertPathChecker;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidatorException;
import java.security.cert.CertSelector;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXCertPathBuilderResult;
import java.security.cert.PKIXCertPathChecker;
import java.security.cert.PKIXReason;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.PolicyNode;
import java.security.cert.TrustAnchor;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import sun.security.provider.certpath.PKIX;
import sun.security.util.Debug;
import sun.security.x509.PKIXExtensions;

public final class SunCertPathBuilder extends CertPathBuilderSpi {
    private static final Debug debug = Debug.getInstance("certpath");
    private PKIX.BuilderParams buildParams;
    private CertificateFactory cf;
    private PublicKey finalPublicKey;
    private boolean pathCompleted = false;
    private PolicyNode policyTreeResult;
    private TrustAnchor trustAnchor;

    public SunCertPathBuilder() throws CertPathBuilderException {
        try {
            this.cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            throw new CertPathBuilderException(e);
        }
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathChecker engineGetRevocationChecker() {
        return new RevocationChecker();
    }

    @Override // java.security.cert.CertPathBuilderSpi
    public CertPathBuilderResult engineBuild(CertPathParameters params) throws CertPathBuilderException, InvalidAlgorithmParameterException {
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("SunCertPathBuilder.engineBuild(" + ((Object) params) + ")");
        }
        this.buildParams = PKIX.checkBuilderParams(params);
        return build();
    }

    private PKIXCertPathBuilderResult build() throws CertPathBuilderException {
        List<List<Vertex>> adjList = new ArrayList<>();
        PKIXCertPathBuilderResult result = buildCertPath(false, adjList);
        if (result == null) {
            Debug debug2 = debug;
            if (debug2 != null) {
                debug2.println("SunCertPathBuilder.engineBuild: 2nd pass; try building again searching all certstores");
            }
            adjList.clear();
            result = buildCertPath(true, adjList);
            if (result == null) {
                throw new SunCertPathBuilderException("unable to find valid certification path to requested target", new AdjacencyList(adjList));
            }
        }
        return result;
    }

    private PKIXCertPathBuilderResult buildCertPath(boolean searchAllCertStores, List<List<Vertex>> adjList) throws CertPathBuilderException {
        this.pathCompleted = false;
        this.trustAnchor = null;
        this.finalPublicKey = null;
        this.policyTreeResult = null;
        LinkedList<X509Certificate> certPathList = new LinkedList<>();
        try {
            buildForward(adjList, certPathList, searchAllCertStores);
            try {
                if (!this.pathCompleted) {
                    return null;
                }
                if (debug != null) {
                    debug.println("SunCertPathBuilder.engineBuild() pathCompleted");
                }
                Collections.reverse(certPathList);
                return new SunCertPathBuilderResult(this.cf.generateCertPath(certPathList), this.trustAnchor, this.policyTreeResult, this.finalPublicKey, new AdjacencyList(adjList));
            } catch (CertificateException e) {
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("SunCertPathBuilder.engineBuild() exception in wrap-up");
                    e.printStackTrace();
                }
                throw new SunCertPathBuilderException("unable to find valid certification path to requested target", e, new AdjacencyList(adjList));
            }
        } catch (IOException | GeneralSecurityException e2) {
            Debug debug3 = debug;
            if (debug3 != null) {
                debug3.println("SunCertPathBuilder.engineBuild() exception in build");
                e2.printStackTrace();
            }
            throw new SunCertPathBuilderException("unable to find valid certification path to requested target", e2, new AdjacencyList(adjList));
        }
    }

    private void buildForward(List<List<Vertex>> adjacencyList, LinkedList<X509Certificate> certPathList, boolean searchAllCertStores) throws GeneralSecurityException, IOException {
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("SunCertPathBuilder.buildForward()...");
        }
        ForwardState currentState = new ForwardState();
        currentState.initState(this.buildParams.certPathCheckers());
        adjacencyList.clear();
        adjacencyList.add(new LinkedList());
        depthFirstSearchForward(this.buildParams.targetSubject(), currentState, new ForwardBuilder(this.buildParams, searchAllCertStores), adjacencyList, certPathList);
    }

    private void depthFirstSearchForward(X500Principal dN, ForwardState currentState, ForwardBuilder builder, List<List<Vertex>> adjList, LinkedList<X509Certificate> cpList) throws GeneralSecurityException, IOException {
        List<Vertex> vertices;
        Collection<X509Certificate> certs;
        Certificate finalCert;
        List<PKIXCertPathChecker> ckrs;
        List<X509Certificate> appendedCerts;
        Set<String> unresCritExts;
        Set<String> suppExts;
        List<PKIXCertPathChecker> checkers;
        Iterator<PKIXCertPathChecker> it;
        Iterator<Vertex> it2;
        boolean revCheckerAdded;
        PublicKey rootKey;
        Debug debug2 = debug;
        if (debug2 != null) {
            debug2.println("SunCertPathBuilder.depthFirstSearchForward(" + ((Object) dN) + ", " + currentState.toString() + ")");
        }
        Collection<X509Certificate> certs2 = builder.getMatchingCerts(currentState, this.buildParams.certStores());
        List<Vertex> vertices2 = addVertices(certs2, adjList);
        Debug debug3 = debug;
        if (debug3 != null) {
            debug3.println("SunCertPathBuilder.depthFirstSearchForward(): certs.size=" + vertices2.size());
        }
        Iterator<Vertex> it3 = vertices2.iterator();
        while (it3.hasNext()) {
            Vertex vertex = it3.next();
            ForwardState nextState = (ForwardState) currentState.clone();
            X509Certificate cert = vertex.getCertificate();
            try {
                builder.verifyCert(cert, nextState, cpList);
                if (builder.isPathCompleted(cert)) {
                    Debug debug4 = debug;
                    if (debug4 != null) {
                        debug4.println("SunCertPathBuilder.depthFirstSearchForward(): commencing final verification");
                    }
                    List<X509Certificate> appendedCerts2 = new ArrayList<>(cpList);
                    if (builder.trustAnchor.getTrustedCert() == null) {
                        appendedCerts2.add(0, cert);
                    }
                    PolicyNodeImpl rootNode = new PolicyNodeImpl(null, "2.5.29.32.0", null, false, Collections.singleton("2.5.29.32.0"), false);
                    List<PKIXCertPathChecker> checkers2 = new ArrayList<>();
                    PolicyChecker policyChecker = new PolicyChecker(this.buildParams.initialPolicies(), appendedCerts2.size(), this.buildParams.explicitPolicyRequired(), this.buildParams.policyMappingInhibited(), this.buildParams.anyPolicyInhibited(), this.buildParams.policyQualifiersRejected(), rootNode);
                    List<PKIXCertPathChecker> checkers3 = checkers2;
                    checkers3.add(policyChecker);
                    checkers3.add(new AlgorithmChecker(builder.trustAnchor));
                    BasicChecker basicChecker = null;
                    if (nextState.keyParamsNeeded()) {
                        PublicKey rootKey2 = cert.getPublicKey();
                        if (builder.trustAnchor.getTrustedCert() == null) {
                            PublicKey rootKey3 = builder.trustAnchor.getCAPublicKey();
                            Debug debug5 = debug;
                            if (debug5 != null) {
                                StringBuilder sb = new StringBuilder();
                                certs = certs2;
                                sb.append("SunCertPathBuilder.depthFirstSearchForward using buildParams public key: ");
                                sb.append(rootKey3.toString());
                                debug5.println(sb.toString());
                            } else {
                                certs = certs2;
                            }
                            rootKey = rootKey3;
                        } else {
                            certs = certs2;
                            rootKey = rootKey2;
                        }
                        vertices = vertices2;
                        basicChecker = new BasicChecker(new TrustAnchor(cert.getSubjectX500Principal(), rootKey, (byte[]) null), this.buildParams.date(), this.buildParams.sigProvider(), true);
                        checkers3.add(basicChecker);
                    } else {
                        certs = certs2;
                        vertices = vertices2;
                    }
                    this.buildParams.setCertPath(this.cf.generateCertPath(appendedCerts2));
                    boolean revCheckerAdded2 = false;
                    List<PKIXCertPathChecker> ckrs2 = this.buildParams.certPathCheckers();
                    for (Iterator<PKIXCertPathChecker> it4 = ckrs2.iterator(); it4.hasNext(); it4 = it4) {
                        PKIXCertPathChecker ckr = it4.next();
                        if (ckr instanceof PKIXRevocationChecker) {
                            if (!revCheckerAdded2) {
                                if (ckr instanceof RevocationChecker) {
                                    revCheckerAdded = true;
                                    ((RevocationChecker) ckr).init(builder.trustAnchor, this.buildParams);
                                } else {
                                    revCheckerAdded = true;
                                }
                                revCheckerAdded2 = revCheckerAdded;
                            } else {
                                throw new CertPathValidatorException("Only one PKIXRevocationChecker can be specified");
                            }
                        }
                    }
                    if (this.buildParams.revocationEnabled() && !revCheckerAdded2) {
                        checkers3.add(new RevocationChecker(builder.trustAnchor, this.buildParams));
                    }
                    checkers3.addAll(ckrs2);
                    int i = 0;
                    while (i < appendedCerts2.size()) {
                        X509Certificate currCert = appendedCerts2.get(i);
                        Debug debug6 = debug;
                        if (debug6 != null) {
                            appendedCerts = appendedCerts2;
                            StringBuilder sb2 = new StringBuilder();
                            ckrs = ckrs2;
                            sb2.append("current subject = ");
                            sb2.append((Object) currCert.getSubjectX500Principal());
                            debug6.println(sb2.toString());
                        } else {
                            appendedCerts = appendedCerts2;
                            ckrs = ckrs2;
                        }
                        Set<String> unresCritExts2 = currCert.getCriticalExtensionOIDs();
                        if (unresCritExts2 == null) {
                            unresCritExts = Collections.emptySet();
                        } else {
                            unresCritExts = unresCritExts2;
                        }
                        Iterator<PKIXCertPathChecker> it5 = checkers3.iterator();
                        while (it5.hasNext()) {
                            PKIXCertPathChecker currChecker = it5.next();
                            if (!currChecker.isForwardCheckingSupported()) {
                                if (i == 0) {
                                    it = it5;
                                    currChecker.init(false);
                                    if (currChecker instanceof AlgorithmChecker) {
                                        checkers = checkers3;
                                        ((AlgorithmChecker) currChecker).trySetTrustAnchor(builder.trustAnchor);
                                    } else {
                                        checkers = checkers3;
                                    }
                                } else {
                                    it = it5;
                                    checkers = checkers3;
                                }
                                try {
                                    currChecker.check(currCert, unresCritExts);
                                } catch (CertPathValidatorException cpve) {
                                    Debug debug7 = debug;
                                    if (debug7 != null) {
                                        StringBuilder sb3 = new StringBuilder();
                                        it2 = it3;
                                        sb3.append("SunCertPathBuilder.depthFirstSearchForward(): final verification failed: ");
                                        sb3.append((Object) cpve);
                                        debug7.println(sb3.toString());
                                    } else {
                                        it2 = it3;
                                    }
                                    if (!this.buildParams.targetCertConstraints().match(currCert) || cpve.getReason() != CertPathValidatorException.BasicReason.REVOKED) {
                                        vertex.setThrowable(cpve);
                                        certs2 = certs;
                                        vertices2 = vertices;
                                        it3 = it2;
                                    } else {
                                        throw cpve;
                                    }
                                }
                            } else {
                                it = it5;
                                checkers = checkers3;
                            }
                            it5 = it;
                            checkers3 = checkers;
                            it3 = it3;
                        }
                        for (PKIXCertPathChecker checker : this.buildParams.certPathCheckers()) {
                            if (checker.isForwardCheckingSupported() && (suppExts = checker.getSupportedExtensions()) != null) {
                                unresCritExts.removeAll(suppExts);
                            }
                        }
                        if (!unresCritExts.isEmpty()) {
                            unresCritExts.remove(PKIXExtensions.BasicConstraints_Id.toString());
                            unresCritExts.remove(PKIXExtensions.NameConstraints_Id.toString());
                            unresCritExts.remove(PKIXExtensions.CertificatePolicies_Id.toString());
                            unresCritExts.remove(PKIXExtensions.PolicyMappings_Id.toString());
                            unresCritExts.remove(PKIXExtensions.PolicyConstraints_Id.toString());
                            unresCritExts.remove(PKIXExtensions.InhibitAnyPolicy_Id.toString());
                            unresCritExts.remove(PKIXExtensions.SubjectAlternativeName_Id.toString());
                            unresCritExts.remove(PKIXExtensions.KeyUsage_Id.toString());
                            unresCritExts.remove(PKIXExtensions.ExtendedKeyUsage_Id.toString());
                            if (!unresCritExts.isEmpty()) {
                                throw new CertPathValidatorException("unrecognized critical extension(s)", null, null, -1, PKIXReason.UNRECOGNIZED_CRIT_EXT);
                            }
                        }
                        i++;
                        appendedCerts2 = appendedCerts;
                        ckrs2 = ckrs;
                        checkers3 = checkers3;
                        it3 = it3;
                    }
                    Debug debug8 = debug;
                    if (debug8 != null) {
                        debug8.println("SunCertPathBuilder.depthFirstSearchForward(): final verification succeeded - path completed!");
                    }
                    this.pathCompleted = true;
                    if (builder.trustAnchor.getTrustedCert() == null) {
                        builder.addCertToPath(cert, cpList);
                    }
                    this.trustAnchor = builder.trustAnchor;
                    if (basicChecker != null) {
                        this.finalPublicKey = basicChecker.getPublicKey();
                    } else {
                        if (cpList.isEmpty()) {
                            finalCert = builder.trustAnchor.getTrustedCert();
                        } else {
                            finalCert = cpList.getLast();
                        }
                        this.finalPublicKey = finalCert.getPublicKey();
                    }
                    this.policyTreeResult = policyChecker.getPolicyTree();
                    return;
                }
                builder.addCertToPath(cert, cpList);
                nextState.updateState(cert);
                adjList.add(new LinkedList());
                vertex.setIndex(adjList.size() - 1);
                depthFirstSearchForward(cert.getIssuerX500Principal(), nextState, builder, adjList, cpList);
                if (!this.pathCompleted) {
                    Debug debug9 = debug;
                    if (debug9 != null) {
                        debug9.println("SunCertPathBuilder.depthFirstSearchForward(): backtracking");
                    }
                    builder.removeFinalCertFromPath(cpList);
                    certs2 = certs2;
                    vertices2 = vertices2;
                    it3 = it3;
                } else {
                    return;
                }
            } catch (GeneralSecurityException gse) {
                Debug debug10 = debug;
                if (debug10 != null) {
                    debug10.println("SunCertPathBuilder.depthFirstSearchForward(): validation failed: " + ((Object) gse));
                    gse.printStackTrace();
                }
                vertex.setThrowable(gse);
                certs2 = certs2;
                vertices2 = vertices2;
                it3 = it3;
            }
        }
    }

    private static List<Vertex> addVertices(Collection<X509Certificate> certs, List<List<Vertex>> adjList) {
        List<Vertex> l = adjList.get(adjList.size() - 1);
        for (X509Certificate cert : certs) {
            l.add(new Vertex(cert));
        }
        return l;
    }

    private static boolean anchorIsTarget(TrustAnchor anchor, CertSelector sel) {
        X509Certificate anchorCert = anchor.getTrustedCert();
        if (anchorCert != null) {
            return sel.match(anchorCert);
        }
        return false;
    }
}
