package sun.security.x509;

import java.util.Comparator;

/* access modifiers changed from: package-private */
/* compiled from: RDN */
public class AVAComparator implements Comparator<AVA> {
    private static final Comparator<AVA> INSTANCE = new AVAComparator();

    private AVAComparator() {
    }

    static Comparator<AVA> getInstance() {
        return INSTANCE;
    }

    public int compare(AVA a1, AVA a2) {
        int len;
        boolean a1Has2253 = a1.hasRFC2253Keyword();
        boolean a2Has2253 = a2.hasRFC2253Keyword();
        if (a1Has2253) {
            if (a2Has2253) {
                return a1.toRFC2253CanonicalString().compareTo(a2.toRFC2253CanonicalString());
            }
            return -1;
        } else if (a2Has2253) {
            return 1;
        } else {
            int[] a1Oid = a1.getObjectIdentifier().toIntArray();
            int[] a2Oid = a2.getObjectIdentifier().toIntArray();
            int pos = 0;
            if (a1Oid.length > a2Oid.length) {
                len = a2Oid.length;
            } else {
                len = a1Oid.length;
            }
            while (pos < len && a1Oid[pos] == a2Oid[pos]) {
                pos++;
            }
            if (pos == len) {
                return a1Oid.length - a2Oid.length;
            }
            return a1Oid[pos] - a2Oid[pos];
        }
    }
}
