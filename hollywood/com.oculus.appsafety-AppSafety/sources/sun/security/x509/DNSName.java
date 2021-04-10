package sun.security.x509;

import java.io.IOException;
import java.util.Locale;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class DNSName implements GeneralNameInterface {
    private static final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String alphaDigitsAndHyphen = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-";
    private static final String digitsAndHyphen = "0123456789-";
    private String name;

    public DNSName(DerValue derValue) throws IOException {
        this.name = derValue.getIA5String();
    }

    public DNSName(String name2) throws IOException {
        if (name2 == null || name2.length() == 0) {
            throw new IOException("DNS name must not be null");
        } else if (name2.indexOf(32) != -1) {
            throw new IOException("DNS names or NameConstraints with blank components are not permitted");
        } else if (name2.charAt(0) == '.' || name2.charAt(name2.length() - 1) == '.') {
            throw new IOException("DNS names or NameConstraints may not begin or end with a .");
        } else {
            int startIndex = 0;
            while (startIndex < name2.length()) {
                int endIndex = name2.indexOf(46, startIndex);
                endIndex = endIndex < 0 ? name2.length() : endIndex;
                if (endIndex - startIndex < 1) {
                    throw new IOException("DNSName SubjectAltNames with empty components are not permitted");
                } else if (alpha.indexOf(name2.charAt(startIndex)) >= 0) {
                    for (int nonStartIndex = startIndex + 1; nonStartIndex < endIndex; nonStartIndex++) {
                        if (alphaDigitsAndHyphen.indexOf(name2.charAt(nonStartIndex)) < 0) {
                            throw new IOException("DNSName components must consist of letters, digits, and hyphens");
                        }
                    }
                    startIndex = endIndex + 1;
                } else {
                    throw new IOException("DNSName components must begin with a letter");
                }
            }
            this.name = name2;
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 2;
    }

    public String getName() {
        return this.name;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream out) throws IOException {
        out.putIA5String(this.name);
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
    public int constrains(GeneralNameInterface inputName) throws UnsupportedOperationException {
        int ndx;
        int inNdx;
        if (inputName == null || inputName.getType() != 2) {
            return -1;
        }
        String inName = ((DNSName) inputName).getName().toLowerCase(Locale.ENGLISH);
        String thisName = this.name.toLowerCase(Locale.ENGLISH);
        if (inName.equals(thisName)) {
            return 0;
        }
        if (thisName.endsWith(inName)) {
            if (thisName.charAt(thisName.lastIndexOf(inName) - 1) == '.') {
                inNdx = 2;
            } else {
                inNdx = 3;
            }
            return inNdx;
        } else if (!inName.endsWith(thisName)) {
            return 3;
        } else {
            if (inName.charAt(inName.lastIndexOf(thisName) - 1) == '.') {
                ndx = 1;
            } else {
                ndx = 3;
            }
            return ndx;
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int subtreeDepth() throws UnsupportedOperationException {
        int sum = 1;
        int i = this.name.indexOf(46);
        while (i >= 0) {
            sum++;
            i = this.name.indexOf(46, i + 1);
        }
        return sum;
    }
}
