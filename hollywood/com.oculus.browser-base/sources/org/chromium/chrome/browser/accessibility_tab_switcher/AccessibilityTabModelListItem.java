package org.chromium.chrome.browser.accessibility_tab_switcher;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabFavicon;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccessibilityTabModelListItem extends FrameLayout implements View.OnClickListener {
    public static final /* synthetic */ int F = 0;
    public int G;
    public int H;
    public int I;

    /* renamed from: J  reason: collision with root package name */
    public Animator f10605J;
    public final float K;
    public final float L;
    public final int M;
    public final int N;
    public final ColorStateList O;
    public final ColorStateList P;
    public final ColorStateList Q;
    public final ColorStateList R;
    public float S;
    public LinearLayout T;
    public TextView U;
    public TextView V;
    public ImageView W;
    public ImageButton a0;
    public LinearLayout b0;
    public Button c0;
    public Tab d0;
    public boolean e0;
    public boolean f0;
    public K g0;
    public final GestureDetector h0;
    public final int i0;
    public AccessibilityTabModelListView j0;
    public boolean k0;
    public final Runnable l0 = new M(this);
    public final Handler m0 = new Handler();
    public final AnimatorListenerAdapter n0 = new N(this);
    public final AnimatorListenerAdapter o0 = new O(this);
    public final AbstractC1404Xa1 p0 = new P(this);

    public AccessibilityTabModelListItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h0 = new GestureDetector(context, new Q(this, null));
        float dimension = context.getResources().getDimension(R.dimen.f25280_resource_name_obfuscated_RES_2131166147);
        this.K = dimension;
        this.L = dimension / 3.0f;
        this.i0 = context.getResources().getDimensionPixelOffset(R.dimen.f16430_resource_name_obfuscated_RES_2131165262);
        this.O = AbstractC2934hr.c(context, false);
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        this.P = context.getColorStateList(R.color.f11260_resource_name_obfuscated_RES_2131099816);
        this.Q = context.getColorStateList(R.color.f11340_resource_name_obfuscated_RES_2131099824);
        this.R = context.getColorStateList(R.color.f15610_resource_name_obfuscated_RES_2131100251);
        this.M = getResources().getInteger(R.integer.f35900_resource_name_obfuscated_RES_2131492887);
        this.N = getResources().getInteger(R.integer.f35910_resource_name_obfuscated_RES_2131492888);
        this.G = 100;
        this.H = 300;
        this.I = 4000;
    }

    public static void a(AccessibilityTabModelListItem accessibilityTabModelListItem, Tab tab) {
        K k = accessibilityTabModelListItem.g0;
        if (k != null) {
            tab.getId();
            k.f8333a.notifyDataSetChanged();
        }
    }

    public final void b() {
        Animator animator = this.f10605J;
        if (animator != null && animator.isRunning()) {
            this.f10605J.cancel();
        }
        this.f10605J = null;
    }

    public final void c() {
        b();
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "height", 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.SCALE_Y, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofInt, ofFloat);
        animatorSet.addListener(this.o0);
        animatorSet.setDuration((long) this.H);
        animatorSet.start();
        this.f10605J = animatorSet;
    }

    public final void d(boolean z) {
        int i;
        b();
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.TRANSLATION_X, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, View.ALPHA, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, View.SCALE_X, 1.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(this, View.SCALE_Y, 1.0f);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, "height", this.i0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4, ofInt);
        if (z) {
            i = this.G;
        } else {
            i = this.H;
        }
        animatorSet.setDuration((long) i);
        animatorSet.start();
        this.f10605J = animatorSet;
    }

    public final void e(long j) {
        b();
        this.S = getTranslationX();
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[1];
        fArr[0] = (float) (getTranslationX() > 0.0f ? getWidth() : -getWidth());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, property, fArr);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, View.ALPHA, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat2, ofFloat);
        animatorSet.addListener(this.n0);
        animatorSet.setDuration(Math.min(j, (long) this.H));
        animatorSet.start();
        this.f10605J = animatorSet;
    }

    public final void f(boolean z) {
        if (!z || !this.e0) {
            this.T.setVisibility(0);
            this.b0.setVisibility(4);
            h();
            g();
            return;
        }
        this.b0.setVisibility(0);
        this.T.setVisibility(4);
        this.b0.requestFocus();
    }

    public final void g() {
        Tab tab = this.d0;
        if (tab != null) {
            Bitmap k = TabFavicon.k(tab);
            if (k != null) {
                this.W.setImageTintList(null);
                this.W.setImageBitmap(k);
                return;
            }
            this.W.setImageResource(R.drawable.f30310_resource_name_obfuscated_RES_2131231071);
            this.W.setImageTintList(this.d0.a() ? this.P : this.O);
        }
    }

    public final void h() {
        String str;
        String str2;
        Tab tab = this.d0;
        String str3 = null;
        if (tab == null || !tab.isInitialized()) {
            str = null;
        } else {
            str3 = this.d0.getTitle();
            str = this.d0.s();
            if (TextUtils.isEmpty(str3)) {
                str3 = str;
            }
        }
        if (TextUtils.isEmpty(str3)) {
            str3 = getContext().getResources().getString(R.string.f63150_resource_name_obfuscated_RES_2131953632);
        }
        if (!str3.equals(this.U.getText())) {
            this.U.setText(str3);
        }
        if (this.f0) {
            str2 = getContext().getString(R.string.f46120_resource_name_obfuscated_RES_2131951929, str3);
        } else {
            str2 = getContext().getString(R.string.f46110_resource_name_obfuscated_RES_2131951928, str3);
        }
        if (!str2.equals(getContentDescription())) {
            setContentDescription(str2);
            this.a0.setContentDescription(getContext().getString(R.string.f46030_resource_name_obfuscated_RES_2131951920, str3));
        }
        if (this.d0.a()) {
            setBackgroundResource(R.color.f10880_resource_name_obfuscated_RES_2131099778);
            this.W.getBackground().setLevel(this.N);
            AbstractC3153j7.i(this.U, R.style.f71870_resource_name_obfuscated_RES_2132017760);
            AbstractC3153j7.i(this.V, R.style.f72000_resource_name_obfuscated_RES_2132017773);
            this.a0.setImageTintList(this.R);
        } else {
            setBackgroundResource(R.color.f10840_resource_name_obfuscated_RES_2131099774);
            this.W.getBackground().setLevel(this.M);
            AbstractC3153j7.i(this.U, R.style.f71850_resource_name_obfuscated_RES_2132017758);
            AbstractC3153j7.i(this.V, R.style.f72010_resource_name_obfuscated_RES_2132017774);
            this.a0.setImageTintList(this.Q);
        }
        if (TextUtils.isEmpty(str)) {
            this.V.setVisibility(8);
            return;
        }
        this.V.setText(str);
        this.V.setVisibility(0);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.d0 != null) {
            g();
            h();
            this.d0.A(this.p0);
        }
    }

    public void onClick(View view) {
        if (this.g0 != null) {
            int id = this.d0.getId();
            if (view == this && !this.g0.a(id)) {
                K k = this.g0;
                C2090cu0 cu0 = k.f8333a.I;
                if (cu0 != null) {
                    cu0.Q(id, true);
                }
                TabModel tabModel = k.f8333a.H;
                tabModel.x(AbstractC1160Ta1.e(tabModel, id), 3);
                k.f8333a.notifyDataSetChanged();
            } else if (view == this.a0) {
                this.k0 = true;
                if (this.e0) {
                    b();
                    this.S = 0.0f;
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, View.SCALE_X, 1.2f);
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this, View.SCALE_Y, 0.0f);
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(this, View.ALPHA, 0.0f);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(ofFloat3, ofFloat2, ofFloat);
                    animatorSet.addListener(this.n0);
                    animatorSet.setDuration((long) this.G);
                    animatorSet.start();
                    this.f10605J = animatorSet;
                    return;
                }
                c();
            } else {
                Button button = this.c0;
                if (view == button) {
                    this.c0.announceForAccessibility(button.getContext().getString(R.string.f46330_resource_name_obfuscated_RES_2131951950, this.d0.getTitle()));
                    this.m0.removeCallbacks(this.l0);
                    K k2 = this.g0;
                    k2.f8333a.H.s(id);
                    k2.f8333a.notifyDataSetChanged();
                    f(false);
                    setAlpha(0.0f);
                    float f = this.S;
                    if (f > 0.0f) {
                        setTranslationX((float) getWidth());
                        d(false);
                    } else if (f < 0.0f) {
                        setTranslationX((float) (-getWidth()));
                        d(false);
                    } else {
                        setScaleX(1.2f);
                        setScaleY(0.0f);
                        d(true);
                    }
                }
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Tab tab = this.d0;
        if (tab != null) {
            tab.I(this.p0);
        }
        b();
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.content);
        this.T = linearLayout;
        this.U = (TextView) linearLayout.findViewById(R.id.title);
        this.V = (TextView) this.T.findViewById(R.id.description);
        this.W = (ImageView) this.T.findViewById(R.id.start_icon);
        this.a0 = (ImageButton) this.T.findViewById(R.id.end_button);
        this.W.setBackgroundResource(R.drawable.f33510_resource_name_obfuscated_RES_2131231391);
        this.b0 = (LinearLayout) findViewById(R.id.undo_contents);
        this.c0 = (Button) findViewById(R.id.undo_button);
        setClickable(true);
        setFocusable(true);
        this.a0.setOnClickListener(this);
        this.c0.setOnClickListener(this);
        setOnClickListener(this);
        this.a0.setVisibility(0);
        this.a0.setImageResource(R.drawable.f28450_resource_name_obfuscated_RES_2131230885);
        this.a0.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        this.a0.setPaddingRelative(getResources().getDimensionPixelSize(R.dimen.f16450_resource_name_obfuscated_RES_2131165264), getPaddingTop(), getResources().getDimensionPixelSize(R.dimen.f16440_resource_name_obfuscated_RES_2131165263), getPaddingBottom());
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        this.m0.removeCallbacks(this.l0);
        if (this.h0.onTouchEvent(motionEvent)) {
            return true;
        }
        if (motionEvent.getActionMasked() != 1) {
            return super.onTouchEvent(motionEvent);
        }
        if (Math.abs(getTranslationX()) > this.K) {
            e(300);
        } else {
            d(false);
        }
        this.j0.G = true;
        return true;
    }

    public void setHeight(int i) {
        AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new AbsListView.LayoutParams(-1, i);
        } else if (layoutParams.height != i) {
            layoutParams.height = i;
        } else {
            return;
        }
        setLayoutParams(layoutParams);
    }
}
