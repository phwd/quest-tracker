package defpackage;

import android.content.Context;

/* renamed from: BG1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class BG1 implements MJ {
    @Override // defpackage.MJ
    public final KJ a(Context context, String str, LJ lj) {
        KJ kj = new KJ();
        kj.f8359a = lj.a(context, str);
        int b = lj.b(context, str, true);
        kj.b = b;
        int i = kj.f8359a;
        if (i == 0 && b == 0) {
            kj.c = 0;
        } else if (b >= i) {
            kj.c = 1;
        } else {
            kj.c = -1;
        }
        return kj;
    }
}
