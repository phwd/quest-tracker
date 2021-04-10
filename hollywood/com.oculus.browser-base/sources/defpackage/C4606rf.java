package defpackage;

import android.os.Bundle;
import android.util.Base64;

/* renamed from: rf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4606rf implements AbstractC1538Ze1 {

    /* renamed from: a  reason: collision with root package name */
    public String f11212a;
    public final Bundle b;
    public final int c;
    public final boolean d;

    public C4606rf(Bundle bundle, int i, boolean z) {
        this.b = bundle;
        this.c = i;
        this.d = z;
    }

    @Override // defpackage.AbstractC1538Ze1
    public void a(C1477Ye1 ye1) {
        OO0 n = TO0.n();
        n.e(2);
        this.f11212a = Base64.encodeToString(((TO0) n.a()).c(), 0);
    }

    @Override // defpackage.AbstractC1538Ze1
    public void b(C1355We1 we1) {
        P21 f0 = P21.f0();
        try {
            OO0 n = TO0.n();
            n.e(1);
            this.f11212a = Base64.encodeToString(((TO0) n.a()).c(), 0);
            f0.close();
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0247  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x0261  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0290 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0265 A[SYNTHETIC] */
    @Override // defpackage.AbstractC1538Ze1
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(defpackage.C1233Ue1 r19) {
        /*
        // Method dump skipped, instructions count: 700
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4606rf.c(Ue1):void");
    }
}
