package defpackage;

import android.widget.EditText;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: Cy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0180Cy0 implements AbstractC0203Dg1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0363Fy0 f7850a;

    public C0180Cy0(C0363Fy0 fy0) {
        this.f7850a = fy0;
    }

    @Override // defpackage.AbstractC0203Dg1
    public void a(TextInputLayout textInputLayout) {
        EditText editText = textInputLayout.f9696J;
        textInputLayout.v(true);
        C0363Fy0 fy0 = this.f7850a;
        fy0.c.setChecked(!C0363Fy0.d(fy0));
        editText.removeTextChangedListener(this.f7850a.d);
        editText.addTextChangedListener(this.f7850a.d);
    }
}
