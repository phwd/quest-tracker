package defpackage;

import android.widget.SeekBar;

/* renamed from: Ef0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0260Ef0 extends AbstractC0750Mg0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DialogC0504If0 f7971a;

    public C0260Ef0(DialogC0504If0 if0) {
        this.f7971a = if0;
    }

    @Override // defpackage.AbstractC0750Mg0
    public void e(C3246jh0 jh0, C2392eh0 eh0) {
        this.f7971a.s(true);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void h(C3246jh0 jh0, C2392eh0 eh0) {
        this.f7971a.s(false);
    }

    @Override // defpackage.AbstractC0750Mg0
    public void j(C3246jh0 jh0, C2392eh0 eh0) {
        SeekBar seekBar = (SeekBar) this.f7971a.v0.get(eh0);
        int i = eh0.o;
        int i2 = DialogC0504If0.I;
        if (seekBar != null && this.f7971a.q0 != eh0) {
            seekBar.setProgress(i);
        }
    }
}
