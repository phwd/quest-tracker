package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import java.security.PrivateKey;
import org.chromium.base.ThreadUtils;

/* renamed from: aO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1655aO0 extends AbstractC2032cb {
    public byte[][] i;
    public PrivateKey j;
    public final long k;
    public final Context l;
    public final String m;
    public final Activity n;

    public C1655aO0(Context context, long j2, String str, Activity activity) {
        this.k = j2;
        this.l = context;
        this.m = str;
        this.n = activity;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x004a A[ADDED_TO_REGION] */
    @Override // defpackage.AbstractC2032cb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object c() {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C1655aO0.c():java.lang.Object");
    }

    @Override // defpackage.AbstractC2032cb
    public void k(Object obj) {
        Void r4 = (Void) obj;
        Object obj2 = ThreadUtils.f10596a;
        N.M8LmNuWo(this.k, this.i, this.j);
        this.n.finish();
    }
}
