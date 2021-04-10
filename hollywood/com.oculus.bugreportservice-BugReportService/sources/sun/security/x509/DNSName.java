package sun.security.x509;

import java.util.Locale;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class DNSName implements GeneralNameInterface {
    private String name;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 2;
    }

    public DNSName(DerValue derValue) {
        derValue.getIA5String();
        throw null;
    }

    public String getName() {
        return this.name;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.putIA5String(this.name);
    }

    public String toString() {
        return "DNSName: " + this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DNSName)) {
            return false;
        }
        return this.name.equalsIgnoreCase(((DNSName) obj).name);
    }

    public int hashCode() {
        return this.name.toUpperCase(Locale.ENGLISH).hashCode();
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        if (generalNameInterface == null || generalNameInterface.getType() != 2) {
            return -1;
        }
        String lowerCase = ((DNSName) generalNameInterface).getName().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = this.name.toLowerCase(Locale.ENGLISH);
        if (lowerCase.equals(lowerCase2)) {
            return 0;
        }
        if (lowerCase2.endsWith(lowerCase)) {
            if (lowerCase2.charAt(lowerCase2.lastIndexOf(lowerCase) - 1) == '.') {
                return 2;
            }
        } else if (lowerCase.endsWith(lowerCase2) && lowerCase.charAt(lowerCase.lastIndexOf(lowerCase2) - 1) == '.') {
            return 1;
        }
        return 3;
    }
}
