package sun.security.x509;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.StringJoiner;
import sun.security.util.DerInputStream;
import sun.security.util.DerOutputStream;
import sun.security.util.DerValue;

public class RDN {
    final AVA[] assertion;
    private volatile String canonicalString;

    RDN(DerValue derValue) {
        if (derValue.tag == 49) {
            DerValue[] set = new DerInputStream(derValue.toByteArray()).getSet(5);
            this.assertion = new AVA[set.length];
            for (int i = 0; i < set.length; i++) {
                this.assertion[i] = new AVA(set[i]);
            }
            return;
        }
        throw new IOException("X500 RDN");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RDN)) {
            return false;
        }
        RDN rdn = (RDN) obj;
        if (this.assertion.length != rdn.assertion.length) {
            return false;
        }
        return toRFC2253String(true).equals(rdn.toRFC2253String(true));
    }

    public int hashCode() {
        return toRFC2253String(true).hashCode();
    }

    /* access modifiers changed from: package-private */
    public void encode(DerOutputStream derOutputStream) {
        derOutputStream.putOrderedSetOf((byte) 49, this.assertion);
    }

    public String toString() {
        AVA[] avaArr = this.assertion;
        if (avaArr.length == 1) {
            return avaArr[0].toString();
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.assertion.length; i++) {
            if (i != 0) {
                sb.append(" + ");
            }
            sb.append(this.assertion[i].toString());
        }
        return sb.toString();
    }

    public String toRFC1779String(Map map) {
        AVA[] avaArr = this.assertion;
        if (avaArr.length == 1) {
            return avaArr[0].toRFC1779String(map);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.assertion.length; i++) {
            if (i != 0) {
                sb.append(" + ");
            }
            sb.append(this.assertion[i].toRFC1779String(map));
        }
        return sb.toString();
    }

    public String toRFC2253String(Map map) {
        return toRFC2253StringInternal(false, map);
    }

    public String toRFC2253String(boolean z) {
        if (!z) {
            return toRFC2253StringInternal(false, Collections.emptyMap());
        }
        String str = this.canonicalString;
        if (str != null) {
            return str;
        }
        String rFC2253StringInternal = toRFC2253StringInternal(true, Collections.emptyMap());
        this.canonicalString = rFC2253StringInternal;
        return rFC2253StringInternal;
    }

    private String toRFC2253StringInternal(boolean z, Map map) {
        String str;
        AVA[] avaArr = this.assertion;
        if (avaArr.length != 1) {
            if (z) {
                avaArr = (AVA[]) avaArr.clone();
                Arrays.sort(avaArr, AVAComparator.getInstance());
            }
            StringJoiner stringJoiner = new StringJoiner("+");
            for (AVA ava : avaArr) {
                if (z) {
                    str = ava.toRFC2253CanonicalString();
                } else {
                    str = ava.toRFC2253String(map);
                }
                stringJoiner.add(str);
            }
            return stringJoiner.toString();
        } else if (z) {
            return avaArr[0].toRFC2253CanonicalString();
        } else {
            return avaArr[0].toRFC2253String(map);
        }
    }
}
