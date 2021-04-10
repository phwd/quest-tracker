package com.facebook.debug.tracer;

import X.AnonymousClass0NO;
import X.AnonymousClass0Nd;
import X.AnonymousClass0Ne;
import X.C06660pQ;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.IgnoreAllocations;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public final class Tracer {
    public static C06660pQ A00 = new C06660pQ();
    public static final ThreadLocal<AnonymousClass0Ne> A01 = new AnonymousClass0Nd();

    public static void A01(String str, int i, @Nullable Object obj) {
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
                    AnonymousClass0NO.A0D("Tracer", "Bad format string", e);
                }
            } else {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj, null, null, null);
            }
            Systrace.A01(32, str);
        }
    }

    @IgnoreAllocations
    public static void A02(String str, @Nullable Object obj) {
        A01(str, 1, obj);
    }

    public static void A00() {
        Systrace.A00(32);
    }
}
