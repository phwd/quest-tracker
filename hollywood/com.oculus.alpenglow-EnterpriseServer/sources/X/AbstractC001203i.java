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
import com.oculus.alpenglow.R;

/* renamed from: X.03i  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC001203i extends ViewGroup {
    public AnonymousClass0Mm A00;
    public ActionMenuView A01;
    public AnonymousClass0B0 A02;
    public boolean A03;
    public boolean A04;
    public int A05;
    public final Context A06;
    public final AnonymousClass0eP A07 = new AnonymousClass0eP(this);

    public final AnonymousClass0B0 A04(int i, long j) {
        AnonymousClass0B0 r0 = this.A02;
        if (r0 != null) {
            r0.A00();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            AnonymousClass0B0 A002 = AnonymousClass0Aw.A00(this);
            A002.A01(1.0f);
            A002.A03(j);
            AnonymousClass0eP r2 = this.A07;
            r2.A02.A02 = A002;
            r2.A00 = 0;
            A002.A04(r2);
            return A002;
        }
        AnonymousClass0B0 A003 = AnonymousClass0Aw.A00(this);
        A003.A01(0.0f);
        A003.A03(j);
        AnonymousClass0eP r1 = this.A07;
        r1.A02.A02 = A003;
        r1.A00 = i;
        A003.A04(r1);
        return A003;
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

    public AbstractC001203i(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        int i2;
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.A06 = context;
        } else {
            this.A06 = new ContextThemeWrapper(context, i2);
        }
    }

    public static final int A00(View view, int i, int i2, int i3, boolean z) {
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
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, AnonymousClass18N.A00, R.attr.actionBarStyle, 0);
        setContentHeight(obtainStyledAttributes.getLayoutDimension(13, 0));
        obtainStyledAttributes.recycle();
        AnonymousClass0Mm r2 = this.A00;
        if (r2 != null) {
            r2.A01 = new AnonymousClass03B(((AbstractC04310ee) r2).A01).A00();
            C04280eZ r1 = ((AbstractC04310ee) r2).A03;
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
            AnonymousClass0B0 r0 = this.A02;
            if (r0 != null) {
                r0.A00();
            }
            super.setVisibility(i);
        }
    }
}
