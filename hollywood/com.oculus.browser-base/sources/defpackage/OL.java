package defpackage;

import java.util.Locale;

/* renamed from: OL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OL extends Exception {
    public OL(int i) {
        super(String.format(Locale.US, "Error code %d is not supported", Integer.valueOf(i)));
    }
}
