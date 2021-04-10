package com.facebook.debug.tracer;

import X.Mu;
import X.N9;
import X.NA;
import X.YC;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.IgnoreAllocations;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public final class Tracer {
    public static YC A00 = new YC();
    public static final ThreadLocal<NA> A01 = new N9();

    @IgnoreAllocations
    public static void A00(String str) {
        if (Systrace.A03(32)) {
            try {
                str = StringFormatUtil.formatStrLocaleSafe(str);
            } catch (IllegalFormatException e) {
                Mu.A04("Tracer", "Bad format string", e);
            }
            Systrace.A01(32, str);
        }
    }

    @IgnoreAllocations
    public static void A01(String str, @Nullable Object obj) {
        if (Systrace.A03(32)) {
            try {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj);
            } catch (IllegalFormatException e) {
                Mu.A04("Tracer", "Bad format string", e);
            }
            Systrace.A01(32, str);
        }
    }
}
