package defpackage;

import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;

/* renamed from: KF1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class KF1 {

    /* renamed from: a  reason: collision with root package name */
    public static final C2299e7 f8354a = new C2299e7();
    public static boolean b = true;

    static {
        try {
            Charset.forName("UTF-8");
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
        }
        GF1.e("com.google.cast.multizone");
    }
}
