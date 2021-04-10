package sun.security.x509;

import java.util.HashMap;
import java.util.Map;
import sun.security.pkcs.PKCS9Attribute;
import sun.security.util.ObjectIdentifier;

/* compiled from: AVA */
class AVAKeyword {
    private static final Map keywordMap = new HashMap();
    private static final Map oidMap = new HashMap();
    private String keyword;
    private ObjectIdentifier oid;
    private boolean rfc1779Compliant;
    private boolean rfc2253Compliant;

    private AVAKeyword(String str, ObjectIdentifier objectIdentifier, boolean z, boolean z2) {
        this.keyword = str;
        this.oid = objectIdentifier;
        this.rfc1779Compliant = z;
        this.rfc2253Compliant = z2;
        oidMap.put(objectIdentifier, this);
        keywordMap.put(str, this);
    }

    private boolean isCompliant(int i) {
        if (i == 1) {
            return true;
        }
        if (i == 2) {
            return this.rfc1779Compliant;
        }
        if (i == 3) {
            return this.rfc2253Compliant;
        }
        throw new IllegalArgumentException("Invalid standard " + i);
    }

    static String getKeyword(ObjectIdentifier objectIdentifier, int i, Map map) {
        String objectIdentifier2 = objectIdentifier.toString();
        String str = (String) map.get(objectIdentifier2);
        if (str == null) {
            AVAKeyword aVAKeyword = (AVAKeyword) oidMap.get(objectIdentifier);
            if (aVAKeyword != null && aVAKeyword.isCompliant(i)) {
                return aVAKeyword.keyword;
            }
            if (i == 3) {
                return objectIdentifier2;
            }
            return "OID." + objectIdentifier2;
        } else if (str.length() != 0) {
            String trim = str.trim();
            char charAt = trim.charAt(0);
            if (charAt < 'A' || charAt > 'z' || (charAt > 'Z' && charAt < 'a')) {
                throw new IllegalArgumentException("keyword does not start with letter");
            }
            for (int i2 = 1; i2 < trim.length(); i2++) {
                char charAt2 = trim.charAt(i2);
                if ((charAt2 < 'A' || charAt2 > 'z' || (charAt2 > 'Z' && charAt2 < 'a')) && ((charAt2 < '0' || charAt2 > '9') && charAt2 != '_')) {
                    throw new IllegalArgumentException("keyword character is not a letter, digit, or underscore");
                }
            }
            return trim;
        } else {
            throw new IllegalArgumentException("keyword cannot be empty");
        }
    }

    static boolean hasKeyword(ObjectIdentifier objectIdentifier, int i) {
        AVAKeyword aVAKeyword = (AVAKeyword) oidMap.get(objectIdentifier);
        if (aVAKeyword == null) {
            return false;
        }
        return aVAKeyword.isCompliant(i);
    }

    static {
        new AVAKeyword("CN", X500Name.commonName_oid, true, true);
        new AVAKeyword("C", X500Name.countryName_oid, true, true);
        new AVAKeyword("L", X500Name.localityName_oid, true, true);
        new AVAKeyword("S", X500Name.stateName_oid, false, false);
        new AVAKeyword("ST", X500Name.stateName_oid, true, true);
        new AVAKeyword("O", X500Name.orgName_oid, true, true);
        new AVAKeyword("OU", X500Name.orgUnitName_oid, true, true);
        new AVAKeyword("T", X500Name.title_oid, false, false);
        new AVAKeyword("IP", X500Name.ipAddress_oid, false, false);
        new AVAKeyword("STREET", X500Name.streetAddress_oid, true, true);
        new AVAKeyword("DC", X500Name.DOMAIN_COMPONENT_OID, false, true);
        new AVAKeyword("DNQUALIFIER", X500Name.DNQUALIFIER_OID, false, false);
        new AVAKeyword("DNQ", X500Name.DNQUALIFIER_OID, false, false);
        new AVAKeyword("SURNAME", X500Name.SURNAME_OID, false, false);
        new AVAKeyword("GIVENNAME", X500Name.GIVENNAME_OID, false, false);
        new AVAKeyword("INITIALS", X500Name.INITIALS_OID, false, false);
        new AVAKeyword("GENERATION", X500Name.GENERATIONQUALIFIER_OID, false, false);
        new AVAKeyword("EMAIL", PKCS9Attribute.EMAIL_ADDRESS_OID, false, false);
        new AVAKeyword("EMAILADDRESS", PKCS9Attribute.EMAIL_ADDRESS_OID, false, false);
        new AVAKeyword("UID", X500Name.userid_oid, false, true);
        new AVAKeyword("SERIALNUMBER", X500Name.SERIALNUMBER_OID, false, false);
    }
}
