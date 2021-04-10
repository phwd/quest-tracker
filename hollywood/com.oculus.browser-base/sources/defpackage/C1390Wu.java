package defpackage;

import android.widget.EditText;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: Wu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1390Wu implements AbstractC0203Dg1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C2092cv f9179a;

    public C1390Wu(C2092cv cvVar) {
        this.f9179a = cvVar;
    }

    @Override // defpackage.AbstractC0203Dg1
    public void a(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.f9696J;
        textInputLayout.v(editText.getText().length() > 0);
        CheckableImageButton checkableImageButton = textInputLayout.I0;
        if (checkableImageButton.f9693J) {
            checkableImageButton.f9693J = false;
            checkableImageButton.sendAccessibilityEvent(0);
        }
        editText.setOnFocusChangeListener(new View$OnFocusChangeListenerC1329Vu(this));
        editText.removeTextChangedListener(this.f9179a.d);
        editText.addTextChangedListener(this.f9179a.d);
    }
}
