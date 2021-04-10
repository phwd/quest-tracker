package defpackage;

import android.os.Build;
import android.os.Bundle;

/* renamed from: H  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class H {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8127a;

    public H() {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f8127a = new G(this);
        } else {
            this.f8127a = new F(this);
        }
    }

    public D a(int i) {
        return null;
    }

    public D b(int i) {
        return null;
    }

    public boolean c(int i, int i2, Bundle bundle) {
        return false;
    }

    public H(Object obj) {
        this.f8127a = obj;
    }
}
