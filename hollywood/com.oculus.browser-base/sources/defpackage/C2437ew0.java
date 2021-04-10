package defpackage;

import android.util.SparseBooleanArray;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: ew0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2437ew0 {

    /* renamed from: a  reason: collision with root package name */
    public static final C1925bw0 f9890a = new C1925bw0();
    public final List b;
    public final List c;
    public final Map d = new C4931ta();
    public final SparseBooleanArray e = new SparseBooleanArray();
    public final C2266dw0 f;

    public C2437ew0(List list, List list2) {
        this.b = list;
        this.c = list2;
        int size = list.size();
        int i = Integer.MIN_VALUE;
        C2266dw0 dw0 = null;
        for (int i2 = 0; i2 < size; i2++) {
            C2266dw0 dw02 = (C2266dw0) this.b.get(i2);
            int i3 = dw02.e;
            if (i3 > i) {
                dw0 = dw02;
                i = i3;
            }
        }
        this.f = dw0;
    }

    public List a() {
        return Collections.unmodifiableList(this.b);
    }
}
