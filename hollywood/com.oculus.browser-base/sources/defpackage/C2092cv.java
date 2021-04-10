package defpackage;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.text.TextWatcher;
import android.view.View;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;

/* renamed from: cv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2092cv extends AbstractC2170dL {
    public final TextWatcher d = new C1268Uu(this);
    public final AbstractC0203Dg1 e = new C1390Wu(this);
    public AnimatorSet f;
    public ValueAnimator g;

    public C2092cv(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    @Override // defpackage.AbstractC2170dL
    public void a() {
        TextInputLayout textInputLayout = this.f9772a;
        textInputLayout.I0.setImageDrawable(AbstractC5544x8.a(this.b, R.drawable.f34110_resource_name_obfuscated_RES_2131231451));
        TextInputLayout textInputLayout2 = this.f9772a;
        textInputLayout2.s(textInputLayout2.getResources().getText(R.string.f49080_resource_name_obfuscated_RES_2131952225));
        TextInputLayout textInputLayout3 = this.f9772a;
        View$OnClickListenerC1451Xu xu = new View$OnClickListenerC1451Xu(this);
        CheckableImageButton checkableImageButton = textInputLayout3.I0;
        View.OnLongClickListener onLongClickListener = textInputLayout3.R0;
        checkableImageButton.setOnClickListener(xu);
        TextInputLayout.C(checkableImageButton, onLongClickListener);
        this.f9772a.a(this.e);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(P6.d);
        ofFloat.setDuration(150L);
        ofFloat.addUpdateListener(new C1921bv(this));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        TimeInterpolator timeInterpolator = P6.f8667a;
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration(100L);
        ofFloat2.addUpdateListener(new C1750av(this));
        AnimatorSet animatorSet = new AnimatorSet();
        this.f = animatorSet;
        animatorSet.playTogether(ofFloat, ofFloat2);
        this.f.addListener(new C1512Yu(this));
        ValueAnimator ofFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat3.setInterpolator(timeInterpolator);
        ofFloat3.setDuration(100L);
        ofFloat3.addUpdateListener(new C1750av(this));
        this.g = ofFloat3;
        ofFloat3.addListener(new C1573Zu(this));
    }

    @Override // defpackage.AbstractC2170dL
    public void c(boolean z) {
        if (this.f9772a.e0 != null) {
            d(z);
        }
    }

    public final void d(boolean z) {
        boolean z2 = this.f9772a.n() == z;
        if (z) {
            this.g.cancel();
            this.f.start();
            if (z2) {
                this.f.end();
                return;
            }
            return;
        }
        this.f.cancel();
        this.g.start();
        if (z2) {
            this.g.end();
        }
    }
}
