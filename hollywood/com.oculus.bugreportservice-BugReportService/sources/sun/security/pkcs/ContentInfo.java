package sun.security.pkcs;

import java.io.IOException;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class ContentInfo {
    public static ObjectIdentifier DATA_OID = ObjectIdentifier.newInternal(data);
    public static ObjectIdentifier DIGESTED_DATA_OID = ObjectIdentifier.newInternal(ddata);
    public static ObjectIdentifier ENCRYPTED_DATA_OID = ObjectIdentifier.newInternal(crdata);
    public static ObjectIdentifier ENVELOPED_DATA_OID = ObjectIdentifier.newInternal(edata);
    public static ObjectIdentifier NETSCAPE_CERT_SEQUENCE_OID = ObjectIdentifier.newInternal(nsdata);
    private static final int[] OLD_DATA = {1, 2, 840, 1113549, 1, 7, 1};
    public static ObjectIdentifier OLD_DATA_OID = ObjectIdentifier.newInternal(OLD_DATA);
    private static final int[] OLD_SDATA = {1, 2, 840, 1113549, 1, 7, 2};
    public static ObjectIdentifier OLD_SIGNED_DATA_OID = ObjectIdentifier.newInternal(OLD_SDATA);
    public static ObjectIdentifier PKCS7_OID = ObjectIdentifier.newInternal(pkcs7);
    public static ObjectIdentifier SIGNED_AND_ENVELOPED_DATA_OID = ObjectIdentifier.newInternal(sedata);
    public static ObjectIdentifier SIGNED_DATA_OID = ObjectIdentifier.newInternal(sdata);
    public static ObjectIdentifier TIMESTAMP_TOKEN_INFO_OID = ObjectIdentifier.newInternal(tstInfo);
    private static int[] crdata = {1, 2, 840, 113549, 1, 7, 6};
    private static int[] data = {1, 2, 840, 113549, 1, 7, 1};
    private static int[] ddata = {1, 2, 840, 113549, 1, 7, 5};
    private static int[] edata = {1, 2, 840, 113549, 1, 7, 3};
    private static int[] nsdata = {2, 16, 840, 1, 113730, 2, 5};
    private static int[] pkcs7 = {1, 2, 840, 113549, 1, 7};
    private static int[] sdata = {1, 2, 840, 113549, 1, 7, 2};
    private static int[] sedata = {1, 2, 840, 113549, 1, 7, 4};
    private static int[] tstInfo = {1, 2, 840, 113549, 1, 9, 16, 1, 4};
    DerValue content;
    ObjectIdentifier contentType;

    public ContentInfo(DerInputStream derInputStream) {
        this(derInputStream, false);
    }

    public ContentInfo(DerInputStream derInputStream, boolean z) {
        DerValue[] sequence = derInputStream.getSequence(2);
        this.contentType = new DerInputStream(sequence[0].toByteArray()).getOID();
        if (z) {
            this.content = sequence[1];
        } else if (sequence.length > 1) {
            this.content = new DerInputStream(sequence[1].toByteArray()).getSet(1, true)[0];
        }
    }

    public DerValue getContent() {
        return this.content;
    }

    public byte[] getData() {
        if (this.contentType.equals(DATA_OID) || this.contentType.equals(OLD_DATA_OID) || this.contentType.equals(TIMESTAMP_TOKEN_INFO_OID)) {
            DerValue derValue = this.content;
            if (derValue == null) {
                return null;
            }
            return derValue.getOctetString();
        }
        throw new IOException("content type is not DATA: " + this.contentType);
    }

    public byte[] getContentBytes() {
        DerValue derValue = this.content;
        if (derValue == null) {
            return null;
        }
        return new DerInputStream(derValue.toByteArray()).getOctetString();
    }

    public String toString() {
        return ("" + "Content Info Sequence\n\tContent type: " + this.contentType + "\n") + "\tContent: " + this.content;
    }
}
