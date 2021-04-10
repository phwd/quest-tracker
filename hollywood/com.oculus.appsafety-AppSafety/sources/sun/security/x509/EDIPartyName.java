package sun.security.x509;

import java.io.IOException;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class EDIPartyName implements GeneralNameInterface {
    private static final byte TAG_ASSIGNER = 0;
    private static final byte TAG_PARTYNAME = 1;
    private String assigner = null;
    private int myhash = -1;
    private String party = null;

    public EDIPartyName(String assignerName, String partyName) {
        this.assigner = assignerName;
        this.party = partyName;
    }

    public EDIPartyName(String partyName) {
        this.party = partyName;
    }

    public EDIPartyName(DerValue derValue) throws IOException {
        DerValue[] seq = new DerInputStream(derValue.toByteArray()).getSequence(2);
        int len = seq.length;
        if (len < 1 || len > 2) {
            throw new IOException("Invalid encoding of EDIPartyName");
        }
        for (int i = 0; i < len; i++) {
            DerValue opt = seq[i];
            if (opt.isContextSpecific((byte) 0) && !opt.isConstructed()) {
                if (this.assigner == null) {
                    opt = opt.data.getDerValue();
                    this.assigner = opt.getAsString();
                } else {
                    throw new IOException("Duplicate nameAssigner found in EDIPartyName");
                }
            }
            if (opt.isContextSpecific((byte) 1) && !opt.isConstructed()) {
                if (this.party == null) {
                    this.party = opt.data.getDerValue().getAsString();
                } else {
                    throw new IOException("Duplicate partyName found in EDIPartyName");
                }
            }
        }
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int getType() {
        return 5;
    }

    @Override // sun.security.x509.GeneralNameInterface
    public void encode(DerOutputStream out) throws IOException {
        DerOutputStream tagged = new DerOutputStream();
        DerOutputStream tmp = new DerOutputStream();
        if (this.assigner != null) {
            DerOutputStream tmp2 = new DerOutputStream();
            tmp2.putPrintableString(this.assigner);
            tagged.write(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 0), tmp2);
        }
        String str = this.party;
        if (str != null) {
            tmp.putPrintableString(str);
            tagged.write(DerValue.createTag(Byte.MIN_VALUE, false, (byte) 1), tmp);
            out.write((byte) 48, tagged);
            return;
        }
        throw new IOException("Cannot have null partyName");
    }

    public String getAssignerName() {
        return this.assigner;
    }

    public String getPartyName() {
        return this.party;
    }

    public boolean equals(Object other) {
        if (!(other instanceof EDIPartyName)) {
            return false;
        }
        String otherAssigner = ((EDIPartyName) other).assigner;
        String str = this.assigner;
        if (str == null) {
            if (otherAssigner != null) {
                return false;
            }
        } else if (!str.equals(otherAssigner)) {
            return false;
        }
        String otherParty = ((EDIPartyName) other).party;
        String str2 = this.party;
        if (str2 == null) {
            if (otherParty != null) {
                return false;
            }
            return true;
        } else if (!str2.equals(otherParty)) {
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
    public int constrains(GeneralNameInterface inputName) throws UnsupportedOperationException {
        if (inputName == null || inputName.getType() != 5) {
            return -1;
        }
        throw new UnsupportedOperationException("Narrowing, widening, and matching of names not supported for EDIPartyName");
    }

    @Override // sun.security.x509.GeneralNameInterface
    public int subtreeDepth() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("subtreeDepth() not supported for EDIPartyName");
    }
}
