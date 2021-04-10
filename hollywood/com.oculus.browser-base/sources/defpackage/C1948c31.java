package defpackage;

import android.os.Handler;

/* renamed from: c31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1948c31 extends VK {
    public final /* synthetic */ C2631g31 F;

    public C1948c31(C2631g31 g31) {
        this.F = g31;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        this.F.h();
        new Handler().post(new RunnableC1777b31(this));
    }
}
