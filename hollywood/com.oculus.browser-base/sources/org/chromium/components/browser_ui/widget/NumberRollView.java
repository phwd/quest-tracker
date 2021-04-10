package org.chromium.components.browser_ui.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.browser.R;
import java.text.NumberFormat;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NumberRollView extends FrameLayout {
    public static final Property F = new C4128oq0(Float.class, "");
    public TextView G;
    public TextView H;
    public float I;

    /* renamed from: J  reason: collision with root package name */
    public Animator f10821J;
    public int K;
    public int L;

    public NumberRollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(int i, boolean z) {
        Animator animator = this.f10821J;
        if (animator != null) {
            animator.cancel();
        }
        if (z) {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, F, (float) i);
            ofFloat.setInterpolator(animation.InterpolatorC5286vf.e);
            ofFloat.start();
            this.f10821J = ofFloat;
            return;
        }
        b((float) i);
    }

    public final void b(float f) {
        String str;
        String str2;
        this.I = f;
        int i = (int) f;
        int i2 = i + 1;
        NumberFormat integerInstance = NumberFormat.getIntegerInstance();
        if (this.K == 0) {
            str = integerInstance.format((long) i2);
        } else if (i2 != 0 || this.L == 0) {
            str = getResources().getQuantityString(this.K, i2, Integer.valueOf(i2));
        } else {
            str = getResources().getString(this.L);
        }
        if (!str.equals(this.G.getText().toString())) {
            this.G.setText(str);
        }
        if (this.K == 0) {
            str2 = integerInstance.format((long) i);
        } else if (i != 0 || this.L == 0) {
            str2 = getResources().getQuantityString(this.K, i, Integer.valueOf(i));
        } else {
            str2 = getResources().getString(this.L);
        }
        if (!str2.equals(this.H.getText().toString())) {
            this.H.setText(str2);
        }
        float f2 = f % 1.0f;
        TextView textView = this.G;
        textView.setTranslationY((f2 - 1.0f) * ((float) textView.getHeight()));
        TextView textView2 = this.H;
        textView2.setTranslationY(((float) textView2.getHeight()) * f2);
        this.G.setAlpha(f2);
        this.H.setAlpha(1.0f - f2);
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.G = (TextView) findViewById(R.id.up);
        this.H = (TextView) findViewById(R.id.down);
        b(this.I);
    }
}
