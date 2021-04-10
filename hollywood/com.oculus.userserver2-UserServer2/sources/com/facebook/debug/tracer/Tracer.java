package com.facebook.debug.tracer;

import X.C0108Mw;
import X.C0135Sk;
import X.Mi;
import X.Mx;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public final class Tracer {
    public static C0135Sk A00 = new C0135Sk();
    public static final ThreadLocal<Mx> A01 = new C0108Mw();

    public static void A00(String str, int i, @Nullable Object obj) {
        if (Systrace.A03(32)) {
            if (i == 0) {
                str = StringFormatUtil.formatStrLocaleSafe(str);
            } else if (i == 1) {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj);
            } else if (i == 2) {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj, null);
            } else if (i == 3) {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj, null, null);
            } else if (i != 4) {
                try {
                    str = StringFormatUtil.formatStrLocaleSafe(str, (Object[]) null);
                } catch (IllegalFormatException e) {
                    if (Mi.A01.A2C(6)) {
                        Mi.A01.A46("Tracer", "Bad format string", e);
                    }
                }
            } else {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj, null, null, null);
            }
            Systrace.A01(32, str);
        }
    }
}
