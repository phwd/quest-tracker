package sun.security.provider.certpath;

import java.io.IOException;
import java.math.BigInteger;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Date;
import sun.security.util.Debug;
import sun.security.util.DerInputStream;
import sun.security.x509.AuthorityKeyIdentifierExtension;
import sun.security.x509.SerialNumber;

class AdaptableX509CertSelector extends X509CertSelector {
    private static final Debug debug = Debug.getInstance("certpath");
    private Date endDate;
    private BigInteger serial;
    private byte[] ski;
    private Date startDate;

    AdaptableX509CertSelector() {
    }

    /* access modifiers changed from: package-private */
    public void setValidityPeriod(Date startDate2, Date endDate2) {
        this.startDate = startDate2;
        this.endDate = endDate2;
    }

    @Override // java.security.cert.X509CertSelector
    public void setSubjectKeyIdentifier(byte[] subjectKeyID) {
        throw new IllegalArgumentException();
    }

    @Override // java.security.cert.X509CertSelector
    public void setSerialNumber(BigInteger serial2) {
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: package-private */
    public void setSkiAndSerialNumber(AuthorityKeyIdentifierExtension ext) throws IOException {
        this.ski = null;
        this.serial = null;
        if (ext != null) {
            this.ski = ext.getEncodedKeyIdentifier();
            SerialNumber asn = (SerialNumber) ext.get(AuthorityKeyIdentifierExtension.SERIAL_NUMBER);
            if (asn != null) {
                this.serial = asn.getNumber();
            }
        }
    }

    @Override // java.security.cert.X509CertSelector, java.security.cert.CertSelector
    public boolean match(Certificate cert) {
        X509Certificate xcert = (X509Certificate) cert;
        if (!matchSubjectKeyID(xcert)) {
            return false;
        }
        int version = xcert.getVersion();
        BigInteger bigInteger = this.serial;
        if (bigInteger != null && version > 2 && !bigInteger.equals(xcert.getSerialNumber())) {
            return false;
        }
        if (version < 3) {
            Date date = this.startDate;
            if (date != null) {
                try {
                    xcert.checkValidity(date);
                } catch (CertificateException e) {
                    return false;
                }
            }
            Date date2 = this.endDate;
            if (date2 != null) {
                try {
                    xcert.checkValidity(date2);
                } catch (CertificateException e2) {
                    return false;
                }
            }
        }
        if (!super.match(cert)) {
            return false;
        }
        return true;
    }

    private boolean matchSubjectKeyID(X509Certificate xcert) {
        if (this.ski == null) {
            return true;
        }
        try {
            byte[] extVal = xcert.getExtensionValue("2.5.29.14");
            if (extVal == null) {
                if (debug != null) {
                    Debug debug2 = debug;
                    debug2.println("AdaptableX509CertSelector.match: no subject key ID extension. Subject: " + ((Object) xcert.getSubjectX500Principal()));
                }
                return true;
            }
            byte[] certSubjectKeyID = new DerInputStream(extVal).getOctetString();
            if (certSubjectKeyID != null) {
                if (Arrays.equals(this.ski, certSubjectKeyID)) {
                    return true;
                }
            }
            if (debug != null) {
                Debug debug3 = debug;
                debug3.println("AdaptableX509CertSelector.match: subject key IDs don't match. Expected: " + Arrays.toString(this.ski) + " Cert's: " + Arrays.toString(certSubjectKeyID));
            }
            return false;
        } catch (IOException e) {
            Debug debug4 = debug;
            if (debug4 != null) {
                debug4.println("AdaptableX509CertSelector.match: exception in subject key ID check");
            }
            return false;
        }
    }

    @Override // java.security.cert.X509CertSelector, java.security.cert.CertSelector
    public Object clone() {
        AdaptableX509CertSelector copy = (AdaptableX509CertSelector) super.clone();
        Date date = this.startDate;
        if (date != null) {
            copy.startDate = (Date) date.clone();
        }
        Date date2 = this.endDate;
        if (date2 != null) {
            copy.endDate = (Date) date2.clone();
        }
        byte[] bArr = this.ski;
        if (bArr != null) {
            copy.ski = (byte[]) bArr.clone();
        }
        return copy;
    }
}
