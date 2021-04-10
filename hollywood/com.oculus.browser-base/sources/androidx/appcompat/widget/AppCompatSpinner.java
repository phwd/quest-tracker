package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppCompatSpinner extends Spinner {
    public static final int[] F = {16843505};
    public final K7 G;
    public final Context H;
    public AbstractView$OnTouchListenerC2013cS I;

    /* renamed from: J  reason: collision with root package name */
    public SpinnerAdapter f9460J;
    public final boolean K;
    public J8 L;
    public int M;
    public final Rect N = new Rect();

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator CREATOR = new I8();
        public boolean F;

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeByte(this.F ? (byte) 1 : 0);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.F = parcel.readByte() != 0;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0059, code lost:
        if (r5 != null) goto L_0x005b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AppCompatSpinner(android.content.Context r11, android.util.AttributeSet r12) {
        /*
        // Method dump skipped, instructions count: 216
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatSpinner.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public int a(SpinnerAdapter spinnerAdapter, Drawable drawable) {
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
        drawable.getPadding(this.N);
        Rect rect = this.N;
        return i2 + rect.left + rect.right;
    }

    public void b() {
        this.L.l(getTextDirection(), getTextAlignment());
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        K7 k7 = this.G;
        if (k7 != null) {
            k7.a();
        }
    }

    public int getDropDownHorizontalOffset() {
        J8 j8 = this.L;
        if (j8 != null) {
            return j8.c();
        }
        return super.getDropDownHorizontalOffset();
    }

    public int getDropDownVerticalOffset() {
        J8 j8 = this.L;
        if (j8 != null) {
            return j8.m();
        }
        return super.getDropDownVerticalOffset();
    }

    public int getDropDownWidth() {
        if (this.L != null) {
            return this.M;
        }
        return super.getDropDownWidth();
    }

    public Drawable getPopupBackground() {
        J8 j8 = this.L;
        if (j8 != null) {
            return j8.e();
        }
        return super.getPopupBackground();
    }

    public Context getPopupContext() {
        return this.H;
    }

    public CharSequence getPrompt() {
        J8 j8 = this.L;
        return j8 != null ? j8.o() : super.getPrompt();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        J8 j8 = this.L;
        if (j8 != null && j8.b()) {
            this.L.dismiss();
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.L != null && View.MeasureSpec.getMode(i) == Integer.MIN_VALUE) {
            setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), a(getAdapter(), getBackground())), View.MeasureSpec.getSize(i)), getMeasuredHeight());
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.F && (viewTreeObserver = getViewTreeObserver()) != null) {
            viewTreeObserver.addOnGlobalLayoutListener(new B8(this));
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        J8 j8 = this.L;
        savedState.F = j8 != null && j8.b();
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        AbstractView$OnTouchListenerC2013cS cSVar = this.I;
        if (cSVar == null || !cSVar.onTouch(this, motionEvent)) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    public boolean performClick() {
        J8 j8 = this.L;
        if (j8 == null) {
            return super.performClick();
        }
        if (j8.b()) {
            return true;
        }
        b();
        return true;
    }

    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        K7 k7 = this.G;
        if (k7 != null) {
            k7.e();
        }
    }

    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        K7 k7 = this.G;
        if (k7 != null) {
            k7.f(i);
        }
    }

    public void setDropDownHorizontalOffset(int i) {
        J8 j8 = this.L;
        if (j8 != null) {
            j8.j(i);
            this.L.k(i);
            return;
        }
        super.setDropDownHorizontalOffset(i);
    }

    public void setDropDownVerticalOffset(int i) {
        J8 j8 = this.L;
        if (j8 != null) {
            j8.i(i);
        } else {
            super.setDropDownVerticalOffset(i);
        }
    }

    public void setDropDownWidth(int i) {
        if (this.L != null) {
            this.M = i;
        } else {
            super.setDropDownWidth(i);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        J8 j8 = this.L;
        if (j8 != null) {
            j8.h(drawable);
        } else {
            super.setPopupBackgroundDrawable(drawable);
        }
    }

    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(AbstractC5544x8.a(getPopupContext(), i));
    }

    public void setPrompt(CharSequence charSequence) {
        J8 j8 = this.L;
        if (j8 != null) {
            j8.g(charSequence);
        } else {
            super.setPrompt(charSequence);
        }
    }

    @Override // android.widget.AbsSpinner, android.widget.Spinner
    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        if (!this.K) {
            this.f9460J = spinnerAdapter;
            return;
        }
        super.setAdapter(spinnerAdapter);
        if (this.L != null) {
            Context context = this.H;
            if (context == null) {
                context = getContext();
            }
            this.L.p(new D8(spinnerAdapter, context.getTheme()));
        }
    }
}
