package defpackage;

import android.util.Patterns;

/* renamed from: qy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4493qy implements AbstractC4559rK {
    public C4493qy(C4663ry ryVar) {
    }

    @Override // defpackage.AbstractC4559rK
    public boolean a(CharSequence charSequence) {
        return charSequence != null && Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }

    @Override // defpackage.AbstractC4559rK
    public boolean b(CharSequence charSequence) {
        return false;
    }
}
