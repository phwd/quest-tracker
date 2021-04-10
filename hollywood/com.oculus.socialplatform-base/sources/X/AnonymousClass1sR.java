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

/* renamed from: X.1sR  reason: invalid class name */
public final class AnonymousClass1sR extends Spinner {
    public static final int[] A08 = {16843505};
    public int A00;
    public SpinnerAdapter A01;
    public AbstractC11811ss A02;
    public AnonymousClass1F8 A03;
    public final Context A04;
    public final Rect A05 = new Rect();
    public final C10991qO A06;
    public final boolean A07;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x004f, code lost:
        if (r1 == null) goto L_0x0054;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AnonymousClass1sR(@androidx.annotation.NonNull android.content.Context r11, @androidx.annotation.Nullable android.util.AttributeSet r12) {
        /*
        // Method dump skipped, instructions count: 201
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1sR.<init>(android.content.Context, android.util.AttributeSet):void");
    }

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
        Rect rect = this.A05;
        drawable.getPadding(rect);
        return i2 + rect.left + rect.right;
    }

    public int getDropDownHorizontalOffset() {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            return r0.A47();
        }
        return super.getDropDownHorizontalOffset();
    }

    public int getDropDownVerticalOffset() {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            return r0.A5H();
        }
        return super.getDropDownVerticalOffset();
    }

    public int getDropDownWidth() {
        if (this.A02 != null) {
            return this.A00;
        }
        return super.getDropDownWidth();
    }

    @VisibleForTesting
    public final AbstractC11811ss getInternalPopup() {
        return this.A02;
    }

    public Drawable getPopupBackground() {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            return r0.A3Q();
        }
        return super.getPopupBackground();
    }

    public Context getPopupContext() {
        return this.A04;
    }

    public CharSequence getPrompt() {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            return r0.A46();
        }
        return super.getPrompt();
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public ColorStateList getSupportBackgroundTintList() {
        C10991qO r0 = this.A06;
        if (r0 != null) {
            return r0.A01();
        }
        return null;
    }

    @Nullable
    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public PorterDuff.Mode getSupportBackgroundTintMode() {
        C10991qO r0 = this.A06;
        if (r0 != null) {
            return r0.A02();
        }
        return null;
    }

    public final void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        AppCompatSpinner$SavedState appCompatSpinner$SavedState = (AppCompatSpinner$SavedState) parcelable;
        super.onRestoreInstanceState(appCompatSpinner$SavedState.getSuperState());
        if (appCompatSpinner$SavedState.A00 && (viewTreeObserver = getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new AnonymousClass1tC(this));
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        AnonymousClass1F8 r0 = this.A03;
        if (r0 == null || !r0.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public final boolean performClick() {
        AbstractC11811ss r0 = this.A02;
        if (r0 == null) {
            return super.performClick();
        }
        if (r0.A6B()) {
            return true;
        }
        this.A02.AAP(getTextDirection(), getTextAlignment());
        return true;
    }

    public void setDropDownHorizontalOffset(int i) {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            r0.A9v(i);
            this.A02.A9u(i);
            return;
        }
        super.setDropDownHorizontalOffset(i);
    }

    public void setDropDownVerticalOffset(int i) {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            r0.AAG(i);
        } else {
            super.setDropDownVerticalOffset(i);
        }
    }

    public void setDropDownWidth(int i) {
        if (this.A02 != null) {
            this.A00 = i;
        } else {
            super.setDropDownWidth(i);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            r0.A9f(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPrompt(CharSequence charSequence) {
        AbstractC11811ss r0 = this.A02;
        if (r0 != null) {
            r0.AA6(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintList(@Nullable ColorStateList colorStateList) {
        C10991qO r0 = this.A06;
        if (r0 != null) {
            r0.A05(colorStateList);
        }
    }

    @RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
    public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode mode) {
        C10991qO r0 = this.A06;
        if (r0 != null) {
            r0.A06(mode);
        }
    }

    public final void drawableStateChanged() {
        super.drawableStateChanged();
        C10991qO r0 = this.A06;
        if (r0 != null) {
            r0.A03();
        }
    }

    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        AbstractC11811ss r0 = this.A02;
        if (r0 != null && r0.A6B()) {
            this.A02.dismiss();
        }
    }

    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.A02 != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
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
            X.1ss r0 = r3.A02
            if (r0 == 0) goto L_0x0014
            boolean r1 = r0.A6B()
            r0 = 1
            if (r1 != 0) goto L_0x0015
        L_0x0014:
            r0 = 0
        L_0x0015:
            r2.A00 = r0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass1sR.onSaveInstanceState():android.os.Parcelable");
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        C10991qO r1 = this.A06;
        if (r1 != null) {
            C10991qO.A00(r1, null);
            r1.A03();
        }
    }

    public void setBackgroundResource(@DrawableRes int i) {
        super.setBackgroundResource(i);
        C10991qO r0 = this.A06;
        if (r0 != null) {
            r0.A04(i);
        }
    }

    public void setPopupBackgroundResource(@DrawableRes int i) {
        setPopupBackgroundDrawable(AnonymousClass1pW.A00(getPopupContext(), i));
    }

    @Override // android.widget.AbsSpinner, android.widget.Spinner
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.A07) {
            this.A01 = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        AbstractC11811ss r2 = this.A02;
        if (r2 != null) {
            Context context = this.A04;
            if (context == null) {
                context = getContext();
            }
            r2.A9e(new AnonymousClass1tN(spinnerAdapter, context.getTheme()));
        }
    }
}
