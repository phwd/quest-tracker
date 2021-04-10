package defpackage;

/* renamed from: j10  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3136j10 extends OK {
    public final /* synthetic */ View$OnAttachStateChangeListenerC3307k10 F;

    public C3136j10(View$OnAttachStateChangeListenerC3307k10 k10) {
        this.F = k10;
    }

    @Override // defpackage.AbstractC0576Jj, defpackage.OK
    public void i(int i) {
        if (!this.F.F.M.isHidden()) {
            this.F.F.R.setVisibility(i == 3 ? 4 : 0);
        }
    }
}
