package defpackage;

import android.content.Context;

/* renamed from: YE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class YE1 implements MJ {
    @Override // defpackage.MJ
    public final KJ a(Context context, String str, LJ lj) {
        KJ kj = new KJ();
        int b = lj.b(context, str, true);
        kj.b = b;
        if (b != 0) {
            kj.c = 1;
        } else {
            int a2 = lj.a(context, str);
            kj.f8359a = a2;
            if (a2 != 0) {
                kj.c = -1;
            }
        }
        return kj;
    }
}
