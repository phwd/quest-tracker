package defpackage;

import android.content.Context;
import android.view.ViewGroup;
import com.oculus.browser.R;
import org.chromium.ui.base.LocalizationUtils;

/* renamed from: rz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4666rz {

    /* renamed from: a  reason: collision with root package name */
    public C1796bA f11240a;
    public final C5346vz b;
    public final C5380wA c;
    public final C5006tz d;
    public final C3846nA e;
    public final C5176uz f;
    public final float g;
    public C0242Dz h;
    public float i;
    public float j;
    public final float k;
    public final float l;
    public final float m;
    public final boolean n;
    public C5677xw o;
    public C5677xw p;
    public boolean q;
    public float r;
    public float s;

    public C4666rz(C1796bA bAVar, Context context, ViewGroup viewGroup, IJ ij) {
        this.f11240a = bAVar;
        boolean m0 = bAVar.m0();
        this.n = m0;
        this.h = new C0242Dz(bAVar);
        this.b = new C5346vz(bAVar, context, viewGroup, ij);
        this.c = new C5380wA(bAVar, context, viewGroup, ij);
        this.d = new C5006tz(bAVar, context, viewGroup, ij, m0);
        this.e = new C3846nA(context, ij);
        this.f = new C5176uz(context, ij);
        this.k = context.getResources().getDimension(R.dimen.f17810_resource_name_obfuscated_RES_2131165400);
        this.l = context.getResources().getDimension(R.dimen.f17800_resource_name_obfuscated_RES_2131165399);
        this.g = context.getResources().getDimension(R.dimen.f17790_resource_name_obfuscated_RES_2131165398);
        context.getResources().getDimension(R.dimen.f23500_resource_name_obfuscated_RES_2131165969);
        this.m = context.getResources().getDisplayMetrics().density;
    }

    public void a() {
        if (this.o == null) {
            C5677xw c2 = C5677xw.c(this.f11240a.D(), 0.0f, 1.0f, 218, null);
            this.o = c2;
            c2.I.add(new C4325pz(this));
        }
        this.o.cancel();
        this.o.start();
    }

    public void b(float f2) {
        C0242Dz dz = this.h;
        dz.b = f2;
        if (dz.d || dz.f) {
            dz.h = 1.0f - f2;
        }
        this.d.n(f2);
        this.c.n(f2);
        this.b.n(f2);
    }

    public void c(String str) {
        C5677xw xwVar = this.o;
        if (xwVar != null) {
            xwVar.cancel();
        }
        C5006tz tzVar = this.d;
        tzVar.b0 = false;
        tzVar.c0 = 0.0f;
        tzVar.a0 = false;
        this.e.o();
        C5380wA wAVar = this.c;
        wAVar.e();
        wAVar.Z.setText(AbstractC1145St0.l(str));
        wAVar.m(true);
        this.i = 0.0f;
        this.j = 1.0f;
    }

    public final void d(float f2) {
        if (!this.q) {
            if (this.f11240a.i0() || this.n) {
                int H = this.f11240a.H();
                float f3 = 0.0f;
                if (this.f11240a.i0()) {
                    this.r = 0.0f;
                    this.s = (float) H;
                } else {
                    boolean isLayoutRtl = LocalizationUtils.isLayoutRtl();
                    C1796bA bAVar = this.f11240a;
                    float I = bAVar.I() + bAVar.W;
                    C1796bA bAVar2 = this.f11240a;
                    float f4 = I + ((float) bAVar2.M);
                    float f5 = this.m;
                    float f6 = f4 * f5;
                    float f7 = ((float) H) - f6;
                    float f8 = f2 - (bAVar2.S * f5);
                    if ((!isLayoutRtl || f8 <= f6) && (isLayoutRtl || f8 >= f7)) {
                        if (!isLayoutRtl) {
                            f3 = f7;
                        }
                        this.r = f3;
                        this.s = f6;
                    } else {
                        if (isLayoutRtl) {
                            f3 = f6;
                        }
                        this.r = f3;
                        this.s = f7;
                    }
                }
                this.q = true;
                if (this.p == null) {
                    C5677xw xwVar = new C5677xw(this.f11240a.D());
                    this.p = xwVar;
                    xwVar.i(218);
                    C5677xw xwVar2 = this.p;
                    xwVar2.H.b(new C4496qz(this));
                }
                this.p.cancel();
                this.p.start();
            }
        }
    }
}
