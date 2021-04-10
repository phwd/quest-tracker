package org.chromium.chrome.browser.gesturenav;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NavigationBubble extends LinearLayout {
    public final ValueAnimator F;
    public final int G = getResources().getColor(R.color.f11230_resource_name_obfuscated_RES_2131099813);
    public final int H = getResources().getColor(R.color.f14310_resource_name_obfuscated_RES_2131100121);
    public final String I;

    /* renamed from: J  reason: collision with root package name */
    public final String f10677J;
    public final C0644Km0 K;
    public TextView L;
    public ImageView M;
    public Animation.AnimationListener N;
    public boolean O;
    public int P;

    public NavigationBubble(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        C0644Km0 km0 = new C0644Km0(this, null);
        this.K = km0;
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(250L);
        this.F = duration;
        duration.addUpdateListener(km0);
        getBackground().setColorFilter(getResources().getColor(R.color.f14320_resource_name_obfuscated_RES_2131100122), PorterDuff.Mode.MULTIPLY);
        this.I = getResources().getString(R.string.f56870_resource_name_obfuscated_RES_2131953004, getContext().getString(R.string.f46950_resource_name_obfuscated_RES_2131952012));
        this.f10677J = getResources().getString(R.string.f56880_resource_name_obfuscated_RES_2131953005);
        this.P = 0;
    }

    public void a(boolean z, boolean z2) {
        if (z != this.O) {
            animate().alpha(z ? 0.5f : 1.0f).setDuration(z2 ? 400 : 0);
            this.O = z;
        }
    }

    public void b(boolean z) {
        C0644Km0 km0 = this.K;
        int i = z ? this.H : this.G;
        int i2 = z ? this.G : this.H;
        km0.F = i;
        km0.G = i2;
        this.F.start();
    }

    public void c(int i) {
        boolean z = true;
        if (i != 0) {
            if (!(this.L.getVisibility() == 0)) {
                if (this.P != i) {
                    this.P = i;
                    this.L.setText(i == 2 ? this.I : this.f10677J);
                }
                this.L.setVisibility(0);
                measure(0, 0);
                return;
            }
        }
        if (i == 0) {
            if (this.L.getVisibility() != 0) {
                z = false;
            }
            if (z) {
                this.L.setVisibility(8);
            }
        }
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.N;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.N;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    public void onFinishInflate() {
        super.onFinishInflate();
        this.M = (ImageView) findViewById(R.id.navigation_bubble_arrow);
        this.L = (TextView) findViewById(R.id.navigation_bubble_text);
    }
}
