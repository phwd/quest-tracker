package defpackage;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ViewTreeObserver$OnPreDrawListenerC1587a00 implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
    public final View F;
    public ZZ G;
    public double H;

    public ViewTreeObserver$OnPreDrawListenerC1587a00(View view) {
        this.F = view;
    }

    public void a(ZZ zz) {
        if (this.G != null) {
            this.F.removeOnAttachStateChangeListener(this);
            View view = this.F;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (view.isAttachedToWindow()) {
                this.F.getViewTreeObserver().removeOnPreDrawListener(this);
            }
        }
        this.G = zz;
        if (zz != null) {
            this.F.addOnAttachStateChangeListener(this);
            View view2 = this.F;
            AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
            if (view2.isAttachedToWindow()) {
                this.F.getViewTreeObserver().addOnPreDrawListener(this);
            }
        }
    }

    public boolean onPreDraw() {
        int i;
        ViewParent parent = this.F.getParent();
        if (parent == null) {
            return true;
        }
        Rect rect = new Rect(0, 0, this.F.getWidth(), this.F.getHeight());
        if (this.H != 0.0d) {
            i = (int) (((double) this.F.getHeight()) * this.H);
        } else {
            i = (this.F.getHeight() * 2) / 3;
        }
        if (!parent.getChildVisibleRect(this.F, rect, null) || rect.height() < i) {
            return true;
        }
        this.G.a();
        return true;
    }

    public void onViewAttachedToWindow(View view) {
        this.F.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void onViewDetachedFromWindow(View view) {
        this.F.getViewTreeObserver().removeOnPreDrawListener(this);
    }
}
