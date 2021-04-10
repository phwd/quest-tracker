package X;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.AppCompatSpinner$SavedState;

/* renamed from: X.0e4  reason: invalid class name and case insensitive filesystem */
public final class C04110e4 extends Spinner {
    public static final int[] A08 = {16843505};
    public int A00;
    public AnonymousClass04S A01;
    public SpinnerAdapter A02;
    public AbstractView$OnAttachStateChangeListenerC003504l A03;
    public final Rect A04 = new Rect();
    public final Context A05;
    public final AnonymousClass04A A06;
    public final boolean A07;

    public final int A00(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i2 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, this);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i2;
        }
        Rect rect = this.A04;
        drawable.getPadding(rect);
        return i2 + rect.left + rect.right;
    }

    public int getDropDownHorizontalOffset() {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            return r0.A3g();
        }
        return super.getDropDownHorizontalOffset();
    }

    public int getDropDownVerticalOffset() {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            return r0.A4r();
        }
        return super.getDropDownVerticalOffset();
    }

    public int getDropDownWidth() {
        if (this.A01 != null) {
            return this.A00;
        }
        return super.getDropDownWidth();
    }

    @VisibleForTesting
    public final AnonymousClass04S getInternalPopup() {
        return this.A01;
    }

    public Drawable getPopupBackground() {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            return r0.A33();
        }
        return super.getPopupBackground();
    }

    public Context getPopupContext() {
        return this.A05;
    }

    public CharSequence getPrompt() {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            return r0.A3e();
        }
        return super.getPrompt();
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        AnonymousClass04A r0 = this.A06;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AnonymousClass04A r0 = this.A06;
        if (r0 != null) {
            return r0.A03();
        }
        return null;
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        AppCompatSpinner$SavedState appCompatSpinner$SavedState = (AppCompatSpinner$SavedState) parcelable;
        super.onRestoreInstanceState(appCompatSpinner$SavedState.getSuperState());
        if (appCompatSpinner$SavedState.A00 && (viewTreeObserver = getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new AnonymousClass04L(this));
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractView$OnAttachStateChangeListenerC003504l r0 = this.A03;
        if (r0 == null || !r0.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public final boolean performClick() {
        AnonymousClass04S r0 = this.A01;
        if (r0 == null) {
            return super.performClick();
        }
        if (r0.A5a()) {
            return true;
        }
        this.A01.A8Q(getTextDirection(), getTextAlignment());
        return true;
    }

    public void setDropDownHorizontalOffset(int i) {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            r0.A7v(i);
            this.A01.A7u(i);
            return;
        }
        super.setDropDownHorizontalOffset(i);
    }

    public void setDropDownVerticalOffset(int i) {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            r0.A8E(i);
        } else {
            super.setDropDownVerticalOffset(i);
        }
    }

    public void setDropDownWidth(int i) {
        if (this.A01 != null) {
            this.A00 = i;
        } else {
            super.setDropDownWidth(i);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            r0.A7l(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPrompt(CharSequence charSequence) {
        AnonymousClass04S r0 = this.A01;
        if (r0 != null) {
            r0.A84(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        AnonymousClass04A r0 = this.A06;
        if (r0 != null) {
            r0.A06(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        AnonymousClass04A r0 = this.A06;
        if (r0 != null) {
            r0.A07(mode);
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
        if (r1 == null) goto L_0x0055;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public C04110e4(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.Nullable android.util.AttributeSet r12) {
        /*
        // Method dump skipped, instructions count: 202
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04110e4.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AnonymousClass04A r0 = this.A06;
        if (r0 != null) {
            r0.A04();
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AnonymousClass04S r0 = this.A01;
        if (r0 != null && r0.A5a()) {
            this.A01.dismiss();
        }
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.A01 != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), A00(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0012, code lost:
        if (r1 == false) goto L_0x0014;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Parcelable onSaveInstanceState() {
        /*
            r3 = this;
            android.os.Parcelable r0 = super.onSaveInstanceState()
            androidx.appcompat.widget.AppCompatSpinner$SavedState r2 = new androidx.appcompat.widget.AppCompatSpinner$SavedState
            r2.<init>(r0)
            X.04S r0 = r3.A01
            if (r0 == 0) goto L_0x0014
            boolean r1 = r0.A5a()
            r0 = 1
            if (r1 != 0) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            r2.A00 = r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C04110e4.onSaveInstanceState():android.os.Parcelable");
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AnonymousClass04A r1 = this.A06;
        if (r1 != null) {
            AnonymousClass04A.A00(r1, null);
            r1.A04();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        AnonymousClass04A r0 = this.A06;
        if (r0 != null) {
            r0.A05(i);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int i) {
        setPopupBackgroundDrawable(AnonymousClass17E.A00(getPopupContext(), i));
    }

    @Override // android.widget.AbsSpinner, android.widget.Spinner
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.A07) {
            this.A02 = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        AnonymousClass04S r2 = this.A01;
        if (r2 != null) {
            Context context = this.A05;
            if (context == null) {
                context = getContext();
            }
            r2.A7j(new AnonymousClass04M(spinnerAdapter, context.getTheme()));
        }
    }
}
