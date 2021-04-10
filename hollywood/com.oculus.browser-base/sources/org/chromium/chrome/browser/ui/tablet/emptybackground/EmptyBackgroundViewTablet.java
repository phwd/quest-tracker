package org.chromium.chrome.browser.ui.tablet.emptybackground;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.ui.tablet.emptybackground.incognitotoggle.IncognitoToggleButtonTablet;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class EmptyBackgroundViewTablet extends FrameLayout {
    public static final /* synthetic */ int F = 0;
    public AbstractC0124Ca1 G;
    public A61 H;
    public Animator I;

    /* renamed from: J  reason: collision with root package name */
    public Animator f10793J;
    public Animator K;
    public IncognitoToggleButtonTablet L;

    public EmptyBackgroundViewTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        findViewById(R.id.empty_new_tab_button).setOnClickListener(new GK(this));
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(R.style.f73990_resource_name_obfuscated_RES_2132017972, new int[]{16842997});
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, 0);
        obtainStyledAttributes.recycle();
        View findViewById = findViewById(R.id.empty_layout_button_container);
        float f = (float) (-dimensionPixelSize);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(findViewById, View.TRANSLATION_Y, f, 0.0f);
        this.f10793J = ofFloat;
        ofFloat.setDuration(200L);
        this.f10793J.addListener(new HK(this));
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(findViewById, View.TRANSLATION_Y, 0.0f, f);
        this.K = ofFloat2;
        ofFloat2.setDuration(200L);
        this.K.addListener(new IK(this));
    }
}
