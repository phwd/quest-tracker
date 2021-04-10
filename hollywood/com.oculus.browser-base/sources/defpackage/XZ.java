package defpackage;

import org.chromium.base.ThreadUtils;

/* renamed from: XZ  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class XZ {
    public static void a() {
        if (!ThreadUtils.i()) {
            throw new AssertionError("Should be on UI thread.");
        }
    }
}
