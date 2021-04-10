package defpackage;

import android.os.Bundle;
import android.util.Log;

/* renamed from: zC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5898zC1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11733a;
    public final C0563Je1 b = new C0563Je1();
    public final int c;
    public final Bundle d;

    public C5898zC1(int i, Bundle bundle) {
        this.f11733a = i;
        this.c = 1;
        this.d = bundle;
    }

    public final void a(C5218vC1 vc1) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(vc1);
            valueOf.length();
            valueOf2.length();
        }
        this.b.a(vc1);
    }

    public String toString() {
        int i = this.c;
        int i2 = this.f11733a;
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=false}");
        return sb.toString();
    }
}
