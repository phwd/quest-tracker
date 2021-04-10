package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionBarContextView extends ViewGroup {
    public final C1765b F = new C1765b(this);
    public final Context G;
    public ActionMenuView H;
    public C4676s2 I;

    /* renamed from: J  reason: collision with root package name */
    public int f9458J;
    public Zu1 K;
    public boolean L;
    public boolean M;
    public CharSequence N;
    public CharSequence O;
    public View P;
    public View Q;
    public LinearLayout R;
    public TextView S;
    public TextView T;
    public int U;
    public int V;
    public boolean W;
    public int a0;

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f1570_resource_name_obfuscated_RES_2130968603);
        Drawable drawable;
        int resourceId;
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.f1320_resource_name_obfuscated_RES_2130968578, typedValue, true) || typedValue.resourceId == 0) {
            this.G = context;
        } else {
            this.G = new ContextThemeWrapper(context, typedValue.resourceId);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, FJ0.d, R.attr.f1570_resource_name_obfuscated_RES_2130968603, 0);
        if (!obtainStyledAttributes.hasValue(0) || (resourceId = obtainStyledAttributes.getResourceId(0, 0)) == 0) {
            drawable = obtainStyledAttributes.getDrawable(0);
        } else {
            drawable = AbstractC5544x8.a(context, resourceId);
        }
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        setBackground(drawable);
        this.U = obtainStyledAttributes.getResourceId(5, 0);
        this.V = obtainStyledAttributes.getResourceId(4, 0);
        this.f9458J = obtainStyledAttributes.getLayoutDimension(3, 0);
        this.a0 = obtainStyledAttributes.getResourceId(2, R.layout.f36320_resource_name_obfuscated_RES_2131623941);
        obtainStyledAttributes.recycle();
    }

    public void c(AbstractC5696y2 y2Var) {
        View view = this.P;
        if (view == null) {
            View inflate = LayoutInflater.from(getContext()).inflate(this.a0, (ViewGroup) this, false);
            this.P = inflate;
            addView(inflate);
        } else if (view.getParent() == null) {
            addView(this.P);
        }
        this.P.findViewById(R.id.action_mode_close_button).setOnClickListener(new View$OnClickListenerC2114d2(this, y2Var));
        C4616ri0 ri0 = (C4616ri0) y2Var.e();
        C4676s2 s2Var = this.I;
        if (s2Var != null) {
            s2Var.b();
        }
        C4676s2 s2Var2 = new C4676s2(getContext());
        this.I = s2Var2;
        s2Var2.Q = true;
        s2Var2.R = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        ri0.b(this.I, this.G);
        C4676s2 s2Var3 = this.I;
        AbstractC2398ej0 ej0 = s2Var3.M;
        if (ej0 == null) {
            AbstractC2398ej0 ej02 = (AbstractC2398ej0) s2Var3.I.inflate(s2Var3.K, (ViewGroup) this, false);
            s2Var3.M = ej02;
            ej02.c(s2Var3.H);
            s2Var3.h(true);
        }
        AbstractC2398ej0 ej03 = s2Var3.M;
        if (ej0 != ej03) {
            ActionMenuView actionMenuView = (ActionMenuView) ej03;
            actionMenuView.b0 = s2Var3;
            s2Var3.M = actionMenuView;
            actionMenuView.U = s2Var3.H;
        }
        ActionMenuView actionMenuView2 = (ActionMenuView) ej03;
        this.H = actionMenuView2;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        actionMenuView2.setBackground(null);
        addView(this.H, layoutParams);
    }

    public final void d() {
        if (this.R == null) {
            LayoutInflater.from(getContext()).inflate(R.layout.f36270_resource_name_obfuscated_RES_2131623936, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.R = linearLayout;
            this.S = (TextView) linearLayout.findViewById(R.id.action_bar_title);
            this.T = (TextView) this.R.findViewById(R.id.action_bar_subtitle);
            if (this.U != 0) {
                this.S.setTextAppearance(getContext(), this.U);
            }
            if (this.V != 0) {
                this.T.setTextAppearance(getContext(), this.V);
            }
        }
        this.S.setText(this.N);
        this.T.setText(this.O);
        boolean z = !TextUtils.isEmpty(this.N);
        boolean z2 = !TextUtils.isEmpty(this.O);
        int i = 0;
        this.T.setVisibility(z2 ? 0 : 8);
        LinearLayout linearLayout2 = this.R;
        if (!z && !z2) {
            i = 8;
        }
        linearLayout2.setVisibility(i);
        if (this.R.getParent() == null) {
            addView(this.R);
        }
    }

    public void e() {
        removeAllViews();
        this.Q = null;
        this.H = null;
    }

    public int f(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    public int g(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public void h(View view) {
        LinearLayout linearLayout;
        View view2 = this.Q;
        if (view2 != null) {
            removeView(view2);
        }
        this.Q = view;
        if (!(view == null || (linearLayout = this.R) == null)) {
            removeView(linearLayout);
            this.R = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public Zu1 i(int i, long j) {
        Zu1 zu1 = this.K;
        if (zu1 != null) {
            zu1.b();
        }
        if (i == 0) {
            if (getVisibility() != 0) {
                setAlpha(0.0f);
            }
            Zu1 a2 = AbstractC1920bu1.a(this);
            a2.a(1.0f);
            a2.c(j);
            C1765b bVar = this.F;
            bVar.c.K = a2;
            bVar.b = i;
            View view = (View) a2.f9382a.get();
            if (view == null) {
                return a2;
            }
            a2.e(view, bVar);
            return a2;
        }
        Zu1 a3 = AbstractC1920bu1.a(this);
        a3.a(0.0f);
        a3.c(j);
        C1765b bVar2 = this.F;
        bVar2.c.K = a3;
        bVar2.b = i;
        View view2 = (View) a3.f9382a.get();
        if (view2 != null) {
            a3.e(view2, bVar2);
        }
        return a3;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(null, FJ0.f8010a, R.attr.f1350_resource_name_obfuscated_RES_2130968581, 0);
        this.f9458J = obtainStyledAttributes.getLayoutDimension(13, 0);
        obtainStyledAttributes.recycle();
        C4676s2 s2Var = this.I;
        if (s2Var != null) {
            Configuration configuration2 = s2Var.G.getResources().getConfiguration();
            int i = configuration2.screenWidthDp;
            int i2 = configuration2.screenHeightDp;
            s2Var.U = (configuration2.smallestScreenWidthDp > 600 || i > 600 || (i > 960 && i2 > 720) || (i > 720 && i2 > 960)) ? 5 : (i >= 500 || (i > 640 && i2 > 480) || (i > 480 && i2 > 640)) ? 4 : i >= 360 ? 3 : 2;
            C4616ri0 ri0 = s2Var.H;
            if (ri0 != null) {
                ri0.p(true);
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C4676s2 s2Var = this.I;
        if (s2Var != null) {
            s2Var.f();
            this.I.l();
        }
    }

    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.M = false;
        }
        if (!this.M) {
            boolean onHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !onHoverEvent) {
                this.M = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.M = false;
        }
        return true;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            accessibilityEvent.setSource(this);
            accessibilityEvent.setClassName(getClass().getName());
            accessibilityEvent.setPackageName(getContext().getPackageName());
            accessibilityEvent.setContentDescription(this.N);
            return;
        }
        super.onInitializeAccessibilityEvent(accessibilityEvent);
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean a2 = AbstractC4826sv1.a(this);
        int paddingRight = a2 ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.P;
        if (!(view == null || view.getVisibility() == 8)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.P.getLayoutParams();
            int i5 = a2 ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = a2 ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i7 = a2 ? paddingRight - i5 : paddingRight + i5;
            int g = i7 + g(this.P, i7, paddingTop, paddingTop2, a2);
            paddingRight = a2 ? g - i6 : g + i6;
        }
        int i8 = paddingRight;
        LinearLayout linearLayout = this.R;
        if (!(linearLayout == null || this.Q != null || linearLayout.getVisibility() == 8)) {
            i8 += g(this.R, i8, paddingTop, paddingTop2, a2);
        }
        View view2 = this.Q;
        if (view2 != null) {
            g(view2, i8, paddingTop, paddingTop2, a2);
        }
        int paddingLeft = a2 ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.H;
        if (actionMenuView != null) {
            g(actionMenuView, paddingLeft, paddingTop, paddingTop2, !a2);
        }
    }

    public void onMeasure(int i, int i2) {
        int i3 = 1073741824;
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_width=\"match_parent\" (or fill_parent)");
        } else if (View.MeasureSpec.getMode(i2) != 0) {
            int size = View.MeasureSpec.getSize(i);
            int i4 = this.f9458J;
            if (i4 <= 0) {
                i4 = View.MeasureSpec.getSize(i2);
            }
            int paddingBottom = getPaddingBottom() + getPaddingTop();
            int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
            int i5 = i4 - paddingBottom;
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
            View view = this.P;
            if (view != null) {
                int f = f(view, paddingLeft, makeMeasureSpec, 0);
                ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.P.getLayoutParams();
                paddingLeft = f - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            }
            ActionMenuView actionMenuView = this.H;
            if (actionMenuView != null && actionMenuView.getParent() == this) {
                paddingLeft = f(this.H, paddingLeft, makeMeasureSpec, 0);
            }
            LinearLayout linearLayout = this.R;
            if (linearLayout != null && this.Q == null) {
                if (this.W) {
                    this.R.measure(View.MeasureSpec.makeMeasureSpec(0, 0), makeMeasureSpec);
                    int measuredWidth = this.R.getMeasuredWidth();
                    boolean z = measuredWidth <= paddingLeft;
                    if (z) {
                        paddingLeft -= measuredWidth;
                    }
                    this.R.setVisibility(z ? 0 : 8);
                } else {
                    paddingLeft = f(linearLayout, paddingLeft, makeMeasureSpec, 0);
                }
            }
            View view2 = this.Q;
            if (view2 != null) {
                ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                int i6 = layoutParams.width;
                int i7 = i6 != -2 ? 1073741824 : Integer.MIN_VALUE;
                if (i6 >= 0) {
                    paddingLeft = Math.min(i6, paddingLeft);
                }
                int i8 = layoutParams.height;
                if (i8 == -2) {
                    i3 = Integer.MIN_VALUE;
                }
                if (i8 >= 0) {
                    i5 = Math.min(i8, i5);
                }
                this.Q.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i7), View.MeasureSpec.makeMeasureSpec(i5, i3));
            }
            if (this.f9458J <= 0) {
                int childCount = getChildCount();
                int i9 = 0;
                for (int i10 = 0; i10 < childCount; i10++) {
                    int measuredHeight = getChildAt(i10).getMeasuredHeight() + paddingBottom;
                    if (measuredHeight > i9) {
                        i9 = measuredHeight;
                    }
                }
                setMeasuredDimension(size, i9);
                return;
            }
            setMeasuredDimension(size, i4);
        } else {
            throw new IllegalStateException(getClass().getSimpleName() + " can only be used with android:layout_height=\"wrap_content\"");
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.L = false;
        }
        if (!this.L) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !onTouchEvent) {
                this.L = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.L = false;
        }
        return true;
    }

    public void setVisibility(int i) {
        if (i != getVisibility()) {
            Zu1 zu1 = this.K;
            if (zu1 != null) {
                zu1.b();
            }
            super.setVisibility(i);
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
