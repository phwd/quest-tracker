package defpackage;

import java.util.concurrent.CountDownLatch;

/* renamed from: lf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3583lf1 implements AbstractC3754mf1 {

    /* renamed from: a  reason: collision with root package name */
    public final CountDownLatch f10362a = new CountDownLatch(1);

    public C3583lf1(VI1 vi1) {
    }

    @Override // defpackage.AbstractC1143Ss0
    public final void a(Object obj) {
        this.f10362a.countDown();
    }

    @Override // defpackage.AbstractC0899Os0
    public final void b(Exception exc) {
        this.f10362a.countDown();
    }
}
