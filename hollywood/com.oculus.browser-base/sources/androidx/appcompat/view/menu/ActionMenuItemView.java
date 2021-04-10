package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionMenuItemView extends N8 implements AbstractC2228dj0, View.OnClickListener, AbstractC4846t2 {
    public C0817Ni0 I;

    /* renamed from: J  reason: collision with root package name */
    public CharSequence f9455J;
    public Drawable K;
    public AbstractC4446qi0 L;
    public AbstractView$OnTouchListenerC2013cS M;
    public C3651m2 N;
    public boolean O = g();
    public int P;
    public int Q;
    public int R;

    public ActionMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        Resources resources = context.getResources();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.c, 0, 0);
        this.P = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        this.R = (int) ((resources.getDisplayMetrics().density * 32.0f) + 0.5f);
        setOnClickListener(this);
        this.Q = -1;
        setSaveEnabled(false);
    }

    @Override // defpackage.AbstractC4846t2
    public boolean a() {
        return f();
    }

    @Override // defpackage.AbstractC4846t2
    public boolean b() {
        return f() && this.I.getIcon() == null;
    }

    @Override // defpackage.AbstractC2228dj0
    public C0817Ni0 d() {
        return this.I;
    }

    @Override // defpackage.AbstractC2228dj0
    public void e(C0817Ni0 ni0, int i) {
        this.I = ni0;
        Drawable icon = ni0.getIcon();
        this.K = icon;
        int i2 = 0;
        if (icon != null) {
            int intrinsicWidth = icon.getIntrinsicWidth();
            int intrinsicHeight = icon.getIntrinsicHeight();
            int i3 = this.R;
            if (intrinsicWidth > i3) {
                intrinsicHeight = (int) (((float) intrinsicHeight) * (((float) i3) / ((float) intrinsicWidth)));
                intrinsicWidth = i3;
            }
            if (intrinsicHeight > i3) {
                intrinsicWidth = (int) (((float) intrinsicWidth) * (((float) i3) / ((float) intrinsicHeight)));
            } else {
                i3 = intrinsicHeight;
            }
            icon.setBounds(0, 0, intrinsicWidth, i3);
        }
        setCompoundDrawables(icon, null, null, null);
        h();
        this.f9455J = ni0.getTitleCondensed();
        h();
        setId(ni0.f8568a);
        if (!ni0.isVisible()) {
            i2 = 8;
        }
        setVisibility(i2);
        setEnabled(ni0.isEnabled());
        if (ni0.hasSubMenu() && this.M == null) {
            this.M = new C3309k2(this);
        }
    }

    public boolean f() {
        return !TextUtils.isEmpty(getText());
    }

    public final boolean g() {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        return i >= 480 || (i >= 640 && configuration.screenHeightDp >= 480) || configuration.orientation == 2;
    }

    public final void h() {
        CharSequence charSequence;
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(this.f9455J);
        if (this.K != null) {
            if (!((this.I.y & 4) == 4) || !this.O) {
                z = false;
            }
        }
        boolean z3 = z2 & z;
        CharSequence charSequence2 = null;
        setText(z3 ? this.f9455J : null);
        CharSequence charSequence3 = this.I.q;
        if (TextUtils.isEmpty(charSequence3)) {
            if (z3) {
                charSequence = null;
            } else {
                charSequence = this.I.e;
            }
            setContentDescription(charSequence);
        } else {
            setContentDescription(charSequence3);
        }
        CharSequence charSequence4 = this.I.r;
        if (TextUtils.isEmpty(charSequence4)) {
            if (!z3) {
                charSequence2 = this.I.e;
            }
            Il1.a(this, charSequence2);
            return;
        }
        Il1.a(this, charSequence4);
    }

    public void onClick(View view) {
        AbstractC4446qi0 qi0 = this.L;
        if (qi0 != null) {
            qi0.a(this.I);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.O = g();
        h();
    }

    @Override // defpackage.N8
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        boolean f = f();
        if (f && (i4 = this.Q) >= 0) {
            super.setPadding(i4, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int measuredWidth = getMeasuredWidth();
        if (mode == Integer.MIN_VALUE) {
            i3 = Math.min(size, this.P);
        } else {
            i3 = this.P;
        }
        if (mode != 1073741824 && this.P > 0 && measuredWidth < i3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(i3, 1073741824), i2);
        }
        if (!f && this.K != null) {
            super.setPadding((getMeasuredWidth() - this.K.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        super.onRestoreInstanceState(null);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractView$OnTouchListenerC2013cS cSVar;
        if (!this.I.hasSubMenu() || (cSVar = this.M) == null || !cSVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.Q = i;
        super.setPadding(i, i2, i3, i4);
    }
}
