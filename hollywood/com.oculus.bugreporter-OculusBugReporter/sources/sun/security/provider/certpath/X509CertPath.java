package sun.security.provider.certpath;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import sun.security.pkcs.ContentInfo;
import sun.security.pkcs.PKCS7;
import sun.security.pkcs.SignerInfo;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.x509.AlgorithmId;

public class X509CertPath extends CertPath {
    private static final String COUNT_ENCODING = "count";
    private static final String PKCS7_ENCODING = "PKCS7";
    private static final String PKIPATH_ENCODING = "PkiPath";
    private static final Collection<String> encodingList;
    private static final long serialVersionUID = 4989800333263052980L;
    private List<X509Certificate> certs;

    static {
        List<String> list = new ArrayList<>(2);
        list.add(PKIPATH_ENCODING);
        list.add(PKCS7_ENCODING);
        encodingList = Collections.unmodifiableCollection(list);
    }

    public X509CertPath(List<? extends Certificate> certs2) throws CertificateException {
        super("X.509");
        for (Object obj : certs2) {
            if (!(obj instanceof X509Certificate)) {
                throw new CertificateException("List is not all X509Certificates: " + obj.getClass().getName());
            }
        }
        this.certs = Collections.unmodifiableList(new ArrayList(certs2));
    }

    public X509CertPath(InputStream is) throws CertificateException {
        this(is, PKIPATH_ENCODING);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x003d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public X509CertPath(java.io.InputStream r4, java.lang.String r5) throws java.security.cert.CertificateException {
        /*
            r3 = this;
            java.lang.String r0 = "X.509"
            r3.<init>(r0)
            int r0 = r5.hashCode()
            r1 = 76183020(0x48a75ec, float:3.255194E-36)
            r2 = 1
            if (r0 == r1) goto L_0x001f
            r1 = 1148619507(0x44768af3, float:986.1711)
            if (r0 == r1) goto L_0x0015
        L_0x0014:
            goto L_0x0029
        L_0x0015:
            java.lang.String r0 = "PkiPath"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0014
            r0 = 0
            goto L_0x002a
        L_0x001f:
            java.lang.String r0 = "PKCS7"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0014
            r0 = r2
            goto L_0x002a
        L_0x0029:
            r0 = -1
        L_0x002a:
            if (r0 == 0) goto L_0x003d
            if (r0 != r2) goto L_0x0035
            java.util.List r0 = parsePKCS7(r4)
            r3.certs = r0
            goto L_0x0044
        L_0x0035:
            java.security.cert.CertificateException r0 = new java.security.cert.CertificateException
            java.lang.String r1 = "unsupported encoding"
            r0.<init>(r1)
            throw r0
        L_0x003d:
            java.util.List r0 = parsePKIPATH(r4)
            r3.certs = r0
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.X509CertPath.<init>(java.io.InputStream, java.lang.String):void");
    }

    private static List<X509Certificate> parsePKIPATH(InputStream is) throws CertificateException {
        if (is != null) {
            try {
                DerValue[] seq = new DerInputStream(readAllBytes(is)).getSequence(3);
                if (seq.length == 0) {
                    return Collections.emptyList();
                }
                CertificateFactory certFac = CertificateFactory.getInstance("X.509");
                List<X509Certificate> certList = new ArrayList<>(seq.length);
                for (int i = seq.length - 1; i >= 0; i--) {
                    certList.add((X509Certificate) certFac.generateCertificate(new ByteArrayInputStream(seq[i].toByteArray())));
                }
                return Collections.unmodifiableList(certList);
            } catch (IOException ioe) {
                throw new CertificateException("IOException parsing PkiPath data: " + ((Object) ioe), ioe);
            }
        } else {
            throw new CertificateException("input stream is null");
        }
    }

    private static List<X509Certificate> parsePKCS7(InputStream is) throws CertificateException {
        List<X509Certificate> certList;
        if (is != null) {
            try {
                if (!is.markSupported()) {
                    is = new ByteArrayInputStream(readAllBytes(is));
                }
                X509Certificate[] certArray = new PKCS7(is).getCertificates();
                if (certArray != null) {
                    certList = Arrays.asList(certArray);
                } else {
                    certList = new ArrayList<>(0);
                }
                return Collections.unmodifiableList(certList);
            } catch (IOException ioe) {
                throw new CertificateException("IOException parsing PKCS7 data: " + ((Object) ioe));
            }
        } else {
            throw new CertificateException("input stream is null");
        }
    }

    private static byte[] readAllBytes(InputStream is) throws IOException {
        byte[] buffer = new byte[8192];
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
        while (true) {
            int n = is.read(buffer);
            if (n == -1) {
                return baos.toByteArray();
            }
            baos.write(buffer, 0, n);
        }
    }

    @Override // java.security.cert.CertPath
    public byte[] getEncoded() throws CertificateEncodingException {
        return encodePKIPATH();
    }

    private byte[] encodePKIPATH() throws CertificateEncodingException {
        List<X509Certificate> list = this.certs;
        ListIterator<X509Certificate> li = list.listIterator(list.size());
        try {
            DerOutputStream bytes = new DerOutputStream();
            while (li.hasPrevious()) {
                X509Certificate cert = li.previous();
                if (this.certs.lastIndexOf(cert) == this.certs.indexOf(cert)) {
                    bytes.write(cert.getEncoded());
                } else {
                    throw new CertificateEncodingException("Duplicate Certificate");
                }
            }
            DerOutputStream derout = new DerOutputStream();
            derout.write((byte) 48, bytes);
            return derout.toByteArray();
        } catch (IOException ioe) {
            throw new CertificateEncodingException("IOException encoding PkiPath data: " + ((Object) ioe), ioe);
        }
    }

    private byte[] encodePKCS7() throws CertificateEncodingException {
        ContentInfo contentInfo = new ContentInfo(ContentInfo.DATA_OID, (DerValue) null);
        List<X509Certificate> list = this.certs;
        PKCS7 p7 = new PKCS7(new AlgorithmId[0], contentInfo, (X509Certificate[]) list.toArray(new X509Certificate[list.size()]), new SignerInfo[0]);
        DerOutputStream derout = new DerOutputStream();
        try {
            p7.encodeSignedData(derout);
            return derout.toByteArray();
        } catch (IOException ioe) {
            throw new CertificateEncodingException(ioe.getMessage());
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0027  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    @Override // java.security.cert.CertPath
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getEncoded(java.lang.String r4) throws java.security.cert.CertificateEncodingException {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = 76183020(0x48a75ec, float:3.255194E-36)
            r2 = 1
            if (r0 == r1) goto L_0x001a
            r1 = 1148619507(0x44768af3, float:986.1711)
            if (r0 == r1) goto L_0x0010
        L_0x000f:
            goto L_0x0024
        L_0x0010:
            java.lang.String r0 = "PkiPath"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x000f
            r0 = 0
            goto L_0x0025
        L_0x001a:
            java.lang.String r0 = "PKCS7"
            boolean r0 = r4.equals(r0)
            if (r0 == 0) goto L_0x000f
            r0 = r2
            goto L_0x0025
        L_0x0024:
            r0 = -1
        L_0x0025:
            if (r0 == 0) goto L_0x0036
            if (r0 != r2) goto L_0x002e
            byte[] r0 = r3.encodePKCS7()
            return r0
        L_0x002e:
            java.security.cert.CertificateEncodingException r0 = new java.security.cert.CertificateEncodingException
            java.lang.String r1 = "unsupported encoding"
            r0.<init>(r1)
            throw r0
        L_0x0036:
            byte[] r0 = r3.encodePKIPATH()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.security.provider.certpath.X509CertPath.getEncoded(java.lang.String):byte[]");
    }

    public static Iterator<String> getEncodingsStatic() {
        return encodingList.iterator();
    }

    @Override // java.security.cert.CertPath
    public Iterator<String> getEncodings() {
        return getEncodingsStatic();
    }

    @Override // java.security.cert.CertPath
    public List<X509Certificate> getCertificates() {
        return this.certs;
    }
}
