package sun.security.x509;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.Principal;
import java.security.PrivilegedExceptionAction;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.security.auth.x500.X500Principal;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;
import sun.security.util.ObjectIdentifier;

public class X500Name implements GeneralNameInterface, Principal {
    private static final int[] DNQUALIFIER_DATA = {2, 5, 4, 46};
    public static final ObjectIdentifier DNQUALIFIER_OID = intern(ObjectIdentifier.newInternal(DNQUALIFIER_DATA));
    private static final int[] DOMAIN_COMPONENT_DATA = {0, 9, 2342, 19200300, 100, 1, 25};
    public static final ObjectIdentifier DOMAIN_COMPONENT_OID = intern(ObjectIdentifier.newInternal(DOMAIN_COMPONENT_DATA));
    private static final int[] GENERATIONQUALIFIER_DATA = {2, 5, 4, 44};
    public static final ObjectIdentifier GENERATIONQUALIFIER_OID = intern(ObjectIdentifier.newInternal(GENERATIONQUALIFIER_DATA));
    private static final int[] GIVENNAME_DATA = {2, 5, 4, 42};
    public static final ObjectIdentifier GIVENNAME_OID = intern(ObjectIdentifier.newInternal(GIVENNAME_DATA));
    private static final int[] INITIALS_DATA = {2, 5, 4, 43};
    public static final ObjectIdentifier INITIALS_OID = intern(ObjectIdentifier.newInternal(INITIALS_DATA));
    private static final int[] SERIALNUMBER_DATA = {2, 5, 4, 5};
    public static final ObjectIdentifier SERIALNUMBER_OID = intern(ObjectIdentifier.newInternal(SERIALNUMBER_DATA));
    private static final int[] SURNAME_DATA = {2, 5, 4, 4};
    public static final ObjectIdentifier SURNAME_OID = intern(ObjectIdentifier.newInternal(SURNAME_DATA));
    private static final int[] commonName_data = {2, 5, 4, 3};
    public static final ObjectIdentifier commonName_oid = intern(ObjectIdentifier.newInternal(commonName_data));
    private static final int[] countryName_data = {2, 5, 4, 6};
    public static final ObjectIdentifier countryName_oid = intern(ObjectIdentifier.newInternal(countryName_data));
    private static final Map internedOIDs = new HashMap();
    private static final int[] ipAddress_data = {1, 3, 6, 1, 4, 1, 42, 2, 11, 2, 1};
    public static final ObjectIdentifier ipAddress_oid = intern(ObjectIdentifier.newInternal(ipAddress_data));
    private static final int[] localityName_data = {2, 5, 4, 7};
    public static final ObjectIdentifier localityName_oid = intern(ObjectIdentifier.newInternal(localityName_data));
    private static final int[] orgName_data = {2, 5, 4, 10};
    public static final ObjectIdentifier orgName_oid = intern(ObjectIdentifier.newInternal(orgName_data));
    private static final int[] orgUnitName_data = {2, 5, 4, 11};
    public static final ObjectIdentifier orgUnitName_oid = intern(ObjectIdentifier.newInternal(orgUnitName_data));
    private static final Constructor principalConstructor;
    private static final Field principalField;
    private static final int[] stateName_data = {2, 5, 4, 8};
    public static final ObjectIdentifier stateName_oid = intern(ObjectIdentifier.newInternal(stateName_data));
    private static final int[] streetAddress_data = {2, 5, 4, 9};
    public static final ObjectIdentifier streetAddress_oid = intern(ObjectIdentifier.newInternal(streetAddress_data));
    private static final int[] title_data = {2, 5, 4, 12};
    public static final ObjectIdentifier title_oid = intern(ObjectIdentifier.newInternal(title_data));
    private static final int[] userid_data = {0, 9, 2342, 19200300, 100, 1, 1};
    public static final ObjectIdentifier userid_oid = intern(ObjectIdentifier.newInternal(userid_data));
    private String canonicalDn;
    private String dn;
    private byte[] encoded;
    private RDN[] names;
    private String rfc1779Dn;
    private String rfc2253Dn;
    private X500Principal x500Principal;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 4;
    }

    public X500Name(DerValue derValue) {
        this(derValue.toDerInputStream());
    }

    public X500Name(DerInputStream derInputStream) {
        parseDER(derInputStream);
    }

    public X500Name(byte[] bArr) {
        parseDER(new DerInputStream(bArr));
    }

    public boolean isEmpty() {
        int length = this.names.length;
        for (int i = 0; i < length; i++) {
            if (this.names[i].assertion.length != 0) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return getRFC2253CanonicalName().hashCode();
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        String str;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof X500Name)) {
            return false;
        }
        X500Name x500Name = (X500Name) obj;
        String str2 = this.canonicalDn;
        if (!(str2 == null || (str = x500Name.canonicalDn) == null)) {
            return str2.equals(str);
        }
        int length = this.names.length;
        if (length != x500Name.names.length) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            if (this.names[i].assertion.length != x500Name.names[i].assertion.length) {
                return false;
            }
        }
        return getRFC2253CanonicalName().equals(x500Name.getRFC2253CanonicalName());
    }

    public String toString() {
        if (this.dn == null) {
            generateDN();
        }
        return this.dn;
    }

    public String getRFC1779Name() {
        return getRFC1779Name(Collections.emptyMap());
    }

    public String getRFC1779Name(Map map) {
        if (!map.isEmpty()) {
            return generateRFC1779DN(map);
        }
        String str = this.rfc1779Dn;
        if (str != null) {
            return str;
        }
        this.rfc1779Dn = generateRFC1779DN(map);
        return this.rfc1779Dn;
    }

    public String getRFC2253Name() {
        return getRFC2253Name(Collections.emptyMap());
    }

    public String getRFC2253Name(Map map) {
        if (!map.isEmpty()) {
            return generateRFC2253DN(map);
        }
        String str = this.rfc2253Dn;
        if (str != null) {
            return str;
        }
        this.rfc2253Dn = generateRFC2253DN(map);
        return this.rfc2253Dn;
    }

    private String generateRFC2253DN(Map map) {
        if (this.names.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(48);
        for (int length = this.names.length - 1; length >= 0; length--) {
            if (length < this.names.length - 1) {
                sb.append(',');
            }
            sb.append(this.names[length].toRFC2253String(map));
        }
        return sb.toString();
    }

    public String getRFC2253CanonicalName() {
        String str = this.canonicalDn;
        if (str != null) {
            return str;
        }
        if (this.names.length == 0) {
            this.canonicalDn = "";
            return this.canonicalDn;
        }
        StringBuilder sb = new StringBuilder(48);
        for (int length = this.names.length - 1; length >= 0; length--) {
            if (length < this.names.length - 1) {
                sb.append(',');
            }
            sb.append(this.names[length].toRFC2253String(true));
        }
        this.canonicalDn = sb.toString();
        return this.canonicalDn;
    }

    @Override // java.security.Principal
    public String getName() {
        return toString();
    }

    private void parseDER(DerInputStream derInputStream) {
        DerValue[] derValueArr;
        byte[] byteArray = derInputStream.toByteArray();
        try {
            derValueArr = derInputStream.getSequence(5);
        } catch (IOException unused) {
            derValueArr = byteArray == null ? null : new DerInputStream(new DerValue((byte) 48, byteArray).toByteArray()).getSequence(5);
        }
        if (derValueArr == null) {
            this.names = new RDN[0];
            return;
        }
        this.names = new RDN[derValueArr.length];
        for (int i = 0; i < derValueArr.length; i++) {
            this.names[i] = new RDN(derValueArr[i]);
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        int i = 0;
        while (true) {
            RDN[] rdnArr = this.names;
            if (i < rdnArr.length) {
                rdnArr[i].encode(derOutputStream2);
                i++;
            } else {
                derOutputStream.write((byte) 48, derOutputStream2);
                return;
            }
        }
    }

    public byte[] getEncodedInternal() {
        if (this.encoded == null) {
            DerOutputStream derOutputStream = new DerOutputStream();
            DerOutputStream derOutputStream2 = new DerOutputStream();
            int i = 0;
            while (true) {
                RDN[] rdnArr = this.names;
                if (i >= rdnArr.length) {
                    break;
                }
                rdnArr[i].encode(derOutputStream2);
                i++;
            }
            derOutputStream.write((byte) 48, derOutputStream2);
            this.encoded = derOutputStream.toByteArray();
        }
        return this.encoded;
    }

    private void generateDN() {
        RDN[] rdnArr = this.names;
        if (rdnArr.length == 1) {
            this.dn = rdnArr[0].toString();
            return;
        }
        StringBuilder sb = new StringBuilder(48);
        RDN[] rdnArr2 = this.names;
        if (rdnArr2 != null) {
            for (int length = rdnArr2.length - 1; length >= 0; length--) {
                if (length != this.names.length - 1) {
                    sb.append(", ");
                }
                sb.append(this.names[length].toString());
            }
        }
        this.dn = sb.toString();
    }

    private String generateRFC1779DN(Map map) {
        RDN[] rdnArr = this.names;
        if (rdnArr.length == 1) {
            return rdnArr[0].toRFC1779String(map);
        }
        StringBuilder sb = new StringBuilder(48);
        RDN[] rdnArr2 = this.names;
        if (rdnArr2 != null) {
            for (int length = rdnArr2.length - 1; length >= 0; length--) {
                if (length != this.names.length - 1) {
                    sb.append(", ");
                }
                sb.append(this.names[length].toRFC1779String(map));
            }
        }
        return sb.toString();
    }

    static ObjectIdentifier intern(ObjectIdentifier objectIdentifier) {
        ObjectIdentifier objectIdentifier2 = (ObjectIdentifier) internedOIDs.putIfAbsent(objectIdentifier, objectIdentifier);
        return objectIdentifier2 == null ? objectIdentifier : objectIdentifier2;
    }

    static {
        try {
            Object[] objArr = (Object[]) AccessController.doPrivileged(new PrivilegedExceptionAction() {
                /* class sun.security.x509.X500Name.AnonymousClass1 */

                @Override // java.security.PrivilegedExceptionAction
                public Object[] run() {
                    Constructor declaredConstructor = X500Principal.class.getDeclaredConstructor(X500Name.class);
                    declaredConstructor.setAccessible(true);
                    Field declaredField = X500Principal.class.getDeclaredField("thisX500Name");
                    declaredField.setAccessible(true);
                    return new Object[]{declaredConstructor, declaredField};
                }
            });
            principalConstructor = (Constructor) objArr[0];
            principalField = (Field) objArr[1];
        } catch (Exception e) {
            throw new InternalError("Could not obtain X500Principal access", e);
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        if (generalNameInterface == null || generalNameInterface.getType() != 4) {
            return -1;
        }
        X500Name x500Name = (X500Name) generalNameInterface;
        if (x500Name.equals(this)) {
            return 0;
        }
        if (x500Name.names.length != 0) {
            if (this.names.length == 0 || x500Name.isWithinSubtree(this)) {
                return 1;
            }
            if (!isWithinSubtree(x500Name)) {
                return 3;
            }
        }
        return 2;
    }

    private boolean isWithinSubtree(X500Name x500Name) {
        if (this == x500Name) {
            return true;
        }
        if (x500Name == null) {
            return false;
        }
        RDN[] rdnArr = x500Name.names;
        if (rdnArr.length == 0) {
            return true;
        }
        RDN[] rdnArr2 = this.names;
        if (rdnArr2.length == 0 || rdnArr2.length < rdnArr.length) {
            return false;
        }
        int i = 0;
        while (true) {
            RDN[] rdnArr3 = x500Name.names;
            if (i >= rdnArr3.length) {
                return true;
            }
            if (!this.names[i].equals(rdnArr3[i])) {
                return false;
            }
            i++;
        }
    }

    public X500Principal asX500Principal() {
        if (this.x500Principal == null) {
            try {
                this.x500Principal = (X500Principal) principalConstructor.newInstance(this);
            } catch (Exception e) {
                throw new RuntimeException("Unexpected exception", e);
            }
        }
        return this.x500Principal;
    }
}
