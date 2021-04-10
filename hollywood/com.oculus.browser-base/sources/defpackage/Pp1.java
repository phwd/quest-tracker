package defpackage;

import com.google.android.gms.common.Feature;

/* renamed from: Pp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Pp1 extends UnsupportedOperationException {
    public final Feature F;

    public Pp1(Feature feature) {
        this.F = feature;
    }

    public final String getMessage() {
        String valueOf = String.valueOf(this.F);
        StringBuilder sb = new StringBuilder(valueOf.length() + 8);
        sb.append("Missing ");
        sb.append(valueOf);
        return sb.toString();
    }
}
