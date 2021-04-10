package com.facebook.debug.tracer;

import X.C0139Dd;
import X.C0147Dm;
import X.C0850jy;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;

public final class Tracer {
    public static C0850jy A00 = new C0850jy();
    public static final ThreadLocal A01 = new C0147Dm();

    public static void A01(String str) {
        A02(str, 0, null);
    }

    public static void A02(String str, int i, Object obj) {
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
                    C0139Dd.A0N("Tracer", "Bad format string", e);
                }
            } else {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj, null, null, null);
            }
            Systrace.A01(32, str);
        }
    }

    public static void A00() {
        Systrace.A00(32);
    }
}
