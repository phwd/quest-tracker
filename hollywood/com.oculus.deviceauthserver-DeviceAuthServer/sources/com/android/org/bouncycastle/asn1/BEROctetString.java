package com.android.org.bouncycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

public class BEROctetString extends ASN1OctetString {
    private static final int DEFAULT_LENGTH = 1000;
    private final int chunkSize;
    private final ASN1OctetString[] octs;

    private static byte[] toBytes(ASN1OctetString[] octs2) {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        for (int i = 0; i != octs2.length; i++) {
            try {
                bOut.write(((DEROctetString) octs2[i]).getOctets());
            } catch (ClassCastException e) {
                throw new IllegalArgumentException(octs2[i].getClass().getName() + " found in input should only contain DEROctetString");
            } catch (IOException e2) {
                throw new IllegalArgumentException("exception converting octets " + e2.toString());
            }
        }
        return bOut.toByteArray();
    }

    public BEROctetString(byte[] string) {
        this(string, 1000);
    }

    public BEROctetString(ASN1OctetString[] octs2) {
        this(octs2, 1000);
    }

    public BEROctetString(byte[] string, int chunkSize2) {
        this(string, null, chunkSize2);
    }

    public BEROctetString(ASN1OctetString[] octs2, int chunkSize2) {
        this(toBytes(octs2), octs2, chunkSize2);
    }

    private BEROctetString(byte[] string, ASN1OctetString[] octs2, int chunkSize2) {
        super(string);
        this.octs = octs2;
        this.chunkSize = chunkSize2;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1OctetString
    public byte[] getOctets() {
        return this.string;
    }

    public Enumeration getObjects() {
        if (this.octs == null) {
            return generateOcts().elements();
        }
        return new Enumeration() {
            /* class com.android.org.bouncycastle.asn1.BEROctetString.AnonymousClass1 */
            int counter = 0;

            public boolean hasMoreElements() {
                return this.counter < BEROctetString.this.octs.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                ASN1OctetString[] aSN1OctetStringArr = BEROctetString.this.octs;
                int i = this.counter;
                this.counter = i + 1;
                return aSN1OctetStringArr[i];
            }
        };
    }

    private Vector generateOcts() {
        int end;
        Vector vec = new Vector();
        int i = 0;
        while (i < this.string.length) {
            if (this.chunkSize + i > this.string.length) {
                end = this.string.length;
            } else {
                end = this.chunkSize + i;
            }
            byte[] nStr = new byte[(end - i)];
            System.arraycopy(this.string, i, nStr, 0, nStr.length);
            vec.addElement(new DEROctetString(nStr));
            i += this.chunkSize;
        }
        return vec;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return true;
    }

    /* access modifiers changed from: package-private */
    @Override // com.android.org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() throws IOException {
        int length = 0;
        Enumeration e = getObjects();
        while (e.hasMoreElements()) {
            length += ((ASN1Encodable) e.nextElement()).toASN1Primitive().encodedLength();
        }
        return length + 2 + 2;
    }

    @Override // com.android.org.bouncycastle.asn1.ASN1OctetString, com.android.org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream out) throws IOException {
        out.write(36);
        out.write(128);
        Enumeration e = getObjects();
        while (e.hasMoreElements()) {
            out.writeObject((ASN1Encodable) e.nextElement());
        }
        out.write(0);
        out.write(0);
    }

    static BEROctetString fromSequence(ASN1Sequence seq) {
        ASN1OctetString[] v = new ASN1OctetString[seq.size()];
        Enumeration e = seq.getObjects();
        int index = 0;
        while (e.hasMoreElements()) {
            v[index] = (ASN1OctetString) e.nextElement();
            index++;
        }
        return new BEROctetString(v);
    }
}
