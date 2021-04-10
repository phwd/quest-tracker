package defpackage;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import java.util.ArrayList;

/* renamed from: wS  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5425wS extends FrameLayout {
    public ArrayList F;
    public ArrayList G;
    public boolean H = true;

    public C5425wS(Context context, AttributeSet attributeSet, KS ks) {
        super(context, attributeSet);
        String classAttribute = attributeSet.getClassAttribute();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.R);
        classAttribute = classAttribute == null ? obtainStyledAttributes.getString(0) : classAttribute;
        String string = obtainStyledAttributes.getString(1);
        obtainStyledAttributes.recycle();
        int id = getId();
        AbstractComponentCallbacksC3550lS I = ks.I(id);
        if (classAttribute != null && I == null) {
            if (id <= 0) {
                throw new IllegalStateException(AbstractC2531fV.g("FragmentContainerView must have an android:id to add Fragment ", classAttribute, string != null ? AbstractC2531fV.f(" with tag ", string) : ""));
            }
            AbstractComponentCallbacksC3550lS a2 = ks.P().a(context.getClassLoader(), classAttribute);
            a2.s0(attributeSet, null);
            C0317Fe fe = new C0317Fe(ks);
            fe.p = true;
            a2.j0 = this;
            fe.i(getId(), a2, string, 1);
            if (!fe.g) {
                fe.h = false;
                fe.q.E(fe, true);
                return;
            }
            throw new IllegalStateException("This transaction is already being added to the back stack");
        }
    }

    public final void a(View view) {
        ArrayList arrayList;
        if (view.getAnimation() != null || ((arrayList = this.G) != null && arrayList.contains(view))) {
            if (this.F == null) {
                this.F = new ArrayList();
            }
            this.F.add(view);
        }
    }

    @Override // android.view.ViewGroup
    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if ((tag instanceof AbstractComponentCallbacksC3550lS ? (AbstractComponentCallbacksC3550lS) tag : null) != null) {
            super.addView(view, i, layoutParams);
            return;
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    public boolean addViewInLayout(View view, int i, ViewGroup.LayoutParams layoutParams, boolean z) {
        Object tag = view.getTag(R.id.fragment_container_view_tag);
        if ((tag instanceof AbstractComponentCallbacksC3550lS ? (AbstractComponentCallbacksC3550lS) tag : null) != null) {
            return super.addViewInLayout(view, i, layoutParams, z);
        }
        throw new IllegalStateException("Views added to a FragmentContainerView must be associated with a Fragment. View " + view + " is not associated with a Fragment.");
    }

    public void dispatchDraw(Canvas canvas) {
        if (this.H && this.F != null) {
            for (int i = 0; i < this.F.size(); i++) {
                super.drawChild(canvas, (View) this.F.get(i), getDrawingTime());
            }
        }
        super.dispatchDraw(canvas);
    }

    public boolean drawChild(Canvas canvas, View view, long j) {
        ArrayList arrayList;
        if (!this.H || (arrayList = this.F) == null || arrayList.size() <= 0 || !this.F.contains(view)) {
            return super.drawChild(canvas, view, j);
        }
        return false;
    }

    public void endViewTransition(View view) {
        ArrayList arrayList = this.G;
        if (arrayList != null) {
            arrayList.remove(view);
            ArrayList arrayList2 = this.F;
            if (arrayList2 != null && arrayList2.remove(view)) {
                this.H = true;
            }
        }
        super.endViewTransition(view);
    }

    public WindowInsets onApplyWindowInsets(WindowInsets windowInsets) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).dispatchApplyWindowInsets(new WindowInsets(windowInsets));
        }
        return windowInsets;
    }

    public void removeAllViewsInLayout() {
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            a(getChildAt(childCount));
        }
        super.removeAllViewsInLayout();
    }

    public void removeDetachedView(View view, boolean z) {
        if (z) {
            a(view);
        }
        super.removeDetachedView(view, z);
    }

    public void removeView(View view) {
        a(view);
        super.removeView(view);
    }

    public void removeViewAt(int i) {
        a(getChildAt(i));
        super.removeViewAt(i);
    }

    public void removeViewInLayout(View view) {
        a(view);
        super.removeViewInLayout(view);
    }

    public void removeViews(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(getChildAt(i3));
        }
        super.removeViews(i, i2);
    }

    public void removeViewsInLayout(int i, int i2) {
        for (int i3 = i; i3 < i + i2; i3++) {
            a(getChildAt(i3));
        }
        super.removeViewsInLayout(i, i2);
    }

    public void setLayoutTransition(LayoutTransition layoutTransition) {
        throw new UnsupportedOperationException("FragmentContainerView does not support Layout Transitions or animateLayoutChanges=\"true\".");
    }

    public void startViewTransition(View view) {
        if (view.getParent() == this) {
            if (this.G == null) {
                this.G = new ArrayList();
            }
            this.G.add(view);
        }
        super.startViewTransition(view);
    }
}
