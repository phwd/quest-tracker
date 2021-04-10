package org.chromium.chrome.browser.customtabs.features.toolbar;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.ui.base.Clipboard;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CustomTabToolbar extends Wj1 implements View.OnLongClickListener {
    public static final Object U = new Object();
    public View V;
    public View W;
    public TextView a0;
    public View b0;
    public View c0;
    public TextView d0;
    public ImageView e0;
    public ImageButton f0;
    public LinearLayout g0;
    public ImageButton h0;
    public boolean i0;
    public final ColorStateList j0;
    public final ColorStateList k0;
    public ValueAnimator l0;
    public boolean m0;
    public ZB n0;
    public int o0 = 0;
    public String p0;
    public WB q0;
    public LocationBarModel r0;
    public Runnable s0 = new QB(this);

    /* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
    public class InterceptTouchLayout extends FrameLayout {
        public GestureDetector F = new GestureDetector(getContext(), new XB(this), ThreadUtils.b());

        public InterceptTouchLayout(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            return true;
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            this.F.onTouchEvent(motionEvent);
            return super.onTouchEvent(motionEvent);
        }
    }

    public CustomTabToolbar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j0 = AbstractC1300Vg1.b(context, false);
        this.k0 = AbstractC1300Vg1.b(context, true);
    }

    public static Tab Y(CustomTabToolbar customTabToolbar) {
        return customTabToolbar.f9169J.d();
    }

    @Override // defpackage.Wj1
    public void H(View.OnClickListener onClickListener) {
        this.h0.setOnClickListener(onClickListener);
    }

    /* renamed from: Z */
    public ColorDrawable getBackground() {
        return (ColorDrawable) super.getBackground();
    }

    public final int a0() {
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) == this.V) {
                return i;
            }
        }
        return -1;
    }

    public final void b0(ImageButton imageButton) {
        Drawable drawable = imageButton.getDrawable();
        if (drawable instanceof C0636Ki1) {
            ((C0636Ki1) drawable).c(this.i0 ? this.j0 : this.k0);
        }
    }

    @Override // defpackage.Wj1
    public AbstractC3225ja0 h() {
        return this.q0;
    }

    @Override // defpackage.Wj1
    public int k() {
        return 0;
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.r0.A();
        this.r0.B();
        this.r0.y();
    }

    @Override // defpackage.Wj1
    public void onFinishInflate() {
        super.onFinishInflate();
        int a2 = AbstractC2934hr.a(getResources(), false);
        setBackground(new ColorDrawable(a2));
        this.i0 = !AbstractC1270Uv.g(a2);
        TextView textView = (TextView) findViewById(R.id.url_bar);
        this.a0 = textView;
        textView.setHint("");
        this.a0.setEnabled(false);
        this.b0 = findViewById(R.id.url_bar_lite_status);
        this.c0 = findViewById(R.id.url_bar_lite_status_separator);
        this.d0 = (TextView) findViewById(R.id.title_bar);
        this.V = findViewById(R.id.location_bar_frame_layout);
        View findViewById = findViewById(R.id.title_url_container);
        this.W = findViewById;
        findViewById.setOnLongClickListener(this);
        this.e0 = (ImageView) findViewById(R.id.incognito_cct_logo_image_view);
        this.f0 = (ImageButton) findViewById(R.id.security_button);
        this.g0 = (LinearLayout) findViewById(R.id.action_buttons);
        ImageButton imageButton = (ImageButton) findViewById(R.id.close_button);
        this.h0 = imageButton;
        imageButton.setOnLongClickListener(this);
        this.n0 = new ZB(this.f0, this.W, R.dimen.f20430_resource_name_obfuscated_RES_2131165662);
    }

    public boolean onLongClick(View view) {
        Tab d;
        if (view == this.h0 || view.getParent() == this.g0) {
            return C1184Ti1.c(getContext(), view, view.getContentDescription());
        }
        if (view != this.W || (d = this.f9169J.d()) == null) {
            return false;
        }
        Clipboard.getInstance().b(d.n());
        return true;
    }

    @Override // defpackage.Wj1
    public void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5 = 0;
        this.e0.setVisibility(this.f9169J.a() ? 0 : 8);
        int dimensionPixelSize = this.h0.getVisibility() == 8 ? getResources().getDimensionPixelSize(R.dimen.f17900_resource_name_obfuscated_RES_2131165409) : 0;
        int a02 = a0();
        for (int i6 = 0; i6 < a02; i6++) {
            View childAt = getChildAt(i6);
            if (childAt.getVisibility() != 8) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                if (layoutParams.getMarginStart() != dimensionPixelSize) {
                    layoutParams.setMarginStart(dimensionPixelSize);
                    childAt.setLayoutParams(layoutParams);
                }
                FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) childAt.getLayoutParams();
                int i7 = layoutParams2.width;
                if (i7 == -2) {
                    i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), Integer.MIN_VALUE);
                } else if (i7 == -1) {
                    i3 = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
                } else {
                    i3 = View.MeasureSpec.makeMeasureSpec(i7, 1073741824);
                }
                int i8 = layoutParams2.height;
                if (i8 == -2) {
                    i4 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), Integer.MIN_VALUE);
                } else if (i8 == -1) {
                    i4 = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
                } else {
                    i4 = View.MeasureSpec.makeMeasureSpec(i8, 1073741824);
                }
                childAt.measure(i3, i4);
                dimensionPixelSize = childAt.getMeasuredWidth() + dimensionPixelSize;
            }
        }
        View childAt2 = getChildAt(a0());
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) childAt2.getLayoutParams();
        if (layoutParams3.getMarginStart() != dimensionPixelSize) {
            layoutParams3.setMarginStart(dimensionPixelSize);
            childAt2.setLayoutParams(layoutParams3);
        }
        int a03 = a0();
        while (true) {
            a03++;
            if (a03 >= getChildCount()) {
                break;
            }
            View childAt3 = getChildAt(a03);
            if (childAt3.getVisibility() != 8) {
                i5 = childAt3.getMeasuredWidth() + i5;
            }
        }
        FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) this.V.getLayoutParams();
        if (layoutParams4.getMarginEnd() != i5) {
            layoutParams4.setMarginEnd(i5);
            this.V.setLayoutParams(layoutParams4);
        }
        int measuredWidth = this.f0.getMeasuredWidth();
        FrameLayout.LayoutParams layoutParams5 = (FrameLayout.LayoutParams) this.W.getLayoutParams();
        if (this.f0.getVisibility() == 8) {
            measuredWidth -= this.f0.getMeasuredWidth();
        }
        layoutParams5.leftMargin = measuredWidth;
        this.W.setLayoutParams(layoutParams5);
        super.onMeasure(i, i2);
    }

    @Override // defpackage.Wj1
    public void v() {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.g0.getLayoutParams();
        marginLayoutParams.setMarginEnd(0);
        this.g0.setLayoutParams(marginLayoutParams);
    }

    @Override // defpackage.Wj1
    public void w() {
        super.w();
        WB wb = this.q0;
        wb.I.f0.setOnClickListener(new UB(wb));
    }

    @Override // defpackage.Wj1
    public void x() {
        this.r0.A();
        if (this.o0 == 1) {
            if (TextUtils.isEmpty(this.p0)) {
                this.p0 = this.f9169J.d().s();
            } else if (!this.p0.equals(this.f9169J.d().s())) {
                int i = this.o0;
                if (i != 0 && i == 1) {
                    this.o0 = 2;
                    this.d0.setVisibility(0);
                    this.a0.setTextSize(0, getResources().getDimension(R.dimen.f17940_resource_name_obfuscated_RES_2131165413));
                    this.a0.setVisibility(0);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.d0.getLayoutParams();
                    layoutParams.bottomMargin = getResources().getDimensionPixelSize(R.dimen.f17920_resource_name_obfuscated_RES_2131165411);
                    this.d0.setLayoutParams(layoutParams);
                    this.d0.setTextSize(0, getResources().getDimension(R.dimen.f17890_resource_name_obfuscated_RES_2131165408));
                    this.r0.B();
                    this.r0.z();
                }
            } else {
                return;
            }
        }
        this.r0.z();
    }

    @Override // defpackage.Wj1
    public void y(boolean z) {
        if (this.m0) {
            this.l0.cancel();
        }
        ColorDrawable Z = getBackground();
        int color = Z.getColor();
        int c = this.f9169J.c();
        if (Z.getColor() != c) {
            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(250L);
            this.l0 = duration;
            duration.setInterpolator(animation.InterpolatorC5286vf.e);
            this.l0.addUpdateListener(new RB(this, color, c, Z));
            this.l0.addListener(new SB(this, Z));
            this.l0.start();
            this.m0 = true;
            if (!z) {
                this.l0.end();
            }
        }
    }
}
