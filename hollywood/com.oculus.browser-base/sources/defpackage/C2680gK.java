package defpackage;

import android.text.InputFilter;
import android.text.Spanned;
import java.util.regex.Pattern;

/* renamed from: gK  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2680gK implements InputFilter {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Pattern f9989a;

    public C2680gK(View$OnClickListenerC3876nK nKVar, Pattern pattern) {
        this.f9989a = pattern;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        if (i != i2 && !this.f9989a.matcher(charSequence.subSequence(i, i2)).matches()) {
            return "";
        }
        return null;
    }
}
