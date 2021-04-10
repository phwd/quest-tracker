package defpackage;

import android.text.TextUtils;
import android.util.Log;
import java.util.Locale;

/* renamed from: NF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NF1 {

    /* renamed from: a  reason: collision with root package name */
    public final String f8536a;
    public final boolean b;
    public String c;

    public NF1(String str) {
        SE0.g(str, "The log tag cannot be null or empty.");
        this.f8536a = str;
        this.b = str.length() <= 23;
    }

    public final void a(String str, Object... objArr) {
        Log.e(this.f8536a, c(str, objArr));
    }

    public final void b(String str, Object... objArr) {
        Log.w(this.f8536a, c(str, objArr));
    }

    public final String c(String str, Object... objArr) {
        if (objArr.length != 0) {
            str = String.format(Locale.ROOT, str, objArr);
        }
        if (TextUtils.isEmpty(this.c)) {
            return str;
        }
        String valueOf = String.valueOf(this.c);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final boolean d() {
        return this.b && Log.isLoggable(this.f8536a, 3);
    }
}
