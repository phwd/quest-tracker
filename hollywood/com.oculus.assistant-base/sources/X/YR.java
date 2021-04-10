package X;

import java.util.Arrays;
import java.util.Set;

public final class YR {
    public static void A00(Set set, YQ... yqArr) {
        if (yqArr.length == 1) {
            set.add(yqArr[0]);
        } else {
            set.addAll(Arrays.asList(yqArr));
        }
    }

    public static void A01(Set set, YQ... yqArr) {
        if (yqArr.length == 1) {
            set.remove(yqArr[0]);
        } else {
            set.removeAll(Arrays.asList(yqArr));
        }
    }

    public static boolean A02(Set set, YQ... yqArr) {
        if (yqArr.length == 1) {
            return set.contains(yqArr[0]);
        }
        return set.contains(Arrays.asList(yqArr));
    }
}
