package sun.security.x509;

import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class GeneralSubtree {
    private int maximum;
    private int minimum;
    private int myhash;
    private GeneralName name;

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("\n   GeneralSubtree: [\n    GeneralName: ");
        GeneralName generalName = this.name;
        sb.append(generalName == null ? "" : generalName.toString());
        sb.append("\n    Minimum: ");
        sb.append(this.minimum);
        String sb2 = sb.toString();
        if (this.maximum == -1) {
            str = sb2 + "\t    Maximum: undefined";
        } else {
            str = sb2 + "\t    Maximum: " + this.maximum;
        }
        return str + "    ]\n";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeneralSubtree)) {
            return false;
        }
        GeneralSubtree generalSubtree = (GeneralSubtree) obj;
        GeneralName generalName = this.name;
        if (generalName == null) {
            if (generalSubtree.name != null) {
                return false;
            }
        } else if (!generalName.equals(generalSubtree.name)) {
            return false;
        }
        if (this.minimum == generalSubtree.minimum && this.maximum == generalSubtree.maximum) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.myhash == -1) {
            this.myhash = 17;
            GeneralName generalName = this.name;
            if (generalName != null) {
                this.myhash = (this.myhash * 37) + generalName.hashCode();
            }
            int i = this.minimum;
            if (i != 0) {
                this.myhash = (this.myhash * 37) + i;
            }
            int i2 = this.maximum;
            if (i2 != -1) {
                this.myhash = (this.myhash * 37) + i2;
            }
        }
        return this.myhash;
    }

    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        this.name.encode(derOutputStream2);
        if (this.minimum != 0) {
            DerOutputStream derOutputStream3 = new DerOutputStream();
            derOutputStream3.putInteger(this.minimum);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 0), derOutputStream3);
        }
        if (this.maximum != -1) {
            DerOutputStream derOutputStream4 = new DerOutputStream();
            derOutputStream4.putInteger(this.maximum);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), derOutputStream4);
        }
        derOutputStream.write((byte) 48, derOutputStream2);
    }
}
