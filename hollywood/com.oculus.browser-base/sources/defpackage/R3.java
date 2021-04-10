package defpackage;

import J.N;
import android.text.TextUtils;

/* renamed from: R3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class R3 implements AbstractC4559rK {

    /* renamed from: a  reason: collision with root package name */
    public String f8807a;
    public boolean b;

    public R3(boolean z) {
        this.b = z;
    }

    @Override // defpackage.AbstractC4559rK
    public boolean a(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            return this.b;
        }
        return N.MgAmKYAu(charSequence.toString(), this.f8807a);
    }

    @Override // defpackage.AbstractC4559rK
    public boolean b(CharSequence charSequence) {
        return false;
    }
}
