package org.chromium.chrome.browser.omnibox;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.TouchDelegate;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.TraceEvent;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocationBarPhone extends AbstractView$OnClickListenerC5272va0 {
    public View g0;
    public View h0;
    public View i0;

    public LocationBarPhone(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.layout.f39150_resource_name_obfuscated_RES_2131624224);
        C3974nw nwVar = new C3974nw(this);
        this.e0 = nwVar;
        setTouchDelegate(nwVar);
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void b(boolean z, boolean z2) {
        super.b(z, z2);
        if (!z) {
            this.c0.setVisibility(8);
        }
        C5698y21 y21 = this.O.G;
        C5528x21 x21 = y21.a0;
        boolean z3 = y21.g0;
        Objects.requireNonNull(x21);
        if (AbstractC5762yQ0.g(z3) && !z && AbstractC4089od0.a(y21.e0, 0.0f) && AbstractC5762yQ0.a(y21.c0)) {
            y21.b(false);
        }
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        boolean z = false;
        if (view == this.h0 && this.c0.getVisibility() == 0) {
            canvas.save();
            if (this.h0.getLeft() < this.c0.getLeft()) {
                canvas.clipRect(0, 0, (int) this.c0.getX(), getBottom());
            } else {
                canvas.clipRect(this.c0.getX() + ((float) this.c0.getWidth()), 0.0f, (float) getWidth(), (float) getBottom());
            }
            z = true;
        }
        boolean drawChild = super.drawChild(canvas, view, j);
        if (z) {
            canvas.restore();
        }
        return drawChild;
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void i() {
        super.i();
        k(AbstractC5762yQ0.g(this.L.a()));
        y();
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void j(boolean z) {
        if (z) {
            setFocusable(false);
            setFocusableInTouchMode(false);
        }
        q(true);
        x();
        super.j(z);
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void k(boolean z) {
        this.g0 = z ? this.i0 : this.h0;
        C5698y21 y21 = this.O.G;
        y21.M = z;
        y21.e();
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void onFinishInflate() {
        super.onFinishInflate();
        this.h0 = findViewById(R.id.url_bar);
        this.i0 = findViewById(R.id.location_bar_status);
        View view = this.g0;
        if (view == null) {
            view = this.h0;
        }
        this.g0 = view;
        Rect rect = new Rect();
        this.c0.getHitRect(rect);
        rect.left -= 15;
        this.e0.f10521a.add(new TouchDelegate(rect, this.c0));
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent j0 = TraceEvent.j0("LocationBarPhone.onLayout");
        try {
            super.onLayout(z, i, i2, i3, i4);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void onMeasure(int i, int i2) {
        TraceEvent j0 = TraceEvent.j0("LocationBarPhone.onMeasure");
        try {
            super.onMeasure(i, i2);
            if (j0 != null) {
                j0.close();
                return;
            }
            return;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void p(float f) {
        this.b0 = f;
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        if (i > 0) {
            this.c0.setVisibility(0);
        } else if (i == 0 && !this.R) {
            this.c0.setVisibility(8);
        }
        super.t();
        v();
        C5698y21 y21 = this.O.G;
        y21.e0 = f;
        C5528x21 x21 = y21.a0;
        boolean z = y21.g0;
        Objects.requireNonNull(x21);
        if (AbstractC5762yQ0.g(z)) {
            if (y21.e0 > 0.0f) {
                y21.b(true);
            }
            if (AbstractC5762yQ0.a(y21.c0)) {
                if (!y21.H) {
                    f = AbstractC4089od0.b((f - y21.i0) / y21.j0, 0.0f, 1.0f);
                }
                y21.F.k(A21.g, f);
            } else {
                y21.F.k(A21.g, 1.0f);
            }
            y21.e();
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void t() {
        super.t();
        v();
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void w(boolean z, boolean z2, String str) {
        R11 r11 = this.O;
        C5698y21 y21 = r11.G;
        y21.N = true;
        y21.O = z2;
        y21.f0 = str;
        y21.e();
        r11.F.e();
        boolean g = AbstractC5762yQ0.g(this.L.a());
        if (AbstractC5762yQ0.e()) {
            int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.f24860_resource_name_obfuscated_RES_2131166105);
            setPaddingRelative(dimensionPixelOffset, getPaddingTop(), dimensionPixelOffset, getPaddingBottom());
        }
        if (g) {
            setClipToPadding(false);
        }
        k(g);
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void x() {
        this.O.G.F.j(A21.f7651a, this.T || this.R);
    }

    public final void y() {
        if (AbstractC5762yQ0.g(this.L.a())) {
            if (AbstractC5762yQ0.a(this.L)) {
                this.O.q(hasFocus());
            } else {
                this.O.q(true);
            }
        }
    }
}
