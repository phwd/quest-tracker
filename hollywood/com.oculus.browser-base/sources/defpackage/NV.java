package defpackage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.util.Arrays;
import java.util.Objects;

/* renamed from: NV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class NV {

    /* renamed from: a  reason: collision with root package name */
    public static final Uri f8550a = new Uri.Builder().scheme("content").authority("com.google.android.gms.chimera").build();
    public final String b;
    public final String c;
    public final ComponentName d;
    public final int e;
    public final boolean f;

    public NV(String str, String str2, int i) {
        SE0.f(str);
        this.b = str;
        SE0.f(str2);
        this.c = str2;
        this.d = null;
        this.e = i;
        this.f = false;
    }

    public final Intent a(Context context) {
        if (this.b == null) {
            return new Intent().setComponent(this.d);
        }
        Intent intent = null;
        if (this.f) {
            Bundle bundle = new Bundle();
            bundle.putString("serviceActionBundleKey", this.b);
            Bundle call = context.getContentResolver().call(f8550a, "serviceIntentCall", (String) null, bundle);
            if (call != null) {
                intent = (Intent) call.getParcelable("serviceResponseIntentKey");
            }
            if (intent == null) {
                String valueOf = String.valueOf(this.b);
                Log.w("ConnectionStatusConfig", valueOf.length() != 0 ? "Dynamic lookup for intent failed for action: ".concat(valueOf) : new String("Dynamic lookup for intent failed for action: "));
            }
        }
        if (intent == null) {
            return new Intent(this.b).setPackage(this.c);
        }
        return intent;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NV)) {
            return false;
        }
        NV nv = (NV) obj;
        return AbstractC0895Oq0.a(this.b, nv.b) && AbstractC0895Oq0.a(this.c, nv.c) && AbstractC0895Oq0.a(this.d, nv.d) && this.e == nv.e && this.f == nv.f;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.b, this.c, this.d, Integer.valueOf(this.e), Boolean.valueOf(this.f)});
    }

    public final String toString() {
        String str = this.b;
        return str == null ? this.d.flattenToString() : str;
    }

    public NV(String str, String str2, int i, boolean z) {
        SE0.f(str);
        this.b = str;
        SE0.f(str2);
        this.c = str2;
        this.d = null;
        this.e = i;
        this.f = z;
    }

    public NV(ComponentName componentName) {
        this.b = null;
        this.c = null;
        Objects.requireNonNull(componentName, "null reference");
        this.d = componentName;
        this.e = 129;
        this.f = false;
    }
}
