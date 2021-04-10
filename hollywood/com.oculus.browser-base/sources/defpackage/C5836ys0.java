package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.recyclerview.widget.RecyclerView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* renamed from: ys0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5836ys0 extends RecyclerView {
    public final int k1;
    public final int l1;
    public final int[] m1 = new int[2];
    public final Rect n1 = new Rect();
    public final C5666xs0 o1;
    public C6006zs0 p1;
    public AbstractC0046As0 q1;
    public AbstractC5496ws0 r1;
    public View s1;
    public View t1;
    public ViewTreeObserver.OnGlobalLayoutListener u1;
    public View.OnLayoutChangeListener v1;
    public int w1;
    public int x1;

    public C5836ys0(Context context) {
        super(context, null, 16842861);
        setFocusable(true);
        setFocusableInTouchMode(true);
        C5326vs0 vs0 = new C5326vs0(this);
        PK0 pk0 = this.f9482J;
        OK0 ok0 = pk0.g;
        if (ok0 != null) {
            ok0.b--;
        }
        pk0.g = vs0;
        if (pk0.h.T != null) {
            vs0.b++;
        }
        s0(null);
        C5666xs0 xs0 = new C5666xs0(this, null);
        this.o1 = xs0;
        this.R0 = xs0;
        t0(new C4816ss0(this, context));
        Resources resources = context.getResources();
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.f23350_resource_name_obfuscated_RES_2131165954);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setPaddingRelative(0, 0, 0, dimensionPixelOffset);
        this.k1 = AbstractC2934hr.a(resources, false);
        this.l1 = AbstractC2934hr.a(resources, true);
    }

    public final void C0() {
        View view = this.t1;
        if (view != null) {
            AbstractC4656rv1.d(this.s1, view, this.m1);
            setPadding(this.m1[0], getPaddingTop(), (this.s1.getWidth() - this.t1.getWidth()) - this.m1[0], getPaddingBottom());
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        AbstractC5496ws0 ws0;
        int actionMasked = motionEvent.getActionMasked();
        boolean z = true;
        if ((actionMasked == 1 || actionMasked == 0) && (ws0 = this.r1) != null) {
            if (actionMasked != 1) {
                z = false;
            }
            long eventTime = motionEvent.getEventTime();
            C2379ed edVar = (C2379ed) ws0;
            edVar.r(false);
            if (z) {
                edVar.b0 = eventTime;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.s1.getViewTreeObserver().addOnGlobalLayoutListener(this.u1);
        if (this.t1 != null) {
            C0();
            this.t1.addOnLayoutChangeListener(this.v1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        P().a();
        this.s1.getViewTreeObserver().removeOnGlobalLayoutListener(this.u1);
        View view = this.t1;
        if (view != null) {
            view.removeOnLayoutChangeListener(this.v1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if ((motionEvent.getSource() & 2) != 0 && motionEvent.getToolType(0) == 3 && (actionMasked == 11 || actionMasked == 12)) {
            return true;
        }
        super.onGenericMotionEvent(motionEvent);
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        View w;
        if (!isShown()) {
            return false;
        }
        int i2 = this.p1.L;
        if (R40.b(keyEvent)) {
            return this.p1.x(i2 + 1);
        }
        if (R40.e(keyEvent)) {
            return this.p1.x(i2 - 1);
        }
        if (R40.d(keyEvent) || R40.c(keyEvent)) {
            View w2 = this.p1.w();
            if (w2 != null) {
                return w2.onKeyDown(i, keyEvent);
            }
        } else if (R40.a(keyEvent) && (w = this.p1.w()) != null) {
            return w.performClick();
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        TraceEvent j0 = TraceEvent.j0("OmniboxSuggestionsList.Layout");
        try {
            int i5 = I31.b;
            H31 h31 = new H31("Android.Omnibox.SuggestionList.LayoutTime");
            try {
                super.onLayout(z, i, i2, i3, i4);
                h31.close();
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
            throw th;
        } catch (Throwable th2) {
            AbstractC0754Mh1.f8495a.a(th, th2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void onMeasure(int i, int i2) {
        TraceEvent j0 = TraceEvent.j0("OmniboxSuggestionsList.Measure");
        try {
            int i3 = I31.b;
            H31 h31 = new H31("Android.Omnibox.SuggestionList.MeasureTime");
            try {
                AbstractC4656rv1.d(((C3909na0) this.q1).M.getRootView().findViewById(16908290), this.s1, this.m1);
                int measuredHeight = this.s1.getMeasuredHeight() + this.m1[1];
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null && (layoutParams instanceof ViewGroup.MarginLayoutParams)) {
                    ((ViewGroup.MarginLayoutParams) layoutParams).topMargin = measuredHeight;
                }
                ((C3909na0) this.q1).L.a(this.n1);
                int height = this.n1.height() - measuredHeight;
                if (height != this.w1) {
                    this.w1 = height;
                    if (this.r1 != null) {
                        PostTask.b(Zo1.f9374a, new RunnableC4646rs0(this, height), 0);
                    }
                }
                int i4 = 1073741824;
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.s1.getMeasuredWidth(), 1073741824);
                if (((C3909na0) this.q1).g()) {
                    i4 = Integer.MIN_VALUE;
                }
                super.onMeasure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(height, i4));
                h31.close();
                if (j0 != null) {
                    j0.close();
                    return;
                }
                return;
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
            throw th;
            throw th;
        } catch (Throwable th2) {
            AbstractC0754Mh1.f8495a.a(th, th2);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void q0(AbstractC5750yK0 yk0) {
        C6006zs0 zs0 = (C6006zs0) yk0;
        this.p1 = zs0;
        super.q0(zs0);
    }
}
