package defpackage;

import android.view.View;

/* renamed from: kj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3423kj0 implements AbstractC2620g0 {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f10298a;

    public C3423kj0(Runnable runnable) {
        this.f10298a = runnable;
    }

    @Override // defpackage.AbstractC2620g0
    public boolean a(View view, Y y) {
        this.f10298a.run();
        return false;
    }
}
