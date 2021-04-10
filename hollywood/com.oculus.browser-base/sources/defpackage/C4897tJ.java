package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: tJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4897tJ implements AbstractC0203Dg1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AJ f11336a;

    public C4897tJ(AJ aj) {
        this.f11336a = aj;
    }

    @Override // defpackage.AbstractC0203Dg1
    public void a(TextInputLayout textInputLayout) {
        AutoCompleteTextView d = AJ.d(this.f11336a, textInputLayout.f9696J);
        AJ aj = this.f11336a;
        int i = aj.f9772a.n0;
        if (i == 2) {
            d.setDropDownBackgroundDrawable(aj.k);
        } else if (i == 1) {
            d.setDropDownBackgroundDrawable(aj.j);
        }
        AJ aj2 = this.f11336a;
        Objects.requireNonNull(aj2);
        if (d.getKeyListener() == null) {
            TextInputLayout textInputLayout2 = aj2.f9772a;
            int i2 = textInputLayout2.n0;
            if (i2 == 1 || i2 == 2) {
                C3234jd0 jd0 = textInputLayout2.j0;
                int b = AbstractC1226Uc0.b(d, R.attr.f3070_resource_name_obfuscated_RES_2130968753);
                int[][] iArr = {new int[]{16842919}, new int[0]};
                if (i2 == 2) {
                    int b2 = AbstractC1226Uc0.b(d, R.attr.f3250_resource_name_obfuscated_RES_2130968771);
                    C3234jd0 jd02 = new C3234jd0(jd0.H.f10150a);
                    int c = AbstractC1226Uc0.c(b, b2, 0.1f);
                    jd02.o(new ColorStateList(iArr, new int[]{c, 0}));
                    jd02.setTint(b2);
                    ColorStateList colorStateList = new ColorStateList(iArr, new int[]{c, b2});
                    C3234jd0 jd03 = new C3234jd0(jd0.H.f10150a);
                    jd03.setTint(-1);
                    LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, jd02, jd03), jd0});
                    AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                    d.setBackground(layerDrawable);
                } else if (i2 == 1) {
                    int i3 = aj2.f9772a.t0;
                    RippleDrawable rippleDrawable = new RippleDrawable(new ColorStateList(iArr, new int[]{AbstractC1226Uc0.c(b, i3, 0.1f), i3}), jd0, jd0);
                    AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                    d.setBackground(rippleDrawable);
                }
            } else {
                throw new IllegalStateException();
            }
        }
        AJ aj3 = this.f11336a;
        Objects.requireNonNull(aj3);
        d.setOnTouchListener(new View$OnTouchListenerC5237vJ(aj3, d));
        d.setOnFocusChangeListener(new View$OnFocusChangeListenerC5407wJ(aj3));
        d.setOnDismissListener(new C5577xJ(aj3));
        d.setThreshold(0);
        d.removeTextChangedListener(this.f11336a.d);
        d.addTextChangedListener(this.f11336a.d);
        textInputLayout.y(null);
        C0142Cg1 cg1 = this.f11336a.e;
        EditText editText = textInputLayout.f9696J;
        if (editText != null) {
            AbstractC1920bu1.n(editText, cg1);
        }
        textInputLayout.v(true);
    }
}
