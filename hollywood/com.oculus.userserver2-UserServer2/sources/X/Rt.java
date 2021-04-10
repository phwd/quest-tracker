package X;

import com.google.common.annotations.GwtCompatible;
import com.oculus.common.build.BuildConfig;
import java.util.Set;

@GwtCompatible(emulated = BuildConfig.IS_LIBCXX_BUILD)
public final class Rt {
    public static int A00(Set<?> set) {
        int i;
        int i2 = 0;
        for (Object obj : set) {
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i2 = ((i2 + i) ^ -1) ^ -1;
        }
        return i2;
    }
}
