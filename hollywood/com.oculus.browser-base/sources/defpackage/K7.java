package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: K7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K7 {

    /* renamed from: a  reason: collision with root package name */
    public final View f8344a;
    public final C3840n8 b;
    public int c = -1;
    public C0392Gi1 d;
    public C0392Gi1 e;
    public C0392Gi1 f;

    public K7(View view) {
        this.f8344a = view;
        this.b = C3840n8.a();
    }

    public void a() {
        Drawable background = this.f8344a.getBackground();
        if (background != null) {
            boolean z = true;
            if (this.d != null) {
                if (this.f == null) {
                    this.f = new C0392Gi1();
                }
                C0392Gi1 gi1 = this.f;
                gi1.f8104a = null;
                gi1.d = false;
                gi1.b = null;
                gi1.c = false;
                View view = this.f8344a;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                ColorStateList backgroundTintList = view.getBackgroundTintList();
                if (backgroundTintList != null) {
                    gi1.d = true;
                    gi1.f8104a = backgroundTintList;
                }
                PorterDuff.Mode backgroundTintMode = this.f8344a.getBackgroundTintMode();
                if (backgroundTintMode != null) {
                    gi1.c = true;
                    gi1.b = backgroundTintMode;
                }
                if (gi1.d || gi1.c) {
                    C3840n8.f(background, gi1, this.f8344a.getDrawableState());
                } else {
                    z = false;
                }
                if (z) {
                    return;
                }
            }
            C0392Gi1 gi12 = this.e;
            if (gi12 != null) {
                C3840n8.f(background, gi12, this.f8344a.getDrawableState());
                return;
            }
            C0392Gi1 gi13 = this.d;
            if (gi13 != null) {
                C3840n8.f(background, gi13, this.f8344a.getDrawableState());
            }
        }
    }

    public ColorStateList b() {
        C0392Gi1 gi1 = this.e;
        if (gi1 != null) {
            return gi1.f8104a;
        }
        return null;
    }

    public PorterDuff.Mode c() {
        C0392Gi1 gi1 = this.e;
        if (gi1 != null) {
            return gi1.b;
        }
        return null;
    }

    public void d(AttributeSet attributeSet, int i) {
        Context context = this.f8344a.getContext();
        int[] iArr = FJ0.R0;
        C0514Ii1 q = C0514Ii1.q(context, attributeSet, iArr, i, 0);
        View view = this.f8344a;
        AbstractC1920bu1.m(view, view.getContext(), iArr, attributeSet, q.b, i, 0);
        try {
            if (q.o(0)) {
                this.c = q.l(0, -1);
                ColorStateList d2 = this.b.d(this.f8344a.getContext(), this.c);
                if (d2 != null) {
                    g(d2);
                }
            }
            if (q.o(1)) {
                this.f8344a.setBackgroundTintList(q.c(1));
            }
            if (q.o(2)) {
                this.f8344a.setBackgroundTintMode(XI.c(q.j(2, -1), null));
            }
            q.b.recycle();
        } catch (Throwable th) {
            q.b.recycle();
            throw th;
        }
    }

    public void e() {
        this.c = -1;
        g(null);
        a();
    }

    public void f(int i) {
        this.c = i;
        C3840n8 n8Var = this.b;
        g(n8Var != null ? n8Var.d(this.f8344a.getContext(), i) : null);
        a();
    }

    public void g(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (this.d == null) {
                this.d = new C0392Gi1();
            }
            C0392Gi1 gi1 = this.d;
            gi1.f8104a = colorStateList;
            gi1.d = true;
        } else {
            this.d = null;
        }
        a();
    }

    public void h(ColorStateList colorStateList) {
        if (this.e == null) {
            this.e = new C0392Gi1();
        }
        C0392Gi1 gi1 = this.e;
        gi1.f8104a = colorStateList;
        gi1.d = true;
        a();
    }

    public void i(PorterDuff.Mode mode) {
        if (this.e == null) {
            this.e = new C0392Gi1();
        }
        C0392Gi1 gi1 = this.e;
        gi1.b = mode;
        gi1.c = true;
        a();
    }
}
