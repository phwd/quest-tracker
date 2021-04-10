package defpackage;

import java.util.HashMap;
import java.util.Map;

/* renamed from: jC0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum EnumC3169jC0 {
    CRITICAL_PERSISTED_TAB_DATA("CPTD"),
    ENCRYPTED_CRITICAL_PERSISTED_TAB_DATA("ECPTD"),
    MOCK_PERSISTED_TAB_DATA("MPTD"),
    ENCRYPTED_MOCK_PERSISTED_TAB_DATA("EMPTD"),
    SHOPPING_PERSISTED_TAB_DATA("SPTD"),
    TEST_CONFIG("TC");
    
    public static final Map L;
    public static final Map M;
    public static TP N;
    public static C1999cL O;
    public static C1554Zk0 P;
    public final String R;
    public AbstractC3682mC0 S;

    /* access modifiers changed from: public */
    static {
        EnumC3169jC0 jc0;
        EnumC3169jC0 jc02;
        EnumC3169jC0 jc03;
        EnumC3169jC0 jc04;
        EnumC3169jC0 jc05;
        EnumC3169jC0 jc06;
        HashMap hashMap = new HashMap();
        L = hashMap;
        HashMap hashMap2 = new HashMap();
        M = hashMap2;
        hashMap.put(C5383wB.class, jc0);
        hashMap2.put(C5383wB.class, jc02);
        hashMap.put(AbstractC1432Xk0.class, jc03);
        hashMap2.put(AbstractC1432Xk0.class, jc04);
        hashMap.put(C2361eV0.class, jc05);
        hashMap2.put(C2361eV0.class, jc05);
        jc0.S = new C2315eC0();
        jc02.S = new C2486fC0();
        jc03.S = new C2657gC0();
        jc04.S = new C2828hC0();
        jc05.S = new C2133d80();
        jc06.S = new C2999iC0();
    }

    /* access modifiers changed from: public */
    EnumC3169jC0(String str) {
        this.R = str;
    }

    public static EnumC3169jC0 a(Class cls, boolean z) {
        if (z) {
            return (EnumC3169jC0) M.get(cls);
        }
        return (EnumC3169jC0) L.get(cls);
    }

    public AbstractC3511lC0 b() {
        return this.S.a();
    }
}
