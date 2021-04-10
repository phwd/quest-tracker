package defpackage;

import java.io.Closeable;
import java.io.IOException;

/* renamed from: O21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class O21 {
    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
