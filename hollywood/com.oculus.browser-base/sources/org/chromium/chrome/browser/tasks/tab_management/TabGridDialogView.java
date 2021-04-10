package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.util.HashMap;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TabGridDialogView extends FrameLayout {
    public final Context F;
    public final int G;
    public final int H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public View f10781J;
    public View K;
    public View L;
    public View M;
    public ViewGroup N;
    public ViewGroup O;
    public TextView P;
    public RelativeLayout Q;
    public UH0 R;
    public GP0 S;
    public FrameLayout.LayoutParams T;
    public ViewTreeObserver.OnGlobalLayoutListener U;
    public Animator V;
    public Animator W;
    public AnimatorSet a0;
    public AnimatorSet b0;
    public ObjectAnimator c0;
    public ObjectAnimator d0;
    public AnimatorSet e0;
    public AnimatorSet f0;
    public AnimatorListenerAdapter g0;
    public AnimatorListenerAdapter h0;
    public Map i0 = new HashMap();
    public int j0;
    public int k0;
    public int l0;
    public int m0;
    public int n0;
    public int o0 = 1;
    public int p0 = R.color.f15230_resource_name_obfuscated_RES_2131100213;
    public int q0 = R.color.f15150_resource_name_obfuscated_RES_2131100205;
    public int r0 = R.style.f72050_resource_name_obfuscated_RES_2132017778;

    public TabGridDialogView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.F = context;
        this.I = context.getResources().getDimension(R.dimen.f25610_resource_name_obfuscated_RES_2131166180);
        this.G = (int) context.getResources().getDimension(R.dimen.f25560_resource_name_obfuscated_RES_2131166175);
        this.H = (int) context.getResources().getDimension(R.dimen.f16870_resource_name_obfuscated_RES_2131165306);
    }

    public void a(int i) {
        if (i == 1) {
            this.j0 = (int) this.F.getResources().getDimension(R.dimen.f25390_resource_name_obfuscated_RES_2131166158);
            this.k0 = (int) this.F.getResources().getDimension(R.dimen.f25400_resource_name_obfuscated_RES_2131166159);
        } else {
            this.j0 = (int) this.F.getResources().getDimension(R.dimen.f25400_resource_name_obfuscated_RES_2131166159);
            this.k0 = (int) this.F.getResources().getDimension(R.dimen.f25390_resource_name_obfuscated_RES_2131166158);
        }
        FrameLayout.LayoutParams layoutParams = this.T;
        int i2 = this.j0;
        int i3 = this.k0;
        layoutParams.setMargins(i2, i3, i2, i3);
        this.l0 = i;
    }

    public final void b(boolean z) {
        int i;
        int i2;
        this.M.bringToFront();
        GradientDrawable gradientDrawable = (GradientDrawable) this.P.getBackground();
        Context context = this.F;
        if (z) {
            i = this.q0;
        } else {
            i = this.p0;
        }
        Object obj = K2.f8337a;
        gradientDrawable.setColor(context.getColor(i));
        TextView textView = this.P;
        Context context2 = this.F;
        if (z) {
            i2 = R.style.f72120_resource_name_obfuscated_RES_2132017785;
        } else {
            i2 = this.r0;
        }
        textView.setTextAppearance(context2, i2);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewGroup viewGroup = (ViewGroup) getParent();
        this.O = viewGroup;
        this.m0 = viewGroup.getHeight();
        this.n0 = this.O.getWidth();
        this.U = new ViewTreeObserver$OnGlobalLayoutListenerC2131d71(this);
        this.O.getViewTreeObserver().addOnGlobalLayoutListener(this.U);
        setVisibility(8);
    }

    public void onConfigurationChanged(Configuration configuration) {
        a(configuration.orientation);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ViewGroup viewGroup = this.O;
        if (viewGroup != null) {
            viewGroup.getViewTreeObserver().removeOnGlobalLayoutListener(this.U);
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.T = new FrameLayout.LayoutParams(-1, -1);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.dialog_container_view);
        this.Q = relativeLayout;
        relativeLayout.setLayoutParams(this.T);
        Drawable background = this.Q.getBackground();
        Context context = this.F;
        Object obj = K2.f8337a;
        background.setTint(context.getColor(R.color.f10940_resource_name_obfuscated_RES_2131099784));
        View findViewById = findViewById(R.id.dialog_ungroup_bar);
        this.M = findViewById;
        this.P = (TextView) findViewById.findViewById(R.id.dialog_ungroup_bar_text);
        View findViewById2 = findViewById(R.id.dialog_frame);
        this.f10781J = findViewById2;
        findViewById2.setLayoutParams(this.T);
        this.K = findViewById(R.id.dialog_animation_card_view);
        this.N = (ViewGroup) findViewById(R.id.dialog_snack_bar_container_view);
        a(this.F.getResources().getConfiguration().orientation);
        this.e0 = new AnimatorSet();
        this.f0 = new AnimatorSet();
        this.a0 = new AnimatorSet();
        this.a0.play(ObjectAnimator.ofFloat(this.Q, View.ALPHA, 0.0f, 1.0f));
        this.a0.setInterpolator(animation.InterpolatorC5286vf.g);
        this.a0.setDuration(300L);
        this.b0 = new AnimatorSet();
        this.b0.play(ObjectAnimator.ofFloat(this.Q, View.ALPHA, 1.0f, 0.0f));
        this.b0.setInterpolator(animation.InterpolatorC5286vf.f);
        this.b0.setDuration(300L);
        this.b0.addListener(new C2472f71(this));
        this.g0 = new C2643g71(this);
        this.h0 = new C2814h71(this);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.M, View.TRANSLATION_Y, (float) this.H, 0.0f);
        this.c0 = ofFloat;
        ofFloat.setDuration(300L);
        this.c0.setInterpolator(G30.e);
        this.c0.addListener(new C2985i71(this));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.M, View.TRANSLATION_Y, 0.0f, (float) this.H);
        this.d0 = ofFloat2;
        ofFloat2.setDuration(300L);
        this.d0.setInterpolator(G30.b);
        this.d0.addListener(new C3155j71(this));
    }
}
