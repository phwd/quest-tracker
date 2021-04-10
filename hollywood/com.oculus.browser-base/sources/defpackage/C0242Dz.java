package defpackage;

import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* renamed from: Dz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0242Dz {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0292Et0 f7929a;
    public float b;
    public int c;
    public boolean d;
    public String e;
    public boolean f;
    public int g;
    public float h;
    public C5677xw i;
    public Interpolator j;

    public C0242Dz(AbstractC0292Et0 et0) {
        this.f7929a = et0;
    }

    public final void a(boolean z) {
        if (!z || this.b <= 0.0f) {
            if (this.j == null) {
                this.j = new PathInterpolator(0.4f, 0.0f, 0.6f, 1.0f);
            }
            C5677xw xwVar = this.i;
            if (xwVar != null) {
                xwVar.cancel();
            }
            C5677xw c2 = C5677xw.c(this.f7929a.D(), this.h, z ? 1.0f : 0.0f, 218, new C0120Bz(this));
            this.i = c2;
            c2.K = this.j;
            c2.H.b(new C0181Cz(this));
            this.i.start();
        }
    }

    public void b(boolean z) {
        if ((this.f || this.d) && z) {
            a(false);
            return;
        }
        C5677xw xwVar = this.i;
        if (xwVar != null) {
            xwVar.cancel();
        }
        this.c = 0;
        this.d = false;
        this.e = "";
        this.f = false;
        this.h = 0.0f;
    }
}
