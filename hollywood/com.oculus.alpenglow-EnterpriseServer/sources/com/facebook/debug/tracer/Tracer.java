package com.facebook.debug.tracer;

import X.AnonymousClass0NK;
import X.AnonymousClass0NX;
import X.AnonymousClass0NY;
import X.C03160bj;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.IgnoreAllocations;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;
import javax.annotation.Nullable;

public final class Tracer {
    public static C03160bj A00 = new C03160bj();
    public static final ThreadLocal<AnonymousClass0NY> A01 = new AnonymousClass0NX();

    public static void A00() {
        Systrace.A00(32);
    }

    @IgnoreAllocations
    public static void A01(String str) {
        if (Systrace.A03(32)) {
            try {
                str = StringFormatUtil.formatStrLocaleSafe(str);
            } catch (IllegalFormatException e) {
                AnonymousClass0NK.A05("Tracer", "Bad format string", e);
            }
            Systrace.A01(32, str);
        }
    }

    @IgnoreAllocations
    public static void A02(String str, @Nullable Object obj) {
        if (Systrace.A03(32)) {
            try {
                str = StringFormatUtil.formatStrLocaleSafe(str, obj);
            } catch (IllegalFormatException e) {
                AnonymousClass0NK.A05("Tracer", "Bad format string", e);
            }
            Systrace.A01(32, str);
        }
    }
}
