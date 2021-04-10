package sun.security.x509;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class GeneralNames {
    private final List names;

    public GeneralNames(DerValue derValue) {
        this();
        if (derValue.tag != 48) {
            throw new IOException("Invalid encoding for GeneralNames.");
        } else if (derValue.data.available() != 0) {
            while (derValue.data.available() != 0) {
                add(new GeneralName(derValue.data.getDerValue()));
            }
        } else {
            throw new IOException("No data available in passed DER encoded value.");
        }
    }

    public GeneralNames() {
        this.names = new ArrayList();
    }

    public GeneralNames add(GeneralName generalName) {
        if (generalName != null) {
            this.names.add(generalName);
            return this;
        }
        throw new NullPointerException();
    }

    public GeneralName get(int i) {
        return (GeneralName) this.names.get(i);
    }

    public boolean isEmpty() {
        return this.names.isEmpty();
    }

    public List names() {
        return this.names;
    }

    public void encode(DerOutputStream derOutputStream) {
        if (!isEmpty()) {
            DerOutputStream derOutputStream2 = new DerOutputStream();
            for (GeneralName generalName : this.names) {
                generalName.encode(derOutputStream2);
            }
            derOutputStream.write((byte) 48, derOutputStream2);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GeneralNames)) {
            return false;
        }
        return this.names.equals(((GeneralNames) obj).names);
    }

    public int hashCode() {
        return this.names.hashCode();
    }

    public String toString() {
        return this.names.toString();
    }
}
