package defpackage;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import java.util.List;
import org.chromium.ui.widget.ViewLookupCachingFrameLayout;

/* renamed from: VR0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class VR0 extends ViewLookupCachingFrameLayout implements Checkable, View.OnClickListener, View.OnLongClickListener, View.OnTouchListener, AbstractC3039iS0 {
    public C3209jS0 I;

    /* renamed from: J  reason: collision with root package name */
    public Object f9084J;
    public Boolean K;
    public boolean L = true;
    public float M;
    public float N;

    public VR0(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // defpackage.AbstractC3039iS0
    public void b(List list) {
        setChecked(this.I.c(this.f9084J));
    }

    public abstract void f();

    public void g(Object obj) {
        this.f9084J = obj;
        setChecked(this.I.c.contains(obj));
    }

    public void h(C3209jS0 js0) {
        C3209jS0 js02 = this.I;
        if (js02 != js0) {
            if (js02 != null) {
                js02.d.c(this);
            }
            this.I = js0;
            js0.d.b(this);
        }
    }

    public boolean i(Object obj) {
        return this.I.f(obj);
    }

    public boolean isChecked() {
        Boolean bool = this.K;
        return bool != null && bool.booleanValue();
    }

    public void j(boolean z) {
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        C3209jS0 js0 = this.I;
        if (js0 != null) {
            setChecked(js0.c(this.f9084J));
        }
    }

    public void onClick(View view) {
        if (!this.L) {
            setChecked(i(this.f9084J));
        } else if (this.I.d()) {
            onLongClick(view);
        } else {
            f();
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        setChecked(false);
        this.K = null;
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        setOnTouchListener(this);
        setOnClickListener(this);
        setOnLongClickListener(this);
        setAccessibilityDelegate(new UR0(this));
    }

    public boolean onLongClick(View view) {
        if (Math.abs(this.N - this.M) >= 100.0f) {
            return true;
        }
        setChecked(i(this.f9084J));
        return true;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            float x = motionEvent.getX();
            this.N = x;
            this.M = x;
            return false;
        } else if (actionMasked != 2) {
            return false;
        } else {
            this.N = motionEvent.getX();
            return false;
        }
    }

    public void setChecked(boolean z) {
        Boolean bool = this.K;
        if (bool == null || z != bool.booleanValue()) {
            boolean z2 = this.K != null;
            this.K = Boolean.valueOf(z);
            j(z2);
        }
    }

    public void toggle() {
        setChecked(!isChecked());
    }
}
