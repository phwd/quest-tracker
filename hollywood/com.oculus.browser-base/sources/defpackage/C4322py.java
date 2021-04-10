package defpackage;

import android.telephony.PhoneNumberUtils;

/* renamed from: py  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4322py implements AbstractC4559rK {
    public C4322py(C4663ry ryVar) {
    }

    @Override // defpackage.AbstractC4559rK
    public boolean a(CharSequence charSequence) {
        boolean z;
        P21 f0 = P21.f0();
        if (charSequence != null) {
            try {
                if (PhoneNumberUtils.isGlobalPhoneNumber(PhoneNumberUtils.stripSeparators(charSequence.toString()))) {
                    z = true;
                    f0.close();
                    return z;
                }
            } catch (Throwable th) {
                AbstractC0754Mh1.f8495a.a(th, th);
            }
        }
        z = false;
        f0.close();
        return z;
        throw th;
    }

    @Override // defpackage.AbstractC4559rK
    public boolean b(CharSequence charSequence) {
        return false;
    }
}
