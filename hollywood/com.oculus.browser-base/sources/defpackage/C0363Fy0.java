package defpackage;

import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import com.oculus.browser.R;

/* renamed from: Fy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0363Fy0 extends AbstractC2170dL {
    public final TextWatcher d = new C0119By0(this);
    public final AbstractC0203Dg1 e = new C0180Cy0(this);
    public final C0241Dy0 f = new C0241Dy0(this);

    public C0363Fy0(TextInputLayout textInputLayout) {
        super(textInputLayout);
    }

    public static boolean d(C0363Fy0 fy0) {
        EditText editText = fy0.f9772a.f9696J;
        return editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    @Override // defpackage.AbstractC2170dL
    public void a() {
        this.f9772a.I0.setImageDrawable(AbstractC5544x8.a(this.b, R.drawable.f29090_resource_name_obfuscated_RES_2131230949));
        TextInputLayout textInputLayout = this.f9772a;
        textInputLayout.s(textInputLayout.getResources().getText(R.string.f58070_resource_name_obfuscated_RES_2131953124));
        TextInputLayout textInputLayout2 = this.f9772a;
        View$OnClickListenerC0302Ey0 ey0 = new View$OnClickListenerC0302Ey0(this);
        CheckableImageButton checkableImageButton = textInputLayout2.I0;
        View.OnLongClickListener onLongClickListener = textInputLayout2.R0;
        checkableImageButton.setOnClickListener(ey0);
        TextInputLayout.C(checkableImageButton, onLongClickListener);
        this.f9772a.a(this.e);
        this.f9772a.J0.add(this.f);
        EditText editText = this.f9772a.f9696J;
        if (editText != null && (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224)) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
