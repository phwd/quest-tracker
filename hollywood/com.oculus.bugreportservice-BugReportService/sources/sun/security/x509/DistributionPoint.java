package sun.security.x509;

import java.util.Arrays;
import java.util.Objects;
import sun.security.util.BitArray;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class DistributionPoint {
    private static final String[] REASON_STRINGS = {null, "key compromise", "CA compromise", "affiliation changed", "superseded", "cessation of operation", "certificate hold", "privilege withdrawn", "AA compromise"};
    private GeneralNames crlIssuer;
    private GeneralNames fullName;
    private volatile int hashCode;
    private boolean[] reasonFlags;
    private RDN relativeName;

    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        if (!(this.fullName == null && this.relativeName == null)) {
            DerOutputStream derOutputStream3 = new DerOutputStream();
            if (this.fullName != null) {
                DerOutputStream derOutputStream4 = new DerOutputStream();
                this.fullName.encode(derOutputStream4);
                derOutputStream3.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), derOutputStream4);
            } else if (this.relativeName != null) {
                DerOutputStream derOutputStream5 = new DerOutputStream();
                this.relativeName.encode(derOutputStream5);
                derOutputStream3.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 1), derOutputStream5);
            }
            derOutputStream2.write(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 0), derOutputStream3);
        }
        if (this.reasonFlags != null) {
            DerOutputStream derOutputStream6 = new DerOutputStream();
            derOutputStream6.putTruncatedUnalignedBitString(new BitArray(this.reasonFlags));
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), derOutputStream6);
        }
        if (this.crlIssuer != null) {
            DerOutputStream derOutputStream7 = new DerOutputStream();
            this.crlIssuer.encode(derOutputStream7);
            derOutputStream2.writeImplicit(DerValue.createTag(Byte.MIN_VALUE, true, (byte) 2), derOutputStream7);
        }
        derOutputStream.write((byte) 48, derOutputStream2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DistributionPoint)) {
            return false;
        }
        DistributionPoint distributionPoint = (DistributionPoint) obj;
        return Objects.equals(this.fullName, distributionPoint.fullName) && Objects.equals(this.relativeName, distributionPoint.relativeName) && Objects.equals(this.crlIssuer, distributionPoint.crlIssuer) && Arrays.equals(this.reasonFlags, distributionPoint.reasonFlags);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        GeneralNames generalNames = this.fullName;
        int i2 = 1;
        if (generalNames != null) {
            i2 = 1 + generalNames.hashCode();
        }
        RDN rdn = this.relativeName;
        if (rdn != null) {
            i2 += rdn.hashCode();
        }
        GeneralNames generalNames2 = this.crlIssuer;
        if (generalNames2 != null) {
            i2 += generalNames2.hashCode();
        }
        if (this.reasonFlags != null) {
            int i3 = 0;
            while (true) {
                boolean[] zArr = this.reasonFlags;
                if (i3 >= zArr.length) {
                    break;
                }
                if (zArr[i3]) {
                    i2 += i3;
                }
                i3++;
            }
        }
        this.hashCode = i2;
        return i2;
    }

    private static String reasonToString(int i) {
        if (i > 0) {
            String[] strArr = REASON_STRINGS;
            if (i < strArr.length) {
                return strArr[i];
            }
        }
        return "Unknown reason " + i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.fullName != null) {
            sb.append("DistributionPoint:\n     " + this.fullName + "\n");
        }
        if (this.relativeName != null) {
            sb.append("DistributionPoint:\n     " + this.relativeName + "\n");
        }
        if (this.reasonFlags != null) {
            sb.append("   ReasonFlags:\n");
            int i = 0;
            while (true) {
                boolean[] zArr = this.reasonFlags;
                if (i >= zArr.length) {
                    break;
                }
                if (zArr[i]) {
                    sb.append("    " + reasonToString(i) + "\n");
                }
                i++;
            }
        }
        if (this.crlIssuer != null) {
            sb.append("   CRLIssuer:" + this.crlIssuer + "\n");
        }
        return sb.toString();
    }
}
