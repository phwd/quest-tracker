package defpackage;

/* renamed from: OV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OV {
    public OV(String str) {
        SE0.i(str, "log tag cannot be null");
        Object[] objArr = {str, 23};
        if (!(str.length() <= 23)) {
            throw new IllegalArgumentException(String.format("tag \"%s\" is longer than the %d character maximum", objArr));
        }
    }
}
