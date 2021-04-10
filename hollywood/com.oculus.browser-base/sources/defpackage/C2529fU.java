package defpackage;

import android.os.Bundle;

/* renamed from: fU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2529fU implements AbstractC3212jU {
    public C2529fU(C3383kU kUVar, AbstractC2188dU dUVar) {
    }

    @Override // defpackage.AbstractC3212jU
    public Object a() {
        return new Bundle();
    }

    @Override // defpackage.AbstractC3212jU
    public void b(Object obj, String str, String[] strArr) {
        ((Bundle) obj).putStringArray(str, strArr);
    }

    @Override // defpackage.AbstractC3212jU
    public void c(Object obj, String str, String str2) {
        ((Bundle) obj).putString(str, str2);
    }
}
