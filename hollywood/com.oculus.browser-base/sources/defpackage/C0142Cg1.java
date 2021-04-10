package defpackage;

import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputLayout;

/* renamed from: Cg1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0142Cg1 extends C5349w {
    public final TextInputLayout d;

    public C0142Cg1(TextInputLayout textInputLayout) {
        this.d = textInputLayout;
    }

    @Override // defpackage.C5349w
    public void d(View view, D d2) {
        String str;
        String str2;
        TextView textView;
        this.b.onInitializeAccessibilityNodeInfo(view, d2.b);
        EditText editText = this.d.f9696J;
        CharSequence charSequence = null;
        Editable text = editText != null ? editText.getText() : null;
        CharSequence j = this.d.j();
        TextInputLayout textInputLayout = this.d;
        C1941c10 c10 = textInputLayout.L;
        CharSequence charSequence2 = c10.r ? c10.q : null;
        CharSequence i = textInputLayout.i();
        TextInputLayout textInputLayout2 = this.d;
        int i2 = textInputLayout2.N;
        if (textInputLayout2.M && textInputLayout2.O && (textView = textInputLayout2.P) != null) {
            charSequence = textView.getContentDescription();
        }
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(text);
        boolean z3 = !TextUtils.isEmpty(j);
        boolean z4 = !TextUtils.isEmpty(charSequence2);
        boolean z5 = !TextUtils.isEmpty(i);
        if (!z5 && TextUtils.isEmpty(charSequence)) {
            z = false;
        }
        if (z3) {
            str = j.toString();
        } else {
            str = "";
        }
        StringBuilder i3 = AbstractC2531fV.i(str);
        if ((z5 || z4) && !TextUtils.isEmpty(str)) {
            str2 = ", ";
        } else {
            str2 = "";
        }
        i3.append(str2);
        StringBuilder i4 = AbstractC2531fV.i(i3.toString());
        if (z5) {
            charSequence2 = i;
        } else if (!z4) {
            charSequence2 = "";
        }
        i4.append((Object) charSequence2);
        String sb = i4.toString();
        if (z2) {
            d2.b.setText(text);
        } else if (!TextUtils.isEmpty(sb)) {
            d2.b.setText(sb);
        }
        if (!TextUtils.isEmpty(sb)) {
            int i5 = Build.VERSION.SDK_INT;
            if (i5 >= 26) {
                d2.k(sb);
            } else {
                if (z2) {
                    sb = ((Object) text) + ", " + sb;
                }
                d2.b.setText(sb);
            }
            boolean z6 = !z2;
            if (i5 >= 26) {
                d2.b.setShowingHintText(z6);
            } else {
                d2.h(4, z6);
            }
        }
        if (text == null || text.length() != i2) {
            i2 = -1;
        }
        d2.b.setMaxTextLength(i2);
        if (z) {
            if (!z5) {
                i = charSequence;
            }
            d2.b.setError(i);
        }
    }
}
