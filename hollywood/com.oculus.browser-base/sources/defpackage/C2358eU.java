package defpackage;

import android.os.Bundle;

/* renamed from: eU  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2358eU implements AbstractC3042iU {
    public C2358eU(AbstractC2188dU dUVar) {
    }

    @Override // defpackage.AbstractC3042iU
    public String a(Object obj, String str) {
        return ((Bundle) obj).getString(str);
    }

    @Override // defpackage.AbstractC3042iU
    public String[] b(Object obj, String str) {
        return ((Bundle) obj).getStringArray(str);
    }

    @Override // defpackage.AbstractC3042iU
    public boolean c(Object obj, String str) {
        return ((Bundle) obj).containsKey(str);
    }
}
