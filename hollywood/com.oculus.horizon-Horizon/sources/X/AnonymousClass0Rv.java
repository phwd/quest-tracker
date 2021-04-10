package X;

import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Rv  reason: invalid class name */
public final class AnonymousClass0Rv {
    @Nullable
    public static int A00(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return 2;
        }
        if (i == 3) {
            return 3;
        }
        if (i == 4) {
            return 4;
        }
        throw new IllegalArgumentException(StringFormatUtil.formatStrLocaleSafe("%d is not a MobileConfigParamType", Integer.valueOf(i)));
    }
}
