package defpackage;

import org.chromium.net.NetworkChangeNotifier;

/* renamed from: vn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5311vn0 implements AbstractC0524In0 {
    public final /* synthetic */ NetworkChangeNotifier F;

    public C5311vn0(NetworkChangeNotifier networkChangeNotifier) {
        this.F = networkChangeNotifier;
    }

    @Override // defpackage.AbstractC0524In0
    public void a(int i) {
        NetworkChangeNotifier networkChangeNotifier = this.F;
        networkChangeNotifier.f = i;
        networkChangeNotifier.e(i, networkChangeNotifier.getCurrentDefaultNetId());
    }

    @Override // defpackage.AbstractC0524In0
    public void b(long j, int i) {
        this.F.f(j, i);
    }

    @Override // defpackage.AbstractC0524In0
    public void c(int i) {
        this.F.d(i);
    }

    @Override // defpackage.AbstractC0524In0
    public void d(long[] jArr) {
        this.F.i(jArr);
    }

    @Override // defpackage.AbstractC0524In0
    public void k(long j) {
        this.F.g(j);
    }

    @Override // defpackage.AbstractC0524In0
    public void l(long j) {
        this.F.h(j);
    }
}
