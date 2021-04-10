package defpackage;

import android.text.TextUtils;

/* renamed from: bT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC1845bT0 implements Runnable {
    public final /* synthetic */ AbstractC2016cT0 F;
    public final /* synthetic */ String G;

    public RunnableC1845bT0(C2187dT0 dt0, AbstractC2016cT0 ct0, String str) {
        this.F = ct0;
        this.G = str;
    }

    public void run() {
        C5084uR0 ur0;
        AbstractC2016cT0 ct0 = this.F;
        String str = this.G;
        AB0 ab0 = (AB0) ct0;
        if (ab0.y && (ur0 = ab0.B) != null) {
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i2 >= ur0.f11410a.size()) {
                    break;
                } else if (TextUtils.equals(((C1997cK) ur0.f11410a.get(i2)).g, str)) {
                    int i3 = ur0.c;
                    if (i3 == i2) {
                        ur0.c = -1;
                    } else if (i3 > 0) {
                        if (i3 > i2) {
                            i = 1;
                        }
                        ur0.c = i3 - i;
                    }
                    ur0.f11410a.remove(i2);
                    if (ur0.f11410a.size() == 0) {
                        ur0.c = -2;
                    }
                } else {
                    i2++;
                }
            }
            ab0.s();
            TA0 ta0 = ab0.w;
            if (ta0 != null) {
                ta0.o(4, ab0.B);
            }
        }
    }
}
