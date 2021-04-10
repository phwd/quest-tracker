package java.security.cert;

import java.io.IOException;
import sun.misc.HexDumpEncoder;
import sun.security.util.DerValue;

public class PolicyQualifierInfo {
    private byte[] mData;
    private byte[] mEncoded;
    private String mId;
    private String pqiString;

    public PolicyQualifierInfo(byte[] encoded) throws IOException {
        this.mEncoded = (byte[]) encoded.clone();
        DerValue val = new DerValue(this.mEncoded);
        if (val.tag == 48) {
            this.mId = val.data.getDerValue().getOID().toString();
            byte[] tmp = val.data.toByteArray();
            if (tmp == null) {
                this.mData = null;
                return;
            }
            this.mData = new byte[tmp.length];
            System.arraycopy(tmp, 0, this.mData, 0, tmp.length);
            return;
        }
        throw new IOException("Invalid encoding for PolicyQualifierInfo");
    }

    public final String getPolicyQualifierId() {
        return this.mId;
    }

    public final byte[] getEncoded() {
        return (byte[]) this.mEncoded.clone();
    }

    public final byte[] getPolicyQualifier() {
        byte[] bArr = this.mData;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public String toString() {
        String str = this.pqiString;
        if (str != null) {
            return str;
        }
        HexDumpEncoder enc = new HexDumpEncoder();
        StringBuffer sb = new StringBuffer();
        sb.append("PolicyQualifierInfo: [\n");
        sb.append("  qualifierID: " + this.mId + "\n");
        StringBuilder sb2 = new StringBuilder();
        sb2.append("  qualifier: ");
        byte[] bArr = this.mData;
        sb2.append(bArr == null ? "null" : enc.encodeBuffer(bArr));
        sb2.append("\n");
        sb.append(sb2.toString());
        sb.append("]");
        this.pqiString = sb.toString();
        return this.pqiString;
    }
}
