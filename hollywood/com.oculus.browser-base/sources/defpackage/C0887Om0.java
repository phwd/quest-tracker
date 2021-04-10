package defpackage;

import android.content.Context;
import android.gesture.GesturePoint;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import java.util.Objects;

/* renamed from: Om0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0887Om0 {

    /* renamed from: a  reason: collision with root package name */
    public final float f8647a;
    public final ViewGroup b;
    public final Context c;
    public C0682Ld1 d;
    public GestureDetector e;
    public View.OnAttachStateChangeListener f;
    public final Q31 g;
    public final Q31 h;
    public int i = 0;
    public UH0 j;
    public float k;

    public C0887Om0(UH0 uh0, ViewGroup viewGroup, Q31 q31, Q31 q312) {
        this.j = uh0;
        this.b = viewGroup;
        Context context = viewGroup.getContext();
        this.c = context;
        this.g = q31;
        this.h = q312;
        this.f8647a = viewGroup.getResources().getDisplayMetrics().density * 24.0f;
        this.e = new GestureDetector(context, new C0826Nm0(this, null));
        View$OnAttachStateChangeListenerC0765Mm0 mm0 = new View$OnAttachStateChangeListenerC0765Mm0(this);
        this.f = mm0;
        viewGroup.addOnAttachStateChangeListener(mm0);
    }

    public void a(float f2) {
        float f3 = this.k + f2;
        this.k = f3;
        int i2 = this.i;
        if (i2 == 2) {
            this.j.k(AbstractC5261vV.d, f3);
        } else if (i2 == 3) {
            this.j.k(AbstractC5261vV.e, f3);
        }
        if (this.i == 2 && ((Boolean) this.h.get()).booleanValue()) {
            this.i = 0;
        }
    }

    public void b(boolean z) {
        this.j.j(AbstractC5261vV.c, z);
        int i2 = this.i;
        if (i2 == 2) {
            this.j.l(AbstractC5261vV.f11481a, 3);
        } else if (i2 == 3) {
            this.j.l(AbstractC5261vV.f11481a, 4);
        }
        this.k = 0.0f;
    }

    public void c() {
        int i2 = this.i;
        if (i2 == 2) {
            this.j.l(AbstractC5261vV.f11481a, 5);
        } else if (i2 == 3) {
            this.j.l(AbstractC5261vV.f11481a, 6);
        }
        this.i = 0;
        this.k = 0.0f;
    }

    public boolean d(boolean z, float f2, float f3) {
        int i2 = 0;
        if (this.d == null) {
            return false;
        }
        this.j.j(AbstractC5261vV.b, z);
        C0682Ld1 ld1 = this.d;
        Objects.requireNonNull(ld1);
        boolean k2 = z ? ld1.f8430a.k() : true;
        if (k2) {
            if (this.i != 1) {
                this.j.l(AbstractC5261vV.f11481a, 5);
            }
            UH0 uh0 = this.j;
            SH0 sh0 = AbstractC5261vV.f;
            if (!z) {
                C0682Ld1 ld12 = this.d;
                if (!ld12.f8430a.h() && ((Boolean) ld12.c.apply(ld12.f8430a)).booleanValue()) {
                    i2 = 1;
                    uh0.l(sh0, i2);
                    this.j.l(AbstractC5261vV.f11481a, 1);
                    this.i = 2;
                }
            }
            if (!z) {
                C0682Ld1 ld13 = this.d;
                if (!ld13.f8430a.h() && (!((Boolean) ld13.c.apply(ld13.f8430a)).booleanValue() || T51.l(ld13.f8430a))) {
                    i2 = 2;
                }
            }
            uh0.l(sh0, i2);
            this.j.l(AbstractC5261vV.f11481a, 1);
            this.i = 2;
        } else {
            if (this.i != 1) {
                this.j.l(AbstractC5261vV.f11481a, 6);
            }
            this.j.m(AbstractC5261vV.g, new GesturePoint(f2, f3, 0));
            this.j.l(AbstractC5261vV.f11481a, 2);
            this.i = 3;
        }
        return k2;
    }
}
