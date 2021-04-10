package sun.security.x509;

import java.util.Locale;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class RFC822Name implements GeneralNameInterface {
    private String name;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 1;
    }

    public RFC822Name(DerValue derValue) {
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
        return "RFC822Name: " + this.name;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RFC822Name)) {
            return false;
        }
        return this.name.equalsIgnoreCase(((RFC822Name) obj).name);
    }

    public int hashCode() {
        return this.name.toUpperCase(Locale.ENGLISH).hashCode();
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        if (generalNameInterface == null || generalNameInterface.getType() != 1) {
            return -1;
        }
        String lowerCase = ((RFC822Name) generalNameInterface).getName().toLowerCase(Locale.ENGLISH);
        String lowerCase2 = this.name.toLowerCase(Locale.ENGLISH);
        if (lowerCase.equals(lowerCase2)) {
            return 0;
        }
        if (lowerCase2.endsWith(lowerCase)) {
            if (lowerCase.indexOf(64) == -1 && (lowerCase.startsWith(".") || lowerCase2.charAt(lowerCase2.lastIndexOf(lowerCase) - 1) == '@')) {
                return 2;
            }
        } else if (lowerCase.endsWith(lowerCase2) && lowerCase2.indexOf(64) == -1 && (lowerCase2.startsWith(".") || lowerCase.charAt(lowerCase.lastIndexOf(lowerCase2) - 1) == '@')) {
            return 1;
        }
        return 3;
    }
}
