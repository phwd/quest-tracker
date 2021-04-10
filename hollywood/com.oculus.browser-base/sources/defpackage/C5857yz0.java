package defpackage;

/* renamed from: yz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5857yz0 implements AbstractC4371qE {
    public final /* synthetic */ View$OnLayoutChangeListenerC6027zz0 F;

    public C5857yz0(View$OnLayoutChangeListenerC6027zz0 zz0) {
        this.F = zz0;
    }

    @Override // defpackage.AbstractC4371qE
    public void destroy() {
        View$OnLayoutChangeListenerC6027zz0 zz0 = this.F;
        zz0.R = 2;
        zz0.L.post(zz0.H);
    }
}
