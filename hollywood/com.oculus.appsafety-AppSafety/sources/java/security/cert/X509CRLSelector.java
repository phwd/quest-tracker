package java.security.cert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import javax.security.auth.x500.X500Principal;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.x509.CRLNumberExtension;
import sun.security.x509.X500Name;

public class X509CRLSelector implements CRLSelector {
    private static final Debug debug = Debug.getInstance("certpath");
    private X509Certificate certChecking;
    private Date dateAndTime;
    private HashSet<Object> issuerNames;
    private HashSet<X500Principal> issuerX500Principals;
    private BigInteger maxCRL;
    private BigInteger minCRL;
    private long skew = 0;

    static {
        CertPathHelperImpl.initialize();
    }

    public void setIssuers(Collection<X500Principal> issuers) {
        if (issuers == null || issuers.isEmpty()) {
            this.issuerNames = null;
            this.issuerX500Principals = null;
            return;
        }
        this.issuerX500Principals = new HashSet<>(issuers);
        this.issuerNames = new HashSet<>();
        Iterator<X500Principal> it = this.issuerX500Principals.iterator();
        while (it.hasNext()) {
            this.issuerNames.add(it.next().getEncoded());
        }
    }

    public void setIssuerNames(Collection<?> names) throws IOException {
        if (names == null || names.size() == 0) {
            this.issuerNames = null;
            this.issuerX500Principals = null;
            return;
        }
        HashSet<Object> tempNames = cloneAndCheckIssuerNames(names);
        this.issuerX500Principals = parseIssuerNames(tempNames);
        this.issuerNames = tempNames;
    }

    public void addIssuer(X500Principal issuer) {
        addIssuerNameInternal(issuer.getEncoded(), issuer);
    }

    public void addIssuerName(String name) throws IOException {
        addIssuerNameInternal(name, new X500Name(name).asX500Principal());
    }

    public void addIssuerName(byte[] name) throws IOException {
        addIssuerNameInternal(name.clone(), new X500Name(name).asX500Principal());
    }

    private void addIssuerNameInternal(Object name, X500Principal principal) {
        if (this.issuerNames == null) {
            this.issuerNames = new HashSet<>();
        }
        if (this.issuerX500Principals == null) {
            this.issuerX500Principals = new HashSet<>();
        }
        this.issuerNames.add(name);
        this.issuerX500Principals.add(principal);
    }

    private static HashSet<Object> cloneAndCheckIssuerNames(Collection<?> names) throws IOException {
        HashSet<Object> namesCopy = new HashSet<>();
        for (Object nameObject : names) {
            if (!(nameObject instanceof byte[]) && !(nameObject instanceof String)) {
                throw new IOException("name not byte array or String");
            } else if (nameObject instanceof byte[]) {
                namesCopy.add(((byte[]) nameObject).clone());
            } else {
                namesCopy.add(nameObject);
            }
        }
        return namesCopy;
    }

    private static HashSet<Object> cloneIssuerNames(Collection<Object> names) {
        try {
            return cloneAndCheckIssuerNames(names);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private static HashSet<X500Principal> parseIssuerNames(Collection<Object> names) throws IOException {
        HashSet<X500Principal> x500Principals = new HashSet<>();
        for (Object nameObject : names) {
            if (nameObject instanceof String) {
                x500Principals.add(new X500Name((String) nameObject).asX500Principal());
            } else {
                try {
                    x500Principals.add(new X500Principal((byte[]) nameObject));
                } catch (IllegalArgumentException e) {
                    throw ((IOException) new IOException("Invalid name").initCause(e));
                }
            }
        }
        return x500Principals;
    }

    public void setMinCRLNumber(BigInteger minCRL2) {
        this.minCRL = minCRL2;
    }

    public void setMaxCRLNumber(BigInteger maxCRL2) {
        this.maxCRL = maxCRL2;
    }

    public void setDateAndTime(Date dateAndTime2) {
        if (dateAndTime2 == null) {
            this.dateAndTime = null;
        } else {
            this.dateAndTime = new Date(dateAndTime2.getTime());
        }
        this.skew = 0;
    }

    /* access modifiers changed from: package-private */
    public void setDateAndTime(Date dateAndTime2, long skew2) {
        this.dateAndTime = dateAndTime2 == null ? null : new Date(dateAndTime2.getTime());
        this.skew = skew2;
    }

    public void setCertificateChecking(X509Certificate cert) {
        this.certChecking = cert;
    }

    public Collection<X500Principal> getIssuers() {
        HashSet<X500Principal> hashSet = this.issuerX500Principals;
        if (hashSet == null) {
            return null;
        }
        return Collections.unmodifiableCollection(hashSet);
    }

    public Collection<Object> getIssuerNames() {
        HashSet<Object> hashSet = this.issuerNames;
        if (hashSet == null) {
            return null;
        }
        return cloneIssuerNames(hashSet);
    }

    public BigInteger getMinCRL() {
        return this.minCRL;
    }

    public BigInteger getMaxCRL() {
        return this.maxCRL;
    }

    public Date getDateAndTime() {
        Date date = this.dateAndTime;
        if (date == null) {
            return null;
        }
        return (Date) date.clone();
    }

    public X509Certificate getCertificateChecking() {
        return this.certChecking;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("X509CRLSelector: [\n");
        if (this.issuerNames != null) {
            sb.append("  IssuerNames:\n");
            Iterator<Object> i = this.issuerNames.iterator();
            while (i.hasNext()) {
                sb.append("    " + i.next() + "\n");
            }
        }
        if (this.minCRL != null) {
            sb.append("  minCRLNumber: " + ((Object) this.minCRL) + "\n");
        }
        if (this.maxCRL != null) {
            sb.append("  maxCRLNumber: " + ((Object) this.maxCRL) + "\n");
        }
        if (this.dateAndTime != null) {
            sb.append("  dateAndTime: " + ((Object) this.dateAndTime) + "\n");
        }
        if (this.certChecking != null) {
            sb.append("  Certificate being checked: " + ((Object) this.certChecking) + "\n");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // java.security.cert.CRLSelector
    public boolean match(CRL crl) {
        Debug debug2;
        if (!(crl instanceof X509CRL)) {
            return false;
        }
        X509CRL xcrl = (X509CRL) crl;
        if (this.issuerNames != null) {
            X500Principal issuer = xcrl.getIssuerX500Principal();
            Iterator<X500Principal> i = this.issuerX500Principals.iterator();
            boolean found = false;
            while (!found && i.hasNext()) {
                if (i.next().equals(issuer)) {
                    found = true;
                }
            }
            if (!found) {
                Debug debug3 = debug;
                if (debug3 != null) {
                    debug3.println("X509CRLSelector.match: issuer DNs don't match");
                }
                return false;
            }
        }
        if (!(this.minCRL == null && this.maxCRL == null)) {
            byte[] crlNumExtVal = xcrl.getExtensionValue("2.5.29.20");
            if (crlNumExtVal == null && (debug2 = debug) != null) {
                debug2.println("X509CRLSelector.match: no CRLNumber");
            }
            try {
                BigInteger crlNum = new CRLNumberExtension(Boolean.FALSE, new DerInputStream(crlNumExtVal).getOctetString()).get("value");
                BigInteger bigInteger = this.minCRL;
                if (bigInteger == null || crlNum.compareTo(bigInteger) >= 0) {
                    BigInteger bigInteger2 = this.maxCRL;
                    if (bigInteger2 != null && crlNum.compareTo(bigInteger2) > 0) {
                        Debug debug4 = debug;
                        if (debug4 != null) {
                            debug4.println("X509CRLSelector.match: CRLNumber too large");
                        }
                        return false;
                    }
                } else {
                    Debug debug5 = debug;
                    if (debug5 != null) {
                        debug5.println("X509CRLSelector.match: CRLNumber too small");
                    }
                    return false;
                }
            } catch (IOException e) {
                Debug debug6 = debug;
                if (debug6 != null) {
                    debug6.println("X509CRLSelector.match: exception in decoding CRL number");
                }
                return false;
            }
        }
        if (this.dateAndTime == null) {
            return true;
        }
        Date crlThisUpdate = xcrl.getThisUpdate();
        Date nextUpdate = xcrl.getNextUpdate();
        if (nextUpdate == null) {
            Debug debug7 = debug;
            if (debug7 != null) {
                debug7.println("X509CRLSelector.match: nextUpdate null");
            }
            return false;
        }
        Date nowPlusSkew = this.dateAndTime;
        Date nowMinusSkew = this.dateAndTime;
        if (this.skew > 0) {
            nowPlusSkew = new Date(this.dateAndTime.getTime() + this.skew);
            nowMinusSkew = new Date(this.dateAndTime.getTime() - this.skew);
        }
        if (!nowMinusSkew.after(nextUpdate) && !nowPlusSkew.before(crlThisUpdate)) {
            return true;
        }
        Debug debug8 = debug;
        if (debug8 != null) {
            debug8.println("X509CRLSelector.match: update out-of-range");
        }
        return false;
    }

    @Override // java.security.cert.CRLSelector
    public Object clone() {
        try {
            X509CRLSelector copy = (X509CRLSelector) super.clone();
            if (this.issuerNames != null) {
                copy.issuerNames = new HashSet<>(this.issuerNames);
                copy.issuerX500Principals = new HashSet<>(this.issuerX500Principals);
            }
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e.toString(), e);
        }
    }
}
