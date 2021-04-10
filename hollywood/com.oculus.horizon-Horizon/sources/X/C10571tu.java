package X;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1tu  reason: invalid class name and case insensitive filesystem */
public class C10571tu implements AnonymousClass1u0 {
    public Map<String, String> A00;
    public final /* synthetic */ AnonymousClass1tq A01;
    public final /* synthetic */ Map A02;

    public C10571tu(AnonymousClass1tq r1, Map map) {
        this.A01 = r1;
        this.A02 = map;
    }

    @Override // X.AnonymousClass1u0
    public final void A9m(String str, double d) {
        this.A00.put(str, String.valueOf(d));
    }

    @Override // X.AnonymousClass1u0
    public final void A9n(String str, int i) {
        this.A00.put(str, String.valueOf(i));
    }

    @Override // X.AnonymousClass1u0
    public final void A9o(String str, long j) {
        this.A00.put(str, String.valueOf(j));
    }

    @Override // X.AnonymousClass1u0
    public final void A9p(String str, String str2) {
        this.A00.put(str, str2);
    }

    @Override // X.AnonymousClass1u0
    public final void A9q(String str, boolean z) {
        this.A00.put(str, String.valueOf(z));
    }

    @Override // X.AnonymousClass1u0
    public final void A9r(String str, int[] iArr) {
        this.A00.put(str, Arrays.toString(iArr));
    }

    @Override // X.AnonymousClass1u0
    public final void A9s(String str, long[] jArr) {
        this.A00.put(str, Arrays.toString(jArr));
    }

    @Override // X.AnonymousClass1u0
    public final void A9t(String str, String[] strArr) {
        this.A00.put(str, Arrays.toString(strArr));
    }

    @Override // X.AnonymousClass1u0
    public final void A9v(String str) {
        HashMap hashMap = new HashMap();
        this.A00 = hashMap;
        this.A02.put(str, hashMap);
    }
}
