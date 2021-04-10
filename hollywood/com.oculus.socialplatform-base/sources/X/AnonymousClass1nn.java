package X;

import java.security.Principal;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.net.ssl.SSLSessionContext;
import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

/* renamed from: X.1nn  reason: invalid class name */
public final class AnonymousClass1nn implements SSLSession {
    public long A00;
    public AnonymousClass1np A01;
    public LinkedHashSet<AnonymousClass1np> A02 = new LinkedHashSet<>();
    public Map<Byte, Certificate[]> A03 = new HashMap();
    public int A04;
    public long A05;
    public AnonymousClass1oB A06;
    public String A07;
    public String A08;
    public boolean A09 = true;
    public byte[] A0A;
    public Certificate[] A0B;
    public final Map<String, Object> A0C = new HashMap();

    public final int getApplicationBufferSize() {
        return 16384;
    }

    public final int getPacketBufferSize() {
        return 16645;
    }

    public final String getProtocol() {
        return "TLSv1.3";
    }

    public final void invalidate() {
        this.A09 = false;
    }

    public final Byte A00() {
        Certificate[] certificateArr = this.A0B;
        if (certificateArr == null) {
            return null;
        }
        byte b = 0;
        for (Certificate certificate : certificateArr) {
            for (byte b2 : certificate.getPublicKey().getEncoded()) {
                b = (byte) (b ^ b2);
            }
        }
        return Byte.valueOf(b);
    }

    public final void A01(Certificate[] certificateArr) {
        Certificate[] certificateArr2;
        if (certificateArr != null) {
            certificateArr2 = (Certificate[]) certificateArr.clone();
        } else {
            certificateArr2 = null;
        }
        this.A0B = certificateArr2;
    }

    public final String getCipherSuite() {
        return this.A07;
    }

    public final long getCreationTime() {
        return this.A05;
    }

    public final byte[] getId() {
        byte[] bArr = this.A0A;
        if (bArr != null) {
            return (byte[]) bArr.clone();
        }
        return null;
    }

    public final long getLastAccessedTime() {
        return this.A00;
    }

    public final Certificate[] getLocalCertificates() {
        return null;
    }

    public final Principal getLocalPrincipal() {
        return null;
    }

    @Override // javax.net.ssl.SSLSession
    public final X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
        Certificate[] certificateArr = this.A0B;
        if (certificateArr == null || (r7 = certificateArr.length) == 0) {
            throw new SSLPeerUnverifiedException("No peer certificates found");
        }
        ArrayList arrayList = new ArrayList();
        for (Certificate certificate : certificateArr) {
            try {
                arrayList.add(X509Certificate.getInstance(certificate.getEncoded()));
            } catch (CertificateEncodingException | CertificateException e) {
                Integer num = AnonymousClass007.A01;
                StringBuilder sb = new StringBuilder("Could not parse certificates. Exception ");
                sb.append(e);
                AnonymousClass1Ou.A00(num, sb.toString());
            }
        }
        return (X509Certificate[]) arrayList.toArray(new X509Certificate[0]);
    }

    @Override // javax.net.ssl.SSLSession
    public final Certificate[] getPeerCertificates() {
        Certificate[] certificateArr = this.A0B;
        if (certificateArr != null) {
            return (Certificate[]) certificateArr.clone();
        }
        return null;
    }

    public final String getPeerHost() {
        return this.A08;
    }

    public final int getPeerPort() {
        return this.A04;
    }

    @Override // javax.net.ssl.SSLSession
    public final Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        Certificate[] certificateArr = this.A0B;
        if (certificateArr != null && certificateArr.length != 0) {
            return ((java.security.cert.X509Certificate) certificateArr[0]).getSubjectX500Principal();
        }
        throw new SSLPeerUnverifiedException("No peer certificates found.");
    }

    public final SSLSessionContext getSessionContext() {
        return this.A06;
    }

    public final Object getValue(String str) {
        if (str != null) {
            return this.A0C.get(str);
        }
        throw new IllegalArgumentException("key cannot be null.");
    }

    public final String[] getValueNames() {
        return (String[]) this.A0C.keySet().toArray(new String[0]);
    }

    public final boolean isValid() {
        AnonymousClass1np[] r6;
        long currentTimeMillis;
        boolean z = this.A09;
        if (!z) {
            return z;
        }
        HashSet hashSet = new HashSet();
        LinkedHashSet<AnonymousClass1np> linkedHashSet = this.A02;
        if (linkedHashSet == null) {
            r6 = null;
        } else {
            r6 = (AnonymousClass1np[]) linkedHashSet.toArray(new AnonymousClass1np[0]);
        }
        if (r6 != null) {
            for (AnonymousClass1np r8 : r6) {
                if (r8.useTestTime) {
                    currentTimeMillis = 3600000;
                } else {
                    currentTimeMillis = System.currentTimeMillis();
                }
                if (currentTimeMillis - r8.ticketIssuedTime > r8.ticketLifetime || !r8.sni.equals(this.A08)) {
                    this.A02.remove(r8);
                } else {
                    hashSet.add(Byte.valueOf(r8.certsID));
                }
            }
        }
        Iterator<Byte> it = this.A03.keySet().iterator();
        while (it.hasNext()) {
            if (!hashSet.contains(it.next())) {
                it.remove();
            }
        }
        LinkedHashSet<AnonymousClass1np> linkedHashSet2 = this.A02;
        if (linkedHashSet2 == null || linkedHashSet2.isEmpty()) {
            this.A09 = false;
        }
        return this.A09;
    }

    public final void putValue(String str, Object obj) {
        if (str == null || obj == null) {
            throw new IllegalArgumentException("key and value cannot be null.");
        }
        Object put = this.A0C.put(str, obj);
        if (put instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) put).valueUnbound(new SSLSessionBindingEvent(this, str));
        }
        if (obj instanceof SSLSessionBindingListener) {
            ((SSLSessionBindingListener) obj).valueBound(new SSLSessionBindingEvent(this, str));
        }
    }

    public final void removeValue(String str) {
        if (str != null) {
            Object remove = this.A0C.remove(str);
            if (remove instanceof SSLSessionBindingListener) {
                ((SSLSessionBindingListener) remove).valueUnbound(new SSLSessionBindingEvent(this, str));
                return;
            }
            return;
        }
        throw new IllegalArgumentException("key cannot be null");
    }

    public AnonymousClass1nn(AnonymousClass1oB r3, String str, int i, String str2) throws AnonymousClass25A {
        long currentTimeMillis = System.currentTimeMillis();
        this.A00 = currentTimeMillis;
        this.A05 = currentTimeMillis;
        this.A06 = r3;
        this.A08 = str;
        this.A04 = i;
        this.A07 = str2;
        this.A0A = AnonymousClass24K.A07(str, i, str2);
    }
}
