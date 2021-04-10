package sun.security.x509;

import java.io.IOException;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class EDIPartyName implements GeneralNameInterface {
    private String assigner = null;
    private int myhash = -1;
    private String party = null;

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 5;
    }

    public EDIPartyName(DerValue derValue) {
        DerValue[] sequence = new DerInputStream(derValue.toByteArray()).getSequence(2);
        int length = sequence.length;
        if (length < 1 || length > 2) {
            throw new IOException("Invalid encoding of EDIPartyName");
        }
        for (int i = 0; i < length; i++) {
            DerValue derValue2 = sequence[i];
            if (derValue2.isContextSpecific((byte) 0) && !derValue2.isConstructed()) {
                if (this.assigner == null) {
                    derValue2 = derValue2.data.getDerValue();
                    this.assigner = derValue2.getAsString();
                } else {
                    throw new IOException("Duplicate nameAssigner found in EDIPartyName");
                }
            }
            if (derValue2.isContextSpecific((byte) 1) && !derValue2.isConstructed()) {
                if (this.party == null) {
                    this.party = derValue2.data.getDerValue().getAsString();
                } else {
                    throw new IOException("Duplicate partyName found in EDIPartyName");
                }
            }
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream derOutputStream) {
        DerOutputStream derOutputStream2 = new DerOutputStream();
        DerOutputStream derOutputStream3 = new DerOutputStream();
        if (this.assigner != null) {
            DerOutputStream derOutputStream4 = new DerOutputStream();
            derOutputStream4.putPrintableString(this.assigner);
            derOutputStream2.write(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 0), derOutputStream4);
        }
        String str = this.party;
        if (str != null) {
            derOutputStream3.putPrintableString(str);
            derOutputStream2.write(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), derOutputStream3);
            derOutputStream.write((byte) 48, derOutputStream2);
            return;
        }
        throw new IOException("Cannot have null partyName");
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EDIPartyName)) {
            return false;
        }
        EDIPartyName eDIPartyName = (EDIPartyName) obj;
        String str = eDIPartyName.assigner;
        String str2 = this.assigner;
        if (str2 == null) {
            if (str != null) {
                return false;
            }
        } else if (!str2.equals(str)) {
            return false;
        }
        String str3 = eDIPartyName.party;
        String str4 = this.party;
        if (str4 == null) {
            if (str3 != null) {
                return false;
            }
            return true;
        } else if (!str4.equals(str3)) {
            return false;
        } else {
            return true;
        }
    }

    public int hashCode() {
        if (this.myhash == -1) {
            String str = this.party;
            this.myhash = (str == null ? 1 : str.hashCode()) + 37;
            String str2 = this.assigner;
            if (str2 != null) {
                this.myhash = (this.myhash * 37) + str2.hashCode();
            }
        }
        return this.myhash;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("EDIPartyName: ");
        if (this.assigner == null) {
            str = "";
        } else {
            str = "  nameAssigner = " + this.assigner + ",";
        }
        sb.append(str);
        sb.append("  partyName = ");
        sb.append(this.party);
        return sb.toString();
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int constrains(GeneralNameInterface generalNameInterface) {
        if (generalNameInterface == null || generalNameInterface.getType() != 5) {
            return -1;
        }
        throw new UnsupportedOperationException("Narrowing, widening, and matching of names not supported for EDIPartyName");
    }
}
