package defpackage;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.StateListDrawable;
import android.text.TextWatcher;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: AJ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AJ extends AbstractC2170dL {
    public final TextWatcher d = new C4556rJ(this);
    public final C0142Cg1 e = new C4727sJ(this, this.f9772a);
    public final AbstractC0203Dg1 f = new C4897tJ(this);
    public boolean g = false;
    public boolean h = false;
    public long i = Long.MAX_VALUE;
    public StateListDrawable j;
    public C3234jd0 k;
    public AccessibilityManager l;
    public ValueAnimator m;
    public ValueAnimator n;

    public AJ(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    public static AutoCompleteTextView d(AJ aj, EditText editText) {
        Objects.requireNonNull(aj);
        if (editText instanceof AutoCompleteTextView) {
            return (AutoCompleteTextView) editText;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    public static void e(AJ aj, boolean z) {
        if (aj.h != z) {
            aj.h = z;
            aj.n.cancel();
            aj.m.start();
        }
    }

    public static void f(AJ aj, AutoCompleteTextView autoCompleteTextView) {
        Objects.requireNonNull(aj);
        if (autoCompleteTextView != null) {
            if (aj.h()) {
                aj.g = false;
            }
            if (!aj.g) {
                boolean z = aj.h;
                boolean z2 = !z;
                if (z != z2) {
                    aj.h = z2;
                    aj.n.cancel();
                    aj.m.start();
                }
                if (aj.h) {
                    autoCompleteTextView.requestFocus();
                    autoCompleteTextView.showDropDown();
                    return;
                }
                autoCompleteTextView.dismissDropDown();
                return;
            }
            aj.g = false;
        }
    }

    @Override // defpackage.AbstractC2170dL
    public void a() {
        float dimensionPixelOffset = (float) this.b.getResources().getDimensionPixelOffset(R.dimen.f22400_resource_name_obfuscated_RES_2131165859);
        float dimensionPixelOffset2 = (float) this.b.getResources().getDimensionPixelOffset(R.dimen.f21980_resource_name_obfuscated_RES_2131165817);
        int dimensionPixelOffset3 = this.b.getResources().getDimensionPixelOffset(R.dimen.f22000_resource_name_obfuscated_RES_2131165819);
        C3234jd0 g2 = g(dimensionPixelOffset, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        C3234jd0 g3 = g(0.0f, dimensionPixelOffset, dimensionPixelOffset2, dimensionPixelOffset3);
        this.k = g2;
        StateListDrawable stateListDrawable = new StateListDrawable();
        this.j = stateListDrawable;
        stateListDrawable.addState(new int[]{16842922}, g2);
        this.j.addState(new int[0], g3);
        TextInputLayout textInputLayout = this.f9772a;
        textInputLayout.I0.setImageDrawable(AbstractC5544x8.a(this.b, R.drawable.f34080_resource_name_obfuscated_RES_2131231448));
        TextInputLayout textInputLayout2 = this.f9772a;
        textInputLayout2.s(textInputLayout2.getResources().getText(R.string.f52120_resource_name_obfuscated_RES_2131952529));
        TextInputLayout textInputLayout3 = this.f9772a;
        View$OnClickListenerC5067uJ uJVar = new View$OnClickListenerC5067uJ(this);
        CheckableImageButton checkableImageButton = textInputLayout3.I0;
        View.OnLongClickListener onLongClickListener = textInputLayout3.R0;
        checkableImageButton.setOnClickListener(uJVar);
        TextInputLayout.C(checkableImageButton, onLongClickListener);
        this.f9772a.a(this.f);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        TimeInterpolator timeInterpolator = P6.f8667a;
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.setDuration((long) 67);
        ofFloat.addUpdateListener(new C5917zJ(this));
        this.n = ofFloat;
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        ofFloat2.setInterpolator(timeInterpolator);
        ofFloat2.setDuration((long) 50);
        ofFloat2.addUpdateListener(new C5917zJ(this));
        this.m = ofFloat2;
        ofFloat2.addListener(new C5747yJ(this));
        CheckableImageButton checkableImageButton2 = this.c;
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        checkableImageButton2.setImportantForAccessibility(2);
        this.l = (AccessibilityManager) this.b.getSystemService("accessibility");
    }

    @Override // defpackage.AbstractC2170dL
    public boolean b(int i2) {
        return i2 != 0;
    }

    public final C3234jd0 g(float f2, float f3, float f4, int i2) {
        C3382kT0 kt0 = new C3382kT0();
        kt0.e = new C2107d(f2);
        kt0.f = new C2107d(f2);
        kt0.h = new C2107d(f3);
        kt0.g = new C2107d(f3);
        C3553lT0 a2 = kt0.a();
        Context context = this.b;
        String str = C3234jd0.F;
        int c = AbstractC0251Ec0.c(context, R.attr.f3250_resource_name_obfuscated_RES_2130968771, C3234jd0.class.getSimpleName());
        C3234jd0 jd0 = new C3234jd0();
        jd0.H.b = new EK(context);
        jd0.s();
        jd0.o(ColorStateList.valueOf(c));
        C3064id0 id0 = jd0.H;
        if (id0.o != f4) {
            id0.o = f4;
            jd0.s();
        }
        jd0.H.f10150a = a2;
        jd0.invalidateSelf();
        C3064id0 id02 = jd0.H;
        if (id02.i == null) {
            id02.i = new Rect();
        }
        jd0.H.i.set(0, i2, 0, i2);
        jd0.invalidateSelf();
        return jd0;
    }

    public final boolean h() {
        long currentTimeMillis = System.currentTimeMillis() - this.i;
        return currentTimeMillis < 0 || currentTimeMillis > 300;
    }
}
