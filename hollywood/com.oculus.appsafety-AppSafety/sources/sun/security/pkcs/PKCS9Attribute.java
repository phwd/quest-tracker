package sun.security.pkcs;

import android.icu.text.PluralRules;
import java.io.IOException;
import java.io.OutputStream;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import javax.xml.datatype.DatatypeConstants;
import sun.misc.HexDumpEncoder;
import sun.security.util.Debug;
import sun.security.util.DerEncoder;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;
import sun.security.x509.CertificateExtensions;

public class PKCS9Attribute implements DerEncoder {
    private static final Class<?> BYTE_ARRAY_CLASS;
    public static final ObjectIdentifier CHALLENGE_PASSWORD_OID;
    public static final String CHALLENGE_PASSWORD_STR = "ChallengePassword";
    public static final ObjectIdentifier CONTENT_TYPE_OID;
    public static final String CONTENT_TYPE_STR = "ContentType";
    public static final ObjectIdentifier COUNTERSIGNATURE_OID;
    public static final String COUNTERSIGNATURE_STR = "Countersignature";
    public static final ObjectIdentifier EMAIL_ADDRESS_OID;
    public static final String EMAIL_ADDRESS_STR = "EmailAddress";
    public static final ObjectIdentifier EXTENDED_CERTIFICATE_ATTRIBUTES_OID;
    public static final String EXTENDED_CERTIFICATE_ATTRIBUTES_STR = "ExtendedCertificateAttributes";
    public static final ObjectIdentifier EXTENSION_REQUEST_OID;
    public static final String EXTENSION_REQUEST_STR = "ExtensionRequest";
    public static final ObjectIdentifier ISSUER_SERIALNUMBER_OID;
    public static final String ISSUER_SERIALNUMBER_STR = "IssuerAndSerialNumber";
    public static final ObjectIdentifier MESSAGE_DIGEST_OID;
    public static final String MESSAGE_DIGEST_STR = "MessageDigest";
    private static final Hashtable<String, ObjectIdentifier> NAME_OID_TABLE = new Hashtable<>(18);
    private static final Hashtable<ObjectIdentifier, String> OID_NAME_TABLE = new Hashtable<>(16);
    static final ObjectIdentifier[] PKCS9_OIDS = new ObjectIdentifier[18];
    private static final Byte[][] PKCS9_VALUE_TAGS = {null, new Byte[]{new Byte((byte) 22)}, new Byte[]{new Byte((byte) 22), new Byte((byte) 19)}, new Byte[]{new Byte((byte) 6)}, new Byte[]{new Byte((byte) 4)}, new Byte[]{new Byte((byte) 23)}, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 19), new Byte((byte) 20)}, new Byte[]{new Byte((byte) 19), new Byte((byte) 20)}, new Byte[]{new Byte((byte) 49)}, new Byte[]{new Byte((byte) 48)}, null, null, null, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 48)}, new Byte[]{new Byte((byte) 48)}};
    private static final String RSA_PROPRIETARY_STR = "RSAProprietary";
    public static final ObjectIdentifier SIGNATURE_TIMESTAMP_TOKEN_OID;
    public static final String SIGNATURE_TIMESTAMP_TOKEN_STR = "SignatureTimestampToken";
    public static final ObjectIdentifier SIGNING_CERTIFICATE_OID;
    public static final String SIGNING_CERTIFICATE_STR = "SigningCertificate";
    public static final ObjectIdentifier SIGNING_TIME_OID;
    public static final String SIGNING_TIME_STR = "SigningTime";
    private static final boolean[] SINGLE_VALUED = {false, false, false, true, true, true, false, true, false, false, true, false, false, false, true, true, true, true};
    public static final ObjectIdentifier SMIME_CAPABILITY_OID;
    public static final String SMIME_CAPABILITY_STR = "SMIMECapability";
    private static final String SMIME_SIGNING_DESC_STR = "SMIMESigningDesc";
    public static final ObjectIdentifier UNSTRUCTURED_ADDRESS_OID;
    public static final String UNSTRUCTURED_ADDRESS_STR = "UnstructuredAddress";
    public static final ObjectIdentifier UNSTRUCTURED_NAME_OID;
    public static final String UNSTRUCTURED_NAME_STR = "UnstructuredName";
    private static final Class<?>[] VALUE_CLASSES = new Class[18];
    private static final Debug debug = Debug.getInstance("jar");
    private int index;
    private ObjectIdentifier oid;
    private Object value;

    static {
        int i = 1;
        while (true) {
            ObjectIdentifier[] objectIdentifierArr = PKCS9_OIDS;
            if (i < objectIdentifierArr.length - 2) {
                objectIdentifierArr[i] = ObjectIdentifier.newInternal(new int[]{1, 2, 840, 113549, 1, 9, i});
                i++;
            } else {
                objectIdentifierArr[objectIdentifierArr.length - 2] = ObjectIdentifier.newInternal(new int[]{1, 2, DatatypeConstants.MIN_TIMEZONE_OFFSET, 113549, 1, 9, 16, 2, 12});
                ObjectIdentifier[] objectIdentifierArr2 = PKCS9_OIDS;
                objectIdentifierArr2[objectIdentifierArr2.length - 1] = ObjectIdentifier.newInternal(new int[]{1, 2, DatatypeConstants.MIN_TIMEZONE_OFFSET, 113549, 1, 9, 16, 2, 14});
                try {
                    BYTE_ARRAY_CLASS = Class.forName("[B");
                    ObjectIdentifier[] objectIdentifierArr3 = PKCS9_OIDS;
                    EMAIL_ADDRESS_OID = objectIdentifierArr3[1];
                    UNSTRUCTURED_NAME_OID = objectIdentifierArr3[2];
                    CONTENT_TYPE_OID = objectIdentifierArr3[3];
                    MESSAGE_DIGEST_OID = objectIdentifierArr3[4];
                    SIGNING_TIME_OID = objectIdentifierArr3[5];
                    COUNTERSIGNATURE_OID = objectIdentifierArr3[6];
                    CHALLENGE_PASSWORD_OID = objectIdentifierArr3[7];
                    UNSTRUCTURED_ADDRESS_OID = objectIdentifierArr3[8];
                    EXTENDED_CERTIFICATE_ATTRIBUTES_OID = objectIdentifierArr3[9];
                    ISSUER_SERIALNUMBER_OID = objectIdentifierArr3[10];
                    EXTENSION_REQUEST_OID = objectIdentifierArr3[14];
                    SMIME_CAPABILITY_OID = objectIdentifierArr3[15];
                    SIGNING_CERTIFICATE_OID = objectIdentifierArr3[16];
                    SIGNATURE_TIMESTAMP_TOKEN_OID = objectIdentifierArr3[17];
                    NAME_OID_TABLE.put("emailaddress", PKCS9_OIDS[1]);
                    NAME_OID_TABLE.put("unstructuredname", PKCS9_OIDS[2]);
                    NAME_OID_TABLE.put("contenttype", PKCS9_OIDS[3]);
                    NAME_OID_TABLE.put("messagedigest", PKCS9_OIDS[4]);
                    NAME_OID_TABLE.put("signingtime", PKCS9_OIDS[5]);
                    NAME_OID_TABLE.put("countersignature", PKCS9_OIDS[6]);
                    NAME_OID_TABLE.put("challengepassword", PKCS9_OIDS[7]);
                    NAME_OID_TABLE.put("unstructuredaddress", PKCS9_OIDS[8]);
                    NAME_OID_TABLE.put("extendedcertificateattributes", PKCS9_OIDS[9]);
                    NAME_OID_TABLE.put("issuerandserialnumber", PKCS9_OIDS[10]);
                    NAME_OID_TABLE.put("rsaproprietary", PKCS9_OIDS[11]);
                    NAME_OID_TABLE.put("rsaproprietary", PKCS9_OIDS[12]);
                    NAME_OID_TABLE.put("signingdescription", PKCS9_OIDS[13]);
                    NAME_OID_TABLE.put("extensionrequest", PKCS9_OIDS[14]);
                    NAME_OID_TABLE.put("smimecapability", PKCS9_OIDS[15]);
                    NAME_OID_TABLE.put("signingcertificate", PKCS9_OIDS[16]);
                    NAME_OID_TABLE.put("signaturetimestamptoken", PKCS9_OIDS[17]);
                    OID_NAME_TABLE.put(PKCS9_OIDS[1], EMAIL_ADDRESS_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[2], UNSTRUCTURED_NAME_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[3], CONTENT_TYPE_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[4], MESSAGE_DIGEST_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[5], SIGNING_TIME_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[6], COUNTERSIGNATURE_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[7], CHALLENGE_PASSWORD_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[8], UNSTRUCTURED_ADDRESS_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[9], EXTENDED_CERTIFICATE_ATTRIBUTES_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[10], ISSUER_SERIALNUMBER_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[11], RSA_PROPRIETARY_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[12], RSA_PROPRIETARY_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[13], SMIME_SIGNING_DESC_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[14], EXTENSION_REQUEST_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[15], SMIME_CAPABILITY_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[16], SIGNING_CERTIFICATE_STR);
                    OID_NAME_TABLE.put(PKCS9_OIDS[17], SIGNATURE_TIMESTAMP_TOKEN_STR);
                    try {
                        Class<?> str = Class.forName("[Ljava.lang.String;");
                        VALUE_CLASSES[0] = null;
                        VALUE_CLASSES[1] = str;
                        VALUE_CLASSES[2] = str;
                        VALUE_CLASSES[3] = Class.forName("sun.security.util.ObjectIdentifier");
                        VALUE_CLASSES[4] = BYTE_ARRAY_CLASS;
                        VALUE_CLASSES[5] = Class.forName("java.util.Date");
                        VALUE_CLASSES[6] = Class.forName("[Lsun.security.pkcs.SignerInfo;");
                        VALUE_CLASSES[7] = Class.forName("java.lang.String");
                        VALUE_CLASSES[8] = str;
                        VALUE_CLASSES[9] = null;
                        VALUE_CLASSES[10] = null;
                        VALUE_CLASSES[11] = null;
                        VALUE_CLASSES[12] = null;
                        VALUE_CLASSES[13] = null;
                        VALUE_CLASSES[14] = Class.forName("sun.security.x509.CertificateExtensions");
                        VALUE_CLASSES[15] = null;
                        VALUE_CLASSES[16] = null;
                        VALUE_CLASSES[17] = BYTE_ARRAY_CLASS;
                        return;
                    } catch (ClassNotFoundException e) {
                        throw new ExceptionInInitializerError(e.toString());
                    }
                } catch (ClassNotFoundException e2) {
                    throw new ExceptionInInitializerError(e2.toString());
                }
            }
        }
    }

    public PKCS9Attribute(ObjectIdentifier oid2, Object value2) throws IllegalArgumentException {
        init(oid2, value2);
    }

    public PKCS9Attribute(String name, Object value2) throws IllegalArgumentException {
        ObjectIdentifier oid2 = getOID(name);
        if (oid2 != null) {
            init(oid2, value2);
            return;
        }
        throw new IllegalArgumentException("Unrecognized attribute name " + name + " constructing PKCS9Attribute.");
    }

    private void init(ObjectIdentifier oid2, Object value2) throws IllegalArgumentException {
        this.oid = oid2;
        this.index = indexOf(oid2, PKCS9_OIDS, 1);
        int i = this.index;
        Class<?> clazz = i == -1 ? BYTE_ARRAY_CLASS : VALUE_CLASSES[i];
        if (clazz.isInstance(value2)) {
            this.value = value2;
            return;
        }
        throw new IllegalArgumentException("Wrong value class  for attribute " + ((Object) oid2) + " constructing PKCS9Attribute; was " + value2.getClass().toString() + ", should be " + clazz.toString());
    }

    public PKCS9Attribute(DerValue derVal) throws IOException {
        DerInputStream derIn = new DerInputStream(derVal.toByteArray());
        DerValue[] val = derIn.getSequence(2);
        if (derIn.available() != 0) {
            throw new IOException("Excess data parsing PKCS9Attribute");
        } else if (val.length == 2) {
            this.oid = val[0].getOID();
            byte[] content = val[1].toByteArray();
            DerValue[] elems = new DerInputStream(content).getSet(1);
            this.index = indexOf(this.oid, PKCS9_OIDS, 1);
            int i = this.index;
            if (i == -1) {
                Debug debug2 = debug;
                if (debug2 != null) {
                    debug2.println("Unsupported signer attribute: " + ((Object) this.oid));
                }
                this.value = content;
                return;
            }
            if (SINGLE_VALUED[i] && elems.length > 1) {
                throwSingleValuedException();
            }
            for (DerValue derValue : elems) {
                Byte tag = new Byte(derValue.tag);
                if (indexOf(tag, PKCS9_VALUE_TAGS[this.index], 0) == -1) {
                    throwTagException(tag);
                }
            }
            switch (this.index) {
                case 1:
                case 2:
                case 8:
                    String[] values = new String[elems.length];
                    for (int i2 = 0; i2 < elems.length; i2++) {
                        values[i2] = elems[i2].getAsString();
                    }
                    this.value = values;
                    return;
                case 3:
                    this.value = elems[0].getOID();
                    return;
                case 4:
                    this.value = elems[0].getOctetString();
                    return;
                case 5:
                    this.value = new DerInputStream(elems[0].toByteArray()).getUTCTime();
                    return;
                case 6:
                    SignerInfo[] values2 = new SignerInfo[elems.length];
                    for (int i3 = 0; i3 < elems.length; i3++) {
                        values2[i3] = new SignerInfo(elems[i3].toDerInputStream());
                    }
                    this.value = values2;
                    return;
                case 7:
                    this.value = elems[0].getAsString();
                    return;
                case 9:
                    throw new IOException("PKCS9 extended-certificate attribute not supported.");
                case 10:
                    throw new IOException("PKCS9 IssuerAndSerialNumberattribute not supported.");
                case 11:
                case 12:
                    throw new IOException("PKCS9 RSA DSI attributes11 and 12, not supported.");
                case 13:
                    throw new IOException("PKCS9 attribute #13 not supported.");
                case 14:
                    this.value = new CertificateExtensions(new DerInputStream(elems[0].toByteArray()));
                    return;
                case 15:
                    throw new IOException("PKCS9 SMIMECapability attribute not supported.");
                case 16:
                    this.value = new SigningCertificateInfo(elems[0].toByteArray());
                    return;
                case 17:
                    this.value = elems[0].toByteArray();
                    return;
                default:
                    return;
            }
        } else {
            throw new IOException("PKCS9Attribute doesn't have two components");
        }
    }

    @Override // sun.security.util.DerEncoder
    public void derEncode(OutputStream out) throws IOException {
        DerOutputStream temp = new DerOutputStream();
        temp.putOID(this.oid);
        switch (this.index) {
            case -1:
                temp.write((byte[]) this.value);
                break;
            case 1:
            case 2:
                String[] values = (String[]) this.value;
                DerOutputStream[] temps = new DerOutputStream[values.length];
                for (int i = 0; i < values.length; i++) {
                    temps[i] = new DerOutputStream();
                    temps[i].putIA5String(values[i]);
                }
                temp.putOrderedSetOf((byte) 49, temps);
                break;
            case 3:
                DerOutputStream temp2 = new DerOutputStream();
                temp2.putOID((ObjectIdentifier) this.value);
                temp.write((byte) 49, temp2.toByteArray());
                break;
            case 4:
                DerOutputStream temp22 = new DerOutputStream();
                temp22.putOctetString((byte[]) this.value);
                temp.write((byte) 49, temp22.toByteArray());
                break;
            case 5:
                DerOutputStream temp23 = new DerOutputStream();
                temp23.putUTCTime((Date) this.value);
                temp.write((byte) 49, temp23.toByteArray());
                break;
            case 6:
                temp.putOrderedSetOf((byte) 49, (DerEncoder[]) this.value);
                break;
            case 7:
                DerOutputStream temp24 = new DerOutputStream();
                temp24.putPrintableString((String) this.value);
                temp.write((byte) 49, temp24.toByteArray());
                break;
            case 8:
                String[] values2 = (String[]) this.value;
                DerOutputStream[] temps2 = new DerOutputStream[values2.length];
                for (int i2 = 0; i2 < values2.length; i2++) {
                    temps2[i2] = new DerOutputStream();
                    temps2[i2].putPrintableString(values2[i2]);
                }
                temp.putOrderedSetOf((byte) 49, temps2);
                break;
            case 9:
                throw new IOException("PKCS9 extended-certificate attribute not supported.");
            case 10:
                throw new IOException("PKCS9 IssuerAndSerialNumberattribute not supported.");
            case 11:
            case 12:
                throw new IOException("PKCS9 RSA DSI attributes11 and 12, not supported.");
            case 13:
                throw new IOException("PKCS9 attribute #13 not supported.");
            case 14:
                DerOutputStream temp25 = new DerOutputStream();
                try {
                    ((CertificateExtensions) this.value).encode(temp25, true);
                    temp.write((byte) 49, temp25.toByteArray());
                    break;
                } catch (CertificateException ex) {
                    throw new IOException(ex.toString());
                }
            case 15:
                throw new IOException("PKCS9 attribute #15 not supported.");
            case 16:
                throw new IOException("PKCS9 SigningCertificate attribute not supported.");
            case 17:
                temp.write((byte) 49, (byte[]) this.value);
                break;
        }
        DerOutputStream derOut = new DerOutputStream();
        derOut.write((byte) 48, temp.toByteArray());
        out.write(derOut.toByteArray());
    }

    public boolean isKnown() {
        return this.index != -1;
    }

    public Object getValue() {
        return this.value;
    }

    public boolean isSingleValued() {
        int i = this.index;
        return i == -1 || SINGLE_VALUED[i];
    }

    public ObjectIdentifier getOID() {
        return this.oid;
    }

    public String getName() {
        int i = this.index;
        if (i == -1) {
            return this.oid.toString();
        }
        return OID_NAME_TABLE.get(PKCS9_OIDS[i]);
    }

    public static ObjectIdentifier getOID(String name) {
        return NAME_OID_TABLE.get(name.toLowerCase(Locale.ENGLISH));
    }

    public static String getName(ObjectIdentifier oid2) {
        return OID_NAME_TABLE.get(oid2);
    }

    public String toString() {
        Object[] values;
        StringBuffer buf = new StringBuffer(100);
        buf.append("[");
        int i = this.index;
        if (i == -1) {
            buf.append(this.oid.toString());
        } else {
            buf.append(OID_NAME_TABLE.get(PKCS9_OIDS[i]));
        }
        buf.append(PluralRules.KEYWORD_RULE_SEPARATOR);
        int i2 = this.index;
        if (i2 == -1 || SINGLE_VALUED[i2]) {
            Object obj = this.value;
            if (obj instanceof byte[]) {
                buf.append(new HexDumpEncoder().encodeBuffer((byte[]) this.value));
            } else {
                buf.append(obj.toString());
            }
            buf.append("]");
            return buf.toString();
        }
        boolean first = true;
        for (Object obj2 : (Object[]) this.value) {
            if (first) {
                first = false;
            } else {
                buf.append(", ");
            }
            buf.append(obj2.toString());
        }
        return buf.toString();
    }

    static int indexOf(Object obj, Object[] a, int start) {
        for (int i = start; i < a.length; i++) {
            if (obj.equals(a[i])) {
                return i;
            }
        }
        return -1;
    }

    private void throwSingleValuedException() throws IOException {
        throw new IOException("Single-value attribute " + ((Object) this.oid) + " (" + getName() + ") has multiple values.");
    }

    private void throwTagException(Byte tag) throws IOException {
        Byte[] expectedTags = PKCS9_VALUE_TAGS[this.index];
        StringBuffer msg = new StringBuffer(100);
        msg.append("Value of attribute ");
        msg.append(this.oid.toString());
        msg.append(" (");
        msg.append(getName());
        msg.append(") has wrong tag: ");
        msg.append(tag.toString());
        msg.append(".  Expected tags: ");
        msg.append(expectedTags[0].toString());
        for (int i = 1; i < expectedTags.length; i++) {
            msg.append(", ");
            msg.append(expectedTags[i].toString());
        }
        msg.append(".");
        throw new IOException(msg.toString());
    }
}
