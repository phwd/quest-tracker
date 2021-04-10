package com.facebook.systrace;

import X.AbstractC0223Ku;
import X.AbstractC0224Kv;
import X.C0221Ks;
import X.C0222Kt;
import X.C0979pv;
import X.C0980pw;
import X.C0981px;
import X.C0982py;

public final class SystraceMessage {
    public static final AbstractC0223Ku A00 = new C0981px();
    public static final ThreadLocal A01 = new C0221Ks();
    public static final AbstractC0224Kv A02 = new C0979pv();
    public static final AbstractC0224Kv A03 = new C0980pw();

    public static AbstractC0223Ku A00(String str) {
        AbstractC0224Kv kv = A02;
        if (!Systrace.A03(4)) {
            return A00;
        }
        C0982py pyVar = (C0982py) A01.get();
        pyVar.A00 = 4;
        pyVar.A02 = kv;
        pyVar.A03 = str;
        C0222Kt kt = pyVar.A01;
        for (int i = 0; i < kt.A00; i++) {
            kt.A01[i] = null;
        }
        kt.A00 = 0;
        return pyVar;
    }
}
