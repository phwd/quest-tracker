package defpackage;

import android.view.View;

/* renamed from: Gl1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Gl1 extends AbstractC2264dv1 {

    /* renamed from: a  reason: collision with root package name */
    public boolean f8107a = false;
    public final /* synthetic */ int b;
    public final /* synthetic */ Hl1 c;

    public Gl1(Hl1 hl1, int i) {
        this.c = hl1;
        this.b = i;
    }

    @Override // defpackage.AbstractC2264dv1, defpackage.AbstractC2094cv1
    public void a(View view) {
        this.f8107a = true;
    }

    @Override // defpackage.AbstractC2094cv1
    public void b(View view) {
        if (!this.f8107a) {
            this.c.f8179a.setVisibility(this.b);
        }
    }

    @Override // defpackage.AbstractC2264dv1, defpackage.AbstractC2094cv1
    public void c(View view) {
        this.c.f8179a.setVisibility(0);
    }
}
