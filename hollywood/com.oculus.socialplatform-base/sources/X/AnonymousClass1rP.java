package X;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ActionMenuView;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.AndroidPanelLayer;

/* renamed from: X.1rP  reason: invalid class name */
public abstract class AnonymousClass1rP extends ViewGroup {
    public C11591sO A00;
    public ActionMenuView A01;
    public C003007j A02;
    public boolean A03;
    public boolean A04;
    public int A05;
    public final Context A06;
    public final C11431ry A07 = new C11431ry(this);

    public final C003007j A04(int i, long j) {
        C003007j r0 = this.A02;
        if (r0 != null) {
            r0.A00();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            }
            C003007j A022 = AnonymousClass07f.A02(this);
            A022.A01(1.0f);
            A022.A03(j);
            C11431ry r2 = this.A07;
            r2.A02.A02 = A022;
            r2.A00 = 0;
            A022.A04(r2);
            return A022;
        }
        C003007j A023 = AnonymousClass07f.A02(this);
        A023.A01(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        A023.A03(j);
        C11431ry r1 = this.A07;
        r1.A02.A02 = A023;
        r1.A00 = i;
        A023.A04(r1);
        return A023;
    }

    public int getAnimatedVisibility() {
        if (this.A02 != null) {
            return this.A07.A00;
        }
        return getVisibility();
    }

    public int getContentHeight() {
        return this.A05;
    }

    public void setContentHeight(int i) {
        this.A05 = i;
        requestLayout();
    }

    public AnonymousClass1rP(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.A06 = context;
        } else {
            this.A06 = new ContextThemeWrapper(context, i2);
        }
    }

    public static final int A01(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) >> 1);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
            return -measuredWidth;
        }
        view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        return measuredWidth;
    }

    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, C11081qa.A00, R.attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(13, 0));
        obtainStyledAttributes.recycle();
        C11591sO r2 = this.A00;
        if (r2 != null) {
            r2.A01 = new C11391ru(((AnonymousClass1sT) r2).A01).A00();
            C11581sN r1 = ((AnonymousClass1sT) r2).A03;
            if (r1 != null) {
                r1.A0G(true);
            }
        }
    }

    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.A03 = false;
        }
        if (!this.A03) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9) {
                if (!onHoverEvent) {
                    this.A03 = true;
                }
                return true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.A03 = false;
            return true;
        }
        return true;
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.A04 = false;
        }
        if (!this.A04) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0) {
                if (!onTouchEvent) {
                    this.A04 = true;
                }
                return true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.A04 = false;
            return true;
        }
        return true;
    }

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            C003007j r0 = this.A02;
            if (r0 != null) {
                r0.A00();
            }
            super.setVisibility(i);
        }
    }
}
