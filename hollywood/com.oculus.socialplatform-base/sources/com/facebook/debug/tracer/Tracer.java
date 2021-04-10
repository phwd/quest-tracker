package com.facebook.debug.tracer;

import X.AnonymousClass0MD;
import X.AnonymousClass0MT;
import X.AnonymousClass0MU;
import X.C03720oN;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.IgnoreAllocations;
import com.facebook.systrace.Systrace;
import java.util.IllegalFormatException;

public final class Tracer {
    public static C03720oN A00 = new C03720oN();
    public static final ThreadLocal<AnonymousClass0MU> A01 = new AnonymousClass0MT();

    @IgnoreAllocations
    public static void A00(String str) {
        if (Systrace.A03(32)) {
            try {
                str = StringFormatUtil.formatStrLocaleSafe(str);
            } catch (IllegalFormatException e) {
                if (AnonymousClass0MD.A01.A64(6)) {
                    AnonymousClass0MD.A01.ABN("Tracer", "Bad format string", e);
                }
            }
            Systrace.A01(32, str);
        }
    }
}
