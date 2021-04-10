package sun.security.x509;

import java.util.Comparator;

/* compiled from: RDN */
class AVAComparator implements Comparator {
    private static final Comparator INSTANCE = new AVAComparator();

    private AVAComparator() {
    }

    static Comparator getInstance() {
        return INSTANCE;
    }

    public int compare(AVA ava, AVA ava2) {
        int i;
        int i2;
        int i3;
        boolean hasRFC2253Keyword = ava.hasRFC2253Keyword();
        boolean hasRFC2253Keyword2 = ava2.hasRFC2253Keyword();
        if (hasRFC2253Keyword) {
            if (hasRFC2253Keyword2) {
                return ava.toRFC2253CanonicalString().compareTo(ava2.toRFC2253CanonicalString());
            }
            return -1;
        } else if (hasRFC2253Keyword2) {
            return 1;
        } else {
            int[] intArray = ava.getObjectIdentifier().toIntArray();
            int[] intArray2 = ava2.getObjectIdentifier().toIntArray();
            int i4 = 0;
            if (intArray.length > intArray2.length) {
                i = intArray2.length;
            } else {
                i = intArray.length;
            }
            while (i4 < i && intArray[i4] == intArray2[i4]) {
                i4++;
            }
            if (i4 == i) {
                i3 = intArray.length;
                i2 = intArray2.length;
            } else {
                i3 = intArray[i4];
                i2 = intArray2[i4];
            }
            return i3 - i2;
        }
    }
}
