package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.customview.view.AbsSavedState;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Toolbar extends ViewGroup {
    public ActionMenuView F;
    public TextView G;
    public TextView H;
    public ImageButton I;

    /* renamed from: J  reason: collision with root package name */
    public ImageView f9464J;
    public Drawable K;
    public CharSequence L;
    public ImageButton M;
    public View N;
    public Context O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int a0;
    public QN0 b0;
    public int c0;
    public int d0;
    public int e0;
    public CharSequence f0;
    public CharSequence g0;
    public ColorStateList h0;
    public ColorStateList i0;
    public boolean j0;
    public boolean k0;
    public final ArrayList l0;
    public final ArrayList m0;
    public final int[] n0;
    public AbstractC4790sj1 o0;
    public final C3937nj1 p0;
    public Hl1 q0;
    public C4676s2 r0;
    public C4450qj1 s0;
    public boolean t0;
    public final Runnable u0;

    public Toolbar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.f9070_resource_name_obfuscated_RES_2130969353);
    }

    public void A(Drawable drawable) {
        if (drawable != null) {
            if (this.f9464J == null) {
                this.f9464J = new AppCompatImageView(getContext(), null);
            }
            if (!u(this.f9464J)) {
                d(this.f9464J, true);
            }
        } else {
            ImageView imageView = this.f9464J;
            if (imageView != null && u(imageView)) {
                removeView(this.f9464J);
                this.m0.remove(this.f9464J);
            }
        }
        ImageView imageView2 = this.f9464J;
        if (imageView2 != null) {
            imageView2.setImageDrawable(drawable);
        }
    }

    public void B(int i) {
        C(i != 0 ? getContext().getText(i) : null);
    }

    public void C(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            h();
        }
        ImageButton imageButton = this.I;
        if (imageButton != null) {
            imageButton.setContentDescription(charSequence);
        }
    }

    public void D(Drawable drawable) {
        if (drawable != null) {
            h();
            if (!u(this.I)) {
                d(this.I, true);
            }
        } else {
            ImageButton imageButton = this.I;
            if (imageButton != null && u(imageButton)) {
                removeView(this.I);
                this.m0.remove(this.I);
            }
        }
        ImageButton imageButton2 = this.I;
        if (imageButton2 != null) {
            imageButton2.setImageDrawable(drawable);
        }
    }

    public void E(View.OnClickListener onClickListener) {
        h();
        this.I.setOnClickListener(onClickListener);
    }

    public void F(Drawable drawable) {
        f();
        ActionMenuView actionMenuView = this.F;
        actionMenuView.t();
        C4676s2 s2Var = actionMenuView.b0;
        C4164p2 p2Var = s2Var.N;
        if (p2Var != null) {
            p2Var.setImageDrawable(drawable);
            return;
        }
        s2Var.P = true;
        s2Var.O = drawable;
    }

    public void G(int i) {
        if (this.P != i) {
            this.P = i;
            if (i == 0) {
                this.O = getContext();
            } else {
                this.O = new ContextThemeWrapper(getContext(), i);
            }
        }
    }

    public void H(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.H == null) {
                Context context = getContext();
                N8 n8 = new N8(context);
                this.H = n8;
                n8.setSingleLine();
                this.H.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.R;
                if (i != 0) {
                    this.H.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.i0;
                if (colorStateList != null) {
                    this.H.setTextColor(colorStateList);
                }
            }
            if (!u(this.H)) {
                d(this.H, true);
            }
        } else {
            TextView textView = this.H;
            if (textView != null && u(textView)) {
                removeView(this.H);
                this.m0.remove(this.H);
            }
        }
        TextView textView2 = this.H;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.g0 = charSequence;
    }

    public void I(CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (this.G == null) {
                Context context = getContext();
                N8 n8 = new N8(context);
                this.G = n8;
                n8.setSingleLine();
                this.G.setEllipsize(TextUtils.TruncateAt.END);
                int i = this.Q;
                if (i != 0) {
                    this.G.setTextAppearance(context, i);
                }
                ColorStateList colorStateList = this.h0;
                if (colorStateList != null) {
                    this.G.setTextColor(colorStateList);
                }
            }
            if (!u(this.G)) {
                d(this.G, true);
            }
        } else {
            TextView textView = this.G;
            if (textView != null && u(textView)) {
                removeView(this.G);
                this.m0.remove(this.G);
            }
        }
        TextView textView2 = this.G;
        if (textView2 != null) {
            textView2.setText(charSequence);
        }
        this.f0 = charSequence;
    }

    public void J(Context context, int i) {
        this.Q = i;
        TextView textView = this.G;
        if (textView != null) {
            textView.setTextAppearance(context, i);
        }
    }

    public final boolean K(View view) {
        return (view == null || view.getParent() != this || view.getVisibility() == 8) ? false : true;
    }

    public boolean L() {
        ActionMenuView actionMenuView = this.F;
        if (actionMenuView != null) {
            C4676s2 s2Var = actionMenuView.b0;
            if (s2Var != null && s2Var.n()) {
                return true;
            }
        }
        return false;
    }

    public final void c(List list, int i) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        boolean z = getLayoutDirection() == 1;
        int childCount = getChildCount();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, getLayoutDirection());
        list.clear();
        if (z) {
            for (int i2 = childCount - 1; i2 >= 0; i2--) {
                View childAt = getChildAt(i2);
                C4620rj1 rj1 = (C4620rj1) childAt.getLayoutParams();
                if (rj1.b == 0 && K(childAt) && k(rj1.f9313a) == absoluteGravity) {
                    list.add(childAt);
                }
            }
            return;
        }
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt2 = getChildAt(i3);
            C4620rj1 rj12 = (C4620rj1) childAt2.getLayoutParams();
            if (rj12.b == 0 && K(childAt2) && k(rj12.f9313a) == absoluteGravity) {
                list.add(childAt2);
            }
        }
    }

    public boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return super.checkLayoutParams(layoutParams) && (layoutParams instanceof C4620rj1);
    }

    public final void d(View view, boolean z) {
        C4620rj1 rj1;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            rj1 = generateDefaultLayoutParams();
        } else if (!checkLayoutParams(layoutParams)) {
            rj1 = generateLayoutParams(layoutParams);
        } else {
            rj1 = (C4620rj1) layoutParams;
        }
        rj1.b = 1;
        if (!z || this.N == null) {
            addView(view, rj1);
            return;
        }
        view.setLayoutParams(rj1);
        this.m0.add(view);
    }

    public final void e() {
        if (this.b0 == null) {
            this.b0 = new QN0();
        }
    }

    public final void f() {
        g();
        ActionMenuView actionMenuView = this.F;
        if (actionMenuView.U == null) {
            C4616ri0 ri0 = (C4616ri0) actionMenuView.t();
            if (this.s0 == null) {
                this.s0 = new C4450qj1(this);
            }
            this.F.b0.V = true;
            ri0.b(this.s0, this.O);
        }
    }

    public final void g() {
        if (this.F == null) {
            ActionMenuView actionMenuView = new ActionMenuView(getContext(), null);
            this.F = actionMenuView;
            actionMenuView.w(this.P);
            ActionMenuView actionMenuView2 = this.F;
            actionMenuView2.i0 = this.p0;
            actionMenuView2.c0 = null;
            actionMenuView2.d0 = null;
            C4620rj1 i = generateDefaultLayoutParams();
            i.f9313a = 8388613 | (this.S & 112);
            this.F.setLayoutParams(i);
            d(this.F, false);
        }
    }

    public final void h() {
        if (this.I == null) {
            this.I = new C4353q8(getContext(), null, R.attr.f9060_resource_name_obfuscated_RES_2130969352);
            C4620rj1 i = generateDefaultLayoutParams();
            i.f9313a = 8388611 | (this.S & 112);
            this.I.setLayoutParams(i);
        }
    }

    /* renamed from: i */
    public C4620rj1 generateDefaultLayoutParams() {
        return new C4620rj1(-2, -2);
    }

    /* renamed from: j */
    public C4620rj1 generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof C4620rj1) {
            return new C4620rj1((C4620rj1) layoutParams);
        }
        if (layoutParams instanceof Z1) {
            return new C4620rj1((Z1) layoutParams);
        }
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new C4620rj1((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new C4620rj1(layoutParams);
    }

    public final int k(int i) {
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        int layoutDirection = getLayoutDirection();
        int absoluteGravity = Gravity.getAbsoluteGravity(i, layoutDirection) & 7;
        if (absoluteGravity == 1 || absoluteGravity == 3 || absoluteGravity == 5) {
            return absoluteGravity;
        }
        return layoutDirection == 1 ? 5 : 3;
    }

    public final int l(View view, int i) {
        C4620rj1 rj1 = (C4620rj1) view.getLayoutParams();
        int measuredHeight = view.getMeasuredHeight();
        int i2 = i > 0 ? (measuredHeight - i) / 2 : 0;
        int i3 = rj1.f9313a & 112;
        if (!(i3 == 16 || i3 == 48 || i3 == 80)) {
            i3 = this.e0 & 112;
        }
        if (i3 == 48) {
            return getPaddingTop() - i2;
        }
        if (i3 == 80) {
            return (((getHeight() - getPaddingBottom()) - measuredHeight) - ((ViewGroup.MarginLayoutParams) rj1).bottomMargin) - i2;
        }
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int height = getHeight();
        int i4 = (((height - paddingTop) - paddingBottom) - measuredHeight) / 2;
        int i5 = ((ViewGroup.MarginLayoutParams) rj1).topMargin;
        if (i4 < i5) {
            i4 = i5;
        } else {
            int i6 = (((height - paddingBottom) - measuredHeight) - i4) - paddingTop;
            int i7 = ((ViewGroup.MarginLayoutParams) rj1).bottomMargin;
            if (i6 < i7) {
                i4 = Math.max(0, i4 - (i7 - i6));
            }
        }
        return paddingTop + i4;
    }

    public int m() {
        C4616ri0 ri0;
        ActionMenuView actionMenuView = this.F;
        int i = 0;
        if ((actionMenuView == null || (ri0 = actionMenuView.U) == null || !ri0.hasVisibleItems()) ? false : true) {
            QN0 qn0 = this.b0;
            return Math.max(qn0 != null ? qn0.g ? qn0.f8757a : qn0.b : 0, Math.max(this.d0, 0));
        }
        QN0 qn02 = this.b0;
        if (qn02 != null) {
            i = qn02.g ? qn02.f8757a : qn02.b;
        }
        return i;
    }

    public int n() {
        int i = 0;
        if (q() != null) {
            QN0 qn0 = this.b0;
            return Math.max(qn0 != null ? qn0.g ? qn0.b : qn0.f8757a : 0, Math.max(this.c0, 0));
        }
        QN0 qn02 = this.b0;
        if (qn02 != null) {
            i = qn02.g ? qn02.b : qn02.f8757a;
        }
        return i;
    }

    public final int o(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.getMarginStart() + marginLayoutParams.getMarginEnd();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        removeCallbacks(this.u0);
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.k0 = false;
        }
        if (!this.k0) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.k0 = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.k0 = false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:110:0x02b7 A[LOOP:0: B:109:0x02b5->B:110:0x02b7, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x02d9 A[LOOP:1: B:112:0x02d7->B:113:0x02d9, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x02fd A[LOOP:2: B:115:0x02fb->B:116:0x02fd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x033e  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x034e A[LOOP:3: B:123:0x034c->B:124:0x034e, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0061  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0078  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0100  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x011b  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0142  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0145  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017f  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x023d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onLayout(boolean r20, int r21, int r22, int r23, int r24) {
        /*
        // Method dump skipped, instructions count: 867
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.Toolbar.onLayout(boolean, int, int, int, int):void");
    }

    public void onMeasure(int i, int i2) {
        char c;
        char c2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int[] iArr = this.n0;
        boolean z = true;
        int i10 = 0;
        if (AbstractC4826sv1.a(this)) {
            c2 = 1;
            c = 0;
        } else {
            c = 1;
            c2 = 0;
        }
        if (K(this.I)) {
            z(this.I, i, 0, i2, 0, this.T);
            i5 = o(this.I) + this.I.getMeasuredWidth();
            i4 = Math.max(0, r(this.I) + this.I.getMeasuredHeight());
            i3 = View.combineMeasuredStates(0, this.I.getMeasuredState());
        } else {
            i5 = 0;
            i4 = 0;
            i3 = 0;
        }
        if (K(this.M)) {
            z(this.M, i, 0, i2, 0, this.T);
            i5 = o(this.M) + this.M.getMeasuredWidth();
            i4 = Math.max(i4, r(this.M) + this.M.getMeasuredHeight());
            i3 = View.combineMeasuredStates(i3, this.M.getMeasuredState());
        }
        int n = n();
        int max = Math.max(n, i5) + 0;
        iArr[c2] = Math.max(0, n - i5);
        if (K(this.F)) {
            z(this.F, i, max, i2, 0, this.T);
            i6 = o(this.F) + this.F.getMeasuredWidth();
            i4 = Math.max(i4, r(this.F) + this.F.getMeasuredHeight());
            i3 = View.combineMeasuredStates(i3, this.F.getMeasuredState());
        } else {
            i6 = 0;
        }
        int m = m();
        int max2 = Math.max(m, i6) + max;
        iArr[c] = Math.max(0, m - i6);
        if (K(this.N)) {
            max2 += y(this.N, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, r(this.N) + this.N.getMeasuredHeight());
            i3 = View.combineMeasuredStates(i3, this.N.getMeasuredState());
        }
        if (K(this.f9464J)) {
            max2 += y(this.f9464J, i, max2, i2, 0, iArr);
            i4 = Math.max(i4, r(this.f9464J) + this.f9464J.getMeasuredHeight());
            i3 = View.combineMeasuredStates(i3, this.f9464J.getMeasuredState());
        }
        int childCount = getChildCount();
        for (int i11 = 0; i11 < childCount; i11++) {
            View childAt = getChildAt(i11);
            if (((C4620rj1) childAt.getLayoutParams()).b == 0 && K(childAt)) {
                max2 += y(childAt, i, max2, i2, 0, iArr);
                i4 = Math.max(i4, r(childAt) + childAt.getMeasuredHeight());
                i3 = View.combineMeasuredStates(i3, childAt.getMeasuredState());
            }
        }
        int i12 = this.W + this.a0;
        int i13 = this.U + this.V;
        if (K(this.G)) {
            y(this.G, i, max2 + i13, i2, i12, iArr);
            int o = o(this.G) + this.G.getMeasuredWidth();
            i7 = r(this.G) + this.G.getMeasuredHeight();
            i9 = View.combineMeasuredStates(i3, this.G.getMeasuredState());
            i8 = o;
        } else {
            i7 = 0;
            i9 = i3;
            i8 = 0;
        }
        if (K(this.H)) {
            i8 = Math.max(i8, y(this.H, i, max2 + i13, i2, i7 + i12, iArr));
            i7 = r(this.H) + this.H.getMeasuredHeight() + i7;
            i9 = View.combineMeasuredStates(i9, this.H.getMeasuredState());
        }
        int max3 = Math.max(i4, i7);
        int paddingRight = getPaddingRight() + getPaddingLeft();
        int paddingBottom = getPaddingBottom() + getPaddingTop() + max3;
        int resolveSizeAndState = View.resolveSizeAndState(Math.max(paddingRight + max2 + i8, getSuggestedMinimumWidth()), i, -16777216 & i9);
        int resolveSizeAndState2 = View.resolveSizeAndState(Math.max(paddingBottom, getSuggestedMinimumHeight()), i2, i9 << 16);
        if (this.t0) {
            int childCount2 = getChildCount();
            int i14 = 0;
            while (true) {
                if (i14 >= childCount2) {
                    break;
                }
                View childAt2 = getChildAt(i14);
                if (K(childAt2) && childAt2.getMeasuredWidth() > 0 && childAt2.getMeasuredHeight() > 0) {
                    break;
                }
                i14++;
            }
        }
        z = false;
        if (!z) {
            i10 = resolveSizeAndState2;
        }
        setMeasuredDimension(resolveSizeAndState, i10);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        MenuItem findItem;
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.G);
        ActionMenuView actionMenuView = this.F;
        C4616ri0 ri0 = actionMenuView != null ? actionMenuView.U : null;
        int i = savedState.H;
        if (!(i == 0 || this.s0 == null || ri0 == null || (findItem = ri0.findItem(i)) == null)) {
            findItem.expandActionView();
        }
        if (savedState.I) {
            removeCallbacks(this.u0);
            post(this.u0);
        }
    }

    public void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        e();
        QN0 qn0 = this.b0;
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        if (z != qn0.g) {
            qn0.g = z;
            if (!qn0.h) {
                qn0.f8757a = qn0.e;
                qn0.b = qn0.f;
            } else if (z) {
                int i2 = qn0.d;
                if (i2 == Integer.MIN_VALUE) {
                    i2 = qn0.e;
                }
                qn0.f8757a = i2;
                int i3 = qn0.c;
                if (i3 == Integer.MIN_VALUE) {
                    i3 = qn0.f;
                }
                qn0.b = i3;
            } else {
                int i4 = qn0.c;
                if (i4 == Integer.MIN_VALUE) {
                    i4 = qn0.e;
                }
                qn0.f8757a = i4;
                int i5 = qn0.d;
                if (i5 == Integer.MIN_VALUE) {
                    i5 = qn0.f;
                }
                qn0.b = i5;
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        C0817Ni0 ni0;
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        C4450qj1 qj1 = this.s0;
        if (!(qj1 == null || (ni0 = qj1.G) == null)) {
            savedState.H = ni0.f8568a;
        }
        savedState.I = v();
        return savedState;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.j0 = false;
        }
        if (!this.j0) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.j0 = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.j0 = false;
        }
        return true;
    }

    public Menu p() {
        f();
        return this.F.t();
    }

    public Drawable q() {
        ImageButton imageButton = this.I;
        if (imageButton != null) {
            return imageButton.getDrawable();
        }
        return null;
    }

    public final int r(View view) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        return marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
    }

    public boolean s() {
        ActionMenuView actionMenuView = this.F;
        if (actionMenuView != null) {
            C4676s2 s2Var = actionMenuView.b0;
            if (s2Var != null && s2Var.f()) {
                return true;
            }
        }
        return false;
    }

    public void t(int i) {
        new X31(getContext()).inflate(i, p());
    }

    public final boolean u(View view) {
        return view.getParent() == this || this.m0.contains(view);
    }

    public boolean v() {
        ActionMenuView actionMenuView = this.F;
        if (actionMenuView != null) {
            C4676s2 s2Var = actionMenuView.b0;
            if (s2Var != null && s2Var.m()) {
                return true;
            }
        }
        return false;
    }

    public final int w(View view, int i, int[] iArr, int i2) {
        C4620rj1 rj1 = (C4620rj1) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) rj1).leftMargin - iArr[0];
        int max = Math.max(0, i3) + i;
        iArr[0] = Math.max(0, -i3);
        int l = l(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max, l, max + measuredWidth, view.getMeasuredHeight() + l);
        return measuredWidth + ((ViewGroup.MarginLayoutParams) rj1).rightMargin + max;
    }

    public final int x(View view, int i, int[] iArr, int i2) {
        C4620rj1 rj1 = (C4620rj1) view.getLayoutParams();
        int i3 = ((ViewGroup.MarginLayoutParams) rj1).rightMargin - iArr[1];
        int max = i - Math.max(0, i3);
        iArr[1] = Math.max(0, -i3);
        int l = l(view, i2);
        int measuredWidth = view.getMeasuredWidth();
        view.layout(max - measuredWidth, l, max, view.getMeasuredHeight() + l);
        return max - (measuredWidth + ((ViewGroup.MarginLayoutParams) rj1).leftMargin);
    }

    public final int y(View view, int i, int i2, int i3, int i4, int[] iArr) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int i5 = marginLayoutParams.leftMargin - iArr[0];
        int i6 = marginLayoutParams.rightMargin - iArr[1];
        int max = Math.max(0, i6) + Math.max(0, i5);
        iArr[0] = Math.max(0, -i5);
        iArr[1] = Math.max(0, -i6);
        view.measure(ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + max + i2, marginLayoutParams.width), ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height));
        return view.getMeasuredWidth() + max;
    }

    public final void z(View view, int i, int i2, int i3, int i4, int i5) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i, getPaddingRight() + getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i2, marginLayoutParams.width);
        int childMeasureSpec2 = ViewGroup.getChildMeasureSpec(i3, getPaddingBottom() + getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i4, marginLayoutParams.height);
        int mode = View.MeasureSpec.getMode(childMeasureSpec2);
        if (mode != 1073741824 && i5 >= 0) {
            if (mode != 0) {
                i5 = Math.min(View.MeasureSpec.getSize(childMeasureSpec2), i5);
            }
            childMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(i5, 1073741824);
        }
        view.measure(childMeasureSpec, childMeasureSpec2);
    }

    public Toolbar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e0 = 8388627;
        this.l0 = new ArrayList();
        this.m0 = new ArrayList();
        this.n0 = new int[2];
        this.p0 = new C3937nj1(this);
        this.u0 = new RunnableC4108oj1(this);
        Context context2 = getContext();
        int[] iArr = FJ0.P0;
        C0514Ii1 q = C0514Ii1.q(context2, attributeSet, iArr, i, 0);
        AbstractC1920bu1.m(this, context, iArr, attributeSet, q.b, i, 0);
        this.Q = q.l(28, 0);
        this.R = q.l(19, 0);
        this.e0 = q.b.getInteger(0, this.e0);
        this.S = q.b.getInteger(2, 48);
        int e = q.e(22, 0);
        e = q.o(27) ? q.e(27, e) : e;
        this.a0 = e;
        this.W = e;
        this.V = e;
        this.U = e;
        int e2 = q.e(25, -1);
        if (e2 >= 0) {
            this.U = e2;
        }
        int e3 = q.e(24, -1);
        if (e3 >= 0) {
            this.V = e3;
        }
        int e4 = q.e(26, -1);
        if (e4 >= 0) {
            this.W = e4;
        }
        int e5 = q.e(23, -1);
        if (e5 >= 0) {
            this.a0 = e5;
        }
        this.T = q.f(13, -1);
        int e6 = q.e(9, Integer.MIN_VALUE);
        int e7 = q.e(5, Integer.MIN_VALUE);
        int f = q.f(7, 0);
        int f2 = q.f(8, 0);
        e();
        QN0 qn0 = this.b0;
        qn0.h = false;
        if (f != Integer.MIN_VALUE) {
            qn0.e = f;
            qn0.f8757a = f;
        }
        if (f2 != Integer.MIN_VALUE) {
            qn0.f = f2;
            qn0.b = f2;
        }
        if (!(e6 == Integer.MIN_VALUE && e7 == Integer.MIN_VALUE)) {
            qn0.a(e6, e7);
        }
        this.c0 = q.e(10, Integer.MIN_VALUE);
        this.d0 = q.e(6, Integer.MIN_VALUE);
        this.K = q.g(4);
        this.L = q.n(3);
        CharSequence n = q.n(21);
        if (!TextUtils.isEmpty(n)) {
            I(n);
        }
        CharSequence n2 = q.n(18);
        if (!TextUtils.isEmpty(n2)) {
            H(n2);
        }
        this.O = getContext();
        G(q.l(17, 0));
        Drawable g = q.g(16);
        if (g != null) {
            D(g);
        }
        CharSequence n3 = q.n(15);
        if (!TextUtils.isEmpty(n3)) {
            C(n3);
        }
        Drawable g2 = q.g(11);
        if (g2 != null) {
            A(g2);
        }
        CharSequence n4 = q.n(12);
        if (!TextUtils.isEmpty(n4)) {
            if (!TextUtils.isEmpty(n4) && this.f9464J == null) {
                this.f9464J = new AppCompatImageView(getContext(), null);
            }
            ImageView imageView = this.f9464J;
            if (imageView != null) {
                imageView.setContentDescription(n4);
            }
        }
        if (q.o(29)) {
            ColorStateList c = q.c(29);
            this.h0 = c;
            TextView textView = this.G;
            if (textView != null) {
                textView.setTextColor(c);
            }
        }
        if (q.o(20)) {
            ColorStateList c2 = q.c(20);
            this.i0 = c2;
            TextView textView2 = this.H;
            if (textView2 != null) {
                textView2.setTextColor(c2);
            }
        }
        if (q.o(14)) {
            t(q.l(14, 0));
        }
        q.b.recycle();
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new C4620rj1(getContext(), attributeSet);
    }

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class SavedState extends AbsSavedState {
        public static final Parcelable.Creator CREATOR = new C4960tj1();
        public int H;
        public boolean I;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.H = parcel.readInt();
            this.I = parcel.readInt() != 0;
        }

        @Override // androidx.customview.view.AbsSavedState
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.G, i);
            parcel.writeInt(this.H);
            parcel.writeInt(this.I ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }
}
